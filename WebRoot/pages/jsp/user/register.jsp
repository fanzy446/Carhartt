<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新用户注册</title>
<link rel="stylesheet" type="text/css" href="../../css/user/mystyle.css" />
<link rel="stylesheet" type="text/css" href="../../css/user/header.css" />

<script type="text/javascript" src="../../js/user/jquery.js"></script>
<script type="text/javascript" src="../../js/user/inputmail.js"></script>
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

	function register() {
		var judge = true;
		if ($("#loginEmail").val() == "") {
			$("h4").eq(0).css("color", "#F33");
			$("h4").eq(0).html("注册邮箱不能为空");
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
		if ($("#registerPassWord").val() != $("#registerPassWordRepeat").val()) {
			$("h4").eq(2).css("color", "#F33");
			$("h4").eq(2).html("两次输入的密码不一致");
			judge = false;
		}
		if ($("#userName").val() == "") {
			$("h4").eq(3).css("color", "#F33");
			$("h4").eq(3).html("用户昵称不能为空");
			judge = false;
		}
		if (!document.getElementById("readConventionSelect").checked) {
			$("h4").eq(4).css("color", "#F33");
			$("h4").eq(4).html("请先阅读条款并勾选同意");
			judge = false;

		}
		if (judge == true) {
			var userParam = {
				"user.userEmail" : $("#loginEmail").val(),
				"user.userPassword" : $("#registerPassWord").val(),
				"user.userName" : $("#userName").val()
			};

			$.ajax({
				url : '../../../UserBasic/register',
				type : 'post',
				data : userParam,
				dataType : 'json',
				success : function(data) {
					if (data['code'] == 0) {
						var email = $("#loginEmail").val();
						var first = email.lastIndexOf("@");
						var last = email.length;
						alert("验证邮件已发送，请注意查收");
						var href = "http://mail." + email.substring(first + 1, last);
						window.location.href = href;
					} else if (data['code'] == '-1') {
						alert("邮件发送失败");
					} else if (data['code'] == '-2') {
						alert("邮箱已被注册");
					}
				},
				error : function(){
				alert("haha");
				}
			});
		}
		return judge;

	}

	$(function() {
		$("#loginEmail").changeTips({
			divTip : ".on_changes"
		});
	});

	$(document).ready(
			function() {
				$("#loginEmail").blur(function() {
					if ($("#loginEmail").val() == "") {
						$("h4").eq(0).css("color", "#F33");
						$("h4").eq(0).html("注册邮箱不能为空");
					}
				});
				$("#loginEmail").focus(function() {
					$("h4").eq(0).css("color", "#B4B4B4");
					$("h4").eq(0).html("请输入邮箱作为注册账户");
				});
				$("#registerPassWord").blur(function() {
					if ($("#registerPassWord").val() == "") {
						$("h4").eq(1).css("color", "#F33");
						$("h4").eq(1).html("密码不能为空");
					} else if ($("#registerPassWord").val().length < 6) {
						$("h4").eq(1).css("color", "#F33");
						$("h4").eq(1).html("密码不能小于六位");
					} else if ($("#registerPassWord").val().length >= 6) {
						$("h4").eq(1).css("color", "#B4B4B4");
						$("h4").eq(1).html("请输入密码");
						//document.getElementById("registerPassWord").focus();
					}
				});
				$("#registerPassWord").focus(function() {
					$("h4").eq(1).css("color", "#B4B4B4");
					$("h4").eq(1).html("请输入密码");
					//document.getElementById("registerPassWord").focus();
				});
				$("#registerPassWordRepeat").blur(
						function() {
							if ($("#registerPassWord").val() != $("#registerPassWordRepeat").val()) {
								$("h4").eq(2).css("color", "#F33");
								$("h4").eq(2).html("两次输入的密码不一致");
								//document.getElementById("registerPassWordRepeat").focus();
							} else {
								$("h4").eq(2).css("color", "#B4B4B4");
								$("h4").eq(2).html("请再次输入密码");
							}
						});
				$("#registerPassWordRepeat").focus(function() {
					$("h4").eq(2).css("color", "#B4B4B4");
					$("h4").eq(2).html("请再次输入密码");
				});

				$("#userName").blur(function() {
					if ($("#userName").val() == "") {
						$("h4").eq(3).css("color", "#F33");
						$("h4").eq(3).html("用户昵称不能为空");
						//document.getElementById("userName").focus();
					} else {
						$("h4").eq(3).css("color", "#B4B4B4");
						$("h4").eq(3).html("请输入要使用的用户昵称，不可以使用特殊字符");
					}
				});

				$("#userName").focus(function() {
					$("h4").eq(3).css("color", "#B4B4B4");
					$("h4").eq(3).html("请输入要使用的用户昵称，不可以使用特殊字符");
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

					<a href="login.jsp" class="register_loginButton">直接登录</a>
					<h1>已经拥有账号？</h1>
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
						<h1>注册新用户</h1>
						<h2>Registration</h2>
					</div>
					<div>
						<form id="registerForm">
							<!-- action="/Carhartt/UserBasic/register"
							onsubmit="return submitRegisterFromCheak(this);" -->

							<div class="register_inputline" style="margin-top:30px;">
								<h3>邮箱</h3>
								<input type="text" name="user.userEmail" id="loginEmail"
									autocomplete="off" />
								<ul class="on_changes">
									<li email="">请选择邮箱格式</li>
									<li email="@sina.com"></li>
									<li email="@163.com"></li>
									<li email="@qq.com"></li>
									<li email="@hotmail.com"></li>
									<li email="@126.com"></li>
									<li email="@gmail.com"></li>
									<li email="@yahoo.com"></li>
								</ul>
								<h4>请输入邮箱作为注册账户</h4>
							</div>
							<div class="register_inputline">
								<h3>密码</h3>
								<input type="password" name="user.userPassword"
									id="registerPassWord" autocomplete="off" />
								<h4>请输入用户密码，区分大小写</h4>
							</div>
							<div class="register_inputline">
								<h3>重复密码</h3>
								<input type="password" name="passwordRepeat"
									id="registerPassWordRepeat" autocomplete="off" />
								<h4>请再次输入密码</h4>
							</div>
							<div class="register_inputline">
								<h3>昵称</h3>
								<input type="text" name="user.userName" autocomplete="off"
									id="userName" />
								<h4>请输入要使用的用户昵称，不可以使用特殊字符</h4>
							</div>

							<div class="register_inputline">
								<input type="checkbox" class="selectionItem"
									name="readTheConvention" id="readConventionSelect" value="yes" />
								<h5>
									我已阅读并同意<a href="####################">《Carhartt注册条款》</a>
								</h5>
								<h4></h4>
								<input type="button" value="确认注册" class="submitButton"
									onclick="register();" style="cursor:pointer;"/>
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
