
$(function(){
	$("#subbtn").on("click",loginAction);
	
	$('#username').bind('input propertychange', toggleSubBtn);
	$('#pwd').bind('input propertychange', toggleSubBtn);
});

/*更换 提交按钮的样式 和 提交按钮的 状态*/
function toggleSubBtn(){
	var pwd = $("#pwd").val();
	var username = $("#username").val();
	if( $.trim(pwd) == "" || $.trim( username) == ""){
		if( !$("#subbtn").hasClass("disabled")){
			$("#subbtn").addClass("disabled");
		}
	}else{
		if( $("#subbtn").hasClass("disabled")){
			$("#subbtn").removeClass("disabled");
		}
	}
}

/**
 * 点击登录按钮时,触发的事件函数
 * 	1. 向服务器发送AJAX请求,提交的请求中携带 请求路径 + 登录的账号密码, 有服务器处理请求
 * 	2. 如果服务器返回的result.state 的值为 SUCCESS:
 * 		则转到登录后的首页
 * 	   否则弹出错误信息提示框
 */
function loginAction(){
	var username = $("#username").val();
	var password = $("#pwd").val();
//	alert(username+":" + password);
	var data = {"username":username,"password":password};
	
	$.post("account/login.do",data,function(result){
//		console.log(data);
		if(result.state == SUCCESS){
			window.location.href="/manager/index.do";
			return ;
		}else{
			alert(result.message);
			return ;
		}
	});
	
}
