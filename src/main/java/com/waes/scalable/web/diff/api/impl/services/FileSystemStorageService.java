package com.waes.scalable.web.diff.api.impl.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.waes.scalable.web.diff.api.IFilesStorageService;


/**
 * The Class FileSystemStorageService.
 */
@Service
public class FileSystemStorageService implements IFilesStorageService {

	/** The root location. */
	private final Path rootLocation;

	/**
	 * Instantiates a new file system storage service.
	 *
	 * @param properties the properties
	 */
	@Autowired
	public FileSystemStorageService(FileStorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}
	
	/* (non-Javadoc)
	 * @see com.waes.scalable.web.diff.api.IFilesStorageService#init()
	 */
	@Override
	public void init() {
		try {
			if (!Files.exists(rootLocation)) {
				Files.createDirectory(rootLocation);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.waes.scalable.web.diff.api.IFilesStorageService#store(java.lang.String, org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public File store(String preffix, MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file " + file.getOriginalFilename());
			}
			Path dest = this.rootLocation.resolve(Paths.get(preffix));
			if(!Files.isDirectory(dest)){
				Files.createDirectory(dest);
			}
			Files.copy(file.getInputStream(), dest.resolve(file.getOriginalFilename()) , StandardCopyOption.REPLACE_EXISTING);
			return load(preffix, file.getOriginalFilename());
			
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.waes.scalable.web.diff.api.IFilesStorageService#load(java.lang.String, java.lang.String)
	 */
	@Override
	public File load(String preffix, String filename) {
		Path dest = this.rootLocation.resolve(Paths.get(preffix));
		return  dest.resolve(filename).toFile();
	}
}
