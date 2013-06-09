<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/header.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/location/add"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${obj.id}">
			 <input type="hidden" name="parent.id" value="${obj.parent.id }">
			 <input type="text" name="allName" value="${all}">;
			<p>
				<label>位置名称：</label> <input name="locationName" type="text" size="30"
					value="${obj.locationName}" class="required" maxlength="150" />
			</p>
			<p>
				<label>排序：</label> <input name="sort" type="text" size="30"
					value="${obj.sort}" maxlength="100" /> 
			</p>
			<p>
				<label>状态：</label> <select name="status" class="combox">
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
