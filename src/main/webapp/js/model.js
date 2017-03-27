/**
 * 用于保存、修改、删除当前显示内容数据的model对象
 */
var model = {
		CurrentAccount:{},
		"users":[],
		updateUsers:function(users){
			this.users = users;
			this.updateUsersView();
		},
		updateUsersView:function(){
			var show_data = $("#show_data").empty();
			$(".alerts_contents").empty();

			var panel_head = $('<div class="panel-heading">'+
			                    '	<h3 class="panel-title text-center">'+
			                    '		用户管理'+
			                    '	</h3>'+
			                    '</div>');
			var panel_body = $('<div class="panel-body"></div>');
			var body_table = $('<table class=" table table-bordered table-hover account_manager" id="account_manager">'+
								'	<thead>'+
								'		<tr>'+
								'			<th class="text-center">'+
								'				用户名'+
								'			</th>'+
								'			<th class="text-center">'+
								'				操作'+
								'			</th>'+
								'		</tr>'+
								'	</thead>'+
								'</table>'
								);
			var body_Tbody = $('	<tbody class="text-center" id="center_data">'+
								'	</tbody>');
			for(var i = 0 ; i<this.users.length;i++){
				var user_data = $('	<tr>'+
								  '		<td>'+
								  				this.users[i].username+
								  '		</td>'+
								  '	</tr>');
				var userManager = $('<td></td>');
				var userManager_Update = $('<button type="button" class="btn btn-warning btn-sm btn_update" data-toggle="modal" data-target="#update_user">修改</button>');
				var userManager_Delete = $('<button type="button" class="btn btn-danger btn-sm btn_delete" data-toggle="modal" data-target="#delete_user">删除</button>'); 
				
				userManager_Update.data('index', i);
				userManager_Delete.data('index', i);
				
				userManager_Update.on("click",function(){
//					alert("更新数据");
					
					var index = $(this).data('index');
					var user=model.users[index];	
					model.currentUser=user;
					model.currentUserIndex=index;
					
					$(".alerts_contents").load("/alerts/alert_update_user.jsp", function() {
						
						$("#user_name").val(model.currentUser.username);
						$("#user_password").val(model.currentUser.password);
						$("#update_user").modal('show');
						
					});
				});
				userManager_Delete.on("click",function(){
					
					var index = $(this).data('index');
					var user=model.users[index];	
					model.currentUser=user;
					model.currentUserIndex=index;
					
					$(".alerts_contents").load("/alerts/alert_delete_user.jsp", function() {
						$("#user_name").val(model.currentUser.username);
						$("#delete_user").modal('show');
					});
				});
				
				userManager.append(userManager_Update).append(" &nbsp;&nbsp;&nbsp; ").append(userManager_Delete);
				user_data.append(userManager);
				
				body_Tbody.append(user_data);
				if(model.currentUser && i == model.currentUserIndex){
						user_data.addClass("info");
				}
			}
			
			var Tbody_data_end = $('<tr>'+
									'	<td>'+
									'	</td>'+
									'	<td>'+
									'	</td>'+
									'	<td>'+
									'	</td>'+
									'</tr>');
			var Tbody_data_end_Manager = $('<td></td>');
			var Tbody_data_end_ManagerAdd = $('<button type="button" class="btn btn-info btn-sm btn_create" data-toggle="modal" data-target="#create_user">新增</button>');
			
			Tbody_data_end_ManagerAdd.on("click",function(){
				$(".alerts_contents").load("/alerts/alert_create_user.jsp", function() {
					$("#create_user").modal('show');
				});
			});
			
			Tbody_data_end_Manager.append(Tbody_data_end_ManagerAdd);
			Tbody_data_end.prepend(Tbody_data_end_Manager);
			
			body_Tbody.append(Tbody_data_end);
			body_table.append(body_Tbody);
			panel_body.append(body_table);
			
			var panel_foot = $('<div class="panel-footer">'+
								'	<span class="label label-primary">以上即为 此次查询得到的所有数据</span>'+
								'</div>');
			
			show_data.append(panel_head).append(panel_body).append(panel_foot);
		}
};

model.updateUser = function(user){
	model.currentUser = user;
	model.users[model.currentUserIndex] = user;
}
/**
  *
  *	model.UserFailedClassAdd = function(errorClass){
  *		$(".show_data #center_data").children().eq(model.currentUserIndex).addClass("danger");
  *	}
  * 
  */
