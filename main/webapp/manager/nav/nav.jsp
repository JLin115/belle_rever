<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="manager_nav">
	<div class="nav">
		<a class="manager_navA" href="#">Belle_Rever網站管理</a> 
		<span class="manager_logoutSpan"><span class="icon-login"></span></span>
	</div>
	<div class="manager_navList_div">
		<ul class="manager_navList">
			<li><a class="itemManager_a">商品管理</a><div class="itemManager_a_bar"><div><a href="${pageContext.request.contextPath}/manager/itemManager/Shelver.jsp">上架</a></div>
			<div><a href="${pageContext.request.contextPath}/manager/itemManager/ItemManager.jsp">管理</a></div>
			</div>
			</li>
			<li><a href="${pageContext.request.contextPath}/manager/orderManager/OrderManager.jsp">訂單管理</a></li>
			<li><a href="${pageContext.request.contextPath}/manager/memberManager/ShowAllMemeber?pageNow=1">會員管理</a></li>
			<li><a href="${pageContext.request.contextPath}/manager/feedBackManager/FeedBackManager.jsp">評論管理</a></li>
			<li><a class="itemManager_a" >折價券管理</a><div class="itemManager_a_bar">
			<div><a href="">新建折價券</a></div> 
			<div><a href="${pageContext.request.contextPath}/manager/couponManager/ShowCoupon?pageNow=1">查看折價券</a></div>
			</div></li>
			
			
			<li class="manager_navList_li">銷售分析</li>
		</ul>
	</div>
</div>
<%-- href="${pageContext.request.contextPath}/manager/itemManager/ItemManager.jsp" --%>