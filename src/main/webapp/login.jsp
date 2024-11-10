<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>XXX - 登录</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
	<script>
		var $username,$pwd,$code;
		$(function () {
			$username = $(".username");
            $pwd = $(".pwd");
            $code = $(".code");

            //绑定登陆单击事件
			$(".submit").click(function () {
				if(validate()) {
					login();
				}

            });

			function validate() {
				if($username.val()==""){
					$("#error").html("用户名不能为空");
					return false;
				}

				if($pwd.val()==""){
					$("#error").html("密码不能为空");
					return false;
				}

				if($code.val()==""){
					$("#error").html("验证码不能为空");
					return false;
				}
				return true;
				
			}



			/*//换一张单击事件
			$("#refresh").click(function () {
                $("#imgCode").src = "${pageContext.request.contextPath}/code?timestame=" + new Date().getTime();
                return false;
            })*/




        });

		function login() {
			$.post("${pageContext.request.contextPath}/user",{
			    "param":"login",
				"username":$username.val(),
				"pwd":$pwd.val(),
				"code":$code.val()},function (data) {
			    	//判断登陆是否正确
					if (data.success){
					    window.location="${pageContext.request.contextPath}/index.jsp";
					}else{
						$("#error").html(data.error)
					}

            },"json")
        }
	</script>
</head>
<body>
<%@ include file="index_top.jsp"  %>
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎回到XXX</h1>
			<form id="loginForm" method="post" action="#" >
				<table>
					<div id="error" style="font-size: 20px;color: red;text-align: center"></div>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="username" type="text" name="userName" /><span></span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="pwd" type="password" id="passWord" name="passWord" /><a href="retrieve_password.jsp">忘记密码</a></td>
		
					</tr>
					<tr>           
						<td class="field">验证码：</td>
						<td><input type="text" name="code" class="code"/>
							<img src="${pageContext.request.contextPath}/code" id="imgCode"><%--<a href="#" id="refresh">换一张</a>--%>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="button" name="submit" value="立即登录" class="submit"/></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2018  上海浦软 All Rights Reserved
</div>
</body>
</html>