model.orders = [];
model.updateOrders = function(orders){
	model.orders = orders;
	model.updateOrdersView();
}
model.updateOrdersView = function(){ 
		var show_data = $("#show_data").empty();
		$(".alerts_contents").empty();
		
		var panel_head = $('<div class="panel-heading">'+
		                    '	<h3 class="panel-title text-center">'+
		                    '		订单管理'+
		                    '	</h3>'+
		                    '</div>');
		var panel_body = $('<div class="panel-body"></div>');
		var body_table = $('<table class=" table table-bordered table-hover order_manager" id="order_manager">'+
							'	<thead>'+
							'		<tr>'+
							'			<th class="text-center">'+
							'				id'+
							'			</th>'+
							'			<th class="text-center">'+
							'				订单名'+
							'			</th>'+
							'			<th class="text-center">'+
							'				操作'+
							'			</th>'+
							'		</tr>'+
							'	</thead>'+
							'</table>'
							);
		var body_Tbody = $('	<tbody class="text-center" id="center_data">'+
							'	</tbody>');
		for(var i = 0 ; i<this.orders.length;i++){
			var order_data = $('	<tr>'+
							  '		<td>'+
							  				this.orders[i].id+
							  '		</td>'+
							  '		<td>'+
							  				this.orders[i].order_name+
							  '		</td>'+
							  '	</tr>');
			var orderManager = $('<td></td>');
			var orderManager_Update = $('<button type="button" class="btn btn-warning btn-sm btn_update" data-toggle="modal" data-target="#update_order">修改</button>');
			var orderManager_Delete = $('<button type="button" class="btn btn-danger btn-sm btn_delete" data-toggle="modal" data-target="#delete_order">删除</button>'); 
			
			orderManager_Update.data('index', i);
			orderManager_Delete.data('index', i);
			
			orderManager_Update.on("click",function(){
//				alert("更新数据");
				
				var index = $(this).data('index');
				order=model.orders[index];	
				model.currentOrder=order;
				model.currentOrderIndex=index;
				
				$(".alerts_contents").load("/alerts/alert_update_order.jsp", function() {
					
					$("#order_name").val(model.currentOrder.order_name);
					$("#update_order").modal('show');
					
				});
			});
			orderManager_Delete.on("click",function(){
				
				var index = $(this).data('index');
				order=model.orders[index];	
				model.currentOrder=order;
				model.currentOrderIndex=index;
				
				$(".alerts_contents").load("/alerts/alert_delete_order.jsp", function() {
					$("#order_name").val(model.currentOrder.order_name);
					$("#delete_order").modal('show');
				});
			});
			
			orderManager.append(orderManager_Update).append(" &nbsp;&nbsp;&nbsp; ").append(orderManager_Delete);
			order_data.append(orderManager);
			
			body_Tbody.append(order_data);
			
			if(model.currentOrder && i == model.currentOrderIndex){
				order_data.addClass("info");
			}
			
		}
		
		var Tbody_data_end = $('<tr>'+
								'	<td>'+
								'	</td>'+
								'	<td>'+
								'	</td>'+
								'</tr>');
		var Tbody_data_end_Manager = $('<td></td>');
		var Tbody_data_end_ManagerAdd = $('<button type="button" class="btn btn-info btn-sm btn_create" data-toggle="modal" data-target="#create_order">新增</button>');
		
		Tbody_data_end_ManagerAdd.on("click",function(){
			$(".alerts_contents").load("/alerts/alert_create_order.jsp", function() {
				$("#create_order").modal('show');
			});
		});
		
		Tbody_data_end_Manager.append(Tbody_data_end_ManagerAdd);
		Tbody_data_end.prepend(Tbody_data_end_Manager);
		
		body_Tbody.append(Tbody_data_end);
		body_table.append(body_Tbody);
		panel_body.append(body_table);
		
		var panel_foot = $('<div class="panel-footer">'+
							'	<span class="label label-primary">以上即为 此次查询得到的所有数据</span>'+
							'</div>');
		
		show_data.append(panel_head).append(panel_body).append(panel_foot);

}

model.updateOrder = function(order){
	model.currentOrder = order;
	model.orders[model.currentOrderIndex] = order;
}

model.CurrentAccount ={};
model.updateCurrentAccountAndElements = function(Account){
	model.CurrentAccount = Account;
	model.updateCurrentAccountElements();
}
model.updateCurrentAccountAll = function(Account){
	model.CurrentAccount = Account;
	model.updateCurrentAccountElements();
	model.updateCurrentAccountInfoView();
}
model.updateCurrentAccountElements = function(){
	$("#cur_userId").val(model.CurrentAccount.id);
	$("#cur_username").val(model.CurrentAccount.username);
}

model.updateCurrentAccountInfoView = function(){
	var show_data = $("#show_data").empty();
	$(".alerts_contents").empty();
	
	var panel_head = $('<div class="panel-heading">'+
						'	<h3 class="panel-title text-center">'+
						'		账号信息'+
						'	</h3>'+
						'</div>');
	var head_back = $('	<button class="btn btn-info btn-sm pull-left">'+
						'		 返回 '+
						'	</button>');
	head_back.on("click",function(){
		$(".navbar").show();
		$(".home").click();
	});
	panel_head.prepend(head_back);
	
	var panel_body = $('<div class="panel-body"></div>');
	var body_accountInfo = $('<p class="text-center">用户名: '+this.CurrentAccount.username+'</p>');
	var accountInfoUpdateBtn = $('<button type="button" class="btn btn-link btn-sm">修改</button>');
	
	accountInfoUpdateBtn.on("click",function(){
		$(".alerts_contents").load("/alerts/alert_update_account.jsp", function() {
			$("#update_account").modal('show');
		});
	});
	
	panel_body.append(body_accountInfo.append(accountInfoUpdateBtn));
	
	var panel_foot = $('<div class="panel-footer">'+
						'	<span class="label label-primary">以上即为 此次查询得到的当前账号信息</span>'+
						'</div>');
	var exitbtn = $('<div class="row data userInfo" id="userInfo">'+
					'	<button class="btn-lg btn-block btn-danger navbar-fixed-bottom" type="button">退出</button>'+
					'</div>');
	exitbtn.on("click",function(){
		$(".alerts_contents").load("/alerts/alert_exit.jsp", function() {
			$("#exit_account").modal('show');
		});
	});
	show_data.append(panel_head).append(panel_body).append(panel_foot).append(exitbtn);
}
