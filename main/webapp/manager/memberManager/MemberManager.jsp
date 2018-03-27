<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<link rel="stylesheet" href="css/MemberManager.css" />
<link rel="stylesheet" href="/Belle_Rever/manager/nav/css/nav.css" />
<link rel="stylesheet" href="/Belle_Rever/css/styles.css" />
<script src='../nav/js/nav.js'></script>
<title>MemberManager</title>   

</head>
<body>
	<%@include file="../nav/Nav.jsp"%>

	<div class="maneger_index_show">
		<div class="maneger_index_show_title_img"><img src="${initParam['showImgRoute'] }member_manage_top.png"/></div>
		<div class="maneger_index_show_right" style="margin-left: 6%;width:88%; border:2px solid #d8d0d0;">
			<div class="show">  
<!-- 				<div class="serch"> -->
<!-- 					<input type="text"><input type="submit" value="送出"> -->
<!-- 				</div> -->
				<table> 
					<tr>
						<td>帳號</td>
						<td>性名</td>
						<td>生日</td>
						<td>信箱</td>
						<td>電話</td>
						<td>註冊日</td>
						<td>權限</td>
					</tr>

					<c:forEach var="m" items="${members}">
				
						<tr>
							
							<td><a href="ShowAllMemeber?account=${m.mid}"><span>${m.mid}</span></a></td>
							<td><span>${m.mname}</span></td>
							<td><span>${m.mbday}</span></td> 
							<td><span>${m.memail}</span></td>
							<td><span>${m.mphone}</span></td>
							<td><span><fmt:formatDate value="${m.mregisterday}" pattern="yyyy-MM-dd" /></span></td>
							<td><span> <c:choose>
										<c:when test="${m.mpid == 1}">正常</c:when>
										<c:otherwise>停權</c:otherwise>
									</c:choose>
							</span></td>
							
						</tr>
						
					</c:forEach>

				</table>
				<!-- --------------------------------------------------- -->
				<c:if test="${pageNow > 0}">
					<input type="hidden" class="hidden_pageNow" value="${pageNow}">
					<div class="controlPage">
						<c:if test="${pageNow ==1 }">
							<a href="ShowAllMemeber?pageNow=${pageNow }"> PREV <</a>
						</c:if>

						<c:if test="${pageNow >1 }">
							<a href="ShowAllMemeber?pageNow=${pageNow -1}">PREV <</a>
						</c:if>



						<c:if test="${totalPage <5}">
							<c:forEach var="x" begin="1" end="${totalPage}">
								<a href="ShowAllMemeber?pageNow=${x}">${x}</a>
							</c:forEach>
						</c:if>

						<c:if test="${totalPage > 5}">
							<c:if test="${pageNow > 3}">
								<c:if test="${totalPage - pageNow > 2}">
									<c:forEach var="x" begin="${pageNow-2 }" end="${pageNow+2 }">
										<a href="ShowAllMemeber?pageNow=${x}">${x}</a>
									</c:forEach>
								</c:if>

								<c:if test="${totalPage - pageNow < 3}">
									<c:forEach var="x" begin="${totalPage-4 }" end="${totalPage }">
										<a href="ShowAllMemeber?pageNow=${x}">${x}</a>
									</c:forEach>
								</c:if>
							</c:if>
							<c:if test="${pageNow < 4}">
								<c:forEach var="x" begin="1" end="5">
									<a href="ShowAllMemeber?pageNow=${x}">${x}</a>
								</c:forEach>
							</c:if>
						</c:if>


						<c:if test="${pageNow==totalPage}">
							<a href="ShowAllMemeber?pageNow=${pageNow }"> >NEXT</a>
						</c:if>
						<c:if test="${pageNow <totalPage }">
							<a href="ShowAllMemeber?pageNow=${pageNow +1}"> >NEXT</a>
						</c:if>
						<c:if test="${totalPage ==0 }">
							<a href="ShowAllMemeber?pageNow=${pageNow}"> >NEXT</a>

						</c:if>
					</div>
				</c:if>


				<!-- --------------------------------------------------- -->

			</div>

		</div>
	</div>


</body>
</html>