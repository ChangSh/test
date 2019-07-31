<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<html lang="zh-CN">
<head>
<title>自动跳转中...</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<script src="${pageContext.request.contextPath}/resources/bdp/js/jquery/jquery.min.js"></script>
<style type="text/css">
body {
  margin: 0;
  padding: 0;
}

.head {
  height: 30px;
  width: 100%;
  background: #f2f2f2;
  padding: 0;
  margin: 0;
}

.logo {
  height: 90px;
  width: 960px;
  margin: 0 auto;
  overflow: hidden;
  clear: both;
}

.logo img {
  height: 90px;
  width: 200px;
  overflow: hidden;
  float: left;
}

.logo div {
  font-size: 24px;
  color: #666;
  height: 40px;
  float: left;
  line-height: 60px;
  margin: 20px 10px;
  padding: 10px;
  border-left: 1px solid #d2d2d2;
}

.login_con {
  width: 960px;
  height: 332px;
  margin: 10px auto;
  clear: both;
}

.login_con_L {
  float: left;
  width: 568px;
  height: 332px;
  overflow: hidden;
}

.login_con_R {
  float: left;
  width: 376px;
  height: 380px;
  border: 1px solid #dce7f4;
}

.login_con_R h4 {
  background: #F2F2F2;
  line-height: 36px;
  width: 376px;
  padding: 0px 6px;
  border: 1px solid #fff;
  border-bottom: 1px solid #d4d4d4;
  margin-top: 0px;
}

.login_con_R  form {
  position: relative;
  padding-top: 10%;
  padding-left: 7%;
  padding-right: 7%;
}

.login_con_R .input-group {
  width: 80%;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 25px !important;
}

.login_footer {
  clear: both;
  margin: 8% auto 0;
  width: 300px;
  color: inherit;
  font-size: 21px;
  font-weight: 200;
  line-height: 2.14286;
}
</style>
<script type="text/javascript">
//goto login , when times out
	String.prototype.trim=function(){
　　    return this.replace(/(^\s*)|(\s*$)/g, "");
　　 }
  function toLoginURL() {
	//单点
	var cookies=document.cookie.split(";");
	if(cookies.length>1){
		var oaToken="";
		for(var i=0;i<cookies.length;i++){
			var v=cookies[i].split("=")[0].trim();
			if(v=="oa_token"){
				oaToken=cookies[i].split("=")[1];
			}
		}
		if(oaToken!=""){
			document.getElementById("loginBtn").click();
		}else{
			window.parent.location.href = "login.jsp";
		}
	}else{
		window.parent.location.href = "login.jsp";
	}
/* 	//end
    if (window.parent.frames && window.parent.frames[0]&& window.parent.frames[0].name == "contentIframe") {
      window.parent.location.href = "login.jsp";
    } */
  }
</script>
</head>
<%
  response.setHeader("login", "login");
%>
<body style="display:none;" onload="toLoginURL();">
  <div class="head login"></div>
  <div class="logo">
    <div>基础开发平台</div>
  </div>
  <div class="login_con">
    <div class="login_con_L">
      <img src="" />
    </div>
    <div class="login_con_R">
      <h4>登录</h4>
      
      <form  id="loginFormId" action="${pageContext.request.contextPath}/loginCheck.do"  class="form-horizontal" method="post">
        <input type="hidden" class="form-control" name="random" id="random" />
        <input type="hidden" class="form-control" name="uuid" id="uuid" />
        <input type="hidden" class="form-control" name="ticket" id="ticket" />
        <div class="form-group input-group">
          用户名： <input  type="text" class="form-control" name="username" id="j_username" class="form-control"/>
        </div>
        <div class="form-group input-group">
          密&nbsp;&nbsp;码：&nbsp;<input type="password" name="password" id="j_password" class="form-control"/>
       &nbsp;&nbsp;<div id="errMsg" style="color:red;" role="alert">${sessionScope.Msg}</div>
        </div>
        <div class="form-group input-group"  >
          <input  class="btn btn-primary btn-block" type="submit" id="loginBtn" value="登录"/>
        </div>
      </form>
    </div>
  </div>
  <div class="login_footer">Fang.com 版权信息 2015</div>
</body>
</html>
