<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>吉祥租房网</title>
<script src="${ctx}/static/jquery-1.9.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/static/pagination_zh/lib/jquery.pagination.js"></script>
<link href="${ctx}/static/shopping_files/css/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css">
<script src="${ctx}/static/shopping_files/js/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/shopping_files/js/common.js"></script>
<script src="${ctx}/static/shopping_files/js/member.js" type="text/javascript"></script> 
<script type="text/javascript" src="${ctx}/static/focus/js/owl.carousel.js"></script>

<link type="text/css" rel="stylesheet" href="${ctx}/static/focus/css/focus.css"> 
<link type="text/css" rel="stylesheet" href="${ctx}/static/focus/css/owl.carousel.css"> 
<link type="text/css" rel="stylesheet" href="${ctx}/static/shopping_files/css/style.css">   
<link rel="stylesheet" type="text/css" href="${ctx}/static/shopping_files/css/main.css">
<link href="${ctx}/static/pagination_zh/lib/pagination.css" rel="stylesheet" type="text/css" />


<style type="text/css">
.ui-dialog{z-index:1000001;border:10px #efefef solid;}
.ui-dialog-content .ui-widget-content{background:url('')}
.ui-dialog-titlebar{display:none}
.memberbox{display:none} 
</style>
<script>

$(function(){
 	$.ajax({
		   type: "POST",
		   url:  "${ctx}/goodsFront/guess.do",
		   data: {},
		   success: function(data){
			  
			      $.each(data.guess, function(i, value) {
			    	  var pic=null;
			    	  if(value.filepath==null){
			    		  pic="${ctx}/static/images/model.png";
			    	  }else{
			    		  pic=value.filepath;
			    	  }
			    	  
			    	   $("#guess").append('				<div style="padding:10px;text-align:center;"> '+
								'<a href="${ctx}/goodsFront/fetchDetail.do?goodsid='+value.id+'" target="_blank"> '+
								'<img src="'+pic+'" width="180" />'+
								'<p>'+value.gname+'</p>'+
								
							'	</a>'+
							'</div>');
					});
		   }
	});
});
$(function(){
 	$.ajax({
		   type: "POST",
		   url:  "${ctx}/goodsFront/fenlei_list.do",
		   data: {},
		   success: function(data){
			      $.each(data.fenleilist, function(i, value) {
			    	   $("#fenlei").append('<li class="menu_li_1"><a href="${ctx}/views/front/fenlei_index.jsp?code='+value.code+'" class="menu_title">'+value.codename+'</a> </li>');
					});
		   }
	});
});
</script>
</head>

<body> 



<div id="memberbox" class="memberbox">
	<div id="member_dialog" class="member_dialog" title="&#160;">
		<div>&nbsp;</div>
		<div id="member_child_box"></div> 
	</div>
</div><div class="w_all">
	<div class="w980">
	<ul>
		<li style="padding:8px 0 8px 0;float:left;">
		您好，欢迎来到吉祥租房网
		</li> 
<li style="float:right;padding-top:6px;">
<shiro:lacksRole name="普通用户">
<a class="member_a" href="javascript:member_dialog('${ctx}/views/front/login.jsp',850,450,'');">登录/注册</a>  
</shiro:lacksRole>
<shiro:hasRole name="普通用户">
 	你好,<a class="member_a" href="javascript:member_dialog('${ctx}/views/front/order.jsp',850,450,'');"> <shiro:principal property="realname"/></a>|
 	<a href="${ctx}/system/user/frontlogout.do" rel="nofollow" onclick="return confirm('确认退出账户？');"> 安全退出</a>
</shiro:hasRole>
</li>
</ul>
	</div> 
</div>
<div class="header w980">
	<div class="clr"></div>
	<div class="header_left">
		<a href="${ctx}/views/front/index.jsp"><img src="${ctx}/static/shopping_files/images/logo.jpg" width="240" height="60"></a>
	</div>
	<div class="header_center">
						<form action="${ctx}/goodsFront/find.do" method="POST" class="search_form"> 
		  <input type="text" name="content" class="serach_txt" id="keywords"> 
		 <!--  <button class="search_btn" onclick="findlike()">搜索</button> -->
		 <input type="submit" value="搜索" class="search_btn">
		</form>
		<div style="padding-top:5px;"><!-- 规则： /search?keywords=搜索关键词 -->
		</div>
	</div>
	<div class="header_right">
	</div>
	<div class="clr"></div> 
</div>

<div class="menu"> 
	<div class="w980">
		<div id="menu_nav">
			<div id="menu_nav_index"><a href="#">全部分类</a><img src="${ctx}/static/shopping_files/images/ico_down.gif" class="menu_ico"></div>
			<div id="hide_menu_layer" style="display:none;clear:both;"></div>
		</div>
		
		<div class="menu_link">
			<div class="menu_link_item"><a href="${ctx}/views/front/index.jsp">首页</a></div>
<shiro:lacksRole name="普通用户">
	<div class="menu_link_item"><a href="javascript:member_dialog('${ctx}/views/front/login.jsp',850,450,'');">发布房源</a></div> 
</shiro:lacksRole>
<shiro:hasRole name="普通用户">
 	<div class="menu_link_item"><a href="javascript:member_dialog('${ctx}/views/front/goodsNew.jsp',900,700,'');">发布房源</a></div> 
</shiro:hasRole>			
						<div class="menu_link_item"><a href="#">关于</a></div> 
						<div class="menu_link_item"><a href="#">联系我们</a></div> 
			  
			<div class="clr"></div> 
		</div>
		<div class="clr"></div>   
	</div>
</div>

<div id="all_menu" style="display:none">
	<div class="col_menu">
			<div class="cat_tree"> 
				<ul id="fenlei">
		 </ul>
			</div>
		</div> 
</div>


<script>


function load_top_menu(){
	$(".cat_tree > ul > li").hover(function(){
		$(this).addClass("selected");
		$(this).children("a:eq(0)").addClass("h2-title");
		$(this).children("ul").show();
	},function(){
		$(this).removeClass("selected");
		$(this).children(".menu_title").removeClass("h2-title");
		$(this).children("ul").hide();
	});
}
load_top_menu();

$(document).ready(function() {  
	$("#menu_nav").hover( 
		function () {   
			$('#hide_menu_layer').html($('#all_menu').html()); 
			$("#hide_menu_layer").css('display','block');  
			$("#hide_menu_layer").css('padding-top','10px'); //+10px
			load_top_menu(); 
		}, 
		function () {  
			$("#hide_menu_layer").css('display','none');  
		} 
	);  
});
</script> 

<div class="clr"></div>

<div class="w" style="background-color:#f3f3f3">
	
	<div class="w980" style="padding-top:10px;">
		<div style="padding-bottom:10px;">
			
					</div>
		<div class="clr"></div>
		<div style="float:left; width:750px;">
			<div style="border:1px solid #D4D4D4; background-color:white;">
				<div style="padding:20px;">
					<h1>${ob.gname}</h1>
					<h2></h2>

					<div class="detail_left">
						<div style="padding-left:20px;padding-top:15px">
							<span class="buy_price"><span id="real_price_box" style="color:white">${ob.gunitprice}</span>元/月</span>
							&nbsp; 
							
						</div>
						<div style="padding-left:20px;padding-top:40px;text-align:center;">
						
						<shiro:lacksRole name="普通用户">
						<a class="member_a" href="javascript:member_dialog('${ctx}/views/front/login.jsp',850,450,'');"><img src="${ctx}/static/shopping_files/images/addtocart.jpg"/></a>  
						</shiro:lacksRole>
							<shiro:hasRole name="普通用户">
							<a href="javascript:member_dialog('${ctx}/goodsFront/addCart.do?id=${ob.id}',850,450,'');"><img src="${ctx}/static/shopping_files/images/addtocart.jpg"/></a>
							</shiro:hasRole>
						</div>
						<div style="padding-left:20px;padding-top:30px;text-align:left;"> 
							<!-- 已售出 <b style="font-size:18px;color:#EE5239"></b> 件  -->
							<b style="font-size:15px;color:#000000">面积:${ob.gsize}㎡</b><br/>
							<b style="font-size:15px;color:#000000">类型:${ob.codename}</b><br/>
							<b style="font-size:15px;color:#000000">地址:${ob.gaddr}</b><br/>
						</div> 
						<div style="padding-left:20px;padding-top:40px;">
						<%-- 	<img src="${ctx}/static/shopping_files/images/buy_ico01.gif"/>
							<img src="${ctx}/static/shopping_files/images/buy_ico02.gif" style="padding-left:50px"/> --%>
						</div>  
					</div>
					<div class="detail_right">
						<div style="">
							<img src="${ob.filepath}" onerror=javascript:this.src="${ctx}/static/images/model.png" width="459" title="" >
						</div>  
					</div>
					<div class="clr"><br/></div>
					<div style="background-color:#F4F4F4">
						<!-- Baidu Button BEGIN -->
<div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare">
<a class="bds_qzone"></a>
<a class="bds_tsina"></a>
<a class="bds_tqq"></a>
<a class="bds_renren"></a>
<a class="bds_t163"></a>

<a class="shareCount"></a>
</div>
<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=0" ></script>
<script type="text/javascript" id="bdshell_js"></script>


					</div>
					<div class="clr"></div> 
				</div>
			</div>

			<div class="clr"><br/></div> 

				<style>
	.tab_body{
		width:100%;
		margin:auto 0;
		background:#fff;
		border:1px solid #d3d3d3;
		padding:1px;
	}
	.tab_menu{
		list-style:none;
		width:100%;
		overflow:hidden;
	}
	.tab_menu li{
		width:25%; 
		float:left;
		height:30px;
		line-height:30px;
		color:#000;
		background:#efefef;
		text-align:center;
		cursor:pointer;
	}
	.tab_menu li.current{
		color:#fff;
		background:#2DB8AD; /* 更改颜色 */
	}
	.tab_box{
		padding:0px;
	}
	.tab_box .hide{
		display:none;
	} 
	.box_w{
		width:750px;  /* 更改宽度 */
	} 
	</style>

	<div class="tab_body my_tab">
		<ul class="tab_menu">
			<li class="current">房源信息</li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
		<table>
		<tr>
		<td width="25%">简介:</td>
		<td><p style="text-align:left;color:#a37e37;">
			${ob.gbriefintro}
		</p></td>
		</tr>
		<tr>
		<td>面积:</td>
		<td><p style="text-align:left;color:#a37e37;">
			${ob.gsize}平方米
		</p></td>
		</tr>
		<tr>
		<td>详细介绍:</td>
		<td><p style="text-align:left;color:#a37e37;">
			${ob.gdetailintro}
		</p></td>
		</tr>
		<tr>
		<td width="25%">附近:</td>
		<td><p style="text-align:left;color:#a37e37;">
			${ob.garound}
		</p></td>
		</tr>
		<tr>
		<td width="25%">交通:</td>
		<td><p style="text-align:left;color:#a37e37;">
			${ob.gtraffic}
		</p></td>
		</tr>				
		<tr>
		<td>实拍图：</td>
		<td><img src="${ob.filepath}" onerror=javascript:this.src="${ctx}/static/images/model.png"  width="459" title="" ></td>
		</tr>
		</table>
		
	</div>
 		</div>

		<div style="float:right; width:220px;">
	
			<div class="clr"><br/></div>

			<div style="border:1px solid #efefef;width:220px;background-color:white;">
				<div style="padding:10px;font-size:18px;font-weight:bold;text-align:center">猜您喜欢</div>
				<div id="guess">
				
			
				</div>
			
				 
			</div>
			<div class="clr"><br/></div>

				<div style="border:1px solid #efefef;width:240;padding:10px;">
				
				<div style="border-bottom:1px solid #efefef;margin-bottom:20px;padding:10px;">
				<img src="${ctx}/static/shopping_files/images/t2.gif"><br>
				<shiro:lacksRole name="普通用户">
						<a class="member_a"  style="padding-left:40px;" href="javascript:member_dialog('${ctx}/views/front/login.jsp',850,450,'');">有您的建议，我们才能更好</a>  
						</shiro:lacksRole>
							<shiro:hasRole name="普通用户">
							<a href="javascript:member_dialog('${ctx}/views/front/message.jsp',500,400,'');" style="padding-left:40px;">有您的建议，我们才能更好</a>
							</shiro:hasRole>
						</div>
				</div>

			<div class="clr"><br/></div>

			
		</div>
		<div class="clr"><br/></div>
	</div>
 
</div> 

 

<div class="clr"><br></div>
<div class="w980 foot"> 
		<div>
		<a href="#" target="_blank">关于我们</a> | <a href="#" "="" target="_blank">联系我们</a> | <a href="#" "="" target="_blank">人才招聘</a> | <a href="#" "="" target="_blank">资质荣誉</a> | <a href="#" "="" target="_blank">帮助中心</a> | <a href="#" target="_blank">手机商城</a>  
	</div>
	<div>
		
		Copyright 2015 &#169;  版权所有
	</div><div class="clr"><br></div>
</div>

</body>
</html>

