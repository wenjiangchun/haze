<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
	<title>Home</title>
	<script>
		$(document).ready(function() {
			$("#index-tab").addClass("active");
		});
	</script>
</head>
<body>
	<h1>框架说明</h1>
	<ul style="padding: 15px;">
		<li>使用Spring+Hibernate+Spring Data JPA+Spring MVC开发技术。</li>
		<li>权限验证采用Apache Shiro控制，目前系统权限基于角色分配，权限资源包括菜单和操作权限。</li>
		<li>数据持久化采用基于注解JPA方式，JPA实现框架使用Hibernate，缓存使用Echache。</li>
		<li>集成Activiti工作流</li>
		<li>使用Maven构建项目</li>
		<li>前台样式使用BootStrap + Jquery</li>
	</ul>
</body>
</html>