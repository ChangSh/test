/**
 * Free UI 的核心共用方法
 *
 */
var Free={
	ajaxError:function(xhr, ajaxOptions, thrownError){
			alert("Http status: " + xhr.status + " " + xhr.statusText + "\najaxOptions: " + ajaxOptions + "\nthrownError:"+thrownError + "\n" +xhr.responseText);	
		
	},
	ajax:function(par){
		var as = true;
		if (par.async=="false"){
			as = false;
		}
		$.ajax({
			   type: "POST",
			   url: par.url,
			   data: par.data,
			   success: par.success,
			   async: as,
			   error:Free.ajaxError
		   });
	},
	//关闭当前的对话框，方便使用
	closeThisDialog:function(){	
		//查询当前所有dialog并取dialogid最大的一个，也就是最前面的dialog并关闭
		var did =0;
		$('.dialog').each(function(i){
			if(did < (this.id.substring(7)*1))
				did = (this.id.substring(7)*1);
		 })
		$('#dialog-'+did).fadeOut('slow', function(){$(this).remove();});
	    $('#dialog-'+did+"-overlay").fadeOut('slow', function(){$(this).remove();});
	},
	//关闭所有对话框，方便使用
	closeAllDialog:function(){	
		//查询当前所有dialog并取dialogid最大的一个，也就是最前面的dialog并关闭
		var did =0;
		$('.dialog').each(function(i){
			if(did < (this.id.substring(7)*1))
				did = (this.id.substring(7)*1);
			
			$('#dialog-'+did).fadeOut('slow', function(){$(this).remove();});
		    $('#dialog-'+did+"-overlay").fadeOut('slow', function(){$(this).remove();});
		 })
	},
	//字符串格式化函数String.format
	replace : function(src) {
		if (arguments.length == 0)
			return null;
		var args = Array.prototype.slice.call(arguments, 1);
		return src.replace(/\{(\d+)\}/g, function(m, i) {
			return args[i];
		});
	},
	format:function(args) {
	    var result = this;
	    if (arguments.length > 0) {    
	        if (arguments.length == 1 && typeof (args) == "object") {
	            for (var key in args) {
	                if(args[key]!=undefined){
	                    var reg = new RegExp("({" + key + "})", "g");
	                    result = result.replace(reg, args[key]);
	                }
	            }
	        }
	        else {
	            for (var i = 0; i < arguments.length; i++) {
	                if (arguments[i] != undefined) {
	                    var reg = new RegExp("({[" + i + "]})", "g");
	                    result = result.replace(reg, arguments[i]);
	                }
	            }
	        }
	    }
	    return result;
	},
	validateSubmitAndClose:function(inputForm,msg){
		if (!msg){
			msg="保存成功";
		}
		inputForm.validate({
		        submitHandler:function(form){ 
		          	$.ajax({
		       		   type: "POST",
		       		   url: inputForm.attr("action"),
		       		   data: inputForm.serialize(),
		      			error:Free.ajaxError,
		       		   success: function(data){
		       	     	if(data=='ok'){
		       	     		if (parent.g)
			   	  			 parent.g.reload();
			   	  			 parent.layer.msg('操作成功', {time: 1000});
			   	  			 parent.layer.closeAll('iframe');
		       	     	}else{
			   	  			 parent.layer.msg('操作失败', {time: 1000});
		       	     	}
		       		}
		         });
		        }    
		    });
	},
	getCodeNames:function(){
		var arg="";
		for (var i = 1; i < arguments.length; i++) {
			 arg += "&sectionName=" + arguments[i];
        }
		var data=arg;
		Free.ajax({async:"false",
			url:arguments[0]+"/system/code/getNames.do",
			data:data,
			success:function(data){
				Free.CodeNames=data;
				//console.info(data);
			}
		});
	},
	CodeNames:{},
	getCodeName:function(sectionName,code){
		var res;
		$.each(Free.CodeNames, function(i,val){   
		      if(i==sectionName){
		    		$.each(val, function(ii,ival){   
		  		      if(ii==code){
		  			     res=ival;
		  		    }   
		  		});
		      }   
		});
		return res;
	},
	closewindow:function(){
		window.close();
	}, 
	// 实现grid组件checkBox选中多条并可以翻页不丢失
	// 一共5个参数: 1 后台json 2 自己的蓝子 3 checkboxClass名称  4 json里的id 5计数标签id
	basket:function(data,clickedArr,checkBoxClassName,idName,countLabelId){
		$("."+checkBoxClassName).click(function() { 
			var thisCb = $(this);
			if (thisCb.is(':checked')){
		       	$.each(data.Rows,function(i,o){
		       		if (thisCb.val()==o[idName]){
		       			clickedArr.push({key:o[idName],value:o});
		       		}
		       });
			}else{
    		    basketRemove(clickedArr,thisCb.val())
			}
		   	$("#"+countLabelId).text(clickedArr.length);
		}); 
		//翻页后将蓝子中的元素再选中checkBox
	   	$.each($("."+checkBoxClassName),function(i,cBox){
	   		$cBox = $(cBox);
	       	$.each(clickedArr,function(i,o){
	       		if ($cBox.val()==o.key){
	       	   		$cBox.attr("checked","true");
	       		}
	       });
	   });
	}
};

