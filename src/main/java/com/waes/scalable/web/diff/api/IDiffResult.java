package com.waes.scalable.web.diff.api;

import java.util.List;

/**
 * The Interface IDiffResult.
 * This represents the result for a Diff operation
 * 
 */
public interface IDiffResult {

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	List<String> getMessages();
	
	/**
	 * Adds the message.
	 *
	 * @param message the message
	 */
	void addMessage(String message);
}
