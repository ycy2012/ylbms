<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>安装记录添加</title>
<link href="${ctx}/styles/css/bill_title.css" rel="stylesheet" type="text/css" />
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
			myTD4.innerHTML = "<input type='text'  value="+temp[4]+"> <input type='hidden'  name='singles["+i+"].state' value="+temp[6]+">";
			var myTD5 = myTR.insertCell(4);
			myTD5.innerHTML = "<input type='text' name='singles["+i+"].remark' > <input type='hidden' name='singles["+i+"].location.wzId' value="+temp[5]+">";
			var myTD6 = myTR.insertCell(5);
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
	function setWzInfo(id,name){
		$("#wzInfo").val(id);
		$("#wzName").val(name);
		$("#ss").val(name);
	}
//-->
</script>
</head>
<body class="page">
	<form action="${ctx}/check/addBill" method="post"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" >
			<div
				style="width: 90%; text-align: center; font-size: 1.2em; font-weight: bold;">回收送检记录表</div>
				<input type="hidden" name="djTitle" value="回收送检记录表">
			<div class="divider"></div>
				<table id="b_title"  width="100%">
				<tr>
					<td>发料地点:</td>
					<td>
						<input name="sendLocation.wzId" type="hidden" id="wzInfo" />
				        <input type="text"  readonly="readonly" id="wzName" class="required" size="30">
						<a class="btnLook" href="${ctx}/tdmis/commUi" width="300" height="400" mask="true"  target="dialog" >选择位置信息</a>	</td>
					<td>接受地点:</td>
					<td> 
						<input name="acceptLocation.wzId" type="hidden"  value="00000000000955" />
						<input  type="text" readonly="readonly" value="基地料场"  size="30"/>
					</td>
				</tr>
				<tr>
					<td>送料人员：</td>
					<td>
						<input name="llren" type="text" alt="送料人员信息" class="required" size="30" />
					</td>
					<td>送料单位：</td>
					<td>
						<input name="llUnit" readonly="readonly" type="text" id="ss" size="30">
					</td>
				</tr>
				<tr>
					<td>备注信息：</td>
					<td colspan="3">
						<textarea name="remark" cols="80" rows="2"></textarea>
					</td>
				</tr>
			</table>
		</div>
		<div class="pageContent" layoutH="188">
			<div class="panelBar">
				<ul class="toolBar">
					<li class="line">line</li>
					<li><a class="add" href="${ctx}/check/addMx"  target="addMxAjaxTodo" title="添加单件信息" warn="有红色星号标识的选项必填！"><span>添加明细</span></a></li>
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