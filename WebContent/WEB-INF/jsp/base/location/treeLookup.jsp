<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../inc/taglib.jsp"%>
<div class="pageContent">
	<div class="pageFormContent" layoutH="58">
		<ul class="tree expand">
			<c:forEach items="${trees}" var="item">
				<li><a href="javascript:">${item.locationName}</a>
				 <c:if test="${item.id==item.parent.id }">
						${item.location}
						</c:if>
					<ul>
						<li><a href="javascript:"
							onclick="$.bringBack({id:'1', districtName:'崇明', cityName:'上海'})"></a></li>
					</ul></li>
			</c:forEach>
		</ul>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="button">
					<div class="buttonContent">
						<button class="close" type="button">关闭</button>
					</div>
				</div></li>
		</ul>
	</div>
</div>