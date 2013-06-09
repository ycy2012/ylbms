<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<div class="pageContent">
	<form  action="${ctx}/location/add" method="post"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>父节点：</label>
				 <input type="hidden" name="parentIds" value="${pids}">
				 <input type="hidden" name="parent.id" value="${obj.id }">
				  <input type="hidden"  name="allName" value="${all}">
				 <input  type="text" readonly value=" ${obj.locationName}" />
			</p>
			<p>
				<label>位置名称：</label> <input name="locationName" type="text"
					size="30" value="" maxlength="255" />
			</p>

			<p>
				<label>排序：</label> <input name="sort" type="text" size="30"
					value="" maxlength="255" />
			</p>
			<p>
				<label>状态：</label> <select name=status class="combox">
					<option value="0">有效</option>
					<option value="1">无效</option>
				</select> <input type="hidden" value="0" name="usertype">
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
