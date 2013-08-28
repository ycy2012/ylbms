<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@include file="../../inc/taglib.jsp"%>
<script type="text/javascript">
<!--
	function setWzInfo(id, name) {
		$("#locationId").val(id);
		$("#locationName").val(name);
	}
//-->
</script>
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
				<label>规格型号：</label> <select name="spectype.speId"
					class="combox required">
					<option value="">请选择规格型号</option>
					<c:forEach items="${spectypes}" var="sp">
						<c:if test="${obj.spectype.speId eq ${sp.value} }">
							<option value="${sp.value}" selected="selected">${sp.text}</option>
						</c:if>
						<option value="${sp.value}">${sp.text}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>当前位置：</label> <input name="location.wzId" class="required"
					value="${obj.location.wzId==null?"
					00000000000955":obj.loction.wzId}" type="hidden" size="30"
					id="locationId" /> <input type="text" size="30"
					value="${obj.location.fullName}" readonly="readonly"
					id="locationName" class="required"> <a class="btnLook"
					href="${ctx}/tdmis/commUi" width="300" height="400" mask="true"
					target="dialog">选择位置信息</a>
			</p>
			<p>
				<label>当前状态：</label> <select name="state.id" class="required combox">
					<option value="">请选择</option>
					<c:forEach items="${state}" var="s">
						<option value="${s.value }">${s.text}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>资产种类：</label> <select name="classId.id"
					class="required combox">
					<option value="">请选择</option>
					<c:forEach items="${fns:getDictList('class_type')}" var="z">
						<option value="${z.id}">${z.value}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>生产厂家：</label> <select name="factory.id" class="combox">
					<option value="">请选择厂家信息</option>
					<c:forEach items="${fns:getDictList('factory_type')}" var="f">
						<c:if test="${obj.factory.id eq f.id }">
							<option value="${f.id}" selected="selected">${f.value}</option>
						</c:if>
						<option value="${f.id}">${f.value}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>出厂编号：</label> <input type="text" name="factoryCode"
					class="required" size="30" />
			</p>
			<p>
				<label>检测日期：</label> <input type="text" name="jdtime" class="date"
					size="30" /><a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
			<p>
				<label>所属单位：</label> <input type="text" name="scunit"
					class="textInput" size="30" value="工程技术部井控中心" />
			</p>
			<p>
				<label>有效日期：</label> <input type="text" name="yxTime" class="date"
					size="30" value="${obg.yx_Time}" /><a class="inputDateButton"
					href="javascript:;">选择</a>
			</p>
			<p>
				<label>报废日期：</label> <input type="text" name="bfTime" class="date"
					readonly="readonly" size="30" value="${obg.bfTime}" /><a
					class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>启用日期：</label> <input type="text" name="qyTime" class="date"
					size="30" value="${obg.qyTime}" /><a class="inputDateButton"
					href="javascript:;">选择</a>
			</p>
			<p>
				<label>计量类别：</label> <select name="typeId.id" class="combox">
					<c:forEach items="${fns:getDictList('jlnb_type')}" var="v">
						<c:if test="${obj.typeId.id eq v.id }">
							<option value="${v.id}" selected="selected">${v.value}</option>
						</c:if>
						<option value="${v.id}">${v.value}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>精确度：</label> <input type="text" size="30" name="zqd"
					value="${obg.zqd}" />
			</p>
			<p>
				<label>使用次数：</label> <input type="text" size="30" name="userTimes"
					readonly="readonly" value="${obg.userTimes}" />
			</p>
			<p>
				<label>价格：</label> <input type="text" size="30" name="price"
					value="${obg.price}" />
			</p>
			<p>
				<label>是否能源器具：</label> <select name="isnyqj" class="combox">
					<option value="否">否</option>
					<option value="是">是</option>
				</select>
			</p>
			<p>
				<label>测量范围：</label> <select name="clfw.id" class="combox">
					<c:forEach items="${fns:getDictList('clfw_type')}" var="v">
						<c:if test="${obj.clfw eq v.value}">
							<option value="${v.value}" selected="selected">${v.value}</option>
						</c:if>
						<option value="${v.id}">${v.value}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>是否强检：</label> <select name="isqj" class="combox">
					<option value="否">否</option>
					<option value="是">是</option>
				</select>
			</p>
			<p>
				<label>出厂日期：</label> <input type="text" name="chcDate" class="date"
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
				<label>固定资产编码：</label> <input type="text" size="30" name="gdzcCode"
					value="${obj.gdzcCode}" />
			</p>
			<p>
				<label>条码号：</label> <input type="text" size="30" name="txCode"
					value="${obj.txCode}" />
			</p>
			<p>
				<label>安装位置：</label> <input type="text" size="30" name="az_Location"
					readonly="readonly" value="${obg.az_Location}" />
			</p>
			<p>
				<label>购入日期：</label> <input type="text" name="grDate" class="date"
					size="30" value="${obg.gr_Date}" /><a class="inputDateButton"
					href="javascript:;">选择</a>
			</p>
			<p>
				<label>四号定位：</label> <input type="text" size="30" name="shdw"
					value="${obg.shdw}" />
			</p>
			<p>
				<label>状态信息：</label> <select name="status" class="combox">
					<c:if test="${obj.status eq '0'}">
						<option value="0">有效</option>
					</c:if>
					<option value="1">无效</option>
				</select>
			</p>
			<p>
				<label>备注信息：</label>
				<textarea name="remark">${obg.remark}</textarea>
			</p>
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
</div>
