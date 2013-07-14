<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@include file="../../inc/taglib.jsp"%>
<!DOCTYPE html PU/BLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>规格型号</title>
</head>
<body>
	<div class="pageContent">
		<form method="post" action="${ctx}/spec/spectype"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input type="hidden" name="speId" value="${obj.speId}">
			<div class="pageFormContent" layoutH="56">
				<p>
					<label>规格型号名称：</label> <input name="speName" type="text" size="30"
						class="required" maxlength="150" value="${obj.speName }" />
				</p>
				<p>
					<label>规格型号状态：</label> <select name="sort" class="required combox">
						<option value="">请选择</option>
						<option value="1">有效</option>
						<option value="0" selected>无效</option>
					</select>
				</p>
				<p>
					<label>显示顺序：</label> <input name="sort" type="text" size="30"
						class="required" maxlength="150" value="${obj.sort}" />
				</p>
				<p>
					<label>备注：</label>
					<textarea name="remark"></textarea>
				</p>
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
	</div>
</body>
</html>

