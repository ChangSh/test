<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>

<!DOCTYPE html>	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
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
<script type="text/javascript">
function selectCity(val){
	$("#div_area").html('');
	$("#div_city").load("common_region.php?parent_code="+val+"&type=city&rnd="+Math.random());
}
function selectArea(val){
	$("#div_area").load("common_region.php?parent_code="+val+"&type=area&rnd="+Math.random());
}
</script>
</head>
<body>

<div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="profile.php">会员中心</a>
    <ul class="nav">  
	<li ><a href="buycart.jsp" rel="nofollow"><i class=" icon-shopping-cart"></i> 我的购物车(<b>1</b>)</a></li>


	<li ><a href="order.jsp"  rel="nofollow"> <i class="icon-pushpin"></i> 我的订单 </a></li> 

	<li class=" dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">我的账户 <b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li><a href="mydetail.jsp"  rel="nofollow"><i class="icon-cog"></i> 个人资料</a></li> 
			<li><a href="addressList.jsp"  rel="nofollow"><i class="icon-map-marker"></i>&nbsp; 收货地址</a></li>
			<li><a href="update_password.jsp"  rel="nofollow"><i class="icon-key"></i> 更改密码</a></li> 
			<li><a href="logout.php" rel="nofollow" onclick="return confirm('确认退出账户？');"><i class="icon-signout"></i> 安全退出</a></li>
		</ul>
	</li> 

	<li ><a href="guestbook.php" rel="nofollow"><i class="icon-comment-alt"></i> 留言</a></li> 

	
 
	</ul>
  </div>
</div>  
<table width="100%" border="0" cellpadding="3" cellspacing="0" >
    
	<tr>
      <td> 
				  </td>
    </tr>
    <tr>
      <td> 
		  		  		  <form method="post" action="do.php?ac=traffic">
           
			  <table width="100%" border="0" cellpadding="3" cellspacing="0"> 
                  <tr>
                    <td width="16%" align="right"><font color="#FF0000">*</font> 收货地区</td>
                    <td style="padding-left:5px">
												<span id="div_province">
						<select id="province" name="province" onchange="selectCity(this.value)" class="span2">
						<option value=""></option>
						 
						<option value="10">北京市</option> 
						 
						<option value="11">天津市</option> 
						 
						<option value="12">河北省</option> 
						 
						<option value="13">内蒙古</option> 
						 
						<option value="14">山西省</option> 
						 
						<option value="20">辽宁省</option> 
						 
						<option value="21">吉林省</option> 
						 
						<option value="22">黑龙江省</option> 
						 
						<option value="30">上海市</option> 
						 
						<option value="31">江苏省</option> 
						 
						<option value="32">山东省</option> 
						 
						<option value="33">安徽省</option> 
						 
						<option value="34">福建省</option> 
						 
						<option value="35">浙江省</option> 
						 
						<option value="36">江西省</option> 
						 
						<option value="40">广东省</option> 
						 
						<option value="41">广西</option> 
						 
						<option value="42">海南省</option> 
						 
						<option value="60">重庆市</option> 
						 
						<option value="61">四川省</option> 
						 
						<option value="62">云南省</option> 
						 
						<option value="63">贵州省</option> 
						 
						<option value="64">西藏</option> 
						 
						<option value="70">河南省</option> 
						 
						<option value="71">湖北省</option> 
						 
						<option value="72">湖南省</option> 
						 
						<option value="80">陕西省</option> 
						 
						<option value="81">青海省</option> 
						 
						<option value="82">宁夏</option> 
						 
						<option value="83">新疆</option> 
						 
						<option value="84">甘肃省</option> 
						 
						<option value="90">香港</option> 
						 
						<option value="91">澳门</option> 
						 
						<option value="92">台湾</option> 
						 
						<option value="93">国外</option> 
												</select>
						</span> 
												<span id="div_city"></span>
					 
						<span id="div_area"></span>
                    </td>
                  </tr>
                  <tr>
                    <td align="right"><font color="#FF0000">*</font> 详细地址</td>
                    <td><input name="address" type="text" class="span6" id="address" size="35" value="" placeholder="详细地址..."/></td>
                  </tr>
				  <tr>
                    <td align="right"> 邮政编码</td>
                    <td><input name="zip" type="text" class="span6" id="zip" size="6" value="" placeholder="邮政编码..."/></td>
                  </tr>
                
                    <td width="25%" align="right"><font color="#FF0000">*</font> 收货人</td>
                    <td><input name="username" type="text" class="span6" id="username" size="25" value="" placeholder="收货人姓名..."/></td>
                  </tr>
                  <tr>
                    <td width="20%" align="right"><font color="#FF0000">*</font> 联系电话</td>
                    <td><input name="tel" type="text" class="span6" id="tel" size="25" value="" placeholder="联系电话..."/></td>
                  </tr>
                  <tr>
                    <td align="right"></td>
                    <td> <input type="submit" value="确认无误，下一步！" class="btn btn-large btn-warning"/></td>
                  </tr> 
                </table>
 
				</form>
				 
        </td>
    </tr>  
</table>
</body>
</html>
