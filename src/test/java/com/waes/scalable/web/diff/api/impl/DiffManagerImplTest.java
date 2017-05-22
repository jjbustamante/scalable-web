package com.waes.scalable.web.diff.api.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.waes.scalable.web.data.Diff;
import com.waes.scalable.web.data.IDiffRepository;
import com.waes.scalable.web.diff.api.DiffMethod;
import com.waes.scalable.web.diff.api.IDiffFactory;
import com.waes.scalable.web.diff.api.IDiffResult;
import com.waes.scalable.web.diff.api.IDiffService;
import com.waes.scalable.web.diff.api.impl.services.DiffManagerImpl;

/**
 * The Class DiffManagerImplTest.
 */
public class DiffManagerImplTest {

	/** System Under Test */
	private DiffManagerImpl SUT;	
	private IDiffService mockDiffService;
	private IDiffRepository mockDAO;
	private IDiffFactory mockDiffFactory;
	private static final String VALID_ID = "1";
	private static final String INVALID_ID = "2";
	private static final String EXPECTED_ORIGIN = "API";
	private Diff expected;
	private File file1;
	private File file2;
	private IDiffResult equalResult;
	private IDiffResult notEqualSizeResult;
	private IDiffResult equalSizeResult;
	
	
	/**
	 * Initialize the test
	 */
	@Before   
	public void setUp() {
		
		SUT = new DiffManagerImpl();
		mockDAO = mock(IDiffRepository.class);
		mockDiffFactory = mock(IDiffFactory.class);
		mockDiffService = mock(IDiffService.class);
		
		SUT.setDiffDAO(mockDAO);
		SUT.setDiffFactory(mockDiffFactory);
		SUT.setDiffService(mockDiffService);
		
		Date expectedDay = Calendar.getInstance().getTime();
		String expectedOrigin = "API";
		expected = new Diff(expectedDay, expectedOrigin); 
		
		file1 = mock(File.class);
		file2 = mock(File.class);
		
		equalResult = new DiffEqualResult();
		notEqualSizeResult = new DiffNotEqualSizeResult();
		
		equalSizeResult = new DiffSameSizeResult();
		equalSizeResult.addMessage("00000001 102 98");
		equalSizeResult.addMessage("00000002 111 97");
		
	}
	
	/**
	 * Test create happy case.
	 */
	@Test
	public void testCreate_happy_case() {

		// Setup fixture
		
		when(mockDiffFactory.create(any(Date.class), eq(EXPECTED_ORIGIN))).thenReturn(expected);
		
		// Exercis the SUT
		Diff diff = SUT.create();
		
		// Verify 
		assertNotNull(diff.getCreatedAt());
		assertNull(diff.getLastUpdate());
		assertNull(diff.getResult());
		assertEquals(EXPECTED_ORIGIN, diff.getOrigin());
	}
	
	/**
	 * Test get Diff Result with valid id.
	 */
	@Test
	public void testGet_with_valid_id() {
	
		// Setup fixture
		when(mockDAO.exists(eq(Long.valueOf(VALID_ID)))).thenReturn(true);
		when(mockDAO.findOne(eq(Long.valueOf(VALID_ID)))).thenReturn(expected);
		
		// Exercise the SUT
		Diff actualDiff = SUT.get(VALID_ID);
		
		// Verify
		assertEquals(expected, actualDiff);
		
	}
	
	/**
	 * Test get Diff Result with invalid id.
	 */
	@Test
	public void testGet_with_invalid_id() {
		
		// Setup fixture
		when(mockDAO.exists(eq(Long.valueOf(INVALID_ID)))).thenReturn(false);
		
		// Exercise the SUT
		Diff diff = SUT.get(INVALID_ID);
		
		// Verify
		assertNull(diff);
	}
	
	/**
	 * Test get Diff Result with null id.
	 */
	@Test
	public void testGet_with_null_id() {
		
		// Exercise the SUT
		Diff diff = SUT.get(null);
		
		// Verify
		assertNull(diff);
	}
	
