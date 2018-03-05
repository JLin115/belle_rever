$(window).ready(function() {
 
	var totalPage = parseInt($('.totalPage').val());
	var pageNow = parseInt($('.pageNow').val());
	var val = $('.val').val();
	page()
	
		for(var c in x){
			if(x[c].itemdiscount <1){
			$('.toShow').append(
						'<a href="/Belle_Rever/home/showItem/ShowSingleItem_home?itemId='+x[c].itemID+'"><li>'+
						'<img src="/bimg/itemImg/'+x[c].itemPic1+'" />'+
						'<div>'+x[c].itemHeader+'</div>'+
						'<div class="onsales">NT:'+x[c].itemPrice+'</div>'+
						'NT:'+x[c].itemPrice*x[c].itemdiscount+
						'</li>'+
						'</a>'
						)	
						 
						
			}else{
				$('.toShow').append(
						'<a href="/Belle_Rever/home/showItem/ShowSingleItem_home?itemId='+x[c].itemID+'"><li>'+
						'<img src="/bimg/itemImg/'+x[c].itemPic1+'" />'+
						'<div>'+x[c].itemHeader+'</div>'+
						'NT:'+x[c].itemPrice*x[c].itemdiscount+
						'</li>'+
						'</a>'
				)	
			}
		}
 
 
		
	function page() {
		
	
		if(pageNow >1){ 
			var pgn = pageNow-1
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/home/search/Searcher?val='+val+'&pageNow='+pgn+'"> PREV <</a> '
			)
		}else if(pageNow==1){
			$('.controlPage').append(
				'<a class="controlPage_a_color" href="/Belle_Rever/home/search/Searcher?val='+val+'&pageNow='+1+'"> PREV <</a> '
			)			
		}
		
		for(  pg =1 ;pg <= totalPage ;pg++){ 
			
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/home/search/Searcher?val='+val+'&pageNow='+pg+'">'+pg+'</a> '
			)
		} 
		
		if(pageNow == totalPage){ 
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/home/search/Searcher?val='+val+'&pageNow='+pageNow+'"> >NEXT</a>'
			)
		}else if(pageNow<totalPage){
			var pgn = pageNow+1
			$('.controlPage').append(
			'<a class="controlPage_a_color" href="/Belle_Rever/home/search/Searcher?val='+val+'&pageNow='+pgn+'"> >NEXT</a>'
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

