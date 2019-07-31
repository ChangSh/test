<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"><%@ include
	file="/views/common/import-all-js-css.jsp"%>
<script type="text/javascript">
 $(function(){
	$.ajax({
		type:"POST",
		url:"${ctx}/stu/stuInfo.do",
		data:{},
		success:function(data){
				$("#name").text(data.stu.name);
				$("#code").text(data.stu.code);
				$("#college").text(data.college.college);
				$("#major").text(data.major.major);
				$("#phone").text(data.stu.phone);
				$("#registration_date").text(data.stu.registration_date);
				var html = "";
				if(data.stu.topic==""||data.stu.topic==null){
					html += "<button class='btn1'  onclick='addedu()'>选择课题</button>";
					$("#edu").html(html);
				}else{
					$("#edu").html("<lable>已选<lable>");
					$("#topic").text(data.stu.topic);
					$("#tutor").text(data.stu.tutor);
					html += "<input id='file' type='file'/> <button id='upload' class='btn3' onclick='upload()'>上传</button>"
					$("#uploadForm").html(html);
				}
		}
	})
}); 
function addedu(){
	   layer.open({
		    type: 2,
		    title: '选择课题',
		    shadeClose: true,
		    shade: 0.8,
		    offset: ['50px', '50px'], 
		    area: ['1000px', '600px'],
		    content:'${ctx}/views/stus/topicList.jsp'//'${ctx}/buildingPackAdmin/building/findBuildingInfo.do'
		}); 
	   
}
function upload(){
	var formData = new FormData();
	formData.append("file",$("#file")[0].files[0]);
	$.ajax({
		beforeSend:function(){
			var fileName = $("#file").val();
			if(fileName == ""){
				alert("请选择文件");
				return false;
			}else{
				var suffix = fileName.substring(fileName.lastIndexOf(".")+1);
				if(suffix != "doc"){
					alert("请上传doc格式文档");
					return false;
				}
			}
		},
		type:"POST",
		url:"${ctx}/stu/upload.do",
		data:formData,
		contentType:false,
		cache:false,
		processData:false,
		success:function(data){
			var json = eval("["+data+"]");
			if(json[0].filerealname.length>0){
				alert("上传成功");
			var html = "";
				html += "<a href=''>";
				html += json[0].filerealname;
				html += "</a>";
				$("#txtname").html(html);
				$("#topicname").css('display','');
			
			}else{
				alert("上传失败");
			}
			
			
		}
	})
}
</script>
<title>个人信息</title>
</head>
<body>
	<div class="content">
		<div class="tit">个人信息</div>
		<div class="tit_down">
			
				<table width="100%" class="table2">
					<tr>
						<td width="15%"><label for="" class="lb">姓名：</label></td>
						<td width="30%"><label id="name"></label></td>
						<td width="15%"><label for="" class="lb">学号：</label></td>
						<td width="30%"><label id="code"></label></td>
					</tr>
					<tr>
						<td><label for="" class="lb" >学院：</label></td>
						<td><label id="college"></label></td>
					</tr>
					<tr>
						<td><label for="" class="lb" >专业：</label></td>
						<td><label id="major"></label></td>
					</tr>
					<tr>
						<td><label for="" class="lb">联系电话：</label></td>
						<td><label id="phone"></label></td>
					</tr>
					<tr>
						<td><label for="" class="lb">注册时间：</label></td>
						<td><label id="registration_date"></label></td>
					</tr>
					<tr>
						<td><label for="" class="lb">毕设选题：</label></td>
						<td><div id="edu"></div></td>
					</tr>
					<tr>
						<td><label for="" class="lb">课题名称：</label></td>
						<td><label id="topic"></label></td>
					</tr>
					<tr>
						<td><label for="" class="lb">指导老师：</label></td>
						<td><label id="tutor"></label></td>
					</tr>
					<tr>
						<td><label for="" class="lb">上传论文：</label></td>
						<td><div id="uploadForm"> </div></td>
					</tr>
					<tr id="topicname" style="display: none">
						<td><label for="" class="lb">论文名称：</label></td>
						<td><div id="txtname"> </div></td>
					</tr>						
				</table>
	
		</div>
	</div>
</body>
</html>