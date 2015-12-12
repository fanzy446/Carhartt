<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<title>购物车</title>
<meta name="keywords" content="购物车">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="../../css/user/style1.css" />
<script type="text/javascript" src="../../js/user/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	//layout in the begining
	$(document)
			.ready(
					function() {
						$("#order_content").hide();
						$("#content_suc").hide();

						//compute the subTotal 
						var oList = document.getElementById("list_table");
						var row = oList.rows;
						var subTotelPrice = 0;
						var i = 1;
						for (i = 1; i < row.length; i++) {
							unitPrice = row[i].getElementsByTagName("span")[0].innerHTML
									.substring(0);
							//alert(unitPrice);
							buy_num = row[i].getElementsByTagName("input")[1].value;
							//alert(buy_num);
							subTotelPrice = unitPrice * buy_num;
							//alert(subTotelPrice);
							row[i].getElementsByTagName("span")[1].innerHTML = subTotelPrice;
						}
						//comput the totel quantity and the totel price
						checkedAdd();
					});

	//add_btn  and  reduce_btn
	function btn_reduce_click(i) {
		var buyNum = document.getElementsByName("buy_num")[i - 1].value;
		//var buyNum = document.getElementById("buy_num")[i].value;
		//alert("xxx");
		//alert(buyNum);
		if (buyNum <= 1) {
			alert("购买数量不能低于1");
		} else {
			document.getElementsByName("buy_num")[i - 1].value = buyNum - 1;
			var oList = document.getElementById("list_table");
			var row = oList.rows;
			var subTotelPrice = 0;
			unitPrice = row[i].getElementsByTagName("span")[0].innerHTML
					.substring(0);
			//alert(unitPrice);
			buy_num = row[i].getElementsByTagName("input")[1].value;
			//alert(buy_num);
			subTotelPrice = unitPrice * buy_num;
			//alert(subTotelPrice);
			row[i].getElementsByTagName("span")[1].innerHTML = subTotelPrice.toFixed(2);

			if (row[i].getElementsByTagName("input")[0].checked) {
				checkedAdd();
			}

			var cartid = row[i].getElementsByTagName("span")[2].innerHTML;
			var para ={
				"change":0,
				"cartid":cartid
			};
			$.ajax({
				url : '../../../changeshoppingcart',
				data : para,
				type : 'post',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
				dataType : 'json',
				success :function(data){
				}
			});
		}
	}
	function btn_add_click(i) {
		var buyNum = document.getElementsByName("buy_num")[i - 1].value;
		//alert("hhhhhhhhh");
		//alert(buyNum);
		var stock = 100;//从后台获得的库存量
		if (buyNum >= stock) {
			alert("库存量不足");
		} else {
			buyNum++;
			document.getElementsByName("buy_num")[i - 1].value = buyNum;
			var oList = document.getElementById("list_table");
			var row = oList.rows;
			var subTotelPrice = 0;
			unitPrice = row[i].getElementsByTagName("span")[0].innerHTML
					.substring(0);
			//alert(unitPrice);
			buy_num = row[i].getElementsByTagName("input")[1].value;
			//alert(buy_num);
			subTotelPrice = unitPrice * buy_num;
			//alert(subTotelPrice);
			row[i].getElementsByTagName("span")[1].innerHTML = subTotelPrice.toFixed(2);

			if (row[i].getElementsByTagName("input")[0].checked) {
				checkedAdd();
			}
		}
		
		var cartid = row[i].getElementsByTagName("span")[2].innerHTML;
			var para ={
				"change":1,
				"cartid":cartid
			};
			$.ajax({
				url : '../../../changeshoppingcart',
				data : para,
				type : 'post',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
				dataType : 'json',
				success :function(data){
				}
			});
	}

	//结算check()
	function check() {
		var selItems = selectedItems();
		if(selItems.length==0){
		alert("请选择商品后结算！");
		return ;
		}
		var totalQuanOrder = 0;
		var totalPriceOrder = 0;
		var list = document.getElementById("order_list_table");
		//var newTr = table.insertRow();
		//alert(newTr.length);
		var i = 0;
		for (i = 0; i < selItems.length; i++) {
			var newTr = list.insertRow(i + 1);
			//alert(list.rows.length);
			newTr.innerHTML = "<td><div>"+selItems[i]['itemId']+"</div></td><td><div><a  target=\"_blank\" >"
					+ selItems[i]['itemName']
					+ "</a></div></td><td>￥<span class=\"unitprice\" id=\"upID\">"
					+ selItems[i]['unitPrice']
					+ "</span></td><td><input type=\"text\" id=\"buy_num\" value=\""
					+selItems[i]['buy_num']
					+"\"name=\"buy_num\" disabled=\"disabled\" /></a></td><td>￥<span class=\"subtotalprice\"id=\"sp<?php echo $Cproducts[$i][0];?>\">"
					+ selItems[i]['subTotelPrice'] + "</span></td><td><span style=\"display:none;\" >"+selItems[i]['cartId']+"</span></td>";

			totalQuanOrder += parseInt(selItems[i]['buy_num']);
			totalPriceOrder += selItems[i]['subTotelPrice'];
		}
		var totalBox = document.getElementById("order_total_box");
		totalBox.getElementsByTagName("span")[0].innerHTML = totalQuanOrder;
		totalBox.getElementsByTagName("span")[1].innerHTML = totalPriceOrder.toFixed(2);
		document.getElementById("cart_top").innerHTML = "<li><b class=\"f\"></b><a>1.我的购物车</a><s></s></li><li class=\"on\"><b></b><a>2.核对订单信息</a><s></s></li><li><b></b><a>3.成功提交订单</a><s></s></li>";
		$("#cart_content").hide();
		$("#content_suc").hide();
		$("#order_content").show();
		
		$.ajax({
			url : '../../../getaddress',
			type : 'post',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
			dataType : 'json',
			success :function(data){
				getaddress(data);
			}
		});
	}
	//get the selected items

	function selectedItems() {
		var selItems = new Array();
		var item = new Array(4);
		var oList = document.getElementById("list_table");
		var row = oList.rows;
		//alert(row.length);
		//var isChecked=false;
		var itemName = "";
		var unitPrice = 0;
		var buy_num = 0;
		var subTotelPrice = 0;
		var itemId;
		var i = 1;
		for (i = 1; i < row.length; i++) {
			//alert("hhh");
			//alert(row[i].getElementsByTagName("input")[0].checked);
			//alert('jhh');
			if (row[i].getElementsByTagName("input")[0].checked) {
				//alert('aaa');
				cartId=row[i].getElementsByTagName("span")[2].innerHTML
						.substring(0);
				itemId=row[i].getElementsByTagName("a")[0].getAttribute("value");
				//alert(itemId);
				itemName = row[i].getElementsByTagName("a")[0].innerHTML
						.substring(0);
				//alert(itemName);
				unitPrice = row[i].getElementsByTagName("span")[0].innerHTML
						.substring(0);
				//alert(unitPrice);
				buy_num = row[i].getElementsByTagName("input")[1].value;
				//alert(buy_num);
				subTotelPrice = unitPrice * buy_num;
				//alert(subTotelPrice);
				item = {
				    'itemId':itemId,
					'itemName' : itemName,
					'unitPrice' : unitPrice,
					'buy_num' : buy_num,
					'subTotelPrice' : subTotelPrice,
					'cartId':cartId
				};
				selItems.push(item);
			}
		}
		//alert('success');
		return selItems;
	}
	//submitOrder()
	function submitOrder() {
		
		var address;
		var paymethod;
		var content;
		var totalprice;
		var obj1 = document.getElementsByName("myrad");
		
		var address_selected=false;
    	for(var i=0; i<obj1.length; i ++){
        	if(obj1[i].checked){
            	address = obj1[i].id;
            	address_selected=true;
       		}
    	}

    	var obj2 = document.getElementsByName("PaymentMethod");
    	for(var i=0; i<obj2.length; i ++){
        	if(obj2[i].checked){
            	paymethod = obj2[i].id;
       		}
    	}

    	content = document.getElementById("note").value;
    	totalprice = $("#finalPrice").text(); 
    	var keyvalue ="";    	
    	var tab = document.getElementById("order_list_table"); 
  		var rows = tab.rows.length;
  		
  		for(var i = 1; i < rows; i++)
  		{
    		var cols = tab.rows[i].childNodes;
			keyvalue+=cols[0].getElementsByTagName("DIV")[0].innerHTML;
			keyvalue+="##";
			keyvalue+=cols[3].getElementsByTagName("INPUT")[0].value;
			keyvalue+="##";
			keyvalue+=cols[5].getElementsByTagName("span")[0].innerHTML;
			keyvalue+="@@";

  		}
  		
  		if(address_selected==true){
  		para = {
			"consigneeid":address,
			//"consigneePhone":paymethod,
			"context":content,
			"detail":keyvalue,
			"totalprice":totalprice
		};
		$.ajax({
			url : '../../../addorder',
			type : 'post', 
			data:para,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
			dataType : 'json',
			success :function(data){
			}
		});
		document.getElementById("cart_top").innerHTML = "<li><b class=\"f\"></b><a>1.我的购物车</a><s></s></li><li ><b></b><a>2.核对订单信息</a><s></s></li><li class=\"on\"><b></b><a>3.成功提交订单</a><s></s></li>";
		$("#cart_content").hide();
		$("#content_suc").show();
		$("#order_content").hide();
		}else{
		alert('请选择地址！');}
		
		
	}
		


	//add new address
	function displayNewAdd() {
		var newTab = document.getElementById("addNewAddr");
		newTab.style.display = "block";
	}
	//save new address
	var newTab;
	var recvName;
	var recvAddress;
	var recvZip;
	var recvPhone;
	function saveNewAdd() {
		newTab = document.getElementById("addNewAddr");
		recvName = document.getElementById("recvName").value;
		if(recvName==""){
		alert("收件人名称不能为空");
		return false;
		}
		recvAddress = document.getElementById("recvAddress").value;
		if(recvAddress==""){
		alert("地址不能为空");
		return false;
		}
		recvZip = document.getElementById("recvZip").value;
		if(recvZip==""){
		alert("邮政编码不能为空");
		return false;
		}
		recvPhone = document.getElementById("recvPhone").value;
		if(recvPhone==""){
		alert("收件人手机号码不能为空");
		return false;
		}
		para = {
			"consigneeName":recvName,
			"consigneePhone":recvPhone,
			"fullAddress":recvAddress,
			"postcode":recvZip
		}
		$.ajax({
			url : '../../../addaddress',
			type : 'post', 
			data:para,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
			dataType : 'json',
			success :function(data){
				addnew(data['newid']);

			}
		});

		//alert(recvName);
		
	}
	function addnew(newid)
	{
		var table = document.getElementById("recv_infor_array");
		//alert("get the table");
		var newTr = table.insertRow();
		//alert("get the tr");
		var td0 = newTr.insertCell();
		//alert("get the td");
		//alert(newid);
		td0.innerHTML = "<p style=\"color:#696969;font-weight: bold;\"><input id="+newid+" type=\"radio\"  name=\"myrad\" checked>"
				+ recvName
				+ "  "
				+ recvAddress
				+ "  "
				+ recvZip
				+ "  "
				+ recvPhone + "  " + "</p>";
		newTab.style.display = "none";
	}

	//select all
	function select_all() {
		//alert("hhh");
		var oList = document.getElementById("list_table");
		var aToSelect = oList.getElementsByTagName("input");
		var i = 0;
		for (i = 0; i < aToSelect.length; i++) {

			if (aToSelect[i].type == "checkbox") {

				aToSelect[i].checked = document.getElementById("allSelect").checked;
				//alert(aToSelect[i].checked);
			}
		}
		checkedAdd();
	}

	//select an item and then add the price
	function checkedAdd() {
		var selItems = selectedItems();
		var totalQuan = selItems.length;
		var totalBox = document.getElementById("total_box");
		totalBox.getElementsByTagName("span")[1].innerHTML = totalQuan;
		var i = 0;
		var totalPrice = 0;
		for (i = 0; i < totalQuan; i++) {
			totalPrice += selItems[i]['subTotelPrice'];
		}
		totalBox.getElementsByTagName("span")[0].innerHTML = totalPrice.toFixed(2);
	}
	//display the cart items
	
	function  getAllItems(i,item,size){
		var table = document.getElementById("list_table");
		//alert("get the table");
		//alert(data.length);
		//for(var i=0;i<data.length;i++){
		var newTr = table.insertRow(1);
		//alert("get the tr");
		var td0 = newTr.insertCell(0);
		var td1= newTr.insertCell(1);
		var td2= newTr.insertCell(2);
		var td3 = newTr.insertCell(3);
		var td4 = newTr.insertCell(4);
		var td5 = newTr.insertCell(5);
		var td6 = newTr.insertCell(6);
		td0.innerHTML="<input type=\"checkbox\" name=\"radiobutton\" value=\"radiobutton\" onclick=\"checkedAdd();\"></input>";
		td1.innerHTML="<div><a href=\"productDetail.jsp?itemId="+item['itemid']+"\" target=\"_blank\" name=\"itemName\" value=\""+item['itemid']+"\">"+item['itemname']+"</a></div>";
		td2.innerHTML="￥<span class=\"unitprice\" name=\"unitPrice\">"+item['itemprice']+"</span>";
		td3.innerHTML="<div class=\"wrapbuynum\">\<a class=\"btn_reduce\" id=\"btn_reduce\" onclick=\"btn_reduce_click("+(size-i)+");\"></a> <input type=\"text\" id=\"buy_num\" value=\""+item['number']+"\" name=\"buy_num\" disabled=\"disabled\" /><a class=\"btn_add\" id=\"btn_add\" onclick=\"btn_add_click("+(size-i)+");\"></a></div>";
		var subTotal=item['itemprice']*item['number'];
		//alert(subTotal.toFixed(2));
		td4.innerHTML="￥<span class=\"subtotalprice\" name=\"subTotelPrice\">"+subTotal.toFixed(2)+"</span>";
		td5.innerHTML="<a href=\"javascript:;\" class=\"deleteItem\" onclick=\"delCp("+item['cartid']+","+(size-i)+");\">移除</a>";
		//var td6id="cartid"+item['cartid'];
		td6.innerHTML="<span style=\"display:none;\" id=\"cartid"+item['cartid']+"\">"+item['cartid']+"</span>";
		
		//alert(document.getElementById(td6id).innerHTML.substring(0));
		//}
	}
	
	function getaddress(infoAddress)
	{
	    var size=infoAddress['size'];
	    for(var i=0;i<size;i++)
	    {
	       if(infoAddress[i]['state'] == '1'){
	       var table = document.getElementById("recv_infor_array");
			var newTr = table.insertRow(0);
			var td0 = newTr.insertCell(0);
			if(i==size-1){
			td0.innerHTML="<p style=\"color:#696969;font-weight: bold;\"><input id="+infoAddress[i]['ConsigneeId']+" type=\"radio\" name=\"myrad\" checked=true>"+infoAddress[i]['ConsigneeName']+"  "+infoAddress[i]['ConsigneePhone']+"  "+infoAddress[i]['ConsigneePostcode']+"  "+infoAddress[i]['ConsigneeAddress']+"</p>";
			}else{
			td0.innerHTML="<p style=\"color:#696969;font-weight: bold;\"><input id="+infoAddress[i]['ConsigneeId']+" type=\"radio\" name=\"myrad\">"+infoAddress[i]['ConsigneeName']+"  "+infoAddress[i]['ConsigneePhone']+"  "+infoAddress[i]['ConsigneePostcode']+"  "+infoAddress[i]['ConsigneeAddress']+"</p>";
			}
	       }
	    	}
		
	}
	function delCp(obj,row)
	{
		var para={
			'cartid':obj
		};
		$.ajax({
			url : '../../../delshoppingcart',
			type: 'post', 
			data:para,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
			dataType : 'json',
			success :function(data){
				var table = document.getElementById("list_table");
				table.deleteRow(row);
				reDisplayCart(row,table);
			}
		});
	}
	function reDisplayCart(row,table){
	//alert(table.rows.length);
	for(var i=row;i<table.rows.length;i++){
	var cartId=table.rows[i].getElementsByTagName("span")[2].innerHTML
						.substring(0);
	//alert(cartId);
	table.rows[i].cells[5].innerHTML="<a href=\"javascript:;\" class=\"deleteItem\" onclick=\"delCp("+cartId+","+i+");\">移除</a>";
	var buy_num = table.rows[i].getElementsByTagName("input")[1].value;
	//alert(buy_num);
	table.rows[i].cells[3].innerHTML="<div class=\"wrapbuynum\">\<a class=\"btn_reduce\" id=\"btn_reduce\" onclick=\"btn_reduce_click("+i+");\"></a> <input type=\"text\" id=\"buy_num\" value=\""+buy_num+"\" name=\"buy_num\" disabled=\"disabled\" /><a class=\"btn_add\" id=\"btn_add\" onclick=\"btn_add_click("+i+");\"></a></div>";
	}
	}
	window.onload = function() {
	//alert("!!")
		$.ajax({
			url : '../../../getoneshoppingcart',
			type : 'post',                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
			dataType : 'json',
			success :function(data){
			var totalSize="";
			$.each(data, function(i,item) {	
				totalSize+=i;
			});
			//alert(totalSize.length);
				$.each(data, function(i,item) {	
				getAllItems(i,item,totalSize.length);
			});	
			}
		});
	};
