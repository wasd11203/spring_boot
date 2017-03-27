package com.telincn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot 的入口类 要放在项目的根目录:
 * 	为了能够在启动容器时,确保能管理到所有的bean实例
 * 
 * @Description 
 * @author 99
 * @Date 2016年11月23日
 */
@SpringBootApplication
public class SpringBootApplicationMain {

	public SpringBootApplicationMain(){
		
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationMain.class, args);
	}
}
