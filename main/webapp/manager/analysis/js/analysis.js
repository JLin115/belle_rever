$(window).load(function() {
	

	/*下拉框事件*/
		$('.Yopt').on('change',function(){
		$('.anaUl').text(' ')
			$('.anaUl').append(
					'<li class="'+$(this).val()+'">年度分析</li>'+
					'<li class="1">Jan</li>'+
					'<li class="2">Feb</li>'+
					'<li class="3">Mar</li>'+
					'<li class="4">Apr</li>'+
					'<li class="5">May</li>'+
					'<li class="6">Jun</li>'+
					'<li class="7">Jul</li>'+
					'<li class="8">Aug</li>'+
					'<li class="9">Sep</li>'+
					'<li class="10">Oct</li>'+
					'<li class="11">Nov</li>'+
					'<li class="12">Dec</li>'
			)
	
	
		})
		
		
		
		/*載入得到所有年份*/
		$.ajax({
			'type':'GET',
			'url':'/Belle_Rever/manager/analysis/getAllYear',
			'cache': false,
			'headers':{"X-Requested-With": "XmlHttpRequest"}, 
			success:function(data){
				setList(data)
			},error:function(data){}
		})
		function setList(data){
		for(var d in data){
		$('.Yopt').append(
				'<option>'+data[d]+'</option>'
		)}
		}
		/**/
	
		
		/*得到年度報表*/
		function getMonthAna(year){
		$.ajax({
			'type':'GET',
			'url':'/Belle_Rever/manager/analysis/getMonthAna',
			'cache': false,
			'data':{'year':year},
			'headers':{"X-Requested-With": "XmlHttpRequest"}, 
			success:function(data){
			showAna(data)
			},error:function(data){}
			})
		} 
		
		/*將所有欄位建立事件*/
		$('body').on('click','.anaUl li',function(){
			var x = $(this).attr('class')
			if(x.length ==4){
				getMonthAna(x) 
			}else{
				getSingleMon($('.Yopt').val(),x) 
			}
		})
		
		/*年度單月賣場報表*/
		function getSingleMon(year,mon){
			$.ajax({
				'type':'GET',
				'url':'/Belle_Rever/manager/analysis/getSingleMon',
				'cache': false,
				'data':{'year':year,'mon':mon},
				'headers':{"X-Requested-With": "XmlHttpRequest"}, 
				success:function(data){
				console.log(data)
				showSingMon(data,year,mon)
				},error:function(data){}
				})
			}
		/*年度單月報表顯示*/
		function showSingMon(data,year,mon) {
			var setdata = new Array();
			for(var d in data){
				setdata[d] = {label : data[d].type,y : data[d].total}
			}
		console.log(setdata)
		var chart = new CanvasJS.Chart("chartContainer", {
			theme : "light", // "light2", "dark1", "dark2"
			animationEnabled : false, // change to true
			title : {
				text :year+'-'+mon+"月分析"
			},
			data : [ {
				// Change type to "bar", "area", "spline", "pie",etc.
				type : "column",
				yValueFormatString : "$#,##0",
				dataPoints : setdata
			} ]
		});
		chart.render();
			
		}
		/*年度報表顯示*/
	function showAna(data){
		var Jan=0;
		var Feb=0;
		var Mar=0;
		var Apr=0;
		var May=0;
		var Jun=0;
		var Jul=0;
		var Aug=0;
		var Sep=0;
		var Oct=0;
		var Nov=0;
		var Dec=0;
		for(var d in data){
			if(data[d].month=='1'){
			Jan = data[d].total;
			}else if(data[d].month=='2'){
			Feb = data[d].total;
			}else if(data[d].month=='3'){
			Mar = data[d].total;
			}else if(data[d].month=='4'){
			Apr = data[d].total;
			}else if(data[d].month=='5'){
			May = data[d].total;
			}else if(data[d].month=='6'){
			Jun = data[d].total;
			}else if(data[d].month=='7'){
			Jul = data[d].total;
			}else if(data[d].month=='8'){
			Aug = data[d].total;
			}else if(data[d].month=='9'){
			Sep = data[d].total;
			}else if(data[d].month=='10'){
			Oct = data[d].total;
			}else if(data[d].month=='11'){
			Nov = data[d].total;
			}else if(data[d].month=='12'){
			Dec = data[d].total;
			}
		}
		
	var chart = new CanvasJS.Chart("chartContainer", {
		theme : "light", // "light2", "dark1", "dark2"
		animationEnabled : false, // change to true
		title : {
			text :$('.Yopt').val()+"年度分析"
		},
		data : [ {
			// Change type to "bar", "area", "spline", "pie",etc.
			type : "column",
			yValueFormatString : "$#,##0",
			dataPoints : [ 
			    {label : "Jan",y : Jan}, 
				{label : "Feb",y : Feb}, 
				{label : "Mar",y : Mar}, 
				{label : "Apr",y : Apr},
				{label : "May",y : May}, 
				{label : "Jun",y : Jun}, 
				{label : "Jul",y : Jul}, 
				{label : "Aug",y : Aug}, 
				{label : "Sep",y : Sep},
				{label : "Oct",y : Oct}, 
				{label : "Nov",y : Nov}, 
				{label : "Dec",y : Dec} ]
		} ]
	});
	chart.render();}

})