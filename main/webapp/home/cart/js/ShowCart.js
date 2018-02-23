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
		$(".singlePrice2").each(function() {
		total += parseInt($(this).html());	
		})
		$(".total").attr("value",total);
		$(".total").html("Total:"+total);
	}
	
	$('.confirmOrd').on('click',function(){
   	      $.ajax({
   			type:'GET',
   			url:'/Belle_Rever/home/purchase/FillOrdInfo.jsp',
   			cache: false,
// contentType: 'application/x-www-form-urlencoded;charset=utf-8',
// async: false,
// data:"userId="+userId+"&pswd="+pswd,
   			datatype:'JSON',
   			success:function(data){
   				alert("true")
   			    var url = data.url;
   			    window.location.href=url
   			    return
   			},error:function(data){
   				alert("false")
   				 if(data.responseJSON.status == "toLogin"){
   				 $('.loginB').trigger('click')
   				 return
   				}
   				if(data.responseJSON.status == "cartEmpty"){
   				 alert(data.responseJSON.url);
   				 window.location.href=data.responseJSON.url
   				 return
      			}
   			}
   			})
   			
   			
   			
   })
		
		
		
 
	
	
})