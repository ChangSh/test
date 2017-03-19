<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>

<script type="text/javascript">

function f_edit(id){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['600px', '350px'],
	    content: '${ctx}/system/role/input.do?id='+id
	}); 
}
function f_detail(id){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['600px', '350px'],
	    content: '${ctx}/system/role/input.do?readonly=1&id='+id
	}); 
}
function f_roleUser(id){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['800px', '600px'],
	    content: '${ctx}/views/system/userListSelectForRole.jsp?roleid='+id
	}); 
}
function f_roleMenus(id){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['600px', '400px'],
	    content: '${ctx}/system/role/inputRoleMenu.do?id='+id
	}); 
}
function f_roleAuthorize(id){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['600px', '350px'],
	    content: '${ctx}/system/role/inputRoleAuthorize.do?id='+id
	}); 
}
function f_add(){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['600px', '350px'],
	    content: '${ctx}/system/role/input.do'
	}); 
}

var filds = [ 
             {display : '角色',name : 'rolename',width : 15}, 
	          {display : '描述',name : 'description',width : 15},
             {display : '操作',name:'',width : 25, unbindClick:true,
          		 render:function(row){
          			 var html ="";
                     html+='<a href="javascript:void(0);" onclick="f_roleUser({0})" title="授权用户" ><img src="${ctx}/static/images/AuthorizedUsers.png"></a>';
                     html+=' | ';
                     html+='<a href="javascript:void(0);" onclick="f_roleMenus({0})" title="授权菜单" ><img src="${ctx}/static/images/Licensemen.png"></a>';
                     html+=' | ';
                     html+='<a href="javascript:void(0);" onclick="f_roleAuthorize({0})" title="授权功能" ><img src="${ctx}/static/images/UAAF.png"></a>';
                     html+=' | ';
                     html+='<a href="javascript:void(0);" onclick="f_edit({0})" title="编辑" ><img src="${ctx}/static/images/modi.png"></a>';
                     html+=' | ';
                     html+='<a href="javascript:void(0);" onclick="gridDelete(\'{0}\',\'${ctx}/system/role/delete.do\')"  title="删除" ><img src="${ctx}/static/images/del.png"></a>';
                     return Free.replace(html,row.id);
          		 }
             }
           ];
var opt ={
    url: '${ctx}/system/role/ajax_list.do',
	filds : filds,
	pageSize:10,
	clickTr:function(row){
		f_detail(row.id);
	},
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
</script>
</head>
<body>
<div class="content">
	<div class="tit">角色信息列表</div>
	<div class="tit_down">
		<form id="gridQueryForm">
				<ul class="select">
					<li><label class="fl">角色名：</label><input type="text"  name="search@EQ@rolename"></li>
					<div class="clear"></div>
				</ul>
				<div>
					<input type="button" value="查询" onclick="gridSearch()" class="btn1c">
				</div>
				<div class="clear"></div>
				<div class="fr">
					<shiro:hasPermission name="role:add" >
						<input type="button" value="新增" onclick="f_add()" class="btn01">
					</shiro:hasPermission>
				</div>
				<div class="clear"></div>
	
		</form>
		<div id="maingrid"></div>
		<div id="Pagination"></div>
	</div>
</div>	
</body>	

</html>
