package com.waes.scalable.web.diff.api.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Before;
import org.junit.Test;

import com.waes.scalable.web.diff.api.IDiffResult;
import com.waes.scalable.web.diff.api.impl.services.DiffServiceImpl;

/**
 * The Class DiffServiceImplTest.
 */
public class DiffServiceImplTest {
	
	 private static final Logger LOG = LoggerFactory.getLogger(DiffServiceImplTest.class);
	
	/** System Under Test */
	DiffServiceImpl SUT;
	
	/** The Constant ROOT_FOLDER. */
	private static final String ROOT_FOLDER = "src/test/resources/";
	
	/**
	 * Sets up the test
	 */
	@Before
	public void setUp() {
		SUT = new DiffServiceImpl();
	}

	/**
	 * Calculate diff operation for two equal files
	 */
	@Test
	public void equalTest() {
		
		// Set up fixture
		File file1 = Paths.get(ROOT_FOLDER+"test0.txt").toFile();
		File file2 = Paths.get(ROOT_FOLDER+"test0.txt").toFile();
		
		// Exercise the SUT
		IDiffResult result = SUT.diff(file1, file2);
		
		// Verify
		assertTrue(result.getMessages().size() == 1);
		assertEquals("Files are Equal", result.getMessages().get(0));
	}
	
	/**
	 * Calculate diff operation for two files with the same size 
	 */
	@Test
	public void equalSizeTest() {
		
		// Set up fixture
		File file1 = Paths.get(ROOT_FOLDER+"test0.txt").toFile();
		File file2 = Paths.get(ROOT_FOLDER+"test2.txt").toFile();
				
		// Exercise the SUT
		IDiffResult result = SUT.diff(file1, file2);
				
		// Verify
		assertTrue(result.getMessages().size() > 1);
		for (String message : result.getMessages()) {
			LOG.info(message);
		}
	}
	
	/**
	 * Calculate diff operation for two files with different sizes
	 */
	@Test
	public void notEqualSizeTest() {
		// Set up fixture
		File file1 = Paths.get(ROOT_FOLDER+"test0.txt").toFile();
		File file2 = Paths.get(ROOT_FOLDER+"test1.txt").toFile();
		
		// Exercise the SUT
		IDiffResult result = SUT.diff(file1, file2);
		
		// Verify
		assertTrue(result.getMessages().size() == 1);
		assertEquals("Files are not equal size", result.getMessages().get(0));
	}
}
