<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
var x = ${gson};
traceIH  = '';
$(window).ready(function() {
<!-- 	var length = x.length; -->
	var color = new Set();
	
	x.forEach(item => {
 		color.has(item.itemColor) ? null : color.add(item.itemColor);

	})
	
 	color.forEach(item => {
 		$(".div").append('<input type="radio" class="color"  name="color" value="'+item+ '"/>'+item+'&nbsp;');
 		
	})
	
	
	$(".color").change(function (){
<!-- 	alert(this.value); -->
	var temp =this.value;
	$(".size").remove();
		x.forEach(item =>{
			if(item.itemColor == temp){
	
				if(item.itemQty>0){
				$(".div2").append('<span class="size"><input type="radio"  name="size" value="'+item.itemSize+ '"/>'+item.itemSize+'</span>&nbsp;');
				}else{
				$(".div2").append('<span class="size"><input type="radio"  name="size" disabled value="'+item.itemSize+ '"/>'+item.itemSize+'</span>&nbsp;');
				}
			}
		})
	})
	
	
	
	
	$("body").on('click','.size',function (){
	x.forEach(item =>{
	if((item.itemColor==$("input[name=color]:checked").val()) && (item.itemSize ==$("input[name=size]:checked").val()) ){
	$("input[name=serN]").attr('value',item.itemSerialNumber);
	}
	})
	
	})
	
	
	$(".singleitempurchase_addCart , .singleitempurchase_purchase").click(function(){

		if( $("input[name=color]").is(':checked') && $("input[name=size]").is(':checked') ){
		
		var itemId = $("input[name=id]").val();
		var itemSerN = $("input[name=serN]").val();
		type=$(this).attr("name");
	
		$.ajax({
			'type':'GET',
			'url':'/Belle_Rever/home/cart/AddCart',
			'datatype':'text',
			'headers':{"X-Requested-With": "XmlHttpRequest"},
			'data':{
				'itemId':itemId,
				'itemSerN':itemSerN,
				'type':type
			},
			'success':function(data ){
			if(type=='Cart'){
			window.location.reload();
			}else if(type=='Purchase'){
			window.location.href="/Belle_Rever/home/cart/ShowCart.jsp"
			toFillOrdInfo()
			 }
			    }
		 	 })
	 	   }
		})

	
	function toFillOrdInfo(){
	
	$.ajax({
   			'type':'GET',
   			'url':'/Belle_Rever/home/purchase/FillOrdInfo.jsp',
   			'cache': false,
   			'datatype':'JSON',
   			'headers':{"X-Requested-With": "XmlHttpRequest"},
   			success:function(data){
   				window.location.href="/Belle_Rever/home/purchase/FillOrdInfo.jsp"
   			    return	
   			},error:function(data){
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
   			
   			
   			
	}
	
	
	
	$('.icon-heart,.icon-heart[class^="ih"]').on('click',function(){
 		var mId = $(this.previousSibling).val();
 		var itemId = $(this.previousSibling).attr('name');
 		var fbkey = $(this.previousSibling).attr('class');
<!--  		alert(fbkey) -->
 		traceIH = 'ih'+fbkey
<!-- 		alert(mId+"+"+itemId) -->
		var queryS="mId="+mId+"&itemId="+itemId+"&fbkey="+fbkey
		console.log($(this))
		$.ajax({
   			'type':'Get',
   			'url':'/Belle_Rever/member/FeedBack?'+queryS,
   			'cache': false,
<!--    	 'data':{"mId":mId , "itemId":itemId}, -->
   			'datatype':'application/json;charset=utf-8',
   			'headers':{"X-Requested-With": "XmlHttpRequest"},
   			success:function(data){
<!--    			alert(data) -->
 
			alert(data.success)
   			console.log(data)
   			location.reload();
   			 
   			},error:function(data){ 
   			console.log(data)
   			 if(data.responseJSON.status == "toLogin"){
   			  	console.log(data)
       			$('.loginB').trigger('click')
       			return 
   				}
   			 if(data.responseJSON.error == "er1"){
   			 alert("已經送過愛心了喔~!")
   			 location.reload();
   			 	return 
   			 }
   				
   				
   			}
   			
   			
   		})
	})
	
	

	
	
	
<!-- 	$("input[name=Purchase]").click(){ -->
	
	
<!-- 	} -->

	
		
})
