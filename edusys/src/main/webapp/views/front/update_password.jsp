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
<script>
function save(){
	if($("#psw").val()!=""&&$("#npsw").val()!=""&&$("#rpsw").val()!=""){
		if($("#npsw").val()!=$("#rpsw").val()){
			alert("密码输入不一致！");
		}else{
	$.ajax({
        url:"${ctx}/system/user/updatepsw.do",
        type:"post",
        dataType:"text",
        data:{psw:$("#psw").val(),npsw:$("#npsw").val(),rpsw:$("#rpsw").val()},
        success:function(data){
            
            	 alert("修改成功！");
            	 parent.location.reload();
             
        } 
    }); 
	}  
	}else{
		alert("不能空！");
		
	}
	
}
</script>
</head>
<body>

<div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="#">会员中心</a>
    <ul class="nav">  
     <li ><a href="buycart.jsp" rel="nofollow"><i class=" icon-shopping-cart"></i> 我的购物车</a></li>
    <li ><a href="#" rel="nofollow"><i class=" icon-shopping-cart"></i> 我的订单</a></li>
   
		<li class=" dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">我的账户 <b class="caret"></b></a>
		<ul class="dropdown-menu">
			<li><a href="mydetail.jsp"  rel="nofollow"><i class="icon-cog"></i> 个人资料</a></li> 
			
			<li class="active"><a href="#"  rel="nofollow"><i class="icon-key"></i> 更改密码</a></li> 
			
		</ul>
	</li> 

	<li ><a href="${ctx}/views/front/message.jsp" ><i class="icon-comment-alt"></i> 留言</a></li> 


	</ul>
  </div>
</div> 

<form action="" method="POST" id="form">
初始密码：<input name="password" type="password" id="psw" value="" /><br>
新密码：<input name="newpassword" type="password" id="npsw" value="" /><br>
确认新密码：<input name="newpassword1" type="password" id="rpsw" value="" /><br>

</form>
<button onclick="save()">确认修改</button>
</body>
</html>
