<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="../../css/user/header.css" />
<style type="text/css" media="screen">
@import
url(
<c:url
value=
"../../css/user/mystyle.css"
/>
);
</style>
<!-- hai  -->
<script type="text/javascript" src="../../js/user/jquery.js"></script>
<style>
</style>
<script>
	document.onkeydown = function(event) {
		var target, code, tag;
		if (!event) {
			event = window.event; //针对ie浏览器  
			target = event.srcElement;
			code = event.keyCode;
			if (code == 13) {
				tag = target.tagName;
				if (tag == "TEXTAREA") {
					return true;
				} else {
					return false;
				}
			}
		} else {
			target = event.target; //针对遵循w3c标准的浏览器，如Firefox  
			code = event.keyCode;
			if (code == 13) {
				tag = target.tagName;
				if (tag == "INPUT") {
					return false;
				} else {
					return true;
				}
			}
		}
	};

	function loginSub() {
		var judge = true;
		//$("#loginEmail").val("1111");
		//alert($("#loginEmail").val());
		if ($("#loginEmail").val() == "") {
			//alert("hhhhhhhhhh");
			$("h4").eq(0).css("color", "#F33");
			$("h4").eq(0).html("登陆邮箱不能为空");
			judge = false;
		}

		if ($("#registerPassWord").val().length < 6) {
			$("h4").eq(1).css("color", "#F33");
			$("h4").eq(1).html("密码不能小于六位");
			judge = false;
		}
		if ($("#registerPassWord").val() == "") {
			$("h4").eq(1).css("color", "#F33");
			$("h4").eq(1).html("密码不能为空");
			judge = false;
		}
		var email = $("#loginEmail").val();
		//alert(email);
		if (email != "") {
			var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
			var isok = reg.test(email);
			if (!isok) {
				$("h4").eq(0).css("color", "#F33");
				$("h4").eq(0).html("邮箱格式不正确");
				judge = false;
			}
		}
		if (judge == true) {
			var userParam = {
				"user.userEmail" : $("#loginEmail").val(),
				"user.userPassword" : $("#registerPassWord").val()
			};
			$.ajax({
				url : '../../../UserBasic/userLogin',
				type : 'post',
				data : userParam,
				dataType : 'json',
				success : function(data) {
					if (data['code'] > 0) {
						window.location.href = "index.jsp";
					} else if (data['code'] == '-1') {
						alert("用户不存在");
					} else if (data['code'] == '-2') {
						alert("邮箱或密码错误");
					} else if (data['code'] == '-3') {
						alert("用户未激活 ");
					} else if (data['code'] == '-4') {
						alert("用户被冻结");
					}
				}
			});
		}
	}

	$(document)
			.ready(
					function() {
						$("#loginEmail")
								.blur(
										function() {
											if ($("#loginEmail").val() == "") {
												$("h4").eq(0).css("color",
														"#F33");
												$("h4").eq(0).html("登陆邮箱不能为空");
											}
											var email = $("#loginEmail").val();
											//alert(email);
											if (email != "") {
												var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
												var isok = reg.test(email);
												if (!isok) {
													$("h4").eq(0).css("color",
															"#F33");
													$("h4").eq(0).html(
															"邮箱格式不正确");
												} else {
													$("h4").eq(0).css("color",
															"#B4B4B4");
													$("h4").eq(0).html(
															"请输入你的注册邮箱");
												}
											}
										});
						$("#loginEmail").focus(function() {

							$("h4").eq(0).css("color", "#B4B4B4");
							$("h4").eq(0).html("请输入你的注册邮箱");

						});

						$("#registerPassWord")
								.blur(
										function() {
											if ($("#registerPassWord").val() == "") {
												$("h4").eq(1).css("color",
														"#F33");
												$("h4").eq(1).html("密码不能为空");
											} else if ($("#registerPassWord")
													.val().length < 6) {
												$("h4").eq(1).css("color",
														"#F33");
												$("h4").eq(1).html("密码不能小于六位");
											} else if ($("#registerPassWord")
													.val().length >= 6) {
												$("h4").eq(1).css("color",
														"#B4B4B4");
												$("h4").eq(1).html("请输入密码");
												//document.getElementById("registerPassWord").focus();
											}
										});

						$("#registerPassWord").focus(function() {

							$("h4").eq(1).css("color", "#B4B4B4");
							$("h4").eq(1).html("请输入密码");
							//document.getElementById("registerPassWord").focus();

						});

					});
</script>

</head>

<body
	style="background:url(../../source/image/register_backGround.jpg);">

	<center>
		<div class="register-header">
			<div class="register-inheader">
				<div class="register-headerContent">
					<!--LOG块-->
					<div class="logo" style="margin-left:0; margin-top:5px;">
						<img src="../../source/image/logo.png" />
					</div>

					<a href="register.jsp" class="register_loginButton">加入Carhartt</a>
					<h1>还没有账号？</h1>
					<a href="index.jsp" class="returnBackToIndex_register">&lt;&lt;返回首页</a>

				</div>
			</div>


		</div>
		<div class="mainContent"
			style="background:url(../../source/image/register_backGround.jpg);">
			<div class="Content960" style="height:600px; margin-top:100px;"
				style="background:url(../../source/image/register_backGround.jpg);">
				<div class="register_Block">
					<div class="heading">
						<h1>用户登录</h1>
					</div>
					<div>
						<form id="loginForm" method="post">
							<div class="register_inputline login_inputLine"
								style="margin-top:50px;">
								<h3>登录邮箱</h3>
								<input type="text" name="user.userEmail" id="loginEmail"
									autocomplete="off" />
								<ul class="on_changes" style="top:135px">
									<li email="">请选择邮箱格式</li>
									<li email="@sina.com"></li>
									<li email="@163.com"></li>
									<li email="@qq.com"></li>
									<li email="@hotmail.com"></li>
									<li email="@126.com"></li>
									<li email="@gmail.com"></li>
									<li email="@yahoo.com"></li>
								</ul>
								<h4>请输入你的注册邮箱</h4>
							</div>
							<div class="register_inputline login_inputLine">
								<h3>密码</h3>
								<input type="password" name="user.userPassword"
									id="registerPassWord" autocomplete="off" />
								<h4>请输入用户密码，区分大小写</h4>
							</div>
							<div class="register_inputline login_inputLine">
								<input type="button" value="登录" class="submitButton"
									onclick="loginSub();"  style="cursor:pointer;"/>
							</div>
						</form>
					</div>
				</div>


			</div>
			<!--960ContentEnd-->

			<jsp:include page="footer.jsp"></jsp:include>
		</div>
		<!--mainContentEnd-->


	</center>
</body>
</html>

