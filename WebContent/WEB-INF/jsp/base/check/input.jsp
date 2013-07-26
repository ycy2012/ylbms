<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<script type="text/javascript">
<!--
	function addTr(value) {
		var otable = document.getElementById("mxTable1");
		for ( var i = 0, len = value.length; i < len; i++) {
			var temp = value[i].split("#");
			var myTR = otable.insertRow(otable.rows.length);
			var myTD1 = myTR.insertCell(0);
			myTD1.innerHTML = "<input type='text' style='width:100px' readonly='readonly'  value="+temp[1]+"><input type='hidden' id='mid' name='notes["+i+"].single.mid' value="+temp[0]+">";
			var myTD2 = myTR.insertCell(1);
			myTD2.innerHTML = "<input type='text' style='width:100px'  readonly='readonly'  value="+temp[2]+">";
			var myTD3 = myTR.insertCell(2);
			myTD3.innerHTML = "<input id=\"d4312\" type='text' class='Wdate' name='notes["+i+"].jdDate' onfocus=\"WdatePicker()\" readonly='true'>";
			var myTD4 = myTR.insertCell(3);
			myTD4.innerHTML = "<input  id='d' type='text' class='Wdate' name='notes["+i+"].yxDate' onfocus='WdatePicker()' readonly='true'>";
			var myTD5 = myTR.insertCell(4);
			myTD5.innerHTML = "<input type='text'  name='notes["+i+"].azLocation' value='"+temp[3]+"'>";
			var myTD6 = myTR.insertCell(5);
			myTD6.innerHTML = "<input  type='text' name='notes["+i+"].jmbCode'> ";
			var myTD7 = myTR.insertCell(6);
			myTD7.innerHTML = "<input  type='text' name='notes["+i+"].shbCode'> ";
			var myTD8 = myTR.insertCell(7);
			myTD8.innerHTML = "<input  type='text' name='notes["+i+"].remark'> ";
			var myTD9 = myTR.insertCell(8);
			myTD9.innerHTML = "<a  title='删除'  href='javascript:;' onclick='_deleteRow(this)' class='btnDel'>删除</a>";
		}
	}
	function _deleteRow(rowID) {
		var otable = document.getElementById("mxTable1");
		var i = rowID.parentNode.parentNode.rowIndex;
		otable.deleteRow(parseInt(i));
	}
	function getMids() {
		var mids = "";
		$("#mxTable1").find("input[id=mid]").each(function() {
			mids += $(this).val() + ",";
		});
		return mids;
	}
	function getURL() {
		var url = $("a[class='add']").attr("href");
		return url;
	}
	function setWzInfo(id, name) {
		$("#wzInfo").val(id);
		$("#wzName").val(name);
	}
	$(document).ready(
			function() {
				var date = new Date(), title, url;
				title = date.getFullYear() + "/" + date.getMonth() + "/"+ date.getDate() + "检定记录信息卡";
				$("#title").val(title);
			});
//-->
</script>
<form action="${ctx}/jd/save" method="post"
	class="pageForm required-validate"
	onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageHeader">
		<div
			style="width: 90%; text-align: center; font-size: 1.2em; font-weight: bold;">压力表具检定记录信息卡</div>
		<div class="divider"></div>
		<table class="searchContent">
			<tr>
				<td><label>送检单位：</label></td>
				<td><input type="hidden" name="title" id="title"><input
					type="text" name="sjLocation" readonly="readonly"
					style="width: 150px;" value="工程技术部井控欠平衡中心" /></td>
				<td>&nbsp;</td>
				<td><label>作业方：</label></td>
				<td><input type="text" name="jdLocation" readonly="readonly"
					style="width: 150px;" value="工程技术部压力表检定室" /></td>
				<td><label>制单日期:</label></td>
				<td><input type="text" name="createDate" class="date required"
					readonly="true" /></td>
			</tr>
			<tr>
				<td><br></td>
			</tr>
			<tr>
				<td>备注信息:</td>
				<td colspan="5"><textarea name="remark" cols="80" rows="2"></textarea></td>
			</tr>
		</table>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li class="line">line</li>
				<li><a class="add" href="${ctx}/jd/addMx"
					target="addMxAjaxTodo" rel="addJd" title="添加单件信息"><span>添加明细</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="list" width="130%" id="mxTable1" layoutH="180">
			<thead>
				<tr>
					<th>物资名称</th>
					<th>规格型号</th>
					<th>检定日期</th>
					<th>有效日期</th>
					<th>安装位置</th>
					<th>精密表编码</th>
					<th>设备编号</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive">
					<div class="buttonContent">
						<button type="submit">保存</button>
					</div>
				</div></li>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">取消</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</form>
