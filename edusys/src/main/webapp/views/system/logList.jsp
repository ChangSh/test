<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>

<script type="text/javascript">

var filds = [ 
			 {display : 'ID',name : 'id',width : 10}, 
			 {display : '操作IP',name : 'requestIp',width : 20}, 
			 {display : '操作人',name : 'createBy',width : 20}, 
			 {display : '操作日期',name : 'createDate',width : 20}, 
			 {display : '操作类型',name : 'type',width : 10}, 
             {display : '操作',name:'',width : 10, 
          		 render:function(row){
          			 var html ="";
                     html+='<a href="javascript:void(0);" onclick="f_input({0})" title="查看" ><img src="${ctx}/static/images/see.png"></a>';
                     return Free.replace(html,row.id);
          		 }
             }
           ];
var opt ={
	url : "${ctx}/system/log/ajax_list.do",
	filds : filds,
	pageSize:10,
	trimBlank:true,
	param:{}
};     

function f_input(id){
	layer.open({
	    type: 2,
	    title: ' ',
	    area: ['600px', '350px'],
	    content: '${ctx}/system/log/input.do?id='+id
	}); 
}
var g;
$(function() {
	g = $("#maingrid").grid(opt);
});
</script>
</head>
<body>
<div class="content">
	<div class="tit">权限信息列表</div>
	<div class="tit_down">
		<form id="gridQueryForm">
			<ul class="select">
				<li><label class="fl">操作人：</label><input type="text"  name="search@EQ@createBy"></li>
				<li><label class="fl">操作IP：</label><input type="text"  name="search@EQ@requestIp"></li>
				<li><label class="fl">操作日期：</label><input type="text"  name="search@LIKE@createDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  ></li>
				<div class="clear"></div>
				<div style="width:100px;margin:0 auto">
					<input type="button" value="查询" onclick="gridSearch()" class="btn1">
				</div>
				<div class="clear"></div>
			</ul>
		</form>
		<div id="maingrid"></div>
		<div id="Pagination"></div>
	</div>
</div>	
</body>

</html>
