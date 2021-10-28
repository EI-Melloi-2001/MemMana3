<%@page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<!DOCTYPE>
<html>
<head>
	<title>会员注册</title>
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
			margin-top:15px;margin-left:240px;
			line-height:30px;
		}
		</style>
</head>

<body>
	<%@ include file="public/header.jsp"%>
	<div class="main">
		<br/><h3 class="text-center"><strong>会员注册</strong></h3>
		<form method="post" action="${pageContext.request.contextPath}/RegisterServlet" class="form">
			会员名称：<input type="text" name="username"><br /> 会员真名：<input
				type="text" name="realname"><br /> 会员密码：<input
				type="password" name="password"><br /> 电话号码：<input
				type="text" name="mobile"><br /> 年&nbsp;&nbsp;龄：<input
				type="text" name="age"><br /> <input type="submit"
				value="注册" class="btn" />
		</form>
	</div>
	<%@ include file="public/footer.jsp"%>
</body>

</html>