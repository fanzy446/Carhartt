<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>个人资料</title>

<link rel="stylesheet" href="../../css/user/user_center.css"
	type="text/css"></link>
<link rel="stylesheet" href="../../css/user/global_affect.css"
	type="text/css"></link>
<link rel="stylesheet" href="../../css/user/personal_info.css"
	type="text/css"></link>
<script type="text/javascript" src="../../js/user/checkidcardnum.js"></script>
<script type="text/javascript" src="../../js/user/jquery-2.0.1.js"></script>
<script type="text/javascript">
	function edit() {
		var n = document.getElementById('nickName');
		var r = document.getElementById('realname');
		var s = document.getElementById('mysex');
		var p = document.getElementById('phone');
		var i = document.getElementById('idcard');
		var h = document.getElementById('hint');
		var a = document.getElementById('answer');
		var btn = document.getElementById('btn');
		r.disabled = false;
		r.className = 'after_edit';
		n.disabled = false;
		n.className = 'after_edit';
		p.disabled = false;
		p.className = 'after_edit';
		i.disabled = false;
		i.className = 'after_edit';
		h.disabled = false;
		h.className = 'after_edit';
		a.disabled = false;
		a.className = 'after_edit';
		s.innerHTML = '<input type="radio" name="sex" id="man" onclick="manchange()" val="1" checked/>男&nbsp;&nbsp;<input type="radio" name="sex" id="woman" onclick="womanchange()" val="0"/>女';
		btn.style.display = 'block';
	}

	function save() {
		var canSubmit = true;
		if ($("#phone").val().length != 11 && $("#phone").val().length != 0) {
			alert("请正确填写手机号码！");
			canSubmit = false;
		} else if (!$("#idcard").val() == "") {

			if (!checkCardId($("#idcard").val())) {
				canSubmit = false;
			}
		}

		if (canSubmit == true) {
			alert("提交成功");
			var btn = document.getElementById('btn');
			btn.style.display = 'none';

			var userParam = {
				"userName" : $("#nickName").val(),
				"realname" : $("#realname").val(),
				"idCard" : $("#idcard").val(),
				"userPhone" : $("#phone").val(),
				"passwhint" : $("#hint").val(),
				"passanswer" : $("#answer").val(),
				"man" : $("#man").val(),
				"woman" : $("#woman").val()
			};
			$.ajax({
				url : '../../../changepersonalinfo',
				type : 'post',
				data : userParam,
				dataType : 'json',
				success : function(data) {
					location.reload();
				}
			});

		} else {
			alert("提交失败");
		}
	}
	function getperinfo() {
		$.ajax({
			url : '../../../getpersonalinfo',
			type : 'post',
			dataType : 'json',
			success : function(data) {
				document.getElementById("nickName").value = data['name'];
				document.getElementById("realname").value = data['realname'];
				document.getElementById("phone").value = data['phone'];
				document.getElementById("idcard").value = data['idcard'];
				document.getElementById("credits").value = data['credits']
						+ " (即将开放，敬请期待！)";
				document.getElementById("hint").value = data['passwhint'];
				document.getElementById("answer").value = data['passanswer'];
				document.getElementById("email").value = data['useremail'];

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
				if (data['gender'] == "男") {
					document.getElementById("mysex").innerHTML = "男";
				} else {
					document.getElementById("mysex").innerHTML = "女";
				}
			}
		});
	}
	function manchange() {
		document.getElementById("man").value = "1";
		document.getElementById("woman").value = "0";
	}
	function womanchange() {
		document.getElementById("man").value = "0";
		document.getElementById("woman").value = "1";
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
					}
				});
	}
	window.onload = function() {
		getperinfo();
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
							>> <a href="user_center_per_info.jsp">个人资料</a>
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
							<li><a href="user_center_order.jsp"><span>我的订单</span> </a></li>
							<li><a href="user_center_fav.jsp"><span>我的收藏</span> </a></li>
							<li><a href="user_center_per_info.jsp"><span>个人资料</span>
							</a></li>
							<li><a href="user_center_address.jsp"><span>收货信息</span>
							</a></li>
							<li><a href="user_center_pass.jsp"><span>密码修改</span> </a></li>
							<li><a href="user_center_feedback.jsp"><span>在线反馈</span>
							</a></li>
						</ul>
					</div>
					<div class="middle_pane">
						<!-- 待添加 -->
						<!-- <jsp:include page="personal_info.jsp"></jsp:include> -->
						<div class="per_main">
							<div class="per_top">
								<img src="../../source/image/add.jpg"></img>
							</div>

							<div class="hello">
								<span>您好，尊敬的Carhartt网会员，欢迎来到Carhartt网！</span> <a
									onclick="edit();manchange();" style="cursor:pointer;">修改我的资料</a>
							</div>
							<div class="info_pane">
								<table>
									<tr>
										<td width="100px"><span>我的昵称:</span>
										</td>
										<td><input id="nickName" class="before_edit" type="text"
											disabled="disabled"></input>
										</td>
									</tr>
									<tr>
										<td><span>真实姓名:</span>
										</td>
										<td><input id="realname" class="before_edit" type="text"
											disabled="disabled" /></td>
									</tr>
									<tr>
										<td><span>我的性别:</span>
										</td>
										<td id="mysex"></td>
									</tr>
									<tr>
										<td><span>我的积分:</span>
										</td>
										<td><input id="credits" class="before_edit" type="text"
											disabled="disabled"></input></td>
									</tr>
									<tr>
										<td><span>手机号码:</span>
										</td>
										<td><input id="phone" class="before_edit" type="text"
											disabled="disabled"
											onkeyup="this.value=this.value.replace(/\D/g,'')"
											onafterpaste="this.value=this.value.replace(/\D/g,'')"></input>
										</td>
									</tr>
									<tr>
										<td><span>登录邮箱:</span>
										</td>
										<td><input id="email" class="before_edit" type="text"
											disabled="disabled" /></td>
									</tr>
									<tr>
										<td><span>身份证号:</span>
										</td>
										<td><input id="idcard" class="before_edit" type="text"
											disabled="disabled" />
										</td>
									</tr>
								</table>
							</div>
							<div class="pass_info">
								<table>
									<tr>
										<td width="100px"><span>密码提示:</span>
										</td>
										<td><input id="hint" class="before_edit" type="text"
											disabled="disabled"></input>
										</td>
									</tr>
									<tr>
										<td><span>密码回答:</span>
										</td>
										<td><input id="answer" class="before_edit" type="text"
											disabled="disabled"></input>
										</td>
									</tr>
								</table>
							</div>
							<div id="btn" class="handin">
								<input class="save" type="submit" value="保存修改" onclick="save()" />
							</div>
							<div class="ok"></div>
							<div class="order_ad">
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
									<li><a href="">哪些地区支持货到付款？</a></li>
									<li><a href="">优惠券怎样使用？</a></li>
									<li><a href="">易迅商品体验评论送多少积分？</a></li>
									<li><a href="">在易迅开具发票要注意什么？</a></li>
									<li><a href="">怎样申请退换货？</a></li>
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
									<li><a href="">微信扫描二维码，劲优惠触手可得</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td><jsp:include page="footer.jsp"></jsp:include></td>
		</tr>
	</table>
</body>
</html>
