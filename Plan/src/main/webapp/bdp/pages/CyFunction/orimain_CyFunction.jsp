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

#CyFunction_view_form .col-sm-9 label {
	font-weight: normal;
}

#CyFunction_form .col-sm-9 label {
	font-weight: normal;
}
.infoHead {
            font-family: 微软雅黑;
            font-size: 1px;
            font-weight: bold;
            font-style: normal;
            text-decoration: none;
            color: White;
            background-color: #434952;
            width: 100%;
            float: left;
            text-align: left;
            line-height: 30px;
            background-color: rgb(217,217,217);
            font-size: 20px;
            font-weight: bold;
            color: black;
        }
.infoBody100 {
            font-family: 微软雅黑;
            font-size: 16px;
            font-weight: normal;
            font-style: normal;
            text-decoration: none;
            color: #333333;
            width: 100%;
            float: left;
            text-align: left;
            line-height: 32px;
            background-color: rgb(217,217,217);
        }        
.infoBody101 {
            font-family: 微软雅黑;
            font-size: 16px;
            font-weight: normal;
            font-style: normal;
            text-decoration: none;
            color: #333333;
            width: 100%;
            float: left;
            text-align: left;
            line-height: 32px;
        }
.infoBodyScdLmt {
            font-size: 16px;
            text-align: left;
        }                
</style>
<script type="text/javascript">
/* js By CHANG */
function chooseLimit(obj, type) {

    var id = $(obj).attr("class");

    switch (type) {
        //顶级
        case 0:
            {
                var dt = id.split('_');
                //var bb = $('#txtFunc input[type="checkbox"]');
                //选中div下的所有checkbox
                $('#txtFunc input[type="checkbox"]').each(
            function () {
            	//var cc = $(this).attr("class");
                var list = $(this).attr("class").split('_');
                if (dt[0] == list[0]) {
                    if ($(obj).attr("checked")) {
                        $(this).attr("checked", "checked");
                    }
                    else {
                        $(this).attr("checked", false);
                    }
                }
            }
             );
            }
            break;

            //一级
        case 1:
            {
                var dt = id.split('_');

                //选中父集
                if ($(obj).attr("checked")) {
                    $("." + dt[0] + "_0_0").attr("checked", "checked");
                }

                //选中子集
                $('.' + dt[0] + '_0_0 input[type="checkbox"]').each(
            function () {
                var list = $(this).attr("class").split('_');
                if (dt[1] == list[1]) {
                    if ($(obj).attr("checked")) {
                        $(this).attr("checked", "checked");
                    }
                    else {
                        $(this).attr("checked", false)
                    }
                }
            }
             );
            }
            break;

            //二级
        case 2:
            var dt = id.split('_');
            if ($(obj).attr("checked")) {
                $("." + dt[0] + "_0_0").attr("checked", "checked");

                $("." + dt[0] + "_" + dt[1] + "_0").attr("checked", "checked");
            }
            break;
    }

}
$(function(){
	 $.ajax({
	        type: "post",
	        url: "getRoleList.do",
	        data: {},
	        dataType: "json",
	        success: function (data) {
	                    $("#txtFunc").html(data.func);
	               
	        }
	    });
});

