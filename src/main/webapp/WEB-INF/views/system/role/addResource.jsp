<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title><fmt:message key="add"/><fmt:message key="resource"/></title>
	<script type="text/javascript" src="${ctx}/resources/jquery/jquery-tree/jquery.jstree.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#account-tab1").addClass("active");
			
			$("#submit_btn").click(function() {
				var ids = new Array();
                $("input[name=resourcesIds]").each(function() {
                    if (this.checked) {
                        ids.push($(this).val());
                    }
                });
                var roleId = $("#roleId").val();
                var ids = ["7","8"];
				window.location.href="${ctx}/system/role/saveResources/"+roleId+"/"+ids;
			});
			
			 $("#demo1").jstree({ // the `plugins` array allows you to configure the active plugins on this instance
			              "plugins" : ["themes","html_data","checkbox","sort", "ui","types","contextmenu" ],
			              "themes" : {
			            	  "theme" : "classic"
			              },
			           });
		});
		
		function getCheckbox() {
			 var nodes = $("#demo1").jstree('show_checkboxes');  
			 //alert(nodes.length);  
			 $.each(nodes, function(i, n) {  
			    });  
		}
	</script>
</head>

<body>
	<div class="nav1">
        <a class="tip-bottom" title="" href="${ctx}"
            data-original-title="Go to Home"> <i class="icon-home"></i> 首页
        </a> 
        <a href="javascript:void(0)"><fmt:message key="system" /><fmt:message key="manager" /></a>
        <a class="javascript:void(0)" href="#"><fmt:message key="role" /><fmt:message key="manager" /></a>
        <a class="active" href="#"><fmt:message key="role" /><fmt:message key="resource" /></a>
    </div>
	   <form:form id="inputForm" action="${ctx}/system/role/saveResources" method="post" class="form-horizontal">
	    <input type="hidden" name="roleId" id="roleId" value="${role.id}"/>
			<fieldset>
            <legend><small>系统资源信息</small></legend>
			<div id="demo1" style="width:300px;border: 1px;background-color: #f7f7f9">
			    <ul>
			     <li class="jstree-open"><a href="#">系统资源</a>
			     <ul>
                    <c:forEach items="${menus}" var="menu">
                    <li class="jstree-open"><a href="#">${menu.name}</a>
                        <ul>
                            <c:forEach items="${menu.childrens}" var="child">
                                  <li class="jstree-open"><a href="#">${child.name }</a>
                                    <ul>
                                       <c:forEach items="${child.childrens}" var="childs">
                                       <li class="jstree-leaf"><ins class="jstree-icon">&#160;</ins><a href="#">${childs.name }</a>
                                       </c:forEach>
                                    </ul> 
                                  </li>
                             </c:forEach>
                        </ul>
                    </li>
                    </c:forEach>
                </ul>
			     </li>
			    </ul>
				
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="button" onclick="getCheckbox()" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
			</fieldset>
			</form:form>
</body>
</html>
