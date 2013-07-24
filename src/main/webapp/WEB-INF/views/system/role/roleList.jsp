<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title><fmt:message key="role"/><fmt:message key="manager"/></title>
	<link href="${ctx}/resources/datatable/css/jquery.dataTables.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="${ctx}/resources/datatable/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${ctx}/resources/datatable/hazeTable.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			initDataTable();
			$("#delete_btn").click(function() {
				var ids = new Array();
				$("input[name=role_id_checkbox]").each(function() {
					if (this.checked) {
						ids.push($(this).val());
					}
				});
				window.location.href="${ctx}/system/role/delete/" + ids ;
			});
			$("#add_btn").click(function() {
				window.location.href="${ctx}/system/role/add";
			});
		});
		
		function operatorRole(id,operator) {
            if (id != null) {
                window.location.href="${ctx}/system/role/"+operator+"/"+id;
            }
        }
		
		function initDataTable() {
            var options = {
                    divId:"contentTable",
                    url : "${ctx}/system/role/search",
                    columns:[
                         {
                             "sName": "id",
                             "bSortable": false,
                             "sWidth":40
                         },
                         {
                             "sName": "name",
                             "bSortable": true,
                             "sWidth":200
                         },
                         {
                             "sName": "roleName",
                             "bSortable": true     
                         },
                         {
                             "sName": "status",
                             "bSortable": false,
                             "sWidth":50
                         },
                         {
                             "sName": "operator",
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
        <li class="active"><fmt:message key="role" /><fmt:message key="manager" /></li>
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
                <i class="icon-tag"></i><span class="break"></span><a><fmt:message key="role"/><fmt:message key="list"/></a>
            </h2>
            <div class="box-icon">
            </div>
        </div>
                <table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
                    <thead>
                        <tr>
                        <th><fmt:message key="num"/></th>
			            <th><fmt:message key="role.name"/></th>
			            <th><fmt:message key="role.roleName"/></th>
			            <th><fmt:message key="status"/></th>
			            <th><fmt:message key="operate"/></th>
                        </tr>
                        </thead>
                </table>
    </div>
	<button type="button" class="btn btn-primary" id="add_btn"><i class="icon-tag icon-white"></i><fmt:message key="add"/><fmt:message key="role"/></button>
	<button type="button" class="btn" id="delete_btn" style="display:none"><fmt:message key="delete"/><fmt:message key="role"/></button>
</body>
</html>
