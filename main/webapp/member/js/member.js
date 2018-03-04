$(window).ready(function () {
	 $.ajax({
			'type':'Get',
			'url':'ShowOrdList_Member',
			'cache': false,
		    'contentType':false,
		    'enctype': 'multipart/form-data',
			'headers':{"X-Requested-With": "XmlHttpRequest"},
			'async': false,
			'datatype':'JSON',
			'processData': false,
			success:function(data){ 
				console.log(data)
				createOrd(data)
				return
			},error:function(data){
				 console.log(data)
				 return
			}
			})
	
			
	function createOrd(data){
		 
		 $.each(data,function(index, value){
			 var status = value.osId
			 if(status ==1 ){
				 status =  "待確認"
			 }else if(status ==2){
				 status =  "處理中"
			 }else if(status ==3){
				 status =  "已出貨" 
			 }
			 var d = formatDAte(value.orderDate)
			 
			 var content = '<div class="ordlist_show">'+
				 				'<a href="OrdDetail?ordId='+value.ordId+'">'+
				 					'<div class="ordlist_content">'+
				 						'<div>'+
				 							'<span style="color:red; font-weight:bolder;">訂單編號</span><span style="color:red; font-weight:bolder;">:'+value.ordId+'</span>'+
											'<span>訂單狀態</span><span>:'+status+'</span>'+
											'<span>訂單金額</span><span>:'+value.ordTotal+'</span>'+
										'</div>'+
										'<div>'+
				 							'<span>訂購日期</span><span>:'+d+'</span>'+
				 							'<span>出貨日期</span><span>:</span>'+
				 						'</div>'+
				 					'</div>'+
				 				'</a>'+
				 			'</div>'
			 $('.member_content').append(content)
			 
			 
			});
	}
	 
	 
	 
	 function formatDAte(date) {
		 date =  date.replace(',','').split(' ');
		 if(date[1].length<2){
			 date[1]  = '0'+date[1]
		 }
		 if(date[0] == 'Jan'){
			 return date[2] + ' -01- ' +date[1] +'  '+date[3]; 
		 }else if(date[0] == 'Feb'){
			 return date[2] + '-02-' +date[1] +'  '+date[3];
		 }else if(date[0] == 'Mar'){ 
			 return date[2] + '-03-' +date[1] +'  '+date[3];
		 }else if(date[0] == 'Apr'){
			 return date[2] + '-04-' +date[1] +'  '+date[3];
		 }else if(date[0] == 'May'){
			 return date[2] + '-05-' +date[1] +'  '+date[3];
		 }else if(date[0] == 'Jun'){
			 return date[2] + '-06-' +date[1] +'  '+date[3];
		 }else if(date[0] == 'Jul'){
			 return date[2] + '-07-' +date[1] +'  '+date[3];
		 }else if(date[0] == 'Aug'){
			 return date[2] + '-08-' +date[1] +'  '+date[3];
		 }else if(date[0] == 'Sep'){
			 return date[2] + '-09-' +date[1] +'  '+date[3];
		 }else if(date[0] == 'Oct'){
			 return date[2] + '-10-' +date[1] +'  '+date[3];
		 }else if(date[0] == 'Nov'){
			 return date[2] + '-11-' +date[1] +'  '+date[3];
		 }else if(date[0] == 'Dec'){ 
			 return date[2] + '-12-' +date[1] +'  '+date[3];
		 }
		 
		
	}
	
	
	
	
})