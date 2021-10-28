<%@page language="java"  pageEncoding="UTF-8"%>
<html>
<head>
	<title>会员信息修改</title>
			<!-- 由于是转发页面里加载样式文件和js文件，因此，需要获取应用的根路径 -->
	<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
		rel="stylesheet">
	<script src="<%=application.getContextPath()%>/js/jquery-1.10.2.min.js"
		type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"
		type="text/javascript"></script>
	<style>
		*{margin:0;padding:0}
		.main{
			width:800px; height:300px;
			/*border:1px red solid;*/
			margin:0px auto;
		}
		form{
			margin-top:20px;margin-left:240px;
			line-height:30px;
		}
		</style>
</head>
<body>
    <%@ include file="public/header.jsp" %>
    <div class="main">
    	 <br/><h3 class="text-center"><strong>会员信息修改</strong></h3>
		 <form method="post" action="${pageContext.request.contextPath}/UpdateServlet" class="form">
			会员密码：<input type="password" name="password" value="${user.password}"> <br/>
			会员真名：<input type="text" name="realname" value="${user.realname}"> <br/>
			手机号码：<input type="text" name="mobile" value="${user.mobile}"> <br/>
			会员年龄：<input type="text" name="age" value="${user.age}"> <br/>
			<input type="submit" value="提交" class="btn"/></form></div>
	 <%@ include file="public/footer.jsp"%>
</body>
</html>