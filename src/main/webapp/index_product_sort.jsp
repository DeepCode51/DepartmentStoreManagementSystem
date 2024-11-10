<script>
    $(function () {
        $.post("${pageContext.request.contextPath}/Product",{"param":"queryProductCategory"},function (data) {

            var $dl = $(".dl");

            //循环一级分类结果集
            for(var i = 0;i < data.parent.length;i++){
                //获取数组里面的元素
                var $index = data.parent[i];
                var $dt = $("<dt><a href='${pageContext.request.contextPath}/product-list.jsp?id="+$index.id+"'>"+$index.name+"</a></dt>");
                //添加一级分类
                $dl.append($dt);

                //获取这个一级分类的id
                var $id = $index.id;
                //循环输出二级分类
                //当他不为空时进行循环
                if(data[$id]!=null){//因为k是变量所以用data[]获取V
                    for (var j = 0;j < data[$id].length;j++){
                        //获取里面的元素
                        var $indexSon = data[$id][j];
                        var $dd = $("<dd><a href='${pageContext.request.contextPath}/product-list.jsp?id="+$indexSon.id+"'>"+$indexSon.name+"</a></dd>");
                        //添加二级分类
                        $dl.append($dd);
                    }
                }
            }
        },"json");
    })

</script>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"></script>

<div class="p_category">
	<h2>商品分类</h2>
		<dl class="dl">
			<%--<dt>
				<a href=""></a>
			</dt>
				<dd>
					<a href=""></a>
				</dd>--%>
		</dl>
</div>

