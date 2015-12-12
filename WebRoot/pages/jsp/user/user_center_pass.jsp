<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>密码修改</title>

<link rel="stylesheet" href="../../css/user/user_center.css"
	type="text/css"></link>
<link rel="stylesheet" href="../../css/user/global_affect.css"
	type="text/css"></link>
<link rel="stylesheet" href="../../css/user/password.css"
	type="text/css"></link>
<script type="text/javascript" src="../../js/user/jquery.js"></script>
<script type="text/javascript" src="../../js/user/pass.js"></script>
<script type="text/javascript" src="../../js/user/jquery-2.0.1.js"></script>
<script type="text/javascript">
	function toSavePassword() {
		var userParam = {
			"oldpassword" : $("#input_old_psw").val(),
			"newpassword" : $("#input_new_psw_confirm").val(),
			"value" : $("#input_vali_code").val(),
		};
		$.ajax({
			url : '../../../changepassword',
			type : 'post',
			data : userParam,
			dataType : 'json',
			success : function(data) {
				var state1 = "1";
				var state2 = "2";
				var state0 = "0";
				var returnstate = data['statement'];
				if (returnstate == state1) {
					alert("修改成功！");
					location.reload();
				}
				if (returnstate == state2) {
					alert("验证码错误！");
				}
				if (returnstate == state0) {
					alert("旧密码错误！");
				}
			}
		});
	}
	function send() {
		$.ajax({
			url : '../../../sendnumber',
			type : 'post',
			dataType : 'json',
			success : function(data) {
				alert("验证码已发送，请登录您的安全邮箱查收！");
			}
		});
	}
	function getboardinfo() {
		$
				.ajax({
					url : '../../../getannouncement',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						document.getElementById("board1").innerHTML = "<a onclick=\"alert('"
								+ data[0]['Content']
								+ "')\">"
								+ data[0]['Title'] + "</a>";
						document.getElementById("board2").innerHTML = "<a onclick=\"alert('"
								+ data[1]['Content']
								+ "')\">"
								+ data[1]['Title'] + "</a>";
						document.getElementById("board3").innerHTML = "<a onclick=\"alert('"
								+ data[2]['Content']
								+ "')\">"
								+ data[2]['Title'] + "</a>";
						var rank;
						if (data['credits'] < 10) {
							rank = "初出茅庐";
						} else if (data['credits'] < 30) {
							rank = "小有所成";
						} else if (data['credits'] < 70) {
							rank = "游刃有余";
						} else {
							rank = "大显身手";
						}
						document.getElementById("rank").innerHTML = rank;
					}
				});
	}
	window.onload = function() {
		getboardinfo();
	};
</script>
</head>

<body>
	<table width="100%" cellspacing="0">
		<tr>
			<td><jsp:include page="header.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td>
				<div class="main_pane">
					<div class="nav">
						<div style="margin-top: 5px; float: left;">
							<a href="index.jsp">首页</a> >> <a href="user_center_per_info.jsp">个人中心</a>
							>> <a href="user_center_pass.jsp">密码修改</a>
						</div>
					</div>
					<div class="left_pane">
						<div class="head">
							<div class="head_in">
								<img src="../../source/image/head.jpg"></img>
								<p id="rank"></p>
							</div>
						</div>

						<ul id="lul" class="faceul">
							<li><a href="user_center_order.jsp"><span>我的订单</span> </a>
							</li>
							<li><a href="user_center_fav.jsp"><span>我的收藏</span> </a>
							</li>
							<li><a href="user_center_per_info.jsp"><span>个人资料</span>
							</a>
							</li>
							<li><a href="user_center_address.jsp"><span>收货信息</span>
							</a>
							</li>
							<li><a href="user_center_pass.jsp"><span>密码修改</span> </a>
							</li>
							<li><a href="user_center_feedback.jsp"><span>在线反馈</span>
							</a>
							</li>
						</ul>
					</div>
					<div class="middle_pane">
						<!-- 待添加 -->
						<!-- <jsp:include page="password.jsp"></jsp:include> -->
						<div class="pass_main">
							<div class="pass_top">
								<img src="../../source/image/add.jpg"></img>
							</div>
							<div class="pass_pane">
								<div class="hello">
									<span>修改密码</span>
								</div>
								<div class="pass_tb">

									<form action="" method="post" enctype="multipart/form-data"
										name="form1">
										<table cellspacing="0">
											<tr>
												<td width="140px"><span><strong
														style="color: orange;font-size: 25px;">*</strong>&nbsp;请输入原密码:</span>
												</td>
												<td><input id="input_old_psw" class="new_input"
													type="password" name="passwordold" />&nbsp;
													<h5 style="display: inline;" id="oldpasstip"></h5></td>
											</tr>
											<tr>
												<td><span><strong
														style="color: orange;font-size: 25px;">*</strong>&nbsp;请输入新密码:</span>
												</td>
												<td><input id="input_new_psw" class="new_input"
													type="password" name="passwordnew" />&nbsp;
													<h5 style="display: inline;" id="newpasstip"></h5></td>
											</tr>
											<tr>
												<td><span><strong
														style="color: orange;font-size: 25px;">*</strong>&nbsp;请确认新密码:</span>
												</td>
												<td><input id="input_new_psw_confirm" class="new_input"
													type="password" name="passwordConfirm" />&nbsp;
													<h5 style="display: inline;" id="newpassconfirmtip"></h5>
												</td>
											</tr>
											<tr>
												<td><span><strong
														style="color: orange;font-size: 25px;">*</strong>&nbsp;邮箱安全认证:</span>
												</td>
												<td><input id="input_vali_code" class="new_input"
													type="text" name="vali_code" />&nbsp;<input type="button"
													class="btn" value="获取验证码" onclick="send()" />&nbsp;
													<h5 style="display: inline;" id="valicodetip"></h5>
												</td>
											</tr>
											<tr>
												<td></td>
												<td><input class="btn" type="button"
													onclick="toSavePassword()" value="保存密码" />&nbsp;&nbsp;<input
													class="btn" type="reset" value="重新设置" /></td>
											</tr>
										</table>
									</form>
								</div>
							</div>

							<div class="ok"></div>
							<div class="pass_ad">
								<img src="../../source/image/add2.jpg"></img>
							</div>
							<div class="ok"></div>
						</div>
					</div>
					<div class="right_pane">
						<div class="announcement">
							<div class="title">
								<span>商城公告</span><a href="">更多>></a>
							</div>
							<div class="content">
								<ul>
									<li><span id="board1"></span>
									</li>
									<li><span id="board2"></span>
									</li>
									<li><span id="board3"></span>
									</li>
								</ul>
							</div>
						</div>
						<div class="help">
							<div class="title">
								<span>用户帮助</span><a href="">更多>></a>
							</div>
							<div class="content">
								<ul>
									<li><a href="">哪些地区支持货到付款？</a>
									</li>
									<li><a href="">优惠券怎样使用？</a>
									</li>
									<li><a href="">易迅商品体验评论送多少积分？</a>
									</li>
									<li><a href="">在易迅开具发票要注意什么？</a>
									</li>
									<li><a href="">怎样申请退换货？</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="code2d">
							<div class="title">
								<span>微信商城</span><a href="">更多>></a>
							</div>
							<div class="content">
								<img class="content_img" src="../../source/image/my2dcode.jpg"></img>
								<ul>
									<li><a href="">微信扫描二维码，劲优惠触手可得</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div></td>
		</tr>
		<tr>
			<td><jsp:include page="footer.jsp"></jsp:include></td>
		</tr>
	</table>
</body>
</html>
