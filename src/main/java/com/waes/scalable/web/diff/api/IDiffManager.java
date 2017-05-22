package com.waes.scalable.web.diff.api;

import java.io.File;

import com.waes.scalable.web.data.Diff;

public interface IDiffManager {
	
	Diff create();
	void calculate(String id, DiffMethod method, File file1, File file2);
	Diff get(String id);
	boolean isValidDiff(String id);
	
}
