<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title></title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#account-tab").addClass("active");
			$("#delete_btn").click(function() {
				var ids = new Array();
				$("input[name=user_id_checkbox]").each(function() {
					if (this.checked) {
						ids.push($(this).val());
					}
				});
				window.location.href="${ctx}/system/article/delete/" + ids ;
			});
			$("#add_btn").click(function() {
				window.location.href="${ctx}/article/add";
			});
			
			$(".handle").click(function() {
				var tkey = $(this).attr("tkey");
				var tid = $(this).attr("tid");
				var articleId = $(this).attr("articleId");
				window.location.href = "${ctx}/article/handle.action?tkey="+tkey+"&tid="+tid+"&articleId="+articleId;
			}) ;
		});
		
	</script>
</head>

<body>
	<div class="nav1">
        <a class="tip-bottom" title="" href="${ctx}"
            data-original-title="Go to Home"> <i class="icon-home"></i> 首页
        </a> 
        <a href="javascript:void(0)">工作流演示</a>
        <a class="active" href="#">代办信息列表</a>
    </div>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button><fmt:message key="${message}"/></div>
	</c:if>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>文章标题</th>
			<th>当前节点</th>
			<th>发起人</th>
			<th>发起时间</th>
			<th>流程版本</th>
			<th><fmt:message key="operate"/></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${articleList}" var="article">
			<tr>
				<td>${article.title}&nbsp;</td>
				<td>${article.task.name}&nbsp;</td>
				<td>${article.user.name}&nbsp;</td>
				<td>
				    <fmt:formatDate value="${article.task.createTime }" pattern="yyyy年MM月dd日 HH:mm:ss"/>
			    </td>
				<td>${article.processDefinition.suspended ? "已挂起" : "正常" }；<b title='流程版本号'>V: ${article.processDefinition.version }</b></td>
				<td>
					<c:if test="${empty article.task.assignee }">
                            <a class="claim" href="${ctx }/article/claimArticle?taskId=${article.task.id}">签收</a>
                        </c:if>
                    <c:if test="${not empty article.task.assignee }">
                            <a class="handle" tkey='${article.task.taskDefinitionKey }' tname='${article.task.name}' tid="${article.task.id}" href="#" articleId="${article.id }">办理</a>
                   </c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>		
	</table>
	<button type="button" class="btn" id="add_btn"><fmt:message key="add"/><fmt:message key="article"/></button>
	<button type="button" class="btn" id="delete_btn"><fmt:message key="delete"/><fmt:message key="article"/></button>
</body>
</html>
