<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="sidebar-nav">
	<div style="padding: 8px 0;" class="well">
		<ul class="nav nav-list">
			<c:forEach items="${resources}" var="resource">
		       <c:if test="${resource.parent == null }">
		          <shiro:hasPermission name="${resource.permission }">
		          <li class="nav-header">${resource.name}</li>
		          </shiro:hasPermission>
		          <c:forEach items="${resource.childrens}" var="children">
		           <shiro:hasPermission name="${children.permission }">
		               <li><a href="${ctx}${children.url}">${children.name}</a></li>
		           </shiro:hasPermission>
		          </c:forEach>
		       </c:if>
            </c:forEach>
			<li class="nav-header">其它演示</li>
			<li>
			   <a href="${ctx}/article/view/"><i class="icon-user"></i>工作流演示</a>
			</li>
			<li>
               <a href="${ctx}/demo/groovy/view/">groovy集成演示</a>
            </li>
		</ul>
	</div>
</div>

