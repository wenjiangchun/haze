<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title><fmt:message key="user"/><fmt:message key="manager"/></title>
	<link href="${ctx}/resources/datatable/css/jquery.dataTables.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/resources/datatable/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${ctx}/resources/datatable/hazeTable.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			initDataTable(); //初始化用户分页列表
			$("#delete_btn").click(function() { //批量删除用户按钮事件
				var ids = new Array();
				$("input[name=user_id_checkbox]").each(function() {
					if (this.checked) {
						ids.push($(this).val());
					}
				});
				window.location.href="${ctx}/system/user/delete/" + ids ;
			});
			$("#add_btn").click(function() { //添加用户按钮事件
				window.location.href="${ctx}/system/user/add";
			});
		});
		
		/**
		 * 用户列表操作按钮对应事件
		 * id 为用户ID
		 * operator 操作类型 如"edit","delete"等
		 */
		function operatorUser(id,operator) {
			if (id != null) {
				window.location.href="${ctx}/system/user/"+operator+"/"+id;
			}
		}
		
		/**
		 * 初始化用户分页列表
		 */
		function initDataTable() {
			var options = {
                    divId:"contentTable",
                    url : "${ctx}/system/user/search",
                    columns:[
                    	 {
                    		 "sName": "id",
                             "bSortable": false,
                             "bSearchable":false,
                             "sWidth":40
                         },
                         {
                             "sName": "loginName",
                             "bSortable": true,
                             "sWidth":80
                         },
                         {
                             "sName": "name",
                             "bSortable": true     
                         },
                         {
                             "sName": "email",
                             "bSortable": false
                         },
                         {
                             "sName": "role.name",
                             "bSearchable":false,
                             "bSortable": false,
                             "sWidth":250
                         },
                         {
                             "sName": "sex",
                             "bSearchable":false,
                             "bSortable": false
                         },
                         {
                             "sName": "status",
                             "bSearchable":false,
                             "bSortable": false
                         },
                         {
                             "sName": "operator",
                             "bSearchable":false,
                             "bSortable": false,
                             "sWidth":120
                         }
                   ]
            };
			createTable(options);
		}
	</script>
</head>

<body>
	<ul class="breadcrumb">
        <li><a href="${ctx}"><i class="icon-home"></i> <fmt:message key="home"/></a><span class="divider">/</span></li>
        <li><a href="javascript:void(0)"><fmt:message key="system" /><fmt:message key="manager" /></a><span class="divider">/</span></li>
        <li class="active"><fmt:message key="user" /><fmt:message key="manager" /></li>
    </ul>
	<c:if test="${not empty message}">
        <div id="message" class="alert alert-${message.alertType.messageValue}">
            <button data-dismiss="alert" class="close">×</button>
            <fmt:message key="${message.content}" />
        </div>
    </c:if>
	<div class="box">
		<div data-original-title="" class="box-header">
			<h2>
				<i class="icon-user"></i><span class="break"></span><a><fmt:message key="user"/><fmt:message key="list" /></a>
			</h2>
			<div class="box-icon">
			</div>
		</div>
				<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
				    <thead>
						<tr>
						<th><fmt:message key="num"/></th>
						<th><fmt:message key="user.loginName" /></th>
						<th><fmt:message key="user.name" /></th>
						<th><fmt:message key="user.email" /></th>
						<th><fmt:message key="role" /></th>
						<th><fmt:message key="user.sex" /></th>
						<th><fmt:message key="status" /></th>
						<th><fmt:message key="operate" /></th>
						</tr>
						</thead>
				</table>
	</div>
	<button type="button" class="btn btn-primary" id="add_btn"><i class="icon-user icon-white"></i><fmt:message key="add"/><fmt:message key="user"/></button>
	<button type="button" class="btn" id="delete_btn" style="display:none"><fmt:message key="delete"/><fmt:message key="user"/></button>
</body>
</html>
