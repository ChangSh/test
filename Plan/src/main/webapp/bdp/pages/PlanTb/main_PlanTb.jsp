<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../commons/statics.jsp"%>
  <link href="<%=contextPath%>/resources/bdp/css/planTb/style01.css" rel="stylesheet" type="text/css" />
  <link href="<%=contextPath%>/resources/bdp/css/planTb/style05.css" rel="stylesheet" type="text/css" />
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
    </style>

  <style type="text/css">
        .adjustWidthSpan {
            float: right;
            width: 10px;
            color: #D1680F;
            display: inline;
            font-size: bold;
            margin-left: 5px;
            margin-right: 5px;
            cursor: pointer;
        }
    </style>
</head>
	
<body>
    <div id="BgDiv" style="margin-left:30px">
    <br> <button> 修改实际金额</button><br><br>  
    <span>所属销售:xx &nbsp; 销售助理:xx &nbsp; 总价折扣:35% &nbsp; 广告类型:商业广告 &nbsp;  是否占空位:是   &nbsp;当前城市:xx &nbsp; 配送比例:00%</span><br>
    <span>所属品牌名称:&nbsp; 排期客户名称: </span><br>
    <span>对应楼盘id: &nbsp; 产品线: &nbsp; 二级产品线:</span>
    </div>
        <br>
    <!--页面 start-->
    <form runat="server" id="form1">
        <div class="huizong" style="width: 1410px;">
    <table width="1050" border="1" cellspacing="0" cellpadding="0" id="tb">
	
    <tr>
        <td width="25" class="top1">&nbsp;</td>
        <td width="70" class="top1">A</td>
        <td width="150" class="top1"><span onclick="AdjustADWidth(event,30);" class="adjustWidthSpan">&gt;&gt;</span><span onclick="AdjustADWidth(event,-30);" class="adjustWidthSpan">&lt;&lt;</span>B</td>
        <td width="25" class="top1 ">C</td>
        <td width="25" class="top1">D</td>
        <td width="25" class="top1">E</td>
        <td width="25" class="top1">F</td>
        <td width="25" class="top1">G</td>
        <td width="25" class="top1">H</td>
        <td width="25" class="top1">I</td>
        <td width="25" class="top1">J</td>
        <td width="25" class="top1">K</td>
        <td width="25" class="top1">L</td>
        <td width="25" class="top1">M</td>
        <td width="25" class="top1">N</td>
        <td width="25" class="top1">O</td>
        <td width="25" class="top1">P</td>
        <td width="25" class="top1">Q</td>
        <td width="25" class="top1">R</td>
        <td width="25" class="top1">S</td>
        <td width="25" class="top1">T</td>
        <td width="25" class="top1">U</td>
        <td width="25" class="top1">V</td>
        <td width="25" class="top1">W</td>
        <td width="25" class="top1">X</td>
        <td width="25" class="top1">Y</td>
        <td width="25" class="top1">Z</td>
        <td width="25" class="top1">AA</td>
        <td width="25" class="top1">AB</td>
        <td width="25" class="top1">AC</td>
        <td width="25" class="top1">AD</td>
        <td width="25" class="top1">AE</td>
        <td width="25" class="top1">AF</td>
        <td width="25" class="top1">AG</td>
        <td width="25" class="top1">AH</td>
        <td width="60" class="top1">AI</td>
        <td width="80" class="top1">AJ</td>
        <td width="25" class="top1">AK</td>
        <td width="80" class="top1">AL</td>
        <td width="80" class="top1">AM</td>
    </tr>
    <tr>
        <td class="top1">1</td>
        <td width="125">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="22" align="center" class="toptitle">搜房一体化（推广与技术）服务建议书</td>
        <td colspan="10" align="center" class="leftalign">
            <p>订单编号：XFLZ20170209143273-0</p>
        </td>
        <td colspan="4" align="center" class="leftalign">合同编号：</td>
    </tr>
    <tr>
        <td class="top1">2</td>
        <td class="hei rightalign">客户全称：</td>
        <td class="hei leftalign">甘肃天星房地产开发有限公司</td>
        <td>&nbsp;</td>
        <td align="center" class="toptitle">&nbsp;</td>
        <td align="center" class="toptitle">&nbsp;</td>
        <td align="center" class="toptitle">&nbsp;</td>
        <td align="center" class="toptitle">&nbsp;</td>
        <td align="center" class="toptitle">&nbsp;</td>
        <td align="center" class="toptitle">&nbsp;</td>
        <td colspan="20" align="center" class="hei leftalign">推广项目名称：云府158</td>
        <td align="center" class="leftalign">&nbsp;</td>
        <td align="center" class="leftalign">&nbsp;</td>
        <td align="center" class="leftalign">&nbsp;</td>
        <td align="center" class="leftalign">&nbsp;</td>
        <td align="center" class="leftalign">&nbsp;</td>
        <td align="center" class="leftalign">&nbsp;</td>
        <td align="center" class="leftalign">&nbsp;</td>
        <td colspan="3" align="center" class="leftalign">单位：人民币/元</td>
        </tr>

       
