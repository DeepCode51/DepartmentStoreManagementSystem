<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>XXX - 注册页</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
	<script>
		var $username,$pwd,$confirm,$sex,$birthday,$identity,$email,$mobile,$address,$code,$submit;
		$(function () {
			$username = $(".username");
            $pwd = $(".pwd");
            $confirm = $(".confirm");
            $sex = $(".sex");
            $birthday = $(".birthday");
            $identity = $(".identity");
            $email = $(".email");
            $mobile = $(".mobile");
            $address = $(".address");
            $code = $(".code");
            $submit = $(".submit");

            // //绑定失去焦点事件
            // $username.blur(function () {
			// 	unameBlur();
            // });

            //绑定单击事件
			$submit.click(function () {
				register();
            })
        });

		//检测用户名是否存在
		function unameBlur() {
			$.post("${pageContext.request.contextPath}/user",{"param":"nameBlur","username":$username.val()},function (data) {

			    if (data.success1){
                    $("#error").html(data.error);
				} else {
                    $("#error").html(data.error);
				}
            },"json")
        }

        //提交检测
		function register() {
            var regForm = document.getElementById("regForm");
            if(checkForm(regForm)){
				$.post("${pageContext.request.contextPath}/user",{
					"param":"register",
					"username":$username.val(),
					"pwd":$pwd.val(),
					"confirm":$confirm.val(),
					"identity":$identity.val(),
					"sex":$sex.val(),
					"birthday":$birthday.val(),
					"email":$email.val(),
					"mobile":$mobile.val(),
					"address":$address.val(),
					"code":$code.val()},function (data) {
						//判断注册是否正确
						if (data.success){
							window.location="${pageContext.request.contextPath}/reg-result.jsp";
						}else{
							$("#error").html(data.error);
						}
				},"json")
			}
        }


		function FocusItem(obj) {
			obj.parentNode.parentNode.className = "current";
			var msgBox = obj.parentNode.getElementsByTagName("span")[0];
			msgBox.innerHTML = "";
			msgBox.className = "";
		}

		function checkForm(frm) {
			var els = frm.getElementsByTagName("input");

			for (var i = 0; i < els.length; i++) {

				if (!CheckItem(els[i]))
					return false;
			}
			return true;
		}


		function CheckItem(obj) {
			obj.parentNode.parentNode.className = "";
			var msgBox = obj.parentNode.getElementsByTagName("span")[0];
			var regEmail = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
			var regIdentity = /^\d{17,}([0-9]|X)$/;
			//var regMobile = /^1\d{10}$/;
			var regMobile = /^1\d{10,}$/;
			var regBirth = /^((19\d{2})|(200\d))-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
			var regName = /^[a-zA-Z0-9_]{4,}$/;
			var regPass = /^[a-zA-Z0-9]{6,18}$/;
			switch (obj.name) {
				case "userName":
					if (regName.test(obj.value) == true) {
						FocusItem(obj);
						unameBlur();
					}else{
						msgBox.innerHTML = "字母数字下划线组成，长度4-15";
						msgBox.className = "error";
						return false;
					}
					break;
				case "passWord":
					if (regPass.test(obj.value) == true) {
						FocusItem(obj);
					}else{
						msgBox.innerHTML = "数字、字母组成,长度6-18位";
						msgBox.className = "error";
						return false;
					}
					break;
				case "rePassWord":
					 if (obj.value != document.getElementById("passWord").value) {
						msgBox.innerHTML = "两次输入的密码不相同";
						msgBox.className = "error";
						return false;
					}else{
						 FocusItem(obj);
					 }
					break;
				case "code":
					if (obj.value == "") {
						msgBox.innerHTML = "验证码不能为空";
						msgBox.className = "error";
						return false;
					}else{
						FocusItem(obj);
					}
					break;
				case "birthday":
					if (regBirth.test(obj.value) == false) {
						msgBox.innerHTML = "格式：1981-01-01";
						msgBox.className = "error";
						return false;
					}else{
						FocusItem(obj);
					}
					break;
				case "identity":
					if (regIdentity.test(obj.value) == false) {
						msgBox.innerHTML = "前17位数字,后1位数字或X";
						msgBox.className = "error";
						return false;
					}else{
						FocusItem(obj);
					}
					break;
				case "email":
					if (regEmail.test(obj.value) == false) {
						msgBox.innerHTML = "格式:xxxxx@xxx.xxx";
						msgBox.className = "error";
						return false;
					}else{
						FocusItem(obj);
					}
					break;
				case "mobile":
					if (regMobile.test(obj.value) == false) {
						msgBox.innerHTML = "格式：以1开头的11位数字";
						msgBox.className = "error";
						return false;
					}else{
						FocusItem(obj);
					}
					break;
				case "address":
					if (obj.value == "") {
						msgBox.innerHTML = "地址不能为空";
						msgBox.className = "error";
						return false;
					}else{
						FocusItem(obj);
					}
					break;
			}
			return true;
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
			<h1>欢迎注册账号</h1>
			<ul class="steps clearfix">
				<li class="current"><em></em>填写注册信息</li>
				<li class="last"><em></em>注册成功</li>
			</ul>
			<form id="regForm" method="post" action="#">
				<table>
					<div id="error" style="font-size: 20px;color: red;text-align: center"></div>
					<tr>
						<td class="field">用户名：</td>
						<td><input id="userName" class="userName" type="text" name="userName" onblur="CheckItem(this)"/><span class="error">字母数字下划线组成，长度4-15</span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="pwd" type="password" id="passWord" name="passWord"   onblur="CheckItem(this)"/><span class="error">数字、字母组成,长度6-18位</span></td>
					</tr>
					<tr>
						<td class="field">确认密码：</td>
						<td><input class="confirm" type="password" name="rePassWord" onblur="CheckItem(this)"/><span></span></td>
					</tr>
					<tr>
						<td class="field">性别：</td>
						<td ><input class="sex" type="radio" name="sex"  style="border:0px;" checked="checked" value="0" />男<input type="radio" name="sex" style="border:0px;" value="1"/>女<span></span></td>
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td><input class="birthday" type="text" name="birthday"  onblur="CheckItem(this)"/><span class="error">格式：1981-01-01</span></td>
					</tr>
					<tr>
						<td class="field">身份证：</td>
						<td><input class="identity" type="text" name="identity"  onblur="CheckItem(this)"/><span class="error">前17位数字,后1位数字或X</span></td>
					</tr>
					<tr>
						<td class="field">电子邮件：</td>
						<td><input class="email" type="text" name="email"  onblur="CheckItem(this)"/><span class="error">格式:xxxxx@xxx.xxx</span></td>
					</tr>
					<tr>
						<td class="field">手机：</td>
						<td><input class="mobile" type="text" name="mobile"  onblur="CheckItem(this)"/><span class="error">格式：以1开头的11位数字</span></td>
					</tr>
					<tr>
						<td class="field">地址：</td>
						<td><input class="address" type="text" name="address" onblur="CheckItem(this)"/><span class="error">地址不能为空</span></td>
					</tr>
					<tr>
						<td class="field">验证码：</td>
						<td><input type="text" name="code" class="code" onblur="CheckItem(this)"/>
							<span class="error">验证码不能为空</span>
							<%--<a href="#" id="refresh">换一张</a>--%>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><img src="${pageContext.request.contextPath}/code" id="imgCode"></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="button" name="submit" value="提交注册" class="submit"/></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2018 上海浦软 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>

