<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Belle_Rever/home/index/bootstrap/animate.css">
<link rel="stylesheet" href="/Belle_Rever/home/index/bootstrap/bootstrap.min.css">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>

<!--icon    -->
<link href="https://file.myfontastic.com/q7vPAHfyZAb68aTYyhnm4S/icons.css" rel="stylesheet">
<!-- 頁首頁尾 -->
<link href="${initParam['header_footer_css']}" rel="stylesheet">



<script type="text/javascript" src="js/ShowCart.js"></script>
<link rel="stylesheet" href="css/ShowCart.css" />
<title>Shelver</title>
</head>
<body>
<jsp:include page="${initParam['header']}"></jsp:include>
<div class="content">
	<table class="showCart">
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
				<td><img src="${initParam['itemImgRoute']}${x.itemPic1}" alt=""><span>${x.itemHeader}</span></td>
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
				<td class="singlePrice2" name="singlePrice">${x.itemPrice * x.itemDiscount * x.ordQty}</td>
				<td><img class="delete" name="${x.ordSerialNumber}" height="20px" src="/bimg/itemImg/1.jpg"></td>
			</tr>

		</c:forEach>
	</table>
	<div class="total_showCart"></div>
	<div><input type="button" class="confirmOrd_showCart" value="送出"></div>
	 
</div>
<jsp:include page="${initParam['footer']}"></jsp:include>

<!-- 頁首頁尾js -->
<script src="${initParam['header_footer_js']}"></script>
</body>
</html>