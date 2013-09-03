<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/single/list" class="pageForm"
		onsubmit="return navTabSearch(this);">
		<div class="pageFormContent" layoutH="58">
			<!--  
			<div class="unit">
				<label>出厂编码：</label> <input type="text" size="25" name="factoryCode" />
				<span class="inputInfo">出厂编码</span>
			</div>
			-->
			<div class="unit">
				<label>规格型号：</label> <select name="spectype.speId"
					class="combox ">
					<option value="">请选择规格型号</option>
					<c:forEach items="${fns:getSpectypeList()}" var="sp">
						<option value="${sp.value}">${sp.text}</option>
					</c:forEach>	<span class="inputInfo">规格型号</span>
				</select>
			</div>
			<div class="unit">
				<label>检定日期：</label> <input type="text" size="25" name="jdtime"
					class="date" /><a class="inputDateButton" href="javascript:;">选择</a>
				<span class="inputInfo">检定日期</span>
			</div>
			<div class="unit">
				<label>有效日期：</label> <input type="text" name="yxTime" class="date"
					size="25" value="${obg.yx_Time}" /><a class="inputDateButton"
					href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>生产厂家：</label> <select name="factory.id" class="combox">
					<option value="">请选择厂家信息</option>
					<c:forEach items="${fns:getDictList('factory_type')}" var="f">
						<option value="${f.id}">${f.value}</option>
					</c:forEach>
				</select> <span class="inputInfo">选择厂家信息</span>
			</div>
			<div class="unit">
				<label>测量范围：</label> <select name="clfw.id" class="combox">
					<c:forEach items="${fns:getDictList('clfw_type')}" var="v">
						<option value="${v.id}">${v.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">开始检索</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="reset">清空重输</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
