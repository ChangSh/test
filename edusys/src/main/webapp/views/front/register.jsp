<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>


	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  

<script type="text/javascript">window.onerror = function(){return true;}</script>

<script src="${ctx}/static/shopping_files/js/jquery-1.7.2.min.js" type="text/javascript" ></script>

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

<script src="js/cart.js" type="text/javascript" ></script>
</head>
<body>

<div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="profile.php">会员中心</a>
    <ul class="nav">  
	<li class="active"><a href="buycart.jsp" rel="nofollow"><i class=" icon-shopping-cart"></i> 我的购物车(<b>0</b>)</a></li>

	<li ><a href="guestbook.php" rel="nofollow"><i class="icon-comment-alt"></i> 客户留言</a></li> 
	<li><a href="login.php" rel="nofollow">登录/注册</a></li>

 
	</ul>
  </div>
</div> 

	<div class="alert">
	  <button type="button" class="close" data-dismiss="alert">&times;</button>
	  <b>亲！</b> 您的购物车为空，请 <a href="javascript:window.top.location.reload()"><u>去网站</u></a> 选购您所需求的商品或服务！
	</div> 
</body>
</html>
