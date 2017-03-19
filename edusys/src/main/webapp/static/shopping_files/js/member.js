
function member_dialog(v_url, v_w, v_h, v_title){  
	$('#member_child_box').html('<iframe id="member_frm" name="member_frm" frameborder="0" scrolling="auto" width="100%" height="400"></iframe>'); 
	
	document.getElementById('member_frm').width = v_w;
	document.getElementById('member_frm').height = v_h; 

	$(function() { 
		$( "#member_dialog" ).dialog({  
			modal: true,
			minWidth: v_w+30,
			draggable:false,
			buttons: {
				'关闭': function() {
				  $( this ).dialog( "close" );
				}
			},
			close: function() { 
				//window.top.location.reload();  
			} 
		}); 

		
	});


	document.getElementById('member_frm').src = v_url;
}
 