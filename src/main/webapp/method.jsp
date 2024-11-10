
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="scripts/jquery-2.1.0.js"></script>
<script>

    //获取url中的元素
   function getUrlParam (name) {
       var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");//正则表达式
       var r = window.location.search.substr(1).match(reg);//获得匹配的参数
       if (r != null) return decodeURIComponent(r[2]); return null;
   }
</script>