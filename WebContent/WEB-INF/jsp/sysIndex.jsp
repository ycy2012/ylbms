<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@include file="inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>欢迎使用压力表具管理系统v1.0</title>

<link href="${ctx}/dwz/themes/default/style.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/dwz/themes/css/core.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/dwz/themes/css/print.css" rel="stylesheet" type="text/css"
	media="print" />
<link href="${ctx}/dwz/uploadify/css/uploadify.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/oper/om-default.css" rel="stylesheet"
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
<script src="${ctx}/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${ctx}/dwz/xheditor/xheditor-1.1.14-zh-cn.min.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="${ctx}/dwz/uploadify/scripts/jquery.uploadify.v2.1.0.js"
	type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
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
<script src="${ctx}/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${ctx}/dwz/js/dwz.combox.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="${ctx}/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${ctx}/oper/js/operamasks-ui.js" type="text/javascript"></script>
<link href="${ctx}/styles/ztree/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/styles/ztree/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script src="${ctx}/styles/ztree/js/jquery.ztree.excheck-3.5.min.js" type="text/javascript"></script>
<script  type="text/javascript" src="${ctx}/styles/My97DatePicker/WdatePicker.js"></script>
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
	});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
			   <a class="logo">标志</a>
				<ul class="nav">
					<li id="switchEnvBox">
					<a> 欢迎使用[<shiro:principal property="name"/>]</a>
					</li>
					<li><a href="${ctx}/user/editPwdform" target="dialog" width="600">修改密码</a></li>
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
					  <!--    <ul id="menuTree"></ul>-->
			  
						<ul class="tree treeFolder">
							<li><a>用户信息管理</a>
								<ul>
									<li><a href="user/list" target="navTab" rel="user">用户信息管理</a></li>
									<li><a href="menu/list" target="navTab" rel="menu">菜单信息管理</a></li>
									<li><a href="role/list" target="navTab" rel="role">角色信息管理</a></li> 
									<li><a href="dict/list" target="navTab" rel="dict">字典信息管理</a></li> 
									<li><a href="druid/" target="_blank">数据源管理</a></li> 
								</ul></li>
							<li><a>基础信息管理</a>
								<ul>
									<li><a href="location/listUi" target="navTab" rel="location">位置信息管理</a></li> 
									<li><a href="spec/list" target="navTab" rel="spectype">规格型号管理</a></li>
									<li><a href="single/list" target="navTab" rel="singleInfo">单件明细管理</a></li>
									<li><a href="report/barUi" target="navTab" rel="singleBar">单件状态信息</a></li>
								</ul></li>
							<li><a>单据信息管理</a>
								    <ul>
									<li><a href="bill/list" target="navTab" rel="bill">单据信息管理</a></li>
									<li><a href="new/newUi" target="navTab" rel="billgl">入库单制作</a></li>
									<li><a href="ckgl/addUi" target="navTab" rel="billgl">出库单制作</a></li>
									<%-- 
									<li><a href="install/inputUi" target="navTab" rel="billgl">安装单制作</a></li>
									 --%>
									<li><a href="check/checkUi" target="navTab" rel="billgl">回收单制作</a></li>
								</ul></li>
						   <li><a>检测信息管理</a>
								    <ul>
									<li><a href="jd/iList" target="navTab" rel="jdnotes">检定台帐信息</a></li>
									<li><a href="jdzhs/list" target="navTab" rel="jdzhs">检定证书管理</a></li>
								</ul></li>
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
								<h1>信息提示</h1>
								<div>
									<p>本版本为测试版本，针对软件的相关和想法，请及时与我们交流！</p>
									<p>电话号码：0996-2179284</p>
									<p>信息中心， 技术支持</p>
								</div>
							</div>
							<div class="panel collapse" minH="350" defH="400">
								<h1>压力表使用情况统计</h1>
								<div style="width: 98.9%;overflow: hidden;">
								<div id="indexSingleBar" style="min-width:98%; width:98%; height: 400px; margin: 0 auto;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="footer">
		Copyright &copy; 2012  塔里木油田工程技术部信息中心
	</div>
</body>
</html>