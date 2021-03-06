package com.douzone.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:com/douzone/jblog/config/app/jdbc.properties")
public class DBConfig {
	
	@Autowired
	private Environment env;  //jdbc.properties 이걸 읽어서 저장한다.
	
	//  Connection Pool DataSource
	@Bean
	public DataSource dataSource() { // 계정이 다 다르니깐 jdbc프로포티로 빼서 설정
		
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class));
		// 처음에 커넥션을 몇개를줄거냐          스트링으로 가져와서 인티져로 바꾼다.
		dataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));
		// 100개가 늘어나면 200개까지 늘리는 것을 제한 201번은 1번이끝날때까지 기다련 후 들어간다
		
		return dataSource;
	}
}
