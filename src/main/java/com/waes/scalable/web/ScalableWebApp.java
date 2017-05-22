package com.waes.scalable.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.waes.scalable.web.diff.api.IFilesStorageService;
import com.waes.scalable.web.diff.api.impl.services.FileStorageProperties;


/**
 * The Class ScalableWebApp is the main program to run the Diff microservice
 */
@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
public class ScalableWebApp 
{
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main( String[] args )
    {
    	SpringApplication.run(ScalableWebApp.class, args);
    }
    
    /**
     * Inits the ScalableWebApp Application
     *
     * @param storage the storage
     * @return the command line runner
     */
    @Bean
	CommandLineRunner init(IFilesStorageService storage) {
		return (args) -> {
			storage.init();
		};
	}
}
