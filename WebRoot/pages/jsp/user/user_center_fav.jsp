<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的收藏</title>

<link rel="stylesheet" href="../../css/user/favourite.css"
	type="text/css"></link>
<link rel="stylesheet" href="../../css/user/global_affect.css"
	type="text/css"></link>
<link rel="stylesheet" href="../../css/user/user_center.css"
	type="text/css"></link>
<script type="text/javascript" src="../../js/user/jquery.js"></script>
<script type="text/javascript" src="../../js/user/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../../js/user/myjs.js"></script>

<script type="text/javascript">
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
							>> <a href="user_center_fav.jsp">我的收藏</a>
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
						<!-- <jsp:include page="favourite.jsp"></jsp:include> -->
						<div class="fav_main">

							<div class="fav_top_ad">
								<img src="../../source/image/add.jpg"></img>
							</div>

							<div class="hello">
								<span>我的收藏</span><a id='btnAddCartChose'>加入购物车</a>&nbsp;&nbsp; <a
									id='btnDeleteChose'>删除</a>&nbsp;&nbsp;<a id="all_select"
									onclick="de_select_all()">取消全选</a>&nbsp;&nbsp;<a
									id="all_select" onclick="select_all()">全选</a>
							</div>
							<ul id="item_list" class="fav_list">
							</ul>
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
