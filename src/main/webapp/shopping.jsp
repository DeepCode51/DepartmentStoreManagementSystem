<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊- 购物车</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
<script type="text/javascript" src="scripts/shopping.js"></script>
	<%@ include file="method.jsp" %>
<%--	<script>
		var $id,$num;
		$(function () {
		    //获取商品id，加购数量
			$id = getUrlParam('id');
			$num = getUrlParam('num');
			//调用ajax拼接页面
            $.post("${pageContext.request.contextPath}/shopCar",{"param":"showCar","id":$id,"num":$num},function (data) {
                var $table = $("#table");
                for (var i = 0; i <data.shopCar.length ; i++) {
                    var $tr = $("<tr></tr>");
                    var $em = data.shopCar[i];//获取元素
					//设置商品图片和姓名
					var $td1 = $("<td class='thumb'><img style='width: 100px; height: 100px;' src='"+$em.pro.img_source+"'/><a href=''>"+$em.pro.name+"</a></td>");
					//设置商品金额
					var $td2 = $("<td class='price' id='price_id_1'>￥<span id='span_1'>"+$em.subtotal+"</span></td>")
					//设置数量增减
					var $td3 = $("<td class='number'></td>");
					var $input1 = $("<input type='button' class='minusCar' value=' - ' width='3px' name='minusButton'>");
					var $input2 = $("<input class='countCar' type='text' name='number' value='"+$em.pnum+"' maxlength='5' size='1'  style='text-align:center; vertical-align:middle'/>");
					var $input3 = $("<input type='button' class='addCar' value='+' width='2px' name='addButton'>");
					//设置删除键
					var $td4 = $("<td class='delete'><a href=''>删除</a></td>");
					$td3.append($input1);
					$td3.append($input2);
					$td3.append($input3);
					$tr.append($td1);
					$tr.append($td2);
					$tr.append($td3);
					$tr.append($td4);
                    $table.append($tr);

            	}

                $(".number").on("click",".minusCar",function () {
                    alert("1")
                    var $count = $(this).find(".countCar").val();
                    var $countV = $(".countCar").val();
                    alert($count);
                    alert($countV);
                })
            },"json")
        })


	</script>--%>
	<script>
		$(function () {
		    //遍历购物车列表
            $(".productCar").each(function () {
                //操作购物项数量
                event(this);
            })

        });


		//操作购物项方法
		function event(obj) {
            //给每个-号生成单机事件
            $("#minusCar",obj).click(function () {
                //获取当前单击对象的tr内容，再从tr内容中找到#countCar元素
				//获取当前购物项数量
                var $count = $(this).parents("tr").find("#countCar");
                //获取当前购物项id
                var $id = $(this).parents("tr").find("#hidden").val();
                var $price = $(this).parents("tr").find("#span_1");
                $.post("${pageContext.request.contextPath}/shopCar",{"param":"update","pnum":$count.val(),"id":$id,"type":"-"},function (data) {
					if (data.updatesuccess) {
                        //修改页面商品数量
                        $count.val(data.updateCart.pnum);
                        //修改页面商品价格
                        $price.html(data.updateCart.subtotal);
                    }else {
					    alert(data.error);
                        //修改页面商品数量
                        $count.val('1');
                        //修改页面商品价格
                        $price.html(data.updatePro.price);
					}
                },"json")

            });

            //给每个+号生成单机事件
            $("#addCar",obj).click(function () {
                //获取当前单击对象的tr内容，再从tr内容中找到#countCar元素
                //获取当前购物项数量
                var $count = $(this).parents("tr").find("#countCar");
                //获取当前购物项id
                var $id = $(this).parents("tr").find("#hidden").val();
                var $price = $(this).parents("tr").find("#span_1");
                $.post("${pageContext.request.contextPath}/shopCar",{"param":"update","pnum":$count.val(),"id":$id,"type":"+"},function (data) {
                    if (data.updatesuccess) {
                        //修改页面商品数量
                        $count.val(data.updateCart.pnum);
                        //修改页面商品价格
                        $price.html(data.updateCart.subtotal);
                    }else {
                        alert(data.error);
                        //修改页面商品数量
                        $count.val(data.updatePro.stock);
                        //修改页面商品价格
                        $price.html(data.updatePro.maxPrice);
                    }
                },"json")

            });

            //给每个countCar绑定失去焦点事件
            $("#countCar",obj).blur(function () {

                //获取当前单击对象的tr内容，再从tr内容中找到#countCar元素
                //获取当前购物项数量
                var $count = $(this);
                //获取当前购物项id
                var $id = $(this).parents("tr").find("#hidden").val();
                var $price = $(this).parents("tr").find("#span_1");
                $.post("${pageContext.request.contextPath}/shopCar",{"param":"update","pnum":$count.val(),"id":$id,"type":"updateNum"},function (data) {
                    if (data.updatesuccess) {
                        //修改页面商品价格
                        $price.html(data.updateCart.subtotal);
                    }else {
                        alert(data.error);
                        if ($count.val()<=0){
                            //修改页面商品数量
                            $count.val('1');
                            //修改页面商品价格
                            $price.html(data.updatePro.price);
						} else{
                            //修改页面商品数量
                            $count.val(data.updatePro.stock);
                            //修改页面商品价格
                            $price.html(data.updatePro.maxPrice);
						}
                    }
                },"json")

            });

            //删除购物项
            $("#delCar",obj).click(function () {

                var $id = $(this).parents("tr").find("#hidden").val();
                window.location = "${pageContext.request.contextPath}/shopCar?param=delPro&id="+$id+"";
            })

        }
	</script>

</head>
<body>
<%@ include file="index_top.jsp"  %>

<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">亚马逊</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="${pageContext.request.contextPath}/doBuy?param=buy" method="post">
			<table id="table">
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				
				<c:forEach var="list" items="${shopCar}">
				<!-- 根据用户购物车生成列表 -->
					<tr id="product_id_1" class="productCar">
						<td class='thumb'><img style='width: 100px; height: 100px;' src='${list.pro.img_source}'/><a href="${pageContext.request.contextPath}/product_view.jsp?id=${list.pro.id}">${list.pro.name}</a></td>
						<td class='price' id='price_id_1'>
							￥<span id='span_1'>${list.subtotal}</span>
						</td>
						<td class='number'>
								<input type='button' id='minusCar' value=' - ' width='3px' name='minusButton'>
								<input id='countCar' type='text' name='number' value='${list.pnum}' maxlength='5' size='1'  style='text-align:center; vertical-align:middle'/>
								<input type='button' id='addCar' value='+' width='2px' name='addButton'>
								<input type="hidden" value="${list.id}" id="hidden">
						</td>
						<td class='delete' id="delCar"><a href='#'>删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div class="button"><input type="submit" value="" /></div>
		</form>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2018 上海浦软 All Rights Reserved.
</div>
</body>
</html>

