<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<script type="text/javascript">
<!--
	function setWzInfo(id, name) {
		$("#locationId").val(id);
		$("#locationName").val(name);
	}
//-->
</script>
<form id="pagerForm" method="post" action="${ctx}/single/list">
	<input type="hidden" name="pageNum" value="${page.pageNum}" /> <input
		type="hidden" name="numPerPage" value="${page.numPerPage}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/single/list"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>物资编码：<input type="text" name="owercode" /></td>
					<td>物资名称：<input type="text" name="wzname" /></td>
					<td>当前位置：</td>
					<td><input name="location.wzId" class="required" type="hidden"
						id="locationId" /> <input type="text" readonly="readonly"
						id="locationName"></td>
					<td><a class="btnLook" href="${ctx}/tdmis/commUi" width="300"
						height="400" mask="true" target="dialog">选择位置信息</a></td>
					<td>当前状态：</td>
					<td><select name="status" class="combox">
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
					<li><a class="button" href="${ctx}/single/query"
						target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="base:single:add">
				<li><a class="add" href="${ctx}/single/addUi" target="navTab"
					mask="true" title="添加单件信息"><span>添加</span></a></li>
			</shiro:hasPermission>
			<li class="line">line</li>
			<shiro:hasPermission name="base:single:delete">
				<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
					postType="string" href="${ctx}/single/delByIds" class="delete"><span>批量删除</span></a></li>
			</shiro:hasPermission>
			<li class="line">line</li>
			<shiro:hasPermission name="base:single:add">
				<li><a class="edit" href="${ctx}/single/importUi"
					target="dialog" mask="true" title="导入单件信息"><span>EXCEL导入</span></a></li>
			</shiro:hasPermission>
			<li class="line">line</li>

			<li><a class="icon" href="${ctx}/single/export"
				target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>

		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="26"><input type="checkbox" group="ids"
					class="checkboxCtrl"></th>
				<th>物资编码</th>
				<th>物资名称</th>
				<th>规格型号</th>
				<th>当前位置</th>
				<th>当前状态</th>
				<th>资产种类</th>
				<th>生产厂家</th>
				<th>出厂编号</th>
				<%-- 	<th>检测日期</th>
			 <th>所属单位</th>
				<th>有效日期</th>
				<th>报废日期</th>
				<th>启用日期</th>
				<th>计量类别</th>
				<th>精确度</th>
				<th>使用次数</th>
				<th>价格</th>
				<th>能否能源器具</th>
				<th>测量范围</th>
				<th>是否强检</th>
				<th>出厂日期</th>
				<th>ABC形式</th>
				<th>固定资产编码</th>
				<th>条码号</th>
				<th>安装位置</th>
				<th>购入日期</th>
				<th>四位定号</th>
				<th>其他指标</th>
				<th>录入人员</th>
				<th>录入时间</th>
				<th>状态信息</th>--%>
				<th>备注信息</th>
				<th>操作</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="acc">
				<tr target="sid_single" rel="${acc.mid}">
					<td><input name="ids" value="'${acc.mid}'" type="checkbox"></td>
					<td>${acc.owercode}</td>
					<td>${acc.wzname}</td>
					<td>${acc.spectype.speName}</td>
					<td>${acc.location.fullName}</td>
					<td>${acc.state.stateName}</td>
					<td>${acc.classId.value}</td>
					<td>${acc.factory.value}</td>
					<td>${acc.factoryCode}</td>
					<%--<td>${acc.jdtime}</td>
					<td>${acc.scunit}</td>
					<td>${acc.yxTime}</td>
					<td>${acc.bfTime}</td>
					<td>${acc.qyTime}</td>
					<td>${acc.typeId}</td>
					<td>${acc.zqd}</td>
					<td>${acc.userTimes}</td>
					<td>${acc.price}</td>
					<td>${acc.isnyqj}</td>
					<td>${acc.clfw}</td>
					<td>${acc.isqj}</td>
					<td>${acc.chcDate}</td>
					<td>${acc.gdzcCode}</td>
					<td>${acc.txCode}</td>
					<td>${acc.azLocation}</td>
					<td>${acc.grDate}</td>
					<td>${acc.shdw}</td>
					<td>${acc.other}</td>
					<td>${acc.creater}</td>
					<td>${acc.createDate}</td>
					<td>${acc.status}</td>--%>
					<td>${acc.remark}</td>

					<td><shiro:hasPermission name="base:single:delete">
							<a title="删除单件信息" target="ajaxTodo"
								href="${ctx}/single/delete/${acc.mid}" class="btnDel">删除单件信息</a>
						</shiro:hasPermission> <shiro:hasPermission name="base:single:edit">
							<a title="编辑单件信息" target="navTab"
								href="${ctx}/single/edit/${acc.mid}" class="btnEdit">编辑单件信息</a>
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