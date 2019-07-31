<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<%@ include file="/views/common/import-all-js-css.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/webuploader.css">
<script src="${ctx}/static/webuploader.min.js"></script>

<script type="text/javascript">
	function jqvalidate(){
		return $("#form").validate({
			rules:{
				
				codename:{//姓名
					required:true,
					maxlength:15
				}
			
			}
	}).form();
	}
	function save(){//保存按钮
		if(jqvalidate()){
			$.ajax({
				type:"POST",
				url:"${ctx}/goods/fenlei.do",
				data:$("#form").serialize(),
				success:function(data){
					if(data=="ok"){
		              alert("保存成功");
		              parent.location.href='${ctx}/views/goods/goodsInfoList.jsp';
					}else{
						
						alert("保存失败");
					}
				}
			})
			
			
		
	}
	}
		function cancel(){
			parent.location.href='${ctx}/views/goods/goodsInfoList.jsp';
		}
	



</script>
<title>新增类型</title>
</head>
<body>
	<div class="content">
		<div class="tit">新增类型</div>
		<div class="tit_down">
			<form id="form">
			
				<table class="table2 cus_info work" id ="tableinfo" style="margin:0 auto">
					<tr>
						<td style="width:70px;"><label for="" class="lb" style="width:70px;"><span style = "color: Red">*</span><span>类型名称：</span></label></td>
						<td ><input type="text" name="codename" /></td>
					</tr>
					
						
				</table>
			</form>
	       
			<div style="width: 201px; margin: 0 auto">
				<button class="btn1" onclick="save()">保存</button>
				<button class="btn2" onclick="cancel()">取消</button>
				<div class="clear"></div>
			</div>
			
		</div>
	</div>
</body>
</html>