<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="nav">
<a class="navA" href="#">Belle_Rever網站管理</a>
<span class="logoutSpan" ><span class="icon-login"></span></span>
</div>
<ul class="navbar">
	<li><a href="${pageContext.request.contextPath}/manager/itemManager/ItemManager.jsp">商品管理</a></li>
	<li><a href="${pageContext.request.contextPath}/manager/orderManager/OrderManager.jsp">訂單管理</a></li>
	<li><a href="${pageContext.request.contextPath}/manager/memberManager/ShowAllMemeber?pageNow=1">會員管理</a></li>
</ul>
