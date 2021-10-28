<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
	<title>后台管理主页</title>
	<style type="text/css">
		.main {
			width: 800px;
			height: 500px;
			margin: 0 auto; /*水平居中*/
		}
		.row1 {
			width: 800px;
			height: 40px;
			font-size: 35px;
			text-align: center;
			background-color: #CC6;
		}
		.row2 {
			width: 800px;
			height: 460px;
			/*border:1px red solid;*/
		}
		.row2_left {
			width: 250px;
			height: 460px;
			float: left;
			background: url(../../images/bg.jpg);
		}
		.row2_left ul {
			padding-left: 15px;
			//
		}
		.row2_right {
			width: 550px;
			height: 460px;
			float: left;
			margin-top: 10px;
			background: url(../../images/bg2.jpg) repeat-x;
		}
	</style>
	<link rel="stylesheet" href="../../css/adminIndex.css" type="text/css" />
	<script src="../../js/jquery-1.10.2.min.js" type="text/javascript"></script>
</head>

<body>
	<script type="text/javascript">
		$(document).ready(function() { 
			$(".subNav").click(function() {
				var temp = $(this).next(".navContent"); //当前主菜单项的子菜单
				temp.slideToggle(500); //当前主菜单项的子菜单卷起或展开
				temp.siblings(".navContent").slideUp("fast"); //同胞元素向上卷起
			});
		});
	</script>
	<div class="main">
		<div class="row1">会员后台管理</div>
		<div class="row2">
			<div class="row2_left">
				<div class="subNavBox">
					<div class="subNav">会员管理</div>
					<ul class="navContent" style="display: block">
						<li><a href="<%=request.getContextPath()%>/MemListServlet"
							target="kj">会员信息</a></li>
						<li><a href="<%=request.getContextPath()%>/MemDeleteServlet"
							target="kj">会员删除</a></li></ul>
							
					<div class="subNav">新闻管理</div>
					<ul class="navContent">
						<li><a href="#">新闻页面上传</a></li>
						<li><a href="#">XXXXXXXXXXX</a></li></ul>
						
					<div class="subNav">系统</div>
					<ul class="navContent">
						<li><a href="<%=request.getContextPath()%>/AdminLogout">登出返回前台</a></li>
						<li><a href="#">XXXXXXXXX</a></li></ul></div></div>
			<div class="row2_right">
				<iframe width="550" height="460" src="adminIndex0.html" name="kj"></iframe></div></div>
	</div>
</body>
</html>