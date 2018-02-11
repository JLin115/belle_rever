$(window).ready(function() {
	var total=0;
	getTotal();
	
	
	
	$(".qty").change(function(){
		var qty =$(this).val()
		var ordSerN =$(this).attr("name");
		
		window.location.href = "/Belle_Rever/home/Delete_qty?ordSerN="+ordSerN+"&qty="+qty;
		
	});
	
	
	
	$("qty").change(function() {
		
		getTotal();

	})
	
	$("delete").click(function() {
		getTotal();

	})
	
	

	function getTotal(){
		$(".singlePrice").each(function() {
		total += parseInt($(this).html());	
		})
		$(".total").attr("value",total);
		$(".total").html("Total:"+total);
	}
	
	
})