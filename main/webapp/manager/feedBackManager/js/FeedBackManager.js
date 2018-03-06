$(window).ready(function () {
  	
	var pageNow=parseInt($('.pageNow').val())
	var totalPage=parseInt($('.totalPage').val())
	var itid=$('.itid').val()
	console.log(res)
	if(totalPage >0){
	page()
	}
 
	
	for(var c in res){
		
		$('.insertContent').append(
				'<tr>'+
				'<td><img src="/bimg/commemtImg/'+res[c].feedBackPic+'"></td>'+
				'<td>'+res[c].itemId+'</td>'+
				'<td>'+res[c].mId+'</td>'+
				'<td><div style="word-break: break-all;">'+res[c].feedBackVal+'</div></td>'+
				'<td><span class="icon-trash-o deleteFB"></span><input type="hidden" value="'+res[c].fbkey+'"></td>'+
				'</tr>'
					)		
		
	}
	
	$('.deleteFB').on('click',function(){
		
		var itemid = $(this.parentNode.previousSibling.previousSibling.previousSibling ).text();
		var mid = $(this.parentNode.previousSibling.previousSibling).text();
		var fbkey = $(this.nextSibling).val();
	 
		 $.ajax({
	 			type:'Get',
	 			url:'/Belle_Rever/manager/feedBackManager/manager_feebBack_delete',
	 			cache: false,
	 			headers:{"X-Requested-With": "XmlHttpRequest"},
	 			contentType: 'application/x-www-form-urlencoded;charset=utf-8',
	 			data:{"itemId":itemid ,"mId": mid ,"fbkey":fbkey },
	 			success:function(data){
	 			if(data=='true'){ 
	 			alert('刪除成功')
	 			location.reload()
	 			}else if(data=='false'){
	 			alert('error')
	 				
	 			}
	 				
	 				
	 				
	 			return
	 			},error:function(data){
	 				 
	 			}
	 			})
		
		
	})
	
	function page() {
		
	
		if(pageNow >1){ 
			var pgn = pageNow-1
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/manager/feedBackManager/manager_feebBack_search?itid='+itid+'&pageNow='+pgn+'"> PREV <</a> '
			)
		}else if(pageNow==1){
			$('.controlPage').append(
				'<a class="controlPage_a_color" href="/Belle_Rever/manager/feedBackManager/manager_feebBack_search?itid='+itid+'&pageNow='+1+'"> PREV <</a> '
			)			
		}
		
		for(  pg =1 ;pg <= totalPage ;pg++){ 
			
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/manager/feedBackManager/manager_feebBack_search?itid='+itid+'&pageNow='+pg+'">'+pg+'</a> '
			)
		} 
		
		if(pageNow == totalPage){ 
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/manager/feedBackManager/manager_feebBack_search?itid='+itid+'&pageNow='+pageNow+'"> >NEXT</a>'
			)
		}else if(pageNow<totalPage){
			var pgn = pageNow+1
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/manager/feedBackManager/manager_feebBack_search?itid='+itid+'&pageNow='+pgn+'"> >NEXT</a>'
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