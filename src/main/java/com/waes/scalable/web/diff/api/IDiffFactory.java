package com.waes.scalable.web.diff.api;

import java.util.Date;

import com.waes.scalable.web.data.Diff;

public interface IDiffFactory {

	Diff create(Date date, String origin);
}
