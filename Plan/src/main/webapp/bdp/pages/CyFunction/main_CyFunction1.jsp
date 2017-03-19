<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../commons/statics.jsp"%>
<style type="text/css">
.bootstrap-table td {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	max-width: 200px;
}

#CyFunction_view_form .col-sm-9 label {
	font-weight: normal;
}

#CyFunction_form .col-sm-9 label {
	font-weight: normal;
}
</style>
<script type="text/javascript">
  /* 保存 */
  function commit(){
   var s = "";
	    /* 遍历页面的checkbox 并获取值 */
	    $('#txtFunc input[type="checkbox"]:checked').each(
	      function () {
	          if ($(this).attr("class") != null && $(this).attr("class") != undefined)
	              s += $(this).attr("class") + "&";
	      }
	  );
	   var roleid = $("#roleLList option:selected").val();
		 $.ajax({
		        type: "post",
		        url: "modifyCyFunction.do",
		        data: {limit:s,roleid:roleid},
		        dataType: "json",
		        success: function (data) {
		        	
		   			alert("保存成功");
		               
		        }
		    });

	} 
  
  /* 刷新 */
 function refresh(){
	 $.ajax({
	        type: "post",
	        url: "initData.do",
	        data: {},
	        dataType: "json",
	        success: function (data) {
	        	if((data.roleList!=null)&&(data.roleList.length>0)){
				      $.each(data.roleList, function(i, value) {
				        	 $("#roleLList").append('<option value="' + value.roleid + '">' + value.rolename + '</option>');

						});
	        	}
	                    $("#txtFunc").html(data.func);
	                    var allAuth = data.funcId.split('&');
	                    if (allAuth.length > 0) {
	                        for (var a = 0; a < allAuth.length; a++) {
	                            var splitAuth = allAuth[a];
	                            if ($("." + splitAuth) != null && $("." + splitAuth) != undefined) {
	                                $("." + splitAuth).attr("checked", "checked");
	                            }
	                        }
	                    }
	               
	        }
	    });
	 
 }
 /* 页面初始化 */
 $(function(){
	 $.ajax({
	        type: "post",
	        url: "initData.do",
	        data: {},
	        dataType: "json",
	        success: function (data) {
	        	if((data.roleList!=null)&&(data.roleList.length>0)){
				      $.each(data.roleList, function(i, value) {
				        	 $("#roleLList").append('<option value="' + value.roleid + '">' + value.rolename + '</option>');

						});
	        	}
	                    $("#txtFunc").html(data.func);
	                    var allAuth = data.funcId.split('&');
	                    if (allAuth.length > 0) {
	                        for (var a = 0; a < allAuth.length; a++) {
	                            var splitAuth = allAuth[a];
	                            if ($("." + splitAuth) != null && $("." + splitAuth) != undefined) {
	                                $("." + splitAuth).attr("checked", "checked");
	                            }
	                        }
	                    }
	               
	        }
	    });
	 /* 角色变化下面权限变化 (对应权限勾选)*/
	 $('#roleLList').change(function(){ 
		 var roleid = $("#roleLList option:selected").val();
		 $.ajax({
		        type: "post",
		        url: "changeData.do",
		        data: {roleid:roleid},
		        dataType: "json",
		        success: function (data) {
		        			$("#txtFunc").html("");
		                    $("#txtFunc").html(data.func);
		                    var allAuth;
		                    if(data.funcId!=""&&data.funcId!=null){
		                    	allAuth = data.funcId.split('&');
		                        for (var i = 0; i < allAuth.length; i++) {
		                            var splitAuth = allAuth[i];
		                            if ($("." + splitAuth) != null && $("." + splitAuth) != undefined) {
		                                $("." + splitAuth).attr("checked", "checked");
		                            }
		                        }
		                    }
		               
		        }
		    });
		 
			}) 
}); 
/* 勾选功能 */
function chooseLimit(obj, type){

     var id = $(obj).attr("class");

     switch(type){

         case 0:
                 var dt = id.split('_');

                 $('#txtFunc input[type="checkbox"]').each(
                     function () {
                         var list = $(this).attr("class").split('_');
                         if (dt[0] == list[0]) {
                             if ($(obj).attr("checked")) {
                                 $(this).attr("checked", "checked");
                             }
                             else {
                                 $(this).attr("checked", false);
                             }
                         }
                     }
              );
             
             break;

         case 1:
             
                 var dt = id.split('_');

                 if ($(obj).attr("checked")){
                     $("." + dt[0] + "_0_0").attr("checked", "checked");
                 }

                 $('#txtFunc input[type="checkbox"]').each(
                       function () {
                           var list = $(this).attr("class").split('_');
                           if (dt[1] == list[1]) {
                               if ($(obj).attr("checked")) {
                                   $(this).attr("checked", "checked");
                               }
                               else {
                                   $(this).attr("checked", false);
                               }
                           }
                       }
              	);
             
             break;
         case 2:
         
             var dt = id.split('_');
             if ($(obj).attr("checked")) {
                 $("." + dt[0] + "_0_0").attr("checked","checked");

                 $("." + dt[0] + "_" + dt[1] + "_0").attr("checked","checked");
             }
             break;
   
 	}
}


</script>
</head>
<body>

      <select id="roleLList"></select>
      <div id="txtFunc"></div>
		<button onclick="commit()">保存</button>
</body>
</html>
