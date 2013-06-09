<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<script type="text/javascript">
<!--
$(document).ready(function(){
	var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
			data:{simpleData:{enable:true}},callback:{beforeClick:function(id, node){
				tree.checkNode(node, !node.checked, true, true);
				return false;
			}}};
	
	// 用户-菜单
	var zNodes=[
			<c:forEach items="${menuList}" var="menu">{id:${menu.id}, pId:${not empty menu.parent.id?menu.parent.id:0}, name:"${not empty menu.parent.id?menu.name:'权限列表'}"},
            </c:forEach>];
	// 初始化树结构
	var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
	// 默认选择节点
	var ids = "${role.menuIds}".split(",");
	for(var i=0; i<ids.length; i++) {
		var node = tree.getNodeByParam("id", ids[i]);
		try{tree.checkNode(node, true, false);}catch(e){}
	}
	// 默认展开全部节点
	tree.expandAll(true);
});
//-->
</script>
<div class="pageContent">
	<form method="post" action="${ctx}/role/add"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" value="${obj.id}">
			<p>
				<label>角色名称：</label> <input name="name" type="text" size="30"
					value="${role.name}" class="required" maxlength="150" />
			</p>
			<p>
			  <label>添加权限</label>
			  <div id="menuTree" class="ztree" style="margin-top:3px;float:left;"></div>
			  <input type="hidden" name="menuIds">
			</p>
			<p>
				<label>用户状态：</label> <select name="delFlag" class="combox">
					<option value="0">有效</option>
					<option value="1">无效</option>
				</select>
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
