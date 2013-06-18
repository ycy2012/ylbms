<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入库管理</title>
<script type="text/javascript">
<!--
function addTr(value){
	alert(value);
	var otable = document.getElementById("mxTable");
	for(var i=0,len=value.length;i<len;i++){
		var temp = value[i].split("#");
		var myTR = otable.insertRow(otable.rows.length);
		var myTD1 = myTR.insertCell(0);
		myTD1.innerHTML = "<input type='text' name='ids' value="+temp[0]+">";
		var myTD2 = myTR.insertCell(1);
		myTD2.innerHTML = "<input type='text' name='ids' value="+temp[1]+">";
		var myTD3 = myTR.insertCell(2);
		myTD3.innerHTML = "<input type='text' name='ids' value="+temp[2]+">";
		var myTD4 = myTR.insertCell(3);
		myTD4.innerHTML = "<input type='text' name='ids' value="+temp[3]+">";
		var myTD5 = myTR.insertCell(4);
		myTD5.innerHTML = "<input type='text' name='ids' value="+temp[4]+">";
		var myTD5 = myTR.insertCell(5);
		myTD5.innerHTML ="<input type='text' name='ids' value="+temp[5]+">";
		var myTD6=myTR.insertCell(6);
		myTD6.innerHTML="<a  title='删除' name='delete' href='javascript:;' onclick='_deleteRow(this)' class='btnDel'>删除</a>";
	}
}
function _deleteRow(rowID){
	var otable=document.getElementById("addTable");
    var i=rowID.parentNode.parentNode.rowIndex;
	otable.deleteRow(parseInt(i));
}
//-->
</script>
</head>
<body class="page">
	<form action="">
		<div class="pageHeader">
			<div
				style="width: 90%; text-align: center; font-size: 1.2em; font-weight: bold;">新产品入库单</div>
			<div class="divider"></div>
			<table class="searchContent" width="100%">
				<tr>
					<td>供货方：</td>
					<td><input type="text" name="sendLocation" /></td>
					<td>接收方：</td>
					<td><input type="text" name="acceptLocation" /></td>
				</tr>
				<tr>
					<td>备     注：</td>
					<td><input type="text" name="remark" /></td>
				</tr>
			</table>
		</div>
		<div class="pageContent">
			<div class="panelBar">
				<ul class="toolBar">
					<li><a class="add" href="${ctx}/new/addMx" target="dialog"
						mask="true" title="添加单件信息"><span>添加明细</span></a></li>
					<li class="line">line</li>
					<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="djIds"
						postType="string" href="${ctx}/bill/delBydjIds/{sdjId_bill}"
						class="delete"><span>批量删除</span></a></li>
					<li class="line">line</li>
				</ul>
			</div>
			<table class="list" width="100%" id="mxTable">
				<thead>
					<tr>
						<th>单件虚拟编号</th>
						<th>物资名称</th>
						<th>规格型号</th>
						<th>当前位置</th>
						<th>当前状态</th>
						<th>备注信息</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>