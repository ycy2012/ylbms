<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
	<div align="center"
		style="font-size: 17px; font-weight: bold; margin-top: 20px; margin-bottom: 30px">单据详细信息</div>
	<div align="center">
		<table width="85%" height="40"
			style="font-size: 12px; margin-bottom: 15px;" BORDER="0"
			CELLSPACING="5" CELLPADDING="0">
			<c:set value="${billH}" var="bh"/>
			<tr valign="bottom">
				<td width="7%" height="10%">单据编号:</td>
				<td width="20%" style="border-bottom: #000 solid 1px">${bh.djId}</td>
				<td width="5%"></td>
				<td width="10%">&nbsp;</td>
				<td width="20%">&nbsp;</td>
				<td width="8%">&nbsp;</td>
				<td width="7%">制单时间:</td>
				<td width="20%" style="border-bottom: #000 solid 1px">${bh.createDate}</td>
			</tr>
			<tr valign="bottom">
				<td width="7%">发出地点:</td>
				<td width="20%" style="border-bottom: #000 solid 1px">${bh.sendLocation.locationName}</td>
				<td width="8%">&nbsp;</td>
				<td width="7%">接收地点:</td>
				<td width="20%" style="border-bottom: #000 solid 1px">${bh.acceptLocation.locationName}</td>
				<td width="8%">&nbsp;</td>
				<td width="7%">生效时间:</td>
				<td width="20%" style="border-bottom: #000 solid 1px">${bh.sxDate}</td>
			</tr>
		</table>
		<table width="90%" align="center" BORDER="1"
			BorderColor="#000" CELLSPACING="0" CELLPADDING="3"
			style="border-collapse: collapse">
			<tr align="center" height="30">
				<td height="20%">序号</td>
				<td>单件虚拟编号</td>
				<td>物资名称</td>
				<td>规格型号</td>
				<td>出厂编码</td>
				<td>备注</td>
			</tr>
			<c:forEach items="${bh.billTbody}" var="bt" varStatus="stauts">
			<tr height="30" align="center">
				<td height="20%" align="center"  width="5%">${stauts.count}</td>
				<td width="20%">${bt.mid.mid}</td>
				<td width="20%">${bt.mid.wzname}</td>
				<td width="10%">${bt.mid.spectype.speId}</td>
				<td width="20%">${tb.mid.factoryCode}</td>
				<td width="25%">${bt.mid.remark }</td>
			</tr>
					</c:forEach>
		</table>

		<table width="90%" height="35"
			style="font-size: 12px; margin-bottom: 15px;" BORDER="0"
			CELLSPACING="0" CELLPADDING="0">
			<tr valign="bottom">
				<td width="40%" height="10%"></td>
				<td width="18%">&nbsp;</td>
				<td width="1%"></td>
				<td width="6%"></td>
				<td width="18%">&nbsp;</td>
				<td width="1%"></td>
				<td width="5%">制作人:</td>
				<td width="18%" style="border-bottom: #000 solid 1px">张三</td>
				<td width="1%"></td>
				<td width="6%"></td>
				<td width="18%">&nbsp;</td>
			</tr>
			</div>

			<%--
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/single/list"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>物资编码：<input type="text" name="owercode"  /></td>
					<td>物资名称：<input type="text" name="wzname"/></td>
					<td>当前位置：<input type="text" name="location.id" /></td>
					<td>当前状态： <select name="status">
							<option value="1">无效</option>
							<option value="0">有效</option>
							<option value="" selected>请选择</option>
					</select>
				</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="${ctx}/single/advanced" target="dialog"
						mask="true" title="查询框"><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div> 

<c:set value="${billH}" var="bh"/>
${bh.djId } 
<c:forEach items="${bh.billTbody}" var="bt">
${bt.mid}
</c:forEach>
--%>

