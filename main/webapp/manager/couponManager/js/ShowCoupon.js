$(window).ready(function () {
	
	var pageNow = $('.pageNow').val()
	var totalPage = $('.totalPage').val()
	page()
	
	for(var d in res){
		
	 
		$('.insertContent').append(
				 '<tr><td><a href="#">'+res[d].cpId+'</a></td>'+
				 '<td>'+res[d].cpDes+'</td>'+
				 '<td>'+res[d].cpVal+'</td>'+
				 '<td>'+res[d].cpQty+'</td>'+
				 '<td>'+res[d].valid+'</td>'+
				 '<td>'+res[d].invalid+'</td>'+
				 '<td><span class="icon-trash-o delete_c"></span></td>'+
				 '</tr>' 
		)
		
		
		
		
	}
	
	
	
	$('.delete_c').on('click',function(){
		
		var cpid = $(this.parentNode.parentNode.firstChild.firstChild).text();
	 
		 $.ajax({
				'type':'Get',
				'url':'/Belle_Rever/manager/couponManager/deleteCoupon',
				'cache': false,
				'data':{'cpid':cpid},
				'headers':{"X-Requested-With": "XmlHttpRequest"},
				 'contentType': 'application/x-www-form-urlencoded;charset=utf-8',
				success:function(data){
					alert(data)
					location.reload()
				return
				},error:function(data){
		 
					alert(data)
				}
				})
		
		
	})
	
	
	
	function page() {
		
		
		if(pageNow >1){ 
			var pgn = pageNow-1
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/manager/couponManager/ShowCoupon?pageNow='+pgn+'"> PREV <</a> '
			)
		}else if(pageNow==1){
			$('.controlPage').append(
				'<a class="controlPage_a_color" href="/Belle_Rever/manager/couponManager/ShowCoupon?pageNow='+1+'"> PREV <</a> '
			)			
		}
		
		for(  pg =1 ;pg <= totalPage ;pg++){ 
			
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/manager/couponManager/ShowCoupon?pageNow='+pg+'">'+pg+'</a> '
			)
		} 
		
		if(pageNow == totalPage){ 
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/manager/couponManager/ShowCoupon?pageNow='+pageNow+'"> >NEXT</a>'
			)
		}else if(pageNow<totalPage){
			var pgn = pageNow+1
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/manager/couponManager/ShowCoupon?pageNow='+pgn+'"> >NEXT</a>'
			)			
		}
	
		setAdec()
	
	}
	
	function setAdec() {

		$('.controlPage a').each(function() {
			if ($(this).text().trim() == pageNow) {
				$(this).css({
					'text-decoration' : 'underline'
				})
			}
		})
	}
	
	
	
	
})