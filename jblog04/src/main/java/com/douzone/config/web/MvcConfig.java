package com.douzone.config.web;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter{
	
	// <!-- ViewResolver -->
	@Bean
	public ViewResolver viewResolver() { 
		// id는 보통 인터페이스(같은 인터페이스 있을수도 있으니 조심하기)
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver(); //이거타고올라가면 viewResolver가 나온다
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
/////// Message Converter
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		
		StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
		messageConverter.setSupportedMediaTypes(           // 순수 문자열로 바꿔주는것 예를들면 <h1>hello</h1>하면 문자그대로출력안되고 hello가 출력되게 해주는것
			Arrays.asList( 
				new MediaType("text", "html", Charset.forName("utf-8")))
			);       //request와 response에 MediaType을 지정해서 요청받을 때 사용하는 MediaType과 응답할 때 보내주는 MediaType을 지정, 사전에 필요한 타입만 거를 수 있다.
		return messageConverter;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		//객체를 제이슨,xml등 문자열로 변환시켜준다. 지금은 제이슨 
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
			.indentOutput(true)    // 들여쓰기 예쁘게 해주는거                    이거랑 밑에꺼는 그냥 추가해준거
			.dateFormat(new SimpleDateFormat("yyyy-mm-dd"));   // 날짜 포멧
		
		
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter(builder.build());
		messageConverter.setSupportedMediaTypes(
			Arrays.asList(
				new MediaType("application", "json", Charset.forName("utf-8"))
			)
		);
		
		return messageConverter;
	}
	
	@Override  // 메세지 컨버터를 등록해주는 역할 
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());           
		converters.add(mappingJackson2HttpMessageConverter());
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) { // handler가 있으면 내가 만든 오류페이지로가는데 이게 없으면 톰캣 404 오류페이지를 보여준다.
		configurer.enable();
	}
	
	
}
