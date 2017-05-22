package com.waes.scalable.web.rest.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * The Class FileUploadForm.
 */
public class FileUploadForm {
	
	/** The files. */
	private List<MultipartFile> files;

	/**
	 * Gets the files.
	 *
	 * @return the files
	 */
	public List<MultipartFile> getFiles() {
		return files;
	}

	/**
	 * Sets the files.
	 *
	 * @param files the new files
	 */
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	
}
