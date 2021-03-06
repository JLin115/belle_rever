<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/Belle_Rever/home/index/bootstrap/animate.css">
<link rel="stylesheet" href="/Belle_Rever/home/index/bootstrap/bootstrap.min.css">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<!--icon    -->
<link href="https://file.myfontastic.com/q7vPAHfyZAb68aTYyhnm4S/icons.css" rel="stylesheet">
<!-- 頁首頁尾 -->
<link href="${initParam['header_footer_css']}" rel="stylesheet">

<link rel="stylesheet" href="css/aaa.css">
<script type="text/javascript" src="js/comment.js"></script>
<script type="text/javascript">
$(window).ready(function() {
	$('.member_title_img').attr('src',"${initParam['showImgRoute']}ord_top.png")
})
</script>
</head>
<body>
<jsp:include page="${initParam['header']}"></jsp:include>
	<jsp:include page="${initParam['member_header']}"></jsp:include>



	<div class="member_content" style="overflow-y:scroll;">
	<ul class="ordStatus">
		<c:forEach var="x" items="${ordStat}">
			<li><a href="ShowOrdList?type=${x.key}&pageNow=1">${x.value}</a></li>
		</c:forEach>
	</ul>
	<span><img src=""></span>

	<div class="ordlist_show">
		<div class="orderdetail_ord">
			<div>
				<span class="ordlist_title">訂單編號</span><span class="ordlist_value">：${ob.ordId}</span><br>
				<span class="ordlist_title">訂單人</span><span class="ordlist_value">：${ob.mId}</span><br>
				<span class="ordlist_title">訂購日期</span><span class="ordlist_value">：<fmt:formatDate value="${ob.orderDate}" pattern="yyyy-MM-dd" /></span><br> 
				<span class="ordlist_title">出貨日期</span><span class="ordlist_value">：<c:if test="${ob.shipDate eq '1990-01-01 00:00:00.0'}"></c:if><c:if test="${ob.shipDate ne '1990-01-01 00:00:00.0'}"><fmt:formatDate value="${ob.shipDate}" pattern="yyyy-MM-dd" />
					</c:if>
				</span><br>
				<span class="ordlist_title">折價券編號</span><span class="ordlist_value">：${ob.cpId}</span><br>
			</div>
			<div>
				<span class="ordlist_title">運送方式 </span><span class="ordlist_value">：${ob.shipType}</span><br>
				<span class="ordlist_title">訂單總價</span><span class="ordlist_value">：${ob.ordTotal}</span><br>
				<span class="ordlist_title">訂單狀態</span><span class="ordlist_value">：<c:forEach var="y" items="${ordStat}"><c:if test="${y.key == ob.osId }">${y.value}</c:if></c:forEach>
				</span><br> <span class="ordlist_title">運送地址</span><span
					class="ordlist_value">：${ob.shipAddr}</span><br>
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
					<td><img src="${initParam['itemImgRoute']}${x.itemPic1}"
						alt=""><span>${x.itemHeader}</span></td>
					<td>${x.itemColor}</td>
					<td>${x.itemSize}</td>
					<td>${x.ordQty}</td>
					<td>${x.itemPrice}</td>
					<td>${x.itemPrice * x.itemDiscount}</td>
					<td class="singlePrice">${x.itemPrice * x.itemDiscount * x.ordQty}</td>
					<td><input type="hidden" class="${x.itemId}" name="${ob.mId}"><input
						type="hidden" class="ordId" value="${ob.ordId}"><input
						type="hidden" class="ordSern" value="${x.ordSerialNumber}">
						
						<c:if test="${x.isFeedBack eq true}">
						<c:if test="${type eq 3}">
							<span class="comment customButton customButtonFix" name="${x.itemHeader}">評價</span>
<%-- 							<input class="comment" name="${x.itemHeader}" type="button" value="評價"> --%>
						</c:if> 
						<c:if test="${type ne 3}">
						<span class="customButton customButtonFix" name="${x.itemHeader}" style="color:gray;">評價</span>
<%-- 							<input class="comment" name="${x.itemHeader}" type="button" value="評價" disabled="disabled" style="color:gray;"> --%>
						</c:if>
						</c:if>
						 
						<c:if test="${x.isFeedBack eq false }">
							<span class="customButton customButtonFix" name="${x.itemHeader}" style="color:gray;">評價</span>
<%-- 							<input class="comment" name="${x.itemHeader}" type="button" value="評價" disabled="disabled" style="color:gray;"> --%>
						</c:if>
						
						</td>
				</tr>
			</c:forEach>
		</table>



	</div>

</div>



	<jsp:include page="${initParam['member_footer']}"></jsp:include>
	<script src="/Belle_Rever/home/index/bootstrap/bootstrap.js"></script>
	<jsp:include page="${initParam['footer']}"></jsp:include>
	<!-- 頁首頁尾js -->
	<script src="${initParam['header_footer_js']}"></script>


</body>
</html>