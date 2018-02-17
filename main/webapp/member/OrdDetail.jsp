<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/aaa.css">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="js/comment.js" ></script>
</head>
<body>
	<ul class="navbar">
		<li><a href="MemberModify.jsp">會員資料</a></li>
		<li><a href="ShowOrdList?type=1">訂單查詢</a></li>

	</ul>
	<ul class="ordStatus">
		<c:forEach var="x" items="${ordStat}">
			<li><a href="ShowOrdList?type=${x.key}">${x.value}</a></li>
		</c:forEach>
	</ul>

	<div class="show">
		<div class="ord">
		<div>
			<span class="title">訂單編號</span><span class="value">:${ob.ordId}</span><br>
			<span class="title">訂單人</span><span class="value">:${ob.mId}</span><br>
			<span class="title">訂購日期</span><span class="value">:<fmt:formatDate value="${ob.orderDate}" pattern="yyyy-MM-dd"/></span><br>
			<span class="title">出貨日期</span><span class="value">:<fmt:formatDate value="${ob.shipDate }" pattern="yyyy-MM-dd"/></span><br>
			<span class="title">折價券編號</span><span class="value">:${ob.cpId}</span><br>


		</div>
		<div>
			<span class="title">運送方式 </span><span class="value">:${ob.shipType}</span><br>
			<span class="title">訂單總價</span><span class="value">:${ob.ordTotal}</span><br>
			<span class="title">訂單狀態</span><span class="value">:<c:forEach var="y" items="${ordStat}"><c:if test="${y.key == ob.osId }">${y.value}</c:if></c:forEach>
			</span><br>
			<span class="title">運送地址</span><span class="value">:${ob.shipAddr}</span><br>
			<c:forEach var="y" items="${ordStat}">
							<c:if test="${y.key == x.osId }">
								${y.value}
							</c:if>
			 </c:forEach>

		</div>

</div>




		<table class="ord_val">
			<tr>
				<td>商品名稱</td>
				<td>顏色</td>
				<td>尺寸</td>
				<td>數量</td>
				<td>單價</td>
				<td>折扣</td>
				<td>小計</td>
				<td>上傳評價</td>
			</tr>

			<c:forEach items="${ovb}" var="x">
				<tr>
					<td><img src="${initParam['itemImgRoute']}${x.itemPic1}" alt=""><span>${x.itemHeader}</span></td>
					<td>${x.itemColor}</td>
					<td>${x.itemSize}</td>
					<td>${x.ordQty}</td>
					<td>${x.itemPrice}</td>
					<td>${x.itemPrice * x.itemDiscount}</td>
					<td class="singlePrice">${x.itemPrice * x.itemDiscount * x.ordQty}</td>
					<td><input type="hidden" class="${x.itemId}" name="${ob.mId}"><input type="hidden" class="ordId" value="${ob.ordId}"><input type="hidden" class="ordSern" value="${x.ordSerialNumber}">
					<c:if test="${x.isFeedBack eq true}">
						<input class="comment" name="${x.itemHeader}" type="button" value="評價" >
					</c:if>
					<c:if test="${x.isFeedBack eq false}">
					 	<input class="comment" name="${x.itemHeader}" type="button" value="評價"  disabled="disabled">
					</c:if>
					</td>
					
					
				</tr>

			</c:forEach>
		</table>



	</div>


</body>
</html>