<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/itemManager.css" />
<link rel="stylesheet" href="/Belle_Rever/manager/nav/css/nav.css" />
<link rel="stylesheet" href="/Belle_Rever/css/styles.css" />
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<script type="text/javascript" src="../nav/js/nav.js"></script>
<title>Shelver</title>
</head>
<body>
	<%-- 	<%@include file="../nav/nav.jsp" %> --%>
	<jsp:include page="../nav/Nav.jsp"></jsp:include>


	<div class="maneger_index_show">

		<div class="maneger_index_show_title_img"></div>
		<div class="maneger_index_show_left">

			<ul class="sidebar">
				<li><form>
						<input type="text"><input type="submit">
					</form></li>
				<c:forEach var="x" items="${itemType}">
					<li><a href="ItemManagerServlet?itid=${x.key }&pageNow=1">${x.value}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="maneger_index_show_right">

			<table class="itemManger_show_table">
				<thead>
					<tr>
						<th>商品ID</th>
						<th>商品圖片</th>
						<th>商品標頭</th>
						<th>商品價錢</th>
						<th>商品狀態</th>
						<th>商品折扣</th>
						<th>商品描述</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>123456789</td>
						<td></td>
						<td>花猴聯名雕花蕾絲不規則傘襬長裙</td>
						<td>1000</td>
						<td>上架</td>
						<td>0.75</td>
						<td>材質:滌綸100% 產地:中國 彈性:無 內裡:有 透光度:不透 商品描述:拉鍊設計/不規則裙襬</td>

					</tr>

					<c:forEach var="items" items="${allItem}">
						
						<tr>
							<td> <a href="ShowSingleItem?itemId=${items.itemID}" /> ${items.itemID}</td>
							<td> <img src="${initParam['itemImgRoute']}${items.itemPic1}">  </td>
							<td> ${items.itemHeader} </td>
							<td> ${items.itemPrice} </td>
							<td>	<c:choose> 
										<c:when test="${items.itemstatusid == 1}">
											<span>上架</span>
										</c:when>
										<c:otherwise>
											<span>下架</span>
										</c:otherwise>
									</c:choose>
							</td>
							<td> ${items.itemdiscount} </td>
							<td> ${items.itemDes} </td>
						</tr>
					</c:forEach>
					
					
				</tbody>
			</table>
			
			
			
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











	<!-- 	<div class="content"> -->
	<!-- 		<ul class="sidebar"> -->
	<!-- 			<li><form> -->
	<!-- 					<input type="text"><input type="submit"> -->
	<!-- 				</form></li> -->

	<%-- 			<c:forEach var="x" items="${itemType}"> --%>
	<%-- 				<li><a href="ItemManagerServlet?itid=${x.key }&pageNow=1">${x.value}</a></li> --%>
	<%-- 			</c:forEach> --%>


	<!-- 		</ul> -->
	<!-- 		<div class="show"> -->

	<%-- 			<c:forEach var="items" items="${allItem}"> --%>

	<%-- 				<a href="ShowSingleItem?itemId=${items.itemID}"> --%>
	<!-- 					<ul> -->
	<%-- 						<li><img src="${initParam['itemImgRoute']}${items.itemPic1}"> --%>
	<%-- 						 <img src="${initParam['itemImgRoute']}${items.itemPic2} ">  --%>
	<%-- 						 <img src="${initParam['itemImgRoute']}${items.itemPic3} "> --%>
	<%-- 							 <c:if test="${not empty items.itemPic4}"> --%>
	<%-- 							  <img src="${initParam['itemImgRoute']}${items.itemPic4} "> --%>
	<%-- 							 </c:if> --%>
	<%-- 							 <c:if test="${not empty items.itemPic5}"> --%>
	<%-- 							   <img src="${initParam['itemImgRoute']}${items.itemPic5} "> --%>
	<%-- 							 </c:if> --%>
	<!-- 						</li> -->
	<%-- 						<li><div>商品ID:${items.itemID}</div> --%>
	<%-- 							<div>商品標頭:${items.itemHeader}</div> --%>
	<%-- 							<div>商品價錢:${items.itemPrice}</div> --%>
<%-- 										<c:choose>  --%>
<%-- 									<c:when test="${items.itemstatusid == 1}"> --%>
<!-- 										<div>商品狀態:上架</div> -->
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<!-- 										<div>商品狀態:下架</div> -->
<%-- 									</c:otherwise> --%>
<%-- 									</c:choose> --%>
	<%-- 							<div>商品折扣:${items.itemdiscount}</div></li> --%>
	<!-- 						<li> -->
	<!-- 							<div> -->
	<%-- 								商品描述:<br> ${items.itemDes } --%>
	<!-- 							</div> -->
	<!-- 						</li> -->
	<!-- 					</ul> -->
	<!-- 				</a> -->
	<%-- 			</c:forEach> --%>



	<!-- 			<!--下面是分頁-->
 
				



	<!-- 		</div> -->

	<!-- 	</div> -->

</body>
</html>