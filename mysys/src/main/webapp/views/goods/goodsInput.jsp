<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<%@ include file="/views/common/import-all-js-css.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/webuploader.css">
<script src="${ctx}/static/webuploader.min.js"></script>

<script type="text/javascript">
	function jqvalidate(){
		return $("#form").validate({
			rules:{
				
				gname:{
					required:true,
					maxlength:15
				},
				gunitprice:{
					required:true,
					maxlength:11,
					number:true
				},
				gbriefintro:{
					required:true,
					maxlength:30
				},
				gdetailintro:{
					required:true,
					maxlength:200
				},
				gsize:{
					required:true,
					maxlength:30
				},
			
			}
	}).form();
	}
	function save(){//保存按钮
		if(jqvalidate()){
			if($("#imgname").val()!=null){
				$.ajax({
					type:"POST",
					url:"${ctx}/goods/save.do",
					data:$("#form").serialize(),
					success:function(data){
			              alert("保存成功");
			              window.location.href='${ctx}/views/goods/goodsInfoList.jsp';
					}
				})
				
			}else
			    uploader.upload();
		
	}
	}
		function cancel(){
			window.location.href='${ctx}/views/goods/goodsInfoList.jsp';
		}
	
		
//下面是图片上传

// 初始化Web Uploader
$(function(){  
uploader = WebUploader.create({

    // 选完文件后，是否自动上传。
    auto: false,

    // swf文件路径
    swf: '${ctx}/static/Uploader.swf',

    // 文件接收服务端。
    server: '${ctx}/goods/upload.do',

    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#filePicker',

    // 只允许选择图片文件。
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    },
    // 上传文件个数
	fileNumLimit : 1,
	// 全局设置, 文件上传请求的参数表，每次发送都会发送此对象中的参数。
	formData: {
	    token : 'zi1OZ8VhS6nZ0YRAc6NcCCjKR8m2OaTWxKWPl7Hy:ObKB-V4Y2lK6Mbt1bTigBACRGEI=:eyJzY29wZSI6ImRqc3BhY2UiLCJkZWFkbGluZSI6MTQzOTU2OTg1MX0=' 
	}
});

//当有文件添加进来的时候
uploader.on( 'beforeFileQueued', function( file ) {
	uploader.reset();
	var $li = $(
            '<div id="' + file.id + '" class="file-item thumbnail">' +
                '<img>' +
            '</div>'
            ),
        $img = $li.find('img');


    // $list为容器jQuery实例
    $("#imgList").html( $li );

    // 创建缩略图
    // 如果为非图片文件，可以不用调用此方法。
    // thumbnailWidth x thumbnailHeight 为 100 x 100
    uploader.makeThumb( file, function( error, src ) {
        if ( error ) {
            $img.replaceWith('<span>请选择正确格式图片</span>');
            return;
        }

        $img.attr( 'src', src );
    }, 100,100);
});
//局部设置，给每个独立的文件上传请求参数设置，每次发送都会发送此对象中的参数。。参考：https://github.com/fex-team/webuploader/issues/145
uploader.on('uploadBeforeSend', function( block, data, headers) {
    data.key = new Date().toLocaleTimeString();
});
//文件上传过程中创建进度条实时显示。
uploader.on( 'uploadProgress', function( file, percentage ) {
    var $li = $( '#'+file.id ),
        $percent = $li.find('.progress span');

    // 避免重复创建
    if ( !$percent.length ) {
        $percent = $('<p class="progress"><span></span></p>')
                .appendTo( $li )
                .find('span');
    }

    $percent.css( 'width', percentage * 100 + '%' );
});

// 文件上传成功，给item添加成功class, 用样式标记上传成功。
uploader.on( 'uploadSuccess', function( file ) {
    $( '#'+file.id ).addClass('upload-state-done');
});

// 文件上传失败，显示上传出错。
uploader.on( 'uploadError', function( file ) {
    var $li = $( '#'+file.id ),
        $error = $li.find('div.error');

    // 避免重复创建
    if ( !$error.length ) {
        $error = $('<div class="error"></div>').appendTo( $li );
    }

    $error.text('上传失败');
});

// 完成上传完了，成功或者失败，先删除进度条。
uploader.on( 'uploadSuccess', function( file,response) {
	$("#imgname").val(response.filerealname);
	$.ajax({
		type:"POST",
		url:"${ctx}/goods/save.do",
		data:$("#form").serialize(),
		success:function(data){
              alert("保存成功");
              window.location.href='${ctx}/views/goods/goodsInfoList.jsp';
		}
	})
});

});  

