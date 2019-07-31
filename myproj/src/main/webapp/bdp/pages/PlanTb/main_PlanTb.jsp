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
        <tr>
            <td class="top1">3</td>
            <td rowspan="3" class="hei">频道</td>
            <td rowspan="3" class="hei">形式</td>
            <td rowspan="3" class="hei">数量</td>
            <td colspan="31" class="leftalign" id="year_month"></td>
            <td rowspan="3" class="hei">单价</td>
            <td rowspan="3" class="hei">总价</td>
            <td rowspan="3" class="hei">折扣</td>
            <td rowspan="3" class="hei">净价</td>
            <td rowspan="3" class="hei">赠送</td>
        </tr>
        
</table>

        </div>
    </form>
    <!--页面 end-->

</body>

  <script type="text/javascript">
        $(document).ready(function () {      	
            $(".search .tj").show();
            $(".search h2").toggle(
                function () {
                    $(".search h2 span").addClass("out");
                    $(".search .tj").slideUp("slow");
                },
                function () {
                    $(".search h2 span").addClass("on");
                    $(".search h2 span").removeClass("out");
                    $(".search .tj").slideDown("slow");
                }
            );
            $('#exel tr:not(:last)').mouseover(
                function () {
                    $(this).addClass('addcolor');
                }
            );
            $('#exel tr').mouseout(
                function () {
                    $(this).removeClass('addcolor');
                }
            );
            
            //加载日期列
            var c_year=2017;
            var c_month=2;
            $("#year_month").text(c_year+"年"+c_month+"月");
            var date = new Date(c_year+"/"+(c_month+1)+"/01");   
            date.setDate(0);//设置为0 得到月的天数
        	var day=date.getDate();
        	var d_en='<tr><td class="top1">4</td>';
        	var d_ch='<tr><td class="top1">5</td>';
        	for(var d=1;d<=day;d++){
        		date.setDate(d);
        		var w=date.getDay();
        		if(w==0||w==6){
        			d_en+='<td class="top3"><em>'+d+'</em></td>';
        			d_ch+='<td class="top3"><em>'+"日一二三四五六".charAt(date.getDay())+'</em></td>';
        		}
        		else{
        			d_en+='<td class="top3">'+d+'</td>';
        			d_ch+='<td class="top3">'+"日一二三四五六".charAt(date.getDay())+'</td>';
        		}
        	}
			for(var dz=0;dz<=(31-day);dz++){
				d_en+='<td class="top3"></td>';
				d_ch+='<td class="top3"></td>';
        	}
			d_en+='</tr>';
			d_ch+='</tr>';
			$("#tb").append(d_en);
			$("#tb").append(d_ch);
            
            var num=3;
            
            //添加底部
            $("#tb").append('<tr id="tbEnd">'+
            	       '<td class="top1">'+(num+16)+'</td>'+
            	       tds(39)+
            	       '</tr>');
            //添加签字部分
            $("#tbEnd").before(
			           '<tr id="footBegin">' +
			           '<td class="top1">'+(num+11)+'</td>'+
			           tds(3)+
			           '<td colspan="25" class="hei">甲方：</td>' +
			           '<td colspan="5" class="hei">乙方：</td>'+
			           tds(6)+
			           '</tr>');
            $("#tbEnd").before(
			           '<tr id="footBegin">' +
			           '<td class="top1">'+(num+12)+'</td>'+
			           tds(3)+
			           '<td colspan="25" class="hei">地址：</td>' +
			           '<td colspan="5" class="hei">地址：</td>'+
			           tds(6)+
			           '</tr>');
            $("#tbEnd").before(
			           '<tr id="footBegin">' +
			           '<td class="top1">'+(num+13)+'</td>'+
			           tds(3)+
			           '<td colspan="25" class="hei">电话：</td>' +
			           '<td colspan="5" class="hei">电话：</td>'+
			           
			           tds(6)+
			           '</tr>');
            $("#tbEnd").before(
			           '<tr id="footBegin">' +
			           '<td class="top1">'+(num+14)+'</td>'+
			           tds(3)+
			           '<td colspan="25" class="hei">授权代表签字：</td>' +
			           '<td colspan="5" class="hei">授权代表签字：</td>'+
			           tds(6)+
			           '</tr>');
            $("#tbEnd").before(
			           '<tr id="footBegin">' +
			           '<td class="top1">'+(num+15)+'</td>'+
			           tds(3)+
			           '<td colspan="25" class="hei">（单位盖章）</td>' +
			           '<td colspan="5" class="hei">（单位盖章）</td>'+
			           tds(6)+
			           '</tr>');
            
           //添加总金额部分
            $("#footBegin").before('<tr id="dataListEnd">'+
            	       '<td class="top1">'+(num+6)+'</td>'+
            	       tds(34)+            	       
            	       '<td class="hei">总金额：</td>'+
            	       '<td colspan="2" class="hei">169,420.00</td>'+
            	       '<td class="hei">42,665.00</td>'+
            	       '<td class="hei">47,520.00</td>'+
            	    '</tr>');
            $("#footBegin").before('<tr id="dataListEnd">'+
         	       '<td class="top1">'+(num+7)+'</td>'+
         	       tds(34)+             	               	      
         	       '<td colspan="5" class="hei">优惠金额：0.00&nbsp;&nbsp;优惠政策：</td>'+
         	    '</tr>');
            $("#footBegin").before('<tr id="dataListEnd">'+
         	       '<td class="top1">'+(num+8)+'</td>'+
         	       tds(34)+             	       
         	       '<td colspan="5" class="hei">实际金额：42,665.00&nbsp;&nbsp;服务费总额：42,665.00</td>'+
         	    '</tr>');
            
            //空白部分
            $("#footBegin").before('<tr id="dataListEnd">'+
          	       '<td class="top1">'+(num+9)+'</td>'+
          	       tds(34)+             	       
          	       '<td colspan="5">&nbsp;</td>'+
          	    '</tr>');
            
            $("#footBegin").before('<tr id="dataListEnd">'+
           	       '<td class="top1">'+(num+10)+'</td>'+
           	       tds(39)+
           	       '</tr>');
            
            //添加数据部分
            var tempdata=[0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
            for(var i=0;i<num;i++){
            $("#dataListEnd").before(' <tr>'+
                   '<td class="top1">'+(6+i)+'</td>'+
                   '<td>14-9首页(传漾)</td>'+
                   '<td title="98605">新全景视窗1(轮巡)(传漾)</td>'+
                   '<td>5</td>'+
               		tds(tempdata.length,tempdata)+tds(31-tempdata.length)+
                    '<td>14300.00</td>'+
                    '<td>71,500.00</td>'+
                    '<td>35%</td>'+
                    '<td>25,025.00</td>'+
                    '<td>0</td>'+
                "</tr>");
            }
        });
        
        var tds=function (num,data){
        	var values="";
        	 for(var t=0;t<num;t++){
        		 if(data!=undefined){
            		 if(data.length>t){
            			 if(data[t]==1){
            				 values+="<td class='tdmenu' bgcolor='#00FF00'>1.0</td>";
            			 } 
            			 else{
            				 values+="<td class='tdmenu'>&nbsp;</td>";
            			 }
            		 }
            		 else{
            			 values+="<td class='tdmenu'>&nbsp;</td>";
            		 }
        		 }
        		 else{
        		 values+="<td>&nbsp;</td>";
        		 }
             }
        	return values;
        };

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

        function show_DivContr(contractNo, div_id) {     
            document.getElementById('div_' + div_id).className = 'black_bgnew';
        }
        function hidden_DivContr(div_id) {
            document.getElementById('div_' + div_id).className = 'noneBox';
        }

        function ShowDIV(thisObjID) {
            $("#BgDiv").css({ display: "block", height: $(document).height() });
            var yscroll = document.documentElement.scrollTop;
            $("#" + thisObjID).css("top", "100px");
            $("#" + thisObjID).css("display", "block");
            document.documentElement.scrollTop = 0;
        }

        function closeDiv(thisObjID) {
            $("#BgDiv").css("display", "none");
            $("#" + thisObjID).css("display", "none");
        }
    </script>
    </html>