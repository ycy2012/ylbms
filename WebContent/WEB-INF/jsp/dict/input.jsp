<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/dict/add"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${obj.id}">
			<p>
				<label>标签：</label> <input name="label" type="text" size="30"
					value="${obj.label}" class="required" maxlength="150" />
			</p>
			<p>
				<label>键值：</label> <input name="value" type="text" size="30"
					value="${obj.value}" maxlength="100" class="required" />
			</p>
			<p>
				<label>类型：</label><select class="combox required" name="type" >
				         <option value="">选择类型</option>
				         <option value="factory_type">厂家信息</option>
				         <option value="class_type">资产种类</option>
				         <option value="jltype_type">计量类别</option>
				    </select>
			</p>
			<p>
				<label>描述：</label> <input name="desciption" type="text" size="30"
					value="${obj.desciption}"  maxlength="150" />
			</p>
			<p>
				<label>排序：</label> <input name="sort" type="text" size="30"
					value="${obj.sort}"  maxlength="10" class="required" />
			</p>
			<p>
				<label>状态：</label> <select name="delFlag" class="combox">
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
