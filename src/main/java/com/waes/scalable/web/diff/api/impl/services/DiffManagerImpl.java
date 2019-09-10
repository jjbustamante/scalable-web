package com.waes.scalable.web.diff.api.impl.services;

import java.io.File;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.waes.scalable.web.data.Diff;
import com.waes.scalable.web.data.DiffResult;
import com.waes.scalable.web.data.DiffResultDetails;
import com.waes.scalable.web.data.IDiffRepository;
import com.waes.scalable.web.diff.api.DiffMethod;
import com.waes.scalable.web.diff.api.IDiffFactory;
import com.waes.scalable.web.diff.api.IDiffManager;
import com.waes.scalable.web.diff.api.IDiffResult;
import com.waes.scalable.web.diff.api.IDiffService;
import com.waes.scalable.web.rest.controller.DiffMapper;

/**
 * The Class DiffManagerImpl.
 */
@Service
@Transactional
public class DiffManagerImpl implements IDiffManager {

	private IDiffService diffService;
	private IDiffRepository diffDAO;
	private IDiffFactory diffFactory;
	private DiffMapper mapper;
	
	
	/**
	 * Gets the diff service.
	 *
	 * @return the diff service
	 */
	public IDiffService getDiffService() {
		return diffService;
	}

	/**
	 * Sets the diff service.
	 *
	 * @param diffService the new diff service
	 */
	@Autowired
	public void setDiffService(IDiffService diffService) {
		this.diffService = diffService;
	}

	/**
	 * Gets the diff DAO.
	 *
	 * @return the diff DAO
	 */
	public IDiffRepository getDiffDAO() {
		return diffDAO;
	}

	/**
	 * Sets the diff DAO.
	 *
	 * @param diffDAO the new diff DAO
	 */
	@Autowired
	public void setDiffDAO(IDiffRepository diffDAO) {
		this.diffDAO = diffDAO;
	}

	/**
	 * Gets the diff factory.
	 *
	 * @return the diff factory
	 */
	public IDiffFactory getDiffFactory() {
		return diffFactory;
	}

	/**
	 * Sets the diff factory.
	 *
	 * @param diffFactory the new diff factory
	 */
	@Autowired
	public void setDiffFactory(IDiffFactory diffFactory) {
		this.diffFactory = diffFactory;
	}
		
	/**
	 * Gets the mapper.
	 *
	 * @return the mapper
	 */
	public DiffMapper getMapper() {
		return mapper;
	}

	/**
	 * Sets the mapper.
	 *
	 * @param mapper the new mapper
	 */
	@Autowired
	public void setMapper(DiffMapper mapper) {
		this.mapper = mapper;
	}
	
	/* *************************
	 * API
	 ***************************/
	
	/* (non-Javadoc)
	 * @see com.waes.scalable.web.diff.api.IDiffManager#create()
	 */
	@Override
	public Diff create() {
		
		Diff diff = this.diffFactory.create(Calendar.getInstance().getTime(), "API");
		this.diffDAO.save(diff);
		return diff;
	}

	/* (non-Javadoc)
	 * @see com.waes.scalable.web.diff.api.IDiffManager#calculate(java.lang.String, com.waes.scalable.web.diff.api.DiffMethod, java.io.File, java.io.File)
	 */
	@Override
	public void calculate(String id, DiffMethod method, File file1, File file2) {
		
		if(!isValidDiff(id)) { return;}
		
		Diff diff = this.diffDAO.findById(Long.parseLong(id)).get();
		IDiffResult result = this.diffService.diff(file1, file2);	
		prepare(diff, result);
		diff.getResult().setFile1(file1.getName());
		diff.getResult().setFile2(file2.getName());
		diff.getResult().setMethod(method.getValue());
		diff.setLastUpdate(Calendar.getInstance().getTime());
		
		this.diffDAO.save(diff);
		
	}

	/* (non-Javadoc)
	 * @see com.waes.scalable.web.diff.api.IDiffManager#get(java.lang.String)
	 */
	@Override
	public Diff get(String id) {
		
		if(!isValidDiff(id)) {
			// error
			return null;
		}
		return this.diffDAO.findById(Long.parseLong(id)).get();
	}
	
	/* (non-Javadoc)
	 * @see com.waes.scalable.web.diff.api.IDiffManager#isValidDiff(java.lang.String)
	 */
	@Override
	public boolean isValidDiff(String id) {
		
		if(StringUtils.isEmpty(id)) { 
			return false; 
		}
		return this.diffDAO.existsById(Long.parseLong(id));
	}
	
	/* ***********************
	 * Internal Methods
	 * ***********************/
	
	/**
	 * Prepare.
	 *
	 * @param diff the diff
	 * @param result the result
	 */
	private void prepare(Diff diff , IDiffResult result) {
		
		if(diff.getResult() == null) {
			diff.setResult(new DiffResult());
			diff.getResult().setDiff(diff);
		} else {
			removeDetails(diff);
		}
		for (String message : result.getMessages()) {
			diff.getResult().addDetail(new DiffResultDetails(message, diff.getResult()));
		}
	}
	
	/**
	 * Removes the details.
	 *
	 * @param diff the diff
	 */
	private void removeDetails(Diff diff) {
		if(diff.getResult() != null) {
			if(diff.getResult().getId() > 0) {
				this.diffDAO.clearDetails(diff.getResult().getId());
				diff.getResult().getDetails().clear();
			}	
		}
	}
}
