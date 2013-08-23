<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<div class="pageHeader">
	<font color="red">先下载模板！按照模板格式导入</font>
</div>
<div class="pageContent">
<div class="panelBar">
	<ul class="toolBar">
		<li class="line">line</li>
		<li><a class="icon" href="${ctx}/spec/import/template"
			target="dwzExport" targetType="navTab" title="确定要下载模板吗?"><span>下载模板</span></a></li>
		<li class="line">line</li>
	</ul>
</div>

	<form action="${ctx}/spec/import" enctype="multipart/form-data"
		method="post" class="pageForm required-validate"
		onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="110">
			<p>
				<label>目标文件:</label><input type="file" name="fileUpload"
					class="required">
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">excel导入</button>
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