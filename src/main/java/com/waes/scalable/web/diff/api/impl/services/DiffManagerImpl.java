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

@Service
@Transactional
public class DiffManagerImpl implements IDiffManager {

	private IDiffService diffService;
	private IDiffRepository diffDAO;
	private IDiffFactory diffFactory;
	private DiffMapper mapper;
	
	public IDiffService getDiffService() {
		return diffService;
	}

	@Autowired
	public void setDiffService(IDiffService diffService) {
		this.diffService = diffService;
	}

	public IDiffRepository getDiffDAO() {
		return diffDAO;
	}

	@Autowired
	public void setDiffDAO(IDiffRepository diffDAO) {
		this.diffDAO = diffDAO;
	}

	public IDiffFactory getDiffFactory() {
		return diffFactory;
	}

	@Autowired
	public void setDiffFactory(IDiffFactory diffFactory) {
		this.diffFactory = diffFactory;
	}
		
	public DiffMapper getMapper() {
		return mapper;
	}

	@Autowired
	public void setMapper(DiffMapper mapper) {
		this.mapper = mapper;
	}
	
	/* *************************
	 * API
	 ***************************/
	
	@Override
	public Diff create() {
		
		Diff diff = this.diffFactory.create(Calendar.getInstance().getTime(), "API");
		this.diffDAO.save(diff);
		return diff;
	}

	@Override
	public void calculate(String id, DiffMethod method, File file1, File file2) {
		
		if(!isValidDiff(id)) { return;}
		
		Diff diff = this.diffDAO.findOne(Long.parseLong(id));
		IDiffResult result = this.diffService.diff(file1, file2);	
		prepare(diff, result);
		diff.getResult().setFile1(file1.getName());
		diff.getResult().setFile2(file2.getName());
		diff.getResult().setMethod(method.getValue());
		diff.setLastUpdate(Calendar.getInstance().getTime());
		
		this.diffDAO.save(diff);
		
	}

	@Override
	public Diff get(String id) {
		
		if(!isValidDiff(id)) {
			// error
			return null;
		}
		return this.diffDAO.findOne(Long.parseLong(id));
	}
	
	@Override
	public boolean isValidDiff(String id) {
		
		if(StringUtils.isEmpty(id)) { 
			return false; 
		}
		return this.diffDAO.exists(Long.parseLong(id));
	}
	
	/* ***********************
	 * Internal Methods
	 * ***********************/
	
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
	
	private void removeDetails(Diff diff) {
		if(diff.getResult() != null) {
			if(diff.getResult().getId() > 0) {
				this.diffDAO.clearDetails(diff.getResult().getId());
				diff.getResult().getDetails().clear();
			}	
		}
	}
}
