<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/itemManager.css" />
<title>Shelver</title>
</head>
<body>
	 
	<ul class="navbar">
		<li><a href="ItemManager.jsp">商品管理</a></li>
		<li><a href="#">訂單管理</a></li>
		<li><a href="${pageContext.request.contextPath}/manager/memberManager/ShowAllMemeber?pageNow=1">會員管理</a></li>
	</ul>
	<div class="content">
		<ul class="sidebar">
			<li><form>
					<input type="text"><input type="submit">
				</form></li>

			<c:forEach var="x" items="${itemType}">
				<li><a href="ItemManagerServlet?itid=${x.key }&pageNow=1">${x.value}</a></li>
			</c:forEach>


		</ul>
		<div class="show">

			<c:forEach var="items" items="${allItem}">

				<a href="ShowSingleItem?itemId=${items.itemID}">
					<ul>
						<li><img src="/bimg/${items.itemPic1}">
						 <img src="/bimg/${items.itemPic2} "> 
						 <img src="/bimg/${items.itemPic3} ">
							 <c:if test="${not empty items.itemPic4}">
							  <img src="/bimg/${items.itemPic4} ">
							 </c:if>
							 <c:if test="${not empty items.itemPic5}">
							   <img src="/bimg/${items.itemPic5} ">
							 </c:if>
							 
							
							
			
						</li>
						<li><div>商品ID:${items.itemID}</div>
							<div>商品標頭:${items.itemHeader}</div>
							<div>商品價錢:${items.itemPrice}</div> <c:choose>
								<c:when test="${items.itemstatusid == 1}">
									<div>商品狀態:上架</div>
								</c:when>
								<c:otherwise>
									<div>商品狀態:下架</div>
								</c:otherwise>
							</c:choose>
							<div>商品折扣:${items.itemdiscount}</div></li>
						<li>
							<div>
								商品描述:<br> ${items.itemDes }
							</div>
						</li>
					</ul>
				</a>
			</c:forEach>



			<!--下面是分頁-->
			<c:if test="${pageNow > 0}">
				<div class="controlPage">

					<c:if test="${pageNow ==1 }">
						<a href="ItemManagerServlet?itid=${itid}&pageNow=${pageNow }">
							PREV <</a>
					</c:if>

					<c:if test="${pageNow >1 }">
						<a href="ItemManagerServlet?itid=${itid}&pageNow=${pageNow -1}">PREV
							<</a>
					</c:if>



					<c:if test="${totalPage <5}">
						<c:forEach var="x" begin="1" end="${totalPage}">
							<a href="ItemManagerServlet?itid=${itid}&pageNow=${x}">${x}</a>
						</c:forEach>
					</c:if>

					<c:if test="${totalPage > 5}">
						<c:if test="${pageNow > 3}">
							<c:if test="${totalPage - pageNow > 2}">
								<c:forEach var="x" begin="${pageNow-2 }" end="${pageNow+2 }">
									<a href="ItemManagerServlet?itid=${itid}&pageNow=${x}">${x}</a>
								</c:forEach>
							</c:if>

							<c:if test="${totalPage - pageNow < 3}">
								<c:forEach var="x" begin="${totalPage-4 }" end="${totalPage }">
									<a href="ItemManagerServlet?itid=${itid}&pageNow=${x}">${x}</a>
								</c:forEach>
							</c:if>
						</c:if>
						<c:if test="${pageNow < 4}">
							<c:forEach var="x" begin="1" end="5">
								<a href="ItemManagerServlet?itid=${itid}&pageNow=${x}">${x}</a>
							</c:forEach>
						</c:if>
					</c:if>

			
					<c:if test="${pageNow==totalPage}">
						<a href="ItemManagerServlet?itid=${itid}&pageNow=${pageNow }">
							>NEXT</a>
					</c:if>
					<c:if test="${pageNow <totalPage }">
						<a href="ItemManagerServlet?itid=${itid}&pageNow=${pageNow +1}">
							>NEXT</a>
					</c:if>
					<c:if test="${totalPage ==0 }">
						<a href="ItemManagerServlet?itid=${itid}&pageNow=${pageNow}">
							>NEXT</a>
					
			</c:if>
				</div>
			</c:if>
			
			
			
			
		</div>

	</div>

</body>
</html>