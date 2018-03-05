<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member_ord</title>
<link rel="stylesheet"
	href="/Belle_Rever/home/index/bootstrap/animate.css">
	<link rel="stylesheet" href="css/aaa.css" />
<link rel="stylesheet"
	href="/Belle_Rever/home/index/bootstrap/bootstrap.min.css">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<!--icon    -->  
<link href="https://file.myfontastic.com/q7vPAHfyZAb68aTYyhnm4S/icons.css"
	rel="stylesheet">
<!-- 頁首頁尾 -->
<link href="${initParam['header_footer_css']}" rel="stylesheet">

<script type="text/javascript">
	$(window).ready(function (){
	 
				var pageNow = $('.hidden_pageNow').val();
				var controllBar_a = $('.controlPage a');
				$('.member_title_img').attr('src', "${initParam['showImgRoute']}ord_top.png")
				$('.controlPage a').each(function() {
					if ($(this).text().trim() == pageNow) {
						$(this).css({
							'text-decoration' : 'underline'
						})
					}
				})
				  
				var osid = $('.osidH').val();
				$('.ordStatus li').each(function () {
				console.log(this) 
				if($(this).attr('name') == osid){ 
					$(this).css({
						'text-decoration' : 'underline'
					})
				} 
			})  	 
})
</script>

</head>
<body>
	<input type="hidden" value="${osId}" class="osidH">
	<jsp:include page="${initParam['header']}"></jsp:include>
	<jsp:include page="${initParam['member_header']}"></jsp:include>


	<div class="member_content" style="overflow: auto;">

		<ul class="ordStatus">
			<c:forEach var="x" items="${ordStat}">
				<li name="${x.key}"><a href="ShowOrdList?type=${x.key}&pageNow=1">${x.value}</a></li>
			</c:forEach>
		</ul>
		<div class="ordlist_show">
			<c:forEach var="x" items="${ordList}">
				<a href="OrdDetail?ordId=${x.ordId}" style=" text-decoration: none; ">
					<div class="ordlist_content">
						<div>
							<span style="color:red; font-weight:bolder;">訂單編號</span><span style="color:red; font-weight:bolder;">：${x.ordId}</span>

							<c:forEach var="y" items="${ordStat}">
								<c:if test="${y.key == x.osId }">
									<span>訂單狀態</span><span>：${y.value}</span>
								</c:if>
							</c:forEach>
							<span>訂單金額</span><span>：${x.ordTotal}</span>
						</div>
						<div>
							<span>訂購日期</span><span>：${x.orderDate}</span>
							
							 <span>出貨日期</span><span>：	
							 
								<c:if test="${x.shipDate eq '1990-01-01 00:00:00.0'}"></c:if>
								<c:if test="${x.shipDate ne '1990-01-01 00:00:00.0'}">
								<fmt:formatDate value="${x.orderDate}" pattern="yyyy-MM-dd" /> 
								</c:if>
								
							</span>


						</div>
					</div>
				</a>
			</c:forEach>
		</div>

		<c:if test="${pageNow > 0}">
			<input type="hidden" class="hidden_pageNow" value="${pageNow}">
			<div class="controlPage">
				<c:if test="${pageNow ==1 }">
					<a class="controlPage_a_color"href="ShowOrdList?type=${osId}&pageNow=${pageNow }"> PREV <</a>
				</c:if>

				<c:if test="${pageNow >1 }">
					<a class="controlPage_a_color" href="ShowOrdList?type=${osId}&pageNow=${pageNow -1}">PREV <</a>
				</c:if>



				<c:if test="${totalPage <5}">
					<c:forEach var="x" begin="1" end="${totalPage}">
						<a class="controlPage_a_color" href="ShowOrdList?type=${osId}&pageNow=${x}">${x}</a>
					</c:forEach>
				</c:if>
				<c:if test="${totalPage > 5}">
					<c:if test="${pageNow > 3}">
						<c:if test="${totalPage - pageNow > 2}">
							<c:forEach var="x" begin="${pageNow-2 }" end="${pageNow+2 }">
								<a class="controlPage_a_color" href="ShowOrdList?type=${osId}&pageNow=${x}">${x}</a>
							</c:forEach>
						</c:if>

						<c:if test="${totalPage - pageNow < 3}">
							<c:forEach var="x" begin="${totalPage-4 }" end="${totalPage }">
								<a class="controlPage_a_color" href="ShowOrdList?type=${osId}&pageNow=${x}">${x}</a>
							</c:forEach>
						</c:if>
					</c:if>
					<c:if test="${pageNow < 4}">
						<c:forEach var="x" begin="1" end="5">
							<a class="controlPage_a_color" href="ShowOrdList?type=${osId}&pageNow=${x}">${x}</a>
						</c:forEach>
					</c:if>
				</c:if>



				<c:if test="${pageNow==totalPage}">
					<a class="controlPage_a_color" href="ShowOrdList?type=${osId}&pageNow=${pageNow }"> >NEXT</a>
				</c:if>
				<c:if test="${pageNow <totalPage }">
					<a class="controlPage_a_color" href="ShowOrdList?type=${osId}&pageNow=${pageNow +1}"> >NEXT</a>
				</c:if>
				<c:if test="${totalPage ==0 }">
					<a class="controlPage_a_color" href="ShowOrdList?type=${osId}&pageNow=${pageNow}"> >NEXT</a>
				</c:if>
			</div>
		</c:if>
	</div>


	<jsp:include page="${initParam['member_footer']}"></jsp:include>
	<script src="/Belle_Rever/home/index/bootstrap/bootstrap.js"></script>
	<jsp:include page="${initParam['footer']}"></jsp:include>

	<!-- 頁首頁尾js -->
	<script src="${initParam['header_footer_js']}"></script>

</body>
</html>