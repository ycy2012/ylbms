<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<script type="text/javascript">
<!--
	function add() {
		var $box = $.pdialog.getCurrent();
		var ids = new Array();
		$box.find("input:checked").filter("[name='ids']").each(function(i) {
			var val = $(this).val();
			ids[i] = val;
			i++;
		});
		if (ids.length > 0) {
			window.parent.addTr(ids);
			$.pdialog.closeCurrent();//关闭dialog
		}
	}
	$(document).ready(function() {
		var url=window.parent.getURL();
		$("#form").attr("action", url);
		$("#pagerForm").attr("action", url);
	});
//-->
</script>
<form id="pagerForm" method="post" >
	<input type="hidden" name="pageNum" value="${page.pageNum}" /> <input
		type="hidden" name="numPerPage" value="${page.numPerPage}" />
</form>
<div class="pageHeader">
	<form onsubmit="return dialogSearch(this);" id="pagerForm" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>物资名称：<input type="text" name="filter_LIKES_wzname"
						value="${param['filter_LIKES_wzname']}" /></td>
					<td>当前状态:</td>
					<td><select class="combox" name="filter_EQS_status">
							<option value="1">无效</option>
							<option value="0">有效</option>
							<option value="" selected>请选择</option>
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
					<li><a class="button" href="javascript:add();" title="提交到单据"><span>提交</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th>物资编码</th>
				<th>物资名称</th>
				<th>规格型号</th>
				<th>当前位置</th>
				<th>当前状态</th>
				<th>备注信息</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="acc">
				<tr target="sid_singleInfo" rel="${acc.mid}">
					<td><input name="ids"
						value="${acc.mid}#${acc.owercode}#${acc.wzname}#${acc.spectype.speName}#${acc.location.fullName}#${acc.location.wzId}#${acc.state.id}"
						type="checkbox"></td>
					<td>${acc.owercode}</td>
					<td>${acc.wzname}</td>
					<td>${acc.spectype.speName}</td>
					<td>${acc.location.fullName}</td>
					<td>${acc.state.stateName}</td>
					<td>${acc.remark}</td>
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