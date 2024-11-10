<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>XXX - 注册结果</title>
	<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
	<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
</head>
<body>
<%@ include file="index_top.jsp" %>
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎注册XXX</h1>
			<ul class="steps clearfix">
				<li class="finished"><em></em>填写注册信息</li>
				<li class="last-current"><em></em>注册成功</li>
			</ul>
			<div class="msg">
				<p>恭喜：注册成功！</p>
				<p>正在进入登陆页面...</p>
				<script type="text/javascript">
                    setTimeout("location.href='login.jsp'", 3000);
				</script>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2018 上海浦软 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
