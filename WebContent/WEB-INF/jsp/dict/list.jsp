<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/dict/list">
	<input type="hidden" name="pageNum" value="${page.pageNum}" /> 
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/dict/list" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
				    <td>标签类型：</td>
				    <td><select class="combox" name="filter_EQS_type">
				         <option value="">选择类型</option>
				         <option value="factory_type">厂家信息</option>
				         <option value="class_type">资产种类</option>
				         <option value="jltype_type">计量类别</option>
				    </select></td>
					<td >名称：<input type="text" name="filter_LIKES_fullname" value="${param['filter_LIKES_fullname']}"/></td>
					<td>状态：</td>
					<td><select name="filter_EQS_delFlag" class="combox">
					     <option value="0">有效</option>
					     <option value="1">无效</option>
					    </select></td>
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
			<li><a class="add" href="${ctx}/dict/addUi" target="dialog" mask="true" title="添加字典信息"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="${ctx}/dict/delByIds/{sid_dict}" class="delete"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/dict/editUi/{sid_dict}" target="dialog" mask="true" title="修改字典信息"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>标签</th>
				<th>键值</th>
				<th>类型</th>
				<th>描述</th>
				<th>排序</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="acc">
				<tr target="sid_dict" rel="${acc.id}">
				<td><input name="ids" value="'${acc.id}'" type="checkbox"></td>
					<td>${acc.label}</td>
					<td>${acc.value}</td>
					<td>${acc.type}</td>
					<td>${acc.desciption}</td>
					<td>${acc.sort}</td>
					<td>${acc.delFlag==0?'有效':'无效'}</td>
				<td>
				<a title="删除字典信息" target="ajaxTodo" href="${ctx}/dict/delete/${acc.id}" class="btnDel">删除字典信息</a>
				<a title="编辑字典信息" target="dialog" href="${ctx}/dict/editUi/${acc.id}" class="btnEdit">编辑字典信息</a>
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