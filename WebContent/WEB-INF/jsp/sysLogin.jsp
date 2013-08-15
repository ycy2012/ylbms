<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@include file="inc/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用压力表具信息管理系统beta版本</title>
<link href="${ctx}/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<img src="${ctx}/dwz/themes/default/images/login_logo.gif" />
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="#">反馈</a></li>
					</ul>
				</div>
				<h2 class="login_title">
					<img src="${ctx}/dwz/themes/default/images/login_title.png" />
				</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="${ctx}/a/login" id="login_form" method="post">
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
					<p>
						<label>用户名：</label> <input type="text" name="username" size="20"
							class="login_input" />
					</p>
					<p>
						<label>密&nbsp;&nbsp;&nbsp;码：</label> <input type="password"
							name="password" size="20" class="login_input" />
					</p>
					<p>
						<label for="rememberMe" title="下次不需要再登录"><input
							type="checkbox" id="rememberMe" name="rememberMe" /> 记住我</label>
					</p>
					<!--  
					<p>
						<label>验证码：</label>
						<input class="code" type="text" size="5" />
						<span><img src="dwz/themes/default/images/header_bg.png" alt="" width="75" height="24" /></span>
					</p>
					-->
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner">
				<img src="${ctx}/dwz/themes/default/images/login_banner.jpg" />
			</div>
			<div class="login_main">
				<!-- 
				<ul class="helpList">
					<li><a href="#">下载驱动程序</a></li>
					<li><a href="#">如何安装密钥驱动程序？</a></li>
					<li><a href="#">忘记密码怎么办？</a></li>
					<li><a href="#">为什么登录失败？</a></li>
				</ul>
			 
				<div class="login_inner">
					<p>您可以使用 网易网盘 ，随时存，随地取</p>
					<p>您还可以使用 闪电邮 在桌面随时提醒邮件到达，快速收发邮件。</p>
					<p>在 百宝箱 里您可以查星座，订机票，看小说，学做菜…</p>
				</div>
				-->
			</div>
		</div>
		<div id="login_footer">Copyright &copy; 2012 工程技术部信息中心 Inc. All
			Rights Reserved.</div>
	</div>
</body>
</html>