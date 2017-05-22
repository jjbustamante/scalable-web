package com.waes.scalable.web.diff.api;

import java.io.File;

public interface IDiffService {

	IDiffResult diff(File file1, File file2);
}
