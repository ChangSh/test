$(function() {
		$('[name="nice-select"]').click(function(e){
		    $('[name="nice-select"]').find('ul').hide();
		    $(this).find('ul').show();
			e.stopPropagation();
		});
		$('[name="nice-select"] li').hover(function(e){
			$(this).toggleClass('on');
			e.stopPropagation();
		});
		$('[name="nice-select"] li').click(function(e){
			var val = $(this).text();
			$(this).parents('[name="nice-select"]').find('input').val(val);
			$('[name="nice-select"] ul').hide();
			e.stopPropagation();
		});
		$(document).click(function(){
			$('[name="nice-select"] ul').hide();
		});


		$(".topnav").accordion({
				accordion:false,
				speed: 500,
				closedSign: '<img src="images/list1.png" alt="" />',
				openedSign: '<img src="images/list2.png" alt="" />'
			});



	$(".topnav>li>a").click(function(event) {
									$(".topnav>li>ul>li>ul>li>a").removeClass('current3');
									$(".topnav>li>ul>li>a").removeClass('current2');
									$(this).addClass('current').parent().siblings().children().removeClass('current');
								});
								$(".topnav>li>ul>li>a").click(function(event) {
									$(".topnav>li>ul>li>a").removeClass('current2');
									$(this).addClass('current2').parent().siblings().children().removeClass('current2');
								});
								$(".topnav>li>ul>li>ul>li>a").click(function(event) {
									$(".topnav>li>ul>li>ul>li>a").removeClass('current3');
									$(this).addClass('current3').parent().siblings().children().removeClass('current3');
								});

		
		});