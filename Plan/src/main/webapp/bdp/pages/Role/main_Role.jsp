<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../commons/statics.jsp"%>
<style type="text/css">
.bootstrap-table td {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	max-width: 200px;
}

#Role_view_form .col-sm-9 label {
	font-weight: normal;
}

#Role_form .col-sm-9 label {
	font-weight: normal;
}
</style>
<script type="text/javascript">
var zNodes;  
var nodes;
<!-- ztree相关 -->
var zTreeObj;

//zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
		view: {
			showIcon: true
		},
		check: {
			enable: true
		},
		
		data: {
			simpleData: {       //数据是否采用简单 Array 格式，默认false  
				enable: true
			}
		},
     callback:{
         onCheck:onCheck //回调函数onCheck
     }
};

function ztreecommit(){
	var roleid = $("#hiddenID").val();
    var funcid = [];
    for(var i=0;i<nodes.length;i++){
    	funcid.push(nodes[i].id);
    }
    ajaxHander(contextPath + "/CyFunction/modifyCyFunction.do",{roleid:roleid,funcid:funcid.join(",")},"POST");  

}

var roleid = $("#hiddenID").val();
function onCheck(e,treeId,treeNode){
 
 nodes = zTreeObj.getCheckedNodes(true);
}

<!-- ztree相关 -->
var contextPath="<%=contextPath%>";
function operateFormatter(value, row, index) {
    return [
            '&nbsp;<a class="check"  href="javascript:void(0)" title="查看">',
            '<i class="glyphicon glyphicon-link"></i>',
            '</a> &nbsp; ',
            '  <a class="edit" href="javascript:void(0)" title="编辑">',
            '<i class="glyphicon glyphicon-edit"></i>',
            '</a> &nbsp; ',
            ' <a class="delete"  href="javascript:void(0)" title="删除">',
            '<i class="glyphicon glyphicon-remove-circle"></i>',
            '</a>&nbsp;',
            ' <a class="permission"  href="javascript:void(0)" title="权限">',
            '<i class="glyphicon glyphicon-asterisk"></i>',
            '</a>&nbsp;'
    ].join('');
}

window.operateEvents = {
    'click .edit': function (e, value, row, index) {
	    object_operate.show_modify_modal(row);
    },
    'click .check': function (e, value, row, index) {
    	object_operate.show_view_modal(row);
    },
    'click .delete': function (e, value, row, index) {
    	object_operate.del();
   },
    'click .permission': function (e, value, row, index) {
    	object_operate.show_permission_modal(row);
   }
};
global_variable.model = "Role";
global_variable.model_cn = "角色管理";
global_variable.sortName="";
global_variable.columns =
[ 
 {field: 'state',checkbox:true},
 {field: 'roleid',title: 'id',formatter: function(value,row,index){return row.roleid;}}, 
 {field: 'rolename',title: '角色名称',formatter: function(value,row,index){return row.rolename;}}, 
 {field: 'description',title: '角色描述',formatter: function(value,row,index){return row.description;}}, 
	
 /* {field: 'creatDate',sortable:true,title: '创建时间',formatter: function(value,row,index){
	 return  dateFormat(row.creatDate, 'yyyy-MM-dd HH:mm:ss');}}, */
 {field: 'operate',title: '操作',events: operateEvents,formatter: operateFormatter}
]		
global_variable.onLoadSuccess=function(result){
}
global_variable.showExport = false;

object_operate.del=function(){
	var rows = $("#"+global_variable.model+"_table").bootstrapTable("getSelections");
	var ids = [];
	//for( var o in rows) {
		//ids.push(rows[o].id);
	//}
	for( var o in rows) {		
		var k=0;//获取第一列
		for(var i in rows[o]){
			if(k==0){
				ids.push(rows[o][i]);
			}
			k++;
		}
	}
	
	if(rows.length > 0){
		if(confirm("请确认是否批量删除"+global_variable.model_cn)) {
			  $.ajax({
					 async : false,
					 type: 'POST',
			         data:JSON.stringify(ids),
					 url:"delete"+object_name+".do",
					 dataType:"json",
					 contentType:"application/json",
					 error: function (result) {//请求失败处理函数
						 alert("请求失败");
					  },
					 success:function(result){ //请求成功后处理函数。 
						alert(result.msg);
						object_operate.success();
					 }
				 }); 
		}	
	}else {
		alert("请先选择删除行");
	}
}

