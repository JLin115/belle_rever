$(window).ready(function () {
  	
	var pageNow=$('.pageNow').val()
	var totalPage=$('.totalPage').val()
	var itid=$('.itid').val()
	console.log(res)
 
 
	
	for(var c in res){
		
		$('.insertContent').append(
				'<tr>'+
				'<td><img src="/bimg/commemtImg/'+res[c].feedBackPic+'"></td>'+
				'<td>'+res[c].itemId+'</td>'+
				'<td>'+res[c].mId+'</td>'+
				'<td><div style="word-break: break-all;">'+res[c].feedBackVal+'</div></td>'+
				'<td><span class="icon-trash-o deleteFB"></span></td>'+
				'</tr>'
					)		
		
	}
	
	$('.deleteFB').on('click',function(){
		
		var itemid = $(this.parentNode.previousSibling.previousSibling.previousSibling ).text();
		var mid = $(this.parentNode.previousSibling.previousSibling).text();
	 
		 $.ajax({
	 			type:'Get',
	 			url:'/Belle_Rever/manager/feedBackManager/manager_feebBack_delete',
	 			cache: false,
	 			headers:{"X-Requested-With": "XmlHttpRequest"},
	 			contentType: 'application/x-www-form-urlencoded;charset=utf-8',
	 			data:{"itemId":itemid ,"mId": mid },
	 			success:function(data){
	 				 alert(data)
	 				 location.reload()
	 				
	 				
	 			return
	 			},error:function(data){
	 				 
	 			}
	 			})
		
		
	})
	
	
	 
	
	
})