package com.waes.scalable.web.diff.api.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Paths;
import java.util.Calendar;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.waes.scalable.web.data.Diff;
import com.waes.scalable.web.data.IDiffRepository;
import com.waes.scalable.web.diff.api.DiffMethod;
import com.waes.scalable.web.diff.api.IDiffManager;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DiffManagerImplIT {

	@Autowired
	private IDiffManager SUT;	
	
	@Autowired
	private IDiffRepository diffDAO;
	
	/** The Constant ROOT_FOLDER. */
	private static final String ROOT_FOLDER = "src/test/resources/";
	
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	   this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Before
	public void setUp() {
		diffDAO.deleteAll();
	}
	
	/**
	 * Test create happy case.
	 */
	@Test
	public void testCreate() {

		// Exercis the SUT
		Diff diff = SUT.create();
		
		// Verify 
		assertNumDiff(1);
		assertTrue(diff.getId() > 0);
		assertNotNull(diff.getCreatedAt());
		assertNull(diff.getLastUpdate());
		assertNull(diff.getResult());
		assertEquals("API", diff.getOrigin());
	}
	
	/**
	 * Test get Diff Result with valid id.
	 */
	@Test
	public void testGet() {
	
		// set up fixture
		String id = setUpDiffFixture();
		
		// Exercise the SUT
		Diff diff = SUT.get(id);
		
		// Verify
		assertNotNull(diff);
		assertNotNull(diff.getCreatedAt());
		assertNull(diff.getLastUpdate());
		assertNull(diff.getResult());
		assertEquals("API", diff.getOrigin());
	}
	
	@Test
	public void testCalculateLeft_with_equal_result() {
		
		// set up fixture
		String id = setUpDiffFixture();
		
		// Set up fixture
		File file1 = Paths.get(ROOT_FOLDER+"test0.txt").toFile();
		File file2 = Paths.get(ROOT_FOLDER+"test0.txt").toFile();
		
		// Exercise the SUT
		SUT.calculate(String.valueOf(id), DiffMethod.LEFT, file1, file2);
		
		// Verify
		assertNumDiffResult(1);
		assertNumDiffResult(1);
		assertNumDiffResultDetails(1);
	}

	
	@Test
	public void testCalculateLeft_with_not_equal_size_result() {
		
		// set up fixture
		String id = setUpDiffFixture();
		
		// Set up fixture
		File file1 = Paths.get(ROOT_FOLDER+"test0.txt").toFile();
		File file2 = Paths.get(ROOT_FOLDER+"test1.txt").toFile();
		
		// Exercise the SUT
		SUT.calculate(String.valueOf(id), DiffMethod.LEFT, file1, file2);
		
		// Verify
		assertNumDiffResult(1);
		assertNumDiffResult(1);
		assertNumDiffResultDetails(1);
	}
	
	@Test
	public void testCalculateLeft_with_equal_size_result() {
		
		// set up fixture
		String id = setUpDiffFixture();
		
		// Set up fixture
		File file1 = Paths.get(ROOT_FOLDER+"test0.txt").toFile();
		File file2 = Paths.get(ROOT_FOLDER+"test2.txt").toFile();
		
		// Exercise the SUT
		SUT.calculate(String.valueOf(id), DiffMethod.LEFT, file1, file2);
		
		// Verify
		assertNumDiffResult(1);
		assertNumDiffResult(1);
		assertNumDiffResultDetails(8);
	}
	
	
	protected String setUpDiffFixture() {
		Diff diff = new Diff(Calendar.getInstance().getTime(), "API");
		diffDAO.save(diff);
		return String.valueOf(diff.getId());

	}
	
	protected int countRowsInTable(String tableName) {
        return JdbcTestUtils.countRowsInTable(this.jdbcTemplate, tableName);
    }
	
	protected void assertNumDiff(int expected) {
        assertEquals("Number of rows in the [diff] table.", expected, countRowsInTable("diff"));
    }
	
	protected void assertNumDiffResult(int expected) {
        assertEquals("Number of rows in the [diff] table.", expected, countRowsInTable("diff_result"));
    }
	
	protected void assertNumDiffResultDetails(int expected) {
        assertEquals("Number of rows in the [diff] table.", expected, countRowsInTable("diff_result_details"));
    }
}

