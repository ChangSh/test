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
<script>
 $(function(){
 	$.ajax({
		   type: "POST",
		   url:  "${ctx}/goodsFront/cart_list.do",
		   data: {},
		   success: function(data){
			      $.each(data.cart, function(i, value) {
			    	   $("#cart").append('<tr><td><input type="hidden" name="cartId" value="'+value.cartId+'"><input type="hidden" name="id" value="'+value.id+'"><a href="" target="_blank"><img src="'+value.filepath+'" style="width:60px;height:60px" class="img-circle"/></a></td>'+
			           '<td><a href="#"  style="font-size:14px">'+value.gname+'</a><br/></td>'+
			           '<td>&yen;'+value.gunitprice+'</td>'+
			           '<td><a href="${ctx}/goodsFront/removeCart.do?cartId='+value.cartId+'"><img src="${ctx}/static/shopping_files/images/delete.gif"/></a></td></tr>');
					});
		   }
	});
});  
function cacul(price,i){
	
   var n="#"+i+"";
	$(n).html(price);
}

function jiesuan(){
	$.ajax({
		type:"POST",
		url:"${ctx}/goods/jiesuan.do",
		data:$("#form").serialize(),
		success:function(data){
            
              window.location.href='${ctx}/views/front/order.jsp';
		}
	})
	
}
</script>

</head>
<body>

<div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="#">会员中心</a>
    <ul class="nav">  
	<li class="active"><a href="#" rel="nofollow"><i class=" icon-shopping-cart"></i>我的喜欢</a></li>
	 <li ><a href="order.jsp" rel="nofollow"><i class=" icon-shopping-cart"></i> 我的订单</a></li>
		<li class=" dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">我的账户 <b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li><a href="mydetail.jsp"  rel="nofollow"><i class="icon-cog"></i> 个人资料</a></li> 
			
			<li><a href="update_password.jsp"  rel="nofollow"><i class="icon-key"></i> 更改密码</a></li> 
			
		</ul>
	</li> 

	<li ><a href="${ctx}/views/front/message.jsp" ><i class="icon-comment-alt"></i> 留言</a></li> 

	</ul>
  </div>
</div> 

	<table width="100%" border="0" class="table table-hover">
      <tr>
        <td><strong>房源图片</strong></td>
        <td><strong>房源名称</strong></td>
        <td><strong>销售价格</strong></td> 
        <td width="50">删除？</td>
      </tr> 
    </table>  
    <form action="" id="form"> 
    <table width="100%" border="0" class="table table-hover" id="cart">
	
     	</table> 
    </form>  	
     	<table width="100%" border="0" class="table table-hover">
	  	  <tr>
        <td colspan="6" align="right">
		<a href="javascript:window.top.location.reload();" class="btn btn-large btn-info">再逛逛</a> &nbsp; &nbsp; 
		<button class="btn btn-large btn-warning" onclick="jiesuan()">去结算 </button><i class="icon-credit-card"></i> &nbsp; &nbsp; 
		</td>
	  </tr> 
	  </table>

</body>
</html>
