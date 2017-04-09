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
				display : '房名',
				name : 'gname',
				width :7
			},
			{
				display : '类型',
				name : 'gtype',
				width :7,
				render:function (row){
   				 return Free.getCodeName("分类",row.gtype);
                	} 	 
			},
			{
				display : '价格',
				name : 'gunitprice',
				width : 4
			},
			{
				display : '简介',
				name : 'gbriefintro',
				width : 5
			},
			{
				display : '详细',
				name : 'gdetailintro',
				width : 15,
				style:'text-align:left'
			},
			{
				display : '地址',
				name : 'gaddr',
				width : 15,
				style:'text-align:left'
			},
			{
				display : '面积',
				name : 'gsize',
				width : 7
			},
			{
				display : '折扣',
				name : 'discount',
				width : 7
			},
			{
				display : '操作',
				name : '',
				width : 5,
				style:'text-align:left',
				unbindClick : true,
				render : function(row) {
					var html = "";
					html += '<a href="${ctx}/goods/edit.do?id={0}"><img src="${ctx}/static/images/modi.png" title="修改"></a>';
						html += ' | ';
						html += '<a href="javascript:void(0);" onclick="gridDelete(\'{0}\',\'${ctx}/goods/delete.do\')"><img src="${ctx}/static/images/del.png" title="删除"></a>';
						return Free.replace(html, row.id);
				}
			} ];
	var opt = {
		url : "${ctx}/goods/ajax_list.do",
		filds : filds,
		pageSize : 10,
		trimBlank : true,
		clickTr : function(row) {
			findOne(row.id)
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
	
	function addFenlei(){
		   layer.open({
			    type: 2,
			    title: '新增类型',
			    shadeClose: true,
			    shade: 0.8,
			    offset: ['50px', '50px'], 
			    area: ['1000px', '500px'],
			    content:'${ctx}/views/goods/fenleiNew.jsp' //'${ctx}/buildingPackAdmin/building/findBuildingInfo.do'
			}); 
		   
	}
</script>
</head>
<body>
	<div class="content">
		<div class="tit">房源信息</div>
		<div class="tit_down">
			<form id="gridQueryForm">
				<ul class="select">
					<li><label class="fl">名称：</label><input type="text"
						placeholder="模糊查询..." name="search_LIKE@gname"></li>
						<li><label class="fl">类型：</label>
					<f:select name="search_LIKE@gtype"
						sectionname="分类"  value=""></f:select>
				</li>
					<div class="clear"></div>
					<div class="clear"></div>
					
				</ul>
				
					
				
			</form>
			<div style="width: 500px; margin: 0 auto">
				<button class="btn1" onclick="gridSearch()">查询</button>
				<button class="btn1"
					onclick="window.location='${ctx}/views/goods/goodsNew.jsp'">+新增房源</button>
					<!-- <button class="btn1" onclick="addFenlei()">+新增分类</button> -->
			</div>
			<div class="clear"></div>
			
			<div class="clear"></div>
			<div id="maingrid"></div>
			<div id="Pagination"></div>
		</div>
	</div>


</body>

</html>
