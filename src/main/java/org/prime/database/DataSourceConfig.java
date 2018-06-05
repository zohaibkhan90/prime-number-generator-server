package org.prime.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * @author zohaib
 *
 */
@Configuration
@ComponentScan({ "org.prime" })
public class DataSourceConfig {
	
	/**
	 * @return  DataSource for HSQL database
	 */
	@Bean
	public DataSource dataSource(){
		//jdbc:hsqldb:mem:primedb		
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		//EmbeddedDatabaseFactoryBean will take care of shutting down the db
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).setName("primedb")
			.addScript("create.sql")
			.build();
		
		return db;
	}
	
}