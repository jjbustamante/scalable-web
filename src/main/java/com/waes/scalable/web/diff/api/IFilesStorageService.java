package com.waes.scalable.web.diff.api;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;


/**
 * The Interface IFilesStorageService.
 * This class is responsible for handling files, it is used for store and retrieve files.
 */
public interface IFilesStorageService {

	/**
	 * Initializes the component
	 */
	void init();
	
	/**
	 * Store the given file. If the file to store already exits, then it replaces it.
	 *
	 * @param preffix to be used for storing path
	 * @param MultipartFile file to be store
	 * @return The file already stored.
	 */
	File store(String preffix, MultipartFile file);
	
	/**
	 * Load the given file.
	 *
	 * @param preffix used when the file was stored.
	 * @param filename 
	 * @return  The file already stored.
	 */
	File load(String preffix, String filename);
}
