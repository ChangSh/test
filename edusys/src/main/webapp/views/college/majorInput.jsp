<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script>
function jqvalidate(){
	return $("#form").validate({
		rules:{
			
			major:{
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
			url:"${ctx}/college/savemajor.do",
			data:$("#form").serialize(),
			success:function(data){
				if(data=="ok"){
	              alert("保存成功");
	              window.location.href='${ctx}/views/college/majorList.jsp';
				}else{
					alert("保存失败");
				}
			}
		})
}
}
	function cancel(){
		window.location.href='${ctx}/views/college/majorList.jsp';
	}

</script>
</head>
<body>
	<form id="form">
		<input type="hidden" name="id" value="${ob.id}" />
		<div class="content">
			<div class="tit">专业信息</div>
			<div class="tit_down">
				<table width="100%" class="table2">
					<tr>
						<td height="40" class="fr">专业名称：</td>
						<td><input name="major" class="dfinput required"
							value="${ob.major}" type="text" validate="{required:true}" /></td>
					</tr>
					<tr>
						<td height="40" class="fr">所属学院：</td>
						<td><f:select name="college"
						sectionname="学院"  value="${ob.college}"></f:select></td>
					</tr>
				</table>
		 </div>
		 </div>
	</form>			
				<div style="width: 200px; margin: 0 auto">
					<button class="btn1" onclick="save()">保存</button>
					<button class="btn2" onclick="cancel()">取消</button>
				</div>
</body>
</html>