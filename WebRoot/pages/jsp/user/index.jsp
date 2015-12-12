<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>首页</title>
		<link rel="stylesheet" type="text/css"
			href="/Carhartt/pages/css/user/mystyle-index.css" />
		<link type="text/css" rel="stylesheet"
			href="/Carhartt/pages/css/user/style1.css" />
		<script type="text/javascript" src="/Carhartt/pages/js/user/jquery.js"></script>
		<script type="text/javascript"
			src="/Carhartt/pages/js/user/mainPage.js"></script>
		<script type="text/javascript"
			src="/Carhartt/pages/js/user/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
	//var adSize = 3;
	///控制欢迎图片轮播
	var pic = 1;
	window.onload=function(){
	var para={"kind": 0};
	$.ajax({
		url : '/Carhartt/PageShow/showIndex',
		type : 'post',
		data:para,
		dataType : 'json',
		success : function(data) {
			var classes = data['Class'];
			var advertisement = data['Advertisement'];
			var announcement = data['Announcement'];
			$.each(classes, function(i, broadclass) {
				//alert(broadclass.broadClassName)	;		
				displaySubclasses(broadclass.broadClassName,
						broadclass.subclasses);
				displayItem(broadclass.broadClassName, broadclass.items);
			});
			//alert(advertisement.length);
			 if(advertisement.length!=0){
			displayAd(advertisement);
			} else{
			document.getElementById("welcome1").src="/Carhartt/pages/source/image/welcome1.jpg";
			}
		},
		error : function() {
		}
	})};
	window.setInterval(changeWelcome, 4000);
	function changeWelcome() {

		HiddenId = "#wel" + pic;
		addPic();
		showId = "#wel" + pic;
		$(HiddenId).fadeOut(600);
		$(showId).fadeIn(1200);

	}

	function addPic() {
		pic++;
		if (pic > 5) {
			pic = 1;
		}
	}
	/*  $(document)
			.ready(
					function() {
					 var name="图书";
					 displaySubclasses(name);
					});  */
	//display the subclasses
	function displaySubclasses(bigClassName, subclasses) {
		switch (bigClassName) {
		case '图书':
			//alert("fushi");
			for ( var i = 0; i < subclasses.length; i++) {
				var typeDiv = document.getElementById("clothes_types_add");
				var oldContent = typeDiv.innerHTML.substring(0);
				//alert(oldContent);
				typeDiv.innerHTML = oldContent
						+ "<a href=\"/Carhartt/pages/jsp/user/searchList.jsp?subclassId="
						+ subclasses[i]['subClassId']
						+ "\" style=\"font-weight: bold;\" >"
						+ subclasses[i]['subClassName'] + "</a>";
			}
			break;
		case '食品':
			//alert("food");
			for ( var i = 0; i < subclasses.length; i++) {
				var typeDiv = document.getElementById("food_types_add");
				var oldContent = typeDiv.innerHTML.substring(0);
				//alert(oldContent);
				typeDiv.innerHTML = oldContent
						+ "<a href=\"/Carhartt/pages/jsp/user/searchList.jsp?subclassId="
						+ subclasses[i]['subClassId']
						+ "\" style=\"font-weight: bold;\" >"
						+ subclasses[i]['subClassName'] + "</a>";
			}
			break;
		case '数码':
			//alert("digital");
			for ( var i = 0; i < subclasses.length; i++) {
				var typeDiv = document.getElementById("digital_types_add");
				var oldContent = typeDiv.innerHTML.substring(0);
				//alert(oldContent);
				typeDiv.innerHTML = oldContent
						+ "<a href=\"/Carhartt/pages/jsp/user/searchList.jsp?subclassId="
						+ subclasses[i]['subClassId']
						+ "\" style=\"font-weight: bold;\" >"
						+ subclasses[i]['subClassName'] + "</a>";
			}
			break;
		case '美容护理':
			//alert("fushi");		
			for ( var i = 0; i < subclasses.length; i++) {
				var typeDiv = document.getElementById("huli_types_add");
				var oldContent = typeDiv.innerHTML.substring(0);
				//alert(oldContent);
				typeDiv.innerHTML = oldContent
						+ "<a href=\"/Carhartt/pages/jsp/user/searchList.jsp?subclassId="
						+ subclasses[i]['subClassId']
						+ "\" style=\"font-weight: bold;\" >"
						+ subclasses[i]['subClassName'] + "</a>";
			}
			break;
		case '厨卫清洁':
			//alert("chuwei");
			for ( var i = 0; i < subclasses.length; i++) {
				var typeDiv = document.getElementById("clean_types_add");
				var oldContent = typeDiv.innerHTML.substring(0);
				//alert(oldContent);
				typeDiv.innerHTML = oldContent
						+ "<a href=\"/Carhartt/pages/jsp/user/searchList.jsp?subclassId="
						+ subclasses[i]['subClassId']
						+ "\" style=\"font-weight: bold;\" >"
						+ subclasses[i]['subClassName'] + "</a>";
			}
			break;
		case '生活电器':
			//alert("dianqi");
			for ( var i = 0; i < subclasses.length; i++) {
				var typeDiv = document.getElementById("dianqi_types_add");
				var oldContent = typeDiv.innerHTML.substring(0);
				//alert(oldContent);
				typeDiv.innerHTML = oldContent
						+ "<a href=\"/Carhartt/pages/jsp/user/searchList.jsp?subclassId="
						+ subclasses[i]['subClassId']
						+ "\" style=\"font-weight: bold;\" >"
						+ subclasses[i]['subClassName'] + "</a>";
			}
			break;
		//alert("hh");

		}
	}

	//display the item
	//var item=new Array();
	//item={'itemId':itemid,'itemName':itemName,}
	function displayItem(bigClassName, item) {
		switch (bigClassName) {
		case '图书':
			//alert("fushi");
			//alert(item.length);
			for ( var i = 0; i < item.length; i++) {
				var itemUL = document.getElementById("clothes_items");
				//alert("hhhh");
				var oldContent = itemUL.innerHTML.substring(0);
				//alert("hhhh");
				//alert(oldContent);
				var liID="clothes_item_"+parseInt(i+1);
				//alert(liID);
				itemUL.innerHTML = oldContent
						+ "<li id=\""+liID+"\"><div class=\"p_wrapper\" ><div class=\"p_img\"></div><div class=\"p_name\"></div><div class=\"p_price\"></div></div></li>";
				//alert(oldContent);
				
				var li=document.getElementById(liID);
				var imgDiv = li.getElementsByTagName("div")[1];
				var nameDiv = li.getElementsByTagName("div")[2];
				var priceDiv = li.getElementsByTagName("div")[3];
				//alert(oldContent);
				imgDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId']
						+ "\"><img src=\"/Carhartt/pages/source/image/item/"+item[i]['itemId']+".jpg\" /> </a>";
					var name=item[i]['itemName'];
						
						if(name.length<10){
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + item[i]['itemName']
						+ "</a>";
						}else{
						//alert(name.substr(0,15));
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + name.substr(0,9)
						+ "...</a>";
						}
				priceDiv.innerHTML = "¥ " + item[i]['itemPrice'];
			}
			break;
		case '食品':
			//alert("food");
			for ( var i = 0; i < item.length; i++) {
				var itemUL = document.getElementById("food_items");
				//alert("hhhh");
				var oldContent = itemUL.innerHTML.substring(0);
				//alert("hhhh");
				//alert(oldContent);
				var liID="food_item_"+parseInt(i+1);
				//alert(liID);
				itemUL.innerHTML = oldContent
						+ "<li id=\""+liID+"\"><div class=\"p_wrapper\" ><div class=\"p_img\"></div><div class=\"p_name\"></div><div class=\"p_price\"></div></div></li>";
				//alert(oldContent);
				
				var li=document.getElementById(liID);
				var imgDiv = li.getElementsByTagName("div")[1];
				var nameDiv = li.getElementsByTagName("div")[2];
				var priceDiv = li.getElementsByTagName("div")[3];
				//alert(oldContent);
				imgDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId']
						+ "\"><img src=\"/Carhartt/pages/source/image/item/"+item[i]['itemId']+".jpg\" /> </a>";
					var name=item[i]['itemName'];
						
						if(name.length<10){
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + item[i]['itemName']
						+ "</a>";
						}else{
						//alert(name.substr(0,15));
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + name.substr(0,9)
						+ "...</a>";
						}
				priceDiv.innerHTML = "¥ " + item[i]['itemPrice'];
			}
			break;
		case '数码':
			//alert("digital");
			for ( var i = 0; i < item.length; i++) {
				var itemUL = document.getElementById("digital_items");
				//alert("hhhh");
				var oldContent = itemUL.innerHTML.substring(0);
				//alert("hhhh");
				//alert(oldContent);
				var liID="digital_item_"+parseInt(i+1);
				//alert(liID);
				itemUL.innerHTML = oldContent
						+ "<li id=\""+liID+"\"><div class=\"p_wrapper\" ><div class=\"p_img\"></div><div class=\"p_name\"></div><div class=\"p_price\"></div></div></li>";
				//alert(oldContent);
				
				var li=document.getElementById(liID);
				var imgDiv = li.getElementsByTagName("div")[1];
				var nameDiv = li.getElementsByTagName("div")[2];
				var priceDiv = li.getElementsByTagName("div")[3];
				//alert(oldContent);
				imgDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId']
						+ "\"><img src=\"/Carhartt/pages/source/image/item/"+item[i]['itemId']+".jpg\" /> </a>";
						var name=item[i]['itemName'];
						
						if(name.length<10){
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + item[i]['itemName']
						+ "</a>";
						}else{
						//alert(name.substr(0,15));
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + name.substr(0,9)
						+ "...</a>";
						}
				
				priceDiv.innerHTML = "¥ " + item[i]['itemPrice'];
			}
			break;
		case '美容护理':
			//alert("huli");
			for ( var i = 0; i < item.length; i++) {
				var itemUL = document.getElementById("huli_items");
				//alert("hhhh");
				var oldContent = itemUL.innerHTML.substring(0);
				//alert("hhhh");
				//alert(oldContent);
				var liID="huli_item_"+parseInt(i+1);
				//alert(liID);
				itemUL.innerHTML = oldContent
						+ "<li id=\""+liID+"\"><div class=\"p_wrapper\" ><div class=\"p_img\"></div><div class=\"p_name\"></div><div class=\"p_price\"></div></div></li>";
				//alert(oldContent);
				
				var li=document.getElementById(liID);
				var imgDiv = li.getElementsByTagName("div")[1];
				var nameDiv = li.getElementsByTagName("div")[2];
				var priceDiv = li.getElementsByTagName("div")[3];
				//alert(oldContent);
				imgDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId']
						+ "\"><img src=\"/Carhartt/pages/source/image/item/"+item[i]['itemId']+".jpg\" /> </a>";
					var name=item[i]['itemName'];
						
						if(name.length<10){
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + item[i]['itemName']
						+ "</a>";
						}else{
						//alert(name.substr(0,15));
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + name.substr(0,9)
						+ "...</a>";
						}
				priceDiv.innerHTML = "¥ " + item[i]['itemPrice'];
			}
			break;
		case '厨卫清洁':
			//alert("chuwei");
			for ( var i = 0; i < item.length; i++) {
				var itemUL = document.getElementById("clean_items");
				//alert("hhhh");
				var oldContent = itemUL.innerHTML.substring(0);
				//alert("hhhh");
				//alert(oldContent);
				var liID="clean_item_"+parseInt(i+1);
				//alert(liID);
				itemUL.innerHTML = oldContent
						+ "<li id=\""+liID+"\"><div class=\"p_wrapper\" ><div class=\"p_img\"></div><div class=\"p_name\"></div><div class=\"p_price\"></div></div></li>";
				//alert(oldContent);
				
				var li=document.getElementById(liID);
				var imgDiv = li.getElementsByTagName("div")[1];
				var nameDiv = li.getElementsByTagName("div")[2];
				var priceDiv = li.getElementsByTagName("div")[3];
				//alert(oldContent);
				imgDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId']
						+ "\"><img src=\"/Carhartt/pages/source/image/item/"+item[i]['itemId']+".jpg\" /> </a>";
					var name=item[i]['itemName'];
						
						if(name.length<10){
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + item[i]['itemName']
						+ "</a>";
						}else{
						//alert(name.substr(0,15));
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + name.substr(0,9)
						+ "...</a>";
						}
				priceDiv.innerHTML = "¥ " + item[i]['itemPrice'];
			}
			break;
		case '生活电器':
			//alert("dianqi");
			for ( var i = 0; i < item.length; i++) {
				var itemUL = document.getElementById("dianqi_items");
				//alert("hhhh");
				var oldContent = itemUL.innerHTML.substring(0);
				//alert("hhhh");
				//alert(oldContent);
				var liID="dianqi_item_"+parseInt(i+1);
				//alert(liID);
				itemUL.innerHTML = oldContent
						+ "<li id=\""+liID+"\"><div class=\"p_wrapper\" ><div class=\"p_img\"></div><div class=\"p_name\"></div><div class=\"p_price\"></div></div></li>";
				//alert(oldContent);
				
				var li=document.getElementById(liID);
				var imgDiv = li.getElementsByTagName("div")[1];
				var nameDiv = li.getElementsByTagName("div")[2];
				var priceDiv = li.getElementsByTagName("div")[3];
				//alert(oldContent);
				imgDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId']
						+ "\"><img src=\"/Carhartt/pages/source/image/item/"+item[i]['itemId']+".jpg\" /> </a>";
					var name=item[i]['itemName'];
						
						if(name.length<10){
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + item[i]['itemName']
						+ "</a>";
						}else{
						//alert(name.substr(0,15));
						nameDiv.innerHTML = "<a href=\"/Carhartt/pages/jsp/user/productDetail.jsp?itemId="
						+ item[i]['itemId'] + "\">" + name.substr(0,9)
						+ "...</a>";
						}
				priceDiv.innerHTML = "¥ " + item[i]['itemPrice'];
			}
			break;
		//

		}
	}
	//display the ads
	function displayAd(ads) {
	document.getElementById("welcome1").src="/Carhartt/pages/source/image/welcome1.jpg";
	for(var i=0;i<ads.length;i++){
	var pos=ads[i]['position'];
	var imgId="welcome"+pos;
	var img=document.getElementById(imgId);
	//alert(imgId);
	if(ads[i]['adinadps'] != null)
	{
	img.getElementsByTagName("img")[0].src="/Carhartt/pages/source/image/advertisement/"+ads[i]['adinadps'][0]['advertisement']['advertisementId']+".jpg";
	img.href=ads[i]['adinadps'][0]['advertisement']['url'];
	//alert(img.getElementsByTagName("img")[0].src);
	}
	}
	}
	//convert to the searchList of the sub class 
	function convertToSubItems(subClassId) {
		alert(subClassId);
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
					<center>

						<!--主内容---->
						<div class="mainContent">
							<!----布告栏------>
							<div class="welcome">

								<a id="welcome1" href=""><img width="960" height="370"
										class="welcomeImg" id="wel1" src="" /> </a><a id="welcome2"
									href=""> <img width="960" height="370" class="welcomeImg"
										id="wel2" style="display: none;"
										src="/Carhartt/pages/source/image/welcome2.jpg" alt="" /> </a><a
									id="welcome3" href=""><img width="960" height="370"
										class="welcomeImg" id="wel3" style="display: none"
										src="/Carhartt/pages/source/image/welcome3.jpg" alt="" /> </a><a
									id="welcome4" href=""><img width="960" height="370"
										class="welcomeImg" id="wel4" style="display: none"
										src="/Carhartt/pages/source/image/welcome4.jpg" alt="" /> </a><a
									id="welcome5" href=""><img width="960" height="370"
										class="welcomeImg" id="wel5" style="display: none"
										src="/Carhartt/pages/source/image/welcome5.jpg" alt="" /> </a>
								<div class="welcomeWord">
									<h1>
										欢迎来到Carhartt
									</h1>
									<a href="login.jsp">登陆Carhartt</a>
								</div>
							</div>

							<!-- 推荐商品从这里开始 -->
							<div class="clear"></div>
							<div class="w" id="container">
								<!-- 图书推荐BEGIN -->
								<div class="product_wrapper" id="commend">
									<div class="title" id="clothes_types">
										<h3 class="left">
											图书
										</h3>
										<div class="title_right">
											<a
												href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=1"
												title="更多"> >>更多 </a>
										</div>
										<div class="types" id="clothes_types_add"></div>
									</div>
									<div class="product_lst clearfix">
										<ul id="clothes_items"></ul>

									</div>
								</div>
								<!-- 数码商品推荐END -->
								<!-- 广告位1 -->
								<div class="product_wrapper">
									<div class="title">
										<a href="" id="welcome6"><img width="960" height="80"
												src="/Carhartt/pages/source/image/add.jpg" /> </a>
									</div>
								</div>
								<!-- 美食饮料推荐BEGIN -->
								<div class="product_wrapper" id="commend">
									<div class="title" id="digital_types">
										<h3 class="left">
											数码
										</h3>
										<div class="title_right">
											<a
												href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=3"
												title="更多"> >>更多 </a>
										</div>
										<div class="types" id="digital_types_add"></div>
									</div>
									<div class="product_lst clearfix">
										<ul id="digital_items">
										</ul>
									</div>
								</div>
								<!-- 美食饮料推荐END -->
								<!-- 广告位1 -->
								<div class="product_wrapper">
									<div class="title">
										<a href="" id="welcome7"><img width="960" height="80"
												src="/Carhartt/pages/source/image/add.jpg" /> </a>
									</div>
								</div>
								<!-- 美容护理推荐BEGIN -->
								<div class="product_wrapper" id="commend">
									<div class="title" id="food_types">
										<h3 class="left">
											食品
										</h3>
										<div class="title_right">
											<a
												href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=2"
												title="更多"> >>更多 </a>
										</div>
										<div class="types" id="food_types_add"></div>
									</div>
									<div class="product_lst clearfix">
										<ul id="food_items"></ul>
									</div>
								</div>
								<!-- 美容护理推荐END -->
								<!-- 广告位1 -->
								<div class="product_wrapper">
									<div class="title">
										<a href="" id="welcome8"><img width="960" height="80"
												src="/Carhartt/pages/source/image/add.jpg" /> </a>
									</div>
								</div>
								<!-- 厨卫清洁推荐BEGIN -->
								<div class="product_wrapper" id="commend">
									<div class="title" id="huli_types">
										<h3 class="left">
											美容护理
										</h3>
										<div class="title_right">
											<a
												href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=4"
												title="更多"> >>更多 </a>
										</div>
										<div class="types" id="huli_types_add"></div>
									</div>
									<div class="product_lst clearfix">
										<ul id="huli_items">
										</ul>
									</div>
								</div>
								<!-- 厨卫清洁推荐END -->
								<!-- 广告位1 -->
								<div class="product_wrapper">
									<div class="title">
										<a href="" id="welcome9"><img width="960" height="80"
												src="/Carhartt/pages/source/image/add.jpg" /> </a>
									</div>
								</div>
								<!-- 家居家纺推荐BEGIN -->
								<div class="product_wrapper" id="commend">
									<div class="title" id="clean_types">
										<h3 class="left">
											厨卫清洁
										</h3>
										<div class="title_right">
											<a
												href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=5"
												title="更多"> >>更多 </a>
										</div>
										<div class="types" id="clean_types_add"></div>
									</div>
									<div class="product_lst clearfix">
										<ul id="clean_items"></ul>
									</div>
								</div>
								<!-- 家居家纺推荐END -->
								<!-- 广告位1 -->
								<div class="product_wrapper">
									<div class="title">
										<a href="" id="welcome10"><img width="960" height="80"
												src="/Carhartt/pages/source/image/add.jpg" /> </a>
									</div>
								</div>
								<!-- 母婴用品推荐BEGIN -->
								<div class="product_wrapper" id="commend">
									<div class="title" id="dianqi_types">
										<h3 class="left">
											生活电器
										</h3>
										<div class="title_right">
											<a
												href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=6"
												title="更多"> >>更多 </a>
										</div>
										<div class="types" id="dianqi_types_add"></div>
									</div>
									<div class="product_lst clearfix">
										<ul id="dianqi_items"></ul>
									</div>
								</div>
								<!-- 母婴用品推荐END -->
							</div>
							<!--main Content END-->
						</div>
					</center>

					<div class="toTop" onclick="backToTop()" style="display: none;"></div>
				</td>
			</tr>
			<tr>
				<td><jsp:include page="footer.jsp"></jsp:include></td>
			</tr>
		</table>


	</body>
</html>