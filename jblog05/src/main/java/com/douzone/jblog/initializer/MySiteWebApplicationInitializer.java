package com.douzone.jblog.initializer;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.jblog.config.AppConfig;
import com.douzone.jblog.config.WebConfig;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

public class MySiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	// applicationContext.xml 대신
	@Override
	protected Class<?>[] getRootConfigClasses() {
	
		return new Class<?> [] {AppConfig.class};
	}
	
	// spring-servlet.xml 대신
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	// 옵션 양키들은안함
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("UTF-8",false)};
	}
	
	// 추가적으로 url 이상하게 들어가면 에러띄어줘라
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}


}
