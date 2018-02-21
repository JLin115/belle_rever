<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/ShowItem.css" />
<link rel="stylesheet" href="css/ShowCart.css" />

<script type="text/javascript">
$(window).ready(function(){
$(".slide").on("mouseenter",function () {
	$(".inner").stop();
	$(".inner").slideDown(500);
	
})

$(".slide").on("mouseleave",function () {
	$(".inner").stop();
	$(".inner").slideUp(500);
})
})
</script>
<title>Shelver</title>
</head>
<body>
 
	<ul class="sidebar">
		<li><form>
				<input type="text"><input type="submit">
			</form></li>
		<c:forEach var="x" items="${itemType}">
			<li><a href="ShowItem?itid=${x.key }&pageNow=1">${x.value}</a></li>
		</c:forEach>
		<li class="slide">
		 購物車 
		<div class="inner">
<%-- 		<%@include file="Cart.jsp" %> --%>
		<jsp:include page="Cart.jsp"></jsp:include>
		</div>
		</li>
	</ul>
	<div class="show">
		<ul>
			<c:forEach var="items" items="${allItem}">
				<a href="ShowSingleItem_home?itemId=${items.itemID}">
					<li><img src="${initParam['itemImgRoute']}${items.itemPic1}"/>
						<div>${items.itemHeader}</div>
						<fmt:formatNumber var="c" value="${items.itemPrice *items.itemdiscount.doubleValue()}" pattern="#"/>
						<c:choose>
							<c:when test="${items.itemdiscount.doubleValue()  eq 1.00 }">
							NT:${c}
							</c:when>
							<c:when test="${items.itemdiscount.doubleValue()  > 1.00 }">
							NT:${c}
								</c:when>
							<c:otherwise>
							<div class="onsales">NT:${items.itemPrice}</div>
							NT:${c}
							</c:otherwise>
						</c:choose>
					</li>
				</a>
			</c:forEach>
		</ul>
		<div class="controlPage">
			<c:if test="${pageNow > 0}">
				<div class="controlPage">

					<c:if test="${pageNow ==1 }">
						<a href="ShowItem?itid=${itid}&pageNow=${pageNow }"> PREV <</a>
					</c:if>

					<c:if test="${pageNow >1 }">
						<a href="ShowItem?itid=${itid}&pageNow=${pageNow -1}">PREV <</a>
					</c:if>



					<c:if test="${totalPage <5}">
						<c:forEach var="x" begin="1" end="${totalPage}">
							<a href="ShowItem?itid=${itid}&pageNow=${x}">${x}</a>
						</c:forEach>
					</c:if>

					<c:if test="${totalPage > 5}">
						<c:if test="${pageNow > 3}">
							<c:if test="${totalPage - pageNow > 2}">
								<c:forEach var="x" begin="${pageNow-2 }" end="${pageNow+2 }">
									<a href="ShowItem?itid=${itid}&pageNow=${x}">${x}</a>
								</c:forEach>
							</c:if>

							<c:if test="${totalPage - pageNow < 3}">
								<c:forEach var="x" begin="${totalPage-4 }" end="${totalPage }">
									<a href="ShowItem?itid=${itid}&pageNow=${x}">${x}</a>
								</c:forEach>
							</c:if>
						</c:if>
						<c:if test="${pageNow < 4}">
							<c:forEach var="x" begin="1" end="5">
								<a href="ShowItem?itid=${itid}&pageNow=${x}">${x}</a>
							</c:forEach>
						</c:if>
					</c:if>


					<c:if test="${pageNow==totalPage}">
						<a href="ShowItem?itid=${itid}&pageNow=${pageNow }"> >NEXT</a>
					</c:if>
					<c:if test="${pageNow <totalPage }">
						<a href="ShowItem?itid=${itid}&pageNow=${pageNow +1}"> >NEXT</a>
					</c:if>
					<c:if test="${totalPage ==0 }">
						<a href="ShowItem?itid=${itid}&pageNow=${pageNow}"> >NEXT</a>

					</c:if>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>