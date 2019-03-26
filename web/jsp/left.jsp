<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="left">
		<h2 class="leftH2">
			<span class="span1"></span>功能列表 <span></span>
		</h2>
		<nav>
			<ul>
				<li>
					<ul>
						<li>权限管理</li>
						<li>
							<ul class="list">
								<li><a href="/jsp/privilege_add.jsp">添加权限</a></li>
								<li><a href="/PrivilegeServlet?choose=3">查看权限</a></li>
							</ul>
						</li>
					</ul>
				</li>
				<li>
					<ul>
						<li>角色管理</li>
						<li>
							<ul class="list">
								<li><a href="/jsp/roles_add.jsp">添加角色</a></li>
								<li><a href="/RolesServlet?choose=1">查看角色</a></li>
							</ul>
						</li>
					</ul>
				</li>
				<li>
					<ul>
						<li>用户管理</li>
						<li>
							<ul class="list">
								<%--<li><a href="/UserServlet?choose=3">查看用户</a></li>--%>
								<li><a href="/jsp/users_add.jsp">添加用户</a></li>
								<li><a href="/jsp/update_pass.jsp">密码修改</a></li>
								<li><a href="/UserServlet?choose=3">为用户授权角色</a></li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>

		</nav>
	</div>
</body>
</html>