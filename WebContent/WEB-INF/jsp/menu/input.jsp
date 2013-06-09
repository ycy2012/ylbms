<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/jsp/inc/jBox.jsp"%>
<link href="${ctx}/styles/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form method="post" action="${ctx}/menu/add" class="form-horizontal required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${menu.id}">
			<div class="control-group">
				<label class="control-label">上级菜单:</label>
				<div class="controls">
					<tags:treeselect id="menu" name="parent.id"
						value="${menu.parent.id}" labelName="parent.name"
						labelValue="${menu.parent.name}" title="菜单" url="/menu/treeData"
						extId="${menu.id}" />
				    
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">名称:</label>
				<div class="controls">
					<input type="text" name="name" value="${menu.name}"
						class="required">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">链接:</label>
				<div class="controls">
					<input type="text" name="href" value="${menu.href}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">目标:</label>
				<div class="controls">
					<input type="text" name="target" value="${menu.target}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">图标:</label>
				<div class="controls">
					<tags:iconselect id="icon" name="icon" value="${menu.icon}"></tags:iconselect>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">排序:</label>
				<div class="controls">
					<input type="text" name="sort" value="${menu.sort}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">可见:</label>
				<div class="controls">
					<%--
				<form:radiobuttons path="isShow" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			 --%>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">权限标识:</label>
				<div class="controls">
					<input type="text" name="permission" value="${menu.permission}">
				</div>
			</div>
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
