<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>搜索搜搜搜索搜索搜索搜索搜索</title>
<link type="text/css" rel="stylesheet"
	href="../../css/user/searchList.css" />
<script type="text/javascript" src="../../js/user/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
//global var for split pages
var numPerPage=10;

	$(document).ready(function() {
	document.getElementById("items_current_page").value = 0;
		$("#timeOrder").click(function() {
			$("#timeOrder").css("border-bottom", "1px solid #333");
			$("#saleOrder").css("border-bottom", "");
			$("#priceOrder").css("border-bottom", "");

			$("#timeO").show();
			$("#saleO").hide();
			$("#priceO").hide();
		});
		$("#priceOrder").click(function() {
			$("#priceOrder").css("border-bottom", "1px solid #333");
			$("#saleOrder").css("border-bottom", "");
			$("#timeOrder").css("border-bottom", "");
			$("#priceO").show();
			$("#timeO").hide();
			$("#saleO").hide();
		});
		$("#saleOrder").click(function() {
			$("#saleOrder").css("border-bottom", "1px solid #333");
			$("#timeOrder").css("border-bottom", "");
			$("#priceOrder").css("border-bottom", "");
			$("#saleO").show();
			$("#timeO").hide();
			$("#priceO").hide();
		});

	});
	function addCart(itemId) {	
		var userParam = {
			"cart.item.itemId" : itemId,
			"cart.purchaseNum" : 1
		};

		$.ajax({
			url : '../../../UserConsuming/addShoppingCart',
			type : 'post',
			data : userParam,
			dataType : 'json',
			success : function(data) {
				if (data['code'] == 0) {
					alert("添加到购物车成功");
				} else if (data['code'] == -2){
					alert("请先登录");
					window.location.href="login.jsp";
				} else {
					alert("添加到购物车失败");
				}
			},
			error : function() {
				alert("error");
			}
		});
	
	}

	//关键字和价格查找 AJAX
	window.onload = function() {
		document.getElementById("saleOrder").value = false;
		document.getElementById("priceOrder").value = true;
		document.getElementById("timeOrder").value = true;
		search("sale", false,0,numPerPage);
	}

	function search(orderBy, orderSeq,start,limit) {
		var keyword = document.getElementById("keyword").value;
		var priceFrom = document.getElementById("priceFrom").value;
		var priceTo = document.getElementById("priceTo").value;

		var para = new Array();
		//alert("orderBy  " + orderBy + "  orderSeq  " + orderSeq);
		if (keyword == "" && priceFrom == "" && priceTo == "") {
			//alert(1);
			<%-- if(<%=request.getParameter("keyword")%> != null)
			{
			alert("keyword");
			para["condition.key"]=<%=request.getParameter("keyword")%>;
			}
			
			if(<%=request.getParameter("broadclassId")%> != null)
			{
			alert("broadclassId");
			para["condition.broadclass.broadClassId"]=<%=request.getParameter("broadclassId")%>;
			}
			
			if(<%=request.getParameter("subclassId")%> != null)
			{
			alert("subclassId");
			para["condition.subclass.subClassId"]=<%=request.getParameter("subclassId")%>;
			
			} --%>
			para = {
			<%if (request.getParameter("keyword") != null) {%>
				"condition.key" : "<%=request.getParameter("keyword")%>",
				<%}%>
				<%if (request.getParameter("broadClassId") != null) {%>
				"condition.broadclass.broadClassId" : <%=request.getParameter("broadClassId")%>,
				<%}%>
				<%if (request.getParameter("subclassId") != null) {%>
				"condition.subclass.subClassId" : <%=request.getParameter("subclassId")%>,
				<%}%>
				"condition.orderBy" : orderBy,
				"condition.orderSeq" : orderSeq,
				"start":start,
				"limit":limit
			};
			
		} else {
			//alert(2);
			para = {
				"condition.key" : document.getElementById("keyword").value,
				"condition.priceFrom" : document.getElementById("priceFrom").value,
				"condition.priceTo" : document.getElementById("priceTo").value,
				"condition.orderBy" : orderBy,
				"condition.orderSeq" : orderSeq,
				"start":start,
				"limit":limit
			};
		}
		$
				.ajax({
					url : '../../../UserConsuming/searchItems',
					type : 'post',
					data : para,
					dataType : 'json',
					success : function(data) {
					
					 document.getElementById("current_order_by").value =orderBy;
					 //alert(orderBy);
						if (data['items'][1] == 0) {
							document.getElementById("saleO").innerHTML = "<ul><h3>对不起，暂时没有您查找的商品</h3></ul>";
							document.getElementById("priceO").innerHTML = "<ul><h3>对不起，暂时没有您查找的商品</h3></ul>";
							document.getElementById("timeO").innerHTML = "<ul><h3>对不起，暂时没有您查找的商品</h3></ul>";
						} else {
						      //alert(data['items'][1]);
						        var totalPages = parseInt(data['items'][1] / numPerPage);
						        var remain=data['items'][1] % numPerPage;
						       //alert("totalPages "+totalPages +"remain "+remain);
						       if(remain==0){
						       document.getElementById("items_total_pages_num").value = totalPages -1;
						       }
						       else{
						       document.getElementById("items_total_pages_num").value = totalPages;
						       }
						       
							$.each(data['items'][0], function(i, item) {
								//alert(item.itemName);
								displayItem(orderBy, item,i);
							});
						}
					},
					error : function() {
					alert("error");
					}
					
				});
	}
	function displayItem(orderBy, item,i) {
		var displayDiv = "";
		switch (orderBy) {
		case 'sale':
			displayDiv = "saleO";
			break;
		case 'itemPrice':
			displayDiv = "priceO";
			break;
		case 'itemPubDate':
			displayDiv = "timeO";
			break;
		}
		var itemUL = document.getElementById(displayDiv);
		//alert(displayDiv);
		//alert("hhhh");
		if(i==0){
		itemUL.innerHTML="";
		}
		var oldContent = itemUL.innerHTML.substring(0);
		//alert("hhhh");
		//alert(oldContent);
		//alert(liID);
		var liID=displayDiv+i;
		itemUL.innerHTML = oldContent
				+ "<li id=\""+liID+"\"><div class=\"p_wrapper\" ><div class=\"p_img\"></div><div class=\"p_name\" style=\"float:left;\"></div><div style=\"padding:5px 0px 0px 0px;height:30px;float:right;\"></div><div class=\"p_price\" style=\"width:80px;vertical-align:bottom;padding-bottom:0px;\"></div><div class=\"p_buy\"><a href=\"javascript:;\" onclick=\"addCart(" + item[0]['itemId'] + ");\"> 加入购物车 </a>\</div></div></li>";
		//alert(oldContent);

		var li = document.getElementById(liID);
		var imgDiv = li.getElementsByTagName("div")[1];
		var nameDiv = li.getElementsByTagName("div")[2];
		var saleDiv=li.getElementsByTagName("div")[3];
		var priceDiv = li.getElementsByTagName("div")[4];
		//alert(oldContent);
		//alert(item['itemName']);
		imgDiv.innerHTML = "<a href=\"productDetail.jsp?itemId="
				+ item[0]['itemId']
				+ "\"><img src=\"../../source/image/item/"+item[0]['itemId']+".jpg\" /> </a>";
				//alert(imgDiv.innerHTML.substring(0));
		
			var name=item[0]['itemName'];
						
						if(name.length<6){
						nameDiv.innerHTML = "<a href=\"productDetail.jsp?itemId="
						+ item[0]['itemId'] + "\">" + item[0]['itemName']
						+ "</a>";
						}else{
						//alert(name.substr(0,15));
						nameDiv.innerHTML = "<a href=\"productDetail.jsp?itemId="
						+ item[0]['itemId'] + "\">" + name.substr(0,5)
						+ "...</a>";
						}
		saleDiv.innerHTML="已售"+item[1]['sale'];
		priceDiv.innerHTML = "¥ " + item[0]['itemPrice'];
		//alert(item['itemId']);
	}
	//click the sales order
	function orderBySale() {
	document.getElementById("items_current_page").value = 0;
		var orderBy = "sale";
		var orderSeq = document.getElementById("saleOrder").value;
		document.getElementById("current_order_by").value =orderBy;
		
		var start=0;
		//alert(orderSeq);
		
		if (orderSeq == false) {
			document.getElementById("saleOrder").value = true;
		    orderSeq = document.getElementById("saleOrder").value;
			search(orderBy, orderSeq,start,numPerPage);
			//alert(document.getElementById("saleOrder").value);
		} else {
			document.getElementById("saleOrder").value = false;
			orderSeq = document.getElementById("saleOrder").value;
			search(orderBy, orderSeq,start,numPerPage);
			//alert(document.getElementById("saleOrder").value);
		}

	}
	function orderByPrice() {
	document.getElementById("items_current_page").value = 0;
		var orderBy = "itemPrice";
		var orderSeq = document.getElementById("priceOrder").value;
		document.getElementById("current_order_by").value =orderBy;
		//alert(document.getElementById("current_order_by").value);
		var start=0;
		//alert(orderSeq);
		
		if (orderSeq == false) {
			document.getElementById("priceOrder").value = true;
			orderSeq = document.getElementById("priceOrder").value;
			search(orderBy, orderSeq,start,numPerPage);
		} else {
			document.getElementById("priceOrder").value = false;
			orderSeq = document.getElementById("priceOrder").value;
			search(orderBy, orderSeq,start,numPerPage);
		}
	}
	function orderByTime() {
	document.getElementById("items_current_page").value = 0;
		var orderBy = "itemPubDate";
		var orderSeq = document.getElementById("timeOrder").value;
		document.getElementById("current_order_by").value =orderBy;
		var start=0;
		//alert(orderSeq);
		
		if (orderSeq == false) {
			document.getElementById("timeOrder").value = true;
			orderSeq = document.getElementById("timeOrder").value;
			search(orderBy, orderSeq,start,numPerPage);
		} else {
			document.getElementById("timeOrder").value = false;
			orderSeq = document.getElementById("timeOrder").value;
			search(orderBy, orderSeq,start,numPerPage);
		}
	}
	function find() {
	document.getElementById("items_current_page").value = 0;
	var start=0;
		search("sale", false,start,numPerPage);
	}
	//split pages
	function previous_page_btn_click(){
	    var currentPage=document.getElementById("items_current_page").value;
	    var orderBy =document.getElementById("current_order_by").value;
	    var display;
	    switch (orderBy) {
		case 'sale':
			display = "saleOrder";
			break;
		case 'itemPrice':
			display = "priceOrder";
			break;
		case 'itemPubDate':
			display = "timeOrder";
			break;
		}
		//alert(orderBy);
	    var orderSeq=document.getElementById(display).value;
	   // alert("currentPage "+currentPage);
	    if(currentPage==0){
	         alert("已经是第一页了哦");
	         }
	         else{
	         var newPage=currentPage-1;
	         var start=newPage*numPerPage;
	         search(orderBy,orderSeq,start,numPerPage);//orderBy,orderSeq,start,limit
	         document.getElementById("items_current_page").value =newPage;
	         }
	         }
	function next_page_btn_click(){
	var totalPage=document.getElementById("items_total_pages_num").value;
	var currentPage=document.getElementById("items_current_page").value;
	    var orderBy =document.getElementById("current_order_by").value;
	    var display;
	    switch (orderBy) {
		case 'sale':
			display = "saleOrder";
			break;
		case 'itemPrice':
			display = "priceOrder";
			break;
		case 'itemPubDate':
			display = "timeOrder";
			break;
		}
	    var orderSeq=document.getElementById(display).value;
	   // alert(orderSeq);
	//alert("totalPage "+totalPage);
	//alert("currentPage "+currentPage);
	if(currentPage==totalPage){
	         alert("已经是最后一页了哦");
	         }
	         else{
	         var newPage=currentPage+1;
	         var start=newPage*numPerPage;
	         //alert("new "+newPage)
	          search(orderBy,orderSeq,start,numPerPage);//orderBy,orderSeq,start,limit
	         document.getElementById("items_current_page").value =newPage;
	         //alert(document.getElementById("items_current_page").value);
	         }
	         
	}
