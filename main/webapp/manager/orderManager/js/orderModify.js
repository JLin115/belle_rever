$(window).ready(function() {
	$(".button").click(function() {
		$("input[name=coupon]").val($(".cp").val());
		$("input[name=sDate]").val($(".sd").val());
		$("input[name=stat]").val($(".stat").val());
	});
	
	
});