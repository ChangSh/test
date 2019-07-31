function readXml(xml,id){
	$(id).empty();
	if (window.ActiveXObject) {
		var xmlobject = new ActiveXObject("Microsoft.XMLDOM");
		xmlobject.async = "false";
		xmlobject.loadXML(xml);
		}
		// for other browsers
		else {
		var parser = new DOMParser();
		var xmlobject = parser.parseFromString(xml, "text/xml");
		} 
	var xmlRoot = xmlobject.documentElement;
	var PlaceInfo = xmlRoot.getElementsByTagName("PlaceInfo");
    var AdInfo = PlaceInfo[0].getElementsByTagName('AdInfo')[0].childNodes;
    for (i=2;i<AdInfo.length;i++)
    {
    	if(AdInfo[i].nodeName != "#text"){
    		// alert("Nodename: " + AdInfo[i].nodeName);
    		// alert(AdInfo[i].textContent);
    		var  textContent=AdInfo[i].textContent;
    		var array=textContent.split(",");
    		if(AdInfo[i].nodeName == "ClickUrl"){
    			
    		}else{
    	      //  alert(array[0].split("=")[1]);
    	       //  alert(array[1].split("=")[1]);
    	    	//alert(array[2].split("=")[1]);
    	    //	alert(array[3].split("=")[1].replace("]]","")); 
    	    	TenTypeshow(array,AdInfo[i].nodeName,id);		
    		}
    		}
    	}
}
function readJs(code,id){	
    $(id).empty();
	var a=code.split(']]');
	for(var i=0;i<a.length-1;i++){
     var name=a[i].split("= ")[0].split("r ")[1]; 
     a[i]=a[i].split('[[')[1]; 
     var content=a[i].split(",");
      if(name == "ClickUrl"){
			
		}else{
				 TenTypeshow(content,name,id);
			
		} 
     
	}	
}
function TenTypeshow(array,name,id){
	if(array[1].split("=")[1]== "textfield"){
		if(array[2].split("=")[1]=="false"){//是否可以为空
			$(id).append("<label  class='col-sm-3 control-label'><span style='color: red'>*</span>"+array[0].split("=")[1]+":</label>"+
	    	    	"<div class='col-sm-9'><input class='form-control' name='"+name+"' type='text' style='width="+array[3].split("=")[1].replace("]]","")+"'/></div>");
		}else{
			$(id).append("<label  class='col-sm-3 control-label'>"+array[0].split("=")[1]+":</label>"+
	    	    	"<div class='col-sm-9'><input class='form-control' name='"+name+"' type='text' style='width="+array[3].split("=")[1].replace("]]","")+"'/></div>");
		}
		
	}else if(array[1].split("=")[1]== "radiogroup"){
		if(array[3].split("=")[1].replace("]]","")=="true"){//单选默认是 true
			$(id).append("<label  class='col-sm-3 control-label'>"+array[0].split("=")[1]+":</label>"+
	    	    	"<div class='col-sm-9'><label class='checkbox-inline'>是&nbsp&nbsp<input  name='"+name+"' value='1' type='radio'checked style='width="+array[3].split("=")[1].replace("]","").replace("]","")+"'/></label>"
	    	    	+"<label class='checkbox-inline'>否&nbsp&nbsp<input type='radio' name='"+name+"' value='0' style='width="+array[3].split("=")[1].replace("]]","")+"'/></label></div>");
		}else{
			$(id).append("<label  class='col-sm-3 control-label'>"+array[0].split("=")[1]+":</label>"+
	    	    	"<div class='col-sm-9'><label class='checkbox-inline'>是&nbsp&nbsp<input name='"+name+"' value='1' type='radio' style='width="+array[3].split("=")[1].replace("]","").replace("]","")+"'/></label>"
	    	    	+"<label class='checkbox-inline'>否&nbsp&nbsp<input type='radio' name='"+name+"' value='0' checked style='width="+array[3].split("=")[1].replace("]]","")+"'/></label></div>");
		}
		
	}else if(array[1].split("=")[1]== "numberfield"){
			if(array[2].split("=")[1]=="false"){//是否允许为空
				if(array[4].split("=")[1]=="false"){//是否允许有小数点
					$(id).append("<label  class='col-sm-3 control-label'><span style='color: red'>*</span>"+array[0].split("=")[1]+":</label>"+
			    	    	"<div class='col-sm-9'><input class='form-control' type='number' name='"+name+"' min='"+array[5].split("=")[1]+"' max='"+array[6].split("=")[1]+"' style='width="+array[3].split("=")[1].replace("]]","")+"'/></div>");
				}else{
					$(id).append("<label  class='col-sm-3 control-label'><span style='color: red'>*</span>"+array[0].split("=")[1]+":</label>"+
			    	    	"<div class='col-sm-9'><input class='form-control' type='number' name='"+name+"'  step='0.01' min='"+array[5].split("=")[1]+"' max='"+array[6].split("=")[1]+"' style='width="+array[3].split("=")[1].replace("]]","")+"'/></div>");				
				}
			}else{
				if(array[4].split("=")[1]=="false"){
					$(id).append("<label  class='col-sm-3 control-label'>"+array[0].split("=")[1]+":</label>"+
			    	    	"<div class='col-sm-9'><input class='form-control' type='number' name='"+name+"' min='"+array[5].split("=")[1]+"' max='"+array[6].split("=")[1]+"' style='width="+array[3].split("=")[1].replace("]]","")+"'/></div>");
				}else{
					$(id).append("<label  class='col-sm-3 control-label'>"+array[0].split("=")[1]+":</label>"+
			    	    	"<div class='col-sm-9'><input class='form-control' type='number' name='"+name+"' step='0.01' min='"+array[5].split("=")[1]+"' max='"+array[6].split("=")[1]+"' style='width="+array[3].split("=")[1].replace("]]","")+"'/></div>");
				}
		 } 
   }else if(array[1].split("=")[1]== "checkbox"){
		if(array[2].split("=")[1].replace("]]","")== "true"){
			 $(id).append("<label  class='col-sm-3 control-label'>"+array[0].split("=")[1]+":</label>"+
	    	"<div class='col-sm-9'><label class='checkbox-inline'>是&nbsp&nbsp<input type='checkbox' name='"+name+"' value='1' checked='checked'/></label></div>"); 
		}else{
			$(id).append("<label  class='col-sm-3 control-label'>"+array[0].split("=")[1]+":</label>"+
	    	"<div class='col-sm-9'><label class='checkbox-inline'>是&nbsp&nbsp<input class='form-control' name='"+name+"' value='1' type='checkbox' /></label></div>");
		}
		
	}else if(array[1].split("=")[1]== "textarea"){
		if(array[2].split("=")[1]=="false"){//是否可以为空
			$(id).append("<label  class='col-sm-3 control-label'><span style='color: red'>*</span>"+array[0].split("=")[1]+":</label>"+
	    	    	"<div class='col-sm-9'><textarea class='form-control'rows='3' name='"+name+"' style='width="+array[3].split("=")[1].replace("]]","")+"'></textarea></div>");
		}else{
			$(id).append("<label  class='col-sm-3 control-label'>"+array[0].split("=")[1]+":</label>"+
	    	    	"<div class='col-sm-9'><textarea class='form-control' rows='3' name='"+name+"' style='width="+array[3].split("=")[1].replace("]]","")+"'/></textarea></div>");
		}
		
	}else if(array[1].split("=")[1]== "none"){
		//类型为空时  本质上无意义
	}else if(array[1].split("=")[1]== "hr"){
		$(id).append("<hr />")
	}

}

