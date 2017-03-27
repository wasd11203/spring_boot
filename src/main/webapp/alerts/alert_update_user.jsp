<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="update_user" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="panel panel-warning ">

				<div class="panel-heading modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h3 class="panel-title modal-title" id="myModalLabel">更新用户</h3>
				</div>

				<div class="panel-body modal-body">
					<ul class="list-group">
						<li class="info list-group-item">
							<span class="label label-info">用户名:</span>
							<input type="text" name="user_name" id="user_name" maxlength=10/>
							<div class="username_check_Msg">
							
							</div>
						</li>
						<li class="info list-group-item">
							<span class="label label-info">密码:</span>
							<input type="password" name="user_password" id="user_password" maxlength=10/>
							<div class="password_check_Msg">
							
							</div>
						</li>
						<li class="info list-group-item">
							<span class="label label-info">确认密码:</span>
							<input type="password" name="ck_user_password" id="ck_user_password" maxlength=10/>
							<div class="ck_password_check_Msg">
							
							</div>
						</li>
					</ul>
				</div>

				<div class="panel-footer modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary disabled" id="commit">提交</button>
				</div>

			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<script type="text/javascript">

$(function(){
	
	/**
	  * 为 弹出框---更新用户 中的 用户名输入框 绑定输入框绑定输入框中内容改变的事件
	  *  内容一旦被修改,触发事件:
	  *	  1. 先判断用户名输入框中的内容和密码输入框以及确认密码输入框中是否存在空值:
	  *		1. 如果存在:禁用提交按钮,并给出提示信息
	  *		2. 否则:调用方法判断用户名是否重名,给出响应的提示信息
	  */
	$("#update_user #user_name").bind('input propertychange', updateUser_CheckValue);
	  
	  
   /**
  	 * 为 弹出框---更新用户  中的 密码输入框 绑定输入框绑定输入框中内容改变的事件
	  *  内容一旦被修改,触发事件:
		  *	  1. 先判断用户名输入框中的内容和密码输入框以及确认密码输入框中是否存在空值:
		  *		1. 如果存在:禁用提交按钮,并给出提示信息
		  *		2. 否则:调用方法判断用户名是否重名,给出响应的提示信息
		  */
	$("#update_user #user_password").bind('input propertychange', updateUser_CheckValue);
  	
  	 /**
   	 * 为 弹出框---更新用户  中的确认密码输入框 确认密码输入框 绑定输入框中内容改变的事件
	 *  内容一旦被修改,触发事件:
	 *	  1. 先判断用户名输入框中的内容和密码输入框以及确认密码输入框中是否存在空值:
	 *		1. 如果存在:禁用提交按钮,并给出提示信息
	 *		2. 否则:调用方法判断用户名是否重名,给出响应的提示信息
	 */
  	$("#update_user #ck_user_password").bind('input propertychange', updateUser_CheckValue);
	
	/**
	 * 为当前页面上的 提交 按钮 绑定点击事件
	 *  提交确定修改的请求
	 */
	$("#update_user #commit").on("click",updateUserAction);
});

/**
 * 为 弹出框--更新用户  中 ,判断用户名输入框中的用户名和密码以及确认密码内容是否为空,
 *	1. 如果其中任意一个为空,则提交按钮依然被禁用
 *	2. 否则 ,发送AJAX请求,查询DB,根据响应结果判断DB中是否已经存在该用户名:
 *		1. 如果响应结果中的state属性 == SUCCESS:
 *	 		1. 如果响应结果中data属性值为空:
 *	 			提示:"用户名可用"
 *	 		2. 否则,提示"该用户名不可用"
 *  	2. 否则,提示错误信息
 */
