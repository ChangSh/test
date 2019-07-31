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
				url:"${ctx}/customer/savedit.do",
				data:$("#form").serialize(),
				success:function(data){
						alert("保存成功!");
						window.location.href='${ctx}/views/customer/CustomerInfoList.jsp';
				}
			})
		}
	}
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
				
			}
		}).form();
	}
	function cancel(){
		window.location.href='${ctx}/views/customer/CustomerInfoList.jsp'
	}
</script>
<title>客户信息</title>
</head>
<body>
	<div class="content">
		<div class="tit">客户信息</div>
		<div class="tit_down">
			<form id="form">
			<input type="hidden" value="${ob.id}" name="id"/>
			<input type="hidden" value="${ob.code}" name="code"/>
			<input type="hidden" value="${ob.registration_date}" name="registration_date"/>
		
				<table width="100%" class="table2" id="tableinfo">
					<tr>
						<td width="15%"><label for="" class="lb"><span style = "color: Red">*</span>客户姓名：</label></td>
						<td width="30%"><input type="text" name="name" value="${ob.name}"/></td>
						<td width="15%"><label for="" class="lb">客户编号：</label></td>
						<td width="30%"><p>${ob.code}</p></td>
					</tr>
					<tr>
						<td><label for="" class="lb"><span style = "color: Red">*</span>联系电话：</label></td>
						<td><input type="text" name="phone" value="${ob.phone}"/></td>
					</tr>
					<tr>
						<td><label for="" class="lb" >注册时间：</label></td>
						<td><p>${ob.registration_date}</p></td>
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