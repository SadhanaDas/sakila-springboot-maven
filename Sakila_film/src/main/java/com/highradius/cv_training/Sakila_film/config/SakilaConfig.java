package com.highradius.cv_training.Sakila_film.config;

import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.highradius.cv_training.Sakila_film.dbconfig.DataSourceConfig;

@Configuration
public class SakilaConfig extends DataSourceConfig{
	private static final String DB="sakila";
	public static final String SAKILA_PU ="SAKILA_PU";
	public static final String SAKILA_TXN ="SAKILA_TXN";
	private static final String ENTITY_SCAN="com.highradius.cv_training.Sakila_film.*";

	
	public SakilaConfig() {
		super(DB,ENTITY_SCAN,SAKILA_PU,SAKILA_TXN);
	}
	@Bean
	public BeanFactoryPostProcessor createSakilaBeans() {
		return createPersistenceBeans() ;
	}
	
}