</script>
<title>房源信息</title>
</head>
<body>
	<div class="content">
		<div class="tit">修改房源信息</div>
		<div class="tit_down">
			<form id="form">
			<input id="good_id" type="hidden" name="id" value="${ob.id}">
			 <input id="imgname" type="hidden" name="img_src" value="${op.filepath}">
				<table class="table2 cus_info work" id ="tableinfo" style="margin:0 auto">
					<tr>
						<td style="width:70px;"><label for="" class="lb" style="width:70px;"><span style = "color: Red">*</span><span>房名：</span></label></td>
						<td ><input type="text" name="gname" value="${ob.gname}"/></td>
					</tr>
					<tr>
						<td style="width:70px;"><label for="" class="lb" style="width:70px;"><span style = "color: Red">*</span><span>类型：</span></label></td>
						<td ><f:select name="gtype"
						sectionname="分类"  value="${ob.gtype}"></f:select></td>
					</tr>
					<tr>
						<td style="width:70px;"><label for="" class="lb" style="width:70px;"><span style = "color: Red">*</span><span>价格：</span></label></td>
						<td><input type="text" name="gunitprice" value="${ob.gunitprice}"/></td>
					</tr>
					<tr>
						<td style="width:70px;"><label for="" class="lb" style="width:70px;"><span style = "color: Red">*</span><span>简介：</span></label></td>
						<td><input type="text" name="gbriefintro" value="${ob.gbriefintro}"/></td>
					</tr>
					<tr>
						<td style="width:70px;"><label for="" class="lb" style="width:70px;"><span style = "color: Red">*</span><span>详细：</span></label></td>
						<td><input type="text" name="gdetailintro" value="${ob.gdetailintro}"/></td>
					</tr>
										<tr>
						<td style="width:70px;"><label for="" class="lb" style="width:70px;"><span style = "color: Red">*</span><span>地址：</span></label></td>
						<td><input type="text" name="gsize" value="${ob.gaddr}"/></td>
					</tr>
					<tr>
						<td style="width:70px;"><label for="" class="lb" style="width:70px;"><span style = "color: Red">*</span><span>面积：</span></label></td>
						<td><input type="text" name="gsize" value="${ob.gsize}"/></td>
					</tr>
						<tr>
						<td style="width:70px;"><label for="" class="lb" style="width:70px;"><span>折扣：</span></label></td>
						<td><input type="text" name="discount" value="${ob.discount}"/></td>
					</tr>
					<tr>
						<td style="width:70px;"><label for="" class="lb" style="width:70px;"><span style = "color: Red">*</span><span>是否推荐：</span></label></td>
						<td ><f:select name="gisfocus"
						sectionname="是否推荐"  value="${ob.gisfocus}"></f:select></td>
					</tr>
						<tr>
						<td>上传图片：  <!--dom结构部分-->
                         <div id="uploader-demo">
                         <div id="fileList" class="uploader-list"></div></div>
                         <div id="filePicker">选择</div>
                         </td>
						<td>
                         		<div id="imgList" > <img src="${op.filepath}" width="300px" height="200px" name="old"></img> </div>
                         </td>
					</tr>
			
						
				</table>
			</form>
	       
			<div style="width: 201px; margin: 0 auto">
				<button class="btn1" onclick="save()">保存</button>
				<button class="btn2" onclick="cancel()">取消</button>
				<div class="clear"></div>
			</div>
			
		</div>
	</div>
</body>
</html>