<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<form id="pagerForm" method="post" action="user/list">
	<input type="hidden" name="pageNum" value="${page.pageNum}" /> 
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/role/list" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>角色名称：<input type="text" name="filter_LIKES_name" value="${param['filter_LIKES_name']}"/></td>
					<td>角色状态：<input type="text" name="filter_EQS_delFlag" value="${param['filter_EQS_delFlag']}"/></td>
					<%--
 			    	<td>组织机构：<input type="text" name="organization" value="${obj.o.organization}/></td> --%>			        
<%-- 			    	<td>创建人：<input type="text" name="createUser" value="${obj.o.createUser}/></td> --%>			        
<%-- 			    	<td>创建时间：<input type="text" name="createDate" value="${obj.o.createDate}/></td> --%>			        
<%-- 			    	<td>修改人：<input type="text" name="modifyUser" value="${obj.o.modifyUser}/></td> --%>			        
<%-- 			    	<td>修改时间：<input type="text" name="modifyDate" value="${obj.o.modifyDate}/></td> --%>			        
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/role/addUi" target="dialog" mask="true" title="添加角色信息"><span>添加</span></a></li>
			<li><a class="edit" href="${ctx}/role/editUi/{sid_role}" target="dialog" mask="true" title="修改角色信息"><span>修改</span></a></li>
			<li class="line">line</li>
			<!--  
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		-->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>角色名称</th>
				<th>当前状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="acc">
				<tr target="sid_role" rel="${acc.id }">
				<td><input name="ids" value="'${acc.id}'" type="checkbox"></td>
					<td>${acc.name}</td>
					<td>${acc.delFlag==0?'有效':'无效'}</td>
				<td>
				<a title="删除角色信息" target="ajaxTodo" href="${ctx}/role/delete/${acc.id}" class="btnDel">删除角色信息</a>
				<a title="添加权限信息" target="dialog" href="${ctx}/role/permUi/${acc.id}" class="btnPerm" width="250">添加权限</a>
				<a title="编辑角色信息" target="dialog" href="${ctx}/role/editUi/${acc.id}" class="btnEdit">编辑角色信息</a>
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