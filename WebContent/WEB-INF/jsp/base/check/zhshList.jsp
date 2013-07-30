<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/jdzhs/list">
	<input type="hidden" name="pageNum" value="${page.pageNum}" /> 
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/jdzhs/list" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
				    <td>记录编号：<input type="text" style="width: 150px;" name="filter_EQS_zId" /></td>
					<td>
					   制作日期：<input type="text" name="filter_LIKES_createDate" class="date" readonly="true"/>
					</td>
					<td>状态：</td>
					<td><select name="filter_EQS_status" class="combox">
					   <option value="">选择状态</option>
					    <option value="0">有效</option>
					   <option value="1">无效</option>
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
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/jdzhs/addUi" target="navTab" mask="true" title="制作检定记录卡"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="Ids" postType="string" href="${ctx}/jdzhs/delByIds" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="${ctx}/jdzhs/editUi/{sdjId_jd}" target="dialog" mask="true" title="修改检定记录信息"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		    <li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="Ids" class="checkboxCtrl"></th>
				<th>证书编号</th>
				<th>检定记录名称</th>
				<th>送检单位</th>
				<th>结论</th>
				<th>制单时间</th>
				<th>制单人</th>
				<th width="20%">备注</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="acc">
				<tr target="sdjId_jd" rel="${acc.zId }">
				<td><input name="Ids" value="'${acc.zId}'" type="checkbox"></td>
				    <td>${acc.zId}</td>
					<td>${acc.zTitle}</td>
					<td>${acc.sjUnit}</td>
					<td>${acc.result}</td>
					<td><fmt:formatDate value="${acc.createDate}" pattern="yyyy-MM-dd"/> </td>
					<td>${acc.createUser.fullname}</td>
					<td>${acc.remark}</td>
					<td>${acc.status==0?'有效':'无效'}</td>
				<td>
				<a title="删除检定记录信息" target="ajaxTodo" href="${ctx}/jdzhs/delete/${acc.zId}" class="btnDel">删除检定记录信息</a>
				<a title="查看检定记录信息" target="navTab" href="${ctx}/jdzhs/viewUi/${acc.zId}" class="btnView">查看检定记录信息</a>
				<%--
				<a title="编辑检定记录信息" target="navTab" href="${ctx}/jdzhs/editUi/${acc.jdID}" class="btnEdit">编辑检定记录信息</a>
				 --%>
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