// JavaScript Document
function Delete(id)
{
	if(confirm("确定要删除吗？")) {
		open("ProductClassServlet?action=remove&hpcId=" + id,"_self");
	}
}

