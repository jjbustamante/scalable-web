package com.waes.scalable.web.rest.dto;

import java.util.List;

public class DiffDTO {

	private String id;
	private String file1;
	private String file2;
	private List<String> differences;
	private String method;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public String getFile2() {
		return file2;
	}
	public void setFile2(String file2) {
		this.file2 = file2;
	}
	public List<String> getDifferences() {
		return differences;
	}
	public void setDifferences(List<String> differences) {
		this.differences = differences;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}
