$(window).ready(function () {
/*login*/
	/*popup */
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
    /*login*/
    $('.signin').on('click',function(){
    	      var userId = $('input[name=userId]').val();
    	      var pswd = $('input[name=pswd]').val();
    	      $.ajax({
    			type:'Post',
    			url:'/Belle_Rever/home/login/LoginServlet',
    			cache: false,
    		    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
//    			async: false,
    			data:"userId="+userId+"&pswd="+pswd,
    			datatype:'JSON',
    			success:function(data){
    				alert("登入成功")
    				window.location.replace(data.url);
    				location.reload()
    			return
    			
    			},error:function(data){
    				console.log(data)
    				var d = data.responseJSON;
    				
    				if(d!= null){
    				if(d.accountError != null){
    					$('input[name=userId]').val("")
    					$('input[name=userId]').css('border-color','red')
    					$('input[name=userId]').attr('placeholder' , d.accountError);
    					
    				}
    				if(d.passwordError!=null){
    					$('input[name=pswd]').val("")
    					$('input[name=pswd]').css('border-color','red')
        				$('input[name=pswd]').attr('placeholder' , d.passwordError);   			
    				}}	
    			}
    			})
    })

    /*logout*/
     $(".logout").on('click',function () {
    	
    	 $.ajax({
 			type:'Post',
 			url:'/Belle_Rever/home/logout/Logout',
 			cache: false,
 			 contentType: 'application/x-www-form-urlencoded;charset=utf-8',
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
    
    /*cart*/

     $('.cartButton').on('click',function(){
    	 window.location.href=("/Belle_Rever/home/cart/ShowCart.jsp");
     })
     
     
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
        
        
        /*Register*/
        
  
        
         $('.JoinB').on('click',function(){
        	 var account=$('input[name=account]').val();
        	 var pas=$('input[name=pas]').val();
        	 var pasc=$('input[name=pasc]').val();
        	 var name=$('input[name=name]').val();
        	 var bd=$('input[name=bd]').val();
        	 var email=$('input[name=email]').val();
        	 var phone=$('input[name=phone]').val();
 
        	 var data='account='+account+'&pas='+pas+'&pasc='+pas+'&name='+name+'&bd='+bd+'&email='+email+'&phone='+phone
    	      $.ajax({
    			type:'Post',
    			url:'/Belle_Rever/home/register/RegisterServlet',
    			cache: false,
    		    contentType: 'application/x-www-form-urlencoded;charset=utf-8',
//    			async: false,
    			data:data,
    			datatype:'JSON',
    			success:function(data){
    				alert("註冊成功")
    				alert("登入成功")
    				window.location.replace(data.url);
    			return
    			
    			},error:function(data){
    				console.log(data)
    				var d = data.responseJSON;
    				
    				if(d!= null){
    					
    					
    					
    				if(d.accountError != null){
    					$('input[name=account]').val("");
    					$('input[name=account]').css('border-color','red')
    					$('input[name=account]').attr('placeholder' , d.accountError);
    					
    				}
    				
    				if(d.paswordError != null){
    					$('input[name=pas]').val("");
    					$('input[name=pas]').css('border-color','red')
    					$('input[name=pas]').attr('placeholder' , d.paswordError);
    					
    				}
    				
    				if(d.paswordError2 != null){
    					$('input[name=pasc]').val("");
    					$('input[name=pasc]').css('border-color','red')
    					$('input[name=pasc]').attr('placeholder' , d.paswordError2);
    					
    				}
    				
    				
    				if(d.nameError != null){
    					$('input[name=name]').val("");
    					$('input[name=name]').css('border-color','red')
    					$('input[name=name]').attr('placeholder' , d.nameError);
    					
    				}
    				
    				
    				if(d.bderror != null){
    					$('input[name=bd]').val("");
    					$('input[name=bd]').css('border-color','red')
    					$('input[name=bd]').attr('placeholder' , d.bderror);
    					
    				}
    				
    				
    				if(d.emailError != null){
    					$('input[name=email]').val("");
    					$('input[name=email]').css('border-color','red')
    					$('input[name=email]').attr('placeholder' , d.emailError);
    					
    				}
    				
    				
    				if(d.phoneError != null){
    					$('input[name=phone]').val("");
    					$('input[name=phone]').css('border-color','red')
    					$('input[name=phone]').attr('placeholder' , d.phoneError);
    					
    				}
    			
    				
    				
    				
    				
    				}	
    			}
    			})
    })
        
        
	  
})