$(function() {
	/**
	 * index 页面加载时 加载默认页面
	 */
	loadDefaultAction();
	$(".home").on("click",loadDefaultAction);
	$(".user").on("click",loadUserListAction);
	$(".order").on("click",loadOrderListAction);
	$(".accountInfo").on("click",loadAccountInfoAction);
	/*保存当前登录的用户*/
	saveCurrentAccount();
});

/**
 * 保存当前登录的用户到页面上
 * 1. 获取页面中用于保存登录用户的元素的值
 * 2. 将其保存到model的CurrentAccount属性中
 * 3. 更新保存登录用户的元素的值
 */
function saveCurrentAccount(){
	var username = $("#cur_username").val();
	var userId = $("#cur_userId").val();
	if(null != username && username.trim() != "" && null != userId && userId.trim() != ""){
		CurrentAccount = {"id":userId,"username":username};
		model.updateCurrentAccountAndElements(CurrentAccount);
		return ;
	}
	model.CurrentAccount = {};
	return ;
}

/**
 * 加载默认内容域
 */
function loadDefaultAction() {
	$(this).siblings().removeClass("active");
	$(this).addClass("active");
	
	$("#show_data").empty();
	$("#alerts_contents").empty();
	$("#show_data").load("/contents/manager_default.jsp");
	
	return false;
}

/**
 * 列出除当前账号(服务器端session中存储的账户)之外的所有的用户
 * 	1. 使用 AJAX 请求,指定提交的请求地址,向服务器发送请求
 * 	2. 接受到服务器返回的数据result后,判断result.state 的值
 * 		1. 如果是 SUCCESS : 那么利用model更新显示数据域
 * 		2. 否则 弹出失败信息框
 */
function loadUserListAction() {
	$(this).siblings().removeClass("active");
	$(this).addClass("active");
	
	var url = "/user_manager/listUsers.do";
	$.post(url,function(result){
		if(result.state == SUCCESS){
//			console.log(result.data);
			model.updateUsers(result.data);
		}else{
			alert(result.message);
		}
	});
	
	return false;
}

/**
 * 列出所有的订单信息
 * 1. 使用 AJAX 请求,指定提交的请求地址,向服务器发送请求
 * 2. 接受到服务器返回的数据result后,判断result.state 的值
 * 		1. 如果是 SUCCESS : 那么利用model更新显示数据域
 * 		2. 否则 弹出失败信息框
 */
function loadOrderListAction() {
	$(this).siblings().removeClass("active");
	$(this).addClass("active");
	
	var url = "/order_manager/listOrders.do"
	$.post(url, function(result) {
		if(result.state == SUCCESS){
//			console.log(result.data);
			model.updateOrders(result.data);
		}else{
			alert(result.message);
		}
	});
	
	return false;
}

/**
 * 列出当前账户(服务器端session中存储的账户)的信息
 * 1. 使用AJAX 请求,指定提交的请求地址,向服务器发送请求
 * 2. 接受到服务器返回的数据result后,判断result.state 的值
 * 		1. 如果是 SUCCESS : 那么利用model更新显示数据域
 * 		2. 否则 弹出失败信息框
 */
function loadAccountInfoAction() {
	$(this).siblings().removeClass("active");
	$(this).addClass("active");
	$(".navbar").hide();
	
	var url = "/account/loadAccountInfo.do";
	$.post(url,function(result){
		if(result.state == SUCCESS){
//			console.log(result.data);
			model.updateCurrentAccountAll(result.data);
		}else{
			alert(result.message);
			window.location.href="/";
		}
	});
	
//	var userId = model.CurrentAccount.id;
//	var username = model.CurrentAccount.username;
//	var data = {"userId":userId};
//	$.post(url,data,function(result){
//		if(result.state == SUCCESS){
////			console.log(result.data);
//			model.updateCurrentAccountAll(result.data);
//		}else{
//			alert(result.message);
//		}
//	});
	
	return false;
}
