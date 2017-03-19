<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<%@include file="../../commons/statics.jsp"%>
<style type="text/css">
html,body{height:100%;}
.bootstrap-table td {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	max-width: 200px;
}

#AdPlacePrice_view_form .col-sm-9 label {
	font-weight: normal;
}

#AdPlacePrice_form .col-sm-9 label {
	font-weight: normal;
}
</style>
<script type="text/javascript">
$(function(){
	checkWebSite();
	
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
global_variable.model = "AdPlacePrice";
global_variable.model_cn = "刊例价管理";
global_variable.sortName="";
global_variable.columns =
[ 
 {field: '',checkbox:true},
 {field: 'id',title: 'id',formatter: function(value,row,index){return row.id;}}, 
 {field: 'companygroup',title: '集团',formatter: function(value,row,index){return row.companygroup;}}, 
 {field: 'websiteid',title: '网站',formatter: function(value,row,index){return row.websiteid;}}, 
 {field: 'channelid',title: '频道',formatter: function(value,row,index){return row.channelid;}}, 
 {field: 'platform',title: '平台',formatter: function(value,row,index){return row.platform;}}, 
 {field: 'adplaceid',title: '广告位',formatter: function(value,row,index){return row.adplaceid;}}, 
 {field: 'originality',title: '创意',formatter: function(value,row,index){return row.originality;}}, 
 {field: 'priceyear',title: '年份',formatter: function(value,row,index){return row.priceyear;}}, 
 {field: 'price',title: '价格',formatter: function(value,row,index){return row.price;}}, 
 {field: 'state',title: '状态',formatter: function(value,row,index){return row.state;}},
 {field: 'addtime',title: '添加时间',formatter: function(value,row,index){return row.addTime;}}, 
	
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
				 alert(data.msg);
				 object_operate.success();
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
function checkWebSite()
{
	$.ajax({
		 async : false,
		 type: 'post',
		 beforeSend: function(){				 
			    },
         data:null,
		 url:contextPath + "/Channelgroup/getWebsiteByUser.do",
		 success:function(data){ //请求成功后处理函数。 
			 setSelect("#WebList",data,"adplace");
			 setSelect("#ConditionWebSite",data,"adplace");
			 //$("#WebList").append(data);
			 //object_operate.success();
		 }
	 }); 
	}
	
function GetChannel()
{
	$.ajax({
		 async : false,
		 type: 'post',
		 beforeSend: function(){				 
			    },
         data:{
        	 id:$("#WebList").val()
         },
		 url:contextPath+"/AdPlace/GetChannel.do",
		 success:function(data){ //请求成功后处理函数。 
			 setSelect("#channelid",data,"channel");
			 //$("#WebList").append(data);
			 //object_operate.success();
		 }
	 }); 
	}
function GetChannel2()
{
	$.ajax({
		 async : false,
		 type: 'post',
		 beforeSend: function(){				 
			    },
         data:{
        	 id:$("#ConditionWebSite").val()
         },
		 url:contextPath+"/AdPlace/GetChannel.do",
		 success:function(data){ //请求成功后处理函数。 
			 setSelect("#ConditionChannel",data,"channel");
			 //$("#WebList").append(data);
			 //object_operate.success();
		 }
	 }); 
	}
	
function GetCity()
{
	$.ajax({
		 async : false,
		 type: 'post',
		 beforeSend: function(){				 
			    },
         data:null,
		 url:'GetCitys.do',
		 success:function(data){ //请求成功后处理函数。 
			 setSelect("#cityid",data,"city");
			 //$("#WebList").append(data);
			 //object_operate.success();
		 }
	 }); 
	}

function setSelect(id,obj,type){	 
		if($(id).val()==null){
			var len=obj.length;
		 	var temp="<option value=''>请选择</option>";
			for(var i=0 ;i<len;i++){
				if(type=="adplace"){
					temp+="<option value='"+obj[i].websiteid+"'>"+obj[i].websitename+"</option>";
				}
				if(type=="channel"){
					temp+="<option value='"+obj[i].id+"'>"+obj[i].channelgroupname+"</option>";
				}
				if(type=="city"){
					temp+="<option value='"+obj[i].CityId+"'>"+obj[i].CityName+"</option>";
				}
				if(type=="adplace2"){
					temp+="<option value='"+obj[i].id+"'>"+obj[i].name+"</option>";
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

function webchange(id)
{
	GetChannel();
  
}

function getAdplaceByCondition()
{
	$.ajax({
		 async : false,
		 type: 'post',
		 beforeSend: function(){				 
			    },
     data: {
   	  id:$("#WebList").val(),
   	  channelid:$("#channelid").val()
     },
		 url:'GetAdPlaceByWeb.do',
		 success:function(data){ //请求成功后处理函数。 
			 setSelect("#adplaceid",data,"adplace2");
			 //$("#WebList").append(data);
			 //object_operate.success();
		 }
	 }); 
}
	function ConditionGetAdPlace()
	{
		$.ajax({
			 async : false,
			 type: 'post',
			 beforeSend: function(){				 
				    },
	     data: {
	   	  id:$("#ConditionWebSite").val(),
	   	  channelid:$("#ConditionChannel").val()
	     },
			 url:'GetAdPlaceByWeb.do',
			 success:function(data){ //请求成功后处理函数。 
				 setSelect("#ConditionAdPlace",data,"adplace2");
				 //$("#WebList").append(data);
				 //object_operate.success();
			 }
		 }); 
	
	}
</script>
</head>
<body >
	<div class="container" >
		<div class="row">
			<br>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse"
							style="display: block; text-decoration: none;"
							href="#searchAdPlacePrice"> 搜索 </a>
					</h4>
				</div>
				<div id="searchAdPlacePrice" class="panel-collapse collapse">
					<div class="panel-body">
						<form id="AdPlacePrice_searchForm">
							<div class="form-group input-group" style="">
                             
                              <span class="input-group-addon">集团</span> <select class="form-control" style="width: 200px;" name="companygroup">
                                                                            <option value="">请选择</option>
                                                                            <option value="新房">新房</option>
                                                                            </select>
                                                                    
                                <span class="input-group-addon">投放平台</span> <select class="form-control" style="width: 200px;" name="platform">
                                                                            <option value="">请选择</option>
                                                                            <option value="wap">wap</option>
                                                                            </select> 
                                                                          
                                  <span class="input-group-addon">所属网站</span> <select class="form-control" onchange="GetChannel2()" style="width: 200px;" name="websiteid" id="ConditionWebSite">
                                                                           
                                                                            </select> 
                              </div>
                              <div class="form-group input-group" style="">
                                  <span class="input-group-addon">所属频道</span> <select class="form-control" style="width: 200px;" name="channelid" id="ConditionChannel" onchange="ConditionGetAdPlace()">
                                                                           
                                                                            </select> 
                                                                                                        
                                   <span class="input-group-addon">广告位</span> <select class="form-control" style="width: 200px;" name="adplaceid" id="ConditionAdPlace">
                                                                            
                                                                         </select>
                                                                         
                                   <span class="input-group-addon">创意形式</span> <select class="form-control" style="width: 200px;" name="originality">
                                                                            <option value="">请选择</option>
                                                                            <option value="文字">文字</option>
                                                                         </select> 
                               </div>    
                                <div class="form-group input-group" style="">
                                <span class="input-group-addon">刊例价</span> <input type="text" class="form-control" style="width: 200px;" name="pricestart">&nbsp-&nbsp<input type="text" class="form-control" style="width: 200px;" name="priceend">元/0.5天
                                <input type="button" value="搜索" id="AdPlacePrice_search" class="btn btn-Default" /> 
                                <input type="button" value="清空" id="AdPlacePrice_searchReset" class="btn btn-Default" />
                                
                                </div>                                                                                                                                                                           
							<!-- <div style="float: right;">
								
							</div> -->
						</form>
					</div>
				</div>
			</div>
		 <!--功能菜单 -->
			<div id="AdPlacePrice_toolbar">			
				<input type="button" class="btn btn-primary" value="新增"
					id="AdPlacePrice_add">
			</div>
			<table id="AdPlacePrice_table">
			</table>
		</div>
		<!--第一层模态框  -->
		<div class="modal fade" id="AdPlacePrice_Modal" tabindex="-1" role="dialog"
			aria-labelledby="AdPlacePrice_ModalTitle" aria-hidden="true">
			<div class="modal-dialog" style="width: 600x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="AdPlacePrice_ModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form id="AdPlacePrice_form" class="form-horizontal">
						   <input type='hidden' name='id' id='hiddenID'>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>集团：</label>
               <div class='col-sm-9'>
                 <select class='form-control' name='companygroup'>
                 <option>新房</option>
                 <option>二手房</option>
                 <option>家居</option>
                 </select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>网站：</label>
               <div class='col-sm-9'>
                 
                 <select class='form-control' name='websiteid' id="WebList" onchange="webchange()"></select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>频道：</label>
               <div class='col-sm-9'>
                 <select class='form-control' name='channelid' id='channelid' onchange="getAdplaceByCondition()"></select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>广告位：</label>
               <div class='col-sm-9'>
                 <select class='form-control' name='adplaceid' id='adplaceid'></select>
               </div>
             </div>
             
             
             
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>平台：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='platform'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>创意：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='originality'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>年份：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='priceyear'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>价格：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='price'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>是否付费：</label>
               <div class='col-sm-9'>
                  <select class='form-control' name='price'>
                  <option value='1'>付费</option>
                  <option value='0'>免费</option>
                  </select>
               </div>
             </div>
																																									
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="AdPlacePrice_close">关闭</button>
						<button type="button" class="btn btn-primary" id="AdPlacePrice_submit">提交</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="AdPlacePrice_view_Modal" tabindex="-1"
			role="dialog" aria-labelledby="AdPlacePrice_view_ModalTitle"
			aria-hidden="true">
			<div class="modal-dialog" style="width: 580x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="AdPlacePrice_view_ModalTitle">查看用户详情</h4>
					</div>
					<div class="modal-body">
						<form id="AdPlacePrice_view_form" class="form-horizontal">
							             <div class='form-group'> 
             <label class='col-sm-3 control-label'>id：</label> 
               <div class='col-sm-9'> 
                 <label name='id' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>集团：</label> 
               <div class='col-sm-9'> 
                 <label name='companygroup' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>网站：</label> 
               <div class='col-sm-9'> 
                 <label name='websiteid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>频道：</label> 
               <div class='col-sm-9'> 
                 <label name='channelid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>平台：</label> 
               <div class='col-sm-9'> 
                 <label name='platform' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>广告位：</label> 
               <div class='col-sm-9'> 
                 <label name='adplaceid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>创意：</label> 
               <div class='col-sm-9'> 
                 <label name='originality' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>年份：</label> 
               <div class='col-sm-9'> 
                 <label name='priceyear' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>价格：</label> 
               <div class='col-sm-9'> 
                 <label name='price' style='padding-top: 6px;'></label> 
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
