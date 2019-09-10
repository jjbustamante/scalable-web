package com.waes.scalable.web.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * The Class Diff.
 * 
 * This class is an Entity representation for a Diff execution in the application. 
 */
@Entity
@Table(name = "diff")
public class Diff implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4075371984144256609L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	@Column(name="created_at")
	@NotNull
	private Date createdAt;
	
	@Column(name="last_updated")
	private Date lastUpdate;
	
	@Column(name="origin")
	@NotNull
	private String origin;
	
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "diff", cascade = CascadeType.ALL)
    private DiffResult result;
	
    /**
     * Instantiates a new diff.
     */
    public Diff() { }
    
	/**
	 * Instantiates a new diff.
	 *
	 * @param createdAt the created at
	 * @param origin the origin
	 */
	public Diff(Date createdAt, String origin){
		this.createdAt = createdAt != null ? (Date) createdAt.clone() : null;
		this.origin = origin;
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
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Date getCreatedAt() {
		return createdAt != null ? (Date) createdAt.clone() : null;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
	 * Gets the last update.
	 *
	 * @return the last update
	 */
	public Date getLastUpdate() {
		return lastUpdate != null ? (Date) lastUpdate.clone() : null;
	}

	/**
	 * Sets the last update.
	 *
	 * @param lastUpdate the new last update
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * Gets the origin.
	 *
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Sets the origin.
	 *
	 * @param origin the new origin
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public DiffResult getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(DiffResult result) {
		this.result = result;
	}
}
