$(window).ready(function() {
	var total=0;
	getTotal();
	
	
	
	
	$(".qty").change(function(){
		getTotal();
		var qty =$(this).val()
		var ordSerN =$(this).attr("name");
		$.ajax({
			'type':'GET',
			'url':'ChangeQty',
			'datatype':'text',
			'data':{
				'ordSerN':ordSerN,
				'qty':qty,
			},
			'success':function(data){
				alert(data)
				window.location.reload();
			}	
		})
	});

	
	$(".delete").click(function(){
		getTotal();
		var ordSerN =$(this).attr("name");
		$.ajax({
			'type':'GET',
			'url':'DeleteAOrdVal',
			'datatype':'text',
			'data':{
				'ordSerN':ordSerN,
			},
			'success':function(data){
				alert(data)
				window.location.reload();
			}	
		})
	});
	
	

	function getTotal(){
		$(".singlePrice").each(function() {
		total += parseInt($(this).html());	
		})
		$(".total").attr("value",total);
		$(".total").html("Total:"+total);
	}
	
	
})