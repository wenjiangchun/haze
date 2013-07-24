<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户登陆</title>
<link type="image/x-icon" href="${ctx}/resources/images/favicon.ico"
	rel="shortcut icon">
<link href="${ctx}/<spring:theme code='bootstrap-css'/>"
	type="text/css" rel="stylesheet" />
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
<script src="${ctx}/resources/jquery/jquery-1.9.1.js"></script>
<script src="${ctx}/resources/bootstrap/2.2.2/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/jquery/jquery-form/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/resources/jquery/jquery-validation/jquery.validate.min.js"></script>
<script>
	$(document).ready(function() {
		$("#loginForm").validate({
            success: function(label) {
            },
            errorPlacement: function(error, element) { 
                error.appendTo( element.parent() ); 
            },
            errorClass: "help-inline",
            focusInvalid: false,   
            onkeyup: true,
            highlight: function(element, errorClass) {
                $(element).parent().parent().removeClass("success").addClass("error");
             },
            unhighlight: function(element, errorClass) {
                $(element).parent().parent().removeClass("error").addClass("success");
            },
            rules: {
                "username": {required:true},
                "password":{required:true}
            },
            messages: {   
                "username": {required:"用户名不能为空"},
                "password":{required:"密码不能为空"}
            }
        });
	});
	
	function reloadValidateCode() {
		$("#validateCodeImg").attr("src","validateCode?date = " + new Date() + Math.floor(Math.random()*24));
	}
</script>
</head>

<body>
	<div class="container">
		<form id="loginForm" class="form-signin" action="${ctx}/login"
			method="post">
			<%
				String error = (String) request
						.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
				if (error != null) {
			%>
			<div class="alert alert-error controls input-large">
				<button class="close" data-dismiss="alert">×</button>
				<%
					if (error.contains("DisabledAccountException")) {
							out.print("用户已被屏蔽,请登录其他用户.");
						} else if (error.contains("UserNotExistException")) {
							out.print("用户名不存在，请重试");
						} else if (error.contains("ExpiredCredentialsException")) {
							out.println("用户已失效，请登录其他用户");
						} else if (error.contains("IncorrectCredentialsException")) {
							out.println("密码错误，请重试");
						} else {
							out.println(error);
						}
				%>
			</div>
			<%
				}
			%>
			<h3 class="form-signin-heading">请登录</h3>
			<div class="control-group">
				<label class="control-label" for="username">用户名：</label>
				<div class="controls">
					<input type="text" id="username" name="username"
						placeholder="<fmt:message key="user.name"/>">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password">密码：</label>
				<div class="controls">
					<input type="password" id="password" name="password"
						placeholder="Password">
				</div>
			</div>
			<div class="control-group" style="display:none">
				<div class="controls">
					<input type="text" name="validateCode" id="validateCode" class="input-small" /> 
					<span class="help-inline"><img id="validateCodeImg" src="validateCode" width="70" align="absmiddle" /><a
                        href="javascript:reloadValidateCode();">看不请?</a></span>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<label class="checkbox"> <input type="checkbox">Remember me</label>
					<button type="submit" class="btn btn-primary">登录</button>
					<span class="help-inline"><a href="javascript:reloadValidateCode();">忘记密码?</a></span>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
