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

#AdPlace_view_form .col-sm-9 label {
	font-weight: normal;
}

#AdPlace_form .col-sm-9 label {
	font-weight: normal;
}
</style>
<script type="text/javascript">
$(function(){
	checkWebSite();
	GetCity();
	GetBanners();
	/* $.ajax({
		 async : false,
		 type: 'POST',
         data:null,
		 url:"EditAdPlaceToChuanYang.do",
		 dataType:"json",
		 contentType:"application/json",
		 error: function (result) {//请求失败处理函数
			 alert("请求失败");
		  },
		 success:function(result){ //请求成功后处理函数。 
			alert(result.msg);
			object_operate.success();
		 }
	 });  */
	
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
            '</a>&nbsp;',
            ' <a class="search"  href="javascript:void(0)" title="查看素材库">',
            '<i class="glyphicon glyphicon-search"></i>',
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
   },
   'click .search': function (e, value, row, index) {
   	object_operate.search(row);
  }
};
global_variable.model = "AdPlace";
global_variable.model_cn = "广告位管理";
global_variable.sortName="";
global_variable.columns =
[ 
 {field: '',checkbox:true},
 {field: 'id',title: 'id',formatter: function(value,row,index){return row.id;}}, 
 {field: 'name',title: '名称',formatter: function(value,row,index){return row.name;}}, 
 {field: 'width',title: '宽度',formatter: function(value,row,index){return row.width;}}, 
 {field: 'height',title: '高度',formatter: function(value,row,index){return row.height;}}, 
 {field: 'isdeleted',title: '是否删除',formatter: function(value,row,index){return row.isdeleted;}}, 
 {field: 'addtime',title: '添加时间',formatter: function(value,row,index){return row.addtime;}}, 
 {field: 'adderid',title: '添加人id',formatter: function(value,row,index){return row.adderid;}}, 
 {field: 'state',title: '状态',formatter: function(value,row,index){return row.state;}}, 
 {field: 'webid',title: '网站id',formatter: function(value,row,index){return row.webid;}}, 
 {field: 'channelid',title: '频道id',formatter: function(value,row,index){return row.channelid;}}, 
 {field: 'bannerid',title: 'bannerid',formatter: function(value,row,index){return row.bannerid;}}, 
 {field: 'url',title: '链接',formatter: function(value,row,index){return row.url;}}, 
 {field: 'remarks',title: '备注',formatter: function(value,row,index){return row.remarks;}}, 
/*  {field: 'companygroupid',title: '集团id',formatter: function(value,row,index){return row.companygroupid;}}, 
 {field: 'companygroupshort',title: '集团简称',formatter: function(value,row,index){return row.companygroupshort;}},  */
 {field: 'companygroupname',title: '集团名称',formatter: function(value,row,index){return row.companygroupname;}}, 
/*  {field: 'cityid',title: '城市id',formatter: function(value,row,index){return row.cityid;}}, 
 {field: 'cityshort',title: '城市简称',formatter: function(value,row,index){return row.cityshort;}},  */
 {field: 'cityname',title: '城市名称',formatter: function(value,row,index){return row.cityname;}}, 
	
 /* {field: 'creatDate',sortable:true,title: '创建时间',formatter: function(value,row,index){
	 return  dateFormat(row.creatDate, 'yyyy-MM-dd HH:mm:ss');}}, */
 {field: 'operate',title: '操作',events: operateEvents,formatter: operateFormatter}
]		
global_variable.onLoadSuccess=function(result){
}
global_variable.showExport = false;
object_operate.search=function(obj){
	$("#search_material").modal("show");
	ajaxHander("../Banner/queryBannerByPage.do",{bannertype:6,adPlaceId:obj.id,sort:"",limit:"10",offset:"1"},"POST"); 
	$("#adPlaceId").val(obj.id);
}
$(document).ready(function(){
	//新增按钮
	$("#Material_add").click(function(){
		$("#add_material").modal("show");
	});
	//提交按钮
	$("#material_submit").click(function(){
		var data=$("#material_form").serialize();
		//alert(data);
		if(data!=undefined){
	    	ajaxHander("../Banner/addBanner.do",data,"POST"); 
	    	$("#add_material").modal("hide"); 
	    	$("#search_material").modal("hide");
	    	}
	});
	$("#startTime").datetimepicker({
		language:"cn",
	    weekStart:1,
	    todayBtn:1,
	    autoclose:1,
	    todayHighlight:1,
	    startView:2,
	    minView:2,
	    forceParse:0,
	    format:"yyyy-mm-dd"
        });
	$("#endTime").datetimepicker({
		language:"cn",
	    weekStart:1,
	    todayBtn:1,
	    autoclose:1,
	    todayHighlight:1,
	    startView:2,
	    minView:2,
	    forceParse:0,
	    format:"yyyy-mm-dd"
        });
});

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
        alert(data);
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
				 if(url.indexOf("queryBannerByPage")>0){
				     $("#Material_table").empty();
					 var html = "<tr><td>ID</td><td>图片url</td><td>开始时间</td><td>结束时间</td><td>标签</td><td>操作</td></tr>";
					 var row=data.rows;
					 if(row.length<5){
						 $("#warning").css("display","block");
					 }
					 for(var i=0;i<row.length;i++){
							if(row[i].bannertype == 6){
								  html +="<tr><td>"+row[i].id
								 +"</td><td>"+row[i].bannerurl+"</td><td>"+row[i].startTime+"</td><td>"
								 +row[i].endTime+"</td><td>"+row[i].label+"</td><td>&nbsp;<a title='编辑'><i class='glyphicon glyphicon-edit' onclick='updateMar("+row[i].id+")'></i></a>"
								 +"&nbsp;<a title='删除' onclick='delMar("+row[i].id+")'><i class='glyphicon glyphicon-remove-circle'></i></a></td>";
        						 
							
						 } 
						
					 }
					 $("#Material_table").append(html);
				 }else{
					 alert(data.msg);
					 object_operate.success();
				 }
			 }
		 }); 
}
function delMar(id){
	var ids=[];
	ids.push(id);
	$.ajax({
		 async : false,
		 type: 'POST',
        data:JSON.stringify(ids),
		 url:"../Banner/deleteBanner.do",
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
	$("#search_material").modal("hide");
	
}
function updateMar(id){
	$.ajax({
		 async : false,
		 type: "POST",
         data:{id:id},
         url:"../Banner/selectById.do",
		 success:function(data){
			 var obj=data;
			 $("#material_form input[name='id']").val(obj.id);
			  $("#material_form input[name='adPlaceId']").val(obj.adPlaceId);
			  $("#material_form input[name='bannerurl']").val(obj.bannerurl);
			  $("#material_form input[name='startTime']").val(obj.startTime);
			  $("#material_form input[name='endTime']").val(obj.endTime);
			  $("#material_form input[name='label']").val(obj.label);
			  $("#add_material").modal("show");
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
			 //$("#WebList").append(data);
			 //object_operate.success();
		 }
	 }); 
	}
	
function GetChannel()
{
	alert("GetChannel");
	alert($("#channelid").val());
	alert($('#channelid option:selected').val());
	$.ajax({
		 async : false,
		 type: 'post',
		 beforeSend: function(){				 
			    },
         data:{
        	 id:$("#WebList").val()
         },
		 url:'GetChannel.do',
		 success:function(data){ //请求成功后处理函数。 
			 setSelect("#channelid",data,"channel");
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
				if(type=="modal"){
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
function webchange()
{
	
	GetChannel();
	$.ajax({
		 async : false,
		 type: 'post',
         data:
        	 {
        	   id:$("#WebList").val()
        	 },
		 url:'GetWebInfo.do',
		 success:function(data){ //请求成功后处理函数。 
			 alert(data.websitename);
			 $('input[name="companygroupid"').val(data.sitegroupid);       
			 $('input[name="companygroupshort"').val(data.sitegrouppy);
			 $('input[name="companygroupname"').val(data.sitegroupname);
			 $('input[name="cityid"').val(data.id);
			 $('input[name="cityshort"').val(data.websitepy);
			 $('input[name="cityname"').val(data.sitegroupname);
			 //setSelect("#WebList",data,"adplace");
			 //$("#WebList").append(data);
			 //object_operate.success();
		 }
	 }); 
	
}

function GetBanners()
{
	$.ajax({
		 async : false,
		 type: 'post',
		 beforeSend: function(){				 
			    },
        data:null,
		 url:contextPath + "/SizeInfo/getAllSize.do",
		 success:function(data){ //请求成功后处理函数。 
			 setSelect("#bannerids",data,"modal");
			 //$("#WebList").append(data);
			 //object_operate.success();
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
							href="#searchAdPlace"> 搜索 </a>
					</h4>
				</div>
				<div id="searchAdPlace" class="panel-collapse collapse">
					<div class="panel-body">
						<form id="AdPlace_searchForm">
							<div class="form-group input-group">
								<span class="input-group-addon">广告位名称</span> <input type="text"
									class="form-control" style="width: 200px;" name="name" />
							</div>
							<div style="float: right;">
								<input type="button" value="搜索" id="AdPlace_search"
									class="btn btn-Default" /> <input type="button" value="清空"
									id="AdPlace_searchReset" class="btn btn-Default" />
							</div>
						</form>
					</div>
				</div>
			</div>
		 <!--功能菜单 -->
			<div id="AdPlace_toolbar">			
				<input type="button" class="btn btn-primary" value="新增"
					id="AdPlace_add" >
			</div>
			<table id="AdPlace_table">
			</table>
		</div>
		<!--第一层模态框  -->
		<div class="modal fade" id="AdPlace_Modal" tabindex="-1" role="dialog"
			aria-labelledby="AdPlace_ModalTitle" aria-hidden="true">
			<div class="modal-dialog" style="width: 600x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="AdPlace_ModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form id="AdPlace_form" class="form-horizontal">
						   <input type='hidden' name='id' id='hiddenID'>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>名称：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='name'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>宽度：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='width'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>高度：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='height'>
               </div>
             </div>
             <!-- <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>是否删除：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='isdeleted'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>添加时间：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='addtime'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>添加人id：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='adderid'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>状态：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='state'>
               </div>
             </div> -->
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>网站id：</label>
               <div class='col-sm-9'>
                <select class='form-control' name='webid' id="WebList" onchange="webchange()">
                
                </select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>频道id：</label>
               <div class='col-sm-9'>
               <select class='form-control' name='channelid' id='channelid'></select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>模板：</label>
               <div class='col-sm-9'>
                 <select class='form-control' name='bannerid' id="bannerids"></select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>链接：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='url'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>备注：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='remarks'>
               </div>
             </div>
             <div class='form-group' style="display:none;">
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>集团id：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='companygroupid' style="display:none;">
               </div>
             </div>
             <div class='form-group' style="display:none;">
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>集团简称：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='companygroupshort' style="display:none;">
               </div>  
             </div> 
             <div class='form-group' style="display:none;">
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>集团名称：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='companygroupname' style="display:none;">
               </div>
             </div>
             <div class='form-group' style="display:none;">
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>城市：</label>
               <div class='col-sm-9'>
                 <!-- <input class='form-control' name='cityid' style="display:none;"> -->
                  <select class='form-control' name='cityid' id='cityid'></select>
               </div>
             </div>
             <div class='form-group' style="display:none;">
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>城市简称：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='cityshort' style="display:none;">
               </div>
             </div>
             <div class='form-group' style="display:none;">
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>城市名称：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='cityname' style="display:none;">
               </div>
             </div>
																																									
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="AdPlace_close">关闭</button>
						<button type="button" class="btn btn-primary" id="AdPlace_submit">提交</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="AdPlace_view_Modal" tabindex="-1"
			role="dialog" aria-labelledby="AdPlace_view_ModalTitle"
			aria-hidden="true">
			<div class="modal-dialog" style="width: 580x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="AdPlace_view_ModalTitle">查看广告位详情</h4>
					</div>
					<div class="modal-body">
						<form id="AdPlace_view_form" class="form-horizontal">
							             <div class='form-group'> 
             <label class='col-sm-3 control-label'>id：</label> 
               <div class='col-sm-9'> 
                 <label name='id' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>名称：</label> 
               <div class='col-sm-9'> 
                 <label name='name' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>宽度：</label> 
               <div class='col-sm-9'> 
                 <label name='width' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>高度：</label> 
               <div class='col-sm-9'> 
                 <label name='height' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>是否删除：</label> 
               <div class='col-sm-9'> 
                 <label name='isdeleted' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>添加时间：</label> 
               <div class='col-sm-9'> 
                 <label name='addtime' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>添加人id：</label> 
               <div class='col-sm-9'> 
                 <label name='adderid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>状态：</label> 
               <div class='col-sm-9'> 
                 <label name='state' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>网站id：</label> 
               <div class='col-sm-9'> 
                 <label name='webid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>频道id：</label> 
               <div class='col-sm-9'> 
                 <label name='channelid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>模板：</label> 
               <div class='col-sm-9'> 
                 <label name='bannerid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>链接：</label> 
               <div class='col-sm-9'> 
                 <label name='url' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>备注：</label> 
               <div class='col-sm-9'> 
                 <label name='remarks' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>集团id：</label> 
               <div class='col-sm-9'> 
                 <label name='companygroupid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>集团简称：</label> 
               <div class='col-sm-9'> 
                 <label name='companygroupshort' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>集团名称：</label> 
               <div class='col-sm-9'> 
                 <label name='companygroupname' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>城市id：</label> 
               <div class='col-sm-9'> 
                 <label name='cityid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>城市简称：</label> 
               <div class='col-sm-9'> 
                 <label name='cityshort' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>城市名称：</label> 
               <div class='col-sm-9'> 
                 <label name='cityname' style='padding-top: 6px;'></label> 
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
     <!--  -->
    <div class="modal fade" id="search_material" tabindex="-1" role="dialog"
      aria-labelledby="AdPlace_ModalTitle" aria-hidden="true">
      <div class="modal-dialog" style="width: 1200x;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
              aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="AdPlace_ModalTitle">查看素材库</h4>
          </div>
          <div class="modal-body">
               <input type="button" class="btn btn-primary" value="新增" id="Material_add" >
            <table class="table table-bordered table-striped" style="margin-top:8px;" id="Material_table">
              
            </table>
            
             <span style='color:red;display:none' id="warning">警告：本素材库的素材少于5个！！！！</span>
          </div>
         </div>
        </div>
      </div>
      
      <div class="modal fade" id="add_material" tabindex="-1" role="dialog"
      aria-labelledby="AdPlace_ModalTitle" aria-hidden="true">
      <div class="modal-dialog" style="width: 1200x;">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"
              aria-hidden="true">&times;</button>
            <h4 class="modal-title" >新增打底素材</h4>
          </div>
          <div class="modal-body">
          <form id="material_form" class="form-horizontal">
             <input type='hidden' name='id' id='hiddenMID'>
             <input type='hidden' name='adPlaceId' id="adPlaceId">
             <input type='hidden' name='bannertype' value="6">
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>图片链接：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='bannerurl'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>开始时间：</label>
               <div class='col-sm-9'>
                 <input class='form-control ' name='startTime' id="startTime">
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>结束时间：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='endTime' id='endTime'>
               </div>
             </div>
              <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>标签：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='label'>
               </div>
             </div>
             </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
            <button type="button" class="btn btn-primary" id="material_submit">提交</button>
          </div>
         </div>
        </div>
      </div>
	</div>
</body>
</html>
