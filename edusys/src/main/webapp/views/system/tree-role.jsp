<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>

   <script type="text/javascript">
   var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
   function closeDialog()
   {
       dialog.close();//关闭dialog 
   }
var setting = {
		check: {
			enable: true//是否显示checkbox
		},
		async: {
			enable: true,
			url:"${ctx}/system/role/ajax_all_list.do"
		},
		data: {
			key:{
				name:"rolename"//显示名字
			},
			simpleData: {
				enable: true,//true时下面的设置生效
				idKey: "id",//id
				pIdKey: "fid",//pid
			}
		},
		callback: {
			onAsyncSuccess: zTreeOnAsyncSuccess//异步加载成功
		}
	};
//异步加载成功后    展开所有节点
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	//zTree.expandAll(true);
	//将外部的值带回并递归查找选中
	var nodes = zTree.getNodes();
	var s ="${userRole}".split(',');
	if (s.length>0)
	checkNode (nodes,s);
};

function checkNode (nodes,s){
	
	for (var i=0; i<nodes.length; i++) {
		
			for (var j=0; j<s.length; j++) {
				if (nodes[i].id==s[j]){
					zTree.checkNode(nodes[i], true, false);
				}
			}
		
	}
}
var zTree = null;
$(function ()
{	
	zTree = $.fn.zTree.init($("#roleTree"), setting);
});

function confirm(){
	//提取选中
	var nodes = zTree.getCheckedNodes(true);
	var ids = "";
	 for (var i = 0; i < nodes.length; i++)
	 {
	        //if (nodes[i].iconSkin){//只取用户节点
		        ids += nodes[i].id + ",";
	        //}
	 }
	 $("#roleids").val(ids);

	$.ajax({
		type: 'POST',
	    url: '${ctx}/system/role/chooseUserRole.do',
	    data: $('#inputForm').serialize(),
	    success:function(data){
	    	if(data == "success"){
 	     		parent.layer.msg('操作成功',{time: 1000}); 
   	  			parent.layer.closeAll('iframe');
	    	}
	    }
	   
	});
}

</script>

	<style type="text/css">
.ztree li span.button.iconUser_ico_docu{margin-right:2px; background: url(${ctx}/static/ztree/css/zTreeStyle/img/diy/ico_user.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}

ul.ztree {
    background: none repeat scroll 0 0 #f0f6e4;
    border: 1px solid #617775;
    height: 360px;
    margin-top: 10px;
    overflow-x: auto;
    overflow-y: scroll;
    width: 490px;
}
.ztree li span.button.ico_docu {
    margin-right: 2px;
    background-position: -110px -32px;
    vertical-align: top;
    float: left;
}
	</style>
</head>
<body>
<table width="90%">
<tr>
<td style="padding-left:10%;">
 <ul id="roleTree" class="ztree" style="float:left;overflow-x:hidden;height:250px"></ul>
</td></tr>
<tr><td style="padding-left:200px;padding-top:20px;">
<form class="form-horizontal" id="inputForm" method="get" action="stat-system" >
	 <input name="userid" type="hidden" value="${userid}"/>
	 <input name="roleids" id="roleids" type="hidden" value=""/>
	
	<input type="button" onclick="confirm()"   class="confirm" value="确定" />
</form>	
	</td></tr></table>
</body>	

</html>
   
