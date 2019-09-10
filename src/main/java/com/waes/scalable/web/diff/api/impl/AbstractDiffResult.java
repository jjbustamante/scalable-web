package com.waes.scalable.web.diff.api.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.waes.scalable.web.diff.api.IDiffResult;

/**
 * The Class AbstractDiffResult.
 */
public abstract class AbstractDiffResult implements IDiffResult {

	/** The messages. */
	private List<String> messages;
	
	/**
	 * Instantiates a new abstract diff result.
	 */
	public AbstractDiffResult() {
		this.messages = new ArrayList<>();
	}
	
	/* (non-Javadoc)
	 * @see com.waes.scalable.web.diff.api.IDiffResult#getMessages()
	 */
	@Override
	public List<String> getMessages() {
		return Collections.unmodifiableList(messages);
	}
	
	/* (non-Javadoc)
	 * @see com.waes.scalable.web.diff.api.IDiffResult#addMessage(java.lang.String)
	 */
	@Override
	public void addMessage(String message) {
		this.messages.add(message);
	}
}
