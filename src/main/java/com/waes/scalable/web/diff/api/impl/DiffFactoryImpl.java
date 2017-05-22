package com.waes.scalable.web.diff.api.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.waes.scalable.web.data.Diff;
import com.waes.scalable.web.diff.api.IDiffFactory;

@Service
public class DiffFactoryImpl implements IDiffFactory {
	
	@Override
	public Diff create(Date date, String origin) {
		return new Diff(date, origin);
	}
}
