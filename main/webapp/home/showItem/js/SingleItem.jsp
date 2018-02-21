<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

var x = ${gson};
$(window).ready(function() {

	



	var length = x.length;
	var color = new Set();
	
	x.forEach(item => {
 		color.has(item.itemColor) ? null : color.add(item.itemColor);

	})
	
 	color.forEach(item => {
 		$(".div").append('<input type="radio" class="color"  name="color" value="'+item+ '"/>'+item);
	})
	
	
	$(".color").change(function (){
<!-- 	alert(this.value); -->
	var temp =this.value;
	$(".size").remove();
		x.forEach(item =>{
			if(item.itemColor == temp){
	
				if(item.itemQty>0){
				$(".div2").append('<span class="size"><input type="radio"  name="size" value="'+item.itemSize+ '"/>'+item.itemSize+'</span>');
				}else{
				$(".div2").append('<span class="size"><input type="radio"  name="size" disabled value="'+item.itemSize+ '"/>'+item.itemSize+'</span>');
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
	
	
	$("input[type=button]").click(function(){

		if( $("input[name=color]").is(':checked') && $("input[name=size]").is(':checked') ){
		
		var itemId = $("input[name=id]").val();
		var itemSerN = $("input[name=serN]").val();
		type=$(this).attr("name");
	
		$.ajax({
			'type':'GET',
			'url':'/Belle_Rever/home/cart/AddCart',
			'datatype':'text',
			'data':{
				'itemId':itemId,
				'itemSerN':itemSerN,
				'type':type
			},
			'success':function(data ){
			alert(data.status)
			if(type=='Cart'){
				alert(data)
				window.location.reload();
			}else if(type=='Purchase'){
			window.location='/Belle_Rever/home/purchase/FillOrdInfo.jsp';
				}
<!-- 			
			
			}
		 	})
	   }
		})

	
	
	
<!-- 	$("input[name=Purchase]").click(){ -->
	
	
<!-- 	} -->

	
		
})
