<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"><%@ include
	file="/views/common/import-all-js-css.jsp"%>
<script type="text/javascript">
	function cancel() {
		window.location.href = '${ctx}/views/topic/topicList.jsp'
	}
</script>
<title>课题信息</title>
</head>
<body>
	<div class="content">
		<div class="tit">课题信息</div>
		<div class="tit_down">
				<table width="100%" class="table2">
					<tr>
						<td width="15%"><label for="" class="lb">课题名称：</label></td>
						<td width="30%">${ob.tname}</td>
					</tr>
					<tr>
						<td width="15%"><label for="" class="lb">课题背景：</label></td>
						<td width="30%">${ob.tbackinfo}</td>
					</tr>
					<tr>
						<td width="15%"><label for="" class="lb">学主要内容：</label></td>
						<td width="30%">${ob.tdetailinfo}</td>
					</tr>
					<tr>
						<td width="15%"><label for="" class="lb">实现功能：</label></td>
						<td width="30%">${ob.tfunction}</td>
					</tr>
					<tr>
						<td width="15%"><label for="" class="lb">导师名称：</label></td>
						<td width="30%">${ob.tearealname}</td>
					</tr>
					
				</table>
		</div>
	</div>
</body>
</html>