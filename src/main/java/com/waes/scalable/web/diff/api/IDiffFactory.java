package com.waes.scalable.web.diff.api;

import java.util.Date;

import com.waes.scalable.web.data.Diff;

/**
 * A factory for creating IDiff objects.
 */
public interface IDiffFactory {

	/**
	 * Creates the.
	 *
	 * @param date the date
	 * @param origin the origin
	 * @return the diff
	 */
	Diff create(Date date, String origin);
}
