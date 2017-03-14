var global_variable = {
    model: "", //模块英文名字      必填
    model_cn: "", //模块中文名字      必填
    searchData: {}, //自定义搜索数据
    columns: [], //自定义列        必填
	init_validate_form: function() {}, //表单验证方法       必须
	sidePagination: "server", //分页方式，默认服务器端分页
	custom_search: true, //自定义查询
	sortName: "", //排序列名
	sortOrder: "asc", //排序方式
	multiSort: false, //多列排序开关
	sortNameArr: [], //多列排序列名
	sortOrderArr: [], //多列排序方式
	height: "", //高度
	showExport:false, //是否可导出，true可导出
	onLoadSuccess: function() {}, //加载成功的方法
	clickToSelect: true,//多选设置为true，单选甚至为false   add： cj  20160921
	showColumns:true, //选择显示列开关
	descColumnName: "name" //删除时同ids一起传递的字段信息 ","分割
}

var object_name = "";

var object_search_form_operate = {
    search: function() {
		$("#"+global_variable.model+"_table").bootstrapTable('beforeSearch');
		global_variable.searchData = $("#"+global_variable.model+"_searchForm").serializeJson(",");
		$("#"+global_variable.model+"_table").bootstrapTable('refresh', {});
	},
	reset: function() {
		$("#"+global_variable.model+"_searchForm")[0].reset();
		this.search();
	}   
}

var object_export_excel_operate = { 
     exportExcelAll: function(){ 
       var allparams={};
       allparams["searchText"]=global_variable.searchData.search;
       allparams["sortOrder"]=global_variable.sortOrder;
       allparams["sortName"]=global_variable.sortName;
	   window.location="exportExcelAll.do?"+ $.param(allparams)+"&"+$("#"+global_variable.model+"_searchForm").serialize(); 
	   //window.location="exportExcelAll.do"+$("#"+global_variable.model+"_searchForm").serialize();
	 
	}

}
	                
