$(window).ready(function() {
	$(".button").click(function() {
		$("input[name=coupon]").val($(".cp").val());
		$("input[name=sDate]").val($(".sd").val());
		$("input[name=stat]").val($(".stat").val());
	});
	
	
	
	$(".delete").click(function () {
		var ordId= $(this.previousSibling).val();
		var ordSerN =$(this.previousSibling).attr("name");
		$.ajax({
			'type':'GET',
			'url':'DeleteOVItem',
			'datatype':'text',
			'data':{
				'ordId':ordId,
				'ordSerN':ordSerN
			},
			'success':function(data){
				alert(data)
				if(data == "刪除成功 ,訂單以刪除"){
				location.href="OrderManager.jsp";
				}else{
				window.location.reload();
				}
			}
		})
	})
	
	$(".qty").change(function(){
		var qty =$(this).val()
		var ordSerN =$(this).attr("name");
		var ordId=$(this.parentNode.firstChild).val();
		$.ajax({
			'type':'GET',
			'url':'ModifyItem',
			'datatype':'text',
			'data':{
				'qty':qty,
				'ordId':ordId,
				'ordSerN':ordSerN
			},
			'success':function(data){
				alert(data)
				window.location.reload();
			}	
		})
	});
});