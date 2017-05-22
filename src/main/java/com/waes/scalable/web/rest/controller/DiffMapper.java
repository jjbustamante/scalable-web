package com.waes.scalable.web.rest.controller;

import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.waes.scalable.web.data.Diff;
import com.waes.scalable.web.diff.api.DiffMethod;
import com.waes.scalable.web.rest.dto.DiffDTO;

/**
 * The Class DiffMapper.
 */
public class DiffMapper {

	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Instantiates a new diff mapper.
	 *
	 * @param modelMapper the model mapper
	 */
	public DiffMapper(ModelMapper modelMapper) {

		this.modelMapper = modelMapper;
		this.modelMapper.addConverter(domainConverter());
	}

	/**
	 * Convert.
	 *
	 * @param diff the diff
	 * @return the diff DTO
	 */
	public DiffDTO convert(Diff diff) {

		return modelMapper.map(diff, DiffDTO.class);
	}

	/**
	 * Domain converter.
	 *
	 * @return the converter
	 */
	private Converter<Diff, DiffDTO> domainConverter() {
		return new AbstractConverter<Diff, DiffDTO>() {
			@Override
			protected DiffDTO convert(Diff source) {

				DiffDTO dto = new DiffDTO();
				dto.setId(String.valueOf(source.getId()));
				if (source.getResult() != null) {
					dto.setFile1(source.getResult().getFile1());
					dto.setFile2(source.getResult().getFile2());
					dto.setMethod(DiffMethod.fromValue(source.getResult().getMethod()));
					dto.setDifferences(source.getResult().getDetails().stream().map(p -> p.getMessage())
							.collect(Collectors.toList()));
				}
				return dto;
			}
		};
	}
}
