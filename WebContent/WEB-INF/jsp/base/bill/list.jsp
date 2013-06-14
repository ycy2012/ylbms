<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/bill/list">
	<input type="hidden" name="page.pageNo" value="${page.pageNo}" /> 
	<input type="hidden" name="page.pageSize" value="${page.pageSize}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/bill/list" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>单据名称：<input type="text" name="filter_LIKES_fullname" value="${param['filter_LIKES_fullname']}"/></td>
					<td>单据状态：<input type="text" name="filter_EQS_enabled" value="${param['filter_EQS_enabled']}"/></td>
					<%--
 			    	<td>组织机构：<input type="text" name="organization" value="${obj.o.organization}/></td> --%>			        
<%-- 			    	<td>创建人：<input type="text" name="createbill" value="${obj.o.createbill}/></td> --%>			        
<%-- 			    	<td>创建时间：<input type="text" name="createDate" value="${obj.o.createDate}/></td> --%>			        
<%-- 			    	<td>修改人：<input type="text" name="modifybill" value="${obj.o.modifybill}/></td> --%>			        
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
			<li><a class="add" href="${ctx}/bill/addUi" target="dialog" mask="true" title="添加单据信息"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="djIds" postType="string" href="${ctx}/bill/delBydjIds/{sdjId_bill}" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="${ctx}/bill/editUi/{sdjId_bill}" target="dialog" mask="true" title="修改单据信息"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		    <li class="line">line</li>
		</ul>
	</div>
	<table class="table" wdjIdth="100%" layoutH="138">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="djIds" class="checkboxCtrl"></th>
				<th>单据djId</th>
				<th>真实姓名</th>
				<th>当前密码</th>
				<th>单据类型</th>
				<th>创建时间</th>
				<th>登录IP</th>
				<th>备注信息</th>
				<th>当前状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="acc">
				<tr target="sdjId_bill" rel="${acc.djId }">
				<td><input name="djIds" value="'${acc.djId}'" type="checkbox"></td>
					<td>${acc.djTitle}</td>
					<td>${acc.sendLocation}</td>
					<td>${acc.acceptLocation}</td>
					<td><fmt:formatDate value="${acc.sxDate}" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${acc.createDate}" pattern="yyyy-MM-dd"/> </td>
					<td>${acc.llren}</td>
					<td>${acc.llUnit}</td>
					<td>${acc.remark}</td>
					<td>${acc.enabled==0?'有效':'无效'}</td>
				<td>
				<a title="删除单据信息" target="ajaxTodo" href="${ctx}/bill/delete/${acc.djId}" class="btnDel">删除单据信息</a>
				<a title="授权" target="dialog" href="${ctx}/role/permUi/${acc.djId}" class="btnView">授权</a>
				<a title="编辑单据信息" target="dialog" href="${ctx}/bill/editUi/${acc.djId}" class="btnEdit">编辑单据信息</a>
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