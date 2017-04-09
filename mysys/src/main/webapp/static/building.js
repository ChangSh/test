/**
 * 楼宇招商信息
 */

$(function() {
		$.ajax({
	        type:"post",
	        url:"${ctx}/buildingAdmin/building/ajax_bamusement.do?bid="+$("#bid").val(),
	        dataType:"json",
	        success:function(data){
	        	var str1="";
	        	var str2="";
	        	var str3="";
	        	var arr1 = new Array();
	    		var arr2 = new Array();
	    		var arr3 = new Array();
	        	arr1=data.merchantUnit.bamusement.split(",");
	        	arr2=data.merchantUnit.buildingFeature.split(",");
	        	arr3=data.merchantUnit.bmetro.split(",");
	        	for(var i=0;i<data.listAmusement.length;i++){
	        		for(var a=0;a<arr1.length;a++){
	        			if(data.listAmusement[i].code==arr1[a]){
	        				str1+="<input type='checkbox' name='bamusement' value="+data.listAmusement[i].code+" checked>"+data.listAmusement[i].codename;
	        			}
	        		}
	        		str1+="<input type='checkbox' name='bamusement' value="+data.listAmusement[i].code+">"+data.listAmusement[i].codename;
	        	};
	        	for(var j=0;j<data.listFeature.length;j++){
	        		for(var b=0;b<arr2.length;b++){
	        			if(data.listFeature[j].code==arr2[b]){
	        				str2+="<input type='checkbox' name='buildingFeature' value="+data.listFeature[j].code+" checked>"+data.listFeature[j].codename;
	        			}
	        		}
	        		str2+="<input type='checkbox' name='buildingFeature' value="+data.listFeature[j].code+">"+data.listFeature[j].codename;
	        	}
	        	for(var k=0;k<data.listMetro.length;k++){
	        		for(var c=0;c<arr3.length;c++){
	        			if(data.listMetro[k].id==arr3[c]){
	        				str3+="<input type='checkbox' name='bmetro' value="+data.listMetro[k].id+" checked>"+data.listMetro[k].name;
	        			}
	        		}
	        		str3+="<input type='checkbox' name='bmetro' value="+data.listMetro[k].id+">"+data.listMetro[k].name;
	        	}
	        	$("#bamusement_id").html(str1);
	        	$("#buildingFeature_id").html(str2);
	        	$("#bmetro_id").html(str3);
	        }
		});
});
function modifyBMU(){
	$.ajax({
        type:"post",
        url:"${ctx}/buildingAdmin/building/ajax_bamusement.do?bid="+$("#bid").val(),
        dataType:"json",
        success:function(data){
        	var str1="";
        	var str2="";
        	var str3="";
        	var arr1 = new Array();
    		var arr2 = new Array();
    		var arr3 = new Array();
        	arr1=data.merchantUnit.bamusement.split(",");
        	arr2=data.merchantUnit.buildingFeature.split(",");
        	arr3=data.merchantUnit.bmetro.split(",");
        	for(var i=0;i<data.listAmusement.length;i++){
        		for(var a=0;a<arr1.length;a++){
        			if(data.listAmusement[i].code==arr1[a]){
        				str1+="<input type='checkbox' name='bamusement' value="+data.listAmusement[i].code+" checked>"+data.listAmusement[i].codename;
        			}
        		}
        		str1+="<input type='checkbox' name='bamusement' value="+data.listAmusement[i].code+">"+data.listAmusement[i].codename;
        	};
        	for(var j=0;j<data.listFeature.length;j++){
        		for(var b=0;b<arr2.length;b++){
        			if(data.listFeature[j].code==arr2[b]){
        				str2+="<input type='checkbox' name='buildingFeature' value="+data.listFeature[j].code+" checked>"+data.listFeature[j].codename;
        			}
        		}
        		str2+="<input type='checkbox' name='buildingFeature' value="+data.listFeature[j].code+">"+data.listFeature[j].codename;
        	}
        	for(var k=0;k<data.listMetro.length;k++){
        		for(var c=0;c<arr3.length;c++){
        			if(data.listMetro[k].id==arr3[c]){
        				str3+="<input type='checkbox' name='bmetro' value="+data.listMetro[k].id+" checked>"+data.listMetro[k].name;
        			}
        		}
        		str3+="<input type='checkbox' name='bmetro' value="+data.listMetro[k].id+">"+data.listMetro[k].name;
        	}
        	$("#bamusement_id").html(str1);
        	$("#buildingFeature_id").html(str2);
        	$("#bmetro_id").html(str3);
        }
	});
}
