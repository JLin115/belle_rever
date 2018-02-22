<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="js/orderModify.js"></script>
<link rel="stylesheet" href="css/OrderModify.css" />
<title>Shelver</title>
</head>
<body>
	<ul class="navbar">
		<li><a href="#">商品管理</a></li>
		<li><a href="#">訂單管理</a></li>
		<li><a href="#">會員管理</a></li>
	</ul>
	<div class="content">

		<ul class="ordStatus">
			<c:forEach var="x" items="${ordStat}">
				<li><a href="OrdList?type=${x.key}">${x.value}</a></li>
			</c:forEach>
		</ul>

		<div class="ord">
			<div>
				<span class="title">訂單編號</span><span class="value">:${ob.ordId}</span><br>
				<span class="title">訂單人</span><span class="value">:${ob.mId}</span><br>
				<span class="title">訂購日期</span><span class="value">:<fmt:formatDate value="${ob.orderDate}" pattern="yyyy-MM-dd"/></span><br>
				<span class="title">出貨日期 </span><span class="value">:<input type="text" class="sd" value="<fmt:formatDate value="${ob.shipDate }" pattern="yyyy-MM-dd"/>">${errorMsg.shipDateError }</span><br> 
				<span class="title">運送方式 </span><span class="value">:${ob.shipType}</span><br>
			</div>

			<div>

				<span class="title">運送地址</span><span class="value">:${ob.shipAddr}</span><br>
				<span class="title">折價券編號</span><span class="value">:<input
					type="text" class="cp" value="${ob.cpId}">${errorMsg.CouponError }</span><br>
				<span class="title">訂單狀態</span><span class="value">:<select class="stat">
						<c:forEach var="x" items="${ordStat}" >
						<c:choose>
						    <c:when test="${x.key == ob.osId}">
						    <option value="${x.key }" selected="selected">${x.value }</option>
						    </c:when>
						<c:otherwise> 
						<option value="${x.key }">${x.value }</option>
						</c:otherwise>
						</c:choose>
							
						</c:forEach>
				</select>
				</span>
				
				
				
				<br> <span class="title">訂單總價</span><span class="value">:${ob.ordTotal}</span><br>
				<form class="form"action="CheckOrd" method="GET">
				<input type="hidden" name="ordid" value="${ob.ordId}">
				<input type="hidden" name="coupon" value="">
				<input type="hidden" name="sDate" value="">
				<input type="hidden" name="stat" value="">
				<input class="button" type="submit" value="確認">
				</form>
				
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
				<td>刪除</td>
			</tr>
			
			<c:forEach items="${ovb}" var="x">
			<tr>
				<td><img src="${initParam['itemImgRoute']}${x.itemPic1}" alt=""><span>${x.itemHeader}</span></td>
				<td>${x.itemColor}</td>
				<td>${x.itemSize}</td>
				<c:if test="${x.itemQty > 0 }">
				<td><input type="hidden" name="ordId" value="${x.ordId}" />
				<select class="qty" name="${x.ordSerialNumber}">
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
				<td><input type="hidden" name="${x.ordSerialNumber}" value="${x.ordId}" /><img class="delete"  height="20px" src="/bimg/1.jpg"></td>
			
			</tr>

		</c:forEach>
			</table>
			
			</div>
</body>
</html>