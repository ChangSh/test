<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<%@ include file="/views/common/import-all-js-css.jsp"%>

	<form id="inputForm" action="${ctx}/system/user/changePwd.do">
		<input type="hidden" name="id" value="<shiro:principal property="id"/>" /> 
		<div class="content">
			<div class="tit">修改密码</div>
			<div class="tit_down">
				<table width="100%" class="table2">
					<tr>
						<td height="40" class="fr">原密码：</td>
						<td><input name="oldpassword" id="oldpassword" class="dfinput "
							 type="password" /></td>
					</tr>
					<tr>
						<td height="40" class="fr">新密码：</td>
						<td><input name="password" id="password" class="dfinput "
							 type="password" /></td>
					</tr>
					<tr>
						<td height="40" class="fr">确认密码：</td>
						<td><input name="repassword" id="repassword" class="dfinput "
							 type="password" /></td>
					</tr>
				</table>
				<div style="width: 200px; margin: 0 auto">
						<input class="btn1" type="button" onclick="save()" value="保存" />
				</div>
			</div>
		</div>
	</form>

<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引

	function save() {

		if(!$("#password").val()){
			alert("密码不能为空");
			return ;
		}
				
		if($("#password").val() !=  $("#repassword").val())
			{
			layer.msg('两次密码输入的不一致，请重新输入!');
			return;
			}
		$.ajax({
			cache : true,
			type : "post",
			url : "${ctx}/system/user/changePwd.do",
			data : $("#inputForm").serialize(),// 你的formid
			success : function(data) {
				if(data == "false")
					{
						layer.alert('密码修改失败',function(){
							parent.layer.close(index); 
						});
					}else{
						layer.alert('密码修改成功！',function(){
							parent.layer.close(index); 
						});
					}
				

			}
		});
	}
</script>