object_operate.show_add_modal=function(){
	$("#"+global_variable.model+"_submit").removeAttr('disabled');
	this.type = "add";
	object_form_operate.setTitle();
	$("#hiddenID").val(0);//设置默认值
	object_form_operate.reset();
	$("#"+global_variable.model+"_Modal").modal("show");
	$("#"+global_variable.model+"_submit").unbind("click");
	$("#"+global_variable.model+"_submit").bind("click",function(){
		object_operate.add();
		});	
}
object_operate.add=function(){
        var data= checkForm();
        if(data!=undefined){
        	ajaxHander("add"+object_name+".do",data,"POST"); 
        }
}

object_operate.show_permission_modal=function(obj){
	object_form_operate.setValue(obj);
	var roleid = $("#hiddenID").val();
	ajaxHander(contextPath + "/CyFunction/initData.do",{roleid:roleid},"POST"); 
} 

object_operate.show_modify_modal=function(obj){	
	$("#"+global_variable.model+"_submit").removeAttr('disabled');
	this.type = "modify";
	object_form_operate.setTitle();
	object_form_operate.setValue(obj);
	$("#"+global_variable.model+"_Modal").modal("show");
	$("#"+global_variable.model+"_submit").unbind("click");
	$("#"+global_variable.model+"_submit").bind("click",function(){
		object_operate.modify();
		});
	$("#"+global_variable.model+"_close").bind("click",function(){
		});
}
object_operate.modify=function(){	
	 var data= checkForm();
	 if(data!=undefined){
		 ajaxHander("modify"+object_name+".do",data,"POST"); 
	 }
}
object_form_operate.setValue=function(obj){	
	var k=0;//获取第一列
	for(var i in obj){
		if(k==0){
			$("#hiddenID").val(obj[i]);//设置修改主键
		}
		k++;
	}
	
	for(var o in obj) {
		if($("#"+global_variable.model+"_form input[name='"+o+"']").attr('type') == 'radio'){
			$("#"+global_variable.model+"_form input[value='"+obj[o]+"']").attr("checked","checked");
		}
	   if($("#"+global_variable.model+"_form input[name='"+o+"']").attr('type') == 'text'||
		   $("#"+global_variable.model+"_form input[name='"+o+"']").attr('type') == undefined){
		   $("#"+global_variable.model+"_form input[name='"+o+"']").val(obj[o]);
		}
	}
}
object_form_operate.setViewValue=function(obj,parentName){
	if(!parentName) {
		parentName = "";
	}else {
		parentName += ".";
	}
    for(var o in obj) {
    	$("#"+global_variable.model+"_view_form label[name='"+parentName+o+"']").text(obj[o]);
		if(obj[o] instanceof Object) {
			object_form_operate.setViewValue(obj[o],parentName+o);
		}
	}
}
object_form_operate.reset=function(){
	$("#"+global_variable.model+"_form")[0].reset();
	$("#"+global_variable.model+"_form input[type='hidden']").val("");
}
global_variable.height=550;

function ajaxHander(url,data,type,value){
	   $.ajax({
			 async : false,
			 type: type,
			 beforeSend: function(){				 
				    },
	         data:data,
			 url:url,
			 success:function(data){ //请求成功后处理函数。 	
				 if(url.indexOf("initData.do")>0){
					 zNodes = data;  //把后台封装好的简单Json格式赋给treeNodes  
					 zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
					 nodes = zTreeObj.getCheckedNodes(true);
					 var rname = $("#"+global_variable.model+"_form input[name=rolename]").val();
					 $("#rname").text(rname);
					 $("#"+global_variable.model+"_permission_Modal").modal("show");
				 }
				 else if(url.indexOf("modifyCyFunction.do")>0){
					 alert(data.msg);
					 $("#"+global_variable.model+"_permission_Modal").modal("hide");
				 }
				 else{
					 alert(data.msg);
					 object_operate.success();
				 }
			 }
		 }); 
}
	
