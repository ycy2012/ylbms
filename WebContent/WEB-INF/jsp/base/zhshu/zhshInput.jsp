<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<link href="${ctx}/styles/css/bill_title.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--
	function addTr(value) {
		var otable = document.getElementById("mxTable1");
		for ( var i = 0, len = value.length; i < len; i++) {
			var temp = value[i].split("#");
			var myTR = otable.insertRow(otable.rows.length);
			var myTD1 = myTR.insertCell(0);
			myTD1.innerHTML = "<input type='text' style='width:100px' name='detail["+i+"].single.mid'  readonly='readonly'  value="+temp[0]+">";
			var myTD2 = myTR.insertCell(1);
			myTD2.innerHTML = "<input type='text' style='width:100px'  readonly='readonly'  value="+temp[1]+">";
			var myTD3 = myTR.insertCell(2);
			myTD3.innerHTML = "<input  type='text' style='width:100px;' readonly='true' value="+temp[2]+"> ";
			var myTD4 = myTR.insertCell(3);
			myTD4.innerHTML = "<input  type='text' name='detail["+i+"].azLocation'>";
			var myTD5 = myTR.insertCell(4);
			myTD5.innerHTML = "<input  type='text' name='detail["+i+"].shbCode'>";
			var myTD7= myTR.insertCell(5);
			myTD7.innerHTML = "<input   type='text' style='width:80px;' name='detail["+i+"].grade' >";
			var myTD8 = myTR.insertCell(6);
			myTD8.innerHTML = "<input type='text' style='width:80px;'  name='detail[" + i+ "].order' value=" + (i + 1) + ">";
			var myTD9 = myTR.insertCell(7);
			myTD9.innerHTML = "<input  type='text' name='detail["+i+"].remark'> ";
			var myTD10 = myTR.insertCell(8);
			myTD10.innerHTML = "<a  title='删除'  href='javascript:;' onclick='_deleteRow(this)' class='btnDel'>删除</a>";
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
				title = date.getFullYear() + "/" + date.getMonth() + "/"+ date.getDate() + "检定证书";
				$("#title").val(title);
			});
//-->
</script>
<form action="${ctx}/jdzhs/save" method="post"
	class="pageForm required-validate"
	onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageHeader">
		<div>
			<h1 style="text-align: center;">工程技术部压力表检定证书</h1>
			<input type="hidden" name="zTitle" id="title">
		</div>
		<div class="divider"></div>
		<table id="b_title"  width="100%">
			<thead>
				<tr>
					<td>证书编号</td>
					<td><input type="text" name="zCode" class="required"></td>
					<td>送检单位</td>
					<td><input type="text" name="sjUnit" value="工程技术部井控欠平衡中心"></td>
					<td>检定依据</td>
					<td><select name="basis">
							<option value="1">选择精密表信息</option>
					</select></td>
				</tr>
				<tr>
					<td>检定结论</td>
					<td><input type="text" name="result" class="required"></td>
					<td><label>检定日期</label></td>
					<td><input type="text" class="date required" name="jdDate"
						readonly="true"><a class="inputDateButton"
						href="javascript:;">选择</a></td>
					<td>有效日期</td>
					<td><input type="text" class="date required" name="yxDate"
						readonly="true"><a class="inputDateButton"
						href="javascript:;">选择</a></td>

				</tr>
				<tr>
					<td>检定员</td>
					<td><input type="text" name="jdRen" class="required"></td>
					<td>批准人</td>
					<td><input type="text" name="pzren" class="required"></td>
					<td>核验员</td>
					<td><input type="text" name="veriRen" class="required"></td>
				</tr>
				<tr>
					<td>精密表编码</td>
					<td>
					<input name="jmbInfo.jmbID" value="" type="hidden">
				    <input name="jmbInfo.jmbCode" type="text"/>
				    <a class="btnLook" href="${ctx}/jmbinfo/lookUp" lookupGroup="jmbInfo" width="850">查找带回</a>
				    </td>
					<td>备注信息</td>
					<td colspan="3"><input type="text" name="remark"></td>
				</tr>
			</thead>
		</table>
	</div>
	<div class="pageContent" layoutH="170">
		<div class="panelBar">
			<ul class="toolBar">
				<li class="line">line</li>
				<li><a class="add" href="${ctx}/jd/addMx"
					target="addMxAjaxTodo" title="添加单件信息" warn="有红色星号标识的选项必填！"><span>添加明细</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="list" width="100%" id="mxTable1">
			<thead>
				<tr>
				    <th>单件虚拟编号</th>
					<th>物资名称</th>
					<th>规格型号</th>
					<th>安装位置</th>
					<th>设备编号</th>
					<th>等 级</th>
					<th>排 序</th>
					<th>备 注</th>
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
