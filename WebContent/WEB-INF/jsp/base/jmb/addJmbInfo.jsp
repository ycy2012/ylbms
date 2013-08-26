<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<div class="pageContent">
	<form method="post" action="${ctx}/jmbinfo/addJmy"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" value="${obj.jmbID}" name="jmbID">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>精密表名称：</label> <input name="jmbName" type="text" size="30" value="${obj.jmbName}" />
			</p>
			<p>
				<label>精密表型号：</label>
					<input name="jmbType" type="text" size="30" value="${obj.jmbType}" />
			</p>
			<p>
				<label>出厂编号：</label> 
					<input name="factoryCode" class="required"
					type="text" size="30" value="${obj.factoryCode}" />
				</select> 
			</p>
			<p>
				<label>精密表编码：</label>
					<input name="jmbCode" class="required"
					type="text" size="30" value="${obj.jmbCode}" />
			</p>
			<p>
				<label>测量范围：</label> <select name="clfw" class="required combox"
					value="${obj.clfw}">
					<option value="" selected>请选择</option>
					<option value="1">已租</option>
					<option value="0">待租</option>
				</select>
			</p>
			<p>
				<label>准确度等级：</label> <select name="grade" class="required combox"
					value="${obj.grade }">
					<option value="" selected>请选择</option>
					<option value="0">个人</option>
					<option value="1">公司</option>
				</select>
			</p>
			<p>
				<label>证书编码：</label> <input type="text" size="30" name="zhShCode" value="${obj.zhShCode}"/>
			</p>
			<p>
				<label>生产厂家：</label> <input type="text" name="madeIn"
					class="textInput" size="30"  value="${obj.madeIn}"/>
			</p>
			<p>
				<label>检定单位：</label> <input type="text" name="jdUnit" 
					size="30" value="${obj.jdUnit}"/>
			</p>
			<p>
				<label>检定日期：</label> <input type="text" name="jdDate" class="date"
					size="30"    value="<fmt:formatDate  value="${obj.jdDate}" pattern="yyyy-MM-dd"/>"/>
			</p>
			
			<p>
				<label>证书有效期：</label> <input type="text" name="yxDate"
					class="date" size="30"    value="${obj.jdDate}" />
			</p>
			<p> 
				<label>状态：</label> <select name="status" class="required combox"
					value="${obj.status}">
					<option value="" selected>请选择</option>
					<option value="0">有效</option>
					<option value="1">无效</option>
				</select>
			</p>
			<p>
				<label>备注：</label> <input type="text" name="remark"
					class="textInput" size="30" value="${obj.remark}"/>
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
