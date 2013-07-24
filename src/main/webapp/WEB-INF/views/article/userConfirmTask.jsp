<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>用户确认</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#account-tab").addClass("active");
		});
	</script>
</head>

<body>
	<h1>用户确认</h1>
	<form:form id="inputForm" modelAttribute="paramVariables" action="${ctx}/article/handleArticle" method="post" class="form-horizontal">
		<fieldset>
			<legend><small>用户确认</small></legend>
			<c:forEach items="${resultList}" var="result">
        <div style="width:400px;padding-left:200px;margin: 10px">
             <table class="table table-striped table-bordered .table-hover" >
             <tbody>
                <tr>
                  <td>节点名称</td>
                  <td>${result.taskName}</td>
               </tr>
               <tr>
                  <td>操作人</td>
                  <td>${result.user.name}</td>
               </tr>
               <tr>
                  <td>操作时间</td>
                  <td></td>
               </tr>
               <tr>
                  <td>填写内容</td>
                  <td>${result.content}</td>
               </tr>
               <tr>
                  <td>处理结果</td>
                  <td>${result.result}</td>
               </tr>
            </tbody>
    </table>
        </div>
      </c:forEach>
			<div class="control-group">
				<label for="content" class="control-label">用户确认</label>
			</div>
			<input type="hidden" name="taskId" value="${taskId}" />
            <input type="hidden" name="articleId" value="${articleId}" />
            <input type="hidden" name="deptDutyUser" value="${currentUserId}" />
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">确认</button>
				<button type="button" class="btn" onclick="history.go(-1)">返回</button>
			</div>
		</fieldset>
	</form:form>
</body>
</html>
