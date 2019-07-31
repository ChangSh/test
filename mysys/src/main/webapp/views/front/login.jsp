<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>



<!DOCTYPE html>
<!-- saved from url=(0041)http://830.v1.iyunti.com/member/login.php -->
<html>

<script type="text/javascript">window.onerror = function(){return true;}</script>

<script src="${ctx}/static/shopping_files/js/jquery-1.7.2.min.js" type="text/javascript"></script>

<link type="text/css" rel="stylesheet" href="${ctx}/static/shopping_files/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="${ctx}/static/shopping_files/css/bootstrap-responsive.min.css"> 
<script src="${ctx}/static/shopping_files/js/bootstrap.min.js"></script>  


<!-- validator start// -->
<link rel="stylesheet" href="${ctx}/static/shopping_files/css/validationEngine.jquery.css" type="text/css" media="screen" title="no title" charset="utf-8">
<link rel="stylesheet" href="${ctx}/static/shopping_files/css/template.css" type="text/css" media="screen" title="no title" charset="utf-8">
<script src="${ctx}/static/shopping_files/js/jquery.validationEngine-cn.js" type="text/javascript"></script>
<script src="${ctx}/static/shopping_files/js/jquery.validationEngine.js" type="text/javascript"></script>

<script src="${ctx}/static/shopping_files/js/pcasunzip.js" type="text/javascript"></script>

<link type="text/css" rel="stylesheet" href="${ctx}/static/shopping_files/css/font-awesome.min.css"> 


<style type="text/css">
body,td,a,p,div,li,input{
	font-family:微软雅黑;
}
.navbar a{
	font-size:13px; 
}
.alert{
	font-size:13px; 
}
td,th{
	font-size:13px; 
}
</style>
<script>
/* $(document).ready(function() {
	$("#login_form").validationEngine();
});
$(document).ready(function() {
	$("#register_form").validationEngine();
}); */
function judge(){
	 $.ajax({
         url:"${ctx}/system/user/checkRepeak-loginname.do",
         type:"post",
         data:{loginname:$("#loginname1").val()},
         success:function(data){
              if(!data){
           	   $("#alert").text("用户名已存在！");
              $("#loginname1").val("");
              }else{
            	  $("#alert").text("");
              }
              
         }
     });     
	
}

function register(){
	var pas = $("#password").val();
	var repas = $("#repassword").val();
	if(pas != repas){
		alert("两次密码不一致");
	}else{
		$.ajax({
			type:"POST",
			url:"${ctx}/system/user/register.do",
			data:$("#register_form").serialize(),
			success:function(data){
				if(data){
	              alert("注册成功，请登录！");
	              $("#loginname1").val("");
	              $("#password").val("");
	              $("#repassword").val("");
	             
	              
	              
				}
			}
		})
		
	}

	
	
}

function login(){
	$.ajax({
        url:"${ctx}/system/user/loginFront.do",
        type:"post",
        dataType:"text",
        data:{loginname:$("#id_username").val(),password:$("#id_password").val(),code:$("#code").val()},
        success:function(data){
             if(data=="loginFail"){
          	   alert('登陆失败');
             }else if(data=="codeFail"){
          	   alert("验证码错误");
        	   
             }else {
            	 parent.location.reload();
             }
        } 
    });     
	
	
}
$(function(){
	if('${message}'!=''){
		$('#message').show();
		//登录失败：用户名或密码输入错误！
		$('#message').append('${message}');
	}
})
function changeImg() {
    var imgSrc = $("#imgObj");
    /* var src = imgSrc.attr("src"); */
   imgSrc.attr("src", "${ctx}/system/Code/Creatcode.do?" + new Date());
}

</script> 
</head>

<body>  
<table width="100%" border="0" cellpadding="5" style="margin-top:30px">
	<tbody><tr>
        <td width="40%" valign="top">
		<form id="login_form" method="post" action="">
          <table width="100%" border="0" cellspacing="5">
            <tbody><tr>
              <td width="20%" align="right"></td>
              <td><h1>用户登录</h1></td>
            </tr>
            <tr>
              <td align="right">用户名：</td>
              <td><input id="id_username" name="loginname" type="text" class="input_text" size="30" placeholder="用户名…"> </td>
            </tr>
            <tr>
              <td align="right">密 码：</td>
              <td><input id="id_password" name="password" type="password" class="validate[required,length[5,25]] input_text" size="30" placeholder="密 码…"> </td>
            </tr>
            <tr>
               <td>验证码：</td>
              <td><input type="text" placeholder="验证码…"  class="validate[required] input_text" id="code" value="" /></td>
            </tr>
            <tr>
            <td></td>
             <td>
				<a href="#" onclick="changeImg()" title="看不清，换一张" ><img id="imgObj" alt="验证码" src="${ctx}/system/Code/Creatcode.do" class="fl mt20 ml20"/></a></td>
            </tr>
            <tr>
              <td height="50" align="right"></td>
              <td><input type="hidden" name="ref" value="">
			  <input type="button" value="登 录" onclick="login()" class="btn btn-large btn-warning"></td>
            </tr>
          </tbody></table>
        </form>
		</td>
        <td> 
		<form id="register_form" method="post" action="">
          <table width="100%" border="0" cellspacing="5" style="border-left:#CCCCCC 1px solid">
            <tbody><tr>
              <td width="15%" align="right"></td>
              <td width="85%"><h1>新用户注册</h1></td>
            </tr>
            <tr>
              <td width="15%" align="right">用户名：</td>
              <td width="85%"><input name="loginname" type="text"  onblur="judge()" class="validate[required,custom[noSpecialCaracters],length[5,25]] input_text" id="loginname1" size="35" placeholder="5个以上字母数字组成…"><span style = "color: Red" id="alert"></span><br>
			  <font color="#999999">建议使用手机号、电子邮件作为账户名称</font>			  </td>
            </tr>
            <tr>
              <td align="right">密 码：</td>
              <td><input id="password" name="password" type="password" class="validate[required,length[5,25]] input_text" size="35" placeholder="5个以上字符组成…">
              <br>
			  <font color="#999999">可由字母、数字、特殊符号组成，长度为5-25个字符</font></td>
            </tr>
            <tr>
              <td align="right">确认密码：</td>
              <td><input id="repassword" name="repassword" type="password" class="validate[required,confirm[password]] input_text" size="35" placeholder="再次输入密码确认…">
              <br>
			  <font color="#999999">请再次输入您设置的密码</font></td>
            </tr>

			<tr>
              <td align="right" height="50"><input id="agreement" name="agreement" type="checkbox" value="1" checked="checked" class="validate[required]"></td>
              <td>我已阅读并同意 会员注册协议 和 隐私保护政策</td>
            </tr>
            <tr>
              <td align="right"></td>
              <td> 
			  <input type="hidden" name="ref" value="">
			  <input type="button" value="注册新用户" class="btn btn-large btn-success" onclick="register()"></td>
            </tr>
          </tbody></table>
        </form>
		</td>
	</tr>
</tbody></table> 


</body>
</html>