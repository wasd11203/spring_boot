<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="create_user" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3 class="panel-title modal-title" id="myModalLabel">创建用户</h3>
			</div>

			<div class="modal-body">
				<ul class="list-group">
					<li class="info list-group-item">
						<span class="label label-info">用户名:</span>
						<input type="text" name="user_name" id="user_name" maxlength=10/>
						<div class="username_check_Msg">
							
						</div>
					</li>
					<li class="info list-group-item">
						<span class="label label-info">密码:</span>
						<input type="password" name="user_password" id="user_password" maxlength=10 />
						<div class="password_check_Msg">
							
						</div>
					</li>
					<li class="info list-group-item">
						<span class="label label-info">确认密码:</span>
						<input type="password" name="ck_user_password" id="ck_user_password" maxlength=10 />
						<div class="ck_password_check_Msg">
							
						</div>
					</li>
				</ul>
				<div class="Msg">
					<span class="label label-primary" >请注意:要提交的内容如果存在空值或者用户名不可用将无法提交</span>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary disabled" id="commit">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<script type="text/javascript">

$(function(){
	
	/**
	  * 为 弹出框--创建用户 中的 用户名输入框 绑定输入框中内容改变的事件
	  *  内容一旦被修改,触发事件:
	  *	  1. 先判断用户名输入框中的内容和密码输入框以及确认密码输入框中是否存在空值:
	  *		1. 如果存在:禁用提交按钮,并给出提示信息
	  *		2. 否则:调用方法判断用户名是否重名,给出响应的提示信息
	  */
	$("#create_user #user_name").bind('input propertychange', createUser_CheckValue);
	  
	/**
	  * 为 弹出框--创建用户 中的 密码输入框 绑定输入框绑定输入框中内容改变的事件
	  *  内容一旦被修改,触发事件:
	  *	  1. 先判断用户名输入框中的内容和密码输入框以及确认密码输入框中是否存在空值:
	  *		1. 如果存在:禁用提交按钮,并给出提示信息
	  *		2. 否则:调用方法判断用户名是否重名,给出响应的提示信息
	  */
	$("#create_user #user_password").bind('input propertychange', createUser_CheckValue);
	  
   /**
   	 * 为 弹出框---更新用户  中的 确认密码输入框 绑定输入框中内容改变的事件
	 *  内容一旦被修改,触发事件:
	 *	  1. 先判断用户名输入框中的内容和密码输入框以及确认密码输入框中是否存在空值:
	 *		1. 如果存在:禁用提交按钮,并给出提示信息
	 *		2. 否则:调用方法判断用户名是否重名,给出响应的提示信息
	 */
  	$("#create_user #ck_user_password").bind('input propertychange', createUser_CheckValue);
	
	/**
	 * 为当前页面上的 提交 按钮 绑定点击事件
	 *  提交 确定新增 的请求
	 */
	$("#create_user #commit").on("click",createUserAction);
	
});

/**
 * 为 弹出框--创建用户 中 ,判断用户名输入框中的用户名和密码以及确认密码内容是否为空,
 *	1. 如果其中任意一个为空,则提交按钮依然被禁用
 *	2. 否则 ,发送AJAX请求,查询DB,根据响应结果判断DB中是否已经存在该用户名:
 *		1. 如果响应结果中的state属性 == SUCCESS:
 *	 		1. 如果响应结果中data属性值为空:
 *	 			提示:"用户名可用"
 *	 		2. 否则,提示"该用户名不可用"
 *  	2. 否则,提示错误信息
 */
function createUser_CheckValue() {
	var ary = $("#create_user .list-group input").serializeArray();
	var counts = 0;
	for(var i = 0; i < ary.length; i++) {
		if(ary[i].value != "") {
			counts++;
		}
		if(ary[0].value != "") {
			createUserCheckAction();
		}else{
			var errorInfo = $('<label class="text-danger left" >该用户名不能为空</label>');
			$("#create_user .username_check_Msg").empty();
			$("#create_user .username_check_Msg").append(errorInfo);
		}
	}
	
	if(counts < ary.length) {

		//console.log("==== 条件不完全满足");
		if( !$("#create_user #commit").hasClass("disabled")){
			$("#create_user #commit").addClass("disabled");
		}
		
		return;
	}
	
	if(ary[1].value != ary[2].value){
		
		console.log("==== 密码确认错误,条件不完全满足");
		var errorInfo = $('<label class="text-danger left" >两次密码不一致</label>');
		$("#create_user .ck_password_check_Msg").empty();
		$("#create_user .ck_password_check_Msg").append(errorInfo);

		if( !$("#create_user #commit").hasClass("disabled")){
			$("#create_user #commit").addClass("disabled");
		}
		return;
	}
	
	var successInfo = $('<label class="text-success left" >Ok</label>');
	$("#create_user .ck_password_check_Msg").empty();
	$("#create_user .ck_password_check_Msg").append(successInfo);
	
	
	if( $("#create_user #commit").hasClass("disabled")){
		$("#create_user #commit").removeClass("disabled");
	}
	//console.log("==== 条件完全满足");
	return;
}

/**
 * 为 弹出框--创建用户中 ,判断用户名输入框中的用户名,是否在DB中已经存在 发送AJAX请求,根据响应结果判断:
 *	1. 如果响应结果中的state属性 == SUCCESS:
 *	 	1. 如果响应结果中data属性值为空:
 *	 		提示:"用户名可用"
 *	 	2. 否则,提示"该用户名不可用"
 *  2. 否则,提示错误信息
 */
function createUserCheckAction(){
	var url = "/user_manager/checkUser.do";
	var username = $("#create_user #user_name").val();
	var data = {"username":username};
	$.post(url,data,function(result){
		if(result.state == SUCCESS){
			
			if(result.data != null){
				var errorInfo = $('<label class="text-danger left" >该用户名不可用</label>');
				$("#create_user .username_check_Msg").empty();
				$("#create_user .username_check_Msg").append(errorInfo);
				
				//console.log("用户名判定已经重名");
				if( !$("#create_user #commit").hasClass("disabled")){
					$("#create_user #commit").addClass("disabled");
				}
				return ;
			}
			if(result.data == null){
				var successInfo = $('<label class="text-success left" > 该用户名可用 </label>');
				$("#create_user .username_check_Msg").empty();
				$("#create_user .username_check_Msg").append(successInfo);
				//console.log("用户名判定未重名");
			}
			
			
		}else{
			alert(result.message);
			return ;
		}
	});
}

	
/**
 * 新建用户
 * 1. 传入的参数的封装即为新增用户的基本信息,将其封装Json类型的对象data,作为请求参数 
 * 2. 使用AJAX 请求,指定提交的请求地址+data,向服务器发送请求
 * 3. 接受到服务器返回的数据result后,判断result.state 的值
 * 		1. 如果是 SUCCESS : 那么利用model更新显示数据域
 * 		2. 否则 弹出失败信息框
 */
function createUserAction(){
	var username = $("#create_user #user_name").val();
	var password = $("#create_user #user_password").val();
	var url = "/user_manager/createUser.do";
	var data = {"username":username,"password":password};
		
//	console.log(data);
	$.post(url,data,function(result){
		if(result.state == SUCCESS){
			model.users.unshift(
					{id:result.data.id, 
					username:result.data.username,
					password:result.data.password});
			model.currentUser = result.data;
			model.currentUserIndex = 0;
			model.updateUsersView();
			$("#create_user").modal('hide');
		}else{
			alert(result.message);
		}
	});
}
	
</script>