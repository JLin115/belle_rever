$(window).ready(function () {
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
})