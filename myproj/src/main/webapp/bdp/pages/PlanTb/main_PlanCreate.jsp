<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../commons/statics.jsp"%>
  <link href="<%=contextPath%>/resources/bdp/css/planTb/style01.css" rel="stylesheet" type="text/css" />
  <link href="<%=contextPath%>/resources/bdp/css/planTb/style05.css" rel="stylesheet" type="text/css" />
  <link href="<%=contextPath%>/resources/bdp/css/smartMenu.css" rel="stylesheet" type="text/css" />
  <script src="<%=contextPath%>/resources/bdp/js/jquery-smartMenu.js"></script>
  <style type="text/css">
        .toptitle {
            font-size: 20px;
            font-weight: bold;
        }

        .leftalign {
            text-align: left;
        }

        .rightalign {
            text-align: right;
        }

        .hei {
            font-weight: bold;
        }
        .color1{
        background-color:#CFCFCF;height:10px;width:10px;margin-top:6px;float:left
        }
        .color2{
        background-color:#EEAD0E;height:10px;width:10px;margin-top:6px;float:left
        }
        .color3{
        background-color:#00FF00;height:10px;width:10px;margin-top:6px;float:left
        }
    </style>

</head>
	
<body>
    <div id="BgDiv" style="margin:10px 0px 10px 15px ">
    <table>
    <tr>
	    <td style="width:250px">
	      <div style="font-size:15px;"> 当前城市:<span>xxxxxx</span></div>
	    </td>
	    <td style="width:250px">
	      <div style=" font-size:15px;"> 配送额度:<span>xx</span>%</div>
	    </td>
	    <td style="width:250px">
	      <div style=" font-size:15px;"> 订单总额度:<span>xx</span>%</div>
	    </td>
	    <td style="width:250px">
	      <div style="font-size:15px;"> 排期客户名称:<span>xxxxxxxx</span></div>
	    </td>
	    <td style="width:500px">
	         <div style=" font-size:10px;float:left"><div class="color1"></div><span>代表已确定排期&nbsp;</span></div>
             <div style=" font-size:10px; float:left"><div class="color2"></div><span>代表预约未确定排期及天数&nbsp;</span></div>
             <div style="font-size:10px;float:left"><div class="color3"></div><span>代表当前选定排期及天数&nbsp;</span></div>
	    </td>
    </tr>
    <tr>
	    <td>
	     <div style=" font-size:15px;"> 对应楼盘ID:<span>xx</span></div>
	    </td>
	    <td colspan="4">
	     <div style="font-size:15px;"> 项目名称:<span>xx</span></div>
	    </td>
    </tr>
      <tr>
	    <td  colspan="5" >
	     <div style=" font-size:15px;"> 
	                选择预定年月:<select style="font-size:12px">
	     <option value="2017">2017</option>
	     </select>&nbsp;年&nbsp;
	     <select style="font-size:12px">
	     <option value="1">1</option>
	     </select>&nbsp;月&nbsp;&nbsp;
		     所属频道: <select style="font-size:12px" id="groupwebsite">
		     </select>&nbsp;&nbsp;
		      广告位: <select style="font-size:12px" id="adplace">
		     </select>&nbsp;&nbsp;
		      创意形式: <select style="font-size:12px" id="banner">		    
		     </select>&nbsp;&nbsp;
		       刊例价类型: <select style="font-size:12px" id="adplacePrice">
		     </select>&nbsp; &nbsp;是否半价: <input type="checkbox" id="ifhalf"  style="vertical-align:text-bottom; margin-bottom:2px; margin-bottom:-2px\9"/>
	     </div>
	    </td>
    </tr>
    <tr>
	     <td  colspan="5">&nbsp;&nbsp; 
		     <button style="font-size:12px" onclick="loadData()">载入数据</button> &nbsp;&nbsp;  <button style="font-size:12px" onclick="removeData()">删除数据</button> </td>
	</tr>
    </table>       
    </div>
    <!--页面 start-->
  
        <div class="huizong" style="width: 100%;">
   <form id="form_plantb">
    <table width="100%" border="1" cellspacing="0" cellpadding="0" id="tb">
	
        
	</table>
	        <input type="hidden" id="OrderCode">
   </form>
		<div style="text-align:center;margin-top:20px;margin-bottom:30px">
		  <button style="width:90px">返回上一步</button>&nbsp;
		  <button style="width:90px">继续预定</button>&nbsp;
		  <button style="width:110px" onclick="submitTb()">完成预定并提交</button>&nbsp;
		  <button style="width:90px">查看</button>
		</div>
        </div>

    <!--页面 end-->

