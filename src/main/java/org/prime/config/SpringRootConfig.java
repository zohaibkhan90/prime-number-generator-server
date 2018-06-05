package org.prime.config;

import javax.sql.DataSource;

import org.prime.database.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author zohaib
 * Spring Beans Configuration class for preparing NamedParameterJdbcTemplate 
 */
@Configuration
@Import({DataSourceConfig.class})
@ComponentScan({ "org.prime" })
public class SpringRootConfig {
	
	@Autowired
	DataSource dataSource;
	
	/**
	 * @return NamedParameterJdbcTemplate with dataSource
	 */
	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
}
