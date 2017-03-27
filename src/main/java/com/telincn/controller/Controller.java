package com.telincn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.telincn.entity.ExEntity;
import com.telincn.service.UserService;

/**
 * @RestController:注解的作用相当于 @Controller+@ResponseBody
 * @author 99
 *
 */
@RestController
public class Controller {
	
	private UserService userService;
	
	@RequestMapping("/")
	public String hello(){
		return "hello";
	}
	
	/**
	 * 实现转发的到新的页面
	 * 	1. 如果当前方法所在的类上使用的是 @RestController注解时,只允许使用ModelAndView 实现转发
	 *  2. 如果当前方法所在的类上使用的是@Controller 注解时,允许使用ModelAndView 或者 String 类型实现转发
	 *  3. 如果要转发到JSP时
	 *  	1. 在pom文件中添加spring boot 对jsp的依赖
	 *  	2. 需要配置application.properties文件。(见application.properties文件配置)
	 * @return
	 */
	@RequestMapping("/dispatcher")
	public ModelAndView dispatcher(){
		ExEntity entity = userService.findById("1");
		System.out.println(entity.getName());
		return new ModelAndView("index");
	}
	
}
