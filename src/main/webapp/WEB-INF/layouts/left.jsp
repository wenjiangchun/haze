<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="sidebar-nav">
	<div style="padding: 8px 0;" class="well">
		<ul class="nav nav-list">
		<li><a href="${ctx}"><i class="icon-home"></i><fmt:message key="home"/></a></li>
            <li class="nav-header"><i class="icon-wrench"></i>系统管理</li>
            <li>
			   <a href="${ctx}/system/user/view/">用户管理</a>
			</li>
			<li>
			   <a href="${ctx}/system/role/view/">角色管理</a>
			</li>
			<li>
			   <a href="${ctx}/system/group/view/">机构管理</a>
			</li>
			<li>
			   <a href="${ctx}/system/resource/view/">资源管理</a>
			</li>
			<li>
			   <a href="${ctx}/system/config/view/">配置管理</a>
			</li>
            <li class="nav-header"><i class="icon-time"></i>任务调度</li>
            <li>
			   <a href="${ctx}/schedule/jobDetail/view/">作业管理</a>
			</li>
			<li>
			   <a href="${ctx}/schedule/trigger/view/">触发器管理</a>
			</li>
			<li class="nav-header"><i class="icon-random"></i>流程管理</li>
			 <li>
			   <a href="${ctx}/workflow/processDefinition/view/">流程定义</a>
			</li>
			<li>
			   <a href="${ctx}/workflow/processInstance/view/">流程实例</a>
			</li>
			<li>
			   <a href="${ctx}/article/view/"><i class="icon-user"></i>工作流演示</a>
			</li>
			<li>
               <a href="${ctx}/demo/groovy/view/">groovy集成演示</a>
            </li>
		</ul>
	</div>
</div>

