<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"><%@ include
	file="/views/common/import-all-js-css.jsp"%>
<script type="text/javascript">
	function cancel() {
		window.location.href = '${ctx}/views/goods/goodsInfoList.jsp'
	}
	
	Free.getCodeNames("${ctx}","分类","是否推荐");
	$(function(){
		$("#gtype").text(Free.getCodeName("分类","${ob.gtype}"));
		$("#isFocus").text(Free.getCodeName("是否推荐","${ob.gisfocus}"));
	})
</script>
<title>客户信息</title>
</head>
<body>
	<div class="content">
		<div class="tit">商品信息</div>
		<div class="tit_down">
			<form id="form">
				<table width="100%" class="table2">
					<tr>
						<td width="15%"><label for="" class="lb">房名：</label></td>
						<td width="30%">${ob.gname}</td>
						
					</tr>
					<tr>
						<td style="width:70px;"><label for="" class="lb">类型：</label></td>
					     <td width="30%" id="gtype"></td>
					</tr>
					<tr>
					<td width="15%"><label for="" class="lb">价格：</label></td>
						<td width="30%"><p>${ob.gunitprice}</p></td>
					</tr>
					<tr>
						<td><label for="" class="lb">简介：</label></td>
						<td>${ob.gbriefintro}</td>
					</tr>
					<tr>
						<td><label for="" class="lb" >详细信息：</label></td>
						<td><p>${ob.gdetailintro}</p></td>
					</tr>
					<tr>
						<td><label for="" class="lb" >地址：</label></td>
						<td><p>${ob.gaddr}</p></td>
					</tr>
					<tr>
						<td><label for="" class="lb" >周边：</label></td>
						<td><p>${ob.garound}</p></td>
					</tr>
					<tr>
						<td><label for="" class="lb" >交通：</label></td>
						<td><p>${ob.gtraffic}</p></td>
					</tr>
					<tr>
						<td><label for="" class="lb" >面积：</label></td>
						<td><p>${ob.gsize}</p></td>
					</tr>
					<tr>
						<td><label for="" class="lb" >折扣：</label></td>
						<td><p>${ob.discount}</p></td>
					</tr>
					<tr>
						<td><label for="" class="lb" >是否推荐：</label></td>
						<td><p id="isFocus"></p></td>
					</tr>
					<tr>
						<td><label for="" class="lb" >图片：</label></td>
						<td><p><img src="${op.filepath}" width="300px" height="200px"></img></p></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>