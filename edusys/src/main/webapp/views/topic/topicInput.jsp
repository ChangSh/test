<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"><%@ include
	file="/views/common/import-all-js-css.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/selectordie_theme_01.css">
<script src="${ctx}/static/selectordie.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('select').selectOrDie();
	});
	function jqvalidate(){
		return $("#form").validate({
			rules:{
				tname:{
					required:true
				},
				tbackinfo:{
					required:true
				},
				repair_address:{
					required:true
				}
			}
		}).form();
	}
	function save(){
		if(jqvalidate()){
			$.ajax({
				type:"POST",
				url:"${ctx}/topic/saveTopic.do",
				data:$("#form").serialize(),
				success:function(data){
						alert("保存成功!");
						window.location.href='${ctx}/views/topic/topicList.jsp';
				}
			})
		}
	}
	function jqvalidate(){
		return $("#form").validate({
			rules:{
				college:{
					required:true,
					maxlength:15
				}
			}
		}).form();
	}
	function cancel(){
		window.location.href='${ctx}/views/topic/topicList.jsp';
	}
</script>
<title>客户信息</title>
</head>
<body>
	<div class="content">
		<div class="tit">课题信息</div>
		<div class="tit_down">
			<form id="form">
			<input type="hidden" value="${ob.id}" name="id"/>
			<input type="hidden" value="${ob.tealoginname}" name="tealoginname"/>
			<input type="hidden" value="${ob.tearealname}" name="tearealname"/>
			<input type="hidden" value="${ob.stuloginname}" name="stuloginname"/>
			<input type="hidden" value="${ob.sturealname}" name="sturealname"/>
			<input type="hidden" value="${ob.code}" name="code"/>
				<table width="100%" class="table2" id="tableinfo">
					<tr>
						<td><label for="" class="lb"><span style = "color: Red">*</span>课题名称：</label></td>
						<td><input type="text" name="tname" value="${ob.tname}"/></td>
					</tr>
					<tr>
						<td><label for="" class="lb"><span style = "color: Red">*</span>课题背景：</label></td>
						<td><textarea name="tbackinfo">${ob.tbackinfo}</textarea></td>
					</tr>
					<tr>
						<td><label for="" class="lb"><span style = "color: Red">*</span>主要内容：</label></td>
						<td><textarea name="tdetailinfo">${ob.tdetailinfo}</textarea></td>
					</tr>
					<tr>
						<td><label for="" class="lb"><span style = "color: Red">*</span>实现功能：</label></td>
						<td><textarea name="tfunction">${ob.tfunction}</textarea></td>
					</tr>

					
				</table>
			</form>
			<div style="width: 200px; margin: 0 auto">
				<button class="btn1" onclick="save()">保存</button>
				<button class="btn2" onclick="cancel()">取消</button>
			</div>
		</div>
	</div>
</body>
</html>