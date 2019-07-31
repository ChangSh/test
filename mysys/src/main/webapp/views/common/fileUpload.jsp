<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script type="text/javascript">
var readonly = "${param.readonly}";
//以图片后缀结尾
if (typeof String.prototype.endsWith != 'function') {
	  String.prototype.endsWith = function(suffix) {
	    return this.indexOf(suffix, this.length - suffix.length) !== -1;
	};
}
$(function(){
	$("#ajaxFileInput").uploadify({
        height        : 20,
        width         : 120,
        swf: '${ctx}/static/uploadify/scripts/uploadify.swf',
        uploader      : '${ctx}/cmsAdmin/news/ajax_upload.do;jsessionid=<%=session.getId()%>',
        'formData':{'fid':'${param.id}'},
        'multi': true,
        'buttonText': '选择文件',
        'onUploadSuccess':function(file, data, response){
        	var res= eval('('+data+')');
        	if (res.filerealname.endsWith("jpg")||res.filerealname.endsWith("jpeg")||res.filerealname.endsWith("png")){
        		 $("#pics").append('<div class="col-xs-3"  ><img src="'+res.filepath+'" width="80px"  onclick="Popwin(\''+res.filepath+'\');" class="img-square">'+renderSelect(0,res.filerealname)+'<div onclick="deletePic(this,\''+res.filerealname+'\');" class="removeLayer"><img src="${ctx}/static/webCss/images/remov_pic.png"></div></div>');
        	}else{
	             $("#uploadFileTable").append('<tr><td></td><td><a href="${ctx}/cmsAdmin/news/download.do?filename='+res.filerealname+'" >'+file.name+'</a></td>'
	             +'<td><a class="jq_fj" href="javascript:void(0);" onClick="deleteTempAccessory(this,\''+res.filerealname+'\');" ><img src="${ctx}/static/webCss/images/del.png"></img></td></tr></a>');
        	}
          	bindingSelect();
        }
    }); 
	//查出主表中的所有附件并显示
	  Free.ajax({
 	   url: '${ctx}/cmsAdmin/news/ajax_fileList.do;',
 	   data: {fid:'${param.id}'},
 	   success: function(data){
          	$.each(data,function(i,o){
            	if (o.filename.endsWith("jpg")||o.filename.endsWith("jpeg")||o.filename.endsWith("png")){
            		$("#pics").append('<div class="col-xs-3"  ><img src="'+o.filepath+'" width="80px" onclick="Popwin(\''+o.filepath+'\');" class="img-square">'+renderSelect(o.filetype,o.filerealname)
                     <c:if test="${empty param.readonly}">
                     +'<div onclick="deletePic(this,\''+o.filerealname+'\');" class="removeLayer"><img src="${ctx}/static/webCss/images/remov_pic.png"></div>'
                     </c:if>
                     +'</div>');
            		//$("#pics").append(' <img src="${ctx}'+o.filepath+'" width="50px"  onclick="Popwin(\'${ctx}'+o.filepath+'\');" />');
            	}else{
            		 $("#uploadFileTable").append('<tr><td></td><td><a href="${ctx}/cmsAdmin/news/download.do?filename='+o.filerealname+'" >'+o.filename+'</a></td>'
                    +'<td>'
                    <c:if test="${empty param.readonly}">
                   	 +'<a class="jq_fj" href="javascript:void(0);" onClick="deleteTempAccessory(this,\''+o.filerealname+'\');" ><img src="${ctx}/static/webCss/images/del.png"></img>'
                   	</c:if>
                    +'</td></tr></a>');
            	}
          	});
          	<c:if test="${empty param.readonly}">
          	bindingSelect();
          	</c:if>
       }
	  });})

function bindingSelect(){
	//设置下拉的事件
	$(".jq_select").change(function(){
		changeFileType($(this).attr("filerealname"),$(this).children("option:selected").val())
	});
}
function renderSelect(val,filerealname){
	function  inner(val,oVal){
		if (val==oVal){
			return ' value="'+oVal+'" selected ';
		}else{
			return ' value="'+oVal+'" ';
		}
	}
	var html='<span ><select filerealname="'+filerealname+'" class="jq_select" ><option value="">请选择...</option>';
	html+='<option '+inner(val,1)+' >主图</option>';
	html+='<option '+inner(val,2)+' >副图</option>';
	html+='</select></span>';
	return html;
} 
function Popwin(imgSrc){
	top.layer.open({
	    type: 2,
	    title: false,
	    area: ['600px', '400px'],
	    content: imgSrc
	}); 
}

//更改文件类型
function changeFileType(realFileName,filetype){
	layer.confirm('是否更改', function(index){
	  Free.ajax({
 	   url: '${ctx}/cmsAdmin/news/ajax_changeFileType.do;',
 	   data: {realFileName:realFileName,filetype:filetype},
 	   success: function(data){
 		   if (data='ok'){
	     	parent.layer.msg('操作成功',{time: 1000}); 
 		   }
       }
	  });
	    layer.close(index);
	});  
}

//删除附件
function deletePic(content,fileName){
	layer.confirm('是否删除', function(index){
	  Free.ajax({
   	   url: '${ctx}/cmsAdmin/news/ajax_deleteFile.do;',
   	   data: {realFileName:fileName},
   	   success: function(data){
   		   if (data='ok'){
   			$(content).parent().parent().remove();
	     	parent.layer.msg('操作成功',{time: 1000}); 
   		   }
         }
	  });
	    layer.close(index);
	});  
}
//删除附件
function deleteTempAccessory(content,fileName){
	layer.confirm('是否删除', function(index){
	  Free.ajax({
   	   url: '${ctx}/cmsAdmin/news/ajax_deleteFile.do;',
   	   data: {realFileName:fileName},
   	   success: function(data){
   		   if (data='ok'){
   			$(content).parent().parent().remove();
	     	parent.layer.msg('操作成功',{time: 1000}); 
   		   }
         }
	  });
	    layer.close(index);
	});  
}

</script>
</head>

<body >
   <body>
           <div style="width:99%;">
            <div class="clear"></div>
			  <div class="new_bulid" style="padding-left:10px;">上传附件资料</div>
		    	<div  class="menu">
		    	
                        <li style="padding-left:10%" >
                            <label class="list" for="cusername"> </label>
                            <c:if test="${empty param.readonly}">
                               <input class="shu" id="ajaxFileInput" type="file" name="image" />
                            </c:if>   
                        </li>
							  
                         
     
	   <div class="first_column"><span><img src="${ctx}/static/webCss/images/s.png" />已上传文件</span></div> 
            <table class="upload_img" id="uploadFileTable" >
			 <!--  <tr><td>1</td><td><input type="text" /></td><td><img src="${ctx}/static/webCss/images/del.png"></img></td></tr>-->
            </table>
	  <div class="first_column"><span><img src="${ctx}/static/webCss/images/s.png" />已上传图片</span></div>
	             <div class="row" id="pics" >
		                      
                 </div>		
		</div>
	</div>
</body>

    
</body>
</html>