	/**
	 * Test calculate left diff with valid id and equal files result.
	 */
	@Test
	public void testCalculateLeft_with_valid_id_equal_result() {
		
		// Setup fixture
		setUpCalculateFixture(equalResult);
		
		// Exercise the SUT
		SUT.calculate(VALID_ID, DiffMethod.LEFT, file1, file2);
		
		// Verify
		verifyDiffCalculation(DiffMethod.LEFT, 1);
		
	}
	
	/**
	 * Test calculate left diff with valid id and not equal files size result.
	 */
	@Test
	public void testCalculateLeft_with_valid_id_not_equal_size_result() {
		
		// Setup fixture
		setUpCalculateFixture(notEqualSizeResult);
		
		// Exercise the SUT
		SUT.calculate(VALID_ID, DiffMethod.LEFT, file1, file2);
		
		// Verify
		verifyDiffCalculation(DiffMethod.LEFT, 1);
		
	}
	
	/**
	 * Test calculate left diff with valid id  and equal size files result.
	 */
	@Test
	public void testCalculateLeft_with_valid_id_equal_size_result() {
		
		// Setup fixture
		setUpCalculateFixture(equalSizeResult);
		
		// Exercise the SUT
		SUT.calculate(VALID_ID, DiffMethod.LEFT, file1, file2);
		
		// Verify
		verifyDiffCalculation(DiffMethod.LEFT, 2);
		
	}
	
	/**
	 * Test calculate right diff with valid id and equal files result.
	 */
	@Test
	public void testCalculateRight_with_valid_id_equal_result() {
		
		// Setup fixture
		setUpCalculateFixture(equalResult);
		
		// Exercise the SUT
		SUT.calculate(VALID_ID, DiffMethod.RIGHT, file1, file2);
		
		// Verify
		verifyDiffCalculation(DiffMethod.RIGHT, 1);
		
	}
	
	/**
	 * Test calculate right diff with valid id and not equal size files result.
	 */
	@Test
	public void testCalculateRigth_with_valid_id_not_equal_size_result() {
		
		// Setup fixture
		setUpCalculateFixture(notEqualSizeResult);
		
		// Exercise the SUT
		SUT.calculate(VALID_ID, DiffMethod.RIGHT, file1, file2);
		
		// Verify
		verifyDiffCalculation(DiffMethod.RIGHT, 1);
		
	}
	
	/**
	 * Test calculate right diff with valid id and equal size files result.
	 */
	@Test
	public void testCalculateRight_with_valid_id_equal_size_result() {
		
		// Setup fixture
		setUpCalculateFixture(equalSizeResult);
		
		// Exercise the SUT
		SUT.calculate(VALID_ID, DiffMethod.RIGHT, file1, file2);
		
		// Verify
		verifyDiffCalculation(DiffMethod.RIGHT, 2);
	}
	
	/* *********************************
	 * Internal Logic
	 * ********************************/
	
	/**
	 * Sets up calculate fixture.
	 *
	 * @param result is the diff calculation result to be used
	 */
	private void setUpCalculateFixture(IDiffResult result) {
		
		when(mockDAO.exists(eq(Long.valueOf(VALID_ID)))).thenReturn(true);
		when(mockDAO.findOne(eq(Long.valueOf(VALID_ID)))).thenReturn(expected);
		when(mockDiffService.diff(eq(file1), eq(file2))).thenReturn(result);
		when(file1.getName()).thenReturn("foo");
		when(file2.getName()).thenReturn("bar");
		
	}
	
	/**
	 * Verify diff calculation.
	 *
	 * @param method Diff Method used
	 * @param detailSizeExpected the number of differences expected
	 */
	private void verifyDiffCalculation(DiffMethod method, int detailSizeExpected) {
		assertNotNull(expected.getResult());
		assertEquals("foo", expected.getResult().getFile1());
		assertEquals("bar", expected.getResult().getFile2());
		assertEquals(method.getValue(), expected.getResult().getMethod());
		assertTrue(expected.getResult().getDetails().size() == detailSizeExpected);
	}
	
}
