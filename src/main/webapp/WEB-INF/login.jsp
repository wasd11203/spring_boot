<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="keywords" content="关键字,关键字" />
		<meta name="description" content="描述信息" />
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
		<title> 登录页 </title>

		<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<script src="/js/jquery-1.11.3.min.js"></script>
		<script src="/js/common.js"></script>
		<script src="./js/bootstrap.min.js"></script>
		<script src="/js/login.js"></script>
		
	</head>

	<body>

		<div class="container">

			<!-- h1 START -->
			<h1 class=" page-header ">使用账号登录</h1>
			<!-- END h1 -->
			<!-- form START -->
			<div class="form-horizontal">
				<!-- 登录框中的 username 框  START-->
				<div class="form-group">
					<div class="col-md-5">
						<label>用户名</label>
						<input class="form-control" id="username" type="text" name="username" placeholder="请填写登录账号 " maxlength=10/>
					</div>
				</div>
				<!-- END 登录框中 username 框  -->

				<!-- 登录框中 password 框 START -->
				<div class="form-group">
					<div class="col-md-5">
						<label>密码</label>
						<input class="form-control" id="pwd" type="password" name="password" placeholder="请填写登录密码 " maxlength=10/>
					</div>
				</div>
				<!-- END 登录框中 password 框  -->

				<!-- 登录框中 登录按钮  START-->
				<div class="form-group">
					<div class="col-md-5">
                    	<button class="btn btn-primary disabled" id="subbtn"> 登 录</button>
					</div>
				</div>
				<!-- END 登录框中 登录按钮  -->
			</div>
			<!-- END form -->
		</div>
	</body>

</html>