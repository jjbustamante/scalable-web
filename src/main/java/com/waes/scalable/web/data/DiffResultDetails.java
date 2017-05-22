package com.waes.scalable.web.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "diff_result_details")
public class DiffResultDetails implements Serializable {

	private static final long serialVersionUID = 5358944525127096908L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Size(max = 500)
	@Column(name="message", length=500)
	private String message;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diff_result_id", nullable = false)
	private DiffResult diffResult;
	
	public DiffResultDetails() { }
	
	public DiffResultDetails(String message, DiffResult diffResult) {
		this.message = message;
		this.diffResult = diffResult;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DiffResult getDiffResult() {
		return diffResult;
	}

	public void setDiffResult(DiffResult diffResult) {
		this.diffResult = diffResult;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
	        return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	        return false;
	    }

	    final DiffResultDetails detail = (DiffResultDetails) o;

	    if(id != detail.getId()) {
	    	return false;
	    }
	    return true;
	}
}

