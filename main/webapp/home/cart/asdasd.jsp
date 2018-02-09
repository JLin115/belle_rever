<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function change_page(form){
	form.action="./eamp_sample_2.jsp";
	form.submit();
}

</script>
</head>
<body>


<form method="post" name="form">
	<input type="text" name="order_id" value='${param.order_id} '>
	<input type="text" name="st_cate"	 value='${param.st_cate}'>
	<input type="text" name="st_code"	 value='${param.st_code}'>
	<input type="text" name="st_name"	 value='${param.st_name}'>
	<input type="text" name="st_addr"	 value='${param.st_addr}'>
	<input type="text" name="st_tel"	 value='${param.st_tel}'>
	<input type="text" name="webtemp"	 value='${param.webtemp}'>
	<input type="text" name="sn_id"	 value=''>
	<input name="Submit2" type="button" value="取貨不付款 選擇門市" onClick="change_page(document.form)">
<!-- 	<input value="  取貨付款  選擇門市   " type="button" name="simulate" onClick="window.location('https://map.ezship.com.tw/ezship_map_web_2014.jsp?su_id=buyer@myweb.com.tw&order_id=060612345&rturl=http://localhost:8080/Belle_Rever/home/cart/asdasd.jsp&rv_name=謝無忌&rv_email=service@yahoo.com.tw&rv_mobil=0987654321&rv_amount=299&webtemp=s2s','','status=yes,scrollbars=yes,resizable=yes,width=840,height=560,left=0,top=0');"> -->
	<input value="  取貨付款  選擇門市   " type="button" name="simulate" onClick="window.open('http://www.ezship.com.tw/emap/rvpara_web.jsp?su_id=buyer@myweb.com.tw&order_id=060612345&rturl=http://localhost:8080/Belle_Rever/home/cart/asdasd.jsp&rv_name=1&rv_email=asdf@adsf.asdf&rv_mobil=1&rv_amount=11&webtemp=1&xx=1','','status=yes,scrollbars=yes,resizable=yes,width=840,height=560,left=0,top=0');">
</form>

</body>
</html>