<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/jmbinfo/list">
	<input type="hidden" name="pageNum" value="${page.pageNum}" /> 
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/jmbinfo/list" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
				    <td>精密表名称：<input type="text" style="width: 150px;" name="filter_LIKES_jmbName" /></td>
					<td>精密表型号：<input type="text" name="filter_LIKES_jmbType"/>
					<td>当前状态： <select name="filter_LIKES_status">
							<option value="1">无效</option>
							<option value="0">有效</option>
							<option value="" selected>请选择</option>
					</select>
				</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/jmbinfo/addUI" target="navTab" mask="true" title="添加精密表信息"><span>添加</span></a></li>
			<%--<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="djIds" postType="string" href="${ctx}/jd/delBydjIds" class="delete"><span>批量删除</span></a></li> --%>
			<li class="line">line</li>
			<li><a class="edit" href="${ctx}/jmbinfo/editUi/{sdjId_jd}" target="dialog" mask="true" title="修改精密表信息"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		    <li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="djIds" class="checkboxCtrl"></th>
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
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="acc">
				<tr target="sdjId_jd" rel="${acc.jmbID}">
				<td><input name="djIds" value="'${acc.jmbID}'" type="checkbox"></td>
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
				<td>
				<a title="删除精密表信息" target="ajaxTodo" href="${ctx}/jmbinfo/delete/${acc.jmbID}" class="btnDel">删除精密表信息</a>
				<a title="查看精密表详细信息" target="dialog" href="${ctx}/jmbinfo/showview/${acc.jmbID}" class="btnView">查看精密表详细信息</a>
				<a title="编辑精密表信息" target="navTab" href="${ctx}/jmbinfo/editUi/${acc.jmbID}" class="btnEdit">编辑精密表信息</a>
				</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> 
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			<c:forEach begin="10" end="40" step="10" varStatus="s">
				<option value="${s.index}" ${page.numPerPage eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select>
			<span>条，共${page.totalCount}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.numPerPage}" pageNumShown="10" currentPage="${page.pageNum}"></div>
	</div>
</div>