function updateUser_CheckValue() {
	var ary = $("#update_user .list-group input").serializeArray();
	var counts = 0;
	for(var i = 0; i < ary.length; i++) {
		if(ary[i].value != "") {
			counts++;
		}
		if(ary[0].value != "") {
			updateUserCheckAction();
		}else{
			var errorInfo = $('<label class="text-danger left" >该用户名不能为空</label>');
			$("#update_user .username_check_Msg").empty();
			$("#update_user .username_check_Msg").append(errorInfo);
		}
	}

	if(counts < ary.length) {

		//console.log("==== 条件不完全满足");
		if( !$("#update_user #commit").hasClass("disabled")){
			$("#update_user #commit").addClass("disabled");
		}
		return;
	}
	
	if(ary[1].value != ary[2].value){
		
		//console.log("==== 密码确认错误,条件不完全满足");
		var errorInfo = $('<label class="text-danger left" >两次密码不一致</label>');
		$("#update_user .ck_password_check_Msg").empty();
		$("#update_user .ck_password_check_Msg").append(errorInfo);

		if( !$("#update_user #commit").hasClass("disabled")){
			$("#update_user #commit").addClass("disabled");
		}
		return;
	}
	
	var successInfo = $('<label class="text-success left" >Ok</label>');
	$("#update_user .ck_password_check_Msg").empty();
	$("#update_user .ck_password_check_Msg").append(successInfo);
	
	if( $("#update_user #commit").hasClass("disabled")){
		$("#update_user #commit").removeClass("disabled");
	}
	//console.log("==== 条件完全满足");
	return;
}

/**
 * 为 弹出框--更新用户中 ,判断用户名输入框中的用户名,是否在DB中已经存在 发送AJAX请求,根据响应结果判断:
 *	1. 如果响应结果中的state属性 == SUCCESS:
 *	 	1. 如果响应结果中data属性值为空:
 *	 		提示:"用户名可用"
 *	 	2. 否则,提示"该用户名不可用"
 *  2. 否则,提示错误信息
 */
function updateUserCheckAction(){
	var url = "/user_manager/checkUser.do";
	var username = $("#update_user #user_name").val();
	var data = {"username":username};
	
	if(model.currentUser.username == username){
		
		var errorInfo = $('<label class="text-info left" >该用户名未修改</label>');
		$("#update_user .username_check_Msg").empty();
		$("#update_user .username_check_Msg").append(errorInfo);
		
		return ;
	}
	
	$.post(url,data,function(result){
		if(result.state == SUCCESS){
			
			if(result.data != null){
				
				var errorInfo = $('<label class="text-danger left" >该用户名不可用</label>');
				$("#update_user .username_check_Msg").empty();
				$("#update_user .username_check_Msg").append(errorInfo);
				
				console.log("用户名判定已经重名");
				if( !$("#update_user #commit").hasClass("disabled")){
					$("#update_user #commit").addClass("disabled");
				}
				return ;
			}
			if(result.data == null){
				var successInfo = $('<label class="text-success left" > 该用户名可用 </label>');
				$("#update_user .username_check_Msg").empty();
				$("#update_user .username_check_Msg").append(successInfo);
				console.log("用户名判定未重名");
			}
		}else{
			alert(result.message);
			return ;
		}
	});
}

/**
 * 更新当前点击用户的信息
 * 1. 使用AJAX 请求,指定提交的请求地址+当前要更新用户的信息+当前要更新用户的id,向服务器发送请求
 * 2. 接受到服务器返回的数据result后,判断result.state 的值
 * 		1. 如果是 SUCCESS : 那么利用model更新显示数据域
 * 		2. 否则 弹出失败信息框
 */
function updateUserAction(){
	var url = "/user_manager/updateUser.do";
	var userId = model.currentUser.id;
	var username = $("#update_user #user_name").val();
	var userpassword = $("#update_user #user_password").val();
	var data = {"userId":userId,"username":username,"password":userpassword};
	
	$.post(url,data,function(result){
		if(result.state == SUCCESS){
//			console.log(result.data);
			model.updateUser(result.data);
			model.updateUsersView();
			$("#update_user").modal('hide');
		}else{
			alert(result.message);
		}
	});
}
</script>