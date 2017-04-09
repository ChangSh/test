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
<title>人员信息</title>
</head>
<body>
	<div class="content">
		<div class="tit">人员信息</div>
		<div class="tit_down">
			<form id="form">
				<table width="100%" class="table2">
					<tr>
						<td width="15%"><label for="" class="lb">姓名：</label></td>
						<td width="30%">${ob.name}</td>
						<td width="15%"><label for="" class="lb">编号：</label></td>
						<td width="30%"><p>${ob.code}</p></td>
					</tr>
					<tr>
						<td><label for="" class="lb">联系电话：</label></td>
						<td>${ob.phone}</td>
					</tr>
					<tr>
						<td><label for="" class="lb" >注册时间：</label></td>
						<td><p>${ob.registration_date}</p></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>