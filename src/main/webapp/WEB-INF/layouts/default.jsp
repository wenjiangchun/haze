<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link type="image/x-icon" href="${ctx}/resources/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/<spring:theme code='bootstrap-css'/>" rel="stylesheet" media="screen">
<link href="${ctx}/resources/bootstrap/font-awesome/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${ctx}/resources/styles/default.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/resources/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<script src="${ctx}/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
var ctx = "${ctx}";
</script>
<sitemesh:head />
</head>
<body id="grid">
	<div id="main">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span2">
					<!-- jsp:include page="/left" flush="true" /> --> 
					<%@ include file="/WEB-INF/layouts/left.jsp"%>
					</div>
					<div class="span10">
						<sitemesh:body />
					</div>
			</div>
			<%@ include file="/WEB-INF/layouts/footer.jsp"%>
		</div>
	</div>
</body>
</html>