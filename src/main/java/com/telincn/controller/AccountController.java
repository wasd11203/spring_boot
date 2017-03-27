package com.telincn.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telincn.entity.User;
import com.telincn.service.UserService;
import com.telincn.util.JsonResult;

/**
 * 针对 登录的账户 的请求处理类
 * 
 * @author 99
 *
 */
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userServiceImpl;

	/**
	 * 处理 用户存在 检查 请求:根据请求携带的参数 ,判断DB中是否存在该用户名对应的用户 1.
	 * 查询DB,判断是否可以获取到对应的信息,将结果返回给前端
	 * 
	 * @param username
	 *            被检测的用户名
	 * @return Json类型数据
	 */
	@RequestMapping("/checkUser.do")
	public JsonResult<User> checkUser(String username) {

		JsonResult<User> result;

		if (null == username || username.trim().length() == 0) {
			result = new JsonResult<User>("用户名不能为空");
			return result;
		}

		User user = userServiceImpl.findByUsername(username);

		if (null == user) {
			// 正常结果,返回data域为空作为web中判断条件
			result = new JsonResult<User>(user);
			return result;
		}

		result = new JsonResult<User>(user);

		return result;
	}

	/**
	 * 处理登录请求: 根据请求携带的参数,查询DB,比对参数与查询结果,返回对应的结果数据给前端
	 * 
	 * @param username
	 *            要登录的账号的账号
	 * @param password
	 *            要登录的账号的密码
	 * @param session
	 *            登录成功时将用户信息存入session
	 * @return 比对的Json类型的结果数据
	 */
	@RequestMapping("/login.do")
	public JsonResult<User> login(String username, String password, HttpSession session) {

		JsonResult<User> result;

		if (null == username || username.trim().length() == 0) {
			result = new JsonResult<User>("用户名不能为空");
			return result;
		}
		if (null == password || password.trim().length() == 0) {
			result = new JsonResult<User>("密码不能为空");
			return result;
		}

		User user = userServiceImpl.findByUsername(username);

		if (null == user) {
			// 实际错误是 用户名不存在
			result = new JsonResult<User>("用户名或密码错误");
			return result;
		}
		if (!password.equals(user.getPassword())) {
			result = new JsonResult<User>("用户名或密码错误");
			return result;
		}

		result = new JsonResult<User>(user);
		session.setAttribute("user", user);

		return result;

	}

	/**
	 * 处理查询账号信息请求: 根据session中保存的用户id,查询DB,并将查询到的数据返回给前端
	 * 
	 * @return Json类型的结果数据
	 */
	@RequestMapping("/loadAccountInfo.do")
	public JsonResult<User> loadAccountInfo(HttpSession session) {

		JsonResult<User> result;
		String userId;

		User cur_user = (User) session.getAttribute("user");

		if (null == cur_user) {
			result = new JsonResult<User>("用户未登录,请先登录");
			return result;
		}

		userId = cur_user.getId();
		cur_user = userServiceImpl.findByUserId(userId);

		if (null == cur_user) {
			result = new JsonResult<User>("登录异常,请先重新登录");
			return result;
		}

		result = new JsonResult<User>(cur_user);
		return result;

	}

	/**
	 * 更新当前账户(session 中保存的当前账户)的信息: 根据请求携带的参数信息 与 当前账户比对,根据比对结果更新当前账户的信息
	 * 
	 * @param userId
	 *            请求中携带的账户id
	 * @param username
	 *            请求中携带的新的用户名
	 * @param password
	 *            请求中携带的新的密码
	 * @param session
	 *            session域
	 * @return Json类型的数据
	 */
	@RequestMapping("/updateAccount.do")
	public JsonResult<User> updateAccount(String userId, String username, String password, HttpSession session) {

		JsonResult<User> result;
		String cur_userId;

		User cur_user = (User) session.getAttribute("user");

		if (null == cur_user) {
			result = new JsonResult<User>("用户未登录,请先登录");
			return result;
		}
		if (null == userId || userId.trim().isEmpty()) {
			result = new JsonResult<User>("用户id不能为空");
			return result;
		}

		cur_userId = cur_user.getId();

		if (!userServiceImpl.existsUser(cur_userId) || !cur_userId.equals(userId)) {
			result = new JsonResult<User>("登录异常,请尝试重新登录后更新");
			return result;
		}

		if (null == username || username.trim().isEmpty()) {
			result = new JsonResult<User>("用户名不能为空");
			return result;
		}
		if (null == password || password.trim().isEmpty()) {
			result = new JsonResult<User>("密码不能为空");
			return result;
		}

		User user = userServiceImpl.findByUsername(username);

		if (null != user && !user.getId().equals(userId)) {
			result = new JsonResult<User>("用户名已被使用");
			return result;
		}

		user = userServiceImpl.updateUser(userId, username, password);
		result = new JsonResult<User>(user);

		return result;

	}

	/**
	 * 处理账号退出请求: 移除session中保存的用户信息 1. 如果session中保存了用户信息,将其取出返回结果数据给前端并移除
	 * session中的该用户
	 * 
	 * @return Json类型的结果数据
	 */
	@RequestMapping("/exit.do")
	public JsonResult<User> exit(HttpSession session) {
		JsonResult<User> result;
		// String userId;

		User cur_user = (User) session.getAttribute("user");

		if (null == cur_user) {
			result = new JsonResult<User>("登录异常");
			return result;
		}
		// userId = cur_user.getId();
		session.removeAttribute("user");

		result = new JsonResult<User>(cur_user);

		return result;

	}

}
