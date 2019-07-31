<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>

	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  

<script type="text/javascript">window.onerror = function(){return true;}</script>

<script src="${ctx}/static/shopping_files/js/jquery-1.7.2.min.js" type="text/javascript" ></script>

<link type="text/css" rel="stylesheet" href="${ctx}/static/shopping_files/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/static/shopping_files/css/bootstrap-responsive.min.css"/> 
<script src="${ctx}/static/shopping_files/js/bootstrap.min.js"></script>  


<!-- validator start// -->
<link rel="stylesheet" href="${ctx}/static/shopping_files/css/validationEngine.jquery.css" type="text/css" media="screen" title="no title" charset="utf-8" />
<link rel="stylesheet" href="${ctx}/static/shopping_files/css/template.css" type="text/css" media="screen" title="no title" charset="utf-8" />
<script src="${ctx}/static/shopping_files/js/jquery.validationEngine-cn.js" type="text/javascript"></script>
<script src="${ctx}/static/shopping_files/js/jquery.validationEngine.js" type="text/javascript"></script>

<script src="${ctx}/static/shopping_files/js/pcasunzip.js" type="text/javascript" ></script>

<link type="text/css" rel="stylesheet" href="${ctx}/static/shopping_files/css/font-awesome.min.css"> 


<style type="text/css">
body,td,a,p,div,li,input{
	font-family:微软雅黑;
}
.navbar a{
	font-size:13px; 
}
.alert{
	font-size:13px; 
}
td,th{
	font-size:13px; 
}
</style>

<script src="js/cart.js" type="text/javascript" >



</script>
<script>
function changeImg() {
    var imgSrc = $("#imgObj");
    /* var src = imgSrc.attr("src"); */
   imgSrc.attr("src", "${ctx}/system/Code/Creatcode.do?" + new Date());
}

function save(){
	$.ajax({
        url:"${ctx}/msgFront/message.do",
        type:"post",
        dataType:"text",
        data:{msg:$("#msg").val(),code:$("#code").val()},
        success:function(data){
             if(data=="codeFail"){
          	   alert("验证码错误");
        	   
             }else {
            	 alert("提交成功");
            	 parent.location.reload();
             }
        } 
    });     
	
	
}
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="5" style="margin-top:30px">
	<tbody><tr>
        <td width="40%" valign="top">
		<form id="login_form" method="post" action="">
          <table width="100%" border="0" cellspacing="5">
            <tbody>
            <tr>
              <td width="20%" align="right"></td>
              <td><h1>意见反馈</h1></td>
            </tr>
            <tr>
              <td align="right">留言：</td>
              	<td>
						<textarea name="msg" cols=40 rows=5 id="msg"></textarea>
				 </td>
            </tr>
            <tr>
               <td></td>
              <td><input type="text" placeholder="验证码…"  class="validate[required] input_text" id="code" value="" /></td>
            </tr>
            <tr>
            <td></td>
             <td>
				<a href="#" onclick="changeImg()" title="看不清，换一张" ><img id="imgObj" alt="验证码" src="${ctx}/system/Code/Creatcode.do" class="fl mt20 ml20"/></a></td>
            </tr>
            <tr>
              <td height="50" align="right"></td>
              <td><input type="hidden" name="ref" value="">
			  <input type="button" value="提交" onclick="save()" class="btn btn-large btn-warning"></td>
            </tr>
          </tbody>
          </table>
        </form>
		</td>
      
	</tr>
</tbody>
</table> 
</body>
</html>
