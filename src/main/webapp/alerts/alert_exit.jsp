<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="exit_account" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="panel panel-danger ">

				<div class="panel-heading modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h3 class="panel-title modal-title" id="myModalLabel">退出账号</h3>
				</div>

				<div class="panel-body modal-body">
					<ul class="list-group">
						<li class="info list-group-item">
							<span class="text-warning">
								是否退出当前用户？
							</span>
						</li>
						
					</ul>
				</div>

				<div class="panel-footer modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="commit">确定</button>
				</div>

			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<script type="text/javascript">
	/*
	 * 为当前页面上的 提交 按钮 绑定点击事件
	 *  提交 确定退出 的请求
	 */
	$("#exit_account #commit").on("click",exitAccountAction);
	
	/**
	 * 退出当前账号
	 * 1. 传入的参数的封装即为当前账号的id,将其封装Json类型的对象data,作为请求参数 
	 * 2. 使用AJAX 请求,指定提交的请求地址+data,向服务器发送请求
	 * 3. 接受到服务器返回的数据result后,判断result.state 的值
	 * 		1. 如果是 SUCCESS : 那么利用model更新显示数据域
	 * 		2. 否则 弹出失败信息框
	 */
	function exitAccountAction(){
		var url = "/account/exit.do";
		var userId = model.CurrentAccount.id;
		var data = {"userId":userId};
		$.post(url,data,function(result){
			if(result.state == SUCCESS){
				model.updateCurrentAccountAndElements({});
				window.location.href="/";
			}else{
				alert(result.message);
			}
		});
	}
</script>
