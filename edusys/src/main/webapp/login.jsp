<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html >
<html>
<head>
	<link rel="shortcut icon" href="${ctx}/static/favicon.ico" /> 
    <script src="${ctx}/static/jquery-1.9.0.min.js"></script> 
    <script src="${ctx}/static/free.form.js"></script> 
    <link href="${ctx}/static/css/login.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/static/free.dialog.js"></script>
	<title>登录</title>
<script type="text/javascript">
		// 跳出框架
	    if( window.top != window.self ){
	    	window.top.location.href='login.jsp';
	     }
		// 回车自动提交
	    document.onkeydown = function (e) { 
	    	var theEvent = window.event || e; 
	    	var code = theEvent.keyCode || theEvent.which; 
	    	if (code == 13) { 
	    		$('#loginBtn').click();
	    	} 
	    } 
		$(function(){
			$("#loginname").focus();//默认焦点
			$("#loginname").val(getCookie("loginname"));
			$("#password").val(getCookie("password"));
			$('.login_but').click(function(){
				if($('form').validate()){
					if($("#remberPwd").prop("checked")==true){//jquery1.9新方法
						setCookie("loginname",$("#loginname").val());
						setCookie("password",$("#password").val());
					}
					$('form').submit();
				}
			});
			if('${message}'!=''){
				$('#message').show();
				//登录失败：用户名或密码输入错误！
				$('#message').append('${message}');
			}
		})
		//写cookies函数
		//两个参数，一个是cookie的名称，一个是值
		function setCookie(name,value)
		{
		    var Days = 30; //此 cookie 将被保存 30 天
		    var exp  = new Date();    //new Date("December 31, 9998");
		    exp.setTime(exp.getTime() + Days*24*60*60*1000);
		    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
		}
		 
		//读取cookies函数
		function getCookie(name)
		{
		    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
		    if(arr != null) return unescape(arr[2]); return null;
		}
		function vaild(){
			if(!$("#code").val()){
				alert("验证码不能为空");
				return false;
			}
			if($("#loginname").val()&&$("#password").val()){
				 $.ajax({
			          url:"${ctx}/system/user/login.do",
			          type:"post",
			          dataType:"text",
			          data:{loginname:$("#loginname").val(),password:$("#password").val(),code:$("#code").val()},
			          success:function(data){
			               if(data=="loginFail"){
			            	   alert("登录失败：无权限！");
			               }else if(data=="codeFail"){
			            	   alert("验证码错误");
			            	   
			               }else if(data=="error"){
			            	   
			            	   alert("账号或密码错误！");
			               }else{
			            	   location.href="${ctx}/main.jsp";
			               }
			          } 
		          });     
			               
			}else{
				alert("用户名密码不能为空");
			}
			
		}
		function changeImg() {
		    var imgSrc = $("#imgObj");
		    /* var src = imgSrc.attr("src"); */
		   imgSrc.attr("src", "${ctx}/system/Code/Creatcode.do?" + new Date());
		}
    </script>	
</head>
<body>
<form action="${ctx}/system/user/login.do" method="post" id="form1">
	<div class="logo">
		<h1><img src="${ctx}/static/images/login_logo.jpg" alt="" /></h1>
	</div>
		<div class="login_box">
			<div class="l_top">
				<p class="lt fl"></p>
				<p class="m fl"></p>
				<p class="rt fl"></p>
				<div class="clear"></div>
			</div>
			<div class="l_m"></div>
			<div class="l_down">
				<p class="ld fl"></p>
				<p class="m fl"></p>
				<p class="rd fl"></p>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
			<div class="login_content">
				<div class="z_t">
				<p class="zs fl"></p>
				<p class="zm fl"></p>
				<p class="ys fl"></p>
				<div class="clear"></div>
			</div>
			<div class="z_m"></div>
			<div class="l_down">
				<p class="zx fl"></p>
				<p class="zm fl"></p>
				<p class="yx fl"></p>
				<div class="clear"></div>
			</div>			
			</div>
			<div class="lg">
				<ul class="lg_tit">
					<li><a href="javascript:;">登&nbsp;&nbsp;&nbsp;&nbsp;录</a></li>
				</ul>
			<div class="thelogin">
				<div class="mt20"><p class="fl inpl"></p><input type="text" name="loginname" id="loginname" validate="{required:true}" value="请输入用户名" class="user def_input fl"/><p class="inpr"></p>
				<div class="clear"></div></div>
				<div class="password mt20"><p class="fl inpl"></p><input type="password" name="password" id="password" value="请输入密码" validate="{required:true}" class="psw def_input fl"/><p class="inpr"></p>
				<div class="clear"></div>
				<input type="text" class="def_input mt20 fl" id="code" value="" style="width:170px;"/><a href="#" onclick="changeImg()" title="看不清，换一张" ><img id="imgObj" alt="验证码" src="${ctx}/system/Code/Creatcode.do" class="fl mt20 ml20"/></a><div class="clear"></div>
				<div class="checkbox mt20" ><p style="color:red">${error}</p></div>
			</div>
			</div>
			<input type="button" value="登录" class="login_btn" onclick="vaild()" id="loginBtn" />
		</div>
	</div>
	<!-- login_box结束 -->
	<div id="bottom"><p>Copyright © 2016-2017 毕业设计管理系统 版权所有${message}</p></div>
	<div class="screenbg">
		<ul>
			<li><img src="${ctx}/static/images/bg.jpg" alt="" width="100%" /></li>
		</ul>
	</div>
	
</form>
</body>
</html>