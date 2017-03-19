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
	    content: '${ctx}/system/authorize/input.do?id='+id
	}); 
}

function f_add(){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['600px', '350px'],
	    content: '${ctx}/views/system/authorizeInput.jsp' 
	}); 
}


var filds = [ 
             {display : '模块',name : 'module',width : 20}, 
             {display : '描述',name : 'description',width : 20}, 
             {display : '顺序',name : 'sx',width : 20}, 
             {display : '操作',name:'',width : 20, 
          		 render:function(row){
          			 var html ="";
                     html+='<a href="javascript:void(0);" onclick="f_edit({0})" title="修改" ><img src="${ctx}/static/images/modi.png"></a>';
                     html+=' | ';
                     html+='<a href="javascript:void(0);" onclick="gridDelete(\'{0}\',\'${ctx}/system/authorize/delete.do\')"  title="删除" ><img src="${ctx}/static/images/del.png"></a>';
                     return Free.replace(html,row.id);
          		 }
             }
           ];
var opt ={
    url: '${ctx}/system/authorize/ajax_list.do',
	filds : filds,
	pageSize:10,
	trimBlank:true,
	param:{}
};     

var g;
$(function() {
	g = $("#maingrid").grid(opt);
});

</script>
</head>
<body>

<div class="content">
	<div class="tit">权限信息列表</div>
	<div class="tit_down">
		<form id="gridQueryForm">
				<ul class="select">
					<li><label class="fl">描述：</label><input type="text"  name="search@EQ@description"></li>
					<div class="clear"></div>
				 </ul>
				<div style="width:100px;margin:0 auto">
					<input type="button" value="查询" onclick="gridSearch()" class="btn1">
				</div>
				<div class="clear"></div>
				<div class="fr">
					<shiro:hasPermission name="auth:add" >
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
