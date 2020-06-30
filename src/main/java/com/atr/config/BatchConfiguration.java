package com.atr.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.atr.mapper.UserRowMapper;
import com.atr.model.User;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public DataSource dataSource;
	
	
	@Value("${db1.mssql.user}")
    private String msssqlUser;
	
	@Value("${db1.mssql.password}")
    private String msssqlpassword;

    @Value("${db1.mssql.url}")
    private String mssqlUrl;
	
	@Bean
	public DataSource dataSource() {
		
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl(mssqlUrl);
		dataSource.setUsername(msssqlUser);
		dataSource.setPassword(msssqlpassword);
		return dataSource;
	}
	
	@Bean
	public JdbcCursorItemReader<User> reader(){
		JdbcCursorItemReader<User> reader = new JdbcCursorItemReader<User>();
		reader.setDataSource(dataSource);
		reader.setSql("Select * From dbo.COMPLMGMT_st_ASSOCIATES");
		reader.setRowMapper(new UserRowMapper());
		return reader;
	}

}
