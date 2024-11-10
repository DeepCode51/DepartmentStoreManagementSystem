<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="java/script" src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"></script>
<script>
	$(function () {
		$.post("${pageContext.request.contextPath}/Product",{"param":"queryMaxProduct"},function (data) {
			/*alert(JSON.stringify(data));*/
		    //循环热卖商品结果集
		    for (var i = 0; i <data.list.length;i++){

		        var $element = data.list[i];
		        //创建商品图片
		        var $dt = $("<dt><a href='${pageContext.request.contextPath}/product_view.jsp?id="+$element.id+"' target='_self'><img src='"+$element.img_source+"'/></a></dt>");
		        //创建商品名称
				var $dd1 = $("<dd class='p_name'><a href='${pageContext.request.contextPath}/product_view.jsp?id="+$element.id+"' target='_self'>"+$element.name+"</a></dd>");
				//创建商品价格
				var $dd2 = $("<dd class='price'>￥"+$element.price+"</dd>");


				var $dl = $("<dl></dl>");
				var $li = $("<li></li>");
				var $ul = $(".ulHot");
				var $div = $(".hot_sale");
				$dl.append($dt);
				$dl.append($dd1);
				$dl.append($dd2);
				$li.append($dl);
				$ul.append($li);
				$div.append($ul);

			}
        },"json")
    });
</script>

<div class="hot_sale">
	<h2>热卖推荐</h2>
	<ul class="ulHot">
		<%--<li class="li">
			<dl>
				<dt>
					<a href="" target="_self"> <img src="" /></a>
				</dt>
				<dd class="p_name">
					<a href="" target="_self"></a>
				</dd>
				<dd class="price">￥</dd>
			</dl>
		</li>--%>
	</ul>
</div>
