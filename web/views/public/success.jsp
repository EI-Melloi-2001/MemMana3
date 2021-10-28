<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>Welcome,
<%
	String un=(String)session.getAttribute("username");
	out.println(un);
 %>
</body>
</html>