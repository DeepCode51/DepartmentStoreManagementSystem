<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.news-list ul li {
	
}
</style>

<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
		type="text/javascript"></script>
<script>
	$(function () {
		$.post("${pageContext.request.contextPath}/news",{"param":"queryNews"},function (data) {
			var $ul = $(".ul");
		    for (var i = 0;i < data.length;i++){
			    //获取结果集中的新闻
			    var $index = data[i];
			    var $li = $("<li><a href='${pageContext.request.contextPath}/news_view.jsp?id="+$index.id+"'>"+$index.title+"</li>");
			    $ul.append($li);
			}
        },"json")
    })
</script>
<div class="newsList">
	<h2>新闻动态</h2>
	<ul class="ul">
	<%--<li><a href="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>--%>
	</ul>
</div>
