<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="css/aaa.css" />
<title>Shelver</title>

<link rel="stylesheet" href="/Belle_Rever/home/index/bootstrap/animate.css">
<link rel="stylesheet" href="/Belle_Rever/home/index/bootstrap/bootstrap.min.css">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<!--icon    -->
<link href="https://file.myfontastic.com/q7vPAHfyZAb68aTYyhnm4S/icons.css" rel="stylesheet">
<!-- 頁首頁尾 -->
<link href="${initParam['header_footer_css']}" rel="stylesheet">
<link rel="stylesheet" href="css/aaa.css" />
</head>
<body>

<jsp:include page="${initParam['header']}"></jsp:include>

<div  style="margin:10px auto; margin-bottom:10%;width:50%;">
	<ul class="member_navbar">
		<li><a href="MemberModify.jsp">會員資料</a></li>
		<li><a href="ShowOrdList?type=1">訂單查詢</a></li>

	</ul>
	<ul class="ordStatus">
		<c:forEach var="x" items="${ordStat}">
			<li><a href="ShowOrdList?type=${x.key}">${x.value}</a></li>
		</c:forEach>
	</ul>
	<div class="ordlist_show">
		<c:forEach var="x" items="${ordList}">
			<a href="OrdDetail?ordId=${x.ordId}">
				<div class="ordlist_content">

					<div>
						<span>訂單編號</span><span>:${x.ordId}</span>
						
						<c:forEach var="y" items="${ordStat}">
							<c:if test="${y.key == x.osId }">
								<span>訂單狀態</span><span>:${y.value}</span>
							</c:if>
						</c:forEach>
						
						<span>訂單金額</span><span>:${x.ordTotal}</span>
					</div>
					<div>
						<span>訂購日期</span><span>:${x.orderDate}</span> 
						<span>出貨日期</span><span>:${x.shipDate}</span>
					</div>
				</div>
			</a>
		</c:forEach>
	</div>
</div>


<script src="/Belle_Rever/home/index/bootstrap/bootstrap.js"></script>
<jsp:include page="${initParam['footer']}"></jsp:include>

<!-- 頁首頁尾js -->
<script src="${initParam['header_footer_js']}"></script>

</body>
</html>