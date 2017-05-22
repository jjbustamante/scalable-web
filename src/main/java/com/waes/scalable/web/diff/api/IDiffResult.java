package com.waes.scalable.web.diff.api;

import java.util.List;

public interface IDiffResult {

	List<String> getMessages();
	void addMessage(String message);
}
