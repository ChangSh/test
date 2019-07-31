<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<%@ include file="/views/common/import-all-js-css.jsp"%>
<script type="text/javascript">
Free.getCodeNames("${ctx}","分类");
	var filds = [
			{
				display : '序号',
				showIndex : "default",
				width : 4
			},
			{
				display : '内容',
				name : 'msg',
				width :7
			},
			{
				display : '用户',
				name : 'loginname',
				width :7
			},
			{
				display : '时间',
				name : 'msgTime',
				width :7
			},
			{
				display : '操作',
				name : '',
				width : 5,
				style:'text-align:left',
				unbindClick : true,
				render : function(row) {
					var html = "";
						html += '<a href="javascript:void(0);" onclick="gridDelete(\'{0}\',\'${ctx}/message/delete.do\')"><img src="${ctx}/static/images/del.png" title="删除"></a>';
						return Free.replace(html, row.id);
				}
			} ];
	var opt = {
		url : "${ctx}/message/ajax_list.do",
		filds : filds,
		pageSize : 10,
		trimBlank : true,
	/* 	clickTr : function(row) {
			findOne(row.id)
		}, */
		param : {}
	};
	var g;
	$(function() {
		g = $("#maingrid").grid(opt);
	});
	
	function findOne(id) {
	var index = layer.open({
		 type : 2,
		 title : '详细信息',
		 shadeClose : true,
		 shade : 0.8,

		 area : [ '1000px', '550px' ],
		 content : '${ctx}/goods/detail.do?id=' + id
		 });
		 layer.full(index); 
	
	}
	
	 
</script>
</head>
<body>
	<div class="content">
		<div class="tit">留言信息列表</div>
		<div class="tit_down">
			<form id="gridQueryForm">
				<ul class="select">
					<li><label class="fl">用户名：</label><input type="text"
						placeholder="模糊查询..." name="search_LIKE@loginname"></li>
						
				
				</li>
					<div class="clear"></div>
					<div class="clear"></div>
					
				</ul>
				
					
				
			</form>
			<div style="width: 500px; margin: 0 auto">
				<button class="btn1" onclick="gridSearch()">查询</button>
			
			</div>
			<div class="clear"></div>
			
			<div class="clear"></div>
			<div id="maingrid"></div>
			<div id="Pagination"></div>
		</div>
	</div>


</body>

</html>
