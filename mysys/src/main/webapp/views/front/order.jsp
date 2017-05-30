<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
	


	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  

<script type="text/javascript">window.onerror = function(){return true;}</script>

<script src="${ctx}/static/shopping_files/js/jquery-1.7.2.min.js" type="text/javascript" ></script>
<script src="${ctx}/static/shopping_files/js/member.js" type="text/javascript"></script> 

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
		   url:  "${ctx}/goodsFront/order_list.do",
		   data: {},
		   success: function(data){
			  
			      $.each(data.order, function(i, value) {
			    	  var stu=value.statu;
					  var statu;
					   switch(stu){
					   case -1:
					   		  statu="未付款";
					   		  break;
					   case -2:
					   		  statu="未确认";
					   		  break;
					   case -3:
					   		  statu="已确认";
					   		  break;
					   case  3:
					   		  statu="已评价";
					   		  break;
					   }
			    	  
			    	   $("#cart").append('<tr><td style="width:16%"><img src="'+value.filepath+'" style="height:50px" class="img-circle"/></td>'+
			           '<td style="width:16%"><a href="#"  style="font-size:14px">'+value.gname+'</a><br/></td>'+
			           '<td style="width:16%">&yen;'+value.unitprice+'</td>'+
			           '<td style="width:16%"><a href="${ctx}/goodsFront/change.do?id='+value.id+'">'+statu+'</a></td>');
					});
		   }
	});
});  


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
    <li ><a href="house.jsp" rel="nofollow"> 我的房源</a></li>
     <li ><a href="buycart.jsp" rel="nofollow"><i class=" icon-shopping-cart"></i> 我的预定</a></li>
    <li class="active"><a href="#" rel="nofollow">预定详情</a></li>
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
        <td><strong>订单状态</strong></td>
      
      </tr> 
    </table>  
  
    <table width="100%" border="0" class="table table-hover" id="cart">
	
     	</table> 
  
  

</body>
</html>
