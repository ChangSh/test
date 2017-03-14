<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<%@include file="../../commons/statics.jsp" %>
<script src="<%=contextPath%>/resources/bdp/FlexBox/js/jquery.flexbox.js" type="text/javascript"></script>
<link href="<%=contextPath%>/resources/bdp/FlexBox/css/jquery.flexbox.css" rel="stylesheet" type="text/css" />
<title></title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <style type="text/css">
      .ztree li span.button.add {
        margin-left:2px; 
        margin-right: -1px; 
        background-position:-144px 0; 
        vertical-align:top; 
        *vertical-align:middle
      }
      .bootstrap-table td{ 
        text-overflow:ellipsis ;
        white-space: nowrap;
        overflow: hidden; 
        max-width:200px; 
      }
   </style>
   <script type="text/javascript">
   	$(function() {
		$('.form_date').datetimepicker({
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
	});
   	global_variable.model = "optlog";
	global_variable.model_cn = "角色";
	global_variable.columns = [{
		field: "id",
		visible: false
	}, {
		field: "operatorName",
		title: "操作员名字",
		align: "center",
		valign: "middle",
		sortable: true
	}, {
		field: "operatorIp",
		title: "操作员IP",
		align: "center",
		valign: "middle",
		sortable: true
	}, {
        field: 'optTimeDateStr',
        title: '操作时间',
        align: 'center',
        valign: 'middle',
        sortName: 'optTime',
		sortable: true
    }, {
        field: 'optLogDesc',
        title: '操作描述',
        align: 'center',
        valign: 'middle',
        sortable: true
    }, {
        field: 'optResult',
        title: '操作结果',
        align: 'center',
        valign: 'middle',
        sortable: true
    }, {
        field: 'failReason',
        title: '失败原因',
        align: 'center',
        valign: 'middle',
        formatter: operateFormatter
    }];
	global_variable.custom_search = true; //自定义查询
	global_variable.sortName = "optTime"; //排序列名
	global_variable.sortOrder = "desc";
	global_variable.showExport = true;
	
	function operateFormatter(value,row,index){
  	  if(value == null) {
          return "--";
  	  }
  	  return "<a id='"+ row.id +"' onclick=read('" + row.id + "') href='#'>" + value + "</a>";
	  };
	  
	  function read(id){
			$.ajax({
				type:"post",
				url:"queryOptById.do",
				data:{id: id},
				dataType:"json",
				success:function(result){
					$('#readMessage').modal('show');
					if(result.data.optLogDesc == null || result.data.optLogDesc == "")
						result.data.optLogDesc = '无';
					$('#ti').html(result.data.optLogDesc);
					$('#content').html(result.data.failReason);
				}
			});
		}
   </script>
</head>
<body>
  <div class="container">
    <div class="panel panel-default" style="margin-top: 30px;">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" style="display: block; text-decoration: none;" href="#searchNews"> 搜索 </a></h4>
      </div>
      <div id="searchNews" class="panel-collapse collapse">
        <div class="panel-body">
          <form role="form" id="optlog_searchForm">
            <div class="form-group input-group">
              <span class="input-group-addon">操作员名字</span><input type="text" class="form-control" style="width: 200px;" name="operatorName" />
              <span class="input-group-addon">操作员IP</span><input type="text" class="form-control" style="width: 200px;" name="operatorIp">
              <span class="input-group-addon">操作描述</span><input type="text" class="form-control" style="width: 200px;" name="optLogDesc" />
            </div>
            <div class="form-group input-group">
              <span class="input-group-addon">操作结果</span><input type="text" class="form-control" style="width: 200px;" name="optResult" />
              <span class="input-group-addon">失败原因</span><input type="text" class="form-control" style="width: 200px;" name="failReason" />
            </div>
           <div class="from-group">
              <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy"
                data-link-field="news_morris_startDate" data-link-format="yyyy-mm-dd" style="width: 280px; float: left;">
                <span class="input-group-addon">开始时间</span> <input class="form-control" type="text"
                  id="news_morris_startDate" name="startDate" /> <span class="input-group-addon"><span
                  class="glyphicon glyphicon-remove"></span></span> <span class="input-group-addon"><span
                  class="glyphicon glyphicon-calendar"></span></span>
              </div>
              <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy"
                data-link-field="news_morris_endDate" data-link-format="yyyy-mm-dd" style="width: 280px; float: left;">
                <span class="input-group-addon">结束时间</span> <input class="form-control" type="text"
                  id="news_morris_endDate" name="endDate" /> <span class="input-group-addon"><span
                  class="glyphicon glyphicon-remove"></span></span> <span class="input-group-addon"><span
                  class="glyphicon glyphicon-calendar"></span></span>
              </div>
              <input type="hidden" name="endDateOperator" value="LESS_THEN"> <input type="hidden"
                name="startDateOperator" value="GREATER_THEN">
            </div>
            <br>
            <div style="float: right;">
              <input type="button" value="搜索" id="optlog_search" class="btn btn-Default" /> <input type="button"
                value="清空" id="optlog_searchReset" class="btn btn-Default" />
            </div>
          </form>
        </div>
      </div>
    </div>
    <div class="row">
      <input type="button" class="btn btn-primary" value="导出全部到Excel" id="optlog_exportExcelAll">
      <table id="optlog_table" data-show-export="true"></table>
    </div>
  </div>
  <!-- 查看Modal -->
    <div class="modal fade" id="readMessage" tabindex="-1" role="dialog" aria-labelledby="message_ModalTitle" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="ti" align="center">
            </h4>
         </div>
         <div class="modal-body" >
          <div style="width:100%;height:250px;overflow-y:auto;overflow-x:hidden" autosized="true" id="content" >
          </div>
          <div class="modal-footer">
           <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
         </div>
       </div>
     </div>
    </div>
     
</body>
</html>