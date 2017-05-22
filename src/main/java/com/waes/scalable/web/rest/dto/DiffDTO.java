package com.waes.scalable.web.rest.dto;

import java.util.List;


/**
 * The Class DiffDTO.
 * Plain representation of a Diff, is used as Data Transfer Object
 */
public class DiffDTO {


	private String id;
	private String file1;
	private String file2;
	private List<String> differences;
	private String method;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the file 1.
	 *
	 * @return the file 1
	 */
	public String getFile1() {
		return file1;
	}
	
	/**
	 * Sets the file 1.
	 *
	 * @param file1 the new file 1
	 */
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	
	/**
	 * Gets the file 2.
	 *
	 * @return the file 2
	 */
	public String getFile2() {
		return file2;
	}
	
	/**
	 * Sets the file 2.
	 *
	 * @param file2 the new file 2
	 */
	public void setFile2(String file2) {
		this.file2 = file2;
	}
	
	/**
	 * Gets the differences.
	 *
	 * @return the differences
	 */
	public List<String> getDifferences() {
		return differences;
	}
	
	/**
	 * Sets the differences.
	 *
	 * @param differences the new differences
	 */
	public void setDifferences(List<String> differences) {
		this.differences = differences;
	}
	
	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * Sets the method.
	 *
	 * @param method the new method
	 */
	public void setMethod(String method) {
		this.method = method;
	}
}