var object_form_operate = {
	setTitle: function() {
		if(object_operate.type == "add"){
			$("#"+global_variable.model+"_ModalTitle").html("增加"+global_variable.model_cn);
		}
		else if(object_operate.type == "modify") {
			$("#"+global_variable.model+"_ModalTitle").html("修改"+global_variable.model_cn);
		}
	},
	reset: function() {
		$("#"+global_variable.model+"_form")[0].reset();
		$("#"+global_variable.model+"_form input[type='hidden']").val("");
	},
	setValue: function(obj, parentName) {
		if(!parentName) {
			parentName = "";
		}else {
			parentName += ".";
		}
		for(var o in obj) {
			$("#"+global_variable.model+"_form :input[name='"+parentName+o+"']").val(obj[o]);
			if(obj[o] instanceof Object) {
				object_form_operate.setValue(obj[o],parentName+o);
			}
		}
	},
	setViewValue:function(obj, parentName){
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
}

var object_operate = {
    type: "",
    init: function() {
    	if($("#"+global_variable.model+"_table").length > 0) {
    		$("#"+global_variable.model+"_table").bootstrapTable({
    			url: "query"+object_name+"ByPage.do", 
    			queryParams:function(params){
    			  	params=$.extend(global_variable.searchData,params);
    			  	console.info(JSON.stringify(params));
    			  	//重置分页 兼容后台分页
    			  	params.offset=(params.offset/params.limit)+1;
    			  	return params;
    			},  
    			height:global_variable.height,
    			sidePagination: global_variable.sidePagination,
    			cache: false,
    			striped: true,
    			pagination: true,
    			pageSize: 10,
    			pageList: [10, 25, 50, 100, 200],
    			showColumns: true,
    			search: !global_variable.custom_search,
    			multiSort: global_variable.multiSort,
                sortName: global_variable.sortName,
    			sortOrder: global_variable.sortOrder,
    			minimumCountColumns: 2,
    			clickToSelect:global_variable.clickToSelect,
    			toolbar: "#"+global_variable.model+"_toolbar",
    			columns: global_variable.columns,
    			showColumns: global_variable.showColumns,
    			showExport: global_variable.showExport,
    			exportOptions: {
    				fileName: global_variable.model_cn+'信息表',
    				worksheetName: global_variable.model_cn+'信息表'
    			},
    			onLoadSuccess: global_variable.onLoadSuccess
    		}); 
    	}
		global_variable.init_validate_form();
    },
    show_add_modal: function() {
    	this.type = "add";
    	object_form_operate.setTitle();
    	object_form_operate.reset();
		$("#"+global_variable.model+"_Modal").modal("show");
		$("#"+global_variable.model+"_submit").unbind("click");
		$("#"+global_variable.model+"_submit").bind("click",function(){object_operate.add();});
    },
    add: function() {
    	if($("#"+global_variable.model+"_form").valid()){
    		var data = $("#"+global_variable.model+"_form").serialize();
    		$.post("add"+object_name+".do",data,function(result){
    			if(result.hasErrors) {
    				alert(result.errorMessage);
    			}else {
    				alert("增加成功");
    				object_operate.success();
    			}
    		});
    	}
    },
    show_modify_modal: function(obj) {
    	this.type = "modify";
    	object_form_operate.setTitle();
    	object_form_operate.reset();
    	object_form_operate.setValue(obj);
		$("#"+global_variable.model+"_Modal").modal("show");
		$("#"+global_variable.model+"_submit").unbind("click");
		$("#"+global_variable.model+"_submit").bind("click",function(){object_operate.modify()});
    },
    modify: function() {
    	if($("#"+global_variable.model+"_form").valid()){
	    	var data = $("#"+global_variable.model+"_form").serialize();
			$.post("modify"+object_name+".do",data,function(result){
				if(result.hasErrors) {
					alert(result.errorMessage);
				}else {
					alert("修改成功");
					object_operate.success();
				}
			});
		}
	},
	show_view_modal: function(obj) {
    	object_form_operate.setViewValue(obj);
		$("#"+global_variable.model+"_view_Modal").modal("show"); 
    },
	del: function() {
		var rows = $("#"+global_variable.model+"_table").bootstrapTable("getSelections");
		var ids = [];
		var desc = [];
		for( var o in rows) {
			ids.push(rows[o].id);
			desc.push(rows[o][global_variable.descColumnName]);
		}
		if(rows.length > 0){
			if(confirm("请确认是否批量删除"+global_variable.model_cn)) {
				$.get("delete"+object_name+".do",{ids:ids.join(","),desc:desc.join(",")},function(result) {
					if(result.hasErrors) {
						alert(result.errorMessage);
					}else {
						alert("删除成功");
						object_operate.success();
					}
				})
			}	
		}else {
			alert("请先选择删除行");
		}
	},
	success: function() {
		$("#"+global_variable.model+"_Modal").modal("hide");
		$("#"+global_variable.model+"_table").bootstrapTable('refresh', {});
	},
	//2015.9.15 pengmaokui add
	addView: function() {
		window.parent.window.addTab(global_variable.model_cn+'增加', global_variable.model+'/addView.do');
	},
	modifyView: function(id) {
		window.parent.window.addTab(global_variable.model_cn+'修改', global_variable.model+'/modifyView.do?id='+id+'&');
	}
}
$(document).ready(function(){
	object_name = global_variable.model.substr(0,1).toUpperCase()+global_variable.model.substr(1);
    object_operate.init();
	$("#"+global_variable.model+"_add").bind('click',function() {
		object_operate.show_add_modal();
	});
	$("#"+global_variable.model+"_del").bind('click',function() {
		object_operate.del();
	});
	$("#"+global_variable.model+"_search").bind('click',function() {
		object_search_form_operate.search();
	});
	$("#"+global_variable.model+"_searchReset").bind('click',function() {
		object_search_form_operate.reset();
	});
	//2015.7.17 zhaomiao add
	$("#"+global_variable.model+"_exportExcelAll").bind('click',function() {
        object_export_excel_operate.exportExcelAll();
	});
	//2015.9.15 pengmaokui add
	$("#"+global_variable.model+"_addView").bind('click',function() {
		object_operate.addView();
	});
	$("#"+global_variable.model+"_modifyView").bind('click',function() {
		object_operate.modifyView();
	});
});