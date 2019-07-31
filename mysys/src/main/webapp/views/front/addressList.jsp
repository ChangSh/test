<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>

	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  

<script type="text/javascript">window.onerror = function(){return true;}</script>

<script src="${ctx}/static/shopping_files/js/jquery-1.7.2.min.js" type="text/javascript" ></script>

<link type="text/css" rel="stylesheet" href="${ctx}/static/shopping_files/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/static/shopping_files/css/bootstrap-responsive.min.css"/> 
<script src="${ctx}/static/shopping_files/js/bootstrap.min.js"></script>  


<!-- validator start// -->
<link rel="stylesheet" href="${ctx}/static/shopping_files/css/validationEngine.jquery.css" type="text/css" media="screen" title="no title" charset="utf-8" />
<link rel="stylesheet" href="${ctx}/static/shopping_files/css/template.css" type="text/css" media="screen" title="no title" charset="utf-8" />
<script src="${ctx}/static/shopping_files/js/jquery.validationEngine-cn.js" type="text/javascript"></script>
<script src="${ctx}/static/shopping_files/js/jquery.validationEngine.js" type="text/javascript"></script>

<script src="${ctx}/static/shopping_files/js/pcasunzip.js" type="text/javascript" ></script>

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

</head>
<body> 

<div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="profile.php">会员中心</a>
    <ul class="nav">  
	<li ><a href="buycart.jsp" rel="nofollow"><i class=" icon-shopping-cart"></i> 我的购物车(<b>0</b>)</a></li>


	<li ><a href="order.jsp"  rel="nofollow"> <i class="icon-pushpin"></i> 我的订单 </a></li> 
	<li class="active dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">我的账户 <b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li><a href="profile.php"  rel="nofollow"><i class="icon-cog"></i> 个人资料</a></li> 
			<li><a href="addressList.jsp"  rel="nofollow"><i class="icon-map-marker"></i>&nbsp; 收货地址</a></li>
			<li><a href="update_password.php"  rel="nofollow"><i class="icon-key"></i> 更改密码</a></li> 
			
		</ul>
	</li> 

	<li ><a href="guestbook.php" rel="nofollow"><i class="icon-comment-alt"></i> 留言</a></li> 

	
 
	</ul>
  </div>
</div> 
<table width="100%" class="table table-hover"> 
	  <tr> 
		<th>收货人</th>
		<th>省份</th>
		<th>市区</th> 
		<th>区县</th> 
		<th>详细地址</th> 
		<th>邮编</th> 
		<th>固定电话</th> 
		<th>手机号</th> 
		<th>操作</th> 
	  </tr>  
   
	  <tr> 
		<td>5erdfdfd</td>
		<td>天津市</td>
		<td>和平区</td> 
		<td></td> 
		<td>54545sdsdssd</td> 
		<td>300387</td> 
		<td>4545dffd</td> 
		<td></td> 
		<td><a href="do.php?ac=my_address_delete"><img src="${ctx}/static/shopping_files/images/delete.gif"/></a></td> 
	  </tr>  
 
</table>
</body>
</html>