function checkForm(){		
	    //验证
		return $("#"+global_variable.model+"_form").serialize();		
}
function dateFormat(time,format){
	   var t = new Date(time);
 var tf = function(i){return (i < 10 ? '0' : '') + i};
 return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
     switch(a){
         case 'yyyy':
             return tf(t.getFullYear());
             break;
         case 'MM':
             return tf(t.getMonth() + 1);
             break;
         case 'mm':
             return tf(t.getMinutes());
             break;
         case 'dd':
             return tf(t.getDate());
             break;
         case 'HH':
             return tf(t.getHours());
             break;
         case 'ss':
             return tf(t.getSeconds());
             break;
     }
 })
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<br>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse"
							style="display: block; text-decoration: none;"
							href="#searchRole"> 搜索 </a>
					</h4>
				</div>
				<div id="searchRole" class="panel-collapse collapse">
					<div class="panel-body">
						<form id="Role_searchForm">
							<div class="form-group input-group">
								<span class="input-group-addon">角色名称</span> <input type="text"
									class="form-control" style="width: 200px;" name="rolename" />
							</div>
							<div style="float: right;">
								<input type="button" value="搜索" id="Role_search"
									class="btn btn-Default" /> <input type="button" value="清空"
									id="Role_searchReset" class="btn btn-Default" />
							</div>
						</form>
					</div>
				</div>
			</div>
		 <!--功能菜单 -->
			<div id="Role_toolbar">			
				<input type="button" class="btn btn-primary" value="新增"
					id="Role_add">
			</div>
			<table id="Role_table">
			</table>
		</div>
		<!--第一层模态框  -->
		<div class="modal fade" id="Role_Modal" tabindex="-1" role="dialog"
			aria-labelledby="Role_ModalTitle" aria-hidden="true">
			<div class="modal-dialog" style="width: 600x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Role_ModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form id="Role_form" class="form-horizontal">
						   <input type='hidden' name='roleid' id='hiddenID'>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>角色姓名：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='rolename'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>角色描述：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='description'>
               </div>
             </div>
																																									
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="Role_close">关闭</button>
						<button type="button" class="btn btn-primary" id="Role_submit">提交</button>
					</div>
				</div>
			</div>
		</div>
    <!-- daoru -->
    <div class="modal fade" id="Role_daoru" tabindex="-1" role="dialog"
      aria-labelledby="Role_ModalTitle" aria-hidden="true">
      <div class="modal-dialog" style="width: 600x;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
              aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="Role_ModalTitle"></h4>
          </div>
          <div class="modal-body">
            <form enctype="multipart/form-data" id="batchUpload"  action="user/upload" method="post" class="form-horizontal">    
              <button class="btn btn-success btn-xs" id="uploadEventBtn" style="height:26px;"  type="button" >选择文件</button>  
              <input type="file" name="file"  style="width:0px;height:0px;" id="uploadEventFile">  
              <input id="uploadEventPath"  disabled="disabled"  type="text" placeholder="请选择excel表" style="border: 1px solid #e6e6e6; height: 26px;width: 200px;" >                                           
            </form> 
    <button type="button" class="btn btn-success btn-sm"  onclick="user.uploadBtn()" >上传</button>  
          </div>
        </div>
      </div>
    </div>
    <div  class="modal fade" id="Role_permission_Modal" tabindex="-1"
      role="dialog" aria-labelledby="Role_permission_ModalTitle"
      aria-hidden="true">    
       <div class="modal-dialog" style="width: 580x;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
              aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="Role_permission_ModalTitle">修改权限  — (<span id="rname" style="color:red"></span>)</h4>
          </div>
          <div class="modal-body" id="load_func">
              <ul id="treeDemo" class="ztree"></ul>
          </div> 
           
          <div class="modal-footer">
            <button class="btn btn-default" onclick="ztreecommit()">保存</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
  </div>
      
      
      
   <div class="modal fade" id="Role_view_Modal" tabindex="-1"
			role="dialog" aria-labelledby="Role_view_ModalTitle"
			aria-hidden="true">
			<div class="modal-dialog" style="width: 580x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Role_view_ModalTitle">查看用户详情</h4>
					</div>
					<div class="modal-body">
						<form id="Role_view_form" class="form-horizontal">
							             <div class='form-group'> 
             <label class='col-sm-3 control-label'>id：</label> 
               <div class='col-sm-9'> 
                 <label name='roleid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>角色姓名：</label> 
               <div class='col-sm-9'> 
                 <label name='rolename' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>角色描述：</label> 
               <div class='col-sm-9'> 
                 <label name='description' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
												
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
