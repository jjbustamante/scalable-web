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

/**
 * The Class DiffResultDetails.
 * 
 * This class represents a single diff difference from diff operation.
 */
@Entity
@Table(name = "diff_result_details")
public class DiffResultDetails implements Serializable {

	/** The Constant serialVersionUID. */
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
	
	
	/**
	 * Instantiates a new diff result details.
	 */
	public DiffResultDetails() { }
	
	/**
	 * Instantiates a new diff result details.
	 *
	 * @param message the message
	 * @param diffResult the diff result
	 */
	public DiffResultDetails(String message, DiffResult diffResult) {
		this.message = message;
		this.diffResult = diffResult;
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
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the diff result.
	 *
	 * @return the diff result
	 */
	public DiffResult getDiffResult() {
		return diffResult;
	}

	/**
	 * Sets the diff result.
	 *
	 * @param diffResult the new diff result
	 */
	public void setDiffResult(DiffResult diffResult) {
		this.diffResult = diffResult;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

