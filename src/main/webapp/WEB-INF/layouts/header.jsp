<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container-fluid">
      <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
      <a href="${ctx}" class="brand">河南省公安厅12110短信报警平台</a>
      <div class="btn-group pull-right">
      <shiro:guest><a href="${ctx}/login" class="btn btn-link"><fmt:message key="login"/></a></shiro:guest>
      <shiro:user>
        <a href="#" data-toggle="dropdown" class="btn dropdown-toggle">
          <i class="icon-user"></i> <shiro:principal property="name"/><input type="hidden" name="shiroUserId" id="shiroUserId" value="<shiro:principal property="userId"/>"/>
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
          <li><a href="#updatePasswordDiv" data-toggle="modal" role="button"><i class="icon-lock"></i> <fmt:message key="change.password"/></a></li>
          <li class="divider"></li>
          <li><a href="${ctx}/logout"><i class="icon-off"></i><fmt:message key="logout"/></a></li>
        </ul>
        </shiro:user>
      </div>
      <div class="nav-collapse">
        <ul class="nav">
          <li class="dropdown">
              <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                    <fmt:message key="message"/> <span class="badge badge-info">100</span>
                    <b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
                  <li><a href="#">Message 1</a></li>
                  <li class="divider"></li>
                  <li><a href="#">Older messages...</a></li>
              </ul>
          </li>
          <li class="dropdown">
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                      <fmt:message key="settings"/>
                      <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">Personal Info</a></li>
                </ul>
          </li>
          <li class="dropdown">
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                      <fmt:message key="theme"/>
                      <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)" class="theme-switch-default" onclick="changeTheme('default')">Default</a></li>
                    <li><a href="javascript:void(0)" class="theme-switch-amelia" onclick="changeTheme('amelia')">Amelia</a></li>
                    <li><a href="javascript:void(0)" class="theme-switch-amelia" onclick="changeTheme('cerulean')">Cerulean</a></li>
                    <li><a href="javascript:void(0)" class="theme-switch-amelia" onclick="changeTheme('cosmo')">Cosmo</a></li>
                    <li><a href="javascript:void(0)" class="theme-switch-amelia" onclick="changeTheme('slate')">Slate</a></li>
                    <li><a href="javascript:void(0)" class="theme-switch-amelia" onclick="changeTheme('united')">United</a></li>
                    <li><a href="javascript:void(0)" class="theme-switch-amelia" onclick="changeTheme('spruce')">spruce</a></li>
                    <li><a href="javascript:void(0)" class="theme-switch-amelia" onclick="changeTheme('simplex')">simplex</a></li>
                    <li><a href="javascript:void(0)" class="theme-switch-amelia" onclick="changeTheme('cyborg')">cyborg</a></li>
                </ul>
          </li>
           <li class="dropdown">
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                           <fmt:message key="language"/>
                      <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)" class="theme-switch-default" onclick="changeLanguage('zh_CN')">中文</a></li>
                    <li><a href="javascript:void(0)" class="theme-switch-amelia" onclick="changeLanguage('en_US')">English</a></li>
                </ul>
          </li>
          <li><a href="https://github.com/wenjiangchun/haze/wiki"><fmt:message key="help"/></a></li>
        </ul>
      </div>
    </div>
  </div>
  
</div> 
	
<div tabindex="-1" class="modal hide fade" id="updatePasswordDiv" style="display: none;">
        <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button">×</button>
            <h3 id="myModalLabel">修改密码</h3>
        </div>
        <div class="modal-body">
            <div class="control-group">
                <label for="password" class="control-label">请输入新密码：</label>
                <div class="controls">
                    <input type="password" id="password" name="password" class="input-large required"/>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" onclick="updatePassword()"><fmt:message key="save"/></button>
        </div>
    </div>
<script type="text/javascript">
	  function changeLanguage(language) {
		  $.ajax({
			  type: "POST",
			  url: ctx + "/changeLanguage",
			  data: "language="+language,
			  dataType:"text",
			  async: true,
			  error: function(data, error) {alert("change lang error!");},
			  success: function(data){
			     window.location.reload();
			  }
			 });
	  }
  
	  function changeTheme(theme) {
		  $.ajax({
			  type: "POST",
			  url: ctx + "/changeTheme",
			  data: "theme="+theme,
			  dataType:"text",
			  async: true,
			  error: function(data, error) {alert("change lang error!");},
			  success: function(data){
			     window.location.reload();
			  }
			 });
	  }
      function updatePassword() {
       	   var userId = $("#shiroUserId").val();
       	   var password = $("#password").val();
       	   $.ajax({
       		   type:'POST',
       		   url : ctx + "/system/user/updatePassword",
       		   dataType:'json',
       		   data:{"id":userId,"password":password},
       		   success:function(data) {
       			   alert("密码修改成功");
       			   $("#updatePasswordDiv").modal('hide');
       		   }
       	   });
       }
</script>