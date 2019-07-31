<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<head>

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
</style>
<script type="text/javascript">
var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
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

var zNodes;  
var nodes;

function onCheck(e,treeId,treeNode){
    
    nodes = zTreeObj.getCheckedNodes(true);
}

$(function(){  

     $.ajax({  
        async:false,  
        cache:false,  
        type: 'POST',  
        dataType : "json",  
        url: contextPath + "/CyFunction/initData.do",//请求的action路径  
        error: function () {//请求失败处理函数  
            alert('请求失败');  
        },  
        success:function(data){ //请求成功后处理函数。    
            
            zNodes = eval("["+data.func+"]");   //把后台封装好的简单Json格式赋给treeNodes  
        }  
    });   
  
    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    nodes = zTreeObj.getCheckedNodes(true);
    
});

function refresh(){
    $.ajax({  
        async:false,  
        cache:false,  
        type: 'POST',  
        dataType : "json",  
        url: contextPath + "/CyFunction/initData.do",//请求的action路径  
        error: function () {//请求失败处理函数  
            alert('请求失败');  
        },  
        success:function(data){ //请求成功后处理函数。    
            
            zNodes = eval("["+data.func+"]");   //把后台封装好的简单Json格式赋给treeNodes  
        }  
    });   
    $("#treeDemo").html("");
    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    nodes = zTreeObj.getCheckedNodes(true);
}


function commit(){
    funcid = "";
    for(var i=0;i<nodes.length;i++){
    	funcid += nodes[i].id + ",";
    }
    $.ajax({  
        type: 'POST',  
        dataType : "json",
        data:{roleid:1,funcid:funcid},
        url: contextPath + "/CyFunction/modifyCyFunction.do",//请求的action路径  
        error: function () {//请求失败处理函数  
            alert('请求失败');  
        },  
        success:function(data){ //请求成功后处理函数。    
            alert("修改成功");
            refresh();

        }  
    }); 

}
</script>
</head>
<body>
<div>
   <ul id="treeDemo" class="ztree"></ul>
   <button onclick="commit()">保存</button>
</div>
</body>

