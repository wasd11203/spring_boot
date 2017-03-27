package com.telincn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ServletComponentScan // 使得spring能够扫描到我们自己编写的servlet和filter(针对druid) === 针对的是 启动druid 的监控
@EnableAspectJAutoProxy // aop 使能
public class WebApplicationConfiguration {
	public static void main(String[] args) {
		SpringApplication.run(WebApplicationConfiguration.class, args);
	}
}
