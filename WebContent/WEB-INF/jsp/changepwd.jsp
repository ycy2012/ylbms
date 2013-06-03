<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="inc/header.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/user/editPwd"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
		<div class="unit">
				<label>当前用户姓名：</label> <input type="text" name="description"
					value="${obj.description}" readonly="readonly" />
			</div>
			<div class="unit">
			    <input type="hidden" value="${obj.ID}" name="ID">
				<label>当前用户ID：</label> <input type="text" name="userName"
					value="${obj.userName}" readonly="readonly" />
			</div>
			<div class="unit">
				<label>旧密码：</label> <input type="password" name="oldPassword"
					size="30" maxlength="20" class="required" />
			</div>
			<div class="unit">
				<label>新密码：</label> <input type="password" id="cp_newPassword"
					name="password"  size="30" minlength="6"
					maxlength="20" class="required alphanumeric" />
			</div>
			<div class="unit">
				<label>重复输入新密码：</label> <input type="password" name="rnewpassword"
					size="30" equalTo="#cp_newPassword" class="required alphanumeric" />
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">修改</button>
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

</div>