package com.waes.scalable.web.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * The Class DiffResult. 
 * This class is the Entity representation for a diff result, contains information regarding the 
 * operation execution. 
 */
@Entity
@Table(name = "diff_result")
public class DiffResult implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3995909119829726062L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(max = 255)
	@Column(name="file_1", length=255)
	private String file1;
	
	@Size(max = 255)
	@Column(name="file_2", length=255)
	private String file2;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Diff diff;
	
	@Column(name="method")
	private int method;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diffResult", cascade = CascadeType.ALL)
    private Set<DiffResultDetails> details = new HashSet<>(); 
	
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the diff.
	 *
	 * @return the diff
	 */
	public Diff getDiff() {
		return diff;
	}

	/**
	 * Sets the diff.
	 *
	 * @param diff the new diff
	 */
	public void setDiff(Diff diff) {
		this.diff = diff;
	}

	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	public int getMethod() {
		return method;
	}

	/**
	 * Sets the method.
	 *
	 * @param method the new method
	 */
	public void setMethod(int method) {
		this.method = method;
	}
	
	/**
	 * Gets the details.
	 *
	 * @return the details
	 */
	public Set<DiffResultDetails> getDetails() {
		return Collections.unmodifiableSet(details);
	}

	/**
	 * Sets the details.
	 *
	 * @param details the new details
	 */
	public void setDetails(Set<DiffResultDetails> details) {
		this.details.clear();
		this.details.addAll(details);
	}
	
	/**
	 * Adds the detail.
	 *
	 * @param detail the detail
	 */
	public void addDetail(DiffResultDetails detail) {
		this.details.add(detail);
	}
}
