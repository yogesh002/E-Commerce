$(function(){
	$(".login_box").hide();
	$("#loginLink").click(function(){
		$(".login_box").hide();
		$(".indexHeader").fadeTo(700, 0.3);
		$(".indexWrap").fadeOut();
		$(".login_box").fadeTo(1000, 0.9, function(){
			$("body").css({"background-color":"white"});
		});
	})
	
	$("#cancelBtn").click(function(){
		$(".login_box").fadeOut(500);
		$(".indexHeader").fadeTo(500, 1);
		$(".indexWrap").fadeIn(500);
	});
	
	$("#close").click(function(){
		$(".login_box").fadeOut(500);
		$(".indexHeader").fadeTo(500, 1);
		$(".indexWrap").fadeIn(500);
	})
});