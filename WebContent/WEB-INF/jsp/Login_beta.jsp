<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用工程技术部培训课程及考核题库管理系统</title>
<link href="${base}/resources/css/login.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="${base}/dwz/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	var login =
<%=session.getAttribute("me") != null%>
	$(function() {
		if (login) {
			$("#login_status").html("已登陆");
		} else {
			$("#login_status").html("未登陆");
		}
		$("#login_form").submit(function() {
			$.ajax({
				url : "${base}/Account/login",
				data : $("#login_form").serialize(),
				success : function(res) {
					alert(res);
					if (res == "true") {
						window.location.href="index.jsp";
					} else {
						alert("登陆失败!!");
					}
					return false;
				},
				fail : function(res) {
					alert("系统错误?!");
				}
			});
			return false;
		});
	});
</script>
</head>
<body>
<div id="page">
${base}
<div id="header">

</div>
</div>
<div id="login_status"></div>
<!--
	  <form id="login_form" action="#">
		<label>用户名：</label> <input name="name" type="text"> <label>密码：</label>
		<input type="text" name="passwd"> <input type="submit"
			value="提交"><input type="reset" value="取消">
</form>
-->
</body>
</html>