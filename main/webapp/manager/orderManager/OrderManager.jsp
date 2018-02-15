<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="js/aaa.js"></script>
<link rel="stylesheet" href="css/orderManager.css" />
<title>Shelver</title>
</head>
<body>
	<ul class="navbar">
		<li><a href="#">商品管理</a></li>
		<li><a href="OrdList?type=1">訂單管理</a></li>
		<li><a href="#">會員管理</a></li>
	</ul>
	<div class="content">
	<ul class="ordStatus">
		<c:forEach var="x" items="${ordStat}">
		<li><a href="OrdList?type=${x.key}">${x.value}</a></li>
		</c:forEach>
	</ul>

	
	

		<table>
			<tr>
				<td><span>訂單編號</span></td>
				<td><span>訂單人</span></td>
				<td><span>訂單金額</span></td>
				<td><span>訂單日期</span></td>
			</tr>


			<c:forEach var="x" items="${allOrder}">
				
			<tr>
				<td><span><a href="ModifyOrd?id=${x.ordId}"> ${x.ordId}</a></span></td>
				<td><span>${x.mId}</span></td>
				<td><span>${x.ordTotal}</span></td>
				<td><span>${x.orderDate}</span></td>
			</tr>
			
			</c:forEach>






		</table>

	</div>
</body>
</html>