<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>收货信息</title>

<link rel="stylesheet" href="../../css/user/global_affect.css"
	type="text/css"></link>
<link rel="stylesheet" href="../../css/user/address.css" type="text/css"></link>
<link rel="stylesheet" href="../../css/user/user_center.css"
	type="text/css"></link>
<script type="text/javascript" src="../../js/user/myjs.js"></script>
<script type="text/javascript" src="../../js/user/jquery-2.0.1.js"></script>
<script type="text/javascript" src="../../js/user/jquery.js"></script>
<script type="text/javascript" src="../../js/user/address.js"></script>
<script type="text/javascript">
	function toSaveAddress() {
		// 判断能否上传数据
		var canSubmit = true;
		if ($("#recvName").val() == "" || $("#recvProvince").val() == ""
				|| $("#recvAddress").val() == "" || $("#recvZip").val() == ""
				|| $("#recvZip").val().length != 6
				|| $("#recvPhone").val() == ""
				|| $("#recvPhone").val().length != 11) {
			canSubmit = false;
			alert("请正确输入收货信息！");
		}

		if (canSubmit == true) {

			// 将新地址加到上面的table里

			var recvName = document.getElementById("recvName").value;
			var recvAddress = document.getElementById("recvAddress").value;
			var recvZip = document.getElementById("recvZip").value;
			var recvPhone = document.getElementById("recvPhone").value;
			var table = document.getElementById("recv_infor_array");

			var newTr = table.insertRow(0);
			var td0 = newTr.insertCell();

			td0.innerHTML = "<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<span><input type=\"radio\" name=\"addr\"checked=\"checked\" onclick=\"toOldAddress()\"/>&nbsp;"
					+ recvName
					+ "&nbsp;"
					+ recvAddress
					+ "&nbsp;"
					+ recvZip
					+ "&nbsp;" + recvPhone + "</span></td></tr>";

			// 将使用新地址框缩回去
			var oDiv = document.getElementById('new_address_block');
			oDiv.style.display = 'none';
			document.getElementById('norecord').style.display = 'none';
			// ajax保存新地址
			var userParam = {
				"consigneeName" : $("#recvName").val(),
				"consigneePhone" : $("#recvPhone").val(),
				"postcode" : $("#recvZip").val(),
				"fullAddress" : $("#recvAddress").val(),
			};
			$.ajax({
				url : '../../../addaddress',
				type : 'post',
				data : userParam,
				dataType : 'json',
				success : function(data) {
					var state = "1";
					var returnstate = data['statement'];
					if (returnstate == state) {
						alert("添加成功！");
					}
				}
			});
		} else {
			alert('提交失败');
		}
	}

	function getaddress() {
		$
				.ajax({
					url : '../../../getaddress',
					type : 'post',
					dataType : 'json',
					success : function(data) {

						var text;
						var count = 0;
						text = '<table class="table-hover" cellspacing="0" id="recv_infor_array">';
						if (data['size'] != 0) {

							for ( var i = 0; i < data['size']; i++) {
								if (data[i]['state'] == '1') {
									count++;
									text += '<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<span><input type="radio" name="addr" onclick="toOldAddress()"/>';
									text += '&nbsp;' + data[i]['ConsigneeName']
											+ '&nbsp;'
											+ data[i]['ConsigneeAddress']
											+ '&nbsp;'
											+ data[i]['ConsigneePostcode']
											+ '&nbsp;'
											+ data[i]['ConsigneePhone'];
									text += '</span></td></tr>';
								}
							}

							if (count == 0) {
								document.getElementById('norecord').style.display = 'block';
							}
						} else {
							document.getElementById('norecord').style.display = 'block';
						}
						text += '<tr><td class="new_td">&nbsp;&nbsp;&nbsp;&nbsp;<span><input id="new" type="radio" name="addr" onclick="toNewAddress()" />&nbsp;使用新地址</span></td></tr></table>';
						document.getElementById("address").innerHTML = text;

						if (count != 0) {
							$("input[name='addr']:eq(0)").attr("checked",
									'checked');
						}
					}
				});
	}

	function del() {
		var oradio = document.getElementsByName('addr');
		var otable = document.getElementById('recv_infor_array');
		var i = 0;
		var i2 = 0;
		for (i = 0; i < oradio.length - 1; i++) {
			if (oradio[i].checked) {
				i2 = $("#recv_infor_array").find("tr").length - 1 - i;
				otable.deleteRow(i);
				alert("删除成功！" + i2);
			}
		}
		if ($("#recv_infor_array").find("tr").length == 1) {
			document.getElementById('norecord').style.display = 'block';
		}

		var userParam = {
			"num" : i2
		};
		$.ajax({
			url : '../../../deladdress',
			type : 'post',
			data : userParam,
			dataType : 'json',
			success : function(data) {
			}
		});
	}
	function toalert(obj) {
		alert(obj);
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
		getaddress();
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
							>> <a href="user_center_address.jsp">收货信息</a>
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
						<div class="addr_main">
							<div class="addr_top">
								<img src="../../source/image/add.jpg"></img>
							</div>
							<div class="hello">
								<span>收货相关信息</span><a id="delete_addr" onclick="del()"
									style="cursor:pointer;">删除地址</a>
							</div>
							<div class="addr_pane">
								<div id="norecord">
									<span>暂无记录</span>
								</div>
								<div id="address" class="addr_list"></div>

								<div id="new_address_block" class="addr_new">

									<table cellspacing="0">
										<tr>
											<td width="100px"><span><strong
													style="color: orange;font-size: 25px;">*</strong>&nbsp;收货人员:</span>
											</td>
											<td><input class="new_input" type="text" id="recvName" />&nbsp;
												<h5 id="recvNameTip" style="display: inline;"></h5>
											</td>
										</tr>
										<tr>
											<td><span><strong
													style="color: orange;font-size: 25px;">*</strong>&nbsp;详细地址:</span>
											</td>
											<td><input class="new_input" type="text"
												id="recvAddress" />&nbsp;
												<h5 id="recvAddressTip" style="display: inline;"></h5>
											</td>
										</tr>
										<tr>
											<td><span><strong
													style="color: orange;font-size: 25px;">*</strong>&nbsp;邮政编码:</span>
											</td>
											<td><input class="new_input" type="text" id="recvZip"
												style="ime-mode:disabled"
												onkeydown="if(event.keyCode==13)event.keyCode=9"
												onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false" />&nbsp;
												<h5 id="recvZipTip" style="display: inline;"></h5>
											</td>
										</tr>
										<tr>
											<td><span><strong
													style="color: orange;font-size: 25px;">*</strong>&nbsp;手机号码:</span>
											</td>
											<td><input class="new_input" type="text" id="recvPhone"
												style="ime-mode:disabled"
												onkeydown="if(event.keyCode==13)event.keyCode=9"
												onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false" />&nbsp;
												<h5 id="recvPhoneTip" style="display: inline;"></h5>
											</td>
										</tr>
										<tr>
											<td></td>
											<td><input class="btn" type="button" value="保存信息"
												onclick="toSaveAddress()" />&nbsp;&nbsp;<input class="btn"
												type="button" value="重新设置" />
											</td>
										</tr>
									</table>
								</div>
							</div>

							<div class="ok"></div>
							<div class="addr_ad">
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
