package com.waes.scalable.web.diff.api;

import java.io.File;

/**
 * The Interface IDiffService.
 * This class contains the main logic for calculate the diff operation
 */
public interface IDiffService {

	/**
	 * calculate a diff operation for two files
	 *
	 * @param file1 the file 1
	 * @param file2 the file 2
	 * @return {@link IDiffResult}
	 */
	IDiffResult diff(File file1, File file2);
}
