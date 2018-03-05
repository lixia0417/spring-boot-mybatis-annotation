package com.neo.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DbSlaveConfiguration {

	@Value("${spring.ro-datasource.url}")
	private String dbUrl;

	@Value("${spring.ro-datasource.username}")
	private String username;

	@Value("${spring.ro-datasource.password}")
	private String password;

	@Value("${spring.ro-datasource.driverClassName}")
	private String driverClassName;

	@Value("${spring.ro-datasource.initialSize}")
	private int initialSize;

	@Value("${spring.ro-datasource.minIdle}")
	private int minIdle;

	@Value("${spring.ro-datasource.maxActive}")
	private int maxActive;

	@Value("${spring.ro-datasource.maxWait}")
	private int maxWait;

	@Value("${spring.ro-datasource.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.ro-datasource.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.ro-datasource.validationQuery}")
	private String validationQuery;

	@Value("${spring.ro-datasource.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.ro-datasource.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.ro-datasource.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.ro-datasource.filters}")
	private String filters;
	

	/**
	 * Slave (read only) data source.
	 */
	@Bean("slaveDataSource")
	@ConfigurationProperties(prefix = "spring.ro-datasource")
	DataSource slaveDataSource() {
		System.err.println("create slave datasource...");
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(this.dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		// configuration
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			System.err.println("druid configuration initialization filter:" + e);
			e.printStackTrace();
		}
		return datasource;
	}
	
}
