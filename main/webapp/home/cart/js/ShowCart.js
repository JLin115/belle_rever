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
			'headers':{"X-Requested-With": "XmlHttpRequest"},
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
			'headers':{"X-Requested-With": "XmlHttpRequest"},
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
		$(".total_showCart").attr("value",total);
		$(".total_showCart").html("Total:"+total);
	}
	
	$('.confirmOrd_showCart').on('click',function(){
   	      $.ajax({
   			type:'GET',
   			url:'/Belle_Rever/home/purchase/FillOrdInfo.jsp',
   			cache: false,
// contentType: 'application/x-www-form-urlencoded;charset=utf-8',
// async: false,
// data:"userId="+userId+"&pswd="+pswd,
   			datatype:'JSON',
   			headers:{"X-Requested-With": "XmlHttpRequest"},
   			success:function(data){
//   				alert(data)
   				 window.location.href="/Belle_Rever/home/purchase/FillOrdInfo.jsp"
   			    return	
   			},error:function(data){
//   				alert("false")
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