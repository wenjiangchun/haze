<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<title><fmt:message key="add" /><fmt:message key="user" /></title>
	<script type="text/javascript" src="${ctx}/resources/jquery/jquery-form/jquery.form.js"></script>
	<script type="text/javascript" src="${ctx}/resources/jquery/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({ // 注册Form表单验证方法
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
		            "loginName": {required:true,
		            	          remote:{
		            	        	  method:"post",
		            	        	  url:"${ctx}/system/user/isNotExistLoginName/",
		            	        	  dataType:'json',
		            	        	  data:{
		            	                  loginName:function(){return $("#loginName").val();}
		            	                } 
		            	          }
	                             },
	                 "name":{required:true},
	                 "password":{required:true},
	                 "email":{email:true}
		        },
		        messages: {   
		            "loginName": {required:"登录名不能为空",remote: "登录名已存在，请重新输入！"},
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
        <li class="active"><fmt:message key="add" /><fmt:message key="user" /></li>
    </ul>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/system/user/save" method="post" class="form-horizontal">
		<fieldset>
			<legend><fmt:message key="add" /><fmt:message key="user" /></legend>
			<div id="messageBox" class="alert alert-error input-large controls" style="display:none">输入有误，请先更正。</div>
			<div class="control-group">
				<label for="loginName" class="control-label"><fmt:message key="user.loginName" />:</label>
				<div class="controls">
					<input type="text" id="loginName" name="loginName" class="input-large" placeholder="请输入登录名"/>
				</div>
			</div>
			<div class="control-group">
				<label for="name" class="control-label"><fmt:message key="user.name" />:</label>
				<div class="controls">
					<input type="text" id="name" name="name"  class="input-large" placeholder="请输入用户名"/>
				</div>
			</div>
			<div class="control-group">
				<label for="plainPassword" class="control-label"><fmt:message key="user.password" />:</label>
				<div class="controls">
					<input type="password" id="plainPassword" name="password" class="input-large" placeholder="请输入密码"/>
				</div>
			</div>
			<div class="control-group">
                <label for="email" class="control-label"><fmt:message key="user.email" />:</label>
                <div class="controls">
                    <input type="text" id="email" name="email" placeholder="请输入邮箱"/>
                </div>
            </div>
			<div class="control-group">
				<label for="status" class="control-label"><fmt:message key="user.sex" />:</label>
				<div class="controls">
					<c:forEach items="${sexs}" var="sex" varStatus="index">
					   <c:if test="${index.index == 0 }">
						  <label class="radio inline"><input type="radio" name="sex" value="${sex}" checked="checked"/>${sex.sexName}</label>
					   </c:if>
					   <c:if test="${index.index != 0 }">
                          <label class="radio inline"><input type="radio" name="sex" value="${sex}"/>${sex.sexName}</label>
                       </c:if>
					</c:forEach>
				</div>
			</div>
			<div class="control-group">
				<label for="groupList" class="control-label"><fmt:message key="role" />:</label>
				<div class="controls">
					<c:forEach items="${roleList}" var="role">
						<label class="checkbox"><input type="checkbox" name="roleIds" value="${role.id}"/> ${role.name}</label>
					</c:forEach>
				</div>
			</div>	
			<div class="control-group">
				<label for="status" class="control-label"><fmt:message key="status" />:</label>
				<div class="controls">
					<c:forEach items="${statuss}" var="status" varStatus="index">
					 <c:if test="${index.index == 0 }">
						<label class="radio inline"><input type="radio" name="status" value="${status}" checked="checked"/>${status.statusName}</label>
					 </c:if>
					 <c:if test="${index.index != 0 }">
                        <label class="radio inline"><input type="radio" name="status" value="${status}" />${status.statusName}</label>
                     </c:if>
					</c:forEach>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="<fmt:message key="submit" />"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="<fmt:message key="cancel" />" onclick="history.back()"/>
				<p class="help-block">(保存后将发送JMS消息通知改动，而消息接收者将发送提醒邮件)(后续完善)</p>			
			</div>
		</fieldset>
	</form:form>
</body>
</html>
