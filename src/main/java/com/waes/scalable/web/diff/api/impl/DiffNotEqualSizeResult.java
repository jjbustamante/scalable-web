package com.waes.scalable.web.diff.api.impl;

/**
 * The Class DiffNotEqualSizeResult is used when two files are not equal size after executing a
 * Diff operation is executed
 */
public class DiffNotEqualSizeResult extends AbstractDiffResult {

	/**
	 * Instantiates a new diff not equal size result.
	 */
	public DiffNotEqualSizeResult() {
		this.addMessage("Files are not equal size");
	}
}
