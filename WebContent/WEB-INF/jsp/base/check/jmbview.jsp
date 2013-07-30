<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<div class="pageContent">

	<div class="viewInfo" layoutH="97">
	<c:set value="${jmbInfo}" var="obj" />
		<dl>
			<dt>名称：</dt>
			<dd>${obj.jmbName}</dd>
		</dl>
		<dl>
			<dt>精密表型号：</dt>
			<dd>${obj.jmbType}</dd>
		</dl>
		<dl>
			<dt>出厂编码：</dt>
			<dd>${obj.factoryCode}</dd>
		</dl>
		<dl>
			<dt>精密表表编号：</dt>
			<dd>${obj.jmbCode}</dd>
		</dl>
		<dl>
			<dt>测量范围：</dt>
			<dd>${obj.clfw}</dd>
		</dl>
		<dl>
			<dt>准确度等级：</dt>
			<dd>${obj.grade}</dd>
		</dl>
		<dl>
			<dt>证书编码：</dt>
			<dd>${obj.zhShCode}</dd>
		</dl>
		<dl>
			<dt>生产厂家：</dt>
			<dd>${obj.madeIn}</dd>
		</dl>
		<dl>
			<dt>检定单位：</dt>
			<dd>${obj.jdUnit}</dd>
		</dl>
		<dl>
			<dt>检定日期：</dt>
			<dd><fmt:formatDate value="${obj.jdDate}" pattern="yyyy-MM-dd"/></dd>
		</dl>
		<dl>
			<dt>证书有效期：</dt>
			<dd><fmt:formatDate value="${obj.yxDate}" pattern="yyyy-MM-dd"/></dd>
		</dl>
		<dl>
			<dt>创建时间：</dt>
			<dd><fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd"/></dd>
		</dl>
		<dl>
			<dt>检定人员：</dt>
			<dd>${obj.creater.fullname}</dd>
		</dl>
		<dl>
			<dt>状态：</dt>
			<dd>${obj.status==0?'有效':'无效'}</dd>
		</dl>
		<dl>
			<dt>备注：</dt>
			<dd>${obj.remark}</dd>
		</dl>
		<div class="divider"></div>
	</div>

	<div class="formBar">
		<ul>
			<li><div class="button">
					<div class="buttonContent">
						<button type="button" class="close">关闭</button>
					</div>
				</div></li>
		</ul>
	</div>
</div>
