$(window).ready(function() {
	$(".button").click(function() {
		$("input[name=coupon]").val($(".cp").val());
		$("input[name=sDate]").val($(".sd").val());
		$("input[name=stat]").val($(".stat").val());
	});
	
	
	$(".qty").change(function(){
		var qty =$(this).val()
		var ordSerN =$(this).attr("name");
		var ordId=$(this.parentNode.previousSibling.firstChild).val();
//		
//		window.location.href = "/Belle_Rever/home/Delete_qty?ordSerN="+ordSerN+"&qty="+qty;
//		a
		alert(ordId)
		$.ajax({
			'type':'GET',
			'url':'ModifyItem',
			'datatype':'JSON',
			'data':{'qty:':qty,'ordSerN':ordSerN},
			'success':function(){
				window.location.reload();
			}			
			
			
		})
		
	});
	
	
});