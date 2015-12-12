<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>个人中心</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="pages/css/user/bootstrap.css"
	type="text/css"></link>
<script type="text/javascript" src="pages/js/user/jquery-2.0.1.js"></script>
<script type="text/javascript" src="pages/js/user/bootstrap.js"></script>
</head>


<body>
	<div class="span12">
		<div class="tabbable" id="tabs-108187">
			<!-- Only required for left/right tabs -->
			<ul class="nav nav-tabs">
				<li class="active"><a contenteditable="true" data-toggle="tab"
					href="#panel-458626">第一部分</a>
				</li>
				<li><a contenteditable="true" data-toggle="tab"
					href="#panel-970473">第二部分</a>
				</li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane active" contenteditable="true"
					id="panel-458626">
					<p>第一部分内容.</p>
				</div>

				<div class="tab-pane" contenteditable="true" id="panel-970473">
					<p>第二部分内容.</p>
				</div>
			</div>
		</div>
</body>
</html>
