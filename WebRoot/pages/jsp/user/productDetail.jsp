<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品详情</title>
<script type="text/javascript" src="../../js/user/jquery.js"></script>

<script type="text/javascript" src="../../js/user/jquery-1.7.2.min.js"></script>
<link type="text/css" rel="stylesheet"
	href="../../css/user/productDetail.css" />


<script type="text/javascript">
	//operation in the begining
	//product_comment_detail_tab
	var numPerPage = 5;
	var stock;
	window.onload = function() {
		var userParam = {
			"item.itemId" :
<%=request.getParameter("itemId")%>
	,
			"start" : 0,
			"limit" : numPerPage,
			"condition.orderBy" : "sale",
			"condition.orderSeq" : false
		};
		$
				.ajax({
					url : '../../../UserConsuming/checkComDetail',
					type : 'post',
					data : userParam,
					dataType : 'json',
					success : function(data) {
						fillItemInfo(data['itemDetail']);
						fillCommendItems(data['items'], data['itemDetail']);
						//alert("xixi");
						if (data['commentSum'] == 0) {
							//alert("xixi");
							var commentsDiv = document
									.getElementById("detail_remark_page");
							commentsDiv.innerHTML = "";
							commentsDiv.innerHTML = "</br></br><div style=\"color:#CDC8B1;font-weight: bold;font-size:16px;padding-bottom:2px;\">没有更多评论了~~~~</div>";
							document.getElementById("previous_page_btn").style.display = "none";
							document.getElementById("next_page_btn").style.display = "none";
						} else {
							fillComments(data['comment']);

							var totalPages = parseInt(data['commentSum']
									/ numPerPage);
							var remain = data['commentSum'] % numPerPage;
							//alert("remain "+remain);
							//alert("totalPage"+ totalPages);
							if (remain == 0) {
								document.getElementById("total_comment_pages").value = totalPages - 1;
							} else {
								document.getElementById("total_comment_pages").value = totalPages;
							}
							//alert(data['commentSum']);
							//alert(totalPages);
							//document.getElementById("total_comment_pages").value = totalPages - 1;
							document.getElementById("detail_remark_page").value = 0;
							//alert(document.getElementById("total_comment_pages").value);
						}

					}
				});
	}

	function product_btn_click() {
		var comStyle = document.getElementById("comment_tab");
		var proStyle = document.getElementById("product_tab");
		var proDiv = document.getElementById("product_detail");
		var comDiv = document.getElementById("comment_detail");

		comStyle.style.backgroundColor = "#FFCC99";
		proStyle.style.backgroundColor = "#FFFFFF";
		proDiv.style.display = "block";
		comDiv.style.display = "none";

	}
	function comment_btn_click() {
		var comStyle = document.getElementById("comment_tab");
		var proStyle = document.getElementById("product_tab");
		var proDiv = document.getElementById("product_detail");
		var comDiv = document.getElementById("comment_detail");
		comStyle.style.backgroundColor = "#FFFFFF";
		proStyle.style.backgroundColor = "#FFCC99";
		proDiv.style.display = "none";
		comDiv.style.display = "block";

		/* var userParam = {
			"item" : $("#"),
			"start" : $("#loginEmail").val(),
			"limit" : $("#registerPassWord").val()
		};

		$.ajax({
			url : '../../../UserConsuming/getComments',
			type : 'post',
			data : userParam,
			dataType : 'json',
			success : function(data) {
				$.each(data, function(i, evaluate) {

				});
			}
		}); */
	}

	//add_btn  and  reduce_btn
	function btn_reduce_click() {
		var buyNum = document.getElementById("buy_num").value;
		if (buyNum <= 1) {
			alert("购买数量不能低于1");
		} else {
			document.getElementById("buy_num").value = buyNum - 1;
		}
	}
	function btn_add_click() {
		var buyNum = document.getElementById("buy_num").value;
		//var stock = 100;//从后台获得的库存量
		if (buyNum >= stock) {
			alert("库存量不足");
		} else {
			buyNum++;
			document.getElementById("buy_num").value = buyNum;
		}
	}
	function btn_add_cart_click() {
		var userParam = {
			"cart.item.itemId" : $("#product_id").val(),
			"cart.purchaseNum" : $("#buy_num").val(),
			"cart.itemPrice" : document.getElementById("product_price").innerHTML
					.substring(0)
		};

		$.ajax({
			url : '../../../UserConsuming/addShoppingCart',
			type : 'post',
			data : userParam,
			dataType : 'json',
			success : function(data) {
				if (data['code'] == 0) {
					alert("添加到购物车成功");
				} else if (data['code'] == -2) {
					alert("请先登录");
					window.location.href = "login.jsp";
				} else {
					alert("添加到购物车失败");
				}
			},
			error : function() {
				alert("error");
			}
		});
	}
	function btn_add_favorite() {
		var userParam = {
			"favorites.item.itemId" : $("#product_id").val()
		};

		$.ajax({
			url : '../../../UserConsuming/addFavorites',
			type : 'post',
			data : userParam,
			dataType : 'json',
			success : function(data) {
				if (data['code'] == 0) {
					alert("收藏成功");
				} else if (data['code'] == -1) {
					alert("该商品已收藏");
				} else if (data['code'] == -2) {
					alert("请先登录");
					window.location.href = "login.jsp";
				}
			},
			error : function() {
				alert("error");
			}
		});
	}

	//limit the input of the buy_num
	function limitInput(o) {
		var value = o.value;
		//var stock = 100;//从后台获取的库存量
		if (parseInt(value) < 1) {
			alert("购买数量不能低于1");
			o.value = '1';
		} else if (parseInt(value) > stock) {
			alert("库存量不足");
			o.value = '1';
		}
	}
	//fill in the info of the item
	function fillItemInfo(item) {
		//alert("lll");
		var itemId = document.getElementById("product_id");
		var itemPhoto = document.getElementById("spec_n1");
		var itemName = document.getElementById("product_name");
		var itemRemark = document.getElementById("product_remark");
		var itemPrice = document.getElementById("product_price");
		var itemStock = document.getElementById("product_stock");
		var itemDesc = document.getElementById("product_desc");
		var itemSale = document.getElementById("product_sales");
		stock = item['itemCount'];
		itemId.value = item['itemId'];
		itemPhoto.innerHTML = "<img width=\"400\" height=\"400\" src=\"../../source/image/item/"+item['itemId']+".jpg\" />";
		itemName.innerHTML = item['itemName'];
		itemRemark.innerHTML = item['remark'];
		itemPrice.innerHTML = item['itemPrice'];
		itemStock.innerHTML = "<span class=\"dl\">库存：</span>"
				+ item['itemCount'];
		itemSale.innerHTML = "<span class=\"dl\">交易量：</span>" + item['sale'];
		itemDesc.innerHTML = item['itemDesc'];
	}
	//fill in the commendations of this item
	function fillCommendItems(items, itemDetail) {
		var recommendate = document.getElementById("detail_recommend_block");
		var title = document.getElementById("detail_recommend_block_title");
		title.innerHTML = "<h1 style=\"font-weight: bold;\">更多商品推荐</h1><a href=\"searchList.jsp?subclassId="
				+ itemDetail['subclass']['subClassId']
				+ "\" style=\"font-weight: bold;\">查看更多</a>"
		for ( var i = 0; i < items[0].length; i++) {
			var oldContent = recommendate.innerHTML.substring(0);
			recommendate.innerHTML = oldContent
					+ "<div class=\"goodsBlock\"><div class=\"goodsPhoto\"><a href=\"productDetail.jsp?itemId="
					+ items[0][i][0]['itemId']
					+ "\"><img src=\"../../source/image/item/"+items[0][i][0]['itemId']+".jpg\"  /></a></div><div class=\"goodsName\"><a href=\"productDetail.jsp?itemId="
					+ items[0][i][0]['itemId']
					+ "\" style=\"font-weight: bold;\">"
					+ items[0][i][0]['itemName'] + "</a></div></div>";
		}
	}
	//fill in the comments of the item
	function fillComments(evaluate) {
		//alert("kkk");
		//alert(evaluate.length);
		//alert(evaluate[0]['evaluateTime']);
		var commentsDiv = document.getElementById("detail_remark_page");
		commentsDiv.innerHTML = "";
		for ( var i = 0; i < evaluate.length; i++) {
			//alert(i);
			var commentsDiv = document.getElementById("detail_remark_page");
			var oldContent = commentsDiv.innerHTML.substring(0);
			//alert(oldContent);
			commentsDiv.innerHTML = oldContent
					+ "<div class=\"detail_one_remark_block\"  name=\"detail_one_remark_block\"><div class=\"detail_remark_leftHead_40\"></div><div class=\"detail_remark_content\"></div></div>";
			//alert(commentsDiv.innerHTML.substring(0));
			var oneCommentBlock = document
					.getElementsByName("detail_one_remark_block")[i];
			var userDiv = oneCommentBlock.getElementsByTagName("div")[0];
			var commentDiv = oneCommentBlock.getElementsByTagName("div")[1];
			userDiv.innerHTML = "<img src=\"../../source/image/head.jpg\"></img><h1>"
					+ evaluate[i]['user']['userName'] + "</h1>";
			var time = "";
			var year = parseInt(evaluate[i]['evaluateTime']['year']) + 1900;
			var month = parseInt(evaluate[i]['evaluateTime']['month']) + 1;
			var date = parseInt(evaluate[i]['evaluateTime']['date']);
			var hour = parseInt(evaluate[i]['evaluateTime']['hours']);
			var minute = parseInt(evaluate[i]['evaluateTime']['minutes']);
			var sec = parseInt(evaluate[i]['evaluateTime']['seconds']);
			time = year + "-" + month + "-" + date + "   " + hour + ":"
					+ minute + ":" + sec;
			commentDiv.innerHTML = "<h1>" + time + "</h1>"
					+ "<div class=\"textContent\"><span>"
					+ evaluate[i]['evaluateContent'] + "</span></div>";
		}
		//alert(commentsDiv.innerHTML.substring(0));
	}
	//comment:the previous page
	function previous_page_btn_click() {
		//var totalPagesNum = document.getElementById("total_comment_pages").value;
		var currentPage = document.getElementById("detail_remark_page").value;
		//alert(currentPage);
		if (currentPage == 0) {
			alert("已是第一页");
		} else {
			var newPage = parseInt(currentPage) - 1;

			//alert(document.getElementById("detail_remark_page").value);
			var userParam = {
				"item.itemId" :
<%=request.getParameter("itemId")%>
	,
				"start" : newPage * numPerPage,
				"limit" : numPerPage
			};
			$
					.ajax({
						url : '../../../UserConsuming/getComments',
						type : 'post',
						data : userParam,
						dataType : 'json',
						success : function(data) {
							fillComments(data['comment']);
							document.getElementById("detail_remark_page").value = newPage;
							//alert("当前页面" + newPage);
						}
					});
		}
	}
	//comment:the next page
	function next_page_btn_click() {
		var totalPagesNum = document.getElementById("total_comment_pages").value;
		var currentPage = document.getElementById("detail_remark_page").value;
		if (currentPage == totalPagesNum) {
			alert("已是最后一页");
		} else {

			//alert(document.getElementById("detail_remark_page").value);
			var newPage = parseInt(currentPage) + 1;
			//alert(document.getElementById("detail_remark_page").value);
			var userParam = {
				"item.itemId" :
<%=request.getParameter("itemId")%>
	,
				"start" : newPage * numPerPage,
				"limit" : numPerPage
			};
			$
					.ajax({
						url : '../../../UserConsuming/getComments',
						type : 'post',
						data : userParam,
						dataType : 'json',
						success : function(data) {
							//alert(newPage);
							fillComments(data['comment']);
							document.getElementById("detail_remark_page").value = newPage;
							//alert("当前页面" + newPage);
						}
					});
		}
	}
