<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title><fmt:message key="group"/><fmt:message key="manager"/></title>
	<link href="${ctx}/resources/datatable/css/jquery.dataTables.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/resources/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/resources/datatable/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${ctx}/resources/datatable/hazeTable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/zTree/ztree.js"></script>
	<script type="text/javascript">
	    var dataTable ;
		$(document).ready(function() {
			$("#parent_ID").val(""); //初始化父组织机构节点
			initGroupTree(); //初始化机构树
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
			$("#add_btn").click(function() { //添加组织机构按钮事件
				window.location.href="${ctx}/system/group/add";
			});
		});
		
		function initGroupTree() {
		  $.ajax({
			  method : "post",
			  url : "${ctx}/system/group/getTopGroups",
			  dataType : "json",
			  success : function(data) {
				  var setting = {data:{
					  simpleData:{
						  enable:true,
						  idKey:"id",
						  pIdKey:"pid",
						  rootPId:null
					  }
				  }, callback: {
					  onClick:onClick
				  }};
				  $.fn.zTree.init($("#groupTree"), setting, data);
			  }
		  });	
		
		}
		
		function onClick(event, treeId, treeNode, clickFlag) {
			$("#parent_ID").val(treeNode.id);
			dataTable.fnDraw();
		}	
		
		/**
		 * 组织机构列表操作按钮对应事件
		 * id 为组织机构ID
		 * operator 操作类型 如"edit","delete"等
		 */
		function operatorGroup(id,operator) {
			if (id != null) {
				window.location.href="${ctx}/system/group/"+operator+"/"+id;
			}
		}
		
		/**
		 * 初始化用户分页列表
		 */
		function initDataTable(groupId) {
			var options = {
                    divId:"contentTable",
                    url : "${ctx}/system/group/search",
                    columns:[
                    	 {
                    		 "sName": "id",
                             "bSortable": false,
                             "bSearchable":false,
                             "sWidth":40
                         },
                         {
                             "sName": "name",
                             "bSortable": true,
                             "sWidth":80
                         },
                         {
                             "sName": "operator",
                             "bSearchable":false,
                             "bSortable": false,
                             "sWidth":120
                         }
                   ]
            };
			dataTable = createTable(options);
		}
	</script>
</head>

<body>
	<ul class="breadcrumb">
        <li><a href="${ctx}"><i class="icon-home"></i> <fmt:message key="home"/></a><span class="divider">/</span></li>
        <li><a href="javascript:void(0)"><fmt:message key="system" /><fmt:message key="manager" /></a><span class="divider">/</span></li>
        <li class="active"><fmt:message key="group" /><fmt:message key="manager" /></li>
    </ul>
	<c:if test="${not empty message}">
        <div id="message" class="alert alert-${message.alertType.messageValue}">
            <button data-dismiss="alert" class="close">×</button>
            <fmt:message key="${message.content}" />
        </div>
    </c:if>
    <input type="hidden" class="databatle_query" name="parent" id="parent_ID" value=""/>
    <div class="row-fluid">
	    <div class="span2 leftTree" style="border: 1px;border-width: ">
		     <ul id="groupTree" class="ztree"></ul>
		</div>
		<div class="span8">
			<div data-original-title="" class="box-header">
				<h2>
					<a><fmt:message key="group"/><fmt:message key="list" /></a>
				</h2>
				<div class="box-icon">
				<button type="button" class="btn btn-link" id="add_btn"><fmt:message key="add"/><fmt:message key="group"/></button>
				</div>
			</div>
					<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
					    <thead>
							<tr>
							<th><fmt:message key="num"/></th>
							<th><fmt:message key="group.groupName" /></th>
							<th><fmt:message key="operate" /></th>
							</tr>
							</thead>
					</table>
		</div>
	</div>
</body>
</html>
