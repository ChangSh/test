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
				width : 4
			},
			{
				display : '名称',
				name : 'gname',
				width :7
			},
			{
				display : '电话',
				name : 'gsize',
				width : 7
			},
			{
				display : '操作',
				name : '',
				width : 8,
				style:'text-align:center',
				unbindClick : true,
				render : function(row) {
					var html = '数量：<input type="text" id='+row.id+"amou"+' style="width:50px">';
					html+='进价：<input type="text" id='+row.id+' style="width:50px">';
					html += '<button class="btn3" onclick="add(\'{0}\')">添加</button></a>';
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
	//获取主表ID，数量和商品ID，向子表添加
	function add(goodid){
		var pid="${param.pid}";
		var ipu=$(".btn3").prev("#"+goodid).val(); //进价
		alert('ipu'+ipu);
		var amount=$("#"+goodid).prev("#"+(goodid+"amou")).val(); //数量
		alert(amount);
 		$.ajax({
	        url: "${ctx}/input/addGoods.do",
	        data:{pid:pid,amount:amount,cid:goodid,jj:ipu},
	        type:"POST",
	        success:function(data){	
	        	alert(data.info);
	        	parent.refresh();
	        	g.reload(opt);
	        }
	       
		}); 
		
		
		
		
	}

</script>
</head>
<body>
	<div class="content">
		<div class="tit">商品信息列表</div>
		<div class="tit_down">
			<form id="gridQueryForm">
				<ul class="select">
					<li><label class="fl" id="fl"> 商品名称：</label><input type="text"
						placeholder="模糊查询..." name="search_LIKE@name"></li>
					<div class="clear"></div>
				</ul>
			</form>
			<div style="width: 200px; margin: 0 auto">
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
