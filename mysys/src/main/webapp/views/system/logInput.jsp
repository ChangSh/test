<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>

   <script type="text/javascript">
  
        $(function () {
            $("input[type='text']").attr("readonly","readonly");
        });  
    </script>

</head>

<body >
	<form id="inputForm" action="${ctx}/system/code/save.do">
		<input type="hidden" name="id" value="${ob.id}" />
	  <div class="bod">
		  <div class="cont">
		   <ul class="see">
					<li><label>操作IP：</label> <input name="sectionname"
						class="dfinput8 required" value="${ob.requestIp}" type="text"
						validate="{required:true}" /> </li>
					<li><label>操作人：</label>  <input name="sectionname"
						class="dfinput8 required" value="${ob.createBy}" type="text"
						validate="{required:true}" /></li>
					<li><label>操作日期：</label><input name="sectionname"
						class="dfinput8 required" value="${ob.createDate}" type="text"
						validate="{required:true}" /></li>
					<li><label>操作类型：</label><input name="type"
						class="dfinput8 required" value="${ob.type}" type="text"
						validate="{required:true}" /></li>
					<li><label>操作方法：</label><textarea>${ob.description}</textarea></li>
           </ul>
           </div>
           <div class="fot">
           </div>
       </div>
      </form>
  

    
</body>
</html>