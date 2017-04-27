<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
	


	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  

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

<script>
$(document).ready(function() {
	$("#myform").validationEngine();
});
</script> 
</head>

<body>

<div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="#">会员中心</a>
    <ul class="nav">  
<li ><a href="house.jsp" rel="nofollow"><i class=" icon-shopping-cart"></i> 我的房源</a></li>
	 <li ><a href="order.jsp" rel="nofollow"><i class=" icon-shopping-cart"></i> 我的预定</a></li>
		<li class=" dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">预定详情 <b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li class="active"><a href="#" rel="nofollow"><i class="icon-cog"></i> 个人资料</a></li> 
			
			<li><a href="update_password.jsp"  rel="nofollow"><i class="icon-key"></i> 更改密码</a></li> 
			
		</ul>
	</li> 

	<li ><a href="${ctx}/views/front/message.jsp" ><i class="icon-comment-alt"></i> 留言</a></li> 

	</ul>
  </div>
</div> 

<form id="myform" method="post" action="do.php?ac=profile"> 
  <table width="100%" >
	<tr>
		<td width="25%" > </td>
	    <td><h3>个人资料 </h3></td>
	</tr>
	<tr>
		<td align="right"><font color="red">*</font> 真实姓名：</td>
	    <td><input id="username" name="username" type="text" size="35"  class="validate[required, length[2,10]] span6" value="" placeholder="真实姓名..."></td>
	</tr>
	<!-- <tr>
		<td align="right">昵 称：</td>
		<td><input id="nickname" name="nickname" type="text" size="35" class="span6" value="" placeholder="昵 称..."></td>
	</tr> -->
	<!-- <tr>
		<td align="right"><font color="red">*</font> 性 别：</td>
		<td height="30">  
		<input id="sex" name="sex" type="radio" value="男"  class="validate[required]"> 男 
		<input id="sex" name="sex" type="radio" value="女"  class="validate[required]"> 女 
		</td>
	</tr>  -->
<!-- 	<tr>
		<td align="right"><font color="red">*</font> 出生日期：</td>
		<td><input id="birthday" name="birthday" type="text" size="35" class="validate[required,custom[date]] span6" value="0000-00-00" placeholder="出生日期..."></td>
	</tr>  -->
	<tr>
		<td align="right"><font color="red">*</font> 身份证号：</td>
		<td><input id="idcard" name="idcard" type="text" size="35" class="validate[required, length[15,18]] span6" value="" placeholder="身份证号..."></td>
	</tr> 
	<tr>
		<td align="right"><font color="red">*</font> 手机号：</td>
		<td><input id="mobile" name="mobile" type="text" size="35" class="validate[optional,custom[telephone]] span6" value=""  placeholder="手机号..."></td>
	</tr>
	<!-- <tr>
		<td align="right">固定电话：</td>
		<td><input id="tel" name="tel" type="text" size="35" class="validate[optional,custom[telephone]] span6" value="" placeholder="固定电话..."></td>
	</tr>
	<tr>
		<td align="right">QQ号：</td>
		<td><input id="qq" name="qq" type="text" size="35" class="validate[optional,custom[onlyNumber],length[5,15]] span6" placeholder="QQ号..."  value=""></td>
	</tr>
	<tr>
		<td align="right">旺旺号：</td>
		<td><input id="wangwang" name="wangwang" type="text" size="35" class="span6" placeholder="旺旺号..." value=""></td>
	</tr> -->
	<!-- <tr>
		<td align="right"><font color="red">*</font> 电子邮件：</td>
		<td><input id="email" name="email" type="text" size="35" placeholder="电子邮件..." class="span6 validate[optional,custom[email]]" value=""></td>
	</tr> -->
	<tr>
	  <td align="right">&nbsp;</td>
	  <td><input type="submit" value="保 存" class="btn btn-info btn-large"/></td> 
	</tr>
  </table> 
</form>  

</body>
</html>