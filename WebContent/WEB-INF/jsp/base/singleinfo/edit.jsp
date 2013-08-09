<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@include file="../../inc/taglib.jsp"%>
<!DOCTYPE html PU/BLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="pageContent">
	<form method="post" action="${ctx}/single/add"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" value="${obj.mid}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>物资编码：</label> <input name="owercode" type="text" size="30"
					class="required" value="${obj.owercode}" />
			</p>
			<p>
				<label>物资名称：</label> <input name="wzname" class="required"
					type="text" size="30" value="${obj.wzname}" />
			</p>
			<p>
				<label>规格型号：</label> <select name="speId" class="required">
					<option value="">请选择规格型号</option>
					<c:forEach items="${spectypes}" var="sp">
						<option value="${sp.value}">${sp.text}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>当前位置：</label> <select name="id" class="required">
					<option value="">请选择当前位置</option>
					<c:forEach items="${mapLocation}" var="ml">
						<option value="${ml.id}">${ml.locationName}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>当前状态：</label> <select name="id" class="required combox"
					value="${obj.state}">
					<option value="" selected>请选择</option>
					<option value="1">已租</option>
					<option value="0">待租</option>
				</select>
			</p>
			<p>
				<label>资产种类：</label> <select name="classId" class="required combox"
					value="obj.classId">
					<option value="" selected>请选择</option>
					<option value="0">个人</option>
					<option value="1">公司</option>
				</select>
			</p>
			<p>
				<label>生产厂家：</label> <input type="text" size="30" name="factory" />
			</p>
			<p>
				<label>出厂编号：</label> <input type="text" name="factoryCode"
					class="textInput" size="30" />
			</p>
			<p>
				<label>检测日期：</label> <input type="text" name="jd_time" class="date"
					size="30" /><a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
			<p>
				<label>所属单位：</label> <input type="text" name="sc_unit"
					class="textInput" size="30" value="${obg.sc_unit}" />
			</p>
			<%-- <label>所属单位：</label>
				<select name="sc_unit" class="required combox">
					<option value="">请选择</option>
					<option value="10">10</option>
					<option value="50" selected>50</option>
					<option value="100">100</option>
				</select>
				<span class="unit">万元</span>
				 
			</p>--%>
			<p>
				<label>有效日期：</label> <input type="text" name="yx_Time" class="date"
					size="30" value="${obg.yx_Time}" /><a class="inputDateButton"
					href="javascript:;">选择</a>
			</p>
			<p>
				<label>报废日期：</label> <input type="text" name="bf_Time" class="date"
					readonly="readonly" size="30" value="${obg.bf_Time}" /><a
					class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>启用日期：</label> <input type="text" name="qy_Time" class="date"
					size="30" value="${obg.qy_Time}" /><a class="inputDateButton"
					href="javascript:;">选择</a>
			</p>
			<p>
				<label>计量类别：</label> <input type="text" size="30" name="type_Id"
					value="${obg.type_Id}" />
			</p>
			<p>
				<label>精确度：</label> <input type="text" size="30" name="zqd"
					value="${obg.zqd}" />
			</p>
			<p>
				<label>使用次数：</label> <input type="text" size="30" name="userTimes"
					value="${obg.userTimes}" />
			</p>
			<p>
				<label>价格：</label> <input type="text" size="30" name="price"
					value="${obg.price}" />
			</p>
			<p>
				<label>是否能源器具：</label>
				<!-- <input type="text" size="30" name="isnyqj"  value="${obg.isnyqj}"/> -->
				<select name="isnyqj" class="required combox" value="obj.isnyqj">
					<option value="" selected>请选择</option>
					<option value="是">是</option>
					<option value="否">否</option>
				</select>
			</p>
			<p>
				<label>测量范围：</label> <input type="text" size="30" name="clfw"
					value="${obg.clfw}" />
			</p>
			<p>
				<label>是否强检：</label> <input type="text" size="30" name="isqj"
					value="${obg.isqj}" />
			</p>
			<p>
				<label>出厂日期：</label> <input type="text" name="chc_Date" class="date"
					size="30" value="${obg.chc_Date}" /><a class="inputDateButton"
					href="javascript:;">选择</a>
			</p>
			<p>
				<label>ABC形式：</label> <select name="form" class="combox">
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
				</select>
			</p>
			<p>
				<label>固定资产编码：</label> <input type="text" size="30" name="gdzc_Code"
					value="${obg.gdzc_Code}" />
			</p>
			<!-- <div class="divider"></div> -->
			<p>
				<label>条码号：</label> <input type="text" size="30" name="tx_Code"
					value="${obg.tx_Code}" />
			</p>
			<p>
				<label>安装位置：</label> <input type="text" size="30" name="az_Location"
					value="${obg.az_Location}" />
			</p>
			<p>
				<label>购入日期：</label> <input type="text" name="gr_Date" class="date"
					size="30" value="${obg.gr_Date}" /><a class="inputDateButton"
					href="javascript:;">选择</a>
			</p>
			<p>
				<label>四号定位：</label> <input type="text" size="30" name="shdw"
					value="${obg.shdw}" />
			</p>
			<p>
				<label>其他指标：</label> <input type="text" size="30" name="other"
					value="${obg.other}" />
			</p>
			<p>
				<label>录入人员：</label> <input type="text" size="30" name="creater"
					value="${obg.creater}" />
			</p>
			<p>
				<label>录入时间：</label> <input type="text" name="createDate"
					class="date" size="30" value="${obg.createDate}" /><a
					class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>状态信息：</label> <select name="status" class="required combox">
					<option value="" selected>请选择</option>
					<option value="1">无效</option>
					<option value="0">有效</option>
				</select>
			</p>
			<p>
				<label>备注信息：</label>
				<textarea name="remark" value="${obg.remark}"></textarea>
				<!-- <input type="text" size="30" name="remark" value="${obg.remark}"/> -->
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
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
</div>
