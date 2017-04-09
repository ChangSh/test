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
				display : '商品名称',
				name : 'gname',
				width :7
			},
			{
				display : '规格',
				name : 'gsize',
				width :7
			},
			{
				display : '进货单价',
				name : 'cargounitprice',
				width : 4
			},
			{
				display : '数量',
				name : 'cargoamount',
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
						html += '<a href="javascript:void(0);" onclick="gridDelete(\'{0}\',\'${ctx}/input/remove.do\')"><img src="${ctx}/static/images/del.png" title=""></a>';
						return Free.replace(html, row.id);
				}
			} ];
	var opt = {
		url : "${ctx}/input/ajax_ChildList.do?pid="+"${ob.id}",
		filds : filds,
		pageSize : 10,
		trimBlank : true,
		clickTr : function(row) {
			findOne(row.gid)
		},
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
   function  refresh(){
	   g.reload(opt);
	   
   }
   function addgoods(){
	   layer.open({
		    type: 2,
		    title: '选择商品',
		    shadeClose: true,
		    shade: 0.8,
		    offset: ['50px', '50px'], 
		    area: ['1000px', '500px'],
		    content:'${ctx}/views/input/goodsInfo.jsp?pid='+"${ob.id}" //'${ctx}/buildingPackAdmin/building/findBuildingInfo.do'
		}); 
	   
   }
</script>
</head>
<body>
	<div class="content">
		<div class="tit">编辑商品</div>
		<div class="tit_down">
			<form id="gridQueryForm">
				<ul class="select">
					
					<div class="clear"></div>
				</ul>
			</form>
			<div style="width: 200px; margin: 0 auto">
				
				<button class="btn1"
					onclick="addgoods()">+增加商品</button>
			</div>
			<div class="clear"></div>
			
			<div class="clear"></div>
			<div id="maingrid"></div>
			<div id="Pagination"></div>
		</div>
	</div>


</body>

</html>
