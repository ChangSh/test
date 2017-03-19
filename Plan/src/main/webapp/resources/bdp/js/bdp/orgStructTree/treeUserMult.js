		var setting = {
			isSimpleData : true, //数据是否采用简单 Array 格式，默认false
			treeNodeKey : "id", //在isSimpleData格式下，当前节点id属性
			treeNodeParentKey : "pid", //在isSimpleData格式下，当前节点的父节点id属性
			showLine : true, //是否显示节点间的连线
			checkable : true, //每个节点上是否显示 CheckBox 
			data: {
				simpleData: {
					enable: true
				} 
			},
			callback: {
				onClick: zTreeOnClick
			},
			 async:{  
				 enable:true,
				 autoParam:["id"],
    		     type:"post",  
    		     url:contextPath+"/getDepartment.do"  
			 }   
		};
		var zTree;
		var treeNodes;
		$(function(){
			 $.ajax({
    			 async : false,
    			 cache:false,
    			 type: 'POST',
    			 dataType : "json",
    			 url:contextPath+"/getCompanyAll.do",//请求的action路径
    			 error: function () {//请求失败处理函数
    				 alert("请求失败");
    			  },
    			 success:function(data){ //请求成功后处理函数。 
     			 $.each(data, function(index, content)
     			 {  
     				$.extend(content,{icon:contextPath+"/resources/images/fang.png",isParent:true,type:1}); 
     			 });
     			 var json=JSON.stringify(data);
     			 treeNodes = data;
    			 exTree();
    			 }
			 });
			 putRightUser();
		}); 
		function putRightUser(){
			var putNames="["+window.parent.gethiddenUser()+"]";
			if(putNames!=""){
				var putName=eval("data="+putNames);
				for(var i=0;i<putName.length;i++){
					$("#listRight").append("<option value='"+putName[i].id+"'>"+putName[i].lastname+"</option>");  
				}
			 
			}
			
		}
		function exTree(){ 
			$.fn.zTree.init($("#zTreeUserMult"), setting, treeNodes); 
		}
		function zTreeOnClick(event, treeId, treeNode) {
			 var clickURL="";
			 if(treeNode.type){
				 clickURL=contextPath+"/getUsersByCompId.do";
			 }else{
				 clickURL=contextPath+"/getUsersByDepId.do";
			 }
			 $.ajax({
    			 async : false,
    			 type: 'POST',
    			 dataType : "json",
    			 data:{id:treeNode.id},
    			 url:clickURL,
    			 error: function () {//请求失败处理函数
    				 alert("请求失败");
    			  },
    			 success:function(data){ //请求成功后处理函数。 
    				 exUser(data);
    			 }
			 });
		}; 
		function exUser(data){  
			 $("#listLeft").empty();
		   // var jsonStr=strToJson("[{id:1,name:'小一'},{id:2,name:'小二'},{id:2,name:'小二'},{id:2,name:'小四'}]");  
		    var jsonStr=data;  
		    for(var i=0;i<jsonStr.length;i++){  
		      $("#listLeft").append("<option value='"+jsonStr[i].id+"'>"+jsonStr[i].lastname+"&nbsp;&nbsp;&nbsp;<span style='spanJoin'>"+jsonStr[i].join+"</span></option>");  
		    }  
		  };  
	function strToJson(str){  
		var json = eval('(' + str + ')');  
		return json;  
		 } 
	function toRight() {
		var nodes = $("#listLeft option:selected");
		var flag = true;
		var obj = document.getElementById('listRight');
		var options = obj.options;
		for ( var i = 0, len = options.length; i < len; i++) {
			var opt = options[i];
			if (nodes.val() === opt.value) {
				alert("该人已经选过了");
				flag = false;
				nodes.remove();
				return;
			}
		}
		if (flag) {
			$("#listRight").append(nodes);
		}
	}
	//全部右移
	function toAllRight() {
		var nodesleft = $("#listLeft option");
		var nodesright = $("#listRight option");
		if (nodesright.length > 0) {
			alert("已过滤已存在的人员");
			for ( var i = 0; i < nodesleft.length; i++) {
				var opt = $('#listLeft option:first');
				var flag = true;
				for ( var j = 0; j < nodesright.length; j++) {
					var opt2 = nodesright.eq(j);
					if (opt.val() == opt2.val()) {
						flag = false;
						opt.remove();
						break;
					}
				}
				if (flag) {
					$("#listRight").append(
							"<option value='" + opt.val() + "'>" + opt.text()
									+ "</option>");
					opt.remove();
				}
			}
		} else {
			$("#listRight").append(nodesleft);
		}
	};
	//lable值
	function getName(){
		var rightData = document.getElementById('listRight');
		var data=[];
		for(var i=0;i<rightData.length;i++){
			var optionText=(rightData[i].text.substring(0,rightData[i].text.indexOf("|"))).replace(/^\s+|\s+$/g,"");
			data.push(optionText);
		}
		var datas=data.join(",");
		return datas;
	}
	// 用于隐藏域放置人员信息
	function getDataALl(){
		var rightData = document.getElementById('listRight');
		var userDataAll="";
		for(var i=0;i<rightData.length;i++){
			userDataAll+="{id:"+rightData[i].value+",lastname:'"+rightData[i].text+"'},";
		}
		return userDataAll;
	}
	function toLeft() {
		var nodes = $("#listRight option:selected");
		var flag = true;
		var obj = document.getElementById('listLeft');
		var options = obj.options;
		for ( var i = 0, len = options.length; i < len; i++) {
			var opt = options[i];
			if (nodes.val() === opt.value) {
				alert("该人右边已经存在");
				flag = false;
				nodes.remove();
				return;
			}
		}
		if (flag) {
			$("#listLeft").append(nodes);
		}
	}
	//全部左移
	function toAllLeft() {
		var nodesleft = $("#listLeft option");
		var nodesright = $("#listRight option");
		if (nodesleft.length > 0) {
			alert("已过滤已存在的人员");
			for ( var i = 0; i < nodesright.length; i++) {
				var opt = $('#listRight option:first');
				var flag = true;
				for ( var j = 0; j < nodesleft.length; j++) {
					var opt2 = nodesleft.eq(j);
					if (opt.val() == opt2.val()) {
						flag = false;
						opt.remove();
						break;
					}
				}
				if (flag) {
					$("#listLeft").append(
							"<option value='" + opt.val() + "'>" + opt.text()
									+ "</option>");
					opt.remove();
				}
			}

		} else {
			$("#listLeft").append(nodesright);
		}
	};
	function up() {
		$.each($("#listRight option:selected"), function() {
			var onthis = $(this);
			var getUp = onthis.prev();
			$(onthis).after(getUp);
		});
	}
	function down() {
		$.each($("#listRight option:selected"), function() {
			var onthis = $("#listRight option:selected");
			if(onthis.size()==1){
			var getdown = onthis.next();
			$(getdown).after(onthis);
			}
		});
	}
 function userSearcher(){
	   var lastname =$("#sercheMultName").val();
	   $.ajax({
			 async : false,
			 type: 'POST',
			 dataType : "json",
			 data:{name:lastname},
			 url:contextPath+"/LikeUserName.do",
			 error: function () {//请求失败处理函数
				 alert("请求失败");
			  },
			 success:function(data){ //请求成功后处理函数。 
				 exUser(data);
			 }
		 });
  }