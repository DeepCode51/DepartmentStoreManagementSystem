
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"></script>
<script>
    $(function () {
        $.post("${pageContext.request.contextPath}/Product",{"param":"prcLook"},function (data) {
            var $h3 = $("<h3>最近浏览</h3>");
            var $pre_look = $("#pre_look");
            $pre_look.append($h3);

            for (var i = 0; i < data.lookList.length; i++) {
                var $element = data.lookList[i];//获取集合里面的元素
                //创建商品图片
                var $dt = $("<dt><a href='${pageContext.request.contextPath}/product_view.jsp?id="+$element.id+"' target='_self'><img style='width: 54px; height: 54px;' src='"+$element.img_source+"'/></a></dt>");
                //创建商品名称
                var $dd1 = $("<dd class='title'><a href='${pageContext.request.contextPath}/product_view.jsp?id="+$element.id+"'target='_self'>"+$element.description+"</a></dd>");
                //创建商品价格
                var $dd2 = $("<dd class='price'>￥"+$element.price+"</dd>");

                var $dl = $("<dl></dl>");
                $dl.append($dt);
                $dl.append($dd1);
                $dl.append($dd2);
                $pre_look.append($dl);

            }


        },"json")
    })
</script>

<div class="pre_look" id="pre_look">
    <%--<h3>最近浏览</h3>
    <dl>
        <dt>
            <img style='width: 54px; height: 54px;' src="" />
        </dt>
        <dd>
            <a href=""></a>
        </dd>
    </dl>--%>
</div>