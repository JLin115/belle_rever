$(window).ready(function () {

	 
  $('.itemManager_a').on('mouseenter',function(){
	  	 
		var objW=$(this.parentNode).width()
        var nextSiblingwidth = $(this.nextSibling).width()
 
        var w=( objW/2)-nextSiblingwidth/2	;
        var h = $(this).outerHeight()+2;
   
		 $(this.nextSibling).on('mouseenter', function(){
			 $(this).css({
					'left':w+'px',
					'top':h+'px',
					'display':'block'
				})
		 })
		$(this.nextSibling).css({
					'left':w+'px',
					'top':h+'px',
					'display':'block'
				})
  }) 
  
  
  
		$('.itemManager_a').on('mouseleave',function(){
			$(this.nextSibling).on('mouseleave',function(){
				$(this).unbind();
				$(this).css({
					'display':'none'
				})	
			})
		$(this.nextSibling).css({
			'display':'none'
		})
		
	})

  	/* logout */
 $(".manager_logoutSpan").on('click',function () {
   	 $.ajax({
			'type':'Post',
			'url':'/Belle_Rever/home/logout/Logout',
			'cache': false,
			'headers':{"X-Requested-With": "XmlHttpRequest"},
			 'contentType': 'application/x-www-form-urlencoded;charset=utf-8',
			success:function(data){
				alert("登出成功")
				window.location.replace(data);
				location.reload()
			return
			},error:function(data){
				console.log(data) 
				alert("")
			}
			})
    })

		var pageNow = $('.hidden_pageNow').val();
  
		$('.controlPage a').each(function() {
			if ($(this).text().trim() == pageNow) {
				$(this).css({ 'text-decoration' : 'underline' })
				 
			}
			 
		})
   
    
		 
		var osid = $('.osidH').val();
		$('.ordStatus li').each(function () {
			console.log(this) 
			if($(this).attr('name') == osid){ 
				$(this).css({
					'text-decoration' : 'underline'
				})
			}
		
		}) 
    
 
})