$(window).ready(function () {
	/* logout */
	
    $(".logoutSpan").on('click',function () {
   	
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
})