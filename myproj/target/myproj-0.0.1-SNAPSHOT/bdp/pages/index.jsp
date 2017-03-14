<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title> <spring:message code="project.name" /></title>
<%
  String contextPath = (String) request.getContextPath();
%>
<script type="text/javascript">
	var contextPath = "<%=contextPath%>";
	var zNodes = ${treeNodes};
</script>
<script src="<%=contextPath%>/resources/bdp/assets/plugins/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="<%=contextPath%>/resources/bdp/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="<%=contextPath%>/resources/bdp/assets/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js"
  type="text/javascript"></script>
<script src="<%=contextPath%>/resources/bdp/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script
  src="<%=contextPath%>/resources/bdp/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js"
  type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="<%=contextPath%>/resources/bdp/assets/plugins/excanvas.min.js"></script>
<script src="<%=contextPath%>/resources/bdp/assets/plugins/respond.min.js"></script>  
<![endif]-->
<script src="<%=contextPath%>/resources/bdp/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
  type="text/javascript"></script>
<script src="<%=contextPath%>/resources/bdp/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="<%=contextPath%>/resources/bdp/assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
<script src="<%=contextPath%>/resources/bdp/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<link href="<%=contextPath%>/resources/bdp/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"
  type="text/css" />
<link href="<%=contextPath%>/resources/bdp/assets/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
  type="text/css" />
<link href="<%=contextPath%>/resources/bdp/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"
  type="text/css" />
<link href="<%=contextPath%>/resources/bdp/assets/css/style-metro.css" rel="stylesheet" type="text/css" />
<link href="<%=contextPath%>/resources/bdp/assets/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=contextPath%>/resources/bdp/assets/css/style-responsive.css" rel="stylesheet" type="text/css" />
<link href="<%=contextPath%>/resources/bdp/assets/css/themes/default.css" rel="stylesheet" type="text/css"
  id="style_color" />
<link href="<%=contextPath%>/resources/bdp/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet"
  type="text/css" />
