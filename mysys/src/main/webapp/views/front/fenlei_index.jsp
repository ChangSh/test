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

<script type="text/javascript"> 
var pageIndex = 0; //页面索引初始值   
var pageSize = 4; //每页显示条数初始化，修改显示条数，修改这里即可    
var opt = {//分页参数
	prev_text : "上一页",
	next_text : "下一页 ",
	items_per_page : pageSize,
	current_page : pageIndex, //当前页索引
	num_edge_entries : 1, //边缘页数
	num_display_entries : 2, //主体页数
	callback : pageselectCallback,
}

//请求数据   
function InitTable(pageIndex, pageSize) {
	$.ajax({
				url : "${ctx}/goodsFront/ajax_listFenlei.do?pageIndex=" + pageIndex
						+ "&pageSize=" + pageSize+"&code="+"${param.code}"+"&gtype="+"${param.code}", //提交到一般处理程序请求数据            //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)
				type : "POST",
				dataType : "json",
				success : function(data) {
					
				      $.each(data.Rows, function(i, value) {
				    	  var pic=null;
				    	  if(value.filepath==null){
				    		  pic="${ctx}/static/images/model.png";
				    	  }else{
				    		  pic=value.filepath;
				    	  }
				    	  
						   $("#detail").append('<div class="main_item">' +
									'<div style="text-align:center;height:215px;overflow:hidden;"> '+
							'<a href="${ctx}/goodsFront/fetchDetail.do?goodsid='+value.id+'" target="_blank"><img src='+pic+' width="100%"></a></div> '+
						'<div style="padding:10px">'+
							'<a href="#" target="_blank" style="font-size:18px;font-weight:bold;">'+value.gname+'</a><br>'+
											'<br><span class="price">查看详细</span></div></div>');
				
						});
				}
			});
} 
function pageselectCallback(page_index, jq) {//分页回调函数
	$("#detail").html("");
	InitTable(page_index, pageSize)
	return false;
}
$(function() {//Init
	$.ajax({
		url : "${ctx}/goodsFront/ajax_listFenlei.do?code="+"${param.code}",
		type : "POST",
		dataType : "json",
		success : function(data) {
			
			var pageNum = Math.floor(data.Total / pageSize) + 1;
			// 创建分页
			$("#pagination").pagination(data.Total, opt);
			
		},
		error : function(data) {
	
		}
	})
})

function find(){
	var content=$("find").val();
	$.ajax({
		type:"POST",
		url:"${ctx}/goods/find.do",
		data:$("#form").serialize(),
		success:function(data){
			if(data=="ok"){
              alert("保存成功");
              parent.location.href='${ctx}/views/goods/goodsInfoList.jsp';
			}else{
				
				alert("保存失败");
			}
		}
	})
	
}
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
<style type="text/css">
*:focus {
	outline: none;
}
</style>
<style type="text/css">
.ui-dialog{z-index:1000001;border:10px #efefef solid;}
.ui-dialog-content .ui-widget-content{background:url('')}
.ui-dialog-titlebar{display:none}
.memberbox{display:none} 
</style>
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
<a class="member_a" href="javascript:member_dialog('login.jsp',850,450,'');">登录/注册</a>  
</shiro:lacksRole>
<shiro:hasRole name="普通用户">
 	你好,<a class="member_a" href="javascript:member_dialog('order.jsp',850,450,'');"> <shiro:principal property="realname"/></a>|
 	<a href="${ctx}/system/user/frontlogout.do" rel="nofollow" onclick="return confirm('确认退出账户？');"> 安全退出</a>
</shiro:hasRole>
		</li>
	</ul>	
	</div> 
</div>

<div class="header w980">
	<div class="clr"></div>
	<div class="header_left">
		<a href="${ctx}/static/shopping_files/shopping.html"><img src="${ctx}/static/shopping_files/images/logo.jpg" width="240" height="60"></a>
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

</script>
<div class="w980">
	<div id="all_menu_layer">
	<div class="col_menu">
			<div class="cat_tree"> 
				<ul>
										<li class="menu_li_1">
						<a href="#" class="menu_title"></a> 
						   
					</li>
										<li class="menu_li_1">
						<a href="#" class="menu_title"></a> 
						   
					</li>
			  </ul>
			</div>
		</div> 
		
	
	</div>
	<div class="ppt">
			 <div id="owl-demo" class="owl-carousel">
	
</div>
	
	</div>
	

<div class="clr"><br></div>

<div class="w980">
	<div style="float:left;width:730px">
<div class="item_header">
			<span style="font-size:18px;font-weight:bold;"><img src="${ctx}/static/shopping_files/images/tag.gif" align="absmiddle"> 房源精选</span> 
			<div class="clr"></div> 
		</div>
				<div id="detail">
		
		</div>	
		
		 
		<div class="clr"></div> 
	   <div class='pagination' id="pagination" style="text-align:center;"></div>	

	 
	</div>

	<div style="float:right;width:240px;padding-top:30px;">
		
		<div class="clr"><br></div>

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
	</div>

	<div class="clr"><br></div>
</div>



<div class="w980 help">
	<div class="clr"><br></div>
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