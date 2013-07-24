<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><fmt:message key="config" /><fmt:message key="manager" /></title>
<link href="${ctx}/resources/datatable/css/jquery.dataTables.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="${ctx}/resources/datatable/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${ctx}/resources/datatable/hazeTable.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
			$("#delete_btn").click(function() {
				$("#delete_btn").alert();
				return;
				var ids = new Array();
				$("input[name=config_id_checkbox]").each(function() {
					if (this.checked) {
						ids.push($(this).val());
					}
				});
				window.location.href = "${ctx}/system/config/delete/" + ids;
			});
			$("#add_btn").click(function() {
				window.location.href = "${ctx}/system/config/add";
			});

			$(".updateConfig").click(function() {
				var id = $(this).attr("config_id");
				var description = $(this).attr("config_description");
				var value = $(this).attr("config_value");
				var name = $(this).attr("config_name");
				$("#config_description_p").text(description);
				$("#config_name_p").text(name);
				$("#config_value_input").val(value);
				$("#config_id_input").val(id);
				$('#editConfigModal').modal("show");
			});

			$("#saveBtn").click(function() {
				var id = $("#config_id_input").val();
				var value = $("#config_value_input").val();
				var data = {};
				data.id = id;
				data.value = value;
				$.ajax({
							type : "GET",
							contentType : 'application/json',
							url : "${ctx}/system/config/updateConfigValue",
							dataType : "json",
							data: data,
							success : function(data) {
								$('#editConfigModal').modal("hide");
								window.location.reload();
							}
						});
			});
		});
	
</script>
</head>

<body>
	<div class="nav1">
		<a class="tip-bottom" title="" href="${ctx}"
			data-original-title="Go to Home"> <i class="icon-home"></i> <fmt:message key="home" />
		</a>
	   <a href="javascript:void(0)"><fmt:message key="system" /><fmt:message key="manager" /></a>
	   <a class="active" href="#"><fmt:message key="config" /><fmt:message key="manager" /></a>
	</div>
	<c:if test="${not empty hazeMessage}">
		<div id="message"
			class="alert alert-${hazeMessage.alertType.messageValue}">
			<button data-dismiss="alert" class="close">×</button>
			<fmt:message key="${hazeMessage.message}" />
		</div>
	</c:if>
	<div class="box">
		<div class="box-header">
			<h2>
				<a><fmt:message key="config" /><fmt:message key="list" /></a>
			</h2>
			<div class="box-icon">
			</div>
		</div>
		<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
                    <thead>
                        <tr>
                        <th><fmt:message key="num" /></th>
                        <th><fmt:message key="config.name" /></th>
                        <th><fmt:message key="config.configName" /></th>
                        <th><fmt:message key="config.value" /></th>
                        <th><fmt:message key="config.configType" /></th>
                        <th><fmt:message key="operate" /></th>
                        </tr>
                   </thead>
                   <tbody>
                        <c:forEach items="${configs}" var="config">
                          <tr>
                             <td align="center"> ${config.id} </td>
                             <td> ${config.name} </td>
                             <td> ${config.configName} </td>
                             <td> ${config.value} </td>
                             <td> ${config.configType.typeName} </td>
                             <td>
                                  <a href="javascript:void(0)" config_id="${config.id}" config_value="${config.value}" config_description="${config.description}" config_name="${config.name}" class="updateConfig"><fmt:message key="edit" /></a>
                               <c:if test="${config.configType.name() == 'B'}">
                                  <a href="javascript:void(0)" config_id="${config.id}" class="deleteConfig"><fmt:message key="delete" /></a>
                               </c:if>
                             </td>
                          </tr>
                        </c:forEach>
                   </tbody>
         </table>
	</div>
	<button type="button" class="btn btn-primary" id="add_btn">
		<i class="icon-plus-sign icon-white"></i>
		<fmt:message key="add" /><fmt:message key="config" />
	</button>
	<div tabindex="-1" class="modal hide fade" id="editConfigModal"
		style="display: none;">
		<div class="modal-header">
			<button data-dismiss="modal" class="close" type="button">×</button>
			<h4 id="config_name_p"><fmt:message key="edit" /><fmt:message key="config" /><fmt:message key="info" /></h4>
		</div>
		<div class="modal-body">
			<pre id="config_description_p">
			</pre>
			    <div class="input-prepend input-append">
				    <input type="text" name="" id="config_value_input" class="input-xlarge" value="" /> 
				    <input type="hidden" name="" id="config_id_input" value="" />
				</div>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" id="saveBtn"><fmt:message key="save" /></button>
		</div>
	</div>
</body>
</html>
