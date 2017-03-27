<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="delete_order" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="panel panel-danger ">

				<div class="panel-heading modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h3 class="panel-title modal-title" id="myModalLabel">删除订单</h3>
				</div>

				<div class="panel-body modal-body">
					<ul class="list-group">
						<li class="info list-group-item">
							<span class="text-warning">
								是否删除订单名为：
								<input type="text" name="order_name" id="order_name" disabled/>
								的订单？
							</span>
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
	$(function(){
		/**
		 * 为当前页面上的 提交 按钮 绑定点击事件
		 *  提交确定删除的请求
		 */
		$("#delete_order #commit").on("click",deleteOrderAction);
	});
	
	
	/**
	 * 删除当前点击的订单
	 * 1. 使用AJAX 请求,指定提交的请求地址+当前要删除订单的id,向服务器发送请求
	 * 	2. 接受到服务器返回的数据result后,判断result.state 的值
	 * 		1. 如果是 SUCCESS : 那么利用model更新显示数据域
	 * 		2. 否则 弹出失败信息框
	 */
	function deleteOrderAction(){
		var url = "/order_manager/deleteOrder.do";
		var orderId = model.currentOrder.id;
		var data ={"orderId":orderId};
		$.post(url,data,function(result){
			if(result.state == SUCCESS){
//				console.log(result.data);
				model.orders.splice(model.currentOrderIndex,1);
				model.currentOrderIndex = (model.currentOrderIndex+1)/(model.orders.length);
				model.currentOrder = model.users[model.currentOrderIndex];
				model.updateOrdersView();
			}else{
				alert(result.message);
			}
		});
	}
</script>