<link rel="shortcut icon" href="<%=contextPath%>/resources/bdp/favicon.ico" />
<link href="<%=contextPath%>/resources/bdp/css/index.css" rel="stylesheet">
<link href="<%=contextPath%>/resources/bdp/css/TabPanel.css" rel="stylesheet" type="text/css"/>
</head>
<body class="page-header-fixed" onload="showHomePage();">
  <div id="wrapper">
    <div class="header navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="brand" href="#"> <img src="<%=contextPath%>/resources/bdp/assets/img/logo.png" alt="logo" />
          <spring:message code="project.name" />
          </a> <a href="javascript:;" class="btn-navbar collapsed text-center" data-toggle="collapse"
            data-target=".nav-collapse"> <img src="<%=contextPath%>/resources/bdp/assets/img/menu-toggler.png"
            alt="" />
          </a>
          
          <ul class="nav pull-right">
            <li id="daojishi" style="color:white;margin-top:12px;"></li>
            <li class="dropdown user"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
              data-hover="dropdown" data-close-others="true"><i class="icon-user"></i><span class="username">您好:${sessionScope.Plan_RealName}</span> </a></li>
     <!--        <li class="dropdown user" id="pagetj"><a class="dropdown-toggle" data-toggle="dropdown" href="#"
              id="loadingInfoLink" data-hover="dropdown" data-close-others="true"><i class="icon-bar-chart"></i><span
                class="username">页面统计</span><i class="icon-angle-down"></i> </a>
              <ul id="pageLoadingInfo" class="dropdown-menu">
                <li><a href="#">
                    <div>
                      <strong>点击左侧菜单，此处显示页面加载信息。。。</strong>
                    </div>
                </a></li>
                <li class="divider"></li>
              </ul></li> -->
         <!--    <li class="dropdown user"><a id="dropdown" class="dropdown-toggle" data-toggle="dropdown"
              data-hover="dropdown" href="#" data-close-others="true"> <i class="icon-bullhorn"></i><span
                class="username">公告 </span><i class="icon-angle-down"></i></a> 用户的未读消息
              <ul id="ul" class="dropdown-menu">
                                        <li><a class="text-center" href="message/message.do" target="_blank"> <strong>更多</strong> <i class="fa fa-angle-right"></i></a></li>
                <li id="more"><a class="text-center" href="#" onclick="addTab(this.text,'message/main.do')"><strong>更多</strong>
                    <i class="icon-hand-right"></i></a></li>
                <li class="divider"></li>
              </ul></li> -->
            <li class="dropdown user"><a class="dropdown-toggle" data-toggle="dropdown" href="#"
              data-hover="dropdown" data-close-others="true"> <i class="icon-cog"></i> <i class="icon-angle-down"></i></a>
              <ul class="dropdown-menu dropdown-user">
                <!-- <li><a href="#" data-backdrop="static" data-toggle="modal" data-target="#modifyPass"><i
                    class="icon-lock"></i> 锁定</a></li> -->
                <li><a href="#" onclick="logout();"><i class="icon-signout"></i> 退出</a></li>
              </ul></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="page-container row-fluid">
      <div class="page-sidebar nav-collapse collapse">
        <ul class="page-sidebar-menu">
          <li><div class="sidebar-toggler hidden-phone"></div></li>
        </ul>
        
        <ul id="side-menu" class="page-sidebar-menu"></ul>
      </div>
    </div>
  </div>
  <div id="divFullScreen" class="slfullscreenbutton" title="最大" style="position: absolute; right: 15px; top: 350px;"></div>
  <div class="page-content">
    <div id="portlet-config" class="modal hide">
      <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>Widget Settings</h3>
      </div>
      <div class="modal-body">Widget settings form goes here</div>
    </div>
    <div class="">
      <div class="row-fluid">
        <div class="span12">
          <div id="tabs" style="min-height: 760px;">
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="modal hide fade" id="modifyPass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
    aria-hidden="true">
    <!-- <div class="modal-dialog" style="width: 507px;">
      <div class="modal-content"> -->
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
      <h4 class="modal-title" id="modifyPassTitle"></h4>
      修改密码
    </div>
    <div class="modal-body">
      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" bordercolor="#666666">
        <tr>
          <td width="150" align="right">原密码：</td>
          <td><input type="password" id="currentPass" /></td>
          <td><div id="c_errorMsg"></td>
        </tr>
        <tr>
          <td width="150" align="right">新密码：</td>
          <td><input type="password" id="newPass" /></td>
          <td><div id="n_errorMsg"></td>
        </tr>
        <tr>
          <td align="right">确认密码：</td>
          <td><input type="password" id="surePass" /></td>
          <td><div id="s_errorMsg"></td>
        </tr>
      </table>
    </div>
    <div class="modal-footer">
      <button type="button" id="btn" class="btn btn-primary">确定</button>
      <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
    </div>
  </div>
  <!-- </div>
  </div> -->

  <div class="modal hide fade" id="readMessage" tabindex="-1" role="dialog" aria-labelledby="message_ModalTitle"
    aria-hidden="true">
    <!-- <div class="modal-dialog">
      <div class="modal-content"> -->
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
      <h4 class="modal-title" id="ti" align="center"></h4>
    </div>
    <div class="modal-body">
      <div style="width: 100%; height: 300px; overflow-y: auto; overflow-x: hidden" autosized="true" id="content">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
  <!--  </div>
  </div> -->
  <div class="footer">
    <div class="footer-inner">Copyright &copy; 2014 Soufun Holdings Limited, All Rights Reserved 北京搜房科技发展有限公司 版权所有</div>
    <div class="footer-tools">
      <span class="go-top"><i class="icon-angle-up"></i></span>
    </div>
  </div>
  <script src="<%=contextPath%>/resources/bdp/assets/scripts/app.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/resources/bdp/js/bdp/index.js"></script>
  <script src="<%=contextPath%>/resources/bdp/js/bdp/Fader.js" type="text/javascript"></script>
  <script src="<%=contextPath%>/resources/bdp/js/bdp/TabPanel.js" type="text/javascript"></script>
  <script src="<%=contextPath%>/resources/bdp/js/bdp/Math.uuid.js" type="text/javascript"></script>
  <script>
  			var runTime = 0;
			jQuery(document).ready(function() {
				$.post(contextPath+"/getTimeOut.do",null,function(result){
					if(result.hasErrors) {
						alert(result.errorMessage);
					}else {
						App.init(); // initlayout and core plugins
						runTime = result.data/1000;
                        GetRTime();
					}
				});
			});
			
			function GetRTime(){
			    $("#daojishi").empty();
			    var nH = parseInt(runTime/3600);
			    var nM = 0;
			    var nS = 0;
				if(nH!=0){
					nM = (runTime%3600)/60;
					nS = (runTime%3600)%60;
				}else{
					nM = runTime/60;
					nS = runTime%60;
				}
				$("#daojishi").html((parseInt(nH)!=0?parseInt(nH)+":":"")+parseInt(nM)+":"+nS);
			    runTime--;
			    if (runTime==0) {
			    	var currentDate = new Date();
			    	$("#daojishi").empty();
			    	$("#daojishi").html("已超时："+currentDate.toLocaleString());
			    }else {
			    	setTimeout("GetRTime()",1000);
			    }
			}
			
		</script>
</body>
</html>
