package com.telincn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用的启动类
 * @author 99
 *
 */
@SpringBootApplication
@RestController
public class SpringBootApplicationMain {  
	
	public SpringBootApplicationMain() {
	}
	
    public static void main(String[] args) { 
    	
        SpringApplication.run(SpringBootApplicationMain.class);  
        
    }
  
}  