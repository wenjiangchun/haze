<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<title><fmt:message key="add" /><fmt:message key="jobDetail" /></title>
	<script type="text/javascript" src="${ctx}/resources/jquery/jquery-form/jquery.form.js"></script>
	<script type="text/javascript" src="${ctx}/resources/jquery/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="${ctx}/resources/ueditor/editor_all.js"></script>
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
		            "jobName": {required:true,
		            	          remote:{
		            	        	  method:"post",
		            	        	  url:"${ctx}/schedule/quartz/jobDetail/isNotExistJobName/",
		            	        	  dataType:'json',
		            	        	  data:{
		            	                  loginName:function(){return $("#jobName").val();}
		            	                } 
		            	          }
	                             },
	                 "jobClassName":{required:true}
		        },
		        messages: {   
		            "jobName": {required:"工作名不能为空",remote: "工作名已存在，请重新输入！"},
		            "jobClassName":{required:"类名不能为空"}
		        }
		    });
		});
	</script>
</head>

<body>
    <ul class="breadcrumb">
        <li><a href="${ctx}"><i class="icon-home"></i><fmt:message key="home"/></a><span class="divider">/</span></li>
        <li><a href="javascript:void(0)"><fmt:message key="schedule" /><fmt:message key="manager" /></a><span class="divider">/</span></li>
        <li><a href="${ctx}/system/user/view"><fmt:message key="jobDetail" /><fmt:message key="manager" /></a><span class="divider">/</span></li>
        <li class="active"><fmt:message key="add" /><fmt:message key="jobDetail" /></li>
    </ul>
	<form:form id="inputForm" modelAttribute="jobDetail" action="${ctx}/schedule/groovyJob/save" method="post" class="form-horizontal">
		<fieldset>
			<legend><fmt:message key="add" /><fmt:message key="jobDetail" /></legend>
			<div class="control-group">
				<label for="name" class="control-label">作业名称:</label>
				<div class="controls">
					<input type="text" id="name" name="name" class="input-large" placeholder="请输入作业名"/>
				</div>
			</div>
			<div class="control-group">
				<label for="codeContent" class="control-label">作业代码:</label>
				<div class="controls">
					 <textarea name="codeContent" id="myEditor"></textarea> 
                    <script type="text/javascript">
                    var editor = new UE.ui.Editor();
                    editor.options.initialFrameWidth="100%";
                    editor.render("myEditor"); 
                    </script>
				</div>
			</div>
			<div class="control-group">
				<label for="description" class="control-label">作业描述:</label>
				<div class="controls">
					<textarea rows="3" id="description" name="description"  class="input-large" placeholder="请输入作业描述"></textarea>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="<fmt:message key="submit" />"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="<fmt:message key="cancel" />" onclick="history.back()"/>
			</div>
		</fieldset>
	</form:form>
</body>
</html>
