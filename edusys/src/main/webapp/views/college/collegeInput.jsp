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
				name:{//姓名
					required:true,
					maxlength:15
				},
				phone:{//phone
					required:true,
					maxlength:11
				},
				repair_address:{
					required:true,
					maxlength:30
				}
			}
		}).form();
	}
	function save(){
		if(jqvalidate()){
			$.ajax({
				type:"POST",
				url:"${ctx}/college/savecollege.do",
				data:$("#form").serialize(),
				success:function(data){
						alert("保存成功!");
						window.location.href='${ctx}/views/college/collegeList.jsp';
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
		window.location.href='${ctx}/views/college/collegeList.jsp'
	}
</script>
<title>客户信息</title>
</head>
<body>
	<div class="content">
		<div class="tit">学院信息</div>
		<div class="tit_down">
			<form id="form">
			<input type="hidden" value="${ob.id}" name="id"/>
		
				<table width="100%" class="table2" id="tableinfo">
					<tr>
						<td><label for="" class="lb"><span style = "color: Red">*</span>学院名称：</label></td>
						<td><input type="text" name="college" value="${ob.college}"/></td>
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