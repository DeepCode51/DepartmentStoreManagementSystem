function addToCart(pid) {
	
	var stock=$("#stock").html()
	var count=$("#count").val()
	
	if(parseInt(count)>parseInt(stock)){
		alert("您选择的数量超过库存!")
	}else{
		$.ajax({
			url : "addToCart",// 请求的servlet地址
			type : "GET",// 请求方式
			data : "" + pid+"_"+count,// 发送到服务器的数据
			dataType : "text",// 设置返回数据类型
			success : function(total) {
				$("#cartCount").html(total);
				alert("成功添加到购物车!")
			},// 响应成功后执行的回调方法data响应文本
			complete : function(XMLHttpRequest, statusText) {
				
			},// 响应完成后执行的回调方法
			error : function(XMLHttpRequest, statusText) {
				alert("添加到购物车失败!")
			}// 响应失败后执行的回调方法
		})
	}

}

function goingToBuy(pid) {
	window.location.href="goingToBuy?"+pid+"_"+$("#count").val();
	
}

//-按钮事件
function minus(){

	if($("#count").val()==1){
		$("#count").val(1)
	}else if($("#count").val()>=2){
		var old=$("#count").val()
		$("#count").val(parseInt(old)-1)
	}
}

//+按钮事件
function add(){
	var stock=$("#stock").html()
	var old=$("#count").val()
	if(parseInt(old)<parseInt(stock)){
		$("#count").val(parseInt(old)+1)
	}else{
		alert("您选择的数量超过库存!")
	}
	
}



function checkStock(){
	var stock=$("#stock").html()
	var old=$("#count").val()
	if(parseInt(old)>parseInt(stock)){
		alert("您选择的数量超过库存!")
	}
}



function remaind() {
	alert("请先登录亚马逊！")
	window.location.href="login.jsp"
}




