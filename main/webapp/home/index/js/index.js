$(window).ready(function () {
	
	

    $(".loginB").on('click',function () {
        $('.newlo').stop();
       $('.newlo').slideDown(300);
    })

    $('.newlo').on('click' , function () {
        $('.newlo').css({'display':'none'});
    })


    
    /*cart*/
            $(".cartB").on('mouseenter',function () {
            $('.cart').stop();
            w = $(this).offset().left;
           h = $(this).offset().top;
           objW=$(this).outerWidth() ;
           objH=$(this).outerHeight() ;
           w=(w + objW/2) -300;
           h =h + objH ; 
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