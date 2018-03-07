<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Belle_Rever_Manager</title>

<link rel="stylesheet" href="/Belle_Rever/manager/nav/css/nav.css" />
<link rel="stylesheet" href="/Belle_Rever/css/styles.css" />
<link rel="stylesheet" href="css/ShowCoupon.css" />
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<script type="text/javascript" src="../nav/js/nav.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<jsp:include page="../nav/Nav.jsp"></jsp:include>
	<div class="maneger_index_show">
		<div class="maneger_index_show_title_img"><img src="#"></div>

		<div class="maneger_index_show_right"
			style="width: 70%; margin-left: 15%; border: 1px solid black;">

			<form action="/Belle_Rever/manager/couponManager/getSingleCP"
				method="post" class="modifycp">
				<span>折價券編號</span>：<input type="text" name="cpId" 
					value="${cpb.cpId}"><br> <span>描述</span>：<input
					type="text" name="cpDes" value="${cpb.cpDes}"><br> <span>折價券價值</span>：<input
					type="text" name="cpVal" value="${cpb.cpVal}"><br> <span>折價券數量</span>：<input
					type="text" name="cpQty" value="${cpb.cpQty}"><br> <span>有效日期</span>：<input
					type="text" name="valid" value="${cpb.valid}"><br> <span>失效日期</span>：<input
					type="text" name="invalid" value="${cpb.invalid}"><br>
				<span>會員ID</span>：<input type="text" name="mId" value="${cpb.mId}"><br> 
				 <input type="button" value="送出" class="buttion_c"> 
				 
			</form>

		</div>
	</div>

</body>
</html>