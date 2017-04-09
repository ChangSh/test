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
					<div id="edu1">
					<tr>
						<td><label for="" class="lb">课题名称：</label></td>
						<td><label id="topic"></label></td>
					</tr>
					<tr>
						<td><label for="" class="lb">指导老师：</label></td>
						<td><label id="tutor"></label></td>
					</tr>
					</div>
				</table>
	
		</div>
	</div>
</body>
</html>