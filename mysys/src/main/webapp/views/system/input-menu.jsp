<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<script>
	$(function() {
		if ("${param.pid}" != "") {
			$("#pid").val("${param.pid}");
		}
		Free.validateSubmitAndClose($("#inputForm"));
		$("#free_treeReFresh").click(function() {
			if (zTree) {
				zTree.reAsyncChildNodes(null, "refresh");
			}
		})
	});
</script>
<form id="inputForm" class="form-horizontal"
	action="${ctx}/system/menu/save.do">
	<div class="content">
		<div class="tit">菜单信息</div>
		<div class="tit_down">
			<table width="100%" class="table2">
				<tr>
					<td width="30%" >菜单ID：</td>
					<td width="70%" ><input name="id" value="${ob.id}" type="text" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>菜单名称：</td>
					<td><input name="menuname" value="${ob.menuname}" validate="{required:true}" type="text" /></td>
				</tr>
				<tr>
					<td>菜单url：</td>
					<td><input name="url" value="${ob.url}" type="text" /></td>
				</tr>
				<tr>
					<td>父级菜单ID：</td>
					<td><input name="pid" id="pid" value="${ob.pid}" type="text" /></td>
				</tr>
				<tr>
					<td>是否显示：</td>
					<td><f:select name="visible" showTorF="true" value="${ob.visible}" ></f:select></td>
				</tr>
				<tr>
					<td>顺序：</td>
					<td><input name="sx" value="${ob.sx}" type="text"  /></td>
				</tr>
				<tr>
					<td>图标文件名：</td>
					<td><input name="imagesrc" value="${ob.imagesrc}" type="text" /></td>
				</tr>
			</table>
				<div style="width: 200px; margin: 0 auto">
					<button class="btn1">保存</button>
						<button class="btn1" id="free_treeReFresh" type="button">
							刷新菜单</button>
				</div>
		</div>
	</div>
</form>
