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

#HolidayDiscount_view_form .col-sm-9 label {
	font-weight: normal;
}

#HolidayDiscount_form .col-sm-9 label {
	font-weight: normal;
}
</style>
<script type="text/javascript">
$(function(){
	total();
	
	
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
global_variable.model = "HolidayDiscount";
global_variable.model_cn = "xx管理";
global_variable.sortName="";
global_variable.columns =
[ 
 {field: '',checkbox:true},
 {field: 'id',title: 'id',formatter: function(value,row,index){return row.id;}}, 
 {field: 'companyshort',title: 'companyshort',formatter: function(value,row,index){return row.companyshort;}}, 
 {field: 'cityid',title: 'cityid',formatter: function(value,row,index){return row.cityid;}}, 
 {field: 'channelid',title: 'channelid',formatter: function(value,row,index){return row.channelid;}}, 
 {field: 'adplaceid',title: 'adplaceid',formatter: function(value,row,index){return row.adplaceid;}}, 
 {field: 'configyear',title: 'configyear',formatter: function(value,row,index){return row.configyear;}}, 
 {field: 'configmonth',title: 'configmonth',formatter: function(value,row,index){return row.configmonth;}}, 
 {field: 'configday',title: 'configday',formatter: function(value,row,index){return row.configday;}}, 
 {field: 'weekdaydiscount',title: 'weekdaydiscount',formatter: function(value,row,index){return row.weekdaydiscount;}}, 
 {field: 'weekenddiscount',title: 'weekenddiscount',formatter: function(value,row,index){return row.weekenddiscount;}}, 
 {field: 'holidaydiscount',title: 'holidaydiscount',formatter: function(value,row,index){return row.holidaydiscount;}}, 
 {field: 'discountpriority',title: 'discountpriority',formatter: function(value,row,index){return row.discountpriority;}}, 
 {field: 'showorder',title: 'showorder',formatter: function(value,row,index){return row.showorder;}}, 
	
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
function total()
{
	$.ajax({
		 async : false,
		 type: "post",
		 beforeSend: function(){				 
			    },
        data:null,
		 url:"AllMoney.do",
		 success:function(data){ //请求成功后处理函数。 				
			 alert(data.msg);
			 object_operate.success();
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
							href="#searchHolidayDiscount"> 搜索 </a>
					</h4>
				</div>
				<div id="searchHolidayDiscount" class="panel-collapse collapse">
					<div class="panel-body">
						<form id="HolidayDiscount_searchForm">
							<div class="form-group input-group">
								<span class="input-group-addon">条件</span> <input type="text"
									class="form-control" style="width: 200px;" name="" />
							</div>
							<div style="float: right;">
								<input type="button" value="搜索" id="HolidayDiscount_search"
									class="btn btn-Default" /> <input type="button" value="清空"
									id="HolidayDiscount_searchReset" class="btn btn-Default" />
							</div>
						</form>
					</div>
				</div>
			</div>
		 <!--功能菜单 -->
			<div id="HolidayDiscount_toolbar">			
				<input type="button" class="btn btn-primary" value="新增"
					id="HolidayDiscount_add">
			</div>
			<table id="HolidayDiscount_table">
			</table>
		</div>
		<!--第一层模态框  -->
		<div class="modal fade" id="HolidayDiscount_Modal" tabindex="-1" role="dialog"
			aria-labelledby="HolidayDiscount_ModalTitle" aria-hidden="true">
			<div class="modal-dialog" style="width: 600x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="HolidayDiscount_ModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form id="HolidayDiscount_form" class="form-horizontal">
						   <input type='hidden' name='id' id='hiddenID'>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>companyshort：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='companyshort'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>cityid：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='cityid'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>channelid：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='channelid'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>adplaceid：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='adplaceid'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>configyear：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='configyear'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>configmonth：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='configmonth'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>configday：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='configday'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>weekdaydiscount：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='weekdaydiscount'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>weekenddiscount：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='weekenddiscount'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>holidaydiscount：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='holidaydiscount'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>discountpriority：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='discountpriority'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>showorder：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='showorder'>
               </div>
             </div>
																																									
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="HolidayDiscount_close">关闭</button>
						<button type="button" class="btn btn-primary" id="HolidayDiscount_submit">提交</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="HolidayDiscount_view_Modal" tabindex="-1"
			role="dialog" aria-labelledby="HolidayDiscount_view_ModalTitle"
			aria-hidden="true">
			<div class="modal-dialog" style="width: 580x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="HolidayDiscount_view_ModalTitle">查看用户详情</h4>
					</div>
					<div class="modal-body">
						<form id="HolidayDiscount_view_form" class="form-horizontal">
							             <div class='form-group'> 
             <label class='col-sm-3 control-label'>id：</label> 
               <div class='col-sm-9'> 
                 <label name='id' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>companyshort：</label> 
               <div class='col-sm-9'> 
                 <label name='companyshort' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>cityid：</label> 
               <div class='col-sm-9'> 
                 <label name='cityid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>channelid：</label> 
               <div class='col-sm-9'> 
                 <label name='channelid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>adplaceid：</label> 
               <div class='col-sm-9'> 
                 <label name='adplaceid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>configyear：</label> 
               <div class='col-sm-9'> 
                 <label name='configyear' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>configmonth：</label> 
               <div class='col-sm-9'> 
                 <label name='configmonth' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>configday：</label> 
               <div class='col-sm-9'> 
                 <label name='configday' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>weekdaydiscount：</label> 
               <div class='col-sm-9'> 
                 <label name='weekdaydiscount' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>weekenddiscount：</label> 
               <div class='col-sm-9'> 
                 <label name='weekenddiscount' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>holidaydiscount：</label> 
               <div class='col-sm-9'> 
                 <label name='holidaydiscount' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>discountpriority：</label> 
               <div class='col-sm-9'> 
                 <label name='discountpriority' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>showorder：</label> 
               <div class='col-sm-9'> 
                 <label name='showorder' style='padding-top: 6px;'></label> 
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