</script>

</head>

<body>
	<table width="100%" cellspacing="0">
		<tr>
			<td><jsp:include page="header.jsp"></jsp:include></td>
		</tr>
		<tr>
		
		
			<td><div id="wrapper" class="w">
					<div id="header">
						<ul class="flowsteps right" id="cart_top">
							<li class="on"><b class="f"></b><a>1.我的购物车</a><s></s></li>
							<li><b></b><a>2.核对订单信息</a><s></s></li>
							<li><b></b><a>3.成功提交订单</a><s></s></li>
						</ul>

					</div>

					<div class="clear"></div>



					<div id="cart_content">


						<!-- 购物车商品列表,有商品才显示 -->


						<form id="buycart" name="buycart" method="post">
							<input type="hidden" name="method" id="method"
								value="settleAccounts" /> <input type="hidden" name="directUrl"
								value="" />
							<div id="cartlst">
								<table class="lsttable" id="list_table">
									<tr>
										<th class="check_box" style="text-align: center;"><input
											type="checkbox" id="allSelect" name="radiobutton"
											value="radiobutton" onclick="select_all();">全选</input></th>
										<th class="cell_goods">商品</th>
										<th class="cell_normal">价格</th>
										<th class="cell_cb">数量</th>
										<th class="cell_normal">小计</th>
										<th class="cell_cb">操作</th>
									</tr>
								</table>

								<div class="bgbox" id="total_box">
									<div class="right">
										<strong>您共需要为订单支付（不含运费）：￥<span id="finalPrice">1111</span>
										</strong>
									</div>
									共选中&nbsp;<span class="red" id="finalAmount">111</span>&nbsp;种商品
									&nbsp;&nbsp;<span class="red" id="error_msg"></span>
								</div>

								<div class="green_btn">
									<a href="javascript:;" onclick="check();">结算</a>
								</div>
							</div>
						</form>
					</div>
					<!-- end of content -->

					<div id="order_content">
						<form name="deliverForm" id="order" method="post">
							<input type="hidden" name="method" value="saveorder" /> <input
								type="hidden" name="contactid" id="contactid" value="" />
							<h3 class="htitle">收货人信息</h3>
							<table id="recv_infor_array">
								<tr>
									<td><p style="color:#696969;font-weight: bold;">
											<input type="radio" name="myrad" onclick="displayNewAdd();">使用新地址
										</p></td>
								</tr>
							</table>
							<ul id="addNewAddr" style="display:none;">
								<li id="add">
									<table>
										<tr>
											<td><p>收件人姓名:</p></td>
											<td><input type="text" name="RecipientName"
												id="recvName" value="" /></td>
										</tr>
										<tr>
											<td><p>详细地址:</p></td>
											<td><input type="text" name="Address" id="recvAddress"
												value="" /></td>
										</tr>
										<tr>
											<td><p>邮政编码:</p></td>
											<td><input type="text" name="Zip" id="recvZip"
												value="" /></td>
										</tr>
										<tr>
											<td><p>电话号码:</p></td>
											<td><input type="text" name="RecipientPhone"
												id="recvPhone" value="" /></td>
										</tr>
									</table>
								</li>


								<input type="button" value="保存" onclick="saveNewAdd();"
									style="background-color: #B4CA20;color: #FFFFFF;width:50px;height:30px;font-size: 14px;" />
							</ul>
							<h3 class="htitle">商品清单</h3>
							<div id="cartlst">
								<table class="lsttable" id="order_list_table">
									<tr id="orderGoods">
									    <th>ID</th>
										<th>商品</th>
										<th>价格</th>
										<th>数量</th>
										<th>小计</th>
									</tr>
								</table>
							</div>

							<h3 class="htitle">支付方式</h3>
							<ul class="chlst">
								<li><input type="radio" id="crash" checked="checked"
									name="PaymentMethod" value="信用卡" /> <label for="crash">信用卡</label>
									<input type="radio" id="crash" name="PaymentMethod"
									value="货到付款" /> <label for="crash">货到付款</label>
								</li>
							</ul>
							<h3 class="htitle">订单结算</h3>
							<div id="ordermsg">
								<div class="right" id="order_total_box">
									<p>
										总共有<span class="finalAmount red"> </span>件商品
									</p>
									<p>
										<strong>您共需要为订单支付：<span class="red confirm_price"
											id="finalPrice"><input name="total" value=""
												style="display:none"> </span> </strong>
									</p>
								</div>
								<span>留言</span> <br />
								<textarea id="note" name="note" value="/"></textarea>

							</div>
							<div class="green_btn">
								<input type="button" value="提交订单" class="green_btn"
									onclick="submitOrder();" />
							</div>
						</form>
					</div>
					<!-- end of content -->

					<div id="content_suc">
						<div>
							<div>
								<a href="cart.jsp">提交成功，点击此处返回!</a>
							</div>
						</div>
					</div>
				</div></td>
		</tr>
		<tr>
			<td><jsp:include page="footer.jsp"></jsp:include></td>
		</tr>
	</table>


	<div class="clear">
	</div>
</body>
</html>