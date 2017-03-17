var now = new Date(); 
var chart_variable={
	 modelName:"",  //模块名称 
	 title_text:""  //统计图的title.text值
} 
var bar_chart_variable={
	yAxis_title_text:"", //柱状图Y轴yAxis.title.text值
}
var line_chart_variable={
	yAxis_title_text:"", //折线图Y轴yAxis.title.text值
	tooltip_valueSuffix:"", //折线图鼠标悬疑显示数据的计量单位
}
 //Pie Chart
var pieChart={
		pieChartDatas:"",
		options_pieChart:function(datas){
			 $("#"+chart_variable.modelName+"PieChart").highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text: chart_variable.title_text+"统计饼图"
			        },
			        tooltip: {
			    	    pointFormat: '<b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    color: '#000000',
			                    connectorColor: '#000000',
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            data:datas
			        }]
			    });
		},
		data_pieChart:function(){
			 $.ajax({
				 async : false,
				 type: 'GET',
				 url:chart_variable.modelName+"PieChart.do",
				 error: function (data) {//请求失败处理函数
					 alert("请求失败");
				  },
				 success:function(data){ //请求成功后处理函数。 
					 pieChart.options_pieChart(data);
					 pieChart.pieChartDatas=data;
				 }
			 }); 
		}, 
		select_Chart:function(data){
				 var selectData="";
		 		 for(var i=0;i<data.length;i++){  
		 	    	  $("#"+chart_variable.modelName+"PieChartSelect").append("<option value='"+data[i][0]+"'>"+data[i][0]+"</option>");  
 	    	          selectData+=data[i][0]+",";
		 	     } 
		 	 	 selectData=selectData.substring(0,selectData.length-1); 
		 	 	 chart_show.select(selectData,'Pie');
		 	    
		},
		get_select_data:function(event){
			 var data=pieChart.pieChartDatas;
			 var selectName=$("#"+chart_variable.modelName+"PieChartTd").children("div").children("button").text().split(",");
        	 var selectData=[];
			 for( var i in selectName){
				 var name=selectName[i].replace(/^\s+|\s+$/g,"");
		        	for(var j=0;j<data.length;j++){ 
		        		if(data[j][0]==name){
							selectData.push(data[j]); 
							break;
		        		}
		        	}
		    }
   		     pieChart.options_pieChart(selectData);
		}
}; 

 //Bar Chart
 var barChart={
	    yScale:"",
	    barChartDatas:"",
 		options_barChart:function(datas){
			 $("#"+chart_variable.modelName+"BarChart").highcharts({
				    chart: {
		            type: 'column'
		        },
		        title: {
		            text:chart_variable.title_text+"统计柱状图"
		        },
		        xAxis: {
		        	categories: datas.categories
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: bar_chart_variable.yAxis_text
		            }
		        },
		        tooltip: {
		            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		                '<td style="padding:0"><b>{point.y:1f} </b></td></tr>',
		            footerFormat: '</table>',
		            shared: true,
		            useHTML: true
		        },
		        plotOptions: {
		            column: {
		                pointPadding: 0.2,
		                borderWidth: 0
		            }
		        },
		        series: datas.barOrLineData
			    });
 		},
 		data_barChart:function(data){
 			this.yScale=data.yScale;
 			 $.ajax({
 				 async : false,
 				 type: 'GET',
 				 data:data,
 				 url:chart_variable.modelName+"BarChart.do",
 				 error: function (data) {//请求失败处理函数
 					 alert("请求失败");
 				  },
 				 success:function(data){ //请求成功后处理函数。
 					   $("#"+chart_variable.modelName+"BarChartTip").remove();
 					  if(data.barOrLineData.length==0){
 						 $("#"+chart_variable.modelName+"BarChartForm").append('<div class="alert alert-info alert-dismissable" id="'+chart_variable.modelName+'BarChartTip"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>该时间段内，'+chart_variable.title_text+'表中没有数据。。。 </div>');
 					  }
 						 barChart.options_barChart(data);
 	 					 barChart.barChartDatas=data;
 				 }
 			 }); 
 		},
 		select_Chart:function(data){
 			 var selectData="";
 	 		 for(var i=0;i<data.length;i++){  
 	 	    	  $("#"+chart_variable.modelName+"BarChartSelect").append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");  
 		          selectData+=data[i].name+",";
 	 	     } 
 	 	 	 selectData=selectData.substring(0,selectData.length-1); 
 	 	 	 this.selectData=selectData;
 	 	 	 chart_show.select(selectData,'Bar');
 	 	    
 		},
 		selectData:"",
 		get_select_data:function(event){
 			 var data=barChart.barChartDatas.barOrLineData;
 			 var selectName=$("#"+chart_variable.modelName+"BarChartTd").children("div").children("button").text().split(",");
 	   	     var selectData=[];
 			 for( var i in selectName){
 				 var name=selectName[i].replace(/^\s+|\s+$/g,"");
 		        	for(var j=0;j<data.length;j++){ 
 		        		if(data[j].name==name){
 							selectData.push(data[j]);
 							break;
 		        		}
 		        	}
 		    }
 			 var selectDatas={categories:"",barOrLineData:""};
 			 selectDatas.categories=barChart.barChartDatas.categories;
 			 selectDatas.barOrLineData=selectData;
 			 barChart.options_barChart(selectDatas);
 	   }
 };
 	
 //Line Chart
