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

#depUser_view_form .col-sm-9 label {
	font-weight: normal;
}

#depUser_form .col-sm-9 label {
	font-weight: normal;
}
</style>
<script type="text/javascript">
var contextPath="<%=contextPath%>";
function addForm(){
	var data = {};
	data.email = $("#txtEmail").val()+"@fang.com";
	ajaxHander(contextPath + "/UserInit/addForm.do",data,"POST");
}
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
global_variable.model = "depUser";
global_variable.model_cn = "用户管理";
global_variable.sortName="oauserID";
global_variable.columns = [{field: 'state',checkbox:true},
							{field: 'oaUserID',title: '编号',formatter: function(value,row,index){
								 return row.oaUserID;}}, 
							{field: 'realName',title: '姓名',formatter: function(value,row,index){
								 return row.realName;}}, 
							{field: 'email',title: '邮箱',formatter: function(value,row,index){
							     return row.email;}},
							{field: 'groupName',title: '集团',formatter: function(value,row,index){
								     return row.groupName;}},
							{field: 'operate',title: '操作',events: operateEvents,formatter: operateFormatter}
							]		
global_variable.showExport = false;
object_operate.del=function(){
	var rows = $("#"+global_variable.model+"_table").bootstrapTable("getSelections");
	var ids = [];
	for( var o in rows) {
		ids.push(rows[o].uuid);
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
object_operate.show_modify_modal=function(obj){	
	$("#"+global_variable.model+"_submit").removeAttr('disabled');
	this.type = "modify";
	object_form_operate.setTitle();
	object_form_operate.setValue(obj);
	$("#"+global_variable.model+"_Modal").modal("show");
	$("input[name='email']").attr("readonly","readonly");
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
	$("#hUserID").val(obj.uuid);//设置修改主键
	for(var o in obj) {
		if($("#"+global_variable.model+"_form input[name='"+o+"']").attr('type') == 'radio'){
			$("#"+global_variable.model+"_form input[value='"+obj[o]+"']").attr("checked","checked");
		}
	   if($("#"+global_variable.model+"_form input[name='"+o+"']").attr('type') == 'text'||
		   $("#"+global_variable.model+"_form input[name='"+o+"']").attr('type') == undefined){
		   $("#"+global_variable.model+"_form input[name='"+o+"']").val(obj[o]);
		}
	   
	   if(o=="website"){
		   if(obj[o]!=null){
		    var values=obj[o];
		    var valuesArray = obj[o].split(",");
		        $('option', $('#cityIds')).each(function(element) {
		        	$(this).removeAttr('selected').prop('selected', false);
		        	for(var k=0;k<valuesArray.length;k++){	
		 				if(valuesArray[k]==$(this).val()){
		 					$(this).removeAttr('selected').prop('selected', true);
		 				}
		 			} 
	            });
		        $("#cityIds").multiselect('refresh');
		   }
	   }
	  
	   if(o=="roleIds"){
		   if(obj[o]!=null){
		    var values=obj[o];
		    $('option', $('#roleIds')).each(function(element) {
	        	$(this).removeAttr('selected').prop('selected', false);
	        	for(var k=0;k<values.length;k++){	
	 				if(values[k]==$(this).val()){
	 					$(this).removeAttr('selected').prop('selected', true);
	 				}
	 			} 
            });
	        $("#roleIds").multiselect('refresh');
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
				 if(url.indexOf("groupwebsite.do")>0){
					 setSelect("#cityIds",data,"city");//设置内容
					 setMultiselect("#cityIds");//初始化多选
				 }
				 else if(url.indexOf("getAllRoleName.do")>0){
					 setSelect("#roleIds",data,"role");//设置内容
					 setMultiselect("#roleIds");//初始化多选
				 }else if(url.indexOf("addForm.do")>0){
					 if(data.success == undefined){
						 $("#txtrealName").val(data.lastname);
						 $("#txtuserName").val(data.lastname);
						 $("#txtoaUserID ").val(data.userid);
					 }
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
function daoru(){
	$("#User_daoru").modal("show");
}
var Upload = function(){  
    
    this.init = function(){  
          
        //模拟上传excel  
         $("#uploadEventBtn").unbind("click").bind("click",function(){  
             $("#uploadEventFile").click();  
         });  
         $("#uploadEventFile").bind("change",function(){  
             $("#uploadEventPath").attr("value",$("#uploadEventFile").val());  
         });    
    };  
    //点击上传按钮  
    this.uploadBtn = function(){  
        var uploadEventFile = $("#uploadEventFile").val();  
        if(uploadEventFile == ''){  
            alert("请选择excel,再上传");  
        }else if(uploadEventFile.lastIndexOf(".xls")<0){//可判断以.xls和.xlsx结尾的excel  
            alert("只能上传Excel文件");  
        }else{  
            var url = contextPath + "/UserInit/upload.do";  
            var formData = new FormData($("#batchUpload")[0]);  
            upload.sendAjaxRequest(url,'POST',formData);  
        }  
    };  
    this.sendAjaxRequest = function(url,type,data){  
        $.ajax({  
            url : url,  
            type : type,  
            data : data,  
            success : function(result) {  
            	alert(result.msg);
            	$("#User_daoru").modal("hide"); 
            },  
            error : function() {  
                alert( "excel上传失败");  
            },  
            cache : false,  
            contentType : false,  
            processData : false  
        });  
    };  
}  
      
  
var upload;  
$(document).ready(function(){
	ajaxHander(contextPath + "/Channelgroup/groupwebsite.do",{},"POST"); 
	ajaxHander("../Role/getAllRoleName.do",{},"POST"); 
	upload = new Upload();  
	upload.init();   
});



function setSelect(id,obj,type){	 
		if($(id).val()==null){
			var len=obj.length;
		 	var temp="";
			for(var i=0 ;i<len;i++){
				if(type=="city"){
					temp+="<option value='"+obj[i].sitegrouppy+"-"+obj[i].websitepy+"'>"+obj[i].sitegroupname+"/"+obj[i].websitename+"</option>";
				}
				if(type=="role"){
					temp+="<option value='"+obj[i].roleid+"'>"+obj[i].rolename+"</option>";
				}
			}
			$(id).html(temp);
		} 
}
function setMultiselect(id) {
		$(id).multiselect({
			maxHeight : 250,
			includeSelectAllOption: true,
			selectAllText: '全选',
			selectAllValue: '0',
			buttonText : function(options, select) {
				if (options.length === 0) {
					return '请选择';
				} else if (options.length > 3) {
					return '已选三个以上';
				} else {
					var labels = [];
					options.each(function() {
							labels.push($(this).html());
					});
					return labels.join(', ') + '';
				}
			}
		});
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
							href="#searchDepUser"> 搜索 </a>
					</h4>
				</div>
				<div id="searchDepUser" class="panel-collapse collapse">
					<div class="panel-body">
						<form id="depUser_searchForm">
							<div class="form-group input-group">
								<span class="input-group-addon">邮箱</span> <input type="text"
									class="form-control" style="width: 200px;" name="email" />
							</div>
							<div style="float: right;">
								<input type="button" value="搜索" id="depUser_search"
									class="btn btn-Default" /> <input type="button" value="清空"
									id="depUser_searchReset" class="btn btn-Default" />
							</div>
						</form>
					</div>
				</div>
			</div>
		 <!--功能菜单 -->
			<div id="depUser_toolbar">			
				<input type="button" class="btn btn-primary" value="添加用户"
					id="depUser_add">
                <input type="button" class="btn btn-primary" value="批量上传用户"
                    id="upload" onclick="daoru();">
			</div>
			<table id="depUser_table">
			</table>
		</div>
		<!--第一层模态框  -->
		<div class="modal fade" id="depUser_Modal" tabindex="-1" role="dialog"
			aria-labelledby="depUser_ModalTitle" aria-hidden="true">
			<div class="modal-dialog" style="width: 600x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="depUser_ModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form id="depUser_form" class="form-horizontal">
						    <input type="hidden" name="uuid" id='hUserID'>
							<div class="form-group">
								<label class="col-sm-3 control-label"><span style="color:red">*</span>Email：</label>
								<div class="col-sm-9">
									<input class="form-control" name="email" id="txtEmail" onblur="addForm()">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><span style="color:red">*</span>姓名：</label>
								<div class="col-sm-9">
									<input class="form-control" name="realName" id="txtrealName" readonly="readonly">
								</div>
							</div>
                            <div class="form-group">
                              <label class="col-sm-3 control-label"><span style="color:red">*</span>OAUserId：</label>
                              <div class="col-sm-9">
                                <input class="form-control" name="oaUserID" id="txtoaUserID" readonly="readonly">
                              </div>
                            </div>
                            <div class="form-group">
                              <label class="col-sm-3 control-label"><span style="color:red">*</span>用户名：</label>
                              <div class="col-sm-9">
                                <input class="form-control" name="userName" id="txtuserName" readonly="readonly">
                              </div>
                            </div> 
                            <div class="form-group">
                              <label class="col-sm-3 control-label"><span style="color:red">*</span>集团：</label>
                              <div class="col-sm-9">
                                <input class="form-control" name="groupName" >
                              </div>
                            </div>  
                            <div class="form-group">
                              <label class="col-sm-3 control-label"><span style="color:red">*</span>角色名称：</label>
                              <div class="col-sm-9">
                                <select name="roleIds" id="roleIds" multiple="multiple">
                                   
                                </select>
                              </div>
                            </div> 
                            
                              <div class="form-group">
                              <label class="col-sm-3 control-label"><span style="color:red">*</span>投放城市：</label>
                              <div class="col-sm-9">
                                <select name="website"   multiple="multiple" id="cityIds">
                                   
                                </select>
                              </div>
                            </div>    																																										
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="depUser_close">关闭</button>
						<button type="button" class="btn btn-primary" id="depUser_submit">提交</button>
					</div>
				</div>
			</div>
		</div>
<div class="modal fade" id="User_daoru" tabindex="-1" role="dialog"
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
    <button type="button" class="btn btn-success btn-sm"  onclick="upload.uploadBtn()" >上传</button>  
          </div>
        </div>
      </div>
    </div>
		<div class="modal fade" id="depUser_view_Modal" tabindex="-1"
			role="dialog" aria-labelledby="depUser_view_ModalTitle"
			aria-hidden="true">
			<div class="modal-dialog" style="width: 580x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="depUser_view_ModalTitle">查看用户详情</h4>
					</div>
					<div class="modal-body">
						<form id="depUser_view_form" class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-3 control-label">邮箱：</label>
								<div class="col-sm-9">
									<label name="email" style="padding-top: 6px;" ></label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">姓名：</label>
								<div class="col-sm-9">
									<label name="realName" style="padding-top: 6px;"></label>
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
