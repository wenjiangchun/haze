<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>添加文章</title>
	 
	<script type="text/javascript">
		$(document).ready(function() {
			$("#account-tab").addClass("active");
		});
	</script>
	<script type="text/javascript" src="${ctx}/resources/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="${ctx}/resources/ueditor/editor_all.js"></script>
</head>

<body>
	<h1>添加文章</h1>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/article/saveAndStartWorkflow" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>添加文章</small></legend>
			<div id="messageBox" class="alert alert-error input-large controls" style="display:none">输入有误，请先更正。</div>
			<div class="control-group">
				<label for="title" class="control-label">文章标题:</label>
				<div class="controls">
					<input type="text" id="title" name="title" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="content" class="control-label">文章内容:</label>
				<div class="controls" >
				    <textarea name="content" id="myEditor">这里写你的初始化内容</textarea> 
				    <script type="text/javascript">
				    var editor = new UE.ui.Editor();
				    editor.options.initialFrameWidth="100%";
		            editor.render("myEditor"); 
				    </script>
				</div>
			</div>
			<div class="control-group">
				<label for="status" class="control-label">选择流程:</label>
				<div class="controls">
				   <select name="processKey">
					   <c:forEach items="${processDefinitions}" var="p">
					     <option value="${p.key}">${p.name}</option>
					   </c:forEach>
				   </select>
				</div>
			</div>
			
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交并启动流程"/>&nbsp;	
			</div>
		</fieldset>
	</form:form>
</body>
</html>
