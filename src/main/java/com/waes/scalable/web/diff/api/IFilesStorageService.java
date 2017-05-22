package com.waes.scalable.web.diff.api;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface IFilesStorageService {

	void init();
	File store(String preffix, MultipartFile file);
	File load(String preffix, String filename);
}
