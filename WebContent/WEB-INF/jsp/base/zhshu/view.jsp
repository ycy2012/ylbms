<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>展示页面</title>
</head>
<title>无标题文档</title>
<script language="javascript">
	function printWithAlert() {
		document.all.WebBrowser.ExecWB(6, 1);
	}
	function printWithoutAlert() {
		document.all.WebBrowser.ExecWB(6, 6);
	}
	function printSetup() {
		document.all.WebBrowser.ExecWB(8, 1);
	}
	function printPrieview() {
		document.all.WebBrowser.ExecWB(7, 1);
	}
	function printImmediately() {
		document.all.WebBrowser.ExecWB(6, 6);
		window.close();
	}
</script>

<OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2
	style="display: none"> </OBJECT>
<style media="print" type="text/css">
.Noprint {
	display: none;
}

.PageNext {
	page-break-after: always;
}
</style>

<style>
body {
	font-size: 17px;
	font-family: "宋体";
	text-align: center
}

.table1 {
	letter-spacing: 6px;
	padding-top: 20px;
	text-align: right;
}

.border {
	border-top: #000 solid 1px;
	border-bottom: #000 solid 1px;
	border-left: #000 solid 1px;
	border-collapse: collapse;
	font-size: 14px;
}
#tblGrid tr ,#tblGrid td{
	border-top: #000 solid 1px;
	border-bottom: #000 solid 1px;
	border-left: #000 solid 1px;

	border-left: #000 solid 1px;
	border-collapse: collapse;
	font-size: 14px;
	height: 25px;
	}

.div1 {
	border-bottom: #000000 solid 1px;
	text-align: center;
	width: 100%;
}

.div2 {
	float: left;
}

.model {
	padding-top: 5px;
	padding-left: 5px
}

.div3 {
	border-bottom: #000000 solid 1px;
	width: 100%
}

.div_buttom {
	border-top: #000000 solid 1px;
	width: 90%;
	font-size: 15px;
	font-family: "宋体";
	margin-top: 15px;
	letter-spacing: -1px;
	
	margin-tio:10px;
}

.font1 {
	font-size: 14px;
	padding-top: 6px;
	padding-left: 5px
}

.head {
	border-bottom: #000000 solid 2px;
	margin-bottom: 5px;
	width: 90%;
	font-size: 45px;
	letter-spacing: 5px;
	font-weight: bold;
	padding-top: 60px
}

.head1 {
	border-top: #000000 solid 2px;
	padding-top: 5px;
	width: 90%;
	text-align: center;
	font-size: 45px;
	font-weight: bold;
	letter-spacing: 15px;
	padding-bottom: 60px
}
</style>
</head>

