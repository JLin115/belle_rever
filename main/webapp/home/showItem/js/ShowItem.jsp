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
 		$(".div").append('<input type="radio" name="color" value="'+item+'">'+item);
	})
	
	
		
});
