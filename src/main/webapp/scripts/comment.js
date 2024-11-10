/**
 * 
 */


function commentCheck(){
	
	if($("[name=guestName]").val()==""){
		alert("请输入昵称！")
		return false;
	}
	
	if($("[name=guestTitle]").val()==""){
		alert("请输入标题！")
		return false;
	}
	
	if($("[name=guestContent]").val()==""){
		alert("请输入留言内容！")
		return false;
	}
	
	return true;
}
