$(function(){
	var label=$("#labTreeUserMult").text();
	if(label==""){
		$("#hiddenUserMult").val("");
	}
});
var dataTree="";
//存储名称的label的id
var labelId ="";
//存储数据(如id)的input的name
var inputName ="";
//选择人的邮箱
var selectEmailId="";
   function exTree(data,datalabel,datainput,dataEmail){
	   labelId=datalabel;
	   selectEmailId=dataEmail;
	   if(typeof(datainput)!=undefined){
		   inputName=datainput; 
	   }
	   
	   dataTree=data;
	   if(data =='treeMultDep'){
		   $("#treeMultModalTitle").text("组织结构树-分部多选");
		   $("#treeMultModalBody").children().remove();
		   $("#treeMultModalBody").append("<iframe src='"+contextPath+"/bdp/orgStructTree/"+data+".jsp' id='ifram"+data+"' name='ifram"+data+"' width='100%' height='100%'  frameborder='0' scrolling='no' ></iframe>");
	   }else{
		   if(data=='treeDep'){
			   $("#treeModalTitle").text("组织结构树-分部单选");
		   }else if(data=='treeUserMult'){
			   $("#treeModalTitle").text("组织结构树-人员多选");
		   }else if(data=='treeUserSingle'){
			   $("#treeModalTitle").text("组织结构树-人员单选");
		   }else if(data=='queryRole'){
			   $("#treeModalTitle").text("角色列表");
		   }
		   
		   $("div[name='tree']").children().remove();
		   $("div[name='tree']").append("<iframe src='"+contextPath+"/bdp/orgStructTree/"+data+".jsp' id='ifram"+data+"' name='ifram"+data+"' width='100%' height='100%'  frameborder='0' scrolling='no' ></iframe>");
	   }
   } 
   
function saveTreeMultName(){
	var strArr = $("#zTreeMultDep").val();
	if(strArr==null||strArr ==""){
		alert("请选择要分配的集团！");
		return null;
	}
	var lastName = $("#operaterLasname").val();
	var email = $("#operateremail").val();
	$.ajax({
		 async : false,
		 type: 'POST',
        data:{"strArr":strArr,"lastName":lastName,"email":email},
		 url:"" + contextPath + "/operater/allotPrivilege.do",
		 error: function (data) {//请求失败处理函数
			 alert("请求失败");
		  },
		 success:function(data){ //请求成功后处理函数。 
			  	$("#treeMultModal").modal("hide");
		 }
	 }); 
		
	}
		
   

function getName(data){ 
	var name=document.getElementById(data).contentWindow.getName();
	return name;
}

function getEmail(data){ 
	var name=document.getElementById(data).contentWindow.getEmail();
	return name;
}
function getNameALL(data){ 
	var namesALL=document.getElementById(data).contentWindow.getDataALl();
	return namesALL;
}
function gethiddenUser(){
	var userName=$("#hiddenUserMult").val();
	return userName;
}
function saveName(){
	 if(labelId!=null&&labelId!=""){
		 if(dataTree=='treeDep'){
		     	$("#"+labelId).text(getName('iframtreeDep'));
			   }else if(dataTree=='treeUserMult'){
				   $("#"+labelId).text(getName('iframtreeUserMult'));
		           $("input[name='"+inputName+"']").val(getNameALL('iframtreeUserMult'));
			   }else if(dataTree=='treeUserSingle'){
				   $("#"+labelId).text(getName('iframtreeUserSingle'));
				   $("#"+selectEmailId).text(getEmail('iframtreeUserSingle'));
				   $("input[name='"+inputName+"']").val(getNameALL('iframtreeUserSingle'));
			   }else if(dataTree=='queryRole'){
				   $("#"+labelId).text(getName('iframqueryRole'));
				   $("input[name='"+inputName+"']").val(getNameALL('iframqueryRole'));
			   }
	 }else{
		 if(dataTree=='treeDep'){
		     	$("#labTreeDep").text(getName('iframtreeDep'));
			   }else if(dataTree=='treeUserMult'){
				   $("#labTreeUserMult").text(getName('iframtreeUserMult'));
		           $("#hiddenUserMult").val(getNameALL('iframtreeUserMult'));
			   }else if(dataTree=='treeUserSingle'){
				   $("#labTreeUserSingle").text(getName('iframtreeUserSingle'));
				   $("input[name='tblUserId']").val(getNameALL('iframtreeUserSingle'));
			   }else if(dataTree=='queryRole'){
				   $("label[name='labelRoleName']").text(getName('iframqueryRole'));
				   $("input[name='roleIds']").val(getNameALL('iframqueryRole'));
			   }
	 }
}
