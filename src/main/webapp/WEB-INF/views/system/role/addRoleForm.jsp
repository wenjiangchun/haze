<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title><fmt:message key="add"/><fmt:message key="role"/></title>
	<script type="text/javascript" src="${ctx}/resources/jquery/jquery-form/jquery.form.js"></script>
	<script type="text/javascript" src="${ctx}/resources/jquery/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$.validator.addMethod("letterswith", function(value, element) {
			return this.optional(element) || /^[a-z\-.,()'"\s]+$/i.test(value);
		}, "Letters or punctuation only please");
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
	            "roleName": { 
	            	          required:true,
	            	          letterswith:true,
	            	          remote:{
	            	        	  method:"post",
	            	        	  url:"${ctx}/system/role/isExistRoleName/",
	            	        	  dataType:'json',
	            	        	  data:{
	            	                  loginName:function(){return $("#roleName").val();}
	            	                } 
	            	          }
                             },
                 "name":{required:true}
	        },
	        messages: {   
	            "roleName": {required:"英文名不能为空",remote: "英文名已存在，请重新输入！",letterswith:"请输入英文字母"},
	            "name":{required:"角色名不能为空"}
	        }
	    });
	});
	</script>
</head>

<body>
	<ul class="breadcrumb">
        <li><a href="${ctx}"><i class="icon-home"></i> <fmt:message key="home"/></a><span class="divider">/</span></li>
        <li><a href="javascript:void(0)"><fmt:message key="system" /><fmt:message key="manager" /></a><span class="divider">/</span></li>
         <li><a href="${ctx}/system/role/view"><fmt:message key="role" /><fmt:message key="manager" /></a><span class="divider">/</span></li>
        <li class="active"><fmt:message key="add" /><fmt:message key="role" /></li>
    </ul>
	<form:form id="inputForm" modelAttribute="role" action="${ctx}/system/role/save" method="post" class="form-horizontal">
		<fieldset>
			<legend><fmt:message key="add"/><fmt:message key="role"/></legend>
			<div class="control-group">
				<label for="roleName" class="control-label"><fmt:message key="role.roleName"/>:</label>
				<div class="controls">
					<input type="text" id="roleName" name="roleName"  class="input-large"/>
				</div>
			</div>
			<div class="control-group">
				<label for="name" class="control-label"><fmt:message key="role.name"/>:</label>
				<div class="controls">
					<input type="text" id="name" name="name" class="input-large"/>
				</div>
			</div>
			<div class="control-group">
				<label for="status" class="control-label"><fmt:message key="status"/>:</label>
				<div class="controls">
					<c:forEach items="${statuss}" var="status" varStatus="index">
					       <c:if test="${index.index == 0 }">
					         <label class="radio inline">
					           <input type="radio" name="status" value="${status}" checked="checked"/>${status.statusName}
					         </label>
					       </c:if>
					       <c:if test="${index.index != 0 }">
				             <label class="radio inline">
				               <input type="radio" name="status" value="${status}"/>${status.statusName}
				             </label>
					       </c:if>
					</c:forEach>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="<fmt:message key="submit"/>"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="<fmt:message key="cancel"/>" onclick="history.back()"/>
			</div>
		</fieldset>
	</form:form>
</body>
</html>