</script>
</head>

<body>
	<table width="100%">
		<tr>
			<td><jsp:include page="header.jsp"></jsp:include></td>
		</tr>
		<tr>
			<td>
				<center>
					<!--主内容---->

					<div class="mainContent">
						<div class="Content960">
							<!-- 左侧图片 -->

							<div class="w" id="container">
								<div id="footer" class="w">
									<br /> <br /> <br /> <br /> <br />
								</div>
								<div id="product_intro">
									<div id="imgpreview">
										<div id="spec_n1"></div>
									</div>
									<!-- 简介 -->

									<div id="summary">
										<input type="hidden" id="p_count" value="31" />
										<form name="cartForm" id="productForm">
											<!-- method="post"
											action="#"> -->
											<input id="product_id" name="productid" type="hidden"
												value="375" />
											<h1 id="product_name"></h1>
											<ul>
												<li id="product_remark"></li>
												<br />
												<br />
												<li class="mb10"><span class="dl"
													style="line-height:26px; ">价格：</span><span class="price"
													id="product_price"></span>元</li>
												<li id="product_stock"><span class="dl">库存：</span>
												</li>
												<li id="product_sales"><span class="dl">交易量：</span>
												</li>
												<br />
												<li id="buypanel"><span class="dl"
													style="line-height:26px; ">购买数量：</span>
													<div class="wrapbuynum">
														<a class="btn_reduce" id="btn_reduce"
															onclick="btn_reduce_click();"></a> <input type="text"
															id="buy_num" value="1" name="buy_num"
															style="ime-mode:disabled"
															onkeydown="if(event.keyCode==13)event.keyCode=9"
															onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false"
															onKeyup="limitInput(this);" /> <a class="btn_add"
															id="btn_add" onclick="btn_add_click();"></a>
													</div> <input id="btn_add_cart" name="add_cart" type="button"
													value="加入购物车" onclick="btn_add_cart_click();" /> <input
													id="btn_buy_now" name="add_favorite" type="button"
													value="收藏商品" onclick="btn_add_favorite();" /></li>
											</ul>
										</form>
									</div>
								</div>
								<div class="clear"></div>



								<!-- 商品和评价详情 -->


								<div id="detail_comment_tab">
									<div class="product_tab" id="product_tab"
										style="background-color: #FFFFFF;">
										<a id="product_btn" onclick="product_btn_click();"
											style="cursor: pointer;font-size:14px;">商品详情</a>
									</div>
									<div class="product_tab" id="comment_tab">
										<a id="comment_btn" onclick="comment_btn_click();"
											style="cursor: pointer;font-size:14px;">评价详情</a>
									</div>
									<!-- 商品详情 -->
									<div id="product_detail" style="display:block;">
										<!-- 详细介绍，后台html代码 -->
										<div id="detail_list">
											<ul id="product_desc"></ul>
										</div>
									</div>

									<!--评价信息-->
									<div id="comment_detail" style="display:none;">
										<div id="total_comment_pages" value="0" style="display:none;"></div>
										<div class="detail_remark_page" id="detail_remark_page"
											value="0"></div>
										<div
											style="color:#CDC8B1;font-weight: bold;text-align: center;float:left;">
											<div style="float:left;text-align: center;">

												<a id="previous_page_btn"
													onclick="previous_page_btn_click();"
													style="width:80px;background-color:#FFCC99;border: 1px solid #FFCC99;cursor: pointer;font-size:14px;">上一页</a>


												<a id="next_page_btn" onclick="next_page_btn_click();"
													style="width:80px;background-color:#FFCC99;border: 1px solid #FFCC99;cursor: pointer;font-size:14px;">下一页</a>
											</div>
										</div>
									</div>
									<!--评价信息END-->
								</div>
							</div>


							<!--商品推荐块-->
							<div class="detail_recommend_block" id="detail_recommend_block">
								<div class="detail_recommend_block_title"
									id="detail_recommend_block_title"></div>

							</div>
							<!--商品推荐块END-->



						</div>
						<!--中间960px块结束-->



					</div>
					<!--主内容结束-->


				</center>
			</td>
		</tr>
		<tr>
			<td><jsp:include page="footer.jsp"></jsp:include></td>
		</tr>


	</table>


</body>
</html>

