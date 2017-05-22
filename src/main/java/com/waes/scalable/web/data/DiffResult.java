package com.waes.scalable.web.data;

import java.io.Serializable;
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

@Entity
@Table(name = "diff_result")
public class DiffResult implements Serializable {

	private static final long serialVersionUID = 3995909119829726062L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Diff getDiff() {
		return diff;
	}

	public void setDiff(Diff diff) {
		this.diff = diff;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}
	
	public Set<DiffResultDetails> getDetails() {
		return details;
	}

	public void setDetails(Set<DiffResultDetails> details) {
		this.details = details;
	}
	
	public void addDetail(DiffResultDetails detail) {
		this.details.add(detail);
	}
}