/* js By CHANG END*/
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
global_variable.model = "CyFunction";
global_variable.model_cn = "权限管理";
global_variable.sortName="";
global_variable.columns =
[ 
 /* {field: 'state',checkbox:true}, */
 {field: 'id',title: 'id',formatter: function(value,row,index){return row.id;}}, 
 {field: 'functionid',title: 'functionid',formatter: function(value,row,index){return row.functionid;}}, 
 {field: 'pfunctionid',title: 'pfunctionid',formatter: function(value,row,index){return row.pfunctionid;}}, 
 {field: 'code',title: 'code',formatter: function(value,row,index){return row.code;}}, 
 {field: 'functionname',title: 'functionname',formatter: function(value,row,index){return row.functionname;}}, 
 {field: 'ordernum',title: 'ordernum',formatter: function(value,row,index){return row.ordernum;}}, 
 {field: 'releasetime',title: 'releasetime',formatter: function(value,row,index){return row.releasetime;}}, 
 {field: 'deleted',title: 'deleted',formatter: function(value,row,index){return row.deleted;}}, 
 {field: 'functionlevel',title: 'functionlevel',formatter: function(value,row,index){return row.functionlevel;}}, 
 {field: 'moudlename',title: 'moudlename',formatter: function(value,row,index){return row.moudlename;}}, 
	
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
							href="#searchCyFunction"> 搜索 </a>
					</h4>
				</div>
				<div id="searchCyFunction" class="panel-collapse collapse">
					<div class="panel-body">
						<form id="CyFunction_searchForm">
							<div class="form-group input-group">
								<span class="input-group-addon">条件</span> <input type="text"
									class="form-control" style="width: 200px;" name="" />
							</div>
							<div style="float: right;">
								<input type="button" value="搜索" id="CyFunction_search"
									class="btn btn-Default" /> <input type="button" value="清空"
									id="CyFunction_searchReset" class="btn btn-Default" />
							</div>
						</form>
					</div>
				</div>
			</div>
      
      <div id="txtFunc">122</div>
		 <!--功能菜单 -->
			<div id="CyFunction_toolbar">			
				<input type="button" class="btn btn-primary" value="新增"
					id="CyFunction_add">
			</div>
			<table id="CyFunction_table">
			</table>
		</div>
		<!--第一层模态框  -->
		<div class="modal fade" id="CyFunction_Modal" tabindex="-1" role="dialog"
			aria-labelledby="CyFunction_ModalTitle" aria-hidden="true">
			<div class="modal-dialog" style="width: 600x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="CyFunction_ModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form id="CyFunction_form" class="form-horizontal">
						   <input type='hidden' name='id' id='hiddenID'>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>functionid：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='functionid'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>pfunctionid：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='pfunctionid'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>code：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='code'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>functionname：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='functionname'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>ordernum：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='ordernum'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>releasetime：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='releasetime'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>deleted：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='deleted'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>functionlevel：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='functionlevel'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-3 control-label'><span style='color:red'>*</span>moudlename：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='moudlename'>
               </div>
             </div>
																																									
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="CyFunction_close">关闭</button>
						<button type="button" class="btn btn-primary" id="CyFunction_submit">提交</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="CyFunction_view_Modal" tabindex="-1"
			role="dialog" aria-labelledby="CyFunction_view_ModalTitle"
			aria-hidden="true">
			<div class="modal-dialog" style="width: 580x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="CyFunction_view_ModalTitle">查看用户详情</h4>
					</div>
					<div class="modal-body">
						<form id="CyFunction_view_form" class="form-horizontal">
							             <div class='form-group'> 
             <label class='col-sm-3 control-label'>id：</label> 
               <div class='col-sm-9'> 
                 <label name='id' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>functionid：</label> 
               <div class='col-sm-9'> 
                 <label name='functionid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>pfunctionid：</label> 
               <div class='col-sm-9'> 
                 <label name='pfunctionid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>code：</label> 
               <div class='col-sm-9'> 
                 <label name='code' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>functionname：</label> 
               <div class='col-sm-9'> 
                 <label name='functionname' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>ordernum：</label> 
               <div class='col-sm-9'> 
                 <label name='ordernum' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>releasetime：</label> 
               <div class='col-sm-9'> 
                 <label name='releasetime' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>deleted：</label> 
               <div class='col-sm-9'> 
                 <label name='deleted' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>functionlevel：</label> 
               <div class='col-sm-9'> 
                 <label name='functionlevel' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>moudlename：</label> 
               <div class='col-sm-9'> 
                 <label name='moudlename' style='padding-top: 6px;'></label> 
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
