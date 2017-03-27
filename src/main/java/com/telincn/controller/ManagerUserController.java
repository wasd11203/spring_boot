package com.telincn.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telincn.entity.User;
import com.telincn.service.UserService;
import com.telincn.util.JsonResult;

/**
 * 针对 用户 的请求处理类
 * 
 * @author 99
 *
 */
@RestController
@RequestMapping("/user_manager")
public class ManagerUserController {

	@Autowired
	private UserService userServiceImpl;

	/**
	 * 处理 用户存在 检查 请求:根据请求携带的参数 ,判断DB中是否存在该用户名对应的用户 
	 * 	1. 查询DB,判断是否可以获取到对应的信息,将结果返回给前端
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
	 * 处理 查询所有除当前账号(session中保存的当前账号)之外的用户 请求: 
	 * 	根据session中保存的账号id,查询DB,将排除当前账号之后的的数据返回给前端
	 * 
	 * @return Json类型数据
	 */
	@RequestMapping("/listUsers.do")
	public JsonResult<List<User>> listUsers( HttpSession session) {

		JsonResult<List<User>> result;
		String userId;

		User cur_user = (User) session.getAttribute("user");
		
		if (null == cur_user) {
			result = new JsonResult<List<User>>("登录异常,请重新登录后尝试");
			return result;
		}

		userId = cur_user.getId();
		
		if (!userServiceImpl.existsUser(userId)) {
			result = new JsonResult<List<User>>("当前用户不存在,请重新登录后再试");
			return result;
		}

		List<User> list = userServiceImpl.getAllUserExceptCurrentUser(userId);
		
//		if (null == list || list.isEmpty()) {
//			result = new JsonResult<List<User>>("用户列表为空");
//			return result;
//		}

		result = new JsonResult<List<User>>(list);

		return result;
	}

	/**
	 * 处理 更新指定用户 请求: 
	 * 	根据请求携带的参数,查询DB并更新指定的记录,返回最新的指定用户信息并将其返回给前端
	 * 
	 * @param userId
	 *            要更新的用户id
	 * @param username
	 *            最新的用户名
	 * @param password
	 *            最新的用户密码
	 * @return Json类型数据
	 */
	@RequestMapping("/updateUser.do")
	public JsonResult<User> updateUser(String userId, String username, String password) {
		
		JsonResult<User> result;

		if (null == userId || userId.trim().isEmpty()) {
			result = new JsonResult<User>("用户id不能为空");
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
		
		if (null != user && !user.getId().equals( userId)) {
			result = new JsonResult<User>("用户名已被使用");
			return result;
		}

		user = userServiceImpl.updateUser(userId, username, password);
		result = new JsonResult<User>(user);

		return result;
	}

	/**
	 * 处理 删除指定用户的 请求:
	 *  根据请求携带的参数,查询DB并删除指定用户信息,并将删除的指定用户信息返回给前端
	 * 
	 * @param userId
	 *            要删除的用户id
	 * @return Json类型数据
	 */
	@RequestMapping("/deleteUser.do")
	public JsonResult<User> deleteUser(String userId) {

		JsonResult<User> result;

		if (null == userId || userId.trim().isEmpty()) {
			result = new JsonResult<User>("用户id不能为空");
			return result;
		}
		if (!userServiceImpl.existsUser(userId)) {
			result = new JsonResult<User>("用户不存在,请刷新重试");
			return result;
		}
		
		User user = userServiceImpl.delUser(userId);
		result = new JsonResult<User>(user);

		return result;
	}

	/**
	 * 处理 新增用户 请求 :
	 * 	根据请求携带的参数,新增一条用户记录插入到DB中,并返回新增的用户信息数据给前端
	 * 
	 * @param username
	 *            新增的用户名
	 * @param password
	 *            新增的用户密码
	 * @return Json类型数据
	 */
	@RequestMapping("/createUser.do")
	public JsonResult<User> createUser(String username, String password) {

		JsonResult<User> result;

		if (null == username || username.trim().isEmpty()) {
			result = new JsonResult<User>("用户名不能为空");
			return result;
		}
		if (null == password || password.trim().isEmpty()) {
			result = new JsonResult<User>("密码不能为空");
			return result;
		}

		User user = userServiceImpl.findByUsername(username);

		if (null != user) {
			result = new JsonResult<User>("用户名已被使用");
			return result;
		}

		user = userServiceImpl.createUser(username, password);
		result = new JsonResult<User>(user);

		return result;
	}

}