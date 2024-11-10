<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>--%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>XXX - 产品列表</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
	<link type="text/css" rel="stylesheet" href="css/index.css" />
<script type="text/javascript" src="scripts/function.js"></script>
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
	<%@ include file="method.jsp" %>
	<script>
		$(function () {
			//获取id
			var $id = getUrlParam('id');
			$.post("${pageContext.request.contextPath}/proCategory",{"param":"querySonId","id":$id},function (data) {
			    var $position = $("#position");
                var $posA;
                //设置分类导航页
			    if (data.sonName==""){
                    $posA = $("<a href='${pageContext.request.contextPath}/index.jsp'>XXX</a> > <a href='${pageContext.request.contextPath}/product-list.jsp?id="+data.parId+"'>" + data.parentName + "</a>");
				} else {
                    $posA = $("<a href='${pageContext.request.contextPath}/index.jsp'>XXX</a> > <a href='${pageContext.request.contextPath}/product-list.jsp?id="+data.parId+"'>" + data.parentName + "</a> > <a href='${pageContext.request.contextPath}/product-list.jsp?id="+data.sonId+"'>" + data.sonName + "</a>");
                }
                $position.append($posA);


			    //设置商品页面
			    var $ul = $("#productList");
                /*$ul.empty();*/
			    for (var i = 0; i < data.sonList.length; i++) {
                    var $element = data.sonList[i];
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


            },"json")
        });

		
	</script>
</head>
<body>
	<%@ include file="index_top.jsp"%>
	<div id="position" class="wrap">
		您现在的位置：<%--<a href='ProductServlet'>XXX</a> &gt; <a href='product-list.jsp'></a> &gt; &ndash;%&gt;--%>
	</div>
	<div id="main" class="wrap">
		<div class="lefter">
			<%--导入商品分类--%>
			<%@ include file="index_product_sort.jsp"%>
			<div class="spacer"></div>
			<div class="last-view">
				<%--导入最近浏览--%>
				<%@include file="index_pre_look.jsp"%>

			</div>
		</div>
		<div class="main">
			<div class="product-list">
				<h2>全部商品</h2>
				<div class="clear"></div>
				<ul class="product2" id="productList">

					<%--<li>
						<dl>
							<dt>
								<a href="#" target="_self"><img src="images/product/0.jpg" /></a>
							</dt>
							<dd class="title">
								<a href="#" target="_self">商品名称</a>
							</dd>
							<dd class="price">￥12.34</dd>
						</dl>
					</li>

					<li>
						<dl>
							<dt>
								<a href="#" target="_self"><img src="images/product/0.jpg" /></a>
							</dt>
							<dd class="title">
								<a href="#" target="_self">商品名称</a>
							</dd>
							<dd class="price">￥12.34</dd>
						</dl>
					</li>--%>


				</ul>
				<div class="clear"></div>
				<div class="pager">
                    <%--<div align="center" style="margin-top:10px" id="btn">
                        <a href="#">首页</a>&nbsp;&nbsp;&nbsp;
                        <a href="#">上一页</a>&nbsp;&nbsp;
                        <a href="#">下一页</a>&nbsp;&nbsp;
                        <span></span>/<span></span>&nbsp;&nbsp;
                        <a href="#">最后一页</a>
                    </div>--%>
					<%--<ul class="clearfix">
						<li><a href="#">上一页</a></li>
						<li>2</li>


						<li><a href="#">3</a></li>


						<li><a href="#">下一页</a></li>
					</ul>--%>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2018 上海浦软 All Rights Reserved.
		京ICP证1000001号</div>
</body>
</html>

