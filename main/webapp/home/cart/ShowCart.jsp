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
<script type="text/javascript" src="js/ShowCart.js"></script>
<link rel="stylesheet" href="css/ShowCart.css" />
<title>Shelver</title>
</head>
<body>
<div class="content">
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
				<c:if test="${x.itemQty > 0 }">
					<td><select class="qty" name="${x.ordSerialNumber}">
							<c:forEach var="y" begin="1" end="${x.itemQty}">
								<c:choose>
									<c:when test="${x.ordQty  == y  }">
										<option value="${y}" selected="selected">${y}</option>
									</c:when>
									<c:otherwise>
										<option value="${y}">${y}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</c:if>
				<td>${x.itemPrice}</td>
				<td>${x.itemPrice * x.itemDiscount}</td>
				<td class="singlePrice" name="singlePrice">${x.itemPrice * x.itemDiscount * x.ordQty}</td>
				<td><a href="/Belle_Rever/home/Delete_qty?ordSerN=${x.ordSerialNumber}"><img class="delete" height="20px" src="/bimg/1.jpg"></a></td>
			
			</tr>

		</c:forEach>
	</table>
	<div class="total"></div>
	<div><input type="button" onclick="window.location.href =' /Belle_Rever/home/purchase/FillOrdInfo.jsp'" value="送出"></div>
</div>


</body>
</html>