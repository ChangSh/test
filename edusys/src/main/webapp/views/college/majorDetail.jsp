<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"><%@ include
	file="/views/common/import-all-js-css.jsp"%>
<script type="text/javascript">
	function cancel() {
		window.location.href = '${ctx}/views/customer/InfoList.jsp'
	}
</script>
<title>专业信息</title>
</head>
<body>
	<div class="content">
		<div class="tit">专业信息</div>
		<div class="tit_down">
			<form id="form">
				<table width="100%" class="table2">
					<tr>
						<td width="15%"><label for="" class="lb">专业名称：</label></td>
						<td width="30%">${ob.major}</td>
					</tr>
					<tr>
						<td width="15%"><label for="" class="lb">学院名称：</label></td>
						<td width="30%">${ob.college}</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>