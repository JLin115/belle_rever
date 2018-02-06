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
			
	$("input[name = Purchase]").click(function(){
	$("form[name=form]").attr("action","/Belle_Rever/home/Purchase");
	if(
	$("input[name=color]").is(':checked')
	&&
	$("input[name=size]").is(':checked')
	){
	$("form[name=form]").submit();
	}
	});
	
	$("input[name = Cart]").click(function(){
	$("form[name=form]").attr("action","/Belle_Rever/home/AddCart");
	if(
	$("input[name=color]").is(':checked')
	&&
	$("input[name=size]").is(':checked')
	){
	$("form[name=form]").submit();}
	});
	
		
})
