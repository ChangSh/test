var tabpanel;  
var tabHeight = [];
var tab = [];

//用户退出登录
function logout() {
	var url = contextPath + "/logout.do";
	$.post(url, function(data) {
		if (data.success) {
			window.location.href = "http://work.fang.com/v2/login/loginAct.do?method=login";
		}
	})
}

//新增tab页 默认页面不重新加载
function addTab(title,url) {
	addTab(title,url,false);
}

//新增tab页
function addTab(title,url,isfresh) {
	//特殊处理 弹出新页
	if(title.indexOf("上传创意")>=0){
		window.open(url);
		return;
	}
	var code = 0;
	if(tab.length > 0) {
		code = tab[tab.length-1].code+1;
	}
	var flag = false;
    //title=title.replace(".","\\.");
    var id = '';
	for(var i = 0; i < tab.length; i++) {
		if(title == tab[i].title) {
			id = "tabs_" + tab[i].code;
			flag = true;
			break;
		}
	}
    //如果TAB不存在，创建一个新的TAB
    if (!flag) {
    	//加载等待时进度条
    	$("#progressBar").show();
    	id = "tabs_" + code;
    	if(url.indexOf("?") > -1){
    		url = url + "&isMenu=true";
    	}else{
    		url = url + "?isMenu=true";
    	}
		tabpanel.addTab({id: id,
				title: title || id,
				html: "<iframe id='contentIframe_"+id+"' name='contentIframe_"+id+"' src='"+url+"'style='width: 100%; height: 100%;' frameborder='0' onload='load()' scrolling='no'></iframe></p>",
				closable: true,
				disabled:false,
				icon:""
		       });
		tab.push({'title': title, 'code': code});
	} else { //已存在
		$(".tabpanel_mover li").each(function(index,element){
			if(id == $(this).attr("id")) {
				//激活对应的TAB
				tabpanel.show(index, true);
				return false;
			}
	    });
	    if(isfresh) {
	       $('#contentIframe_'+id).attr('src', url);
		}
	}
}
//当iframe加载完成之后隐藏进度条
function load(){
	$("#progressBar").hide();
}
 
//密码修改
$(function(){
	    $("#btn").click(function(){
        var modifyUrl = contextPath+"/modify.do";
        var currentPass  = $("#currentPass").val();
        var newPass = $("#newPass").val();
        var surePass = $("#surePass").val();
        
        if($.trim(currentPass) == ""){
        	alert("原密码不能为空！");
        	return false;
        }

          if($.trim(newPass)==""){
          alert("新密码不能为空！");
          return false;
          }

          if($.trim(surePass)==""){
          alert("确认密码不能为空！");
          return false;
          }

          if(newPass.length<5){
          alert("新密码不能小于5位！");
          return false;
          }

          if(newPass!=surePass){
          alert("两次输入的密码不一致！");
          return false;
          }
          
		$.post(modifyUrl, {
			currentPass : currentPass,
			newPass : newPass,
			surePass : surePass
		}, function(data) {
          if(data.data=="success"){
        	  alert("密码修改成功！");
        	  $("#modifyPass").modal("hide");
        	  $("#currentPass").val("");
              $("#newPass").val("");
              $("#surePass").val("");
        	  
          }else{
            $("#c_errorMsg")[0].innerHTML=data.errorMessage;
            $("#currentPass").val("");
            $("#newPass").val("");
            $("#surePass").val("");
            }
          });
      });
      
      var _url = contextPath + "/pageLoadingInfos.do";
	  $("#loadingInfoLink").click(function() {
		$.get(_url,function(result) {
			if (!result.hasErrors) {
				var loadingInfo = $("#pageLoadingInfo");
				loadingInfo.html("");
				var _html;
				var infos = result.data;
				for ( var i = 0, l = infos.length; i < l; i++) {
					var _o = infos[i];
					_html = "<li><a href='#'>"
							+ "<div>"
							+ "<strong>"
							+ _o.pageLoadingUrl
							+ "</strong> <span class='pull-right text-muted'> <em>"
							+ _o.pageLoadingTime
							+ "</em>"
							+ "</span>"
							+ "</div>"
							+ "</a></li>"
							+ "<li class='divider'></li>";
					loadingInfo.append(_html);
				}
			}
		});
	});
	  
	//add by jiaxx 2015-07-15 根据个人参数设置控制页面统计显隐
//    $.ajax({
//		type:"post",
//		url:"personalParamController/getPersonalParamInfo.do",
//		dataType:"json",
//		success:function(data){
//          if (!data.hasErrors) {
//    	     var flag = data.data.isShowLoadingTime;
//    	     if(flag) { //显示加载时间
//    	    	 $("#pagetj").show();
//    	     } else {	
//    	    	 $("#pagetj").hide();
//    	     }
//		  }  
//		}
//	}); 
});

//轮询策略，每隔60分钟请求一次服务器数据
//$(function(){
//	requestAjax();
//	  var handler = function(){
//		  requestAjax();
//		  }
//	  setInterval(handler,3600000);
//})