function basketRemove(clickedArr,key){
	//console.info("delete ==== "+clickedArr);
	$.each(clickedArr,function(i,o){
		//console.info("====");
		//console.info(clickedArr.length);
		//console.info(o);
   		if (o && o.key==key){
   			clickedArr.baoremove(i); //删除下标为1的元素 
   		}
   });

}
Array.prototype.baoremove = function(dx) 
{ 
    if(isNaN(dx)||dx>this.length){return false;} 
    this.splice(dx,1); 
} 
//调用： 
//var time1 = new Date().Format("yyyy-MM-dd");
//var time2 = new Date().Format("yyyy-MM-dd hh:mm:ss");  
Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
// grid中点删除按钮执行方法
function gridDelete(id,url){
	layer.confirm('是否删除', function(index){
       	$.ajax({
	  		   type: "POST",
	  		   url: url,
	  		   data: {id:id},
	  		   error: function(request) {
	              alert("操作失败");
	           },
	  		   success: function(data){
	  			 layer.msg('操作成功', {time: 1000});
	  			 g.reload();
	  		}
	  	});
	    layer.close(index);
	});  
}
function gridSearch(){

	//重新组建查询条件
    $(".jq_custom").each(function(i,o){
    	delete opt.param[$(o).attr("name")];//先删除opt里所有元素
    	var op= $(o).parent().find("select").val(); 
    	var last =$(o).attr("name").lastIndexOf("@");
    	$(o).attr("name","search@"+op+$(o).attr("name").substring(last));
    });
    $.each($('#gridQueryForm').serializeArray(),function(i,o){
    	opt.param[o.name]=o.value;
    });
	g.reload(opt);
}

$(function(){
	//render所有select
	$('select').selectOrDie();
});

