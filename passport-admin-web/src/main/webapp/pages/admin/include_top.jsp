<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8"%>
<div id="pageHd">
	<h1>passport后台管理系统</h1>
	<ul>
		<li>欢迎：<strong>${UserName}</strong></li>
		<c:forEach items="${MenuTypeList}" var="menuType">
		<li><a href="${menuType.url}" target="_blank">${menuType.name}</a></li>
		</c:forEach>
	</ul>
</div>