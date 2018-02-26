<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="js/aaa.js"></script>
<link rel="stylesheet" href="css/orderManager.css" />
<link rel="stylesheet" href="/Belle_Rever/manager/nav/css/nav.css" />
<link rel="stylesheet" href="/Belle_Rever/css/styles.css"/>
<title>Shelver</title>
</head>
<body>
	<%@include file="../nav/nav.jsp" %>
	<div class="content">
	<ul class="ordStatus">
		<c:forEach var="x" items="${ordStat}">
		<li><a href="OrdList?type=${x.key}&pageNow=1">${x.value}</a></li>
		</c:forEach>
	</ul>
		<table>
			<tr>
				<td><span>訂單編號</span></td>
				<td><span>訂單人</span></td>
				<td><span>訂單金額</span></td>
				<td><span>訂單日期</span></td>
			</tr>
			<c:forEach var="x" items="${allOrder}">
			<tr>
				<td><span><a href="ModifyOrd?id=${x.ordId}"> ${x.ordId}</a></span></td>
				<td><span>${x.mId}</span></td>
				<td><span>${x.ordTotal}</span></td>
				<td><span>${x.orderDate}</span></td>
			</tr>
			</c:forEach>


		</table>
<c:if test="${pageNow > 0}">
				<div class="controlPage">

					<c:if test="${pageNow ==1 }">
						<a href="OrdList?type=${osId}&pageNow=${pageNow}">
							PREV <</a>
					</c:if>

					<c:if test="${pageNow >1 }">
						<a href="OrdList?type=${osId}&pageNow=${pageNow -1}">PREV
							<</a>
					</c:if>



					<c:if test="${totalPage <5}">
						<c:forEach var="x" begin="1" end="${totalPage}">
							<a href="OrdList?type=${osId}&pageNow=${x}">${x}</a>
						</c:forEach>
					</c:if>

					<c:if test="${totalPage > 5}">
						<c:if test="${pageNow > 3}">
							<c:if test="${totalPage - pageNow > 2}">
								<c:forEach var="x" begin="${pageNow-2 }" end="${pageNow+2 }">
									<a href="OrdList?type=${osId}&pageNow=${x}">${x}</a>
								</c:forEach>
							</c:if>

							<c:if test="${totalPage - pageNow < 3}">
								<c:forEach var="x" begin="${totalPage-4 }" end="${totalPage }">
									<a href="OrdList?type=${osId}&pageNow=${x}">${x}</a>
								</c:forEach>
							</c:if>
						</c:if>
						<c:if test="${pageNow < 4}">
							<c:forEach var="x" begin="1" end="5">
								<a href="OrdList?type=${osId}&pageNow=${x}">${x}</a>
							</c:forEach>
						</c:if>
					</c:if>

			
					<c:if test="${pageNow==totalPage}">
						<a href="OrdList?type=${osId}&pageNow=${pageNow }">
							>NEXT</a>
					</c:if>
					<c:if test="${pageNow <totalPage }">
						<a href="OrdList?type=${osId}&pageNow=${pageNow +1}">
							>NEXT</a>
					</c:if>
					<c:if test="${totalPage ==0 }">
						<a href="OrdList?type=${osId}&pageNow=${pageNow}">
							>NEXT</a>
					
			</c:if>
				</div>
			</c:if>
			




	</div>
</body>
</html>