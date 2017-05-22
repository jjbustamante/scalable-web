package com.waes.scalable.web.diff.api.impl.services;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.waes.scalable.web.diff.api.IDiffResult;
import com.waes.scalable.web.diff.api.IDiffService;
import com.waes.scalable.web.diff.api.impl.DiffEqualResult;
import com.waes.scalable.web.diff.api.impl.DiffNotEqualSizeResult;
import com.waes.scalable.web.diff.api.impl.DiffSameSizeResult;

@Service
public class DiffServiceImpl implements IDiffService {

	@Override
	public IDiffResult diff(File file1, File file2) {
		try {
			if( FileUtils.contentEquals(file1, file2)) {
				return new DiffEqualResult();
			} 
			if(FileUtils.sizeOf(file1) == FileUtils.sizeOf(file2)) {
			
				DiffSameSizeResult sameSizeResult = new DiffSameSizeResult();
				
				byte[] f1 = FileUtils.readFileToByteArray(file1);
				byte[] f2 = FileUtils.readFileToByteArray(file2);
				
				for (int k = 0; k < f1.length; k++)
			    {
				    if(f1[k]-f2[k] != 0) {
				    	sameSizeResult.addMessage(String.format("%08d %d %d", (k+1), f1[k], f2[k]));
				    }
				}
				return sameSizeResult;
			}
			return new DiffNotEqualSizeResult();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
