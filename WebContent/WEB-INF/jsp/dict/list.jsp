<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../inc/header.jsp"%>
<form id="pagerForm" method="post" action="user/list">
	<input type="hidden" name="page.pageNo" value="${page.pageNo}" /> 
	<input type="hidden" name="page.pageSize" value="${page.pageSize}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/user/list" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>用户名称：<input type="text" name="filter_LIKES_fullname" value="${param['filter_LIKES_fullname']}"/></td>
					<td>用户状态：<input type="text" name="filter_EQS_enabled" value="${param['filter_EQS_enabled']}"/></td>
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
					<li><a class="button" href="Account/queryUi" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/user/addUi" target="dialog" mask="true" title="添加用户信息"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx}/user/delByIds/{sid_user}" class="delete"><span>批量删除</span></a></li>
			<li><a class="edit" href="Account/editUi?ID={sid_account}" target="dialog" mask="true" title="修改用户信息"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>用户ID</th>
				<th>真实姓名</th>
				<th>当前密码</th>
				<th>用户类型</th>
				<th>创建时间</th>
				<th>登录IP</th>
				<th>最新登录时间</th>
				<th>当前状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="acc">
				<tr target="sid_user" rel="${acc.id }">
				<td><input name="ids" value="'${acc.id}'" type="checkbox"></td>
					<td>${acc.loginName}</td>
					<td>${acc.fullname}</td>
					<td>${acc.password}</td>
					<td>${acc.usertype}</td>
					<td>${acc.createDate}</td>
					<td>${acc.loginIP}</td>
					<td>${acc.loginDate}</td>
					<td>${acc.enabled==0?'有效':'无效'}</td>
				<td>
				<a title="删除用户信息" target="ajaxTodo" href="${ctx}/user/delete/${acc.id}" class="btnDel">删除用户信息</a>
				<a title="授权" target="dialog" href="${ctx}/role/permUi/${acc.id}" class="btnView">授权</a>
				<a title="添加角色" target="dialog" href="${ctx}/user/roleUi/${acc.id}" class="btnPerm">添加角色</a>
				<a title="编辑用户信息" target="dialog" href="${ctx}/user/editUi/${acc.id}" class="btnEdit">编辑用户信息</a>
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
				<option value="${s.index}" ${page.pageSize eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select>
			<span>条，共${page.totalCount}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.pageNo}"></div>
	</div>
</div>