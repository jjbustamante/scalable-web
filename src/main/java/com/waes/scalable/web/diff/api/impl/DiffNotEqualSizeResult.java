package com.waes.scalable.web.diff.api.impl;

public class DiffNotEqualSizeResult extends AbstractDiffResult {

	public DiffNotEqualSizeResult() {
		this.addMessage("Files are not equal size");
	}
}
