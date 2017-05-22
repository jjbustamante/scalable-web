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

@Service
public class FileSystemStorageService implements IFilesStorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(FileStorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}
	
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
	
	@Override
	public File load(String preffix, String filename) {
		Path dest = this.rootLocation.resolve(Paths.get(preffix));
		return  dest.resolve(filename).toFile();
	}
}
