<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/spec/list">
	<input type="hidden" name="pageNum" value="${page.pageNum}" /> <input
		type="hidden" name="numPerPage" value="${page.numPerPage}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/spec/list"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>规格型号名称：<input type="text" name="filter_LIKES_speName"
						value="${param['filter_LIKES_speName']}" /></td>
					<td>规格型号状态： <select name="filter_EQS_status"
						value="${param['filter_EQS_status']}">
							<option value="1">无效</option>
							<option value="0">有效</option>
							<option value="" selected>请选择</option>
					</select>
					</td>
					<%--
 			    	<td>组织机构：<input type="text" name="organization" value="${obj.o.organization}/></td> --%>
					<%-- <td>创建人：<input type="text" name="createUser" value="${obj.o.createUser}/></td> --%>
					<%-- <td>创建时间：<input type="text" name="createDate" value="${obj.o.createDate}/></td> --%>
					<%-- <td>修改人：<input type="text" name="modifyUser" value="${obj.o.modifyUser}/></td> --%>
					<%-- <td>修改时间：<input type="text" name="modifyDate" value="${obj.o.modifyDate}/></td> --%>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="Account/queryUi" target="dialog"
						mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="sys:user:add">
				<li><a class="add" href="${ctx}/spec/addUi" target="dialog"
					mask="true" title="添加规格型号信息"><span>添加</span></a></li>
			</shiro:hasPermission>
			<li class="line">line</li>
			<shiro:hasPermission name="sys:user:delete">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
					postType="string" href="${ctx}/spec/deletes" class="delete"><span>批量删除</span></a></li>
			</shiro:hasPermission>
			<li class="line">line</li>
			<shiro:hasPermission name="sys:user:add">
				<li><a class="edit" href="${ctx}/spec/importUi" target="dialog"
					mask="true" title="导入规格型号信息？"><span>EXCEL导入</span></a></li>
			</shiro:hasPermission>
			<li class="line">line</li>
			<li><a class="icon" href="${ctx}/spec/export" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th>规格型号名称</th>
				<th>显示顺序</th>
				<th>规格型号状态</th>
				<th>规格型号备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="acc">
				<tr target="sid_spectype" rel="${acc.speId}">
					<td><input name="ids" value="'${acc.speId}'" type="checkbox"></td>
					<td>${acc.speName}</td>
					<td>${acc.sort}</td>
					<td>${acc.status==0?'有效':'无效'}</td>
					<td>${acc.remark}</td>
					<td><shiro:hasPermission name="sys:user:delete">
							<a title="删除规格型号信息" target="ajaxTodo"
								href="${ctx}/spec/delete/${acc.speId}" class="btnDel">删除规格型号信息</a>
						</shiro:hasPermission> <shiro:hasPermission name="sys:user:edit">
							<a title="编辑规格型号信息" target="dialog"
								href="${ctx}/spec/edit/${acc.speId}" class="btnEdit">编辑规格型号信息</a>
						</shiro:hasPermission></td>
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
		<div class="pagination" targetType="navTab"
			totalCount="${page.totalCount}" numPerPage="${page.numPerPage}"
			pageNumShown="10" currentPage="${page.pageNum}"></div>
	</div>
</div>