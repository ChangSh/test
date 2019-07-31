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

#Banner_view_form .col-sm-9 label {
	font-weight: normal;
}

#Banner_form .col-sm-9 label {
	font-weight: normal;
}
</style>
<script type="text/javascript">
var contextPath="<%=contextPath%>";
var modalCode="";
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
global_variable.model = "Banner";
global_variable.model_cn = "xx管理";
global_variable.sortName="";
global_variable.columns =
[ 
 {field: '',checkbox:true},
 {field: 'id',title: 'id',formatter: function(value,row,index){return row.id;}}, 
 {field: 'name',title: '名称',formatter: function(value,row,index){return row.name;}}, 
 {field: 'orderid',title: '所属订单',formatter: function(value,row,index){return row.orderid;}}, 
 {field: 'weight',title: '权重',formatter: function(value,row,index){return row.weight;}}, 
 {field: 'targeturl',title: '点击地址',formatter: function(value,row,index){return row.targeturl;}}, 
 {field: 'bannertype',title: '创意类型',formatter: function(value,row,index){
	 if(row.bannertype==1){
		 return "自定义";
	 }else if(row.bannertype==2){
		 return "图片";
	 }else if(row.bannertype==3){
		 return "flash";
	 }if(row.bannertype==4){
		 return "纯点击";
	 }else{
		 return "文字链";
	 }
	 }}, 
 {field: 'targetwindow',title: '目标窗口',formatter: function(value,row,index){
	 if(row.targetwindow==1){
		 return "当前窗口";
	 }else{
		 return "新窗口";
	 }
	}}, 
 {field: 'ifeffective',title: '是否有效',formatter: function(value,row,index){
	 if(row.ifeffective==1){
		 return "是";
	 }else{
		 return "否";
	 }
	 }}, 
 {field: 'ifsetsonbanner',title: '设置子创意',formatter: function(value,row,index){
	 if(row.ifsetsonbanner==1){
		 return "是";
	 }else{
		 return "否";
	 }
	 }}, 
 {field: 'bannerurl',title: '创意地址',formatter: function(value,row,index){return row.bannerurl;}}, 
 {field: 'ifusecustomsize',title: '使用自定义尺寸',formatter: function(value,row,index){
	 if(row.ifusecustomsize==1){
		 return "是";
	 }else{
		 return "否";
		
	 }
	 }}, 
 {field: 'width',title: '宽',formatter: function(value,row,index){return row.width;}}, 
 {field: 'height',title: '高',formatter: function(value,row,index){return row.height;}}, 
 {field: 'thirdmonitoring',title: '第三方曝光监测',formatter: function(value,row,index){return row.thirdmonitoring;}}, 
 {field: 'ifusediv',title: '使用DIV外框',formatter: function(value,row,index){
	 if(row.ifusediv==1){
		 return "是";
	 }else{
		 return "否";
	 }
	}}, 
 {field: 'modalid',title: 'modalid',formatter: function(value,row,index){return row.modalid;}}, 
 {field: 'ifuseclicktag',title: '支持clickTAG',formatter: function(value,row,index){return row.ifuseclicktag;}}, 
 {field: 'windowmode',title: '窗口模式',formatter: function(value,row,index){
	 if(row.windowmode==1){
		 return "透明";
	 }else if(row.windowmode==2){
		 return "不透明";
	 }else{
		 return "悬浮";
	 }
	 }}, 
 {field: 'alternatepictureurl',title: '备用图片地址',formatter: function(value,row,index){return row.alternatepictureurl;}}, 
 {field: 'modaltype',title: '模板类型',formatter: function(value,row,index){
	 if(row.modaltype==1){
		 return "标准";
	 }else{
		 return "自定义";
	 }
	 }}, 
 {field: 'texcontent',title: '文字内容',formatter: function(value,row,index){return row.texcontent;}}, 
 {field: 'ifusepagestyle',title: '跟随页面样式',formatter: function(value,row,index){
	 if(row.ifusepagestyle==1){
		 return "是";
	 }else{
		 return "否";
	 }
	 }}, 
 {field: 'font',title: '字体',formatter: function(value,row,index){return row.font;}}, 
 {field: 'fontsize',title: '字体大小',formatter: function(value,row,index){return row.fontsize;}}, 
 {field: 'ifunderline',title: '使用下划线',formatter: function(value,row,index){
	 if(row.ifunderline==1){
		 return "是";
	 }else{
		 return "否";
	 }
	}}, 
 {field: 'fontcolor',title: '字体颜色',formatter: function(value,row,index){return row.fontcolor;}}, 
 {field: 'mousecolor',title: '鼠标经过时的颜色',formatter: function(value,row,index){return row.mousecolor;}}, 
 {field: 'html',title: '自定义代码',formatter: function(value,row,index){return row.html;}}, 
	
 {field: 'operate',title: '操作',events: operateEvents,formatter: operateFormatter}
]		
global_variable.onLoadSuccess=function(result){
}
global_variable.showExport = false;
object_operate.del=function(){
	var rows = $("#"+global_variable.model+"_table").bootstrapTable("getSelections");
	var ids = [];
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
	var tempdata=[{a:2,b:2,c:'bannerurl'},{a:3,b:2,c:'ifusecustomsize'},{a:2,b:2,c:'width'},{a:2,b:2,c:'height'},{a:2,b:2,c:'thirdmonitoring'},
	              {a:3,b:2,c:'ifusediv'},{a:3,b:2,c:'ifuseclicktag'},{a:3,b:2,c:'windowmode'},{a:2,b:2,c:'alternatepictureurl'},{a:3,b:2,c:'modaltype'},
	              {a:2,b:2,c:'html'},{a:2,b:2,c:'texcontent'},{a:3,b:2,c:'ifusepagestyle'},{a:2,b:2,c:'font'},{a:2,b:2,c:'fontsize'}, {a:3,b:2,c:'ifunderline'},
	              {a:2,b:2,c:'fontcolor'},{a:2,b:2,c:'mousecolor'},{a:3,b:1,c:'modalid'}];
	for(var i=0;i<tempdata.length;i++){
		key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
	}

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
	//alert(modalCode);
	var html="";
	if(modalCode.substring(1, 7).indexOf("xml")>0){
	   html=bulidXml(modalCode);
	 }else{
		 html=bulidJs(modalCode);
	 }
	$("#" + global_variable.model +"_form input[name='html']").val(html);
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
object_form_operate.setValue=function(obj){	//修改时设置值
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
	showByType(obj.bannertype);
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
		if (o == "bannertype") {
			if (obj[o] != null) {
				var values = obj[o];
				if (values == 1) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='bannertype']")
							.text("自定义");
				} else if (values == 2) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='bannertype']")
							.text("图片");
				} else if (values == 3) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='bannertype']")
							.text("flash");
				} else if (values == 4) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='bannertype']")
							.text("纯点击");
				} else{
					$(
							"#" + global_variable.model
									+ "_view_form label[name='bannertype']")
							.text("文字链");
				}
			}
		}
		if (o == "targetwindow") {
			if (obj[o] != null) {
				var values = obj[o];
				if (values == 1) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='targetwindow']")
							.text("当前窗口");
				}  else{
					$(
							"#" + global_variable.model
									+ "_view_form label[name='targetwindow']")
							.text("新窗口");
				}
			}
		}
		if (o == "ifeffective") {
			if (obj[o] != null) {
				var values = obj[o];
				if (values == 1) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifeffective']")
							.text("是");
				}  else{
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifeffective']")
							.text("否");
				}
			}
		}
		if (o == "ifusecustomsize") {
			if (obj[o] != null) {
				var values = obj[o];
				if (values == 1) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifusecustomsize']")
							.text("是");
				}  else{
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifusecustomsize']")
							.text("否");
				}
			}
		}
		if (o == "ifusediv") {
			if (obj[o] != null) {
				var values = obj[o];
				if (values == 1) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifusediv']")
							.text("是");
				}  else{
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifusediv']")
							.text("否");
				}
			}
		}
		if (o == "ifuseclicktag") {
			if (obj[o] != null) {
				var values = obj[o];
				if (values == 1) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifuseclicktag']")
							.text("是");
				}  else{
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifuseclicktag']")
							.text("否");
				}
			}
		}
		if (o == "windowmode") {
			if (obj[o] != null) {
				var values = obj[o];
				if (values == 1) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='windowmode']")
							.text("透明");
				}else if (values == 2) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='windowmode']")
							.text("不透明");
				}  else{
					$(
							"#" + global_variable.model
									+ "_view_form label[name='windowmode']")
							.text("悬浮");
				}
			}
		}
		if (o == "modaltype") {
			if (obj[o] != null) {
				var values = obj[o];
				if (values == 1) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='modaltype']")
							.text("标准");
				}  else{
					$(
							"#" + global_variable.model
									+ "_view_form label[name='modaltype']")
							.text("自定义");
				}
			}
		}
		if (o == "ifusepagestyle") {
			if (obj[o] != null) {
				var values = obj[o];
				if (values == 1) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifusepagestyle']")
							.text("是");
				}  else{
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifusepagestyle']")
							.text("否");
				}
			}
		}
		if (o == "ifunderline") {
			if (obj[o] != null) {
				var values = obj[o];
				if (values == 1) {
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifunderline']")
							.text("是");
				}  else{
					$(
							"#" + global_variable.model
									+ "_view_form label[name='ifunderline']")
							.text("否");
				}
			}
		}
	}
    showDetail(obj.bannertype);
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
				 if(url.indexOf("getAllSize.do")>0){
					 setSelect("#modalids",data,"modal");//设置内容
				 }else if(url.indexOf("getSizeById.do")>0){
					 modalCode=data.html;
					 if(data.html.substring(1, 7).indexOf("xml")>0){
						 readXml(data.html,"#preview");
					 }else{
						 readJs(data.html,"#preview");
					 }
				 }else if(url=="getAllOrder.do"){
					 setSelect("#orderids",data,"order");//设置内容
					 
				 }else{
					 alert(data.msg);
					 object_operate.success(); 
				 }
			 }
		 }); 
}
function setSelect(id,obj,type){
	if($(id).val()==null){
		var len=obj.length;
	 	var temp="<option value='0'>请选择</option>";
		for(var i=0 ;i<len;i++){
			if(type=="modal"){
				temp+="<option value='"+obj[i].id+"'>"+obj[i].name+"</option>";
			}
			if(type=="order"){
				temp+="<option value='"+obj[i]+"'>"+obj[i]+"</option>";
			}
		}
		$(id).append(temp);
	} 
}
function getByModalId(id){
	ajaxHander("../SizeInfo/getSizeById.do",{id:id},"POST");
	
	
}
$(document).ready(function(){
	ajaxHander("../SizeInfo/getAllSize.do",{},"POST");
	ajaxHander("getAllOrder.do",{},"POST");
});
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
function key(type,showStyle,row){
	//type  1是bootstrap的显示隐藏    2input   3select 4label
	if(type==1){
		showStyle=showStyle==1?"showColumn":"hideColumn"
		$('#Banner_table').bootstrapTable(showStyle,row);
	}else if(type==2){
		if(showStyle==1){
			$("#" + global_variable.model + "_form input[name='"+row+"']").parent().parent().show();
		}else {

			$("#" + global_variable.model + "_form input[name='"+row+"']").parent().parent().hide();
		}
	}else if(type==3){
		if(showStyle==1){
			$("#" + global_variable.model + "_form select[name='"+row+"']").parent().parent().show();
		}else {

			$("#" + global_variable.model + "_form select[name='"+row+"']").parent().parent().hide();
		}
	}else{
		if(showStyle==1){
			$("#" + global_variable.model + "_view_form label[name='"+row+"']").parent().parent().show();
		}else {
			$("#" + global_variable.model + "_view_form label[name='"+row+"']").parent().parent().hide();
		}
	}
}
$(function() {
	var tempdata=[{a:1,b:2,c:'bannerurl'},{a:1,b:2,c:'ifusecustomsize'},{a:1,b:2,c:'width'},{a:1,b:2,c:'height'},{a:1,b:2,c:'thirdmonitoring'},{a:1,b:2,c:'ifusediv'},
	              {a:1,b:2,c:'ifuseclicktag'},{a:1,b:2,c:'windowmode'},{a:1,b:2,c:'alternatepictureurl'},{a:1,b:2,c:'modaltype'},{a:1,b:2,c:'html'},{a:1,b:2,c:'texcontent'},
	              {a:1,b:2,c:'ifusepagestyle'},{a:1,b:2,c:'font'},{a:1,b:2,c:'fontsize'}, {a:1,b:2,c:'ifunderline'},{a:1,b:2,c:'fontcolor'},{a:1,b:2,c:'mousecolor'}];
	for(var i=0;i<tempdata.length;i++){
		key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
	}
	
	
});
function showData(type){
	if(type == 1){//自定义
		var tempdata=[{a:1,b:2,c:'bannerurl'},{a:1,b:2,c:'ifusecustomsize'},{a:1,b:2,c:'width'},{a:1,b:2,c:'height'},{a:1,b:2,c:'thirdmonitoring'},{a:1,b:2,c:'ifusediv'},
		              {a:1,b:2,c:'ifuseclicktag'},{a:1,b:2,c:'windowmode'},{a:1,b:2,c:'alternatepictureurl'},{a:1,b:2,c:'modaltype'},{a:1,b:2,c:'html'},{a:1,b:2,c:'texcontent'},
		              {a:1,b:2,c:'ifusepagestyle'},{a:1,b:2,c:'font'},{a:1,b:2,c:'fontsize'}, {a:1,b:2,c:'ifunderline'},{a:1,b:2,c:'fontcolor'},{a:1,b:2,c:'mousecolor'},{a:1,b:1,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
		}
	}else if(type == 2){//图片
		var tempdata=[{a:1,b:1,c:'bannerurl'},{a:1,b:1,c:'ifusecustomsize'},{a:1,b:1,c:'width'},{a:1,b:1,c:'height'},{a:1,b:1,c:'thirdmonitoring'},{a:1,b:1,c:'ifusediv'},
		              {a:1,b:2,c:'ifuseclicktag'},{a:1,b:2,c:'windowmode'},{a:1,b:2,c:'alternatepictureurl'},{a:1,b:2,c:'modaltype'},{a:1,b:2,c:'html'},{a:1,b:2,c:'texcontent'},
		              {a:1,b:2,c:'ifusepagestyle'},{a:1,b:2,c:'font'},{a:1,b:2,c:'fontsize'}, {a:1,b:2,c:'ifunderline'},{a:1,b:2,c:'fontcolor'},{a:1,b:2,c:'mousecolor'},{a:1,b:2,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
		}
	}else if(type == 3){//flash
		var tempdata=[{a:1,b:1,c:'bannerurl'},{a:1,b:1,c:'ifusecustomsize'},{a:1,b:1,c:'width'},{a:1,b:1,c:'height'},{a:1,b:1,c:'thirdmonitoring'},{a:1,b:1,c:'ifusediv'},
		              {a:1,b:1,c:'ifuseclicktag'},{a:1,b:1,c:'windowmode'},{a:1,b:1,c:'alternatepictureurl'},{a:1,b:2,c:'modaltype'},{a:1,b:2,c:'html'},{a:1,b:2,c:'texcontent'},
		              {a:1,b:2,c:'ifusepagestyle'},{a:1,b:2,c:'font'},{a:1,b:2,c:'fontsize'}, {a:1,b:2,c:'ifunderline'},{a:1,b:2,c:'fontcolor'},{a:1,b:2,c:'mousecolor'},{a:1,b:2,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
		}
	}else if(type == 4){
		var tempdata=[{a:1,b:2,c:'bannerurl'},{a:1,b:2,c:'ifusecustomsize'},{a:1,b:2,c:'width'},{a:1,b:2,c:'height'},{a:1,b:2,c:'thirdmonitoring'},{a:1,b:2,c:'ifusediv'},
		              {a:1,b:2,c:'ifuseclicktag'},{a:1,b:2,c:'windowmode'},{a:1,b:2,c:'alternatepictureurl'},{a:1,b:2,c:'modaltype'},{a:1,b:2,c:'html'},{a:1,b:2,c:'texcontent'},
		              {a:1,b:2,c:'ifusepagestyle'},{a:1,b:2,c:'font'},{a:1,b:2,c:'fontsize'}, {a:1,b:2,c:'ifunderline'},{a:1,b:2,c:'fontcolor'},{a:1,b:2,c:'mousecolor'},{a:1,b:2,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
		}
	}else{
		var tempdata=[{a:1,b:2,c:'bannerurl'},{a:1,b:2,c:'ifusecustomsize'},{a:1,b:2,c:'width'},{a:1,b:2,c:'height'},{a:1,b:1,c:'thirdmonitoring'},{a:1,b:2,c:'ifusediv'},
		              {a:1,b:2,c:'ifuseclicktag'},{a:1,b:2,c:'windowmode'},{a:1,b:2,c:'alternatepictureurl'},{a:1,b:2,c:'modaltype'},{a:1,b:2,c:'html'},{a:1,b:1,c:'texcontent'},
		              {a:1,b:1,c:'ifusepagestyle'},{a:1,b:1,c:'font'},{a:1,b:1,c:'fontsize'}, {a:1,b:1,c:'ifunderline'},{a:1,b:1,c:'fontcolor'},{a:1,b:1,c:'mousecolor'},{a:1,b:2,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
		}
	}
}
function showDetail(type){
	if (type == 1) {//自定义
		var tempdata=[{a:4,b:2,c:'bannerurl'},{a:4,b:2,c:'ifusecustomsize'},{a:4,b:2,c:'width'},{a:4,b:2,c:'height'},{a:4,b:2,c:'thirdmonitoring'},
		              {a:4,b:2,c:'ifusediv'},{a:4,b:2,c:'ifuseclicktag'},{a:4,b:2,c:'windowmode'},{a:4,b:2,c:'alternatepictureurl'},{a:4,b:2,c:'modaltype'},
		              {a:4,b:2,c:'html'},{a:4,b:2,c:'texcontent'},{a:4,b:2,c:'ifusepagestyle'},{a:4,b:2,c:'font'},{a:4,b:2,c:'fontsize'}, {a:4,b:2,c:'ifunderline'},
		              {a:4,b:2,c:'fontcolor'},{a:4,b:2,c:'mousecolor'},{a:4,b:1,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
		}
	}else if(type ==2){//图片
			var tempdata=[{a:4,b:1,c:'bannerurl'},{a:4,b:1,c:'ifusecustomsize'},{a:4,b:1,c:'width'},{a:4,b:1,c:'height'},{a:4,b:1,c:'thirdmonitoring'},
			              {a:4,b:1,c:'ifusediv'},{a:4,b:2,c:'ifuseclicktag'},{a:4,b:2,c:'windowmode'},{a:4,b:2,c:'alternatepictureurl'},{a:4,b:2,c:'modaltype'},
			              {a:4,b:2,c:'html'},{a:2,b:4,c:'texcontent'},{a:4,b:2,c:'ifusepagestyle'},{a:4,b:2,c:'font'},{a:4,b:2,c:'fontsize'}, {a:4,b:2,c:'ifunderline'},
			              {a:4,b:2,c:'fontcolor'},{a:4,b:2,c:'mousecolor'},{a:4,b:2,c:'modalid'}];
			for(var i=0;i<tempdata.length;i++){
				key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
			}
	}else if(type ==3){
		var tempdata=[{a:4,b:1,c:'bannerurl'},{a:4,b:1,c:'ifusecustomsize'},{a:4,b:1,c:'width'},{a:4,b:1,c:'height'},{a:4,b:1,c:'thirdmonitoring'},
		              {a:4,b:1,c:'ifusediv'},{a:4,b:1,c:'ifuseclicktag'},{a:4,b:1,c:'windowmode'},{a:4,b:1,c:'alternatepictureurl'},{a:4,b:2,c:'modaltype'},
		              {a:4,b:2,c:'html'},{a:4,b:2,c:'texcontent'},{a:4,b:2,c:'ifusepagestyle'},{a:4,b:2,c:'font'},{a:4,b:2,c:'fontsize'}, {a:4,b:2,c:'ifunderline'},
		              {a:4,b:2,c:'fontcolor'},{a:4,b:2,c:'mousecolor'},{a:4,b:2,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
	     }
	}else if(type ==4){
		var tempdata=[{a:4,b:2,c:'bannerurl'},{a:4,b:2,c:'ifusecustomsize'},{a:4,b:2,c:'width'},{a:4,b:2,c:'height'},{a:4,b:2,c:'thirdmonitoring'},
		              {a:4,b:2,c:'ifusediv'},{a:4,b:2,c:'ifuseclicktag'},{a:4,b:2,c:'windowmode'},{a:4,b:2,c:'alternatepictureurl'},{a:4,b:2,c:'modaltype'},
		              {a:4,b:2,c:'html'},{a:4,b:2,c:'texcontent'},{a:4,b:2,c:'ifusepagestyle'},{a:4,b:2,c:'font'},{a:4,b:2,c:'fontsize'}, {a:4,b:2,c:'ifunderline'},
		              {a:4,b:2,c:'fontcolor'},{a:4,b:2,c:'mousecolor'},{a:4,b:2,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
	     }
	}else{
		var tempdata=[{a:4,b:2,c:'bannerurl'},{a:4,b:2,c:'ifusecustomsize'},{a:4,b:2,c:'width'},{a:4,b:2,c:'height'},{a:4,b:2,c:'thirdmonitoring'},
		              {a:4,b:2,c:'ifusediv'},{a:4,b:2,c:'ifuseclicktag'},{a:4,b:2,c:'windowmode'},{a:4,b:2,c:'alternatepictureurl'},{a:4,b:1,c:'modaltype'},
		              {a:4,b:2,c:'html'},{a:4,b:1,c:'texcontent'},{a:4,b:1,c:'ifusepagestyle'},{a:4,b:2,c:'font'},{a:4,b:1,c:'fontsize'}, {a:4,b:1,c:'ifunderline'},
		              {a:4,b:1,c:'fontcolor'},{a:4,b:1,c:'mousecolor'},{a:4,b:2,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
	     }
	}
}
function showByType(type){
	if (type == 1) {//自定义
		var tempdata=[{a:2,b:2,c:'bannerurl'},{a:3,b:2,c:'ifusecustomsize'},{a:2,b:2,c:'width'},{a:2,b:2,c:'height'},{a:2,b:2,c:'thirdmonitoring'},
		              {a:3,b:2,c:'ifusediv'},{a:3,b:2,c:'ifuseclicktag'},{a:3,b:2,c:'windowmode'},{a:2,b:2,c:'alternatepictureurl'},{a:3,b:2,c:'modaltype'},
		              {a:2,b:2,c:'html'},{a:2,b:2,c:'texcontent'},{a:3,b:2,c:'ifusepagestyle'},{a:2,b:2,c:'font'},{a:2,b:2,c:'fontsize'}, {a:3,b:2,c:'ifunderline'},
		              {a:2,b:2,c:'fontcolor'},{a:2,b:2,c:'mousecolor'},{a:3,b:1,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
		}
	}else if(type ==2){//图片
			var tempdata=[{a:2,b:1,c:'bannerurl'},{a:3,b:1,c:'ifusecustomsize'},{a:2,b:1,c:'width'},{a:2,b:1,c:'height'},{a:2,b:1,c:'thirdmonitoring'},
			              {a:3,b:1,c:'ifusediv'},{a:3,b:2,c:'ifuseclicktag'},{a:3,b:2,c:'windowmode'},{a:2,b:2,c:'alternatepictureurl'},{a:3,b:2,c:'modaltype'},
			              {a:2,b:2,c:'html'},{a:2,b:2,c:'texcontent'},{a:3,b:2,c:'ifusepagestyle'},{a:2,b:2,c:'font'},{a:2,b:2,c:'fontsize'}, {a:3,b:2,c:'ifunderline'},
			              {a:2,b:2,c:'fontcolor'},{a:2,b:2,c:'mousecolor'},{a:3,b:2,c:'modalid'}];
			for(var i=0;i<tempdata.length;i++){
				key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
			}
	}else if(type ==3){
		var tempdata=[{a:2,b:1,c:'bannerurl'},{a:3,b:1,c:'ifusecustomsize'},{a:2,b:1,c:'width'},{a:2,b:1,c:'height'},{a:2,b:1,c:'thirdmonitoring'},
		              {a:3,b:1,c:'ifusediv'},{a:3,b:1,c:'ifuseclicktag'},{a:3,b:1,c:'windowmode'},{a:2,b:1,c:'alternatepictureurl'},{a:3,b:2,c:'modaltype'},
		              {a:2,b:2,c:'html'},{a:2,b:2,c:'texcontent'},{a:3,b:2,c:'ifusepagestyle'},{a:2,b:2,c:'font'},{a:2,b:2,c:'fontsize'}, {a:3,b:2,c:'ifunderline'},
		              {a:2,b:2,c:'fontcolor'},{a:2,b:2,c:'mousecolor'},{a:3,b:2,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
	     }
	}else if(type ==4){
		var tempdata=[{a:2,b:2,c:'bannerurl'},{a:3,b:2,c:'ifusecustomsize'},{a:2,b:2,c:'width'},{a:2,b:2,c:'height'},{a:2,b:2,c:'thirdmonitoring'},
		              {a:3,b:2,c:'ifusediv'},{a:3,b:2,c:'ifuseclicktag'},{a:3,b:2,c:'windowmode'},{a:2,b:2,c:'alternatepictureurl'},{a:3,b:2,c:'modaltype'},
		              {a:2,b:2,c:'html'},{a:2,b:2,c:'texcontent'},{a:3,b:2,c:'ifusepagestyle'},{a:2,b:2,c:'font'},{a:2,b:2,c:'fontsize'}, {a:3,b:2,c:'ifunderline'},
		              {a:2,b:2,c:'fontcolor'},{a:2,b:2,c:'mousecolor'},{a:3,b:2,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
	     }
	}else{
		var tempdata=[{a:2,b:2,c:'bannerurl'},{a:3,b:2,c:'ifusecustomsize'},{a:2,b:2,c:'width'},{a:2,b:2,c:'height'},{a:2,b:2,c:'thirdmonitoring'},
		              {a:3,b:2,c:'ifusediv'},{a:3,b:2,c:'ifuseclicktag'},{a:3,b:2,c:'windowmode'},{a:2,b:2,c:'alternatepictureurl'},{a:3,b:1,c:'modaltype'},
		              {a:2,b:2,c:'html'},{a:2,b:1,c:'texcontent'},{a:3,b:1,c:'ifusepagestyle'},{a:2,b:2,c:'font'},{a:2,b:1,c:'fontsize'}, {a:3,b:1,c:'ifunderline'},
		              {a:2,b:1,c:'fontcolor'},{a:2,b:1,c:'mousecolor'},{a:3,b:2,c:'modalid'}];
		for(var i=0;i<tempdata.length;i++){
			key(tempdata[i].a,tempdata[i].b,tempdata[i].c);
	     }
	}
}
function showWH(value,id){
	if(value==0){
		$("#" + global_variable.model + id + " input[name='width']").parent().parent().hide();
		$("#" + global_variable.model + id + " input[name='height']").parent().parent().hide();
	}else{
		$("#" + global_variable.model + id + " input[name='width']").parent().parent().show();
		$("#" + global_variable.model + id + " input[name='height']").parent().parent().show();
	}
}
function showByModalType(value,id){
	if(value==1){//标准
		$("#" + global_variable.model + id + " input[name='html']").parent().parent().hide();
		$("#" + global_variable.model + id + " input[name='texcontent']").parent().parent().show();
		$("#" + global_variable.model + id + " select[name='ifusepagestyle']").parent().parent().show();
		$("#" + global_variable.model + id + " input[name='font']").parent().parent().show();
		$("#" + global_variable.model + id + " input[name='fontsize']").parent().parent().show();
		$("#" + global_variable.model + id + " select[name='ifunderline']").parent().parent().show();
		$("#" + global_variable.model + id + " input[name='fontcolor']").parent().parent().show();
		$("#" + global_variable.model + id + " input[name='mousecolor']").parent().parent().show();
	}else{//自定义
		$("#" + global_variable.model + id + " input[name='html']").parent().parent().show();
		$("#" + global_variable.model + id + " input[name='texcontent']").parent().parent().hide();
		$("#" + global_variable.model + id + " select[name='ifusepagestyle']").parent().parent().hide();
		$("#" + global_variable.model + id + " input[name='font']").parent().parent().hide();
		$("#" + global_variable.model + id + " input[name='fontsize']").parent().parent().hide();
		$("#" + global_variable.model + id + " select[name='ifunderline']").parent().parent().hide();
		$("#" + global_variable.model + id + " input[name='fontcolor']").parent().parent().hide();
		$("#" + global_variable.model + id + " input[name='mousecolor']").parent().parent().hide();
	}
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
							href="#searchBanner"> 搜索 </a>
					</h4>
				</div>
				<div id="searchBanner" class="panel-collapse collapse">
					<div class="panel-body">
						<form id="Banner_searchForm">
							<div class="form-group input-group">
								<select name='bannertype' id="banner_bannertype" onchange="showData(this.value);">
                                  <option value=1 checked>自定义</option>
                                  <option value=2>图片</option>
                                  <option value=3>flash</option>
                                  <option value=4>纯点击</option>
                                  <option value=5>文字链</option>
                                </select>
							</div>
							<div style="float: right;">
								<input type="button" value="搜索" id="Banner_search"
									class="btn btn-Default" /> <input type="button" value="清空"
									id="Banner_searchReset" class="btn btn-Default" />
							</div>
						</form>
					</div>
				</div>
			</div>
		 <!--功能菜单 -->
			<div id="Banner_toolbar">			
				<input type="button" class="btn btn-primary" value="新增"
					id="Banner_add">
			</div>
			<table id="Banner_table">
			</table>
		</div>
		<!--第一层模态框  -->
		<div class="modal fade" id="Banner_Modal" tabindex="-1" role="dialog"
			aria-labelledby="Banner_ModalTitle" aria-hidden="true">
			<div class="modal-dialog" style="width: 600x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Banner_ModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form id="Banner_form" class="form-horizontal">
						   <input type='hidden' name='id' id='hiddenID'>
             <div class='form-group'>
             <label class='col-sm-4 control-label'><span style='color:red'>*</span>名称：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='name'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'><span style='color:red'>*</span>所属投放id：</label>
               <div class='col-sm-8'>
               <!--  <input class='form-control' name='solutionid'> -->
                 <select name='orderid' id="orderids">
                    
                 </select>
               </div>
             </div>
             <!--  <div class='form-group'>
             <label class='col-sm-4 control-label'><span style='color:red'>*</span>所属创意组id：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='fid'>
               </div>
             </div> -->
             <div class='form-group'>
             <label class='col-sm-4 control-label'><span style='color:red'>*</span>权重：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='weight'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'><span style='color:red'>*</span>点击地址：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='targeturl'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'><span style='color:red'>*</span>创意类型：</label>
               <div class='col-sm-8'>
                 <select name='bannertype' id="banner_bannertype" onchange="showByType(this.value);">
                    <option value=1>自定义</option>
                    <option value=2>图片</option>
                    <option value=3>flash</option>
                    <option value=4>纯点击</option>
                    <option value=5>文字链</option>
                  </select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'><span style='color:red'>*</span>目标窗口：</label>
               <div class='col-sm-8'>
                  <select name='targetwindow' >
                    <option value=1>当前窗口</option>
                    <option value=2>新窗口</option>
                  </select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'><span style='color:red'>*</span>是否有效：</label>
               <div class='col-sm-8'>
                  <select name='ifeffective' >
                    <option value=1>有效</option>
                    <option value=0>无效</option>
                  </select>
               </div>
             </div>
            <!--  <div class='form-group'>
             <label class='col-sm-3 control-label'>是否设置为子创意：</label>
               <div class='col-sm-9'>
                 <input class='form-control' name='ifsetsonbanner'>
                  <select name='ifsetsonbanner'>
                    <option value=1>是</option>
                    <option value=0>否</option>
                  </select>
               </div>
             </div> -->
             <div class='form-group'>
             <label class='col-sm-4 control-label'>创意地址：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='bannerurl'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>是否使用自定义尺寸：</label>
               <div class='col-sm-8'>
                  <select name='ifusecustomsize' onchange="showWH(this.value,'_form');">
                    <option value=1>使用</option>
                    <option value=0>不使用</option>
                  </select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>宽度：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='width'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>高度：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='height'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>第三方曝光监测：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='thirdmonitoring'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>是否使用div外框：</label>
               <div class='col-sm-8'>
                 <select name='ifusediv' >
                    <option value=1>使用</option>
                    <option value=0>不使用</option>
                  </select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>使用用户模版：</label>
               <div class='col-sm-8'>
                <!--  <input class='form-control' name='modalid'> -->
                 <select name='modalid' id="modalids" onchange="getByModalId(this.value);">
                
                 </select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>是否支持clickTAG：</label>
               <div class='col-sm-8'>
                 <select name='ifuseclicktag' >
                    <option value=1>是</option>
                    <option value=0>否</option>
                  </select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>窗口模式：</label>
               <div class='col-sm-8'>
                 <select name='windowmode' >
                    <option value=1>透明</option>
                    <option value=2>不透明</option>
                    <option value=3>悬浮</option>
                  </select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>备用图片地址：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='alternatepictureurl'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>模板类型：</label>
               <div class='col-sm-8'>
                 <select name='modaltype' onchange="showByModalType(this.value,'_form')">
                    <option value=1>标准</option>
                    <option value=2>自定义</option>
                  </select>
               </div>
             </div>
             <div class='form-group' >
             <label class='col-sm-4 control-label'>自定义代码：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='html'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>文字内容：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='texcontent'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>是否跟随页面样式：</label>
               <div class='col-sm-8'>
                 <select name='ifusepagestyle' >
                    <option value=1>是</option>
                    <option value=0>否</option>
                  </select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>字体：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='font'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>字体大小：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='fontsize'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>是否使用下划线：</label>
               <div class='col-sm-8'>
                 <select name='ifunderline' >
                    <option value=1>是</option>
                    <option value=0>否</option>
                  </select>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>字体颜色：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='fontcolor'>
               </div>
             </div>
             <div class='form-group'>
             <label class='col-sm-4 control-label'>鼠标经过颜色：</label>
               <div class='col-sm-8'>
                 <input class='form-control' name='mousecolor'>
               </div>
             </div>																																	
			</form>
            <form class="form-horizontal">
             <div class='form-group' id="preview"></div> 
            </form>   
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="Banner_close">关闭</button>
						<button type="button" class="btn btn-primary" id="Banner_submit">提交</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="Banner_view_Modal" tabindex="-1"
			role="dialog" aria-labelledby="Banner_view_ModalTitle"
			aria-hidden="true">
			<div class="modal-dialog" style="width: 580x;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Banner_view_ModalTitle">查看用户详情</h4>
					</div>
					<div class="modal-body">
						<form id="Banner_view_form" class="form-horizontal">
							             <div class='form-group'> 
             <label class='col-sm-3 control-label'>id：</label> 
               <div class='col-sm-9'> 
                 <label name='id' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>创意名称：</label> 
               <div class='col-sm-9'> 
                 <label name='name' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>所属订单</label> 
               <div class='col-sm-9'> 
                 <label name='orderid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>权重：</label> 
               <div class='col-sm-9'> 
                 <label name='weight' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>点击地址：</label> 
               <div class='col-sm-9'> 
                 <label name='targeturl' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>创意类型：</label> 
               <div class='col-sm-9'> 
                 <label name='bannertype' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>目标窗口：</label> 
               <div class='col-sm-9'> 
                 <label name='targetwindow' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>是否有效：</label> 
               <div class='col-sm-9'> 
                 <label name='ifeffective' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>是否设置为子创意：</label> 
               <div class='col-sm-9'> 
                 <label name='ifsetsonbanner' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>创意地址：</label> 
               <div class='col-sm-9'> 
                 <label name='bannerurl' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>使用自定义尺寸：</label> 
               <div class='col-sm-9'> 
                 <label name='ifusecustomsize' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>宽：</label> 
               <div class='col-sm-9'> 
                 <label name='width' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>高：</label> 
               <div class='col-sm-9'> 
                 <label name='height' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>第三方曝光监测：</label> 
               <div class='col-sm-9'> 
                 <label name='thirdmonitoring' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>使用div：</label> 
               <div class='col-sm-9'> 
                 <label name='ifusediv' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>模板id：</label> 
               <div class='col-sm-9'> 
                 <label name='modalid' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>支持clickTAG：</label> 
               <div class='col-sm-9'> 
                 <label name='ifuseclicktag' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>窗口模式：</label> 
               <div class='col-sm-9'> 
                 <label name='windowmode' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>备用图片地址：</label> 
               <div class='col-sm-9'> 
                 <label name='alternatepictureurl' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>模板类型：</label> 
               <div class='col-sm-9'> 
                 <label name='modaltype' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>文字内容：</label> 
               <div class='col-sm-9'> 
                 <label name='texcontent' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>跟随页面样式：</label> 
               <div class='col-sm-9'> 
                 <label name='ifusepagestyle' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>字体：</label> 
               <div class='col-sm-9'> 
                 <label name='font' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>字体大小：</label> 
               <div class='col-sm-9'> 
                 <label name='fontsize' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>下划线：</label> 
               <div class='col-sm-9'> 
                 <label name='ifunderline' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>字体颜色：</label> 
               <div class='col-sm-9'> 
                 <label name='fontcolor' style='padding-top: 6px;'></label> 
               </div> 
             </div> 
             <div class='form-group'> 
             <label class='col-sm-3 control-label'>鼠标经过颜色：</label> 
               <div class='col-sm-9'> 
                 <label name='mousecolor' style='padding-top: 6px;'></label> 
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
