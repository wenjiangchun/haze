<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title></title>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		
	</script>
	<script type="text/javascript" src="${ctx}/resources/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="${ctx}/resources/ueditor/editor_all.js"></script>
</head>

<body>
	<div class="nav1">
        <a class="tip-bottom" title="" href="${ctx}"
            data-original-title="Go to Home"> <i class="icon-home"></i> 首页
        </a> 
        <a class="active" href="#">Groovy集成演示</a>
    </div>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	
	<form:form id="inputForm" action="${ctx}/demo/groovy/saveGroovy" modelAttribute="user"  method="post" class="form-horizontal">
        <fieldset>
            <legend><small>编辑Groovy代码</small></legend>
            <div class="control-group">
                <label for="content" class="control-label">代码内容:</label>
                <div class="controls" >
                    <textarea name="groovyContent" id="myEditor">${groovyString}</textarea> 
                    <script type="text/javascript">
                    var editor = new UE.ui.Editor();
                    editor.options.initialFrameWidth="100%";
                    editor.render("myEditor"); 
                    </script>
                </div>
            </div>
            <div class="form-actions">
                <input id="submit_btn" class="btn btn-primary" type="submit" value="保存代码并立即生效"/> 
            </div>
        </fieldset>
    </form:form>
</body>
</html>
