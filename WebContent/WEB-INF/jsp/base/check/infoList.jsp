<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/jd/list">
	<input type="hidden" name="pageNum" value="${page.pageNum}" /> <input
		type="hidden" name="numPerPage" value="${page.numPerPage}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/jd/iList"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>检定日期：<input type="text" name="filter_LIKES_createDate"
						class="date" readonly="true" />
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
			<%--
			<li><a class="add" href="${ctx}/jd/addUi" target="navTab" mask="true" title="制作检定记录卡"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="djIds" postType="string" href="${ctx}/jd/delBydjIds" class="delete"><span>批量删除</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="${ctx}/jd/editUi/{sdjId_jd}" target="dialog" mask="true" title="修改检定记录信息"><span>修改</span></a></li>
			 --%>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="djIds"
					class="checkboxCtrl"></th>
				<th>物资名称</th>
				<th>型号规格</th>
				<th>测量范围MPa</th>
				<th>出厂编号</th>
				<th>检定日期</th>
				<th>有效日期</th>
				<th>生产厂家</th>
				<th>安装位置</th>
				<th>精密表编号</th>
				<th>证书编号</th>
				<th>证书有效期</th>
				<th>设备编号</th>
				<th>备&nbsp;&nbsp; 注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="de">
				<tr target="sdjId_jd" rel="${de.single.mid}">
					<td><input name="Ids" value="'${de.single.mid}'" type="checkbox"></td>
					<td>${de.single.wzname}</td>
					<td>${de.single.spectype.speName}</td>
					<td>${de.single.clfw}</td>
					<td>${de.single.factoryCode}</td>
					<td><fmt:formatDate value="${de.jdDate}" pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${de.yxDate}" pattern="yyyy-MM-dd" /></td>
					<td>${de.single.factory}</td>
					<td>${de.azLocation }</td>
					<td>${de.jmbCode }</td>
					<td>${de.zShuCode}</td>
					<td><fmt:formatDate value="${de.zsyxDate}" pattern="yyyy-MM-dd" /></td>
					<td>${de.shbCode}</td>
					<td>${de.remark}</td>
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