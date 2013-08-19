<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp"%>
<link href="${ctx}/oper/om-default.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/oper/js/operamasks-ui.min.js" type="text/javascript"></script>
<div class="pageContent">
	<form method="post" action="${ctx}/user/addRole"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="roleIds" id="roleIds" value="${roleIds}">
			<input type="hidden" name="id" value="${userId}">
			<!-- 华丽分界线-->
				<div id="itemselector" class="om-itemselector om-widget"></div>
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
<script type="text/javascript">
<!--
 iterselector =function(){
	var roleIds = $("#roleIds").val().split(',');
	return{
		init:function(){
			$('#itemselector').omItemSelector({
				 availableTitle : '准备选择权限',
		         selectedTitle : '已选择权限',
		         dataSource:'role/roleData?_='+new Date(),
		         width:'150px',
		         height:'200px',
		         onSuccess:function(data, textStatus, event){
		           if(data.length==0){
		        	  alertMsg.error("没有数据！");
		           } 
		           if(roleIds!="")
		               $("#itemselector").omItemSelector({value:roleIds});
		         },
		         onItemSelect:function(itemDatas,event){
		        	var values=$("#itemselector").omItemSelector('value').join(",");
		        	$("#roleIds").val(values);
		         }
		    });
		}
	};
}();
$(document).ready(function(){
	iterselector.init();
});
//-->
</script>