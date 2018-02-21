<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
	<style>
@import url('https://fonts.googleapis.com/css?family=Shadows+Into+Light');
</style>
<script type="text/javascript"src="js/aaa.js"></script>
<link rel="stylesheet" href="/Belle_Rever/manager/nav/css/nav.css" />
<link rel="stylesheet" href="/Belle_Rever/manager/nav/css/styles.css"/>

<title>Shelver</title>
</head>
<body>

<div class="nav">
<a class="navA" href="#">Belle_Rever網站管理</a>
<span class="loginSpan" ><span class="icon-login"></span>

</span>
</div>
	<ul class="navbar">
		<li><a href="${pageContext.request.contextPath}/manager/itemManager/ItemManager.jsp">商品管理</a></li>
		<li><a href="${pageContext.request.contextPath}/manager/orderManager/OrderManager.jsp">訂單管理</a></li>
		<li><a href="${pageContext.request.contextPath}/manager/memberManager/ShowAllMemeber?pageNow=1">會員管理</a></li>
	</ul>
</body>
</html>