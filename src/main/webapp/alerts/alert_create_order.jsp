<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="create_order" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3 class="panel-title modal-title" id="myModalLabel">创建订单</h3>
			</div>

			<div class="modal-body">
				<ul class="list-group">
					<li class="info list-group-item">
						<span class="label label-info">订单名:</span>
						<input type="text" name="order_name" id="order_name" maxlength=10/>
						
						<div class="checkMsg">
							
						</div>
						
					</li>
				</ul>
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
$(function (){
	/**
	  * 为 弹出框---创建订单中 的输入框绑定输入框中内容改变的事件
	  *  内容一旦被修改,就触发事件,想服务器发送请求
	  */
	$("#create_order #order_name").bind('input propertychange', checkOrder);
	
	/**
	 * 为当前页面上的 提交 按钮 绑定点击事件
	 *  提交 确定新增 的请求
	 */
	$("#create_order #commit").on("click",createOrderAction);
});

/**
  *  为 弹出框---创建订单中 ,对输入框中的内容进行判断,并根据不同的结果,显示不同的提示信息
  *  1. 如果 输入的内容为空或者输入的内容在被清除两端空白后为空时:
  *	  	提示该处不能为空
  *	 2. 如果输入的内容部位空:
  *		 提示 "OK"
  */
function checkOrder(){
	var order_name = $("#create_order #order_name").val();
	if( null == order_name ||  $.trim(order_name) == "" ){
		var errorInfo = $('<label class="text-danger left" >订单名不能为空</label>');
		$("#create_order .checkMsg").empty();
		$("#create_order .checkMsg").append(errorInfo);
		
		if( !$("#create_order #commit").hasClass("disabled")){
			$("#create_order #commit").addClass("disabled");
		}
		
		return ;
	}else{
		var successInfo = $('<label class="text-success left" > OK </label>');
		$("#create_order .checkMsg").empty();
		$("#create_order .checkMsg").append(successInfo);
		
		if( $("#create_order #commit").hasClass("disabled")){
			$("#create_order #commit").removeClass("disabled");
		}
		
		return ;
	}
}

/**
 * 新建订单
 * 1. 传入的参数的封装即为新增订单的基本信息,将其封装Json类型的对象data,作为请求参数 
 * 2. 使用AJAX 请求,指定提交的请求地址+data,向服务器发送请求
 * 3. 接受到服务器返回的数据result后,判断result.state 的值
 * 		1. 如果是 SUCCESS : 那么利用model更新显示数据域
 * 		2. 否则 弹出失败信息框
 */
function createOrderAction(){
	var url = "/order_manager/createOrder.do";
	var ordername = $("#create_order #order_name").val();
	var data = {"ordername":ordername};
//	console.log(data);
	$.post(url,data,function(result){
		if(result.state == SUCCESS){
			model.orders.unshift(
					{"id":result.data.id, 
					 "order_name":result.data.order_name});
			model.currentOrder = result.data;
			model.currentOrderIndex = 0;
			model.updateOrdersView();
			
		}else{
			alert(result.message);
		}
	});
}
	
</script>