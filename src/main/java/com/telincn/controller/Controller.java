package com.telincn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 针对 需要转发 的请求处理类
 * @author 99
 *
 */
@RestController
public class Controller {
	
	/**
	 * 访问根目录:
	 * 	访问根目录实际上就是访问login页面
	 * @return
	 */
	@RequestMapping("/") 
    public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
        return mv;
    }
	
	/**
	 * 访问首页:
	 * 	登录成功后,请求携带的账号参数保存到Session中,然后转发到首页
	 * @param userId 登录的账号的id
	 * @param username 登录的账号的username
	 * @param request 提交的请求
	 * @return 携带转发的目的地址的ModelAndView
	 */
	@RequestMapping("/manager/index.do")
    public ModelAndView login(HttpServletRequest request) {  
		
		ModelAndView mv = new ModelAndView("bs_index");
		
        return mv;
    }
	
}
