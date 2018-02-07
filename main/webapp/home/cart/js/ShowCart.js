$(window).ready(function() {
	
	$(".qty").change(function(){
		var qty =$(this).val()
		var x =$(this).attr("name");
		var itemId=x.split("|")[1];
		var serN=x.split("|")[0];
	
		
		window.location.href = "/Belle_Rever/home/Delete_qty?itemId="+itemId+"&serN="+serN+"&qty="+qty;
		
	});
	
	
	
	
	
	
	
})