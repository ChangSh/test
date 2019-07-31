<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script>

var setting = {
		check: {
			enable: true//是否显示checkbox
		},
		async: {
			enable: true,
			url:"${ctx}/system/menu/ajax_all.do?check=true&roleid=${ob.id}"
		},
		data: {
			key:{
				name:"menuname"
			},
			simpleData: {
				enable: true,//true时下面的设置生效
				idKey: "id",//id
				pIdKey: "pid",//pid
			}
		}
	};
var treeObj = null;
$(function ()
{	
	treeObj = $.fn.zTree.init($("#menuTree"), setting);
	 $(".freeGrid_submit").click(function(){
		 var nodes = treeObj.getCheckedNodes(true);
		 var text="";
         for (var i = 0; i < nodes.length; i++)
         {
             text += nodes[i].id + ",";
         }
		$.ajax({
 			   type: "POST",
			   url: "${ctx}/system/role/saveMenu.do",
			   data: {menus:text,id:'${ob.id}'},
			   success: function(data){
				    parent.g.reload();
    	     		parent.layer.msg('操作成功',{time: 1000}); 
	   	  			parent.layer.closeAll('iframe');
			   },
			   async: false
			});
	 });
});
</script>
<style>
<!--
ul.ztree {
    background: none repeat scroll 0 0 #f0f6e4;
    border: 1px solid #617775;
    height: 360px;
    margin-top: 10px;
    overflow-x: auto;
    overflow-y: scroll;
    width: 490px;
    margin-left:50px;
    
}
-->
</style>
</head>
<body>
<ul id="menuTree" class="ztree" style="overflow-y:scroll;height: 250px;background-color: #FFF"></ul>
<p style="position:absolute;left:50%;margin-left:-40px;" >
	<button class="freeGrid_submit btn1">保存</button>
</p>
</body>
</html>
