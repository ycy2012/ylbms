<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/jsp/inc/jBox.jsp"%>
<link href="${ctx}/styles/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<form method="post" action="${ctx}/menu/add"
		class="form-horizontal required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden" name="id" value="${menu.id}">
			<div class="divider"></div>
			<dl>
				<dt>上级菜单:</dt>
				<dd>
					<tags:treeselect id="menu" name="parent.id"
						value="${menu.parent.id}" labelName="parent.name"
						labelValue="${menu.parent.name}" title="菜单" url="/menu/treeData"
						extId="${menu.id}" />
			</dl>
			<dl>
				<dt>名称:</dt>
				<dd>
					<input type="text" name="name" size="30" value="${menu.name}"
						class="required">
                <input type="hidden" name="parentIds" value="${menu.parentIds}">
			</dl>
			<dl>
				<dt>链接:</dt>
				<dd>
					<input type="text" name="href" size="30" value="${menu.href}">
			</dl>
			<dl>
				<dt>REL属性:</dt>
				<dd>
					<input type="text" name="rel" size="30" value="${menu.rel}">
			</dl>
			<dl>
				<dt>目标:</dt>
				<dd>
					<select name="target" class="combox">
						<option value="">请选择</option>
						<option value="navTab">navTab</option>
						<option value="dialog">dialog</option>
					</select><span class="info">该属性必须选择</span>
			</dl>
			<dl>
				<dt>图标:</dt>
				<dd>
					<tags:iconselect id="icon" name="icon" value="${menu.icon}"></tags:iconselect>
			</dl>
			<dl>
				<dt>排序:</dt>
				<dd>
					<input type="text" name="sort" size="30" value="${menu.sort}">
			</dl>
			<dl>
				<dt>可见:</dt>
				<dd>
					<select name="isShow" class="combox">
						<option value="0">显示</option>
						<option value="1">隐藏</option>
					</select>
			</dl>
			<dl>
				<dt>权限标识:</dt>
				<dd>
					<input type="text" name="permission" size="30"
						value="${menu.permission}">
			</dl>
			<dl>
				<dt>权限标识:</dt>
				<dd>
					<input type="hidden" name="user.id" size="30"
						value="${menu.user.id}"> <input type="text" size="30"
						value="${menu.user.fullname}">
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</body>
</html>
