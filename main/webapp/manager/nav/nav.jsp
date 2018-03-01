<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="manager_nav">
	<div class="nav">
		<a class="manager_navA" href="#">Belle_Rever網站管理</a> 
		<span class="manager_logoutSpan"><span class="icon-login"></span></span>
	</div>
	<div class="manager_navList_div">
		<ul class="manager_navList">
			<li><a href="${pageContext.request.contextPath}/manager/itemManager/ItemManager.jsp">商品管理</a></li>
			<li><a href="${pageContext.request.contextPath}/manager/orderManager/OrderManager.jsp">訂單管理</a></li>
			<li><a href="${pageContext.request.contextPath}/manager/memberManager/ShowAllMemeber?pageNow=1">會員管理</a></li>
			<li><a href="#">評論管理</a></li>
			<li><a href="#">折價券管理</a></li>
			<li class="manager_navList_li"><a href="#">銷售分析</a></li>
 
		</ul>
	</div>
</div>