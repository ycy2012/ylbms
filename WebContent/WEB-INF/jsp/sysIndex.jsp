<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="inc/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>欢迎使用压力表具管理系统v1.0</title>

<link href="${ctx}/dwz/themes/default/style.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/dwz/themes/css/core.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/dwz/themes/css/print.css" rel="stylesheet"
	type="text/css" media="print" />
<link href="${ctx}/dwz/uploadify/css/uploadify.css" rel="stylesheet"
	type="text/css" />
<!--[if IE]>
<link href="${ctx}/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if lte IE 6]>
<script src="${ctx}/styles/js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('a');
    </script>
<![endif]-->
<script src="${ctx}/dwz/js/speedup.js" type="text/javascript"></script>
<script src="${ctx}/styles/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${ctx}/dwz/xheditor/xheditor-1.1.14-zh-cn.min.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/uploadify/scripts/swfobject.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/uploadify/scripts/jquery.uploadify.v2.1.0.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.validate.method.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.combox.js" type="text/javascript"></script>
<!--
<script src="${ctx}/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${ctx}/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/styles/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/styles/highcharts/js/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/indexSingleBar.js"></script>
<script type="text/javascript">
	$(function() {
		DWZ.init("dwz.frag.xml", {
			loginUrl : "login_dialog.html",
			loginTitle : "重新登录", // 弹出登录对话框
			statusCode : {
				ok : 200,
				error : 300,
				timeout : 301
			}, //【可选】
			pageInfo : {
				pageNum : "pageNum",
				numPerPage : "numPerPage",
				orderField : "orderField",
				orderDirection : "orderDirection"
			}, //【可选】
			debug : false, // 调试模式 【true|false】
			callback : function() {
				initEnv();
				$("#themeList").theme({
					themeBase : "${ctx}/dwz/themes"
				}); // themeBase 相对于index页面的主题base路径
			}
		});
		$.post("report/singleCheck", function(data) {
			$("#counts").text(data);
		});
	});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo">标志</a>
				<ul class="nav">
					<li id="switchEnvBox"><a> 欢迎使用[<shiro:principal
								property="name" />]
					</a></li>
					<li><a href="${ctx}/user/editPwdform" target="dialog"
						width="600">修改密码</a></li>
					<!--  
					<li><a href="http://www.cnblogs.com/dwzjs" target="_blank">博客</a></li>
					<li><a href="http://weibo.com/dwzui" target="_blank">微博</a></li>
					<li><a href="http://bbs.dwzjs.com" target="_blank">论坛</a></li>
					-->
					<li><a href="${ctx}/a/logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>
			<!-- navMenu -->
		</div>
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>主菜单</h2>
					<div>收缩</div>
				</div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2>
							<span>Folder</span>功能菜单
						</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
							<c:if test="${menu.parent.id eq 1&&menu.isShow eq 0 }">
								<li><a>${menu.name}</a><ul>
								 <c:forEach items="${menu.childList}" var="subm">
								 	<li><a href="${subm.href}" rel="${subm.rel}" target="${subm.target}" >${subm.name}</a></li>
								 </c:forEach>
								</ul></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span
										class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;" rel="systemHome">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<!--  -->
							<br />
							<h1>压力表具信息管理系统beta版本</h1>
						</div>
						<div class="pageFormContent" layoutH="80">
							<div class="panel collapse" minH="100" defH="150">
								<h1>待办事件</h1>
								<div>
									<p>
										温馨提示：<a href="${ctx}/report/list" target="navTab">将有90天<b
											id="counts" style="color: red;"> ${counts} </b>只压力表到期了!
										</a>
									</p>
									<p>本版本为测试版本，针对软件的相关意见和想法，请及时与我们交流！</p>
									<p>电话号码：0996-2179284</p>
									<p>信息中心， 技术支持</p>
								</div>
							</div>
							<div class="panel collapse" minH="360" defH="380">
								<h1>压力表使用情况统计</h1>
								<div>
									<div id="indexSingleBar"
										style="min-width: 900px;; width: 1000px;; height: 360px; margin: 0 auto;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">Copyright &copy; 2012 塔里木油田工程技术部信息中心</div>
</body>
</html>