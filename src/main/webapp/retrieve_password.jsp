
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
	<script>
		var $reusername,$reuname,$reemail;
		$(function () {
		    //用户名
            $reusername = $("#reusername");
            //真实姓名
            $reuname = $("#reuname");
            //邮箱
            $reemail = $("#reemail");
            //绑定单击事件
            $("#resubmit").click(function () {
                submit();
            });
		});

		function submit() {

            $.post("${pageContext.request.contextPath}/user",{
                "param":"retrievePwd",
				"reusername":$reusername.val(),
				"reuname":$reuname.val(),
				"reemail":$reemail.val()},function (data) {
                	//如果有该用户，打印密码
                	if (data.success){
                	    alert("您的密码是："+data.reUser.pwd);
                	    window.location = "${pageContext.request.contextPath}/login.jsp";
					}else {
                	    $("#reError").html(data.error);
					}


            },"json");

        }
	</script>
</head>
<body>
<%@ include file="index_top.jsp" %>
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>请输入找回密码的相关信息：</h1>
			<form id="loginForm" method="post" action="#">
				<table>
					<div id="reError" style="font-size: 20px;color: red;text-align: center"></div>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" id="reusername"/><span></span></td>
					</tr>
					<tr>
						<td class="field">真实姓名：</td>
						<td><input class="text" type="text" id="reuname"/><span></span></td>
					</tr>
					<tr>
						<td class="field">邮箱：</td>
						<td><input class="text" type="text" id="reemail"  /><span></span></td>
					</tr>
					
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="button" id="resubmit" value="提交" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2018 上海浦软 All Rights Reserved.
</div>
</body>
</html>
