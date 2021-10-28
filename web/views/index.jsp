<!--在MVC模式或框架开发中，JSP页面不包含任何Java脚本，只做显示
	控制器方法处理数据完毕后调用JSP页面显示时，通常会传递（模型）数据 
	JSP显示模型数据时，通常使用JSTL标签及EL表达式 -->

<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 下面的taglib指令可以去掉，因为包含的文件header.jsp里有 -->
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<!DOCTYPE>
<html>
<head>
<title>会员信息管理系统（MVC版）</title>
<style>
	*{
		margin:0;padding:0;
		box-sizing:border-box;
	}
	.main {
		width: 800px;
		height: 500px;
		margin 0px auto; /*水平居中*/
	}
	
	.left {
		width: 250px;
		height: 400px;
		float: left;
		overflow: hidden;
		background: url(images/bg.jpg);
	}
	.left .bt{
		padding-left:85px;
		line-height:50px;
		color:green;
	}
	.left ul {
		list-style: none; /*取消项目符号*/
		padding-left: 25px; //
		overflow: hidden;
	}
	
	.left li {
		line-height: 35px; /*列表文字行高*/
	}
	
	.right {
		width: 550px;
		height: 400px;
		float: left;
		/*overflow:hidden;*/
	}
</style>
<body>
	<%@ include file="public/header.jsp"%>
	<div class="main">
		<div class="left">
			<div class="bt">技术文档</div>
			<ul>
				<!-- 新闻列表 ，静态HTML代码与动态代码混合编程-->
				<c:forEach items="${newsList}" var="row">
					<li><a href="${row.contentPage }" target="iframeName">${row.contentTitle }</a></li>
				</c:forEach></ul></div>
		<div class="right">
			<iframe name="iframeName" width="550px" height="480px"
				src="${pageContext.request.contextPath}/views/public/index0.html" frameborder="0"> </iframe>
		</div>
	</div>
	<%@ include file="public/scrollingphos.jsp"%>
	<%@ include file="public/footer.jsp"%>
</body>
</html>
