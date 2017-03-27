<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="update_order" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="panel panel-warning ">

				<div class="panel-heading modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h3 class="panel-title modal-title" id="myModalLabel">更新订单</h3>
				</div>

				<div class="panel-body modal-body">
					<ul class="list-group">
						<li class="info list-group-item">
							<span class="label label-info">订单名:</span>
							<input type="text" name="order_name" id="order_name" maxlength=10/>
							<div class="checkMsg">
							
							</div>
						</li>
					</ul>
				</div>

				<div class="panel-footer modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="commit">提交</button>
				</div>

			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<script type="text/javascript">

$(function (){
	/**
	 * 为 弹出框---更新订单 中的输入框绑定输入框中内容改变的事件
	 *  内容一旦被修改,就触发事件,想服务器发送请求
	 */
	$("#update_order #order_name").bind('input propertychange', checkOrder);
	
	/**
	 * 为当前页面上的 提交 按钮 绑定点击事件
	 *  提交确定修改的请求
	 */
	$("#update_order #commit").on("click",updateOrderAction);
	
});

/**
 * 为 弹出框---更新订单 中,判断用户输入的订单名 是否为空,并作出相应的提示和修改提交按钮的样式:
 *	 1. 如果 订单名输入框中的内容或者输入的内容在被清除两端空白后 被 判断不为空时:
 *	 	提示:"OK"
 *		并将按钮的禁用模式接触
 * 	 2. 否则:
 *		 提示 "订单不能为空"
 *		并打开按钮的禁用模式
 */
function checkOrder(){
	var order_name = $("#update_order #order_name").val();
	if( null == order_name ||  $.trim(order_name) == "" ){
		var errorInfo = $('<label class="text-danger left" >订单名不能为空</label>');
		$("#update_order .checkMsg").empty();
		$("#update_order .checkMsg").append(errorInfo);
		
		if( !$("#update_order #commit").hasClass("disabled")){
			$("#update_order #commit").addClass("disabled");
		}
		
		return ;
	}else{
		var successInfo = $('<label class="text-success left" > OK </label>');
		$("#update_order .checkMsg").empty();
		$("#update_order .checkMsg").append(successInfo);
		
		if( $("#update_order #commit").hasClass("disabled")){
			$("#update_order #commit").removeClass("disabled");
		}
		
		return ;
	}
}
   
/**
 * 更新当前点击订单的信息
 * 1. 使用AJAX 请求,指定提交的请求地址+当前要更新订单的信息+当前要更新订单的id,向服务器发送请求
 * 2. 接受到服务器返回的数据result后,判断result.state 的值
 * 		1. 如果是 SUCCESS : 那么利用model更新显示数据域
 * 		2. 否则 弹出失败信息框
 */
function updateOrderAction(){
	var url = "/order_manager/updateOrder.do";
	var orderId = model.currentOrder.id;
	var ordername = $("#update_order #order_name").val();
	var data = {"orderId":orderId,"ordername":ordername};
	
	$.post(url,data,function(result){
		if(result.state == SUCCESS){
//			console.log(result.data);
			model.updateOrder(result.data);
			model.updateOrdersView();
		}else{
			alert(result.message);
		}
	});
}
</script>