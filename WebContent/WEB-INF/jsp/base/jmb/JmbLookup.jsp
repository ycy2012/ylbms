<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/jmbinfo/list">
	<input type="hidden" name="pageNum" value="${page.pageNum}" /> <input
		type="hidden" name="numPerPage" value="${page.numPerPage}" />
</form>
<div class="pageHeader">
	<form onsubmit="return DialogSearch(this);"
		action="${ctx}/jmbinfo/lookUp" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>精密表名称：<input type="text" style="width: 150px;"
						name="filter_LIKES_jmbName" /></td>
					<td>精密表型号：<input type="text" name="filter_EQS_jmbType" />
					<td>精密表编码：<input type="text" name="filter_EQS_jmbCode" />
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><div class="button">
							<div class="buttonContent">
								<button type="button" multLookup="jmbIds" warn="请选择精密表信息">选择带回</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar"></div>
	<table class="table" width="100%" layoutH="138" targetType="dialog">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="jmbIds" class="checkboxCtrl"></th>
				<th>精密表名称</th>
				<th>精密表型号</th>
				<th>出厂编号</th>
				<th>精密表编码</th>
				<th>测量范围</th>
				<th>准确度等级</th>
				<th>证书编码</th>
				<th>生产厂家</th>
				<%--<th>检定单位</th>
				 <th>检定日期</th>
				<th>证书有效期</th>
				<th>检定时间</th>
				<th>检定人员</th>
				<th>状态</th>
				<th width="20%">备注</th>
				--%>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="acc">
				<tr>
					<td><input type="checkbox" name="jmbIds"
						value="{jmbID:'${acc.jmbID}', jmbCode:'${acc.jmbCode}'}" /></td>
					<td>${acc.jmbName}</td>
					<td>${acc.jmbType}</td>
					<td>${acc.factoryCode}</td>
					<td>${acc.jmbCode}</td>
					<td>${acc.clfw}</td>
					<td>${acc.grade}</td>
					<td>${acc.zhShCode}</td>
					<td>${acc.madeIn}</td>
					<%-- <td>${acc.jdUnit}</td>
					<td><fmt:formatDate value="${acc.jdDate}" pattern="yyyy-MM-dd"/> </td>
					<td><fmt:formatDate value="${acc.yxDate}" pattern="yyyy-MM-dd"/> </td>
					<td><fmt:formatDate value="${acc.createDate}" pattern="yyyy-MM-dd"/> </td>
					<td>${obj.creater.fullname}</td>
					<td>${acc.status==0?'有效':'无效'}</td>
					<td>${acc.remark}</td>
					--%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<c:forEach begin="10" end="40" step="10" varStatus="s">
					<option value="${s.index}"
						${page.numPerPage eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
				</c:forEach>
			</select> <span>条，共${page.totalCount}条</span>
		</div>
		<div class="pagination" targetType="dialog"
			totalCount="${page.totalCount}" numPerPage="${page.numPerPage}"
			pageNumShown="10" currentPage="${page.pageNum}"></div>
	</div>
</div>
