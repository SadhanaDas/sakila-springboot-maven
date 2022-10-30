package com.highradius.cv_training.Sakila_film.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import com.highradius.cv_training.Sakila_film.annotation.EndPoint;
import com.highradius.cv_training.Sakila_film.cors.CORSResponseFilter;

//the class JerseyConfig

public class JerseyConfig extends ResourceConfig{
	//Instantiates a new Jersey Configuration
	public JerseyConfig() {
		registerEndpoints();
	}


	private void registerEndpoints() {
		ClassPathScanningCandidateComponentProvider scanner=new ClassPathScanningCandidateComponentProvider 
				(Boolean.FALSE);
		scanner.addIncludeFilter(new AnnotationTypeFilter(EndPoint.class));
		
		for(BeanDefinition bd:scanner.findCandidateComponents("com.highradius.cv_training.Sakila_film.*")) {
			try {
				Class<?> loadedClass =getClass().getClassLoader().loadClass(bd.getBeanClassName());
				register(loadedClass);
				System.err.println(loadedClass);
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		register(JacksonFeature.class);
		//register(CustomObjectMapper.class);
		register(CORSResponseFilter.class);
		
	}
}













