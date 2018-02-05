<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
	<script type="text/javascript"src="js/ShowItem.jsp"></script>
	
	
<title>Insert title here</title>
</head>
<body>
	<div class="div"></div>








	<div class="d1">
		<span>編號：</span><input type="text" name='id' value="${ib.itemID }" />
		<p>${errorMsg.idError}</p>
		<br> <span>價格：</span><input type="text" name='price'
			value="${ib.itemPrice }" />
		<p>${errorMsg.priceError}</p>
		<br> <span>標頭：</span><input type="text" name='title'
			value="${ib.itemHeader}" />
		<p>${errorMsg.titleError}</p>
		<br> <span>描述：</span>
		<textarea class="des" name='des' maxlength='100'>${ib.itemDes }</textarea>
		<p class="areap">${errorMsg.desError}</p>
		<br> <span>類型：</span>

	</div>
	<div class="d2">
		<div class="auto">

				



					<div id="auto${x.itemSerialNumber}">
					<span>流水號：${x.itemSerialNumber}</span><br> <span> 顏色：</span><input
						type="text" name='color${x.itemSerialNumber}'
						value="${x.itemColor }" /><br> <span>尺寸：</span><input
						type="text" name='size${x.itemSerialNumber}'
						value="${x.itemSize }" /><br> <span> 庫存：</span><input
						type="text" name='stock${x.itemSerialNumber}'
						value="${x.itemQty }" />
</div>

		</div>
	</div>
</body>
</html>