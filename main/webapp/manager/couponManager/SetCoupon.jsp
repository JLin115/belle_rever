<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Belle_Rever_Manager</title>

<link rel="stylesheet" href="/Belle_Rever/manager/nav/css/nav.css" />
<link rel="stylesheet" href="css/ShowCoupon.css" />
<link rel="stylesheet" href="/Belle_Rever/css/styles.css" /> 
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<script type="text/javascript" src="../nav/js/nav.js"></script>
<script type="text/javascript" src="js/ShowCoupon.js"></script>
</head>
<body>
	<jsp:include page="../nav/Nav.jsp"></jsp:include>
			<div class="maneger_index_show">
			<div class="maneger_index_show_title_img"><img src="${initParam['showImgRoute']}coupon-manage.png"></div> 
			<div class="maneger_index_show_right" style="width:70% ; margin-left: 15%;">
			
			<form  class="modifycp">
			<input type="hidden" class="oldId" value="${cpb.cpId}">
			<input type="hidden" class="status" value="set">
				<span>折價券編號</span>：<input type="text" class="cpId" value="${cpb.cpId}"><span class="cpIdError error"></span><br> 
				<span>描述</span>：<input type="text" class="cpDes" value="${cpb.cpDes}"><span class="cpDesError error"></span><br> 
				<span>折價券價值</span>：<input type="text" class="cpVal" value="${cpb.cpVal}"><span class="cpValError error"></span><br> 
				<span>折價券數量</span>：<input type="text" class="cpQty" value="${cpb.cpQty}"><span class="cpQtyError error"></span><br> 
				<span>有效日期</span>：<input type="text" class="valid" value="<fmt:formatDate value="${cpb.valid}" pattern="yyyy-MM-dd"/>"><span class="validError error"></span><br> 
				<span>失效日期</span>：<input type="text" class="invalid" value="<fmt:formatDate value="${cpb.invalid}" pattern="yyyy-MM-dd"/>"><span class="invalidError error"></span><br>
				<span>會員ID</span>：<input type="text" class="mId" value="${cpb.mId}"><span class="mIdError error"></span><br> 
				 <input type="button" value="送出" class="buttion_c">  
			</form> 
			</div>
		</div>
	
</body>
</html>