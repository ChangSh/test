<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script>

$(function(){
	$(".required").parent().prev().prepend("<span style = 'color: Red'>*</span>");//必填加红星
	Free.validateSubmitAndClose($("#inputForm"));
});
</script>


</head>
<body>
<form id="inputForm" action="${ctx}/system/authorize/save.do">
		<input type="hidden" name="id" value="${ob.id}" />
	  <div class="content">
			<div class="tit">权限信息</div>
			<div class="tit_down">
				<table class="table2 table_lar">
	  <tr><td class="fr" >模块：</td>
	  <td><input name="module" 
						class="dfinput required" value="${ob.module}" maxlength="100" type="text"
						validate="{required:true}" /></td>
	</tr>
	  
	  <tr><td  class="fr" >描述：</td>
	      <td><input name="description"  class="dfinput"
						value="${ob.description}" maxlength="50" type="text" /></td>
	</tr>
	<tr><td  class="fr" >顺序：</td>
	<td><input name="sx"  class="dfinput" value="${ob.sx}" maxlength="4" type="text" />
						
	</td></tr>
	  </table>      
				<div style="width: 100px; margin: 0 auto">
						<button class="btn1">保存</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>