function requestAjax(){
         $.ajax({
         type:"post",
         url: contextPath+"/message/show.do", 
         dataType:"json",
         success:function(data){
        	 if(data.hasErrors==false){
        		 if(data.data.length>0){
            		 $("#dropdown").css("color","red");
            	 }else{
            		 $("#dropdown").css("color","black");
            	 }
            	 $("#ul li").each(function(){
                	   $(this).remove();
            	});
            	 
            	// 提醒消息最多显示5条记录
				for ( var i = data.data.length - 1; i >= 0
						&& i > data.data.length - 6; i--) {
					var li = "<li id='li'><a onclick=read('"
							+ data.data[i].id
							+ "')  data-backdrop='static'  data-toggle='modal'' data-target='#readMessage'  href='#' id='read'><div><i class='fa fa-comment fa-fw'></i>"
							+ data.data[i].title
							+ "<span class='pull-right text-muted small'>"
							+ data.data[i].createDate
							+ "</span></div></a></li><li class='divider'></li>";
					$("#ul").append(li);
				}
            	li = "<li><a class='text-center' href='#' onclick='addTab(\"公告管理\",\"message/main.do\")'><strong>更多</strong><i class='icon-hand-right'></i></a></li>";
            	$("#ul").append(li);
        	 }else{
        			 requestAjax();
        		   }
        	 }
         });
      }

//点击未读信息的下拉菜单是请求ajax
//$(function(){
//  $("#dropdown").click(function(){
//	  requestAjax();
//  });
//});   

//阅读消息之后
function read(id){
	$.ajax({
		type:"get",
		url:contextPath+"/message/message/afterRead.do?id="+id,
		dataType:"json",
		success:function(data){
			$('#readMessage').modal('show');
			$("#ti")[0].innerHTML=data.data.title;
			$("#content")[0].innerHTML=data.data.content;
		}
	})
}

//显示首页
function showHomePage() {
  addTab('主页','bdp/pages/welcome.jsp');
}

var fullScreenFlag = false; //全屏标志
$(function() {
  $('#divFullScreen').on('click', function() {	
	  fullScreenFlag = !fullScreenFlag;
	  if(fullScreenFlag) {
		  $("#wrapper").css("display", "none");
		  $(".page-content").css("margin-left", "0px");
	  } else {
		  $("#wrapper").css("display", "block");
		  if($(window).width() >= 980) {
		    $(".page-content").css("margin-left", "160px");
		  } 
	  }
  });
});			

//*********************************************************新模板设置左侧菜单******************************************//
var curMenuId = '01'; //初始化首页
var curExpandMenuId = ''; //当前展开的菜单
var openFlag = true;
function setMenu(menuId) { 
	if(menuId == curMenuId) {
		return;
	}
	var tmpMenu = menuId;
	//关掉当前展开的菜单(特殊情况)
	while(curExpandMenuId != "" && curExpandMenuId != curMenuId.substr(0,curMenuId.length-2)) { //当前有展开的，但不是已选中的
		$("#" + curExpandMenuId).removeClass("active");
		$("#" + curExpandMenuId).removeClass("open");
		$("#" + curExpandMenuId + "_a").find("span.arrow").removeClass("open"); 
		$("#" + curExpandMenuId + "_ul").css("display","none"); 
		curExpandMenuId = curExpandMenuId.substr(0,curExpandMenuId.length-2);
	}	
	//去掉当前选中菜单的样式
    while(curMenuId != "") { //不是根节点
    	$("#" + curMenuId).removeClass("active");
    	$("#" + curMenuId).removeClass("open");
    	$("#" + curMenuId + "_a").find("span.arrow").removeClass("open"); 
		$("#" + curMenuId + "_ul").css("display","none"); 
		if(curMenuId.length == 2) {
			$("#" + curMenuId + "_a").find("span.selected").remove();
		}
		curMenuId = curMenuId.substr(0,curMenuId.length-2);	
	}	
	//给新选中菜单添加样式
	while(menuId != "") { //1-不是根节点
		$("#" + menuId).addClass("active"); //新选中子菜单
		$("#" + menuId + "_a").find("span.arrow").addClass("open"); 
		$("#" + menuId + "_ul").css("display","block"); 
		if(menuId.length == 2) {
			$("#" + menuId + "_a").append('<span class="selected"></span>');
		}
		menuId = menuId.substr(0,menuId.length-2);
	}
    curMenuId = tmpMenu;
}

function removeOpen(menuId) {
	if(menuId == curMenuId.substr(0,curMenuId.length-2)) { //除去选中的菜单
		return;
	}
	var tmpMenu = curMenuId;
	//不关闭当前选中的菜单
    while(tmpMenu != "") { //不是根节点
		var tmpMenu = tmpMenu.substr(0,tmpMenu.length-2);
		$("#" + tmpMenu).removeClass("open");
		$("#" + tmpMenu+"_a").find("span.arrow").addClass("open"); 
		$("#" + tmpMenu+"_ul").css("display","block"); 
    }
    if(menuId != curExpandMenuId) {
    	curExpandMenuId = menuId;
    } else { 
    	curExpandMenuId = ""; //关闭
    }
    
}
 
