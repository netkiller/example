package cn.netkiller.datasource.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MultiDataSourceConfig {

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties defaultDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSource defaultDataSource() {
		return defaultDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean("JdbcTemplate")
	@Primary
	public JdbcTemplate defaultJdbcTemplate(@Qualifier("defaultDataSource") DataSource Master) {
		return new JdbcTemplate(Master);
	}

	@Bean
	// @Primary
	@ConfigurationProperties("spring.datasource.master")
	public DataSourceProperties masterDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean("Master")
	// @Primary
	@ConfigurationProperties("spring.datasource.master")
	public DataSource masterDataSource() {
		return masterDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean("masterJdbcTemplate")
	// @Primary
	public JdbcTemplate masterJdbcTemplate(@Qualifier("Master") DataSource Master) {
		return new JdbcTemplate(Master);
	}

	@Bean
	@ConfigurationProperties("spring.datasource.slave")
	public DataSourceProperties slaveDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "Slave")
	@ConfigurationProperties("spring.datasource.slave")
	public DataSource slaveDataSource() {
		return slaveDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean("slaveJdbcTemplate")
	public JdbcTemplate slaveJdbcTemplate(@Qualifier("Slave") DataSource Master) {
		return new JdbcTemplate(Master);
	}

}