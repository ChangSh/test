<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script type="text/javascript">
	var filds = [
			{
				display : '序号',
				showIndex : "default",
				width : 1
			},
			{
				display : '进货日期',
				name : 'cargotime',
				width :7
			},
			{
				display : '进货总额',
				name : 'cargosum',
				width : 4
			},
			
			{
				display : '操作',
				name : '',
				width : 1,
				style:'text-align:left',
				unbindClick : true,
				render : function(row) {
					var html = "";
					html += '<a href="${ctx}/input/input.do?id={0}"><img src="${ctx}/static/images/modi.png" title="新增"></a>';
						html += ' | ';
						html += '<a href="javascript:void(0);" onclick="gridDelete(\'{0}\',\'${ctx}/input/delete.do\')"><img src="${ctx}/static/images/del.png" title="删除"></a>';
						return Free.replace(html, row.id);
				}
			} ];
	var opt = {
		url : "${ctx}/input/ajax_ParentList.do",
		filds : filds,
		pageSize : 10,
		trimBlank : true,
		param : {}
	};
	var g;
	$(function() {
		g = $("#maingrid").grid(opt);
	});
	
</script>
</head>
<body>
	<div class="content">
		<div class="tit">进货信息列表</div>
		<div class="tit_down">
			<form id="gridQueryForm">
				<ul class="select">
								<li><label class="fl">进货日期：${param.pid}</label><input type="text"
						placeholder="模糊查询..." name="search_LIKE@registration_date" onclick="WdatePicker({dateFmt:'yyyy-M-d'})" readonly = "readonly"></li>
		
					<div class="clear"></div>
				</ul>
			</form>
			<div style="width: 200px; margin: 0 auto">
				<button class="btn1" onclick="gridSearch()">查询</button>
				<button class="btn1"
					onclick="window.location='${ctx}/input/newInput.do'">+新增进货</button>
			</div>
			<div class="clear"></div>
			
			<div class="clear"></div>
			<div id="maingrid"></div>
			<div id="Pagination"></div>
		</div>
	</div>


</body>

</html>
