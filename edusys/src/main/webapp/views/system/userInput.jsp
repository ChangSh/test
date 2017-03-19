<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script>
$(function(){
	$("#inputForm").validate(
			   {
			    rules: {
			    	areaname:{required:true},
			    	loginname:{required:true,
			        remote:{                    //验证用户名是否存在
			               type:"POST",
			               url:"${ctx}/system/user/checkRepeak-loginname.do",             //servlet
			               data:{
			                 name:function(){return $("#loginname").val();},
			                 oldloginname:"${ob.loginname}"
			               } 
			              } 
			            }
			    },
			    messages: {
			    	loginname:{required:"登录名不能为空",remote:"此登录名已经存在"}
			    },
			    submitHandler:function(form){
		          	$.ajax({
			       		   type: "POST",
			       		   url: $(form).attr("action"),
			       		   data: $(form).serialize(),
			       		   success: function(data){
			       	     	if(data=='ok'){
			       	     		if (parent.g)
				   	  			 parent.g.reload();
				   	  			 parent.layer.msg('操作成功', {time: 1000});
				   	  			 parent.layer.closeAll('iframe');
			       	     	}else{
				   	  			 parent.layer.msg('操作失败', {time: 1000});
			       	     	}
			       		}
			         });  
		         return false;
			    }
			   });
});

</script>
</head>

<body >
    <form name="inputForm" method="post"  id="inputForm" action="${ctx}/system/user/save.do" >
	<input type="hidden" name="id" value="${ob.id}" />
	
	  	<div class="content">
			<div class="tit">用户信息</div>
			<div class="tit_down">
				<table width="100%" class="table2">
	  <tr><td  class="fr">登录名：</td>
	  <td><input name="loginname" id="loginname"
						class="dfinput required" value="${ob.loginname}" type="text"
						validate="{required:true}" /></td>
	</tr>
	  
	  <tr><td  class="fr">姓  名：</td>
	      <td><input name="realname" id="realname" class="dfinput required"
						value="${ob.realname}" type="text" /></td>
	</tr>
	<tr><td class="fr">邮  箱：</td>
	      <td><input name="email" id="email" class="dfinput "
						value="${ob.email}" type="text" /></td>
	</tr>
	  </table>
 
				<div style="width: 200px; margin: 0 auto">
					<button class="btn1">保存</button>
				</div>
			</div>
		</div>
	</form>
          
</body>
</html>