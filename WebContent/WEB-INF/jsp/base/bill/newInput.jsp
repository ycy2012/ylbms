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
	function addTr(value) {
		var otable = document.getElementById("mxTable");
		for ( var i = 0, len = value.length; i < len; i++) {
			var temp = value[i].split("#");
			var myTR = otable.insertRow(otable.rows.length);
			var myTD1 = myTR.insertCell(0);
			myTD1.innerHTML = "<input type='text' id='mid'  readonly='readonly'  name='singles["+i+"].mid' value="+temp[0]+">";
			var myTD2 = myTR.insertCell(1);
			myTD2.innerHTML = "<input type='text'  readonly='readonly'  value="+temp[2]+">";
			var myTD3 = myTR.insertCell(2);
			myTD3.innerHTML = "<input type='text'  readonly='readonly'  value="+temp[3]+">";
			var myTD4 = myTR.insertCell(3);
			myTD4.innerHTML = "<input type='text'  readonly='readonly'  name='singles["+i+"].location' value="+temp[4]+">";
			var myTD5 = myTR.insertCell(4);
			myTD5.innerHTML = "<input type='text'  readonly='readonly'  name='singles["+i+"].state' value="+temp[5]+">";
			var myTD5 = myTR.insertCell(5);
			myTD5.innerHTML = "<input type='text' name='singles["+i+"].remark' >";
			var myTD6 = myTR.insertCell(6);
			myTD6.innerHTML = "<a  title='删除'  href='javascript:;' onclick='_deleteRow(this)' class='btnDel'>删除</a>";
		}
	}
	function _deleteRow(rowID) {
		var otable = document.getElementById("mxTable");
		var i = rowID.parentNode.parentNode.rowIndex;
		otable.deleteRow(parseInt(i));
	}
	function getMids(){
		var mids="";
		$("#mxTable").find("input[id=mid]").each(function(){
			mids+=$(this).val()+",";
		});
		return mids;
	}
	function getURL(){
		var url=$("a[class='add']").attr("href");
		return url;
	}
//-->
</script>
</head>
<body class="page">
	<form action="${ctx}/new/addBill" method="post"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<div
				style="width: 90%; text-align: center; font-size: 1.2em; font-weight: bold;">新产品入库单</div>
				<input type="hidden" name="djTitle" value="新产品入库单">
			<div class="divider"></div>
			<dl>
				<dt>供货方：</dt>
				<dd>
					<input name="sendLocation" type="text" alt="供货方信息" class="required" />
				</dd>
			</dl>
			<dl>
				<dt>接收方：</dt>
				<dd>
					<input name="acceptLocation" type="text" alt="接收方信息" value="基地"
						class="required" />
				</dd>
			</dl>
			<dl>
				<dt>领料人：</dt>
				<dd>
					<input name="llren" type="text" alt="领料人信息" class="required" />
				</dd>
			</dl>
			<dl>
				<dt>领料单位：</dt>
				<dd>
					<select name="lluint" class="combox" class="required">
					    <option value="">请选择信息</option>
						<option value="轮南项目部">轮南项目部</option>
						<option value="库车项目部">库车项目部</option>
						<option value="塔中项目部">塔中项目部</option>
					</select>
				</dd>
			</dl>
			<dl class="nowrap">
				<dt>备注信息：</dt>
				<dd>
					<textarea name="remark" cols="80" rows="1"></textarea>
				</dd>
			</dl>
		</div>
		<div class="pageContent" layoutH="190">
			<div class="panelBar">
				<ul class="toolBar">
					<li class="line">line</li>
					<li><a class="add" href="${ctx}/new/addMx"  target="addMxAjaxTodo" title="添加单件信息" warn="有红色星号标识的选项必填！"><span>添加明细</span></a></li>
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
</body>
</html>