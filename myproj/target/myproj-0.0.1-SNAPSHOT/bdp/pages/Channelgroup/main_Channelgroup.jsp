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

#Channelgroup_view_form .col-sm-9 label {
	font-weight: normal;
}

#Channelgroup_form .col-sm-9 label {
	font-weight: normal;
}
</style>
<script type="text/javascript">
function setSelect(id,obj,type){	 
	if($(id).val()==null){
	 	var temp = "";
		for(var i=0; i<obj.length; i++){
			if(type == "sitegroup"){
				temp += "<option value='"+obj[i].websiteid+"'>"+obj[i].sitegroupname+"/"+obj[i].websitename+"</option>";
			}
			
		}
		$(id).html(temp);
	} 
}
$(function(){
	ajaxHander(contextPath + "/Channelgroup/groupwebsite.do",{},"POST");  

});
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
            '</a>'
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
   }
};
global_variable.model = "Channelgroup";
global_variable.model_cn = "频道管理";
global_variable.sortName="";
global_variable.columns =
[ 
 {field: 'state',checkbox:true},
 {field: 'id',title: 'id',formatter: function(value,row,index){return row.id;}}, 
 {field: 'channelgroupname',title: '频道名称',formatter: function(value,row,index){return row.channelgroupname;}}, 
 {field: 'status',title: '有效性',formatter: function(value,row,index){
	 if(row.status == 0)
		 return '无效';
	 else if(row.status == 1)
		 return '有效';
	 else if(row.status == 2)
		 return '归档';
	 }}, 
 {field: 'websitename',title: '网站名称',formatter: function(value,row,index){return row.websitename;}},
 {field: 'url',title: '页面地址',formatter: function(value,row,index){return row.url;}}, 
 {field: 'sitegroupname',title:'集团名称',formatter: function(value,row,index){return row.sitegroupname;}},  
 {field: 'addperson',title: '添加人',formatter: function(value,row,index){return row.addperson;}}, 
 {field: 'addtime',title: '添加时间',formatter: function(value,row,index){return row.addtime;}}, 
	
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
	$("#addhiddenID").val(0);//设置默认值
	object_form_operate.reset();
	$("#"+global_variable.model+"_addModal").modal("show");
	$("#"+global_variable.model+"_addsubmit").unbind("click");
	$("#"+global_variable.model+"_addsubmit").bind("click",function(){
		object_operate.add();
		});	
}
object_operate.add=function(){
        var data= checkFormadd();
        if(data!=undefined){
        	ajaxHander("add"+object_name+".do",data,"POST"); 
        }
}
object_operate.show_modify_modal=function(obj){	
	$("#"+global_variable.model+"_modifysubmit").removeAttr('disabled');
	this.type = "modify";
	object_form_operate.setTitle();
	object_form_operate.setValue(obj);
	$("#"+global_variable.model+"_modifyModal").modal("show");
	$("#"+global_variable.model+"_modifysubmit").unbind("click");
	$("#"+global_variable.model+"_modifysubmit").bind("click",function(){
		object_operate.modify();
		});
	$("#"+global_variable.model+"_close").bind("click",function(){
		});
}
object_operate.modify=function(){	
	 var data= checkFormmodify();
	 if(data!=undefined){
		 ajaxHander("modify"+object_name+".do",data,"POST"); 
	 }
}
object_form_operate.setValue=function(obj){	
	var k=0;//获取第一列
	for(var i in obj){
		if(k==0){
			$("#modifyhiddenID").val(obj[i]);//设置修改主键
		}
		k++;
	}
	
	for(var o in obj) {
		if($("#"+global_variable.model+"_modifyform input[name='"+o+"']").attr('type') == 'radio'){
			$("#"+global_variable.model+"_modifyform input[value='"+obj[o]+"']").attr("checked","checked");
		}
	   if($("#"+global_variable.model+"_modifyform input[name='"+o+"']").attr('type') == 'text'||
		   $("#"+global_variable.model+"_modifyform input[name='"+o+"']").attr('type') == undefined||$("#"+global_variable.model+"_modifyform input[name='"+o+"']").attr('type') == 'hidden'){
		   if(o=="status"){
			   if(obj[o]==0){
				   $("#status").find("option[value=0]").attr("selected",true);
			   }else if(obj[o]==1){
				   $("#status").find("option[value=1]").attr("selected",true);
			   }else{
				   $("#status").find("option[value=2]").attr("selected",true); 
			   }
		   }else{
			   $("#"+global_variable.model+"_modifyform input[name='"+o+"']").val(obj[o]);
		   }
		 
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
	$("#"+global_variable.model+"_addform")[0].reset();
	$("#"+global_variable.model+"_addform input[type='hidden']").val("");
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
				 if(url.indexOf("groupwebsite")>0){
					 setSelect("#sitegroup",data,"sitegroup");//设置内容
				 }
				 else{
					 alert(data.msg);
					 object_operate.success();
				 }
			 }
		 }); 
}
	
