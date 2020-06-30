package com.atr.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.atr.mapper.UserRowMapper;
import com.atr.model.User;
import com.atr.processor.UserItemProcessor;

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
	
	public UserItemProcessor  processor() {
		return new UserItemProcessor();
		
	}
	
	@Bean
	public FlatFileItemWriter<User> writer(){
		FlatFileItemWriter<User> writer = new FlatFileItemWriter<>();
		writer.setResource(new ClassPathResource("user.csv"));
		writer.setLineAggregator(new DelimitedLineAggregator<User>() {{
			setDelimiter(",");
			setFieldExtractor(new BeanWrapperFieldExtractor<User>() {{
				setNames(new String[] { "userid", "lastName", "firstName","name","phone","ext","securityLevel","reportingAllowed","active"});
			}});
		}});
		
		return writer;
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<User,User> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	@Bean
	public Job exportUserJob() {
		return jobBuilderFactory.get("exportUserJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
		
	}

}
