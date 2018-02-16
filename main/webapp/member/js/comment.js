$(window).ready(function () {
	$(".comment").click(function () {
	var itemId =	$(this.parentNode.firstChild).attr("class")
	var mid =	$(this.parentNode.firstChild).attr("name")	
		alert(itemId+","+mid)
		window.open('Comment.jsp', '評價', config='height=400px,width=400px');
	});
	
	
	
	
	
});