<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="/Belle_Rever/home/index/bootstrap/animate.css">
<link rel="stylesheet"
	href="/Belle_Rever/home/index/bootstrap/bootstrap.min.css">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<!--icon    -->
<link
	href="https://file.myfontastic.com/q7vPAHfyZAb68aTYyhnm4S/icons.css"
	rel="stylesheet">
<!-- 頁首頁尾 -->
<link href="${initParam['header_footer_css']}" rel="stylesheet">
</head>
<body>
	<jsp:include page="${initParam['header']}"></jsp:include>

	<div style="width: 70%; height: 700px; margin: 0 auto; margin-bottom: 7%; text-align: center; font-family: 微軟正黑體;">

		<hr>     
		<span>THANK YOU!</span>
		<hr> 
		<div>感謝您的訂購，查看訂單詳情請至訂單查詢頁面</div>
		<div style="text-align: center;margin-top:1%;">
			<a href="/Belle_Rever/member/OrdList.jsp" style="padding: 6px; background-color: rgb(193, 191, 191); color: black;   font-weight: bold;">訂單詳情</a>
		</div>  
	</div>

	<jsp:include page="${initParam['footer']}"></jsp:include>
	<script src="/Belle_Rever/home/index/bootstrap/bootstrap.js"></script>
	<!-- 頁首頁尾js -->
	<script src="${initParam['header_footer_js']}"></script>
</body>
</html>