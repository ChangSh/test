<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script>


$(function(){
	Free.validateSubmitAndClose($("#inputForm"));
});

$(function(){
	<c:forEach var="it" items="${ob.roleAuthorizes}">
		$("#free_${it.auth_id}").attr("checked","checked"); 
	</c:forEach>
});
</script>


</head>
<body>
<form id="inputForm" action="${ctx}/system/role/saveAuthorize.do"  >

<input type="hidden" name="id" value="${ob.id}" />
<div class="content" style="margin-left: 20px">
	<c:forEach var="it" items="${auths}">
		<li><input type="checkbox" id="free_${it.id}" name="auths" value="${it.id}" >${it.description} [${it.module}]<div class="clear"></div></li> 
	</c:forEach>
</div>

	<p style="position:absolute;left:50%;margin-left:-40px;" ><button class="btn1" id="free_submit" type="submit">保存 </button>
	</p>
</form>
</body>
</html>
