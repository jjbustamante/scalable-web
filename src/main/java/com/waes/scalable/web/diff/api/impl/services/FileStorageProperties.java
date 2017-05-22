package com.waes.scalable.web.diff.api.impl.services;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The Class FileStorageProperties.
 */
@ConfigurationProperties("storage")
public class FileStorageProperties {

	/** Folder location for storing files. */
	private String location = "upload-dir";

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

}