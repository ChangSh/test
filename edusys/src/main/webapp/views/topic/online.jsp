<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script type="text/javascript">
$(function(){
	var token = $("#token").text();
	window.location.href='${ctx}/views/topic/'+ token +'.html';
});
</script>
</head>
<body>
<lable id="token">${token}</lable>

</body>

</html>
