<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>

<script type="text/javascript">
$(function (){
	$("#freeLookup2").click(function(){
		var s = '${ctx}/views/system/departmentTreeSelect.jsp?lookupId='+$(this).attr("lookupId")+"&lookupName="+$(this).attr("lookupName");
		$.post(s, {}, function(str){
			layer.open({
				type: 1,
				content: str //注意，如果str是object，那么需要字符拼接。
			});
		});
	});
 });

function f_role(id){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['600px', '400px'],
	    content: '${ctx}/system/role/userRole.do?userid='+id
	}); 
}

function f_edit(id){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['600px', '350px'],
	    content: '${ctx}/system/user/input.do?id='+id
	}); 
}

function f_add(){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['600px', '450px'],
	    content: '${ctx}/views/system/userInput.jsp' 
	}); 
}


var filds = [ 
			 {display : '序号',name : 'id',width : 20}, 
			 {display : '登录名',name : 'loginname',width : 20}, 
			 {display : '姓名',name : 'realname',width : 20}, 
             {display : '操作',name:'',width : 20, 
          		 render:function(row){
          			 var html ="";
                     html+='<a href="javascript:void(0);" onclick="f_role({0})" title="角色" ></a>';
                     
          			 if(row.loginname!='admin'){
                     	html+=' | ';
	                    html+='<a href="javascript:void(0);" onclick="f_edit({0})" title="修改" ><img src="${ctx}/static/images/modi.png"></a>';
	                 	html+=' | ';
	                    html+='<a href="javascript:void(0);" onclick="gridDelete(\'{0}\',\'${ctx}/system/user/delete.do\')"  title="删除" ><img src="${ctx}/static/images/del.png"></a>';
	                    html+=' | ';
	                    html+='<a href="javascript:void(0);" onclick="reset({0})"  title="重置密码:111111" ><img src="${ctx}/static/images/resetpwd.png"></a>';
          			 }
                     return Free.replace(html,row.id);
          		 }
             }
           ];
var opt ={
    url: '${ctx}/system/user/ajax_list.do',
	filds : filds,
	pageSize:10,
	trimBlank:true,
	param:{}
};     

var g;
$(function() {
	g = $("#maingrid").grid(opt);
});

function clearText(){
	$("#areaname").val("");
	$("#areaid").val("");
}

function reset(id){
	layer.confirm('是否重置密码', function(index){
       	$.ajax({
	  		   type: "POST",
	  		   url: "${ctx}/system/user/reset.do?id="+id,
	  		   data: {id:id},
	  		   error: function(request) {
	              alert("操作失败");
	           },
	  		   success: function(data){
	  			 layer.msg('操作成功');
	  			// g.reload();
	  		}
	  	});
	    layer.close(index);
	});  
}
</script>
</head>
<body>

<div class="content">
	<div class="tit">用户信息列表</div>
	<div class="tit_down">
		<form id="gridQueryForm">
		<ul class="select">
				<li><label class="fl">登录名：</label><input type="text" name="search@EQ@loginname"></li>
				<li><label class="fl">真实姓名：</label><input type="text" name="search@EQ@realname"></li>
				<div class="clear"></div>
		</ul>
				<div><input type="button" value="查询" onclick="gridSearch()" class="btn2c"></div>
				<div class="clear"></div>
				<div class="btn fr">
				<!--  -->
					<!-- <shiro:hasPermission name="user:add" > -->
						<input type="button" value="新增" onclick="f_add()" class="btn01">
					<!-- </shiro:hasPermission>-->
				</div>
				<div class="clear"></div>
		</form>
		<div id="maingrid"></div>
		<div id="Pagination"></div>
	</div>
</div>
</body>	

</html>
