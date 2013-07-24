<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title><fmt:message key="add"/><fmt:message key="group"/></title>
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
		            "configName": {required:true,
		            	          remote:{
		            	        	  method:"post",
		            	        	  url:"${ctx}/system/config/isNotExistConfigName",
		            	        	  dataType:'json'
		            	          }
	                             },
	                 "name":{required:true},
	                 "value":{required:true}
		        },
		        messages: {   
		            "configName": {required:"配置名称不能为空",remote: "配置名称已存在，请重新输入！"},
		            "name":{required:"配置中文名称不能为空"},
                    "value":{required:"配置值不能为空"}
		        }
		    });
		});
	</script>
</head>

<body>
    <ul class="breadcrumb">
        <li><a href="${ctx}"><i class="icon-home"></i><fmt:message key="home"/></a><span class="divider">/</span></li>
        <li><a href="javascript:void(0)"><fmt:message key="system" /><fmt:message key="manager" /></a><span class="divider">/</span></li>
        <li><a href="${ctx}/system/config/view"><fmt:message key="config" /><fmt:message key="manager" /></a><span class="divider">/</span></li>
        <li class="active"><fmt:message key="add" /><fmt:message key="config" /></li>
    </ul>
	<form:form id="inputForm" modelAttribute="config" action="${ctx}/system/config/save" method="post" class="form-horizontal">
		<fieldset>
			<legend><fmt:message key="add" /><fmt:message key="config" /></legend>
			<div class="control-group">
				<label for="name" class="control-label"><fmt:message key="config.name"/>:</label>
				<div class="controls">
					<input type="text" id="name" name="name" class="input-large"/>
				</div>
			</div>
			<div class="control-group">
				<label for="configName" class="control-label"><fmt:message key="config.configName"/>:</label>
				<div class="controls">
					<input type="text" id="configName" name="configName"  class="input-large"/>
				</div>
			</div>
			<div class="control-group">
				<label for="value" class="control-label"><fmt:message key="config.value"/>:</label>
				<div class="controls">
					<input type="text" id="value" name="value" class="input-large" placeholder=""/>
				</div>
			</div>
			<div class="control-group">
				<label for="description" class="control-label"><fmt:message key="description"/>:</label>
				<div class="controls">
				    <textarea rows="3" id="description" name="description"></textarea>
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
