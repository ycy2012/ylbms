<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>

<div class="pageContent">
	<form method="post" action="${ctx}/single/list" class="pageForm" onsubmit="return navTabSearch(this);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>物资名称：</label>
				<input type="text" size="25" name=wzname"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>当前状态：</label>
				<input type="text" size="25" name="stateName" class="lettersonly"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>生成厂家：</label>
				<input type="text" size="25" name="factory" class="alphanumeric"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>检测日期：</label>
				<input type="text" size="25" name="jdtime" class="date"/>
				<span class="inputInfo">填写全称</span>
			</div>
			<div class="unit">
				<label>固定资产编码：</label>
				<input type="text" size="25" name="gdzcCode"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>条码号：</label>
				<input type="text" size="25" name="txCode"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<%-- 
			<div class="unit">
				<label>客户类型：</label>
				<input type="text" size="25" />
				<span class="inputInfo">可多选</span>
			</div>
			<div class="unit">
				<label>信用等级：</label>
				<input type="text" size="25" />
				<span class="inputInfo">可多选</span>
			</div>
			<div class="unit">
				<label>所属行业：</label>
				<input type="text" size="25" />
				<span class="inputInfo">可多选</span>
			</div>
			<div class="unit">
				<label>曾用名称：</label>
				<input type="text" size="25" />
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>检测日期：</label>
				<input type="text" size="25" name="date1" class="date"/>
				<span class="inputInfo">大于等于，小于等于</span>
			</div>
			<div class="unit">
				<label>管户经理：</label>
				<input type="text" size="25" />
				<span class="inputInfo">全辖查询时用</span>
			</div>
			<div class="divider">divider</div>
			<div class="unit">
				<label>排序条件：</label>
				<select>
					<option>按客户号倒排</option>
					<option>按建档日期倒排</option>
					<option>按信用等级顺排</option>
					<option>按客户号顺排</option>
					<option>按建档日期顺排</option>
					<option>按所属行业顺排</option>
				</select>
			</div>--%>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">开始检索</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
