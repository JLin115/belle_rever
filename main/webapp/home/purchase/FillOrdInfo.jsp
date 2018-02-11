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
<script type="text/javascript" src="js/aaa.jsp"></script>
<link rel="stylesheet" href="css/aaa.css" />
</head>
<body>
	
	
	<div class="sType">
		<form action="" name="form" method="get">
			<div class="type1">超商取貨</div>
			<div class="type2">宅配</div>
			<input type="hidden" name="stype" value="">
			<div class="inputType">
				<c:if test="${param.st_cate!=TOK }">
					<span>超商名稱:</span>
					<input type="text" name="st_name" value="${param.st_name}" readonly>
					<br>
					<span>超商地址:</span>
					<input type="text" name="st_addr" value="${param.st_addr}" readonly>
				</c:if>
			</div>
		</form>
	</div>



	<div class="ordData">
		<table>
			<tr>
				<td>商品名稱</td>
				<td>顏色</td>
				<td>尺寸</td>
				<td>數量</td>
				<td>單價</td>
				<td>折扣</td>
				<td>小計</td>
				<td>刪除</td>
			</tr>
			<c:forEach items="${Cart}" var="x">
				<tr>
					<td><img src="/bimg/${x.itemPic1}" alt=""><span>${x.itemHeader}</span></td>
					<td>${x.itemColor}</td>
					<td>${x.itemSize}</td>
					<td>${x.ordQty}</td>
					<td>${x.itemPrice}</td>
					<td>${x.itemPrice * x.itemDiscount}</td>
					<td class="singlePrice">${x.itemPrice * x.itemDiscount * x.ordQty}</td>
				</tr>

			</c:forEach>
		</table>
	</div>


</body>
</html>