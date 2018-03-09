$(window).ready(function () {
	
	var pageNow = $('.pageNow').val()
	var totalPage = $('.totalPage').val()

	if(totalPage>0){
	page()
	}
 
 if($('.nowP').val() == 'ShowCoupon'){
	for(var d in res){
		var vd =formatDate(res[d].valid)
		var id =formatDate(res[d].invalid)
		$('.insertContent').append(
				 '<tr><td><a href="/Belle_Rever/manager/couponManager/getSingleCP?cpid='+res[d].cpId+'">'+res[d].cpId+'</a></td>'+
				 '<td>'+res[d].cpDes+'</td>'+
				 '<td>'+res[d].cpVal+'</td>'+
				 '<td>'+res[d].cpQty+'</td>'+
				 '<td>'+vd+'</td>'+
				 '<td>'+id+'</td>'+
				 '<td><span class="icon-trash-o delete_c"></span></td>'+
				 '</tr>' 
		)	
	}
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
					
					if(data == 'true'){
						alert('刪除成功') 
					}else{
						alert('異常')
					}
					location.reload()
				return
				},error:function(data){
		 
					alert(data)
				}
				})
		
		
	})
	$('.buttion_c').on('click',function(){ 
			$('.error').text(' ')
			 $.ajax({
					'type':'GET',
					'url':'/Belle_Rever/manager/couponManager/ModifyCP',
					'cache': false,
					'data':{
						"cpId":$('.cpId').val(),
						"cpDes":$('.cpDes').val(),
						"cpVal":$('.cpVal').val(),
						"cpQty":$('.cpQty').val(),
						"valid":$('.valid').val(),
						"invalid":$('.invalid').val(),
						"mId":$('.mId').val(),
						"oldId":$('.oldId').val(),
						"status":$('.status').val()
					},
					'headers':{"X-Requested-With": "XmlHttpRequest"}, 
					success:function(data){
						if(data[0].status=="true"){
							if($('.status').val() =='set'){
								alert('新建成功') 
							}else if($('.status').val() =='mod'){ 
								alert('修改成功')		 
							} 
						location.href="/Belle_Rever/manager/couponManager/ShowCoupon?pageNow=1";
						}else if(data[0].status=="false"){
						erm = data[1]
						$.each(erm, function(D) {
							$("."+D+"").text(erm[D])
						})  
						}
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
	
	function formatDate(date) {
		 date =  date.replace(',','').split(' ');
		 if(date[1].length<2){
			 date[1]  = '0'+date[1]
		 }
		 if(date[0] == 'Jan'){
			 return date[2] + ' -01- ' +date[1]   ; 
		 }else if(date[0] == 'Feb'){
			 return date[2] + '-02-' +date[1]  
		 }else if(date[0] == 'Mar'){ 
			 return date[2] + '-03-' +date[1]  
		 }else if(date[0] == 'Apr'){
			 return date[2] + '-04-' +date[1]  
		 }else if(date[0] == 'May'){
			 return date[2] + '-05-' +date[1]  
		 }else if(date[0] == 'Jun'){
			 return date[2] + '-06-' +date[1]  
		 }else if(date[0] == 'Jul'){
			 return date[2] + '-07-' +date[1] 
		 }else if(date[0] == 'Aug'){
			 return date[2] + '-08-' +date[1]  
		 }else if(date[0] == 'Sep'){
			 return date[2] + '-09-' +date[1]  
		 }else if(date[0] == 'Oct'){
			 return date[2] + '-10-' +date[1]  
		 }else if(date[0] == 'Nov'){
			 return date[2] + '-11-' +date[1]  
		 }else if(date[0] == 'Dec'){ 
			 return date[2] + '-12-' +date[1]  
		 }
		 
		
	}
	
	
})