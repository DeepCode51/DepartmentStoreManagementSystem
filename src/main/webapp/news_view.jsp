<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>XXX - 新闻显示</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/method.jsp"></script>
	<%@ include file="method.jsp" %>
	<script>
		var $newId;
		$(function () {
            $newId = getUrlParam('id');//获取新闻id


			$.post("${pageContext.request.contextPath}/news",{"param":"queryNewById","id":$newId},function (data) {
				var $news = $("#news");
			    var $h1 = $("<h1>"+data.title+"</h1>");
				var $p = $("<p style='text-align: right;'>创建时间："+data.time+"</p>");
				var $div = $("<div class='content'>内容："+data.content+"</div>");

				$div.append($p);
				$news.append($h1);
				$news.append($div);
            },"json")
        });


	</script>
</head>
<body>
<%@ include file="index_top.jsp"  %>
<div id="position" class="wrap">
	您现在的位置：<a href="ProductServlet">XXX</a> &gt; 阅读新闻
</div>
<div id="main" class="wrap">
	<div class="left-side">
		<%@ include file="index_news.jsp" %>
	</div>
	<div id="news" class="right-main">
		<%--<h1>标题:</h1>
		<div class="content">
			<p style="text-align: right;">创建时间：</p>
			内容：
		</div>--%>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2018 上海浦软 All Rights Reserved.
</div>
</body>
</html>

