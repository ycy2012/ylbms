<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pageContent">
	<c:set var="ui" value="${obj.account}" />
	<form method="post" action="Account/update"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="ID" value="${ui.ID}">
			<p>
				<label>用户名称：</label> <input name="userName" type="text" size="30"
					value="${ui.userName}" class="required" maxlength="150" />
			</p>
			<p>
				<label>真实姓名：</label> <input name="description" type="text" size="30"
					value="${ui.description}" maxlength="255" />
			</p>
			<p>
				<label>用户密码：</label> <input name="password" type="text" size="30"
					value="${ui.password}" class="required" maxlength="150" />
			</p>
			<p>
				<label>部门信息：</label><select name="organization" class="combox">
					<c:forEach var="di" items="${obj.departmentInfoList}">
					<c:if test="${di.dID == ui.organization}">
					<option value="${di.dID}" selected="selected">${di.dName}</option>
					</c:if>
					<c:if test="${di.dID != ui.organization}">
					<option value="${di.dID}">${di.dName}</option>
					</c:if>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>用户组：</label><input name="roleID" type="text"
					value="${ui.roleID}">
			</p>
			<p>
				<label>用户状态：</label> <select name="status" class="combox">
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
