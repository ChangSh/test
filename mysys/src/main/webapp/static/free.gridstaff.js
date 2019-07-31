/*
 * grid 组件 
 * author 米飞
 * 用于员工派发
 * clickTr 方法实现单击行事件
 * trimBlank 不显示空行
 * unbindClick 为true
 */
;(function ($) {
	var local_opts ;
	var $thisGrid ;
    $.fn.reload1=function (newOpt){
        var $this =$(this);
        if (newOpt){
        	local_opts = newOpt; 
        }
        local_opts.page=1;
    	getData(local_opts,$this);
	}

    function pageselectCallback(page_index, jq){
    	local_opts.page = page_index+1;
    	getData(local_opts,$thisGrid);
    }
    function deleteData(opts,$this,id){
    	 var url;
    	 $.each(opts.actions,function(i,o){
         	if(o.deletable){
         		url = o.url;
         	}
         });
    	 if(confirm("是否将此信息删除?")){
             $.ajax({
          	   type: "POST",
          	   url: url,
          	   data: {id:id},
          	   success: function(data){
          		   if (data='ok'){
                       getData(opts,$this);
          		   }
          	   }
             });
    	 }
    }
	//ajax得到数据并填充
	function getData(opts,$this){ 

    	//$("#g1 tbody",$this).html("");//清空原数据
		//var searchOpts = $this.find("form").serializeArray();
		if (!opts.page)
			opts.page=1;
        var param = {page:opts.page,pageSize:opts.pageSize};
        var newParam = $.extend({}, param, opts.param);
        
        var tbody=""

    	var layerIndex = layer.msg('正在加载数据,请稍等...',{icon: 16,shadeClose: false,shade: 0.1,time:0});  
        $.ajax({
     	   type: "POST",
     	   url: opts.url,
     	   data: newParam,
     	   success: function(data){
     		  var o;

     			$this.find(".fGridData tbody").children().children().unbind("click");//先解绑所有
     		   for(var i=0;i<opts.pageSize;i++){
     			o=data.Rows[i];//数据
               	$.each(opts.filds,function(j,f){
               		if (opts.clickTr&&o && (!f.unbindClick)){//点查询后多绑一次,所以要解绑
               			$this.find(".fGridData tbody").children().eq(i).children().eq(j).click(o,function(t){
                   			opts.clickTr(t.data);//将一条数据传给方法,注意参数o
                   		});
               			//加手型
               			$this.find(".fGridData tbody").children().eq(i).children().eq(j).css("cursor","pointer");
               		}
               		if (o){
               			var tdValue="";
               			//if(o[f.name]){
               				tdValue = o[f.name];
               				if (tdValue==null)
               					tdValue="";
               			//}
                   		if (f.render){
                   			var tRenderHtml = f.render(o);
                   			if (!tRenderHtml)
                   				tRenderHtml="";
                       		$this.find(".fGridData tbody").children().eq(i).children().eq(j).html(tRenderHtml);
                       	    //未处理变色
                       		if(o.user_id!=null&&o.user_id!=""){
                       		    $this.find(".fGridData tbody").children().eq(i).children().eq(j).css('color','#ff0000');
                       		}
                   		}else if (f.showIndex){//显示索引号
                       		$this.find(".fGridData tbody").children().eq(i).children().eq(j).text((newParam.page-1)*newParam.pageSize+i+1);
                   		}else{
                       		$this.find(".fGridData tbody").children().eq(i).children().eq(j).text(tdValue);
                       		//未处理变色
                       		if(o.user_id!=null&&o.user_id!=""){
                       		    $this.find(".fGridData tbody").children().eq(i).children().eq(j).css('color','#ff0000');
                       		}
                   		}
               		}else{
               			$this.find(".fGridData tbody").children().eq(i).children().eq(j).html(" ");
               		}

           			if(opts.trimBlank){//如果修剪空行的话，没数据就不显示空行
	               		if (o){
               				$this.find(".fGridData tbody").children().eq(i).show();
	               		}else{
               				$this.find(".fGridData tbody").children().eq(i).hide();
	               		}
           			}
                  });
               };
             $("#g1",$this).append(tbody);
	           	// 创建分页
	           	$("#Pagination").pagination(data.Total, {//总条数
	           		num_edge_entries: 1, //首尾显示的页按钮数
	           		num_display_entries: 4, //显示的页按钮数
	           		callback: pageselectCallback,
	           		items_per_page: local_opts.pageSize, //每页显示条数
	           		current_page:opts.page-1,
	           		prev_text: "前一页",
	           		next_text: "后一页",	
	        		link_to : 'javascript:void(0);'
	           	});
	           	if (data.Total>0){
		           	$("#Pagination").prepend("<label id=\"jq_total\">共"+data.Total+"条记录</label>");
	           	}
	           	if (opts.afterDataLoad)
	           	opts.afterDataLoad(data);

	        	layer.close(layerIndex);
     	   }
     	});
	}
    $.fn.grid1 = function (options) {
    	$thisGrid = $(this);
        var defualts = {page:1,pageSize:10,title:'',gridClass:''};
        var opts = $.extend({}, defualts, options);
        local_opts = opts;
        var $this =$(this);
        var th="<thead>";
        $.each(opts.filds,function(i,o){
        	var width="";
        	if(o&&o.width)
        		width=o.width;
        	var display="";
        	if(o&&o.display)
        		display=o.display;
        	th+="<th style='width:"+width+"%' class='tab_grey_header'>"+display+"</th>";
        });
        th+="</thead>";
        for (var j = 0; j < opts.pageSize; j++) {
        	th+="<tr>";
        	$.each(opts.filds,function(i,o){
            	th+="<td style='height:28px' ></td>";
            });
        	th+="</tr>";
        }
        
        var table="<table class='fGridData table "+opts.gridClass+" ' id='g1' width='100%' >"+
        th+
        "</table>";

        
        var ul='<div>'
        +'</div>';
        $this.append(ul);
        $this.find('div').append(table);
        getData(opts,$this);
        
        return $this;
    };
})(jQuery);