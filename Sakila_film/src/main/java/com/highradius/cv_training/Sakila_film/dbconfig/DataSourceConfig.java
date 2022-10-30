package com.highradius.cv_training.Sakila_film.dbconfig;

import static org.hibernate.cfg.AvailableSettings.*;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.highradius.cv_training.Sakila_film.helper.*;

@EnableTransactionManagement
public class DataSourceConfig {
	private String dataSource;
	private String packageScan;
	private String puName;
	private String txnName;
	
	/**
	 * Instantiates a new data source config.
	 *
	 * @param dataSource  the data source
	 * @param packageScan the package scan
	 * @param puName      the pu name
	 * @param txnName     the txn name
	 */
	
	public DataSourceConfig(String dataSource,String packageScan,String puName, String txnName) {
		this.dataSource=dataSource;
		this.packageScan=packageScan;
		this.puName=puName;
		this.txnName=txnName;
	}
	
	//Gets data source and return the datasource
	public DataSource getDataSource() {
		String dbHostUrl= Constants.LOCAL_DB_URL; //package helper
		DataSourceBuilder<?> dataSourceBuilder=DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceBuilder.url(dbHostUrl + dataSource);
		dataSourceBuilder.username("root");
		dataSourceBuilder.password("root");
		return dataSourceBuilder.build();
	}
	
	//Creating persistent beans @return the bean factory post processor
	protected BeanFactoryPostProcessor createPersistenceBeans() {
		return factory ->{
			BeanDefinitionRegistry registry =(BeanDefinitionRegistry) factory;
			registry.registerBeanDefinition(puName, 
					BeanDefinitionBuilder.genericBeanDefinition(LocalContainerEntityManagerFactoryBean.class,
					this::createEntityManagerFactory).getBeanDefinition());
		   registry.registerBeanDefinition(txnName,
				   BeanDefinitionBuilder
				   .genericBeanDefinition(PlatformTransactionManager.class,this::transactionManager)
				   .addPropertyReference("entityManagerFactory",puName)
				   .addPropertyValue("persistenceUnitName",puName).getBeanDefinition()
				   );
		};
	}
	
	private HibernateJpaVendorAdapter vendorAdaptor() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); 
		vendorAdapter.setShowSql(true); 
		return vendorAdapter; 

		}
	public LocalContainerEntityManagerFactoryBean createEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean(); 
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
		entityManagerFactoryBean.setPersistenceProviderClass ( HibernatePersistenceProvider.class); 
		entityManagerFactoryBean.setPackagesToScan (packageScan); 
		entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties()); 
		entityManagerFactoryBean.setDataSource(getDataSource()); 
		return entityManagerFactoryBean;
	}
	
	public PlatformTransactionManager transactionManager() { 
		return new JpaTransactionManager();
	}

	//Jpa hibernate properties 

	public Properties jpaHibernateProperties() { 
		Properties properties = new Properties();
		properties.put(HBM2DDL_DATABASE_ACTION, "none");
		properties.put(DIALECT, MySQL8Dialect.class.getName());
		 properties.put(SHOW_SQL, "true"); 
		 return properties;
	}
	
}
