<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<form id="pagerForm" method="post" action="${ctx}/single/list">
	<input type="hidden" name="page.pageNo" value="${page.pageNo}" /> <input
		type="hidden" name="page.pageSize" value="${page.pageSize}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/single/list"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>物资编码：<input type="text" name="filter_LIKES_owercode"  value="${param['filter_LIKES_owercode']}"/></td>
					<td>物资名称：<input type="text" name="filter_LIKES_wzname"  value="${param['filter_LIKES_wzname']}"/></td>
					<td>当前位置：<input type="text" name="filter_LIKES_location"  value="${param['filter_LIKES_location']}"/></td>
					<td>当前状态： <select name="filter_EQS_state" value="${param['filter_EQS_state']}">
							<option value="1">无效</option>
							<option value="0">有效</option>
							<option value="" selected>请选择</option>
					</select>
				</td>
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
			<li><a class="add" href="${ctx}/single/addUi" target="navTab"
				mask="true" title="添加单件信息"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
				postType="string" href="${ctx}/user/delByIds/{sid_user}"
				class="delete"><span>批量删除</span></a></li>
			<li><a class="edit" href="${ctx}/single/edit/{sid_singleInfo}"
				target="dialog" mask="true" title="修改单件信息"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="#" target="dwzExport"
				targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
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
			 <!-- <th>资产种类</th>
				<th>生产厂家</th>
				<th>出厂编号</th>
				<th>检测日期</th>
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
				<th>状态信息</th>-->
				<th>备注信息</th> 
				<th>操作</th> 
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="acc">
				<tr target="sid_singleInfo" rel="${acc.mid}">
					<td><input name="ids" value="'${acc.mid}'" type="checkbox"></td>
					<td>${acc.owercode}</td>
					<td>${acc.wzname}</td>
					<td>${acc.spectype}</td>
					<td>${acc.location}</td>
					<td>${acc.state==0?'有效':'无效'}</td>
					<!-- <td>${acc.classId}</td>
					<td>${acc.factory}</td>
					<td>${acc.factoryCode}</td>
					<td>${acc.jd_time}</td>
					<td>${acc.sc_unit}</td>
					<td>${acc.yx_Time}</td>
					<td>${acc.bf_Time}</td>
					<td>${acc.qy_Time}</td>
					<td>${acc.type_Id}</td>
					<td>${acc.zqd}</td>
					<td>${acc.userTimes}</td>
					<td>${acc.price}</td>
					<td>${acc.isnyqj}</td>
					<td>${acc.clfw}</td>
					<td>${acc.isqj}</td>
					<td>${acc.chc_Date}</td>
					<td>${acc.gdzc_Code}</td>
					<td>${acc.tx_Code}</td>
					<td>${acc.az_Location}</td>
					<td>${acc.gr_Date}</td>
					<td>${acc.shdw}</td>
					<td>${acc.other}</td>
					<td>${acc.creater}</td>
					<td>${acc.createDate}</td>
					<td>${acc.status}</td>-->
					<td>${acc.remark}</td> 
					<td><a title="删除单件信息" target="ajaxTodo"
						href="${ctx}/single/delete/${acc.mid}" class="btnDel">删除单件信息</a>
						 <a title="编辑单件信息" target="dialog"
						href="${ctx}/single/edit/${acc.mid}" class="btnEdit">编辑单件信息</a>
					</td> 
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
						${page.pageSize eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
				</c:forEach>
			</select> <span>条，共${page.totalCount}条</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${page.totalCount}" numPerPage="${page.pageSize}"
			pageNumShown="10" currentPage="${page.pageNo}"></div>
	</div>
</div>