<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提示信息页面</title>
<style>
	.main{
		width:800px; height:300px;
		border:1px red solid;
        margin:0px auto;
        text-align:center;
	}
	.content{
        margin-top:100px;
     }
</style>
</head>
<body>
	 <%@include file="header.jsp"%>
	 <div class="main">
	 	<div class="content"><%=request.getAttribute("message")%><br><a href="HomeServlet">返回前台主页<a></div>

<%-- 	<div class="content">${requestScope.message}<br><a href="HomeServlet">返回前台主页</a></div>
 		<div class="content">${message}<br><a href="HomeServlet">返回前台主页</a></div>  --%>
	</div> 
	
	<%@include file="footer.jsp"%>
</body>
</html>