</script>
</head>

<body>
	<div class="clear"></div>
	<table width="100%">
		<tr>
			<td><jsp:include page="header.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td><div class="w" id="container">
					<!--BEGIN -->
					<div id="research"
						style="height:40px;line-height:40px;font-size:16px;">
						<form method="get" style="folat:left;">
							<li><span>关键词：</span> <input name="kw" type="text"
								id="keyword" /> <span style="margin-left:50px;">价格筛选：</span><input
								id="priceFrom" name="mt" type="text" style="width:30px;"
								style="ime-mode:disabled"
								onkeydown="if(event.keyCode==13)event.keyCode=9"
								onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false" />&nbsp—&nbsp<input
								id="priceTo" name="lt" type="text" style="width:30px;"
								style="ime-mode:disabled;"
								onkeydown="if(event.keyCode==13)event.keyCode=9"
								onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false" />
								<input type="button" value="查找" style="margin-left:20px;"
								onclick="find();" /></li>
						</form>
					</div>
					<div class="product_wrapper" id="items_total_pages_num">
						<div class="title" id="items_current_page">
							<h3 class="left" style="width:200px;" id="current_order_by">
								<span id="saleOrder" style="cursor: pointer;font-size:14px;"
									onclick="orderBySale();">销量</span>&nbsp/&nbsp<span
									id="priceOrder" style="cursor: pointer;font-size:14px;"
									onclick="orderByPrice();">价格</span>&nbsp/&nbsp<span
									id="timeOrder" style="cursor: pointer;font-size:14px;"
									onclick="orderByTime();">时间</span>
							</h3>
						</div>
						<div>
							<ul id="saleO" class="product_lst clearfix">
							</ul>
						</div>
						<div>
							<ul id="priceO" class="product_lst clearfix"
								style="display:none;">
							</ul>
						</div>
						<div>
							<ul id="timeO" class="product_lst clearfix" style="display:none;">
							</ul>
						</div>
						<div style="width:1000px;float:left;text-align: center;">

							<a id="previous_page_btn" onclick="previous_page_btn_click();"
								style="width:150pxpx;background-color:#FFCC99;border: 1px solid #FFCC99;cursor: pointer;font-size:18px;">上一页</a>


							<a id="next_page_btn" onclick="next_page_btn_click();"
								style="width:150px;background-color:#FFCC99;border: 1px solid #FFCC99;cursor: pointer;font-size:18px;">下一页</a>
						</div>
					</div>
					<!-- END -->
				</div></td>
		</tr>
		<tr>
			<td><jsp:include page="footer.jsp"></jsp:include></td>
		</tr>
	</table>


</body>
</html>