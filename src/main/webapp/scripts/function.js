// // JavaScript Document
// window.onload = function() {
// 	showChater();
// 	scrollChater();
// }
// window.onscroll = scrollChater;
// window.onresize = scrollChater;
//
// function FocusItem(obj) {
// 	obj.parentNode.parentNode.className = "current";
// 	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
// 	msgBox.innerHTML = "";
// 	msgBox.className = "";
// }
//
// function Checkexist() {
// 	var userName = document.getElementById("userName");
// 	var flag=true
// 	$.ajax({
// 		url : "CheckUserName",// 请求的servlet地址
// 		type : "POST",// 请求方式
// 		async: false,
// 		data : "userName=" + userName.value,// 发送到服务器的数据
// 		dataType : "text",// 设置返回数据类型
// 		success : function(test) {
// 			if (test == 1) {
// 				var msgBox = document.getElementById("uName");
// 				msgBox.style.display = "inline";
// 				msgBox.innerHTML = "用户名已存在！";
// 				flag= false;
// 			} else {
// 				var msgBox = document.getElementById("uName");
// 				msgBox.style.display = "inline";
// 				msgBox.innerHTML = null;
// 			}
// 		},// 响应成功后执行的回调方法data响应文本
// 		complete : function(XMLHttpRequest, statusText) {
//
// 		},// 响应完成后执行的回调方法
// 		error : function(XMLHttpRequest, statusText) {
// 			alert("操作失败!")
// 		}// 响应失败后执行的回调方法
// 	})
// 	return flag;
// }
// function CheckItem(obj) {
// 	obj.parentNode.parentNode.className = "";
// 	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
// 	var regEmail = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
// 	var regIdentity = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
// 	var regMobile = /^1\d{10}$/;
// 	var regBirth = /^((19\d{2})|(200\d))-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
// 	var regName = /^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
// 	var regPass = /^[a-zA-Z0-9]{4,10}$/;
// 	switch (obj.name) {
// 	case "userName":
// 		if (obj.value == "" || regName.test(obj.value) == false) {
// 			msgBox.innerHTML = "用户名不能为空并且只能是字母开头和字母数字结尾，长度在4-15之间";
// 			msgBox.className = "error";
// 			return false;
// 		}else{
// 			return Checkexist();
// 		}
// 		break;
// 	case "passWord":
// 		if (obj.value == "" || regPass.test(obj.value) == false) {
// 			msgBox.innerHTML = "密码不能为空并且不能含有非法字符，长度在4-10之间";
// 			msgBox.className = "error";
// 			return false;
// 		}
// 		break;
// 	case "rePassWord":
// 		if (obj.value == "") {
// 			msgBox.innerHTML = "确认密码不能为空";
// 			msgBox.className = "error";
// 			return false;
// 		} else if (obj.value != document.getElementById("passWord").value) {
// 			msgBox.innerHTML = "两次输入的密码不相同";
// 			msgBox.className = "error";
// 			return false;
// 		}
// 		break;
// 	case "veryCode":
// 		if (obj.value == "") {
// 			msgBox.innerHTML = "验证码不能为空";
// 			msgBox.className = "error";
// 			return false;
// 		}else{
// 			return checkValidateCode();
// 		}
// 		break;
// 	case "birthday":
// 		if (obj.value == "" || regBirth.test(obj.value) == false) {
// 			msgBox.innerHTML = "出生日期不能空,格式为（1990-01-01）";
// 			msgBox.className = "error";
// 			return false;
// 		}
// 		break;
// 	case "identity":
// 		if (obj.value == "" || regIdentity.test(obj.value) == false) {
// 			msgBox.innerHTML = "输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X";
// 			msgBox.className = "error";
// 			return false;
// 		}
// 		break;
// 	case "email":
// 		if (obj.value == "" || regEmail.test(obj.value) == false) {
// 			msgBox.innerHTML = "电子邮件不能为空,格式为web@sohu.com";
// 			msgBox.className = "error";
// 			return false;
// 		}else{
// 			return emailExist();
// 		}
// 		break;
// 	case "mobile":
// 		if (regMobile.test(obj.value) == false) {
// 			msgBox.innerHTML = "手机不能为空必须为11位并且只能是数字";
// 			msgBox.className = "error";
// 			return false;
// 		}
// 		break;
// 	case "address":
// 		if (obj.value == "") {
// 			msgBox.innerHTML = "地址不能为空";
// 			msgBox.className = "error";
// 			return false;
// 		}
// 		break;
// 	}
// 	return true;
// }
//
// function checkForm(frm) {
// 	var els = frm.getElementsByTagName("input");
//
// 	for (var i = 0; i < els.length; i++) {
//
// 			if (!CheckItem(els[i]))
// 				return false;
// 	}
// 	return true;
// }
//
// /*function showChater() {
// 	var _chater = document.createElement("div");
// 	_chater.setAttribute("id", "chater");
// 	var _dl = document.createElement("dl");
// 	var _dt = document.createElement("dt");
// 	var _dd = document.createElement("dd");
// 	var _a = document.createElement("a");
// 	_a.setAttribute("href", "#");
// 	_a.onclick = openRoom;
// 	_a.appendChild(document.createTextNode("在线聊天"));
// 	_dd.appendChild(_a);
// 	_dl.appendChild(_dt);
// 	_dl.appendChild(_dd);
// 	_chater.appendChild(_dl);
// 	document.body.appendChild(_chater);
// }
//
// function openRoom() {
// 	window.open("chat-room.jsp", "chater",
// 			"status=0,scrollbars=0,menubar=0,location=0,width=600,height=400");
// }*/
//
// /*function scrollChater() {
// 	var chater = document.getElementById("chater");
// 	var scrollTop = document.documentElement.scrollTop;
// 	var scrollLeft = document.documentElement.scrollLeft;
// 	chater.style.left = scrollLeft + document.documentElement.clientWidth - 92
// 			+ "px";
// 	chater.style.top = scrollTop + document.documentElement.clientHeight - 25
// 			+ "px";
// }
//
// function inArray(array, str) {
// 	for (a in array) {
// 		if (array[a] == str)
// 			return true;
// 	}
// 	return false;
// }*/
//
//
// function emailExist() {
// 	var flag=true
// 	var email=$("[name=email]").val()
// 	$.ajax({
// 		url : "CheckEmail",// 请求的servlet地址
// 		type : "GET",// 请求方式
// 		data : "" + email,// 发送到服务器的数据
// 		async: false,
// 		dataType : "text",// 设置返回数据类型
// 		success : function(test) {
// 			if (test == 1) {
// 				var msgBox = document.getElementById("uemail");
// 				msgBox.style.display = "inline";
// 				msgBox.innerHTML = "该邮箱已经注册！";
// 				flag= false;
// 			} else {
// 				var msgBox = document.getElementById("uemail");
// 				msgBox.style.display = "inline";
// 				msgBox.innerHTML = null;
//
// 			}
// 		},// 响应成功后执行的回调方法data响应文本
// 		complete : function(XMLHttpRequest, statusText) {
//
// 		},// 响应完成后执行的回调方法
// 		error : function(XMLHttpRequest, statusText) {
// 			alert("操作失败!")
// 		}// 响应失败后执行的回调方法
// 	})
// 	return flag;
// }
//
//
//
// function checkValidateCode() {
// 	var veryCode=$("[name=veryCode]").val()
// 	var flag=true;
// 	$.ajax({
// 		url : "checkCode",// 请求的servlet地址
// 		type : "GET",// 请求方式
// 		async: false,
// 		data : "" + veryCode,// 发送到服务器的数据
// 		dataType : "text",// 设置返回数据类型
// 		success : function(test) {
// 			if (test != 1) {
// 				var msgBox = document.getElementById("Code");
// 				msgBox.style.display = "inline";
// 				msgBox.innerHTML = "验证码错误！";
// 				flag= false;
// 			} else {
// 				var msgBox = document.getElementById("Code");
// 				msgBox.style.display = "inline";
// 				msgBox.innerHTML = null;
// 			}
// 		}
// 	})
// 	return flag
// }
//
//
// function loginCheck(){
//
// 	if($("[name=userName]").val()==""){
// 		alert("请输入用户名！")
// 		return false;
// 	}
//
// 	if($("[name=passWord]").val()==""){
// 		alert("请输入用户密码！")
// 		return false;
// 	}
//
// 	if($("[name=veryCode]").val()==""){
// 		alert("请输入验证码！")
// 		return false;
// 	}else{
// 		return checkValidateCode();
// 	}
//
//
// }
//
//
//
