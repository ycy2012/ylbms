<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<!DOCTYPE html>
<html style="overflow-x: hidden; overflow-y: auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../inc/treetable.jsp"%>
<style type="text/css">
.table td i {
	margin: 0 2px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#treeTable").treeTable({
			expandLevel : 4
		});
	});
</script>
</head>
<body style="overflow-x: hidden; overflow-y: auto">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="${ctx}/menu/list"
			method="post"></form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li class="line">line</li>
				<li><a class="add" href="${ctx}/menu/addUi" target="dialog"
					mask="true" title="添加菜单信息" height="400"><span>添加</span></a></li>
				<li class="line">line</li>

			</ul>
		</div>
		<div style="height: 700px;; overflow: auto;">
		<table id="treeTable"
			class="table_boot table-striped table-bordered table-condensed table-hover">
			<thead>
				<tr>
					<th width="15%">名称</th>
					<th width="15%">链接</th>
					<th width="8%">REL属性</th>
					<th width="8%">TATGET属性</th>
					<th width="8%">排序</th>
					<th width="8%">可见</th>
					<th width="12%">权限标识</th>
					<th width="25%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page}" var="menu"> 
					<tr id="${menu.id}" pId="${menu.parent.id ne 1?menu.parent.id:'0'}">
						<td><i class="icon-${not empty menu.icon?menu.icon:' hide'}"></i>${menu.name}</td>
						<td>${menu.href}</td>
						<td>${menu.rel}</td>
						<td>${menu.target}</td>
						<td>${menu.sort}</td>
						<td>${menu.isShow eq '0' ?'显示':'隐藏'}</td>
						<td>${menu.permission}</td>
						<td><a class="btn btn-small" target="navTab"
							href="${ctx}/menu/editUi/${menu.id}"><i class="icon-edit"></i>修改</a>
							<a class="btn btn-small" target="ajaxTodo"
							href="${ctx}/menu/delete/${menu.id}" title="删除该选项吗?"><i
								class="icon-remove"></i>删除</a> <a class="btn btn-small"
							target="navTab" href="${ctx}/menu/nextUi?parent.id=${menu.id}"><i
								class="icon-plus"></i>添加下级菜单</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>