$(window).ready(function() {
	
	$(".qty").change(function(){
		var qty =$(this).val()
		var ordSerN =$(this).attr("name");
		
		window.location.href = "/Belle_Rever/home/Delete_qty?ordSerN="+ordSerN+"&qty="+qty;
		
	});
	
	
	
	
	
	
	
})