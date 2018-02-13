<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
$(window).ready(function(){

	$("div[class^=type]").click(function () {
		var type= $(this).text().trim();
		if(type =="超商取貨"){
		window.open('http://www.ezship.com.tw/emap/rvpara_web.jsp?su_id=buyer@myweb.com.tw&order_id=060612345&rturl=http://localhost:8080/Belle_Rever/home/purchase/FillOrdInfo.jsp&rv_name=convenience&rv_email=asdf@adsf.asdf&rv_mobil=1&rv_amount=11&webtemp=convenience','','status=yes,scrollbars=yes,resizable=yes,width=840,height=560,left=0,top=0')
			$(".inputType").empty()
		}

		if(type =="宅配"){
			$(".inputType").empty()
			$(".inputType").append('<span>地址:</span><input type="text" name="st_addr" value="${param.st_addr}">')
			$("input[name=stype]").val("Delivery");
		}

	  })



})

