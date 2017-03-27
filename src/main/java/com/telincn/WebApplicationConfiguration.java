package com.telincn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 使得spring能够扫描到我们自己编写的servlet和filter(针对druid)
public class WebApplicationConfiguration {
	public static void main(String[] args) {
		SpringApplication.run(WebApplicationConfiguration.class, args);
	}
}
