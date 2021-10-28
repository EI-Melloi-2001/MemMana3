<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<title>管理员登录</title>
	<style>
		.main{
			width:798px; height:300px;
			border:1px red solid;
            margin:0px auto;
		}
		form{
			margin-top:100px;margin-left:240px;
			line-height:35px;
		}
		</style>
</head>
<body>
	<%@ include file="public/header.jsp" %>
	<div class="main">
  		<form method="post" action="${pageContext.request.contextPath}/Admin"><!-- 请求控制器Admin -->
  			请输入管理员密码：<input type="password" name="pwd" value="admin">
  			<input type="submit" value="提交"><br/>
  			<font color="red">${msg}</font>   <!-- 提交信息错误时回显 -->
 		</form>
 	</div>
 	 <%@ include file="public/footer.jsp" %>
</body>
</html>