<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title><fmt:message key="edit" /><fmt:message key="user" /></title>
	<script type="text/javascript" src="${ctx}/resources/jquery/jquery-form/jquery.form.js"></script>
	<script type="text/javascript" src="${ctx}/resources/jquery/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#inputForm").validate({
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
                 "name":{required:true},
                 "password":{required:true},
                 "email":{email:true}
	        },
	        messages: {   
	            "name":{required:"用户名不能为空"},
                "password":{required:"密码不能为空"},
                "email":{email:"邮箱格式不正确"}
	        }
	    });
	});
	</script>
</head>

<body>
	<ul class="breadcrumb">
        <li><a href="${ctx}"><i class="icon-home"></i><fmt:message key="home"/></a><span class="divider">/</span></li>
        <li><a href="javascript:void(0)"><fmt:message key="system" /><fmt:message key="manager" /></a><span class="divider">/</span></li>
        <li><a href="${ctx}/system/user/view"><fmt:message key="user" /><fmt:message key="manager" /></a><span class="divider">/</span></li>
        <li class="active"><fmt:message key="edit" /><fmt:message key="user" /></li>
    </ul>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/system/user/update" method="post" class="form-horizontal">
		<fieldset>
			<legend><fmt:message key="edit" /><fmt:message key="user" /></legend>
			<div class="control-group">
				<label for="loginName" class="control-label"><fmt:message key="user.loginName" />:</label>
				<div class="controls">
				    <span id="loginName" class="uneditable-input">${user.loginName}</span>
				</div>
			</div>
			<div class="control-group">
				<label for="name" class="control-label"><fmt:message key="user.name" />:</label>
				<div class="controls">
					<input type="text" id="name" name="name"  class="input-large" value="${user.name}"/>
				</div>
			</div>
			<div class="control-group">
                <label for="email" class="control-label"><fmt:message key="user.email" />:</label>
                <div class="controls">
                    <input type="text" id="email" name="email" value="${user.email}"/>
                </div>
            </div>
			<div class="control-group">
				<label for="status" class="control-label"><fmt:message key="user.sex" />:</label>
				<div class="controls">
					<c:forEach items="${sexs}" var="sex">
					    <c:if test="${(sex == user.sex) }">
					         <label class="radio inline"><input type="radio" name="sex" value="${sex}" checked="checked"/>${sex.sexName}</label>
					    </c:if>
						<c:if test="${! (sex == user.sex) }">
                             <label class="radio inline"><input type="radio" name="sex" value="${sex}"/>${sex.sexName}</label>
                        </c:if>
					</c:forEach>
				</div>
			</div>
			<div class="control-group">
				<label for="status" class="control-label"><fmt:message key="status" />:</label>
				<div class="controls">
					<c:forEach items="${statuss}" var="status">
					  <c:if test="${(status == user.status) }">
					     <label class="radio inline"><input type="radio" name="status" value="${status}" checked="checked"/>${status.statusName}</label>
					  </c:if>
					  <c:if test="${!(status == user.status) }">
                         <label class="radio inline"><input type="radio" name="status" value="${status}"/>${status.statusName}</label>
                      </c:if>
					</c:forEach>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="<fmt:message key="submit" />"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="<fmt:message key="cancel" />" onclick="history.back()"/>
				<p class="help-block">(保存后将发送JMS消息通知改动，而消息接收者将发送提醒邮件)(待完善)</p>			
			</div>
			<input type="hidden" name="id" value="${user.id }">
		</fieldset>
	</form:form>
</body>
</html>