function  bulidXml(xml){
	//xml=$("#SizeInfo_view_Modal label[name='html']").text();
	//alert(xml);
	if (window.ActiveXObject) {
		var xmlobject = new ActiveXObject("Microsoft.XMLDOM");
		xmlobject.async = "false";
		xmlobject.loadXML(xml);
		}
		// for other browsers
		else {
		var parser = new DOMParser();
		var xmlobject = parser.parseFromString(xml, "text/xml");
		} 
	var xmlRoot = xmlobject.documentElement;
	var PlaceInfo = xmlRoot.getElementsByTagName("PlaceInfo");
	var AdInfo = PlaceInfo[0].getElementsByTagName('AdInfo')[0].childNodes;
	 for (i=2;i<AdInfo.length;i++)
	    {
	    	if(AdInfo[i].nodeName != "#text"){

	           var array=AdInfo[i].textContent.split(",");
		           if(AdInfo[i].nodeName != "ClickUrl"){
			       		if(array[1].split("=")[1]== "textfield"){
			       			AdInfo[i].textContent=$("input[name='"+AdInfo[i].nodeName+"']").val();
			       		}else if(array[1].split("=")[1]== "radiogroup"){
			       			if($("input[name='"+AdInfo[i].nodeName+"']:checked").val()==1){
			       				AdInfo[i].textContent=true;
			       			}else{
			       				AdInfo[i].textContent=false;
			       			}
			       		}else if(array[1].split("=")[1]== "numberfield"){
			       			AdInfo[i].textContent=$("input[name='"+AdInfo[i].nodeName+"']").val();
			       		}else if(array[1].split("=")[1]== "checkbox"){
			       			if($("input[name='"+AdInfo[i].nodeName+"']:checked").val()==1){
			       				AdInfo[i].textContent=true;
			       			}else{
			       				AdInfo[i].textContent=false;
			       			}
			       		}else if(array[1].split("=")[1]== "textarea"){
			       			AdInfo[i].textContent=$("textarea[name='"+AdInfo[i].nodeName+"']").val();
			       		}
   	              }
		    }
	    }
	 var oSerializer = new XMLSerializer();
    var code = oSerializer.serializeToString(xmlobject); 
    return code;
}

function  bulidJs(xml){
//	var xml=$("#SizeInfo_view_Modal label[name='html']").text();
	var bl="";
	var a=xml.split(']]');
  // var newXml=xml.split(']]\";');
	for(var i=0;i<a.length-1;i++){
     var name=a[i].split("= ")[0].split("r ")[1];
     var replace=a[i];
     a[i]=a[i].split('[[')[1]; 
     var array=a[i].split(",");
      if(name != "ClickUrl"){
    	  if(array[1].split("=")[1]== "textfield"){
    		  replace=$("input[name='"+name+"']").val();
     		}else if(array[1].split("=")[1]== "radiogroup"){
     			if($("input[name='"+name+"']:checked").val()==1){
     				replace=true;
     			}else{
     				replace=false;
     			}
     		}else if(array[1].split("=")[1]== "numberfield"){
     			replace=$("input[name='"+name+"']").val();
     		}else if(array[1].split("=")[1]== "checkbox"){
     			if($("input[name='"+name+"']:checked").val()==1){
     				replace=true;
     			}else{
     				replace=false;
     			}
     		}else if(array[1].split("=")[1]== "textarea"){
     			replace=$("textarea[name='"+name+"']").val();
     		}
      }
    //  newXml[i]=newXml[i].split('var')[1];
       bl +="var "+name+"=\""+replace+"\";";
    //   newXml[i]="var "+name+"=\""+replace+"\";";
     }
	var  code=xml.split('var')[0]+bl+xml.split(']]\";')[xml.split(']]\";').length-1];
	return code;
}

	