</body>

  <script type="text/javascript">
	      var orderid=0;//参数索引
        $(document).ready(function () {     
        	//获取订单信息 需要传到后台
        	$("OrderCode").val('${info.getOrderCode()}');

            //添加table
           loadData();
            //页面头部显示
        	 
      	  //	所属频道
      	   ajaxHander("../Channelgroup/groupwebsite.do",{},"POST");
      	  //	广告位
      	   ajaxHander("../AdPlace/queryAdPlaceList.do",{},"POST");
      	  //	创意形式
      	   ajaxHander("../Banner/getAllBanner.do",{},"POST");
      	  //	刊例价类型
      	  var date=new Date();
      	  var datetemp=[];
      	  for(var i=-3;i<=3;i++){
      		  datetemp.push(date.getFullYear()+i);
      	  }
      	  setSelect("#adplacePrice",datetemp,"adplacePrice");//设置内容
      	  $("#adplacePrice").val(new Date().getFullYear());
  
        });
        
        function loadData(){
        	removeData();  
        	//重新加载时 初始化 参数索引
        	orderid=0;
        	var ifhalf=$("#ifhalf").is(':checked')==true?1:0;
        	var data={'starttime':'${info.getServiceDateStart()}','endtime':'${info.getServiceDateEnd()}','ifhalf':ifhalf};
        	ajaxHander("../AdPlacePrice/GetAdPlacePriceList.do",data,"POST");
        }
        function removeData(){
        	$("#tb").html("");
        }
        
        function addEvent(date){
        	  //右键事件 
            var    opertionn = {
                    name: "",
                    offsetX: 2,
                    offsetY: 2,
                    textLimit: 8,
                    beforeShow: $.noop,
                    afterShow: function(){
                    	$(".smart_menu_li_separate").remove();
                    	if(this.bgColor=="#00FF00"&&(this.firstChild.nodeValue=="1.0"||this.firstChild.nodeValue=="0.5")){
                    		$("#预定").hide();
                    		$("#取消预定").show();
                    	}
                    	else{
                    		$("#预定").show();
                    		$("#取消预定").hide();
                    	}
                    }
                };

                var MenuData = [
							            [ {
							                text: "预定",
							                func: function () {
							                	 //处理多选
							                	var len=this.parentNode.childNodes.length;
							                	var nodes=this.parentNode.childNodes;
							                	var row=this.lastChild.textContent;
							                	var id=this.parentNode.id.replace('first','');
							                	var dateclumn=document.getElementById("dateclumn"+id);//解决不同浏览器的索引值不一样问题
							                	var textcon=nodes[0].childNodes[3].value;
							                	textcon=textcon>0?"0.5":"1.0";
							                	
							                	this.outerHTML="<td class='tdmenu' bgcolor='#00FF00'>"+textcon+"<input type='hidden' name='e["+row+"].d' value='"+dateclumn.childNodes[this.cellIndex].innerHTML +"'/></td>";
							                	
							                	for(var i=5;i<len;i++){//处理多选
							                		var clumn=dateclumn.childNodes[nodes[i].cellIndex].innerHTML;
							                		if(nodes[i].bgColor=="#00FF00"&&nodes[i].textContent!=textcon){	
									                	nodes[i].outerHTML="<td class='tdmenu' bgcolor='#00FF00'>"+textcon+"<input type='hidden' name='e["+row+"].d' value='"+clumn +"'/></td>";
							                		}
							                	}
							                	
							                   $(".tdmenu").smartMenu(MenuData, opertionn); 
							                }
							            }],
							            [ {
							                text: "取消预定",
							                func: function () {
							                	 this.outerHTML="<td class='tdmenu'  bgcolor='white' onclick='fun(this)'>&nbsp;</td>";
							                   $(".tdmenu").smartMenu(MenuData, opertionn); 
							                }
							            }]
       								]; 
                $(".tdmenu").smartMenu(MenuData, opertionn); 	
        }
        

        function addTable(datalist,date,ifhalf){
        	//加载头部
            var top=["&nbsp;","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","AA","AB","AC","AD","AE","AF","AG","AH","AI"];
            var toptemp="<tr>";
            for(var i=0;i<top.length;i++){
            	if(top[i]=="A"||top[i]=="B"){
            		toptemp+='<td  width="100" style="text-align:center;">'+top[i]+'</td>';
            	}
            	else if(top[i]=="C"||top[i]=="D"){
            		toptemp+='<td  width="40" style="text-align:center;">'+top[i]+'</td>';
            	}
            	else{
            		toptemp+='<td  width="25" style="text-align:center;">'+top[i]+'</td>';
            	}
    		}
            toptemp+="</tr>";            
            $("#tb").append(toptemp);
            
            //加载年份
            $("#tb").append(
            		"<tr><td style='text-align:center;'>1</td>"+tds(4)
		            +"<td style='text-align:left;' colspan='31'><em>"+date.c_year+"年"+date.c_month+"月</em></td>"
		            +"</tr>"
            );
            
            //加载日期列
        	dateclumn(date.c_year,date.c_month);
            //添加底部
            $("#tb").append('<tr id="tbEnd'+date.c_year+date.c_month+'">'+
            	       '<td style="text-align:center;" colspan="36" id="tbEnd_td_'+date.c_year+date.c_month+'">'+           	   
            	       '</td>'+
            	       '</tr>');
            //添加数据部分
            var tempdata=getGroup(date,datalist);//取出当前月份数据          
            var flog=false;
            var number=0;//行数计算用         
        	var num=tempdata.length;
        	var ifh=1;//初始化上下午标志
            if(tempdata.length!=0&&tempdata[0].reserveHour!=null){
            	 var id= "dateclumn"+date.c_year+(date.c_month);
         		 var node=document.getElementById(id);
         		 var tt=[];
	         	 for(var i=1;i<node.childNodes.length;i++){
	         		var v=node.childNodes[i].innerHTML;
	         		if(v){tt.push(v);}
	         	 }         	
	            for(var i=0;i<num;i++){
	            		 $("#tbEnd"+date.c_year+date.c_month).before(' <tr id="first'+date.c_year+date.c_month+'">'+
	                             '<td style="text-align:center;">'+(number+4)+
	                             '<input type="hidden" name="e['+orderid+'].year" value="'+date.c_year+'"/>'+
	                  	         '<input type="hidden" name="e['+orderid+'].month" value="'+date.c_month+'"/>'+
	                  	         '<input type="hidden" name="e['+orderid+'].reservehour" value="'+tempdata[i].reserveHour+'"/>'+
	                  	     	 '</td>'+
	                             '<td>'+tempdata[i].channel+'</td>'+
	                             '<td>'+tempdata[i].adplace+'</td>'+
	                             '<td>'+tempdata[i].price+'</td>'+
	                             '<td>'+tempdata[i].isPay+'</td>'+
	                             tds(tt.length,tempdata[i],orderid)+tds(31-tt.length)+
	                          "</tr>");
	            		 number++;
	            		 orderid++;
	            	} 
            }
            else{//初始化没有订单的排期
            	if(tempdata.length==0){
            		tempdata=getfirstGroup(datalist);
            		num=tempdata.length;
            	}
			        	   for(var i=0;i<num;i++){			        		 
			        		   for(var j=0;j<ifhalf+1;j++){
			        			   $("#tbEnd"+date.c_year+date.c_month).before(' <tr id="first'+date.c_year+date.c_month+'">'+
				                           '<td style="text-align:center;">'+(number+4)+
				                           '<input type="hidden" name="e['+orderid+'].year" value="'+date.c_year+'"/>'+
				                  	       '<input type="hidden" name="e['+orderid+'].month" value="'+date.c_month+'"/>'+
				                  	       '<input type="hidden" name="e['+orderid+'].reservehour" value="'+(ifhalf==1?ifh:ifhalf)+'"/>'+
				                           '</td>'+
				                           '<td>'+tempdata[i].channel+'</td>'+
				                           '<td>'+tempdata[i].adplace+'</td>'+
				                           '<td>'+tempdata[i].price+'</td>'+
				                           '<td>'+tempdata[i].isPay+'</td>'+
				                           tds(31,[],orderid)+
				                        "</tr>");
			        			   number++;
					        	   orderid++;  
					        	   ifh++;
			        		   }
			        		   ifh=1;//重置
			        	   } 
               }
        }
        
        
        var tds=function (num,data){
        	var values="";       	
        	var columns=[];
        	 if(data!=undefined){
        		 columns=getColumns(data);
        	 }
        	 for(var t=0;t<num;t++){
        		 if(data!=undefined){
            		 if(columns.length>t){
            			 if(columns[t]==1){
            				 values+="<td class='tdmenu' bgcolor='#00FF00'>"+(data.allowance.substring(0,3))+"</td>";
            			 } else if(columns[t]>=2){
            				 values+="<td bgcolor='#CFCFCF'>"+(data.allowance.substring(0,3))+"</td>";
            			 }
            			 else{
            				 values+="<td class='tdmenu'  bgcolor='white' >&nbsp;</td>";
            			 }
            		 }
            		 else{
            			 values+="<td class='tdmenu'  bgcolor='white' >&nbsp;</td>";
            		 }
        		 }
        		 else{
        		 values+="<td>&nbsp;</td>";
        		 }
             }
        	return values;
        };
        
        //日期和星期对应
        function dateclumn(c_year,c_month){
        	$("#year_month").text(c_year+"年"+c_month+"月");      
        	if(c_month>12){
        		c_month=1;
        		c_year++;
        	}
            var date = new Date(c_year+"/"+(c_month+1)+"/01");   
            date.setDate(0);//设置为0 得到月的天数
         	var day=date.getDate();
         	var d_en='<tr id="dateclumn'+c_year+c_month+'"><td style="text-align:center;">2</td><td></td><td></td><td></td><td></td>';
         	var d_ch='<tr id="cn_dateclumn'+c_year+c_month+'" style="text-align:center;"><td>3</td><td>所属频道</td><td>广告位</td><td>价格</td><td>付费</td>';
         	for(var d=1;d<=day;d++){
         		date.setDate(d);
         		var w=date.getDay();
         		d_en+='<td class="top3">'+d+'</td>';
         		if(w==0||w==6){
         			d_ch+='<td class="top3"><em>'+"日一二三四五六".charAt(date.getDay())+'</em></td>';
         		}
         		else{
         			d_ch+='<td class="top3">'+"日一二三四五六".charAt(date.getDay())+'</td>';
         		}
         	}
 			for(var dz=0;dz<(31-day);dz++){
 				d_en+='<td class="top3"></td>';
 				d_ch+='<td class="top3"></td>';
         	}
 			d_en+='</tr>';
 			d_ch+='</tr>';
 			$("#tb").append(d_en);
 			$("#tb").append(d_ch);
        }
        
        function ajaxHander(url,param,type,value){
     	   $.ajax({
     			 async : false,
     			 type: type,
     			 beforeSend: function(){				 
     				    },
     	         data:param,
     			 url:url,
     			 success:function(data){ //请求成功后处理函数。 		
     				 if(url=="addPlanTb.do"){
     					alert(data.msg);
     					window.location.reload();
     				 }
	     			 if(url.indexOf("groupwebsite")>0){
	     				setSelect("#groupwebsite",data,"groupwebsite");//设置内容
	     			 }
	     			 if(url.indexOf("queryAdPlaceList")>0){
	     				setSelect("#adplace",data,"adplace");//设置内容
	     			 }
	     			 if(url.indexOf("getAllBanner")>0){
	     				setSelect("#banner",data,"banner");//设置内容
	     			 }
	     			 if(url.indexOf("GetAdPlacePriceList")>0){
	     				var startDate=new Date('${info.getServiceDateStart()}');
	     	        	var endDate=new Date('${info.getServiceDateEnd()}');
	     				var numTemp=[];
	     		        	for(var i=0;i<=getNums(startDate,endDate);i++){
	     		     		     var c_year;
	     		   		  		 var c_month;
	     		        		 c_year=startDate.getFullYear();
	     		                 c_month=startDate.getMonth()+1+i;
	     		                 if(c_month>12){
	     		                	c_month=1;
	     		                	c_year++;
	     		                 }
	     		                 numTemp.push({c_year:c_year,c_month:c_month});
	     		        	} 
	     		        	var m_data=setGroup(numTemp,data);//按月份分组
	     		        	for(var i=0;i<numTemp.length;i++){
	     		        		if(m_data.length>0){
	     		        			   addTable(m_data,numTemp[i],param.ifhalf);
		     		                   //注册事件
		     		                   addEvent(numTemp[i]);
	     		        		}
	     		            }
	     			 }
	     			
     			 }
     		 }); 
        }
        
        //多选赋值
        function setSelect(id,obj,type){	 
    		if($(id).val()==null){
    			var len=obj.length;
    		 	var temp="";
    			for(var i=0 ;i<len;i++){
    				if(type=="groupwebsite"){
    					temp+="<option value='"+obj[i].sitegrouppy+"-"+obj[i].websitepy+"'>"+obj[i].sitegroupname+"/"+obj[i].websitename+"</option>";
    				}
    				if(type=="adplace"){
    					temp+="<option value='"+obj[i].id+"'>"+obj[i].name+"</option>";
    				}
    				if(type=="banner"){
    					temp+="<option value='"+obj[i].id+"'>"+obj[i].name+"</option>";
    				}
    				if(type=="adplacePrice"){
    					temp+="<option value='"+obj[i]+"'>"+obj[i]+"</option>";
    				}
    				
    			}
    			$(id).html(temp);
    		} 
    }
       //保存
       function submitTb(){
    	   var data=$("#form_plantb").serialize();
    	   ajaxHander("addPlanTb.do",data,"POST"); 
    	   
       }
       
  	 //左键选中颜色变换
  	 function fun(e){
  		 var id= "dateclumn"+e.parentNode.id.replace('first','');
  		 var node=document.getElementById(id);
  		 var v=node.childNodes[e.cellIndex].innerHTML;
  		 if(v){
  			 if(e.bgColor=="white"){
  	  			e.bgColor='#00FF00';
  	  		 }
  	  		 else{
  	  			e.bgColor='white';
  	  		 } 
  		 }  	
  	 }
  	 //获取月份差
  	 function getNums(start,end){
  		return (-start.getFullYear()+end.getFullYear())*12-start.getMonth()+end.getMonth();
  	 }
  	 //获取1-31天数据
  	 function getColumns(obj){
  		var temp=[];
  		if(obj.length!=0){
  			for (var Key in obj){
  	  			if(Key.length<=3){//把1-31列数据放到数组中
  	  				temp.push(obj[Key]);
  	  			}
  	  	  	}
  		}else{
  			for (var k=0;k<31;k++){
  	  			temp.push(0);//初始化没预定排期
  	  	  	}
  		}
  		return temp;
  	 }
  	 //按日期分组
  	 function setGroup(date,data){
  		 var m=[];
  		 for(var i=0;i<date.length;i++){
  			 var temp=[];
  			 for(var j=0;j<data.length;j++){
  				 if(data[j].years==date[i].c_year&&data[j].months==date[i].c_month){
  					temp.push(data[j]);
  				 }
  			 }
  			 if(temp.length!=0){
  				m.push(temp);	 
  			 }
  		 }
  		 if(m.length==0){//都没有预定数据
  	  		var temp=[];
  	  		for(var j=0;j<data.length;j++){
  	  			  temp.push(data);
  	  		}
	   		m=temp;	   	  		 
  		 }
  		 return m;
  	 }
	 //按日期取出
  	 function getGroup(date,data){
  		 var m=[];
  		 for(var j=0;j<data.length;j++){
  			 for(var i=0;i<data[j].length;i++){
  				if(data[j][i].years==date.c_year&&data[j][i].months==date.c_month){
  					m.push(data[j][i]);
  				 } 
  			 } 
  			 }
  		 return m;
  	 }
  	 //循环获取有效的数据
  	 function getfirstGroup(data){
  		 var m=[];
  		 var n=data.length;
  		 if(n==1){
  			 m=data[0]; 
  		 }
  		 else if(n>1){
  	  		 for(var i=1;i<n;i++){
  	  			if(data[i-1].length>=data[i].length){
  	  			 m=data[i];
  	  			}
  	  		 } 
  		 }
		 return m;
  	 }
    </script>
    </html>