<body style="text-align: center;" onload="add();">
<div align="center">
	<div class="PageNext">
		<div class="Noprint">
			<input type=button value=打印
				onclick=document.all.WebBrowser.ExecWB(6,1)> <input
				type=button value=直接打印 onclick=document.all.WebBrowser.ExecWB(6,6)>
			<input type=button value=页面设置
				onclick=document.all.WebBrowser.ExecWB(8,1)> <input
				type=button value=打印预览 onclick=document.all.WebBrowser.ExecWB(7,1)>
		</div>
		<div align="center">
			<c:set value="${master}" var="jdzs"></c:set>
			<div align="center" class=" head">工程技术部压力表检定室</div>
			<div class=" head1">检定证书</div>
		</div>
		<table width="80%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="22%" class="table1">证书编号:</td>
				<td width="78%" valign="bottom"><div class="div1">${jdzs.zCode
						}</div></td>
			</tr>
			<tr>
				<td class="table1">送检单位:</td>
				<td class="table2" valign="bottom"><div class="div1">${jdzs.sjUnit}</div></td>
			</tr>
			<tr>
				<td class="table1" style="letter-spacing: 2px">计量器名称:</td>
				<td class="table2" valign="bottom"><div class="div1">附后</div></td>
			</tr>
			<tr>
				<td class="table1">型号规格:</td>
				<td class="table2" valign="bottom"><div class="div1">附后</div></td>
			</tr>
			<tr>
				<td class="table1">出厂编号:</td>
				<td class="table2" valign="bottom"><div class="div1">附后</div></td>
			</tr>
			<tr>
				<td class="table1">制造单位:</td>
				<td class="table2" valign="bottom"><div class="div1">附后</div></td>
			</tr>
			<tr>
				<td class="table1">检定依据:</td>
				<td class="table2" valign="bottom"><div class="div1">${jdzs.basis
						}</div></td>
			</tr>
			<tr>
				<td class="table1">检定结论:</td>
				<td class="table2" valign="bottom"><div class="div1">${jdzs.result
						}</div></td>
			</tr>
		</table>

		<div
			style="padding-top: 75px; padding-bottom: 75px;">
			<table width="80%" border="0">
				<tr>
					<td width="52%" rowspan="3" style="font-size: 24px;" align="left">
						(检定单位盖章)</td>
					<td width="20%" height="40" valign="bottom"><div align="right">批准人：</div></td>
					<td width="28%" valign="bottom"><div class=" div3">${jdzs.pzren}</div></td>
				</tr>
				<tr>
					<td height="40" valign="bottom"><div align="right">核验员：</div></td>
					<td valign="bottom"><div class=" div3">${jdzs.veriRen }</div></td>
				</tr>
				<tr>
					<td height="40" valign="bottom"><div align="right">核定员：</div></td>
					<td valign="bottom"><div class=" div3">${jdzs.jdRen }</div></td>
				</tr>
			</table>
		</div>
		<div align="center" style="padding-bottom: 30px;">
			<table width="70%" border="0">
				<fmt:parseDate value="${jdzs.jdDate}" var="date" />
				<tr height="40px">
					<td>检定日期</td>
					<td><fmt:formatDate var="cTime_yy" value="${date}"
							pattern="yyyy" />${cTime_yy }年</td>
					<td><fmt:formatDate var="cTime_yy" value="${date}"
							pattern="MM" />${cTime_yy }月</td>
					<td><fmt:formatDate var="cTime_yy" value="${date}"
							pattern="dd" />${cTime_yy }日</td>
				</tr>
				<tr>
					<fmt:parseDate value="${jdzs.yxDate}" var="yxdata" />
					<td>有效期至</td>
					<td><fmt:formatDate var="cTime_yy" value="${yxdata}"
							pattern="yyyy" />${cTime_yy}年</td>
					<td><fmt:formatDate var="cTime_yy" value="${yxdata}"
							pattern="MM" />${cTime_yy }月</td>
					<td><fmt:formatDate var="cTime_yy" value="${yxdata}"
							pattern="dd" />${cTime_yy }日</td>
				</tr>
			</table>
			<div class=" div_buttom" align="left">
				<table width="100%" border="0">
					<tr>
						<td width="75%">计量检定机构授权证书号：油质安字[2010]38号</td>
						<td width="25%">电话：0996-2179227</td>
					</tr>
					<tr>
						<td>地址：新疆库尔勒市93号信箱工程技术部井控欠平衡中心</td>
						<td>邮编：841000</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div style="width: 90%; border-top: #000 solid 1px;margin-top:100px;text-align: center;" class="PageNext">
		<div
			style="border-left: #000 solid 1px; border-right: #000 solid 1px;padding-bottom: 10px;padding-top: 5px"
			class="font1" align="left">
			溯源性：本次检定使用的计量标准可溯源到中国国家计量基础<br />本次检定所使用的计量标准器：
		</div>
		<div>
			<table width="100%" border="1" align="center" cellpadding="1"
				cellspacing="0" class="border">
				<tr class="border" height="25px">
					<td class="border">名称</td>
					<td class="border">编号</td>
					<td class="border">测量范围</td>
					<td class="border">准确度等级</td>
					<td class="border">证书编号</td>
					<td class="border">有效日期至</td>
				</tr>
				<c:set value="${jdzs.jmbInfo}" var="bt"></c:set>
				<tr height="25px">
					<td class="border">${bt.jmbName}</td>
					<td class="border">${bt.factoryCode}</td>
					<td class="border">${bt.clfw}</td>
					<td class="border">${bt.grade}</td>
					<td class="border">${bt.zhShCode}</td>
					<td class="border"><fmt:formatDate value="${bt.yxDate}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</table>
		</div>
		<div style="border-left: #000 solid 1px; border-right: #000 solid 1px;padding-bottom:5px">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="font1">
				<tr align="left" class="font1">
					<td width="40%">检定地点、环境条件</td>
					<td width="60%"></td>
				</tr>
				<tr align="left" class="font1">
					<td align="left">地&nbsp;&nbsp;&nbsp;&nbsp;点：压力表检验室</td>
					<td align="left"> </td>
				</tr>
				<tr align="left" class="font1">
					<td>环境温度：25℃</td>
					<td>相对湿度：21%RH</td>
				</tr>
			</table>
		</div>
		<table width="100%" border="0"  cellspacing="0" cellpadding="0"
			 class="border" id="tblGrid">
			<tr height="25px">
				<td>序号</td>
				<td>名称</td>
				<td>型号规格</td>
				<td>制造厂</td>
				<td>测量范围</td>
				<td>出厂编号</td>
				<td>等级</td>
			</tr>
			<c:forEach items="${jdzs.infos}" var="bt" varStatus="stauts" > 
			<tr height="25px">
				<td>${stauts.count}</td>
				<td>${bt.clfw.wzname }</td>
				<td>${bt.clfw.spectype.speName}</td>
				<td>${bt.clfw.factory }</td>
				<td >${bt.clfw.clfw }</td>
				<td>${bt.clfw.factoryCode }</td>
				<td>${bt.clfw.grade }</td>
			</tr>
			</c:forEach>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="font1">
			<tr  align="left">
				<td width="10px">注</td>
				<td>1、检定结论仅对被检器具的本次检定有效，请妥善保管此证。</td>
			</tr>
			<tr  align="left">
				<td></td>
				<td>2、未经本室许可，不得复印或部分复印该证书</td>
			</tr>
			<tr align="left">
				<td></td>
				<td>3、本证书未加盖检定专用章无效</td>
			</tr>
		</table>
	</div>
</div>

</body>
</html>
<script type="text/javascript">
function add()  
{  
	 var table1=document.getElementById("tblGrid");
	 var rows=table1.rows.length;
 for (var i = 0;i < 24-rows;i++)
 {
	var newRow = document.all("tblGrid").insertRow();  
	//得到表的对象并插入一行，下面是插入了行以后，填充相应的列节点，如下面所示  
	var oCell = newRow.insertCell();//插入列的节点  
 	oCell.innerHTML = "&nbsp";//列里面填充的值，innerHtml值列内的所有元素      
	oCell = newRow.insertCell();  
	oCell.innerHTML = "&nbsp"; 
	oCell = newRow.insertCell();  
	oCell.innerHTML = "&nbsp"; 
	oCell = newRow.insertCell();  
	oCell.innerHTML = "&nbsp";//列里面填充的值，innerHtml值列内的所有元素      
	oCell = newRow.insertCell();  
	oCell.innerHTML = "&nbsp"; 
	oCell = newRow.insertCell();  
	oCell.innerHTML = "&nbsp"; 
	oCell = newRow.insertCell();  
	oCell.innerHTML = "&nbsp"; 
	oCell = newRow.insertCell();  
	
 } 
}  

</script>