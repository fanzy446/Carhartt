<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>header</title>

<link rel="stylesheet" href="/Carhartt/pages/css/user/global_affect.css"
	type="text/css"></link>
<link rel="stylesheet" href="/Carhartt/pages/css/user/header.css" type="text/css"></link>
<script type="text/javascript">
	function searchKeywords() {
		var keywords = document.getElementById("keywords").value;
		//alert(keywords);
		window.location.href = "/Carhartt/pages/jsp/user/searchList.jsp?keyword="+keywords;
	}
	
	function logout(){
		$.ajax({
				url : '/Carhartt/UserBasic/userLogout',
				type : 'post',
				dataType : 'json',
				success : function(data) {
				   //alert("jjj");
					window.location.href = "/Carhartt/pages/jsp/user/login.jsp";
				}
			});
	}
</script>
</head>

<body>
	<!--网站头部-->
	<div class="header">
		<div class="header_middle">
			<div class="inheader">
				<%
					if (session.getAttribute("userId") != null) {
				%>
				<a href="cart.jsp" class="headerFont">购物车</a> <a href="/Carhartt/pages/jsp/user/user_center_per_info.jsp"
					class="headerFont"><%=session.getAttribute("userName")%></a> <a
					class="headerFont" href="javascript:;" onclick="logout();">退出</a>
				<%
					} else {
				%>

				<a href="/Carhartt/pages/jsp/user/login.jsp" class="headerFont">登陆</a> <a href="/Carhartt/pages/jsp/user/register.jsp"
					class="headerFont">注册</a>
				<%
					}
				%>


			</div>
		</div>
	</div>
	<!--网站头部结束-->

	<!--导航栏-->
	<div class="navigationHeader">
		<div class="navigtionContent">

			<!--LOG块-->
			<div class="logo">
				<img src="/Carhartt/pages/source/image/logo.png" />
			</div>

			<!--导航块-->
			<div class="navigation">
				<ul class="nav_list">
					<li class="nav_item"><a href="/Carhartt/pages/jsp/user/index.jsp">首页</a>
					</li>
					<li class="nav_item"><a href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=1">图书</a>
					</li>
					<li class="nav_item"><a href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=3">数码</a>
					</li>
					<li class="nav_item"><a href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=2">食品</a>
					</li>
					<li class="nav_item2"><a href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=4">美容护理</a>
					</li>
					<li class="nav_item2"><a href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=5">厨卫清洁</a>
					</li>
					<li class="nav_item2"><a href="/Carhartt/pages/jsp/user/searchList.jsp?broadClassId=6">生活电器</a>
					</li>
				</ul>
			</div>

			<!--搜索块-->
			<div class="search">
				<input type="text" class="searchInput" id="keywords" /> <a
					class="searchButton" onclick="searchKeywords(); " style="cursor:pointer;"  >搜索</a>
			</div>
			<!--导航栏结束-->
		</div>
	</div>
</body>
</html>
