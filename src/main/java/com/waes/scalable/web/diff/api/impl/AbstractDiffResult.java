package com.waes.scalable.web.diff.api.impl;

import java.util.ArrayList;
import java.util.List;

import com.waes.scalable.web.diff.api.IDiffResult;

public abstract class AbstractDiffResult implements IDiffResult {

	private List<String> messages;
	
	public AbstractDiffResult() {
		this.messages = new ArrayList<>();
	}
	
	@Override
	public List<String> getMessages() {
		return messages;
	}
	
	@Override
	public void addMessage(String message) {
		this.messages.add(message);
	}
}
