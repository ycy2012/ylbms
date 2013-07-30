<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>展示页面</title>
</head>
<c:set value="${master}" var="ma" />
<body>
	<div class="pageContent">
		<div class="pageHeader">
			<p>
			<h1 style="text-align: center;">${ma.title}</h1>
			</p>
			<br>
			<p style="float: right;">
				<label>制作时间:</label>
				<fmt:formatDate value="${ma.createDate}" pattern="yyyy年MM月dd日" />
			</p>
		</div>
		<div layoutH="50">
			<table border="1" width="120%" class="list">
				<thead>
					<tr align="center">
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
						<th>设备编号</th>
						<th>备&nbsp;&nbsp; 注</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ma.infos}" var="de">
						<td>${de.single.wzname}</td>
						<td>${de.single.spectype.speName}</td>
						<td>${de.clfw}</td>
						<td>${de.single.factoryCode}</td>
						<td><fmt:formatDate value="${de.jdDate}" pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${de.yxDate}" pattern="yyyy-MM-dd" /></td>
						<td>${de.single.factory}</td>
						<td>${de.azLocation }</td>
						<td>${de.jmbCode }</td>
						<td>${de.zShuCode}</td>
						<td>${de.shbCode}</td>
						<td>${de.remark}</td>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>