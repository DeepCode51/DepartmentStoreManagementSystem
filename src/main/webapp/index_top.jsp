
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	double num = Math.random();
%>
<script>

	$(function () {
        //绑定点击事件
        $("#query_button").click(function () {
            //获取搜索的值
            var $qname = $("#qname").val();
			window.location = "${pageContext.request.contextPath}/queryproduct-list.jsp?qname="+$qname+"";
        })
    })
</script>
<div id="header">
	<div class="login_menu">
		<div class="login_container">
			<ul class="m_right">
				<li><img src="images/icon_3.png"><a
					href="${pageContext.request.contextPath}/shopCar?param=show" class="c_red">购物车</a></li>
				<li><img src="images/icon_4.png"><a
					href="${pageContext.request.contextPath}/doBuy?param=show">我的订单</a></li>
				<li><img src="images/icon_2.png"><a href="guestbook.jsp">留言</a></li>
				<li><img src="images/icon_1.png"><a href="index.jsp">首页</a></li>
			</ul>

			<ul class="m_left">
				<c:choose>
					<c:when test="${empty sessionScope.user}">
						<li><a href="login.jsp" class="c_red">请登录</a>&nbsp;&nbsp;&nbsp;</li>
						<li><a href="register.jsp">请注册</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/index.jsp" class="c_red">欢迎!&nbsp;&nbsp;${user.uname}</a>&nbsp;&nbsp;&nbsp;</li>
						<li><a href="${pageContext.request.contextPath}/user?param=exit">退出</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
	<div class="logo_search">
		<div class="logo">
			<img src="images/logo.png">
		</div>
		<div class="search">
			<input type="text" placeholder="输入宝贝" id="qname" />
			<button class="query_button"  id="query_button">搜索</button>
		</div>
	</div>
	<div class="nav_bar">
		<div class="nav_bar_container">
			<ul>
				<li><a href="${pageContext.request.contextPath}/product-list.jsp?id=1">图音</a></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<li><a href="${pageContext.request.contextPath}/product-list.jsp?id=2">百货</a></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<li><a href="${pageContext.request.contextPath}/product-list.jsp?id=22">团购</a></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<li><a href="${pageContext.request.contextPath}/product-list.jsp?id=21">品牌</a></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</ul>
		</div>
	</div>
</div>
