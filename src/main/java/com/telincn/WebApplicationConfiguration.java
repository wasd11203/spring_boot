package com.telincn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Spring Boot 启动类
 * @author ganzhigang
 * 时间：2017年3月28日 上午9:31:45
 */
@SpringBootApplication
@ServletComponentScan // 使得spring能够扫描到我们自己编写的servlet和filter(针对druid) === 针对的是 启动druid 的监控
public class WebApplicationConfiguration {
	public static void main(String[] args) {
		SpringApplication.run(WebApplicationConfiguration.class, args);
	}
}
