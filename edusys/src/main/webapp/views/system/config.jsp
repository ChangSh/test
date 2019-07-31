<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script type="text/javascript">
$(function() {
	$.ajax({
		url : "${ctx}/system/config/inputRule.do",
		type : "POST",
		dataType: "json",
		success : function(data) {

			$("#settlementRule").val(data.rule);
		}
	})

});
function changePw(){
	if (!$("#pwd").val()){
		alert("密码不能为空");
		return false;
	}
	$.ajax({
		url : "${ctx}/system/config/ajax_changePwd.do",
		type : "POST",
		dataType: "text",
		data : {pwd:$("#pwd").val()},
		success : function(data) {
			if(data=="ok"){
				alert("修改成功");
			}
		}
	})
}

function changeInterval(){
	if (!$("#intervalSec").val()){
		alert("不能为空");
		return false;
	}

	$.ajax({
		url : "${ctx}/system/config/ajax_changeInterval.do",
		type : "POST",
		dataType: "text",
		data : {val:$("#intervalSec").val()},
		success : function(data) {
			if(data=="ok"){
				alert("修改成功");
			}
		}
	})
}

function changeRulel(){
	if (!$("#settlementRule").val()){
		alert("不能为空");
		return false;
	}
	$.ajax({
		url : "${ctx}/system/config/ajax_changeRulel.do",
		type : "POST",
		dataType: "text",
		data : {val:$("#settlementRule").val()},
		success : function(data) {
			if(data=="ok"){
				alert("修改成功");
			}
		}
	})
}

</script>
</head>
<body>
	<input type="hidden" name="id" value="${ob.id}" />
	<input type="hidden" name="order_state" id="order_state" value="" />
	<div class="content">
				<div class="tit">系统配置</div>
				<div class="tit_down">
				<table width="100%" class="table2">
					<tr>
						<td ><label for="" class="lb">客服取消订单时需要录入的密码：</label></td>
						<td ><input type="password" id="pwd" ></td>
						<td >
						<button class="btn1" onclick="changePw();" >修改密码</button></td>
					</tr>
					<tr>
						<td ><label for="" class="lb">规定客服处理定单的最短时间(秒)：</label></td>
						<td ><input type="text" id="intervalSec" value="${interval}"  >最少60秒</td>
						<td >
						<button class="btn1" onclick="changeInterval();" >修改</button></td>
					</tr>
					<tr>
						<td ><label for="" class="lb">订单结算规则：</label></td>
						<td ><textarea id="settlementRule" name="" ></textarea></td>
						<td >
						<button class="btn1" onclick="changeRulel();" >修改</button></td>
					</tr>
					
				</table>
		
				</div>
			</div>
</body>

</html>
