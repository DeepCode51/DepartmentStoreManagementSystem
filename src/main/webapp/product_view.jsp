<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>XXX - 产品显示</title>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/index.js"></script>
<script type="text/javascript" src="scripts/product_view.js"></script>
	<%@ include file="method.jsp" %>
	<script>
        var $id;
		$(function () {
		    //获取商品id
			$id = getUrlParam('id');
			//显示商品详情页
            productPage();

        });



		//拼接商品详情页
		function productPage() {

            $.post("${pageContext.request.contextPath}/Product",{"param":"productById","id":$id},function (data) {

                var $position = $("#position1");
                var $posA;
                if (data.sonName==data.parentName){
                    $posA = $("<a href='${pageContext.request.contextPath}/index.jsp'>XXX</a> > <a href='${pageContext.request.contextPath}/product-list.jsp?id="+data.parId+"'>" + data.parentName + "</a>");
                } else {
                    $posA = $("<a href='${pageContext.request.contextPath}/index.jsp'>XXX</a> > <a href='${pageContext.request.contextPath}/product-list.jsp?id="+data.parId+"'>" + data.parentName + "</a> > <a href='${pageContext.request.contextPath}/product-list.jsp?id="+data.sonId+"'>" + data.sonName + "</a>");
                }
                $position.append($posA);

                //获取元素
                var $em = data.productById;
                //判断是否有货
                var $isStock;
                if ($em.stock>0){
                    $isStock = "(有货)";
                } else {
                    $isStock ="(缺货)"
                }
                //设置商品名称
                var $h1 = $("<h1>商品名称:"+$em.name+"</h1>");
                //添加商品主图
                var $dimg = $("<div class='thumb'><img style='width: 100px; height: 100px;' src='"+$em.img_source+"'/></div>");
                //添加价格
                var $p1 = $("<p>商城价：<span class='price'>￥"+$em.price+"</span></p>");
                //添加库存
                var $p2 = $("<p>库 存：<span id='stock'>"+$em.stock+"</span>"+$isStock+"</p>");
                //添加数量按钮
                var $p3 = $("<p><input type='button' id='minus' value='-'   width='3px'>\n" +
                    "<input type='text' id='count' name='count' value='0' maxlength='5' size='1' style='text-align: center; vertical-align: middle'>\n" +
                    "<input type='button' id='add' value=' + ' width='2px'></p>");
                //添加立即购买、加入购物车按钮
                var $dinput = $("<div class='button'><input id='buyPro' type='button'  name='button' value='' style='background: url(images/buyNow.png)' />" +
					"<input id='shopCar' type='image' name='imageField' src='images/cartbutton.png' /></div>");
                //设置空气墙
                var $clear = $("<div class='clear'></div>");
                //设置商品详情
                var $h2 = $("<h2><strong>商品详情</strong></h2>");
                var $dtext = $("<div class='text'>商品名字："+$em.name+"<br /> 商品描述："+$em.description+"<br />商品价格：￥"+$em.price+"<br /> 商品库存："+$em.stock+"<br /></div>");
                var $introduce = $("<div class='introduce' id='introduce'><div>");
                var $buy = $("#buy");
                var $infos = $("#infos");
                var $product = $("#product");
                $buy.append($p1);
                $buy.append($p2);
                $buy.append($p3);
                $buy.append($dinput);
                $infos.append($dimg);
                $infos.append($buy);
                $infos.append($clear);
                $product.append($h1);
                $product.append($infos);
                $introduce.append($h2);
                $introduce.append($dtext);
                $product.append($introduce);

                $("#count").blur(function () {
					if($(this).val()<=0){
					    alert("操作错误！最低不能小于0！");
					    $(this).val(0)
					}else if($(this).val()>=$em.stock){
                        alert("操作错误！最多不能超过库存："+$em.stock+"！");
                        $(this).val($em.stock)
					}
                });

                //绑定减号单击事件
                $("#minus").click(function () {
                    var $count = $("#count");
                    var $countV = $("#count").val();
                    //减少不能超过0
                    if ($countV<=0){
                        $count.val(0);
					}else {
                        $count.val($countV-1);
					}                });

				//绑定加号单击事件
				$("#add").click(function () {
                    var $count = $("#count");
                    var $countV = $("#count").val();
                    //增加不能超过库存
                    if ($countV>=$em.stock){
                        $count.val($em.stock);
					}else {//如果直接相加，系统默认是字符串相加，$countV*1后会变成数值类型
                        $count.val($countV*1+1);
					}
                });

				//立即购买
				$("#buyPro").click(function () {
				    //判断是否有库存
				    if ($("#count").val()<=0){
				        alert("请选择购买数量！")
					} else {
						window.location="${pageContext.request.contextPath}/doBuy?param=buyOne&pid="+$em.id+"&pnum="+$("#count").val()+"";
					}
                });

				//加入购物车
                $("#shopCar").click(function () {
                    //判断是否有库存
                    if ($("#count").val()<=0){
                        alert("请选择加购数量！")
                    } else {
                    	alert("加购成功!");
                    	window.location="${pageContext.request.contextPath}/shopCar?param=showCar&id="+$em.id+"&num="+$("#count").val()+"";
					}
                })
            },"json")

        }



	</script>
</head>
<body>
	<%@ include file="index_top.jsp"%>
	<div id="position1" class="wrap">
		您现在的位置：<%--<a href="index.jsp">XXX</a> &gt; <a href=""></a> &gt; <a
			href=""></a>--%>
	</div>
	<div id="main" class="wrap">
		<div class="lefter">
			<%@ include file="index_product_sort.jsp"%>
		</div>
		<div id="product" class="main">
			<%--<h1>商品名称:</h1>--%>
			<div class="infos" id="infos">
				<%--<div class="thumb">
					<img style="width: 100px; height: 100px;" src="" />
				</div>--%>
				<div class="buy" id="buy">
					<%--<p>
						商城价：<span class='price'>￥</span>
					</p>
					<p>
						库 存：<span id='stock'></span>(有货)
					</p>
					<p>
						库 存： <input type='button' id='minus' value='-'  width='3px'>
						<input type='text' id='count' name='count' value='1' maxlength='5'
							size='1' style='text-align: center; vertical-align: middle'>
						<input type='button' id='add' value=' + ' width='2px'>
					</p>--%>
					<%--<div class='button'>
						<input type='button' name='button' value=''
							style='background: url(images/buyNow.png)' /> <input
							type='image' name='imageField' src='images/cartbutton.png' />
					</div>--%>


				</div>
				<div class="clear"></div>
			</div>
			<%--<div class='introduce' id='introduce'>
				<h2>
					<strong>商品详情</strong>
				</h2>
				<div class='text'>
					商品名字：<br /> 商品描述：<br />
					商品价格：￥<br /> 商品库存：<br />
				</div>
			</div>--%>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2018 上海浦软 All Rights Reserved.
	</div>
</body>
</html>

