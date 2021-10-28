<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<style> 
		form{display:inline}  /* 表单不另行 */
</style>

<title>会员删除</title>
<!-- 由于是转发页面里加载样式文件和js文件，因此，需要获取应用的根路径 -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=application.getContextPath()%>/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript"></script>

<h3 class="text-center"><strong>会员删除</strong></h3>
<table border="1" width="500" class="table table-striped table-bordered table-hover table-condensed">
	<tr>
		<td>会员名称</td>
		<td>会员真名</td>
		<td>手机号</td>
		<td>年龄</td>
		<td>操作</td></tr>
	<c:forEach items="${users }" var="user">
		<tr>
			<td>${user.username }</td>
			<td>${user.realname }</td>
			<td>${user.mobile }</td>
			<td>${user.age }</td>
			<td><a href="<%=request.getContextPath()%>/MemDelete?un=${user.username }"
				onclick="return window.confirm('Are you sure?')">删除</a></td></tr>
	</c:forEach>
	<!-- 下面的一行是导航条 -->
	<tr><td colspan="5">${pageNav}</td></tr>
</table>
