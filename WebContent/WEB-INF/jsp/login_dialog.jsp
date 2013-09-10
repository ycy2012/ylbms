<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@include file="inc/taglib.jsp"%>
<link href="${ctx}/styles/css/relogin.css" rel="stylesheet"type="text/css" />
<script type="text/javascript">
<!--
	$(function() {
		$("#r2").click(function() {
			$.pdialog.closeCurrent();//关闭dialog
			parent.location.reload(); //刷新重新登录
		});
	});
//-->
</script>
<div class="pageContent">
	<%-- 
	<form method="post" action="${ctx}/index/relogin" class="pageForm"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<%
					String error = (String) request
							.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
					if (error != null) {
				%>
				<div class="alert">
					<strong>Warning!</strong> 输入用户信息错误，请重新输入！
				</div>
				<%
					}
				%>
			</div>
			<div class="unit">
				<label>用户名：</label> <input type="text" name="username" size="30"
					class="required" />
			</div>
			<div class="unit">
				<label>密码：</label> <input type="password" name="password" size="30"
					class="required" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">登录</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
--%>
	<div class="reLogin">
		<div id="r1">
		</div>
		<div id="r2"></div>
	</div>
</div>