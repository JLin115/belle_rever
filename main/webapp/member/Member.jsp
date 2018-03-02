<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="css/aaa.css" />
<script src='js/member.js'></script>
<script type="text/javascript">
$(window).load(function() {
	$('.member_title_img').attr('src',"${initParam['showImgRoute']}member_top.png")
	$('.member_content').css({
		'background-image':'url(1112.png)',
		'-webkit-background-size':'100% 105%'  
	})   
})</script>
</head>
<body>
	<jsp:include page="${initParam['header']}"></jsp:include>
	<jsp:include page="${initParam['member_header']}"></jsp:include>

		<div class="member_content">
		 
		</div>

	<jsp:include page="${initParam['member_footer']}"></jsp:include>
	<script src="/Belle_Rever/home/index/bootstrap/bootstrap.js"></script>
	<jsp:include page="${initParam['footer']}"></jsp:include>
	<!-- 頁首頁尾js -->
	<script src="${initParam['header_footer_js']}"></script>

</body>
</html>