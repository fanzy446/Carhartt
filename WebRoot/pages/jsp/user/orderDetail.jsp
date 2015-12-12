<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单详情</title>
<meta name="keywords" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet"
	href="../../css/user/orderDetail.css" />
<script type="text/javascript" src="../../js/user/jquery.js"></script>

<script type="text/javascript">
	function tocomment(a) {
		a.type = 'text';
		a.className = 'comment_input';
		a.value = '';
	};
	function save_comment(obj) {
		
		alert('评论成功!');
		var text = document.getElementById(obj).value;
		var para = {
			"text":text,
			"detailid":obj
		};
		$.ajax({
			url : '../../../addevaluate',
			type : 'post',
			data:   para,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
			dataType : 'json',
			success :function(data){
				location.reload(); 
			}
		});
	}
	function GetQueryString(name) {  
    	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
    	var r = window.location.search.substr(1).match(reg);  
    	if (r != null) return unescape(r[2]); return null;  
	}
	function  getAllItems(data){

	var table = document.getElementById("item_tb");
	for(var i=0;i<data['size'];i++)
	{
		var newTr = table.insertRow(1);
		var td0 = newTr.insertCell(0);
		var td1= newTr.insertCell(1);
		var td2= newTr.insertCell(2);
		var td3 = newTr.insertCell(3);
		var td4 = newTr.insertCell(4);
		td0.innerHTML="<span>"+data[i]['item']+"</span>";
		td1.innerHTML="<span>"+data[i]['price']+"</span>";
		td2.innerHTML="<span>"+data[i]['count']+"</span>";
		if(data[i]['remark'] == null)
		{
		td3.innerHTML="<input id=\""+data[i]['detailid']+"\" type=\"text\" value=\"待评论\"/>";
		td4.innerHTML="<input type=\"button\" value=\"提交评论\" class=\"submit_btn\" onclick=\"save_comment("+data[i]['detailid']+")\" />";
		}
		else
		{
		td3.innerHTML="<span>"+data[i]['remark']+"</span>";
		td4.innerHTML="无";
		}
	}
	}
	function loadinfo()
	{
		var id = GetQueryString("orderid");
		document.getElementById("inputid").textContent = id;
		var para = {
			"orderid":id
		};
		$.ajax({
			url : '../../../getalldetails',
			type : 'post',
			data:   para,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
			dataType : 'json',
			success :function(data){
				getAllItems(data);
			}
		});
		$.ajax({
			url : '../../../getoneorder',
			type : 'post',
			data:   para,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
			dataType : 'json',
			success :function(data){
				document.getElementById("inputname").textContent = data['ConsigneeName'];
				document.getElementById("inputaddress").textContent = data['Address'];
				document.getElementById("inputcode").textContent = data['PostCode'];
				document.getElementById("inputphone").textContent = data['ConsigneePhone'];
				var state = data['OrderState'];
				var state0=0;
				var state1=1;
				var state2=2;
				if(state == state0)
				{
					document.getElementById("inputstate").textContent = "未发货";
				}else{
					if(state == state1)
					{
						document.getElementById("inputstate").textContent = "已发货";
					}
					else
					{
						if(state == state2)
						{
							document.getElementById("inputstate").textContent = "订单取消";
						}
						else
						{
							document.getElementById("inputstate").textContent = "交易成功";
						}
					}	
				}
			}
		});
		
	}
	window.onload = function() {
		loadinfo();
	};
</script>
</head>
<body>
	<div id="wrapper" class="w">
		<div id="header"></div>
		<!-- <p style="font-size: 26px;font-weight: bold;color: #525252;">订单详情
		</p> -->
		<div id="footer" class="w"></div>
		<div class="clear"></div>

		<div id="order_content">
			<form name="deliverForm" id="order" method="post" action="XXXXXX">
				<input type="hidden" name="method" value="saveorder" /> <input
					type="hidden" name="contactid" id="contactid" value="" />
				<h3 class="htitle">订单号</h3>
				<div class="border_add">
					<span id="inputid"></span>
				</div>
				<h3 class="htitle">收货人信息</h3>
				<div style="border: 1px solid #f3f3f3;">
					<table id="address_tb">
						<tr>
							<td width="100px"><p>收件人名:</p></td>
							<td id="inputname"></td>
						</tr>
						<tr>
							<td><p>详细地址:</p></td>
							<td id="inputaddress"></td>
						</tr>
						<tr>
							<td><p>邮政编码:</p></td>
							<td id="inputcode"></td>
						</tr>
						<tr>
							<td><p>电话号码:</p></td>
							<td id="inputphone"></td>
						</tr>
					</table>
				</div>

				<h3 class="htitle">商品清单</h3>
				<div id="cartlst">
					<table id="item_tb" class="lsttable">
						<tr>
							<th width="200px"><span>商品</span>
							</th>
							<th><span>价格</span>
							</th>
							<th><span>数量</span>
							</th>
							<th><span>评论</span>
							</th>
							<th><span>操作</span>
							</th>
						</tr>
						<!-- <tr>
							<td><span>我的名字是商品</span>
							</td>
							<td><span>1100</span>
							</td>
							<td><span>10000</span>
							</td>
							<td>
								<span></span>
							</td>
							<td>
<span>

									</span>
							</td>
						</tr>
						
						
						
						<tr>
							<td><span>我的名字是商品</span>
							</td>
							<td><span>1100</span>
							</td>
							<td><span>10000</span>
							</td>
							<td><span>11000000</span>
							</td>
							<td><span><input type="button" value="已评论"
									class="btn" /> </span>
							</td>
						</tr>
						 -->
					</table>
				</div>

				<h3 class="htitle">支付方式</h3>
				<div class="border_add">
					<span>货到付款</span>
				</div>

				<h3 class="htitle">订单状态</h3>
				<div id="sentState" class="border_add">
					<span id="inputstate"></span>
				</div>
				<div class="last_btn">
					<input type="button" value="返回我的订单" class="btn"
						style="float: right;" onclick="location.replace('user_center_order.jsp')"/>
				</div>
			</form>
		</div>
	</div>
</body>
</html>