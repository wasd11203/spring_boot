<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<script src="/js/jquery-1.11.3.min.js"></script>
		<script src="/js/common.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/model.js"></script>
		<script src="/js/bs_index.js"></script>
	</head>

	<body>
		
		<div class="container">

			<input type="hidden" id="cur_userId" value="${user.id }" />
			<input type="hidden" id="cur_username" value="${user.username }" />

			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="page-header">
						<h1 class="text-center">
							后台管理
						</h1>
					</div>
				</div>
			</div>
			<div class="row clearfix">
				<div class="col-md-12 column ">

					<div class="alerts_contents" id="alerts">

					</div>

					<div class="panel panel-info show_data" id="show_data">

					</div>

				</div>
			</div>

			<div class="row clearfix">
				<div class="col-md-12 column">
					<nav class="navbar navbar-default navbar-fixed-bottom navbar-inverse" role="navigation">

						<div class="navbar-header">
							<button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="info">导航</span>
							</button>

						</div>

						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-navlist text-center">
								<li class="col-md-3 column home active" value="default_data">
									<a href="javascript:void(0);">首页</a>
								</li>
								<li class="col-md-3 column user" value="account_manager">
									<a href="javascript:void(0);">用户管理</a>
								</li>
								<li class="col-md-3 column order" value="order_manager">
									<a href="javascript:void(0);">订单管理</a>
								</li>
								<li class="col-md-3 column accountInfo" value="userInfo">
									<a href="javascript:void(0);">个人信息</a>
								</li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>

	</body>

</html>