</table>

        </div>
    </form>
    <!--页面 end-->

</body>

  <script type="text/javascript">
        $(document).ready(function () {      	
            //添加数据部分
            	//获取订单信息 需要传到后台	
        	$("#starttime").val('${info.getServiceDateStart()}');
        	$("#endtime").val('${info.getServiceDateEnd()}');
        	$("#orderid").val('${info.getOrderCode()}');
            var data={'starttime':'${info.getServiceDateStart()}','endtime':'${info.getServiceDateEnd()}','orderid':'${info.getOrderCode()}'};
            
            ajaxHander("../AdPlacePrice/GetAdPlacePriceListById.do",data,"POST");
        });
        
        function ajaxHander(url,param,type,value){
      	   $.ajax({
      			 async : false,
      			 type: type,
      			 beforeSend: function(){				 
      				    },
      	         data:param,
      			 url:url,
      			 success:function(data){ //请求成功后处理函数。 		
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
 	     		                 numTemp.push({c_year:c_year,c_month:c_month})
 	     		        	} 
 	     		        	if(data.length>0){
   		        			  addTable(data,numTemp,data.length);
   		        			}
      			 }
      		 }); 
         }
        
        
        function AdjustADWidth(e, changeWidth) {
            var event = $.event.fix(e);
            var elem = event.target;
            while ($(elem)[0].tagName.toLowerCase() != "td") {
                elem = $(elem).parent();
            }
            if ((changeWidth < 0 && elem.width() > 40) || (changeWidth > 0 && elem.width() < 500)) {
                elem.width(elem.width() + changeWidth);
            }
        }
      
        function addTotal(num){
            $("#footBegin").before('<tr id="dataListEnd">'+
         	       '<td class="top1">'+(num+6)+'</td>'+
         	       tds(34)+            	       
         	       '<td class="hei">总金额：</td>'+
         	       '<td colspan="2" class="hei">169,420.00</td>'+
         	       '<td class="hei">42,665.00</td>'+
         	       '<td class="hei">47,520.00</td>'+
         	    '</tr>');
	         $("#footBegin").before('<tr>'+
	      	       '<td class="top1">'+(num+7)+'</td>'+
	      	       tds(34)+             	               	      
	      	       '<td colspan="5" class="hei">优惠金额：0.00&nbsp;&nbsp;优惠政策：</td>'+
	      	    '</tr>');
	         $("#footBegin").before('<tr>'+
	      	       '<td class="top1">'+(num+8)+'</td>'+
	      	       tds(34)+             	       
	      	       '<td colspan="5" class="hei">实际金额：42,665.00&nbsp;&nbsp;服务费总额：42,665.00</td>'+
	      	    '</tr>');
        }
        function addSign(jf,yf,num){
        	var t="";
        	for(var i=0;i<jf.length;i++){
	  			t+='<tr>';
	  			t+='<td class="top1">'+(num+11+i)+'</td>';
	  			t+= tds(3);
	  			t+='<td colspan="25" class="hei">'+jf[i]+'：</td>';
	  			t+='<td colspan="5" class="hei">'+yf[i]+'：</td>';
	  			t+= tds(6);
	  			t+= '</tr>';
        	}
        	return t;
        }
        
        function addTable(data,date,num){
            //添加底部
 	        var	b='<tr  id="footBegin">';
	        	b+='<td class="top1">'+(num+16)+'</td>';
	        	b+=tds(39);
	        	b+='</tr>';
			$("#tb").append(b);
			
            //添加总金额部分
 			addTotal(data.length);
 		
             //空白部分
             $("#footBegin").before('<tr>'+
           	       '<td class="top1">'+(num+9)+'</td>'+
           	       tds(34)+             	       
           	       '<td colspan="5">&nbsp;</td>'+
           	    '</tr>');
             
             $("#footBegin").before('<tr>'+
            	       '<td class="top1">'+(num+10)+'</td>'+
            	       tds(39)+
            	       '</tr>');
         	$("#footBegin").before(addSign(["甲方","地址","电话","授权代表签字","（单位盖章）"],["乙方","地址","电话","授权代表签字","（单位盖章）"],num));
         	
            //加载日期列和数据
            var m_data=setGroup(date,data);//按月份分组
            for(var i=0;i<date.length;i++){
            	 var c_year=date[i].c_year;
                 var c_month=date[i].c_month;               
                 var dd = new Date(c_year+"/"+(c_month+1)+"/01");   
                 dd.setDate(0);//设置为0 得到月的天数
             	var day=dd.getDate();
             	var d_en='<tr><td class="top1">4</td>';
             	var d_ch='<tr><td class="top1">5</td>';
             	for(var d=1;d<=day;d++){
             		dd.setDate(d);
             		var w=dd.getDay();
             		if(w==0||w==6){
             			d_en+='<td class="top3"><em>'+d+'</em></td>';
             			d_ch+='<td class="top3"><em>'+"日一二三四五六".charAt(dd.getDay())+'</em></td>';
             		}
             		else{
             			d_en+='<td class="top3">'+d+'</td>';
             			d_ch+='<td class="top3">'+"日一二三四五六".charAt(dd.getDay())+'</td>';
             		}
             	}
     			for(var dz=0;dz<=(31-day);dz++){
     				d_en+='<td class="top3"></td>';
     				d_ch+='<td class="top3"></td>';
             	}
     			d_en+='</tr>';
     			d_ch+='</tr>';
     			 var lr=' <tr>';
     	        	lr+='<td class="top1">3</td>';
     	        	lr+='<td rowspan="3" class="hei">频道</td>';
     	        	lr+='<td rowspan="3" class="hei">形式</td>';
     	        	lr+='<td rowspan="3" class="hei">数量</td>';
     	        	lr+='<td colspan="31" class="leftalign" id="year_month">'+c_year+"年"+c_month+"月"+'</td>';
     	        	lr+='<td rowspan="3" class="hei">单价</td>';
     	        	lr+='<td rowspan="3" class="hei">总价</td>';
     	        	lr+='<td rowspan="3" class="hei">折扣</td>';
     	        	lr+='<td rowspan="3" class="hei">净价</td>';
     	        	lr+='<td rowspan="3" class="hei">赠送</td>';
     	        	lr+='</tr>';
     	        	$("#dataListEnd").before(lr+d_en+d_ch);
     	        	
     	           //加载数据
     	            var tempdata=getGroup(date[i],m_data);//取出当前月份数据          
     	            var flog=false;
     	            var number=0;//行数计算用         
     	        	var num=tempdata.length;
     	            if(tempdata.length!=0){      
     		            for(var t=0;t<num;t++){
     		            	var td="";
     		            	 if(tempdata[t].years==c_year&&tempdata[t].months==c_month){
     		            		 console.log(tempdata[t]);
     	                         td=tds(31,tempdata[t]);                        
     	                      }
     		            	 else{
     		            		 td=tds(31);
     		            	 }
     			         	   $("#dataListEnd").before(' <tr >'+
     		                           '<td class="top1">'+(number+4)+
     		                           '</td>'+
     		                           '<td>'+tempdata[t].channel+'</td>'+
     		                           '<td>'+tempdata[t].adplace+'</td>'+
     		                           '<td>'+0+'</td>'+
     		                          td+tds(5)+
     		                        "</tr>");
     		            		 number++;
     		            	} 
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
     	  			if(Key.replace("d","")>0){//把1-31列数据放到数组中
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
     				 else if(data[j].years==null&&data[j].months==null){
     					if(i==0){//没有预定排期的 不需要按月存
     						temp.push(data[j]);
     					}
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
   		 console.log(data);
     		 var m=[];
     		 for(var j=0;j<data.length;j++){
     			 for(var i=0;i<data[j].length;i++){
     				if(data[j][i].years==date.c_year&&data[j][i].months==date.c_month){
     					m.push(data[j][i]);
     				 } 
     				else if(data[j][i].years==null&&data[j][i].months==null){
     					m.push(data[j][i]);
     				 }
     				else{//每个月 初始化
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
     	  			else{
     	  			m=data[i-1];
     	  			}
     	  		 } 
     		 }
   		 return m;
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
            			 var u='<%=contextPath%>';
            			 if(columns[t]==1){
            				 values+="<td  bgcolor='#00FF00'>"+(data.allowance.substring(0,3))+"</td>";
            			 } else if(columns[t]>=2&&columns[t]<=4){
            				 values+="<td bgcolor='#CFCFCF'>"+(data.allowance.substring(0,3))+"</td>";
            			 }
            			 else if(columns[t]==6){
            				 values+="<td bgcolor='#00FF00'>";
            				 values+="<div style='width:10px;height:10px;background-image:url(\""+u+"/resources/bdp/images/ui-icons_222222_256x240.png\") ; background-repeat:no-repeat;background-position:-114px -99px;'></div><div style='font-size:8px; text-align:right;width:20px;height:15px;line-height:11px'>"+(data.allowance.substring(0,3))+"</div>";
            				 values+="</td>";
            			 }
            			 else{
            				 values+="<td bgcolor='white' >&nbsp;</td>";
            			 }
            		 }
            		 else{
            			 values+="<td bgcolor='white' >&nbsp;</td>";
            		 }
        		 }
        		 else{
        		 values+="<td>&nbsp;</td>";
        		 }
             }
        	return values;
        };
    </script>
    </html>