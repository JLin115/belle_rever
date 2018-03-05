<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Belle_Rever</title>
<link rel="stylesheet"
	href="/Belle_Rever/home/index/bootstrap/animate.css">
<link rel="stylesheet"
	href="/Belle_Rever/home/index/bootstrap/bootstrap.min.css">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<link href="https://file.myfontastic.com/q7vPAHfyZAb68aTYyhnm4S/icons.css"
	rel="stylesheet">
 	
<!-- 頁首頁尾 -->
<link href="${initParam['header_footer_css']}" rel="stylesheet">
<link href="/Belle_Rever/home/showItem/css/ShowItem.css" rel="stylesheet">
<script type="text/javascript" src="/Belle_Rever/js/searchJs/SearchResult.js"></script>
</head>
<script type="text/javascript">
var x = ${res}
</script>
<body>
	<jsp:include page="${initParam['header']}"></jsp:include>
	
	<div class="ShowItem_show"> 
		<input type="hidden" value="${res}" class="res"> 
		<input type="hidden" value="${totalPage}" class="totalPage"> 
		<input type="hidden" value="${pageNow}" class="pageNow">
		<input type="hidden" value="${val}" class="val">


		<ul class="toShow">
				 
		</ul>
		<div class="controlPage">
		
		
		</div>



	</div>
	<script src="/Belle_Rever/home/index/bootstrap/bootstrap.js"></script>
	<jsp:include page="${initParam['footer']}"></jsp:include>

	<!-- 頁首頁尾js -->
	<script src="${initParam['header_footer_js']}"></script>
</body>
</html>