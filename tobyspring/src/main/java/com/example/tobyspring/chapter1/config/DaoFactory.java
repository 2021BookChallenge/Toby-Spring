package com.example.tobyspring.chapter1.config;

import javax.sql.DataSource;
import com.example.tobyspring.chapter1.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {

	@Bean
	public UserDao userDao() {
		return new UserDao(dataSource());
	}

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost:3306/tobyspring");
		dataSource.setUsername("root");
		dataSource.setPassword("password");

		return dataSource;

	}
}
