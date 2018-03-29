<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<script type="text/javascript" src="js/orderModify.js"></script>
<link rel="stylesheet" href="/Belle_Rever/css/styles.css" />
<link rel="stylesheet" href="css/OrderModify.css" />
<link rel="stylesheet" href="/Belle_Rever/manager/nav/css/nav.css" />
<script type="text/javascript"src="../nav/js/nav.js"></script>
<title>Shelver</title>
</head>
<body>
	<%@include file="../nav/Nav.jsp"%>

	<div class="maneger_index_show">
		<div class="maneger_index_show_title_img"><img src="${initParam['showImgRoute'] }manage_ord.png"/></div>
		<div class="maneger_index_show_right" style="margin-left: 10%;border:2px solid #d8d0d0;">
			<div class="content">
				<!-- 		<ul class="ordStatus"> -->
				<%-- 			<c:forEach var="x" items="${ordStat}"> --%>
				<%-- 				<li><a href="OrdList?type=${x.key}">${x.value}</a></li> --%>
				<%-- 			</c:forEach> --%>
				<!-- 		</ul> -->

				<div class="ord">
					<div>
						<span class="title">訂單編號</span><span class="value">：${ob.ordId}</span><br>
						<span class="title">訂單人</span><span class="value">：${ob.mId}</span><br>
						<span class="title">訂購日期</span><span class="value">：<fmt:formatDate value="${ob.orderDate}" pattern="yyyy-MM-dd" /></span><br> 
						<span class="title">出貨日期 </span><span class="value">：<input type="text" class="sd" value="<fmt:formatDate value="${ob.shipDate }" pattern="yyyy-MM-dd"/>"></span><span class="error">${errorMsg.shipDateError }</span><br>
						<span class="title">運送方式 </span><span class="value">：${ob.shipType}</span><br>
					</div>

					<div>

						<span class="title">運送地址</span><span class="value">：${ob.shipAddr}</span><br>
						<span class="title">折價券編號</span><span class="value">：<input
							type="text" class="cp" value="${ob.cpId}"></span><span class="error">${errorMsg.CouponError }</span><br>
						<span class="title">訂單狀態</span><span class="value">：<select
							class="stat">
								<c:forEach var="x" items="${ordStat}">
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
						</span> <br> <span class="title">訂單總價</span><span class="value">：${ob.ordTotal}</span><br>
						<form class="form" action="CheckOrd" method="GET">
							<input type="hidden" name="ordid" value="${ob.ordId}"> <input
								type="hidden" name="coupon" value=""> <input
								type="hidden" name="sDate" value=""> <input
								type="hidden" name="stat" value=""> <input
								class="button" type="submit" value="確認">
						</form>

					</div>
				</div>




				<table class="ord_val">
					<thead>
					<tr style="text-align: left ;">
						<th class="table_title">商品名稱</th>
						<th>顏色</th>
						<th>尺寸</th>
						<th>數量</th>
						<th>單價</th>
						<th>折扣</th>
						<th>小計</th>
						<th>刪除</th>
					</tr>
					</thead>

					<c:forEach items="${ovb}" var="x">
						<tr>
							<td><img src="${initParam['itemImgRoute']}${x.itemPic1}"
								alt=""><span>${x.itemHeader}</span></td>
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
							<c:if test="${x.itemQty == 0 }">
							<td>  
							<select>
							<option value="1" selected="selected">1</option>
							</select>
							</td>
							</c:if>
							
							<td>${x.itemPrice}</td>
							<td>${x.itemPrice * x.itemDiscount}</td>
							<td class="singlePrice" name="singlePrice">${x.itemPrice * x.itemDiscount * x.ordQty}</td>
							<td><input type="hidden" name="${x.ordSerialNumber}"
								value="${x.ordId}" /><span class="icon-trash-o delete" class="delete" style="padding-left: 5px;"></span></td>

						</tr>

					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>