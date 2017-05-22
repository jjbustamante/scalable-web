package com.waes.scalable.web.diff.api;

import java.io.File;

import com.waes.scalable.web.data.Diff;

/**
 * The Interface IDiffManager
 * This class exposes the main logic API for the application.
 * 
 */
public interface IDiffManager {
	
	/**
	 * Creates a Diff object that can be used  used to perform operation.
	 *
	 * @return the diff
	 */
	Diff create();
	
	/**
	 * Calculate diff between the two files given
	 *
	 * @param id - Diff id
	 * @param method - Diff method to be used
	 * @param file1 
	 * @param file2 
	 */
	void calculate(String id, DiffMethod method, File file1, File file2);
	
	/**
	 * Gets the diff identified by the given id.
	 *
	 * @param id the id
	 * @return the diff
	 */
	Diff get(String id);
	
	/**
	 * Checks if a diff id is valid
	 *
	 * @param id the id
	 * @return true, if is valid diff
	 */
	boolean isValidDiff(String id);
	
}
