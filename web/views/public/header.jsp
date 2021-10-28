<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	*{
		margin:0;padding:0;
		border-size:border-box;
	}
	.header{
		width:800px;height:80px;
		margin:0 auto; /*水平居中*/
		background-color:#CC6;
	}
	.row1{
		width:800px;height:50px;
		text-align:center;
		overflow:hidden;  /*对溢出部分隐藏，但仅对静态文本*/
	}
	.row11{
		width:200px; height:50px;
		line-height:50px; /*行高*/
		float:left;
		overflow:hidden;
	}
	.row12{
		width:450px; line-height:50px;
		font-size:25px;
		font-family:"方正舒体";
		filter:shadow(dirction=45,color=yellow);
		float:left;
	}
	.row13{
		width:150px;height:50px;
		line-height:50px; /*行高*/		
		float:left;
	}
	.row12 span{
		font-size: 18px;
		color: blue;
		font-family: 华文仿宋;
	}
	.row2{  /*菜单*/
		width:800px; height:30px;
		line-height:30px;
		padding-left:150px;
	}
	.row2 ul li{   /*使用空格建立的上下文样式*/
		list-style:none;  /*取消项目符号*/
		width:120px;
		float:left;  /*并排列表项*/
	}
	.row2 ul li a{
		font-size:20px;
		color:#666666;  /*浅灰色*/
		text-decoration:none;
	}
	.row2 ul li a:hover{
		color:#FF0000;  /*红色*/
		text-decoration:underline;
	}
</style>
<div class="header">
    	<div class="row1">
    		<div class="row11"><span id="dtps">date and time</span></div>
			<script><!--客户端脚本，window对象的定时器方法-->
				window.setInterval("dtps.innerText=new Date().toLocaleString()",100);
			</script>
			<div class="row12">会员管理系统<span>【MVC模式—DAO-程序代码分3层】</span></div>
    		<div class="row13">
                        <c:if test="${username==null}">
                        	<font color="red">尚未登录！</font>
                        </c:if>
                        <c:if test="${sessionScope.username!=null}">
                        	欢迎您：<font color="green">${username}</font>
                        </c:if>
			</div>
		</div>
    	<div class="row2">
    		<ul>
    			<!-- 请求jsp页面 -->
    			<li><a href="${pageContext.request.contextPath}/views/mLogin.jsp">会员登录</a></li>   
    			<li><a href="${pageContext.request.contextPath}/views/mRegister.jsp">会员注册</a></li>
    			<!-- 请求Servlet -->
    			<li><a href="${pageContext.request.contextPath}/UpdateServlet0">信息修改</a></li>    
    			<li><a href="${pageContext.request.contextPath}/Logout">会员登出</a></li></ul></div>
</div>