var lineChart={
	 yScale:"",
	lineChartDatas:"",
	options_lineChart:function(datas){
		 $("#"+chart_variable.modelName+"LineChart").highcharts({
	        title: {
            text: chart_variable.title_text+"统计折线图"
	        },
	        xAxis: {
	            categories: datas.categories
	        },
	        yAxis: {
	            title: {
	                text: line_chart_variable.yAxis_text
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
            valueSuffix: line_chart_variable.tooltip_valueSuffix
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series:datas.barOrLineData
		    });
	},
	data_lineChart:function(data){
		this.yScale=data.yScale;
		 $.ajax({
			 async : false,
			 type: 'GET',
			 data:data,
			 url:chart_variable.modelName+"LineChart.do",
			 error: function (data) {//请求失败处理函数
				 alert("请求失败");
			  },
			 success:function(data){ //请求成功后处理函数。
				    $("#"+chart_variable.modelName+"LineChartTip").remove();
				  if(data.barOrLineData.length==0){
 						 $("#"+chart_variable.modelName+"LineChartForm").append('<div class="alert alert-info alert-dismissable" id="'+chart_variable.modelName+'LineChartTip" ><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>该时间段内，'+chart_variable.title_text+'表中没有数据。。。 </div>');
 					  }
				 lineChart.options_lineChart(data);
				 lineChart.lineChartDatas=data;
			 }
		 }); 
	},
	select_Chart:function(data){
		 var selectData="";
 		 for(var i=0;i<data.length;i++){  
 	    	  $("#"+chart_variable.modelName+"LineChartSelect").append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");  
	          selectData+=data[i].name+",";
 	     } 
 	 	 selectData=selectData.substring(0,selectData.length-1); 
 	 	 this.selectData=selectData;
 	 	 chart_show.select(selectData,'Line');
 	    
	},
	selectData:"",
	get_select_data:function(event){
		 var data=lineChart.lineChartDatas.barOrLineData;
		 var selectName=$("#"+chart_variable.modelName+"LineChartTd").children("div").children("button").text().split(",");
   	     var selectData=[];
		 for( var i in selectName){
			 var name=selectName[i].replace(/^\s+|\s+$/g,"");
	        	for(var j=0;j<data.length;j++){ 
	        		if(data[j].name==name){
						selectData.push(data[j]); 
						break;
	        		}
	        	}
	    }
		 
		 var selectDatas={categories:"",barOrLineData:""};
		 selectDatas.categories=lineChart.lineChartDatas.categories;
		 selectDatas.barOrLineData=selectData;
		 lineChart.options_lineChart(selectDatas);
	} 
     
 }; 
 
 var chart_show={
	 select:function(selectData,selectType){
		 var buildSelectAll = function(select) {
	         select.multiselect({
		              includeSelectAllOption: true,
		              enableFiltering: true,
		              maxHeight: 350,
		              buttonWidth: '250px',
		              buttonText: function(options) {
		 	              if (options.length === 0) {
		 	                  return selectData+'<b class="caret"></b>';
		 	              }
		 	              else {
		 	                  var selected = '';
		 	                  options.each(function() {
		 	                      selected += $(this).text() + ',';
		 	                  });
		 	                  return selected.substr(0, selected.length -1) + ' <b class="caret"></b>';
		 	              }
		               },
		               buttonTitle: function(options, select) {
		            	   if (options.length === 0) {
			 	                  return selectData;
			 	              }else{
			 	            	  var selected = '';
			            	      options.each(function () {
			            	        selected += $(this).text() + ', ';
			            	      });
			            	      return selected.substr(0, selected.length - 2);
			 	            }
		              },
		          onDropdownHide: function(event) {
		            	   if(selectType=='Pie'){
		            		   pieChart.get_select_data();
		            	   }else if(selectType=='Bar'){
		            		   barChart.get_select_data();
		            	   }else if(selectType=='Line'){
		            		   lineChart.get_select_data();
		            	   }
		        	  
		          }
	         });
	     }($("#"+chart_variable.modelName+selectType+"ChartSelect"));  
     },
     instantOfTime:function(datas){
    	 $.ajax({
    		 async : false,
    		 type: 'GET',
    		 url:chart_variable.modelName+"InstantOfTime.do",
    		 error: function (data) {//请求失败处理函数
    			 alert("请求失败");
    		  },
    		 success:function(data){ //请求成功后处理函数。 
    			 $("#"+chart_variable.modelName+"_"+datas+"_StartDate").val(data.start);
    			 $("#"+chart_variable.modelName+"_"+datas+"_EndDate").val(data.end);
    		 }
    	 });
     },
     statistics:function(datas){ 
	 	   var start=$("#"+chart_variable.modelName+"_"+datas+"_StartDate").val();
	 	   var end=$("#"+chart_variable.modelName+"_"+datas+"_EndDate").val();
	 	   if(start==""){
	 		   alert("开始时间不能为空！");
	 		   return;
	 	   }
	 	  if(end==""){
			   alert("结束时间不能为空！");
			   return;
		   }
	 	   if(start>end){
	 		   alert("开始时间不能大于结束时间，请重新选择时间！");
	 		   return;
	 	   }
	 	   if(datas=="Bar"){
	 		  $("#"+chart_variable.modelName+"BarChartTd").children("div").children("button").text(barChart.selectData);
	 		  $("#"+chart_variable.modelName+"BarChartTd").children("div").children("button").removeAttr("title").attr("title",barChart.selectData);
	 		  var param={start:start,end:end,yScale:barChart.yScale};
	 		  barChart.data_barChart(param);
	 	   }else{
	 		  $("#"+chart_variable.modelName+"LineChartTd").children("div").children("button").text(lineChart.selectData);
	 		  $("#"+chart_variable.modelName+"LineChartTd").children("div").children("button").removeAttr("title").attr("title",lineChart.selectData);
	 		  var param={start:start,end:end,yScale:lineChart.yScale};
	 		  lineChart.data_lineChart(param);
	 	   }
	 	    $("#"+chart_variable.modelName+datas+"ChartTd").children("div").children("button").append('<b class="caret"></b>');
     },
     search_button:function(data,datas){
    	 if(data=="全部"){
    		 chart_show.instantOfTime(datas);
    		 chart_show.statistics(datas)
     		 return;
     	} else if(data=='本周'){
     		var StartDate = new Date(now.getFullYear(), now.getMonth(), now.getDate() - now.getDay());
     		var EndDate = new Date(now.getFullYear(), now.getMonth(), now.getDate() + (6 - now.getDay()));
     		 
     	}else if(data=='本月'){
     		var StartDate = new Date(now.getFullYear(), now.getMonth(), 1);
     		var EndDate = new Date(now.getFullYear(), now.getMonth(), getMonthDays(now.getMonth()));
     	}else if(data=='本年'){
     		 $("#"+chart_variable.modelName+"_"+datas+"_StartDate").val(now.getFullYear()+"-01-01");
     		 $("#"+chart_variable.modelName+"_"+datas+"_EndDate").val(now.getFullYear()+"-12-31");
     		 chart_show.statistics(datas)
	         return;
     	}
     	 $("#"+chart_variable.modelName+"_"+datas+"_StartDate").val(formatDate(StartDate));
     	 $("#"+chart_variable.modelName+"_"+datas+"_EndDate").val(formatDate(EndDate));
     	 chart_show.statistics(datas)
     } 
 }
 
 //格式化时间
 function formatDate(date) {
 	var myyear = date.getFullYear();
 	var mymonth = date.getMonth()+1;
 	var myweekday = date.getDate();

 	if(mymonth < 10){
 	mymonth = "0" + mymonth;
 	}
 	if(myweekday < 10){
 	myweekday = "0" + myweekday;
 	}
 	return (myyear+"-"+mymonth + "-" + myweekday);
 	} 
 //根据当前月计算出当前月的总天数
 function getMonthDays(myMonth){
 	var monthStartDate = new Date(now.getFullYear(), myMonth, 1);
 	var monthEndDate = new Date(now.getFullYear(), myMonth + 1, 1);
 	var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24);
 	return days;
 	} 			