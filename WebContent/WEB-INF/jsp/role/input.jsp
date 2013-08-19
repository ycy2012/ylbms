<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/role/add"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${obj.id}">
			<p>
				<label>角色名称：</label> <input name="name" type="text" size="30"
					value="${role.name}" class="required" maxlength="150" />
			</p>
			<%-- 
			<p>
			  <label>添加权限 :</label>
			  <div id="menuTree1" class="ztree" style="margin-top:3px;float:left;"></div>
			  <input type="hidden" name="menuIds">
			</p>
			--%>
			<p>
				<label>用户状态：</label> <select name="delFlag" class="combox">
					<option value="0">有效</option>
					<option value="1">无效</option>
				</select>
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
