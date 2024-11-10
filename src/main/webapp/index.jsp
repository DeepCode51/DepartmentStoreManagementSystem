
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>XXX-首页</title>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/index.js"></script>

	<script>
		$(function () {
			go(1,12);

        });

		//分页函数
		function go(currentPage, PageSize) {
			$.post("${pageContext.request.contextPath}/Product",{
			    "param":"paging",
				"currentPage":currentPage,
				"PageSize":PageSize},function (data) {
                var $ul = $("#product2");
                $ul.empty();
			    //遍历当前页面的结果集
                for (var i = 0; i < data.paging.list.length; i++) {
                    var $element = data.paging.list[i];
                    //创建商品图片
                    var $dt = $("<dt><a href='${pageContext.request.contextPath}/product_view.jsp?id="+$element.id+"' target='_self'><img  src='"+$element.img_source+"'/></a></dt>");
                    //创建商品名称
                    var $dd1 = $("<dd class='title'><a href='${pageContext.request.contextPath}/product_view.jsp?id="+$element.id+"'target='_self'>"+$element.name+"</a></dd>");
                    //创建商品价格
                    var $dd2 = $("<dd class='price'>￥"+$element.price+"</dd>");


                    var $dl = $("<dl></dl>");
                    var $li = $("<li></li>");
                    $dl.append($dt);
                    $dl.append($dd1);
                    $dl.append($dd2);
                    $li.append($dl);
                    $ul.append($li);

                }

                $a = $(".btn>a");
                $a.eq(0).attr("href", "javascript:go('" + 1 + "','" + PageSize + "')");// showMsgs('1','5')
                $a.eq(1).attr("href", "javascript:go('" + data.paging.topPage + "','" + PageSize + "')");
                $a.eq(2).attr("href", "javascript:go('" + data.paging.nextPage + "','" + PageSize + "')");
                $a.eq(3).attr("href", "javascript:go('" + data.paging.totalPage + "','" + PageSize + "')");
                $span = $(".btn>span");
                $span.eq(0).html(data.paging.currentPage);
                $span.eq(1).html(data.paging.totalPage);
            },"json")
        }
	</script>
</head>
<body>
	<%@ include file="index_top.jsp"%>
	<div id="middle">
		<div class="p_left">
			<!--商品分类-->
			<%@ include file="index_product_sort.jsp"%>
			<!--商品分类结束-->
			<%--最近浏览--%>
			<%@include file="index_pre_look.jsp"%>
			<%--最近浏览结束--%>
		</div>

		<div class="p_center">
			<div id="wrapper">
				<div id="focus">
					<ul>
						<li><a href="#"><img src="images/T1.jpg" style="width: 520px; height: 280px"/></a></li>
						<li><a href="#"><img src="images/T2.jpg" style="width: 520px; height: 280px"/></a></li>
						<li><a href="#"><img src="images/T3.jpg" style="width: 520px; height: 280px"/></a></li>
						<li><a href="#"><img src="images/T4.jpg" style="width: 520px; height: 280px"/></a></li>
						<li><a href="#"><img src="images/T5.jpg" style="width: 520px; height: 280px"/></a></li>
					</ul>
				</div>
			</div>
			<div class="p_list">
				<div class="p_info">
					<img src="images/icon_sale.png" />商品列表
				</div>
			</div>

			<ul class="product2" id="product2">
					<%--<li>
						<dl>
							<dt>
								<a href="" target="_self">
								<img src="" /></a>
							</dt>
							<dd class="title">
								<a href="" target="_self"></a>
							</dd>
							<dd class="price">￥</dd>
						</dl>
					</li>--%>
			</ul>
		</div>

		<div id="p_right">
			<%@ include file="index_news.jsp"%>
			<%@ include file="hotproduct.jsp"%>
		</div>
	</div>

    <div align="center" style="margin-top:10px" class="btn">
        <a href="#">首页</a>&nbsp;&nbsp;&nbsp;
        <a href="#">上一页</a>&nbsp;&nbsp;
        <a href="#">下一页</a>&nbsp;&nbsp;
        <span></span>/<span></span>&nbsp;&nbsp;
        <a href="#">最后一页</a>
    </div>

	<div id="foot">Copyright © 2018 上海浦软 All Rights Reserved.</div>
</body>
</html>

