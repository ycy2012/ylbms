<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%@include file="../inc/taglib.jsp"%>
<%
	response.setStatus(200);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>500 - 系统内部错误</title>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header">
			<h1>系统发生内部错误.</h1>
		</div>
		<p>错误信息：</p>
		<div>
			<a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a>
		</div>
	</div>
</body>
</html>