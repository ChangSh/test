<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>

<script type="text/javascript">

var filds = [ 
			 {display : 'ID',name : 'id',width : 5}, 
			 {display : '登录名',name : 'loginname',width : 15}, 
			 {display : '姓名',name : 'realname',width : 15}, 
             {display : '选择用户',name:'',width : 5, 
          		 render:function(row){
          			 var html ="";
                     html+='<div style="width:20px;margin:0 auto;"><input type="checkbox" name="bid" class="jq_cb" value="{0}" ';
                     if (row.checked==1){
                     	html+=' checked="checked" ';
                     }
                     html+=' ></div>';
                     return Free.replace(html,row.id);
          		 }
             }
           ];
var opt ={
    url: '${ctx}/system/role/ajax_userlist.do?roleid=${param.roleid}',
	filds : filds,
	pageSize:10,
	param:{},
	afterDataLoad:changeBasket
};     

var g;
$(function() {
	g = $("#maingrid").grid(opt);
});
function changeBasket(data){
	$(".jq_cb").click(function(){
		var thisCb = $(this);
		if (thisCb.is(':checked')){
			  Free.ajax({
			 	   url: '${ctx}/system/role/addRoleUser.do;',
			 	   data: {roleid:'${param.roleid}',userid:$(this).val()},
			 	   success: function(data){
			  			 layer.msg('操作成功', {time: 1000});
			       }
				  });
		}else{
			  Free.ajax({
			 	   url: '${ctx}/system/role/deleteRoleUser.do;',
			 	   data: {roleid:'${param.roleid}',usersid:$(this).val() },
			 	   success: function(data){
			  			 layer.msg('操作成功', {time: 1000});
			       }
				  });
		}
	});
}
function closeAll(){
	parent.layer.closeAll();
}
</script>
</head>
<body>
<div class="content">
	<div class="tit">选择人员列表</div>
	<div class="tit_down">
		<form id="gridQueryForm">
				<ul class="select">
					<li><label class="fl">登录名：</label><input type="text"  name="search@EQ@loginname"></li>
					<div class="clear"></div>
				</ul>
				<div>
					<input type="button" value="查询" onclick="gridSearch()" class="btn1c">
				</div>
				<div class="clear"></div>
				<div class="clear"></div>
	
		</form>
		<div id="maingrid"></div>
		<div id="Pagination"></div>
	</div>
</div>	

</body>	
</html>