//----------------------------------测试ie版本---------------------------
(function(jQuery) {
	if (jQuery.browser)
		return;
	jQuery.browser = {};
	jQuery.browser.mozilla = false;
	jQuery.browser.webkit = false;
	jQuery.browser.opera = false;
	jQuery.browser.msie = false;
	var nAgt = navigator.userAgent;
	jQuery.browser.name = navigator.appName;
	jQuery.browser.fullVersion = '' + parseFloat(navigator.appVersion);
	jQuery.browser.majorVersion = parseInt(navigator.appVersion, 10);
	var nameOffset, verOffset, ix;
	// In Opera, the true version is after "Opera" or after "Version"
	if ((verOffset = nAgt.indexOf("Opera")) != -1) {
		jQuery.browser.opera = true;
		jQuery.browser.name = "Opera";
		jQuery.browser.fullVersion = nAgt.substring(verOffset + 6);
		if ((verOffset = nAgt.indexOf("Version")) != -1)
			jQuery.browser.fullVersion = nAgt.substring(verOffset + 8);
	}
	// In MSIE, the true version is after "MSIE" in userAgent
	else if ((verOffset = nAgt.indexOf("MSIE")) != -1) {
		jQuery.browser.msie = true;
		jQuery.browser.name = "Microsoft Internet Explorer";
		jQuery.browser.fullVersion = nAgt.substring(verOffset + 5);
	}
	// In Chrome, the true version is after "Chrome"
	else if ((verOffset = nAgt.indexOf("Chrome")) != -1) {
		jQuery.browser.webkit = true;
		jQuery.browser.name = "Chrome";
		jQuery.browser.fullVersion = nAgt.substring(verOffset + 7);
	}
	// In Safari, the true version is after "Safari" or after "Version"
	else if ((verOffset = nAgt.indexOf("Safari")) != -1) {
		jQuery.browser.webkit = true;
		jQuery.browser.name = "Safari";
		jQuery.browser.fullVersion = nAgt.substring(verOffset + 7);
		if ((verOffset = nAgt.indexOf("Version")) != -1)
			jQuery.browser.fullVersion = nAgt.substring(verOffset + 8);
	}
	// In Firefox, the true version is after "Firefox"
	else if ((verOffset = nAgt.indexOf("Firefox")) != -1) {
		jQuery.browser.mozilla = true;
		jQuery.browser.name = "Firefox";
		jQuery.browser.fullVersion = nAgt.substring(verOffset + 8);
	}
	// In most other browsers, "name/version" is at the end of userAgent
	else if ((nameOffset = nAgt.lastIndexOf(' ') + 1) < (verOffset = nAgt
			.lastIndexOf('/'))) {
		jQuery.browser.name = nAgt.substring(nameOffset, verOffset);
		jQuery.browser.fullVersion = nAgt.substring(verOffset + 1);
		if (jQuery.browser.name.toLowerCase() == jQuery.browser.name
				.toUpperCase()) {
			jQuery.browser.name = navigator.appName;
		}
	}
	// trim the fullVersion string at semicolon/space if present
	if ((ix = jQuery.browser.fullVersion.indexOf(";")) != -1)
		jQuery.browser.fullVersion = jQuery.browser.fullVersion
				.substring(0, ix);
	if ((ix = jQuery.browser.fullVersion.indexOf(" ")) != -1)
		jQuery.browser.fullVersion = jQuery.browser.fullVersion
				.substring(0, ix);
	jQuery.browser.majorVersion = parseInt('' + jQuery.browser.fullVersion, 10);
	if (isNaN(jQuery.browser.majorVersion)) {
		jQuery.browser.fullVersion = '' + parseFloat(navigator.appVersion);
		jQuery.browser.majorVersion = parseInt(navigator.appVersion, 10);
	}
	jQuery.browser.version = jQuery.browser.majorVersion;
})(jQuery);


$(function(){
	
	// 设置jQuery Ajax全局的参数 
	$.ajaxSetup({
		 type: 'POST',
	     //请求失败遇到异常触发
	     error: function (xhr, status, e) { 
	    	 var str_status;
	    	 switch (xhr.status){  
	             case(500):  
	            	 str_status="服务器系统内部错误";  
	                 break;  
	             case(401):  
	            	 str_status="未登录";  
	                 break;  
	             case(403):  
	            	 str_status="无权限执行此操作";  
	                 break;  
	             case(408):  
	            	 str_status="请求超时";  
	                 break;  
	             default:  
	            	 str_status="未知错误";  
	         }  
	    	 console.info(xhr.responseText);
	    	 if (xhr.responseText.indexOf("用户为空")>0){
	    	    layer.alert('错误:当前用户会话过期,请重新登录', {icon:2});
	    	 }
	    	 layer.alert('系统出错，请与管理员联系', {icon:2});
	 }
	});
});
String.prototype.format = function(args) {
    var result = this;
    if (arguments.length > 0) {    
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
				if(!args[key]){
					args[key]="";
				}
                var reg = new RegExp("({" + key + "})", "g");
                result = result.replace(reg, args[key]);
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                    var reg = new RegExp("({[" + i + "]})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}
function c(ob){
	console.info(ob);
}