function transData(a, idStr, pidStr, chindrenStr){    
  var r = [], hash = {}, id = idStr, pid = pidStr, children = chindrenStr, i = 0, j = 0, len = a.length;    
  for(; i < len; i++){    
    hash[a[i][id]] = a[i];    
  }    
  for(; j < len; j++){    
    var aVal = a[j], hashVP = hash[aVal[pid]];    
    if(hashVP){    
      !hashVP[children] && (hashVP[children] = []);    
      hashVP[children].push(aVal);    
    }else{    
      r.push(aVal);    
    }    
  }    
  return r;    
}     

/**
 * 获取所有的子节点
 */
function getChildren(node) {
	var childli = '';
	var pId = node.code;
	var pname = node.name;
	var purl = node.url;
	var str2 = '<li id="'+pId+'"><a id="'+pId+"_a"+'" href="#" onclick="removeOpen(\''+pId+'\');"><i class=""></i><span class="title">'+pname+'</span><span class="arrow"></span></a><ul id="'+pId+'_ul" class="sub-menu">';
	if(node.children != undefined) {
	  $("li[id='"+pId+"']").replaceWith(str2);
	  for(var j = 0; j < node.children.length; j++) {
	    var code = node.children[j].code;
	    var name = node.children[j].name;
		var url = node.children[j].url;
		childli = '<li id="'+code+'"><a id="'+code+"_a"+'" href="#" onclick="addTab(\''+name+'\',\''+url+'\');setMenu(\''+code+'\');"><i class=""></i><span class="title">'+name+'</span></a></li>';
		$("li[id='"+pId+"'] ul[id='"+pId+"_ul']").append(childli);
		getChildren(node.children[j]);	
		if(j == node.children.length-1) {
			$("li[id='"+code+"']").append('</ul></li>');
		}
	  }
	}
	return;
}

$(document).ready(function(){
	var jsonTree = transData(zNodes, 'code', 'pcode', 'children');    
	//console.log(jsonTree);    
	for(var i = 0; i < jsonTree.length; i++) {
		var pId = jsonTree[i].code;
		var pname = jsonTree[i].name;
		var purl = jsonTree[i].url;
		var img_x = jsonTree[i].imgPosition_x;
		var img_y = jsonTree[i].imgPosition_y;
		var img = jsonTree[i].imgUrl;
		var li = '<li id="'+pId+'"><a id="'+pId+"_a"+'" href="#" onclick="addTab(\''+pname+'\',\''+purl+'\');setMenu(\''+pId+'\');"><div style="float: left;  background-repeat:no-repeat;background-position:'+img_x+'px '+img_y+'px;height:20px;width:18px;"></div><span  class="title">'+pname+'</span></a></li>';	//background:url(http://js.soufunimg.com/ggcp/plan/images/'+img+'.gif);		
		$("#side-menu").append(li);
		if(jsonTree[i].children != undefined) { //有子节点
			getChildren(jsonTree[i]);
		}
	}
	//初始化首页
	$("#01").addClass("start active"); 
	$("#01_a").append('<span class="selected"></span>');
	
	tabpanel = new TabPanel({  
        renderTo:'tabs',  
        width:'100%',  
	    widthResizable:true,
        height:'200px', 
	    heightResizable:true,
	    autoResizable:true,
        border:'none',  
        active:0,
        items:[]
    });  
});
//**************************************************新模板设置左侧菜单**************************************************//

//根据名称关闭tab页
function closeTab(title) {
  var tabId = '';
  for(var i = 0; i < tab.length; i++) {
    if(title == tab[i].title) {
    	tabId = tab[i].code;
	    break;
	}
  }
  $("#tabs_"+tabId).find(".icon-remove").click();
}

//根据名称获取tab标签页的id
function getTabID(title) {
  var tabId = '';
  for(var i = 0; i < tab.length; i++) {
    if(title == tab[i].title) {
    	tabId = tab[i].code;
	    break;
	}
  }
  return tabId;
}

//设置页面高度
function initHeight(height) {
	var tabId = tabpanel.getActiveTab().id;
	$("#contentIframe_"+tabId).height(height);
    $("#tabs").height(height+100);
    $(".tabpanel_content").height(height);
    for(var i = 0; i < tabHeight.length; i++) {
      if(tabId == tabHeight[i].tabId) {
        if(height <= tabHeight[i].height) {
          return;
        } else {
          tabHeight.splice(i, 1);
        }
      } 
    }
    tabHeight.push({'tabId': tabId, 'height': height});
}

//根据tabId获取高度
function getTabHeight(tabId) {
  var height = 0;
  for(var i = 0; i < tabHeight.length; i++) {
    if(tabId == tabHeight[i].tabId) {
    	height = tabHeight[i].height;
	    break;
	}
  }
  return height>760 ? height : 760;
}