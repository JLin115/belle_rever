<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/nav.css" />
<link rel="stylesheet" href="/Belle_Rever/css/styles.css" />
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<script type="text/javascript" src="../nav/js/nav.js"></script>
<title>Shelver</title>
</head>
<body >
	<jsp:include page="../nav/Nav.jsp"></jsp:include>
 
		 
			<div class="maneger_index_show_right" style="height:770px; margin:0 auto;margin-left:10%;width:80%; ">
			<div class="index_img">
			<a href="${pageContext.request.contextPath}/manager/orderManager/OrderManager.jsp"><img alt="" src="${initParam['showImgRoute']}products.png"></a>
			<a href="${pageContext.request.contextPath}/manager/orderManager/OrderManager.jsp"><img alt="" src="${initParam['showImgRoute']}orderlist.png"></a>
			<a href="${pageContext.request.contextPath}/manager/memberManager/ShowAllMemeber?pageNow=1"><img alt="" src="${initParam['showImgRoute']}members.png">  </a>  
			<br>  
			<a href="${pageContext.request.contextPath}/manager/feedBackManager/FeedBackManager.jsp"><img alt="" src="${initParam['showImgRoute']}like.png"></a>
			<a href="${pageContext.request.contextPath}/manager/couponManager/ShowCoupon?pageNow=1"><img alt="" src="${initParam['showImgRoute']}coupon.png"></a>
			<a href=""><img alt="" src="${initParam['showImgRoute']}service.png"></a>
			<a href="${pageContext.request.contextPath}/manager/analysis/analysis.jsp"><img alt="" src="${initParam['showImgRoute']}chart.png"></a>
			</div> 
			</div>
		   

 
</body>
</html>