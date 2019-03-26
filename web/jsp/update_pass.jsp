<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<!--头部-->
	<%@ include file="top.jsp"%>
	<!--主体内容-->
	<section class="publicMian">
		<%@ include file="left.jsp"%>
		<div class="right">
			<h1>修改密码</h1>
			<form method="post" action="/UserServlet?choose=4">
				<a style="font-size: 20px;color: red"> 用户名：${username}</a><br>
				<input type="hidden" name="username" value="${username}">
				<br> 旧密码:<input type="password" name="oldpassword" id="oldpass"><span id="oldpass2"></span><br>
				<br> 新密码:<input type="password" name="password" id="pass"><span id="spass"></span><br>
				<br>
				<span id="error"></span>
				<br> <input type="submit" value="提交">
			</form>
		</div>
	</section>
	<footer class="footer"> 版权归XXXX </footer>
	<script src="../js/time.js"></script>
</body>
<script src="../js/jquery.js"></script>
</html>