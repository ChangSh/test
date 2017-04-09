<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script>
	$(function() {
		Free.validateSubmitAndClose($("#inputForm"));
	});
</script>
</head>
<body>
	<form id="inputForm" action="${ctx}/system/code/save.do">
		<input type="hidden" name="id" value="${ob.id}" />
		<div class="content">
			<div class="tit">参数信息</div>
			<div class="tit_down">
				<table width="100%" class="table2">
					<tr>
						<td height="40" class="fr">类型名称：</td>
						<td><input name="sectionname" class="dfinput required"
							value="${ob.sectionname}" type="text" validate="{required:true}" /></td>
					</tr>
					<tr>
						<td height="40" class="fr">代码：</td>
						<td><input name="code" class="dfinput" value="${ob.code}"
							type="text" /></td>
					</tr>
					<tr>
						<td height="40" class="fr">代码名称：</td>
						<td><input name="codename" class="dfinput"
							value="${ob.codename}" type="text" /></td>
					</tr>
					<tr>
						<td height="40" class="fr">顺序：</td>
						<td><input name="sortorder" class="dfinput"
							value="${ob.sortorder}" type="text" /></td>
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