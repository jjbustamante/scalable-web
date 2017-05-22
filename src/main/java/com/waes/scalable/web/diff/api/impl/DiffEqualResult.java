package com.waes.scalable.web.diff.api.impl;

/**
 * The Class DiffEqualResult is used when two files are equal after executing a
 * Diff operation is executed
 */
public class DiffEqualResult extends AbstractDiffResult {

	/**
	 * Instantiates a new diff equal result.
	 */
	public DiffEqualResult() {
		this.addMessage("Files are Equal");
	}
}
