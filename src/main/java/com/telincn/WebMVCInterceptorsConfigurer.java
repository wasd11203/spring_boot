package com.telincn;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.telincn.interceptors.RequestInterceptor;

/**
 * 注册拦截器 类
 * 
 * @Description 
 * @author 99
 * @Date 2016年11月24日
 */
@Configuration
public class WebMVCInterceptorsConfigurer extends WebMvcConfigurerAdapter {

	
  public void addInterceptors(InterceptorRegistry registry) {
	  String[] urls = new String[]{
			  "/","/account/login.do"
	  };
      registry.addInterceptor(new RequestInterceptor())
      			.addPathPatterns("/**")
      			.excludePathPatterns(urls);
     
  }
}