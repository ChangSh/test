<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<title>后台管理</title>
<%@ include file="/views/common/taglibs.jsp" %>
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script>
$(function() {
	//加载区划
	$.ajax({
		url : "${ctx}/system/menu/show_menu.do",
		type : "post",
		dataType : "json",
		data : {},
		success : function(data) {
			$("#div_menu").html(data.html);
			renderMenu();
		}
	})
	$(".account").click(function(){  
		$(".submenu").toggle();
	});  
    $(document).mouseup(function(){  
 	    $(".submenu").hide();  
 	});
});
function renderMenu(){
	$(".topnav").accordion({
		accordion:true,
		speed: 500,
		closedSign: '<img src="${ctx}/static/images/list1.png" alt="" />',
		openedSign: '<img src="${ctx}/static/images/list2.png" alt="" />'
	});

	$(".topnav>li>a").click(function(event) {
		$(".topnav>li>ul>li>ul>li>a").removeClass('current3');
		$(".topnav>li>ul>li>a").removeClass('current2');
		$(this).addClass('current').parent().siblings().children().removeClass('current');
	});
	$(".topnav>li>ul>li>a").click(function(event) {
		$(".topnav>li>ul>li>a").removeClass('current2');
		$(this).addClass('current2').parent().siblings().children().removeClass('current2');
	});
	$(".topnav>li>ul>li>ul>li>a").click(function(event) {
		$(".topnav>li>ul>li>ul>li>a").removeClass('current3');
		$(this).addClass('current3').parent().siblings().children().removeClass('current3');
	});
}
// iframe自适应
var browserVersion = window.navigator.userAgent.toUpperCase();
var isOpera = false;
var isFireFox = false;
var isChrome = false;
var isSafari = false;
var isIE = false;
var iframeTime;
isOpera = browserVersion.indexOf("OPERA") > -1 ? true : false;
isFireFox = browserVersion.indexOf("FIREFOX") > -1 ? true : false;
isChrome = browserVersion.indexOf("CHROME") > -1 ? true : false;
isSafari = browserVersion.indexOf("SAFARI") > -1 ? true : false;
if (!!window.ActiveXObject || "ActiveXObject" in window)
	isIE = true;
	
function reinitIframe(){ 
	var winHeight = document.body.offsetHeight-70;
	var iframe = document.getElementById("mainIframe"); 
	try{ 
		var height ;
		if(isIE)
			height = iframe.contentWindow.document.body.scrollHeight+4; 
		if(isFireFox)
			height = iframe.contentWindow.document.body.scrollHeight; 
		if (isChrome == true)
			height = iframe.contentWindow.document.documentElement.scrollHeight;
		
		if (winHeight>height)
			height = winHeight;
		iframe.height = height; 
	}catch (ex){} 
} 
$(function(){
	window.setInterval("reinitIframe()", 500); 	
})						
function go(str){
	$("#mainIframe").attr("src",str);
}
function changePw(){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['600px', '400px'],
	    content: '${ctx}/views/system/user-changePwd.jsp'
	}); 
}
</script>
</head>
<body>
	<div class="f_bg"></div>
	<div id="header">
		<div class="bg"><img src="${ctx}/static/images/bg_01.jpg" alt="" width="100%" height="70" /></div>
		<div class="logo fl">
			<h1><img src="${ctx}/static/images/logo.png" alt="" /></h1>
		</div>
		<div class="fr">
			<div class="user">你好，<shiro:principal property="realname"/>
				<a href="#" class="account" ><img src="${ctx}/static/images/down.png" alt="" /></a>
				<div class="submenu" style="display: none; ">  
					<ul class="root">  
						<li>  
						    <a href="javascript:void(0)" onclick="changePw()" >修改密码</a>  
						</li>  
					    <li>  
					    	<a href="${ctx}/system/user/logout.do" >退出</a>  
					    </li>    
					</ul>  
				</div> 
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<!-- <div id="login">
		<p>你好，管理员&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javascript:;">退出</a></p>
	</div> -->
	<div id="main">
		<div id="sidebar">
			<div class="navbg">
				<img src="${ctx}/static/images/sidebg.jpg" alt="" width="100%"/>
				<div class="container">
			<div class="clearfix">
				<div class="bb-custom-wrapper" id="div_menu">
					
				</div>
			</div>
			
		</div><!-- /container -->
			</div>

		</div>
		<div id="main_content">
		
        <iframe  frameborder="0" name="mainIframe" id="mainIframe" height="660" src="" style="width:100%;margin:0px;padding:0px;"></iframe>		
	
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>