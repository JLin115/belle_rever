$(window).ready(function () {
	
	
/*login*/
	
	/*popup*/
    $(".loginB").on('click',function () {
        $('.newlo').stop();
    	var w = $('.innerlo').outerWidth() ;
    	var bodyW =$('body').outerWidth() ;
    	var left = (bodyW-w)/2;

       $('.newlo').slideDown(300);
       $('.innerlo').css({'left':left+'px'})
       $('.innerlo').slideDown(300);
    })
    
    $(window).on('resize',function(){
 
    	var w = $('.innerlo').outerWidth() ;
    	var bodyW =$('body').outerWidth() ;
    	var left = (bodyW-w)/2;
    	  $('.innerlo').css({'left':left+'px'})
    })

    $('.newlo').on('click' , function () {
        $('.newlo').css({'display':'none'});
        $('.innerlo').css({'display':'none'});
    })
    /*connect*/
    $('.signin').on('click',function(){
    	      var formData = new FormData($(".loginForm")[0]);
    	      var userId = $('input[name=userId]').val();
    	      var pswd = $('input[name=pswd]').val();
    	      $.ajax({
    			type:'Post',
    			url:'/Belle_Rever/home/login/LoginServlet',
//    			cache: false,
    		    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
//    			async: false,
    			data:"userId="+userId+"&pswd="+pswd,
    			datatype:'JSON',
    			success:function(data){
    				console.log(data)
    				window.location.replace(data.url);
    				
    			return
    			
    			},error:function(data){
    				console.log(data)
    				var d = data.responseJSON;
    				
    				if(d!= null){
    				if(d.accountError != null){
    					$('input[name=userId]').css('border-color','red')
    					$('input[name=userId]').attr('placeholder' , d.accountError);
    					
    				}
    				if(d.passwordError!=null){
    					$('input[name=pswd]').css('border-color','red')
        				$('input[name=pswd]').attr('placeholder' , d.passwordError);
    					
    				}}
    			
    				
    			}
    			})
    		
    	
    	
    	
    	
    })

    
    /*cart*/
            $(".cartB").on('mouseenter',function () {
            $('.cart').stop();
           var w = $(this).offset().left;
           var h = $(this).offset().top;
           var objW=$(this).outerWidth() ;
           var objH=$(this).outerHeight() ;
           var w=(w + objW/2) -300;
           var h =h + objH ; 
           $(".cart").css({'left':w+'px','top':h+'px' })
           $('.cart').slideDown(500);
        })
        
        $('.cartB').on('mouseleave',function () {
            $('.cart').stop();
            $('.cart').slideUp(500);
        
        })
        
        
        $('.cart').on('mouseenter',function () {
            $('.cart').stop();
            $('.cart').slideDown(500);
        
        })
        
            $('.cart').on('mouseleave',function () {
            $('.cart').stop();
            $('.cart').slideUp(500);
        
        })
        
        
        
        
	  
})