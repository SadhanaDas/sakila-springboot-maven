package com.highradius.cv_training.Sakila_film;

import javax.ws.rs.ApplicationPath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.highradius.cv_training.Sakila_film.config.JerseyConfig;

@SpringBootApplication(exclude= {HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,DataSourceAutoConfiguration.class,
		TransactionAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class})
@ComponentScan (basePackages="com.highradius.cv_training.Sakila_film.*")
@ApplicationPath("/CVSpringbootTraining")

public class SakilaFilmApplication extends JerseyConfig {
	public static void main(String[] args) {
		SpringApplication.run(SakilaFilmApplication.class, args);
		System.err.println("App Started");
	}

}
