
$(window).ready(function () {

	$(".comment").click(function () {
	$(this).attr("disabled","disabled")
	var height =280
	var width = 408
	var left = (screen.width/2)-(height/2)
	var top = (screen.height/2)-(width/2)
	var title  = $(this).attr('name')
	var itemId =	$(this.parentNode.firstChild).attr("class")
	var ordId = $(this.parentNode.firstChild.nextSibling).val()
	var ordSern = $(this.parentNode.firstChild.nextSibling.nextSibling).val()
	var mid =	$(this.parentNode.firstChild.nextSibling).attr("name")
	var url='Comment.jsp?title='+title+'&itemId='+itemId+'&ordId='+ordId+'&ordSern='+ordSern;
	parent=	window.open(url, '評價', 'toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,height='+height+',width='+width+',left='+left+' ,top='+top);
	});
//	$(parent).unload(function () {
//		alert();
//	})
	$("#file").change(function () {
		readURL(this)
	})
	
	  $("form").submit(function(evt){	 
      evt.preventDefault();
      var formData = new FormData($(this)[0]);
      $.ajax({
		'type':'Post',
		'url':'InsertCommemt',
		'cache': false,
	    'contentType': false,
	    'enctype': 'multipart/form-data',
		'headers':{"X-Requested-With": "XmlHttpRequest"},
		'async': false,
		'data':	formData,
		'datatype':'JSON',
		'processData': false,
		success:function(data){ 
		
		window.opener.onChildClosed()
		alert(data)
		window.close();

		return
		},error:function(data){
			window.opener.onChildClosed()
			alert("");
			console.log(data)
			var errors = data.responseJSON;
			 $(".commemtError").text(errors.CommemtError)
			 $(".picError").text(errors.picError)
		}
		})
	
	
	  })
      
//	此方法參考網路
	function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function(e) {
      $('.img').attr('src', e.target.result);
      $('.img').css({'width':'195px',
    	  			 'height':'260px'
      })
    }
    reader.readAsDataURL(input.files[0]);
  }
}
	
	
	
	 window.onChildClosed = function () {
		 
		 window.location.reload();
	}
	
});