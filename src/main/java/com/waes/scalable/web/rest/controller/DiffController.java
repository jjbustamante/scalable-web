package com.waes.scalable.web.rest.controller;

import java.io.File;

import javax.validation.constraints.Null;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.waes.scalable.web.data.Diff;
import com.waes.scalable.web.diff.api.DiffMethod;
import com.waes.scalable.web.diff.api.IDiffManager;
import com.waes.scalable.web.diff.api.IFilesStorageService;
import com.waes.scalable.web.diff.api.impl.DiffServiceImplTest;
import com.waes.scalable.web.rest.dto.DiffDTO;
import com.waes.scalable.web.rest.dto.FileUploadForm;
import com.waes.scalable.web.rest.dto.Metadata;
import com.waes.scalable.web.rest.validation.DiffFiles;
import com.waes.scalable.web.rest.validation.DiffID;

@RestController
@RequestMapping("/v1")
@Validated
public class DiffController {
	
	@Autowired
	private IDiffManager facade;
	
	@Autowired
	private IFilesStorageService storageService;
	
	@Autowired
	private DiffMapper mapper;
	
	private static final Logger LOG = LoggerFactory.getLogger(DiffController.class);
		

	@RequestMapping(value = "/diff/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DiffDTO> diff(@DiffID @PathVariable("id") String id) {
		return new ResponseEntity<DiffDTO>(mapper.convert(facade.get(id)), HttpStatus.OK);
	}

	@PostMapping(value = "/diff/{id}/left", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> diffLeft(@DiffID @PathVariable("id") String id,  @RequestPart(name = "meta", required = false) Metadata metadata,
			 @DiffFiles @ModelAttribute("files") FileUploadForm files) {

		handleMetadata(metadata);
		process(id, DiffMethod.LEFT, files.getFiles().get(0), files.getFiles().get(1));
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PostMapping(value = "/diff/{id}/right", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> diffRight(@DiffID @PathVariable("id") String id,
			@RequestPart(name = "meta", required = false) Metadata metadata, @DiffFiles @ModelAttribute("files") FileUploadForm files) {

		handleMetadata(metadata);
		process(id, DiffMethod.RIGHT, files.getFiles().get(1), files.getFiles().get(0));
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PostMapping(value = "/diff", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DiffDTO> create() {

		Diff diff = facade.create();		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/diff/" + diff.getId());
		return new ResponseEntity<DiffDTO>(mapper.convert(diff), headers, HttpStatus.CREATED);
	}
	
	
	/* ***********************
	 * Internal Methods
	 * ***********************/
	
	private void process(String id, DiffMethod method, MultipartFile mfile1, MultipartFile mfile2) {
		File file1 = storageService.store(id, mfile1);
		File file2 = storageService.store(id, mfile2);
		this.facade.calculate(id, method , file1, file2);
	}
	
	private void handleMetadata(Metadata metadata) {
		if(metadata != null) {
			LOG.info(String.format("Metadata received: Application: %s , Author: %s", metadata.getApplication(), metadata.getAuthor()));
		}
	}
}