function checkFormadd(){		
	    //验证
		return $("#"+global_variable.model+"_addform").serialize();		
}
function checkFormmodify(){		
    //验证
	return $("#"+global_variable.model+"_modifyform").serialize();		
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
							href="#searchChannelgroup"> 搜索 </a>
					</h4>
				</div>
				<div id="searchChannelgroup" class="panel-collapse collapse">
					<div class="panel-body">
						<form id="Channelgroup_searchForm">
							<div class="form-group input-group">
								<span class="input-group-addon">频道名称</span> <input type="text"
									class="form-control" style="width: 200px;" name="channelgroupname" />
							</div>
							<div style="float: right;">
								<input type="button" value="搜索" id="Channelgroup_search"
									class="btn btn-Default" /> <input type="button" value="清空"
									id="Channelgroup_searchReset" class="btn btn-Default" />
							</div>
						</form>
					</div>
				</div>
			</div>
		 <!--功能菜单 -->
			<div id="Channelgroup_toolbar">			
				<input type="button" class="btn btn-primary" value="新增"
					id="Channelgroup_add">
			</div>
			<table id="Channelgroup_table">
			</table>
		</div>
		<!--第一层模态框  -->
		<div class="modal fade" id="Channelgroup_addModal" tabindex="-1" role="dialog"
			aria-labelledby="Channelgroup_ModalTitle" aria-hidden="true">
			<div class="modal-dialog" style="width: 600x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Channelgroup_ModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form id="Channelgroup_addform" class="form-horizontal">
						   <input type='hidden' name='id' id='addhiddenID'>
                              <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>网站名称：</label>
               <div class='col-sm-9'>
                 <select id="sitegroup" name="websiteid" class="form-control" style="width:25%;"></select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>频道名称：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='channelgroupname'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>有效性：</label>
               <div class='col-sm-9'>
                 <select name='status' class="form-control" style="width:25%;">
                   <option value="1">有效</option>
                   <option value="0">无效</option>
                   <option value="2">归档</option>
                 </select>
               </div>
             </div>


             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>页面地址：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='url'>
               </div>
             </div>

																																									
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="Channelgroup_close">关闭</button>
						<button type="button" class="btn btn-primary" id="Channelgroup_addsubmit">提交</button>
					</div>
				</div>
			</div>
		</div>
    
        <div class="modal fade" id="Channelgroup_modifyModal" tabindex="-1" role="dialog"
      aria-labelledby="Channelgroup_ModalTitle" aria-hidden="true">
      <div class="modal-dialog" style="width: 600x;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
              aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="Channelgroup_ModalTitle"></h4>
          </div>
          <div class="modal-body">
            <form id="Channelgroup_modifyform" class="form-horizontal">
               <input type='hidden' name='id' id='modifyhiddenID'>
                <input type='hidden' name='sitegroupid' >
               <input type='hidden' name='sitegrouppy'>
               <input type='hidden' name='websiteid' >
               <input type='hidden' name='websitepy' >
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>集团名称：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='sitegroupname' readonly="readonly">
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>网站名称：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='websitename' readonly="readonly">
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>频道名称：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='channelgroupname'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>有效性：</label>
               <div class='col-sm-9'>
                   <select name='status' id="status" class="form-control" style="width:25%;">
                     <option value="1">有效</option>
                     <option value="0">无效</option>
                     <option value="2">归档</option>
                   </select>
               </div>
             </div>


             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>页面地址：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='url'>
               </div>
             </div>

                                                                                  
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal"
              id="Channelgroup_close">关闭</button>
            <button type="button" class="btn btn-primary" id="Channelgroup_modifysubmit">提交</button>
          </div>
        </div>
      </div>
    </div>

		<div class="modal fade" id="Channelgroup_view_Modal" tabindex="-1"
			role="dialog" aria-labelledby="Channelgroup_view_ModalTitle"
			aria-hidden="true">
			<div class="modal-dialog" style="width: 580x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Channelgroup_view_ModalTitle">查看频道详情</h4>
					</div>
					<div class="modal-body">
			<form id="Channelgroup_view_form" class="form-horizontal">
	             <div class='form-group'> 
             <label class='col-sm-3 control-label'>所属集团：</label> 
               <div class='col-sm-9'> 
                 <label name='sitegroupname' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>所属网站：</label> 
               <div class='col-sm-9'> 
                 <label name='websitename' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>频道名称：</label> 
               <div class='col-sm-9'> 
                 <label name='channelgroupname' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>有效性：</label> 
               <div class='col-sm-9'> 
                 <label name='status' style='padding-top: 6px;'></label> 
               </div> 
             </div> 

             <div class='form-group'> 
             <label class='col-sm-3 control-label'>页面地址：</label> 
               <div class='col-sm-9'> 
                 <label name='url' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>添加人：</label> 
               <div class='col-sm-9'> 
                 <label name='addperson' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>添加时间：</label> 
               <div class='col-sm-9'> 
                 <label name='addtime' style='padding-top: 6px;'></label> 
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
