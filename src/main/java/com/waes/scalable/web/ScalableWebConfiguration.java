package com.waes.scalable.web;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.waes.scalable.web.rest.controller.DiffMapper;

/**
 * The Class ScalableWebConfiguration.
 * 
 */
@Configuration
@EnableAutoConfiguration
public class ScalableWebConfiguration {

	/**
	 * Model mapper.
	 *
	 * @return the model mapper
	 */
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Diff mapper.
     *
     * @param modelMapper the model mapper
     * @return the diff mapper
     */
    @Bean
    public DiffMapper diffMapper(ModelMapper modelMapper) {
        return new DiffMapper(modelMapper);
    }
}
