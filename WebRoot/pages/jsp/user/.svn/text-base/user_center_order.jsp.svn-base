<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的订单</title>

<link rel="stylesheet" href="../../css/user/user_center.css"
	type="text/css"></link>
<link rel="stylesheet" href="../../css/user/global_affect.css"
	type="text/css"></link>
<link rel="stylesheet" href="../../css/user/order.css" type="text/css"></link>
<script type="text/javascript" src="../../js/user/jquery-2.0.1.js"></script>
<script type="text/javascript">
	var textHTML;
	function getorders() {
		$
				.ajax({
					url : '../../../getorders',
					type : 'post',
					dataType : 'json',
					success : function(data) {
						var text;
						var count = 0;
						text = '<table class="table table-hover"><tr class="tr_head"><th width="100px"><span>订单编号</span></th><th><span>金额</span></th><th><span>交易状态</span></th><th><span>操作</span></th><th><span>详情</span></th></tr>';
						for ( var i = 0; i < data['size']; i++) {
							var datatext = data[i]['state'];
							var state0 = "0";
							var state1 = "1";
							var state2 = "2";
							var state3 = "3";
							var state4 = "4";
							if (datatext != state4) {
								count++;
								text += "<tr><td><span>" + data[i]['orderid']
										+ "</span></td><td><span>&yen;"
										+ data[i]['totalprice']
										+ "</span></td><td><span>";

								if (datatext == state0) {
									text += "未发货</span></td><td><span><button "
											+ 'id="button' + data[i]['orderid']
											+ '"' + 'value="'
											+ data[i]['orderid'] + '"'
											+ 'onclick="cancel(this)"'
											+ ">取消订单" + "</button></span></td>";
								}
								if (datatext == state1) {
									text += "已发货</span></td><td><span><button "
											+ 'id="button' + data[i]['orderid']
											+ '"' + 'value="'
											+ data[i]['orderid'] + '"'
											+ 'onclick="receive(this)"'
											+ ">确认收货" + "</button></span></td>";
								}
								if (datatext == state2) {
									text += "订单已取消</span></td><td><span><button "
											+ 'id="button'
											+ data[i]['orderid']
											+ '"'
											+ 'value="'
											+ data[i]['orderid']
											+ '"'
											+ 'onclick="todelete(this)"'
											+ ">删除订单" + "</button></span></td>";
								}
								if (datatext == state3) {
									text += "交易成功</span></td><td><span><button "
											+ 'id="button'
											+ data[i]['orderid']
											+ '"'
											+ 'value="'
											+ data[i]['orderid']
											+ '"'
											+ 'onclick="todelete(this)"'
											+ ">删除订单" + "</button></span></td>";
								}

								text += "<td><span><a href=\"user_center_order_det.jsp?orderid="
										+ data[i]['orderid']
										+ " \">详情</a></span></td></tr>";
							}
						}
						if (count == 0) {
							text += '<tr><td></td><td></td><td><span>无记录</span></td><td></td><td></td></tr>';
						}
						text += "</table>";
						document.getElementById("item_list").innerHTML = text;
					}
				});
	}
	function cancel(obj) {
		var i = obj.value;
		var userParam = {
			"orderid" : $("#button" + i).val()
		};
		$.ajax({
			url : '../../../changeordercancel',
			type : 'post',
			data : userParam,
			dataType : 'json',
			success : function(data) {
				location.reload();
			}
		});
	}
	function receive(obj) {
		var i = obj.value;
		var userParam = {
			"orderid" : $("#button" + i).val()
		};
		$.ajax({
			url : '../../../changeorderreceive',
			type : 'post',
			data : userParam,
			dataType : 'json',
			success : function(data) {
				location.reload();
			}
		});
	}
	function todelete(obj) {
		var i = obj.value;
		var userParam = {
			"orderid" : $("#button" + i).val()
		};
		$.ajax({
			url : '../../../changeorderdelete',
			type : 'post',
			data : userParam,
			dataType : 'json',
			success : function(data) {
				location.reload();
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
		getorders();
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
							>> <a href="user_center_order.jsp">我的订单</a>
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
						<!-- <jsp:include page="order.jsp"></jsp:include> -->
						<div class="order_main">
							<div class="order_top">
								<img src="../../source/image/add.jpg"></img>
							</div>
							<div class="hello">
								<span>我的订单</span>
							</div>
							<div id="item_list" class="order_pane"></div>
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
