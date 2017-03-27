package com.telincn.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.telincn.entity.User;

public class RequestInterceptor  implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 System.out.println("当前访问的地址:"+request.getRequestURL());
//         System.out.println("interceptor====222");
		 
		 HttpSession session = request.getSession();
		 User user = (User) session.getAttribute("user");
		 if( null != session && null != user ){
			 return true;
		 }
//		 response.getWriter().write("error");
		 response.sendRedirect("/");
		 return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("处理请求后,渲染视图前 ... ...");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("完全处理请求后 ... ...");
	}

}
