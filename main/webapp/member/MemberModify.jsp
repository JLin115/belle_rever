<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
</head>
<body>
<jsp:include page="${initParam['header']}"></jsp:include>
	
	<div style="margin:10px auto; margin-bottom:10%;width:50%;">
	<div>個人資料及密碼修改</div>

	<form method="post" name="form" action="MemberModifyServlet" >
		<div>
			<span>會員帳號:"${LoginOK.mid}"</span>
			<p />
			<span>會員姓名:</span><input type="text" name="name"
				value="${LoginOK.mname}">${errorMsg.nameError}<br> <span>生日:</span><input
				type="text" name="bd" value="${LoginOK.mbday}">${errorMsg.bderror}<br>
			<span>電話:</span><input type="text" name="phone"
				value="${LoginOK.mphone}">${errorMsg.phoneError}<br> <span>信箱:</span><input
				type="text" name="email" value="${LoginOK.memail}">${errorMsg.emailError}<br>
			<input type="submit" value="確認送出">
		</div>
	</form>

	<form method="post" name="form" action="PasswordModifyServlet">
		<div>
			<span>修改密碼:</span><input type="password" name="pas">${errorMsg.paswordError}<br>
			<span>密碼確認:</span><input type="password" name="pasc">${errorMsg.paswordError2}<br>
			<br> <input type="submit" value="確認送出">
		</div>
	</form>
	</div>

	<jsp:include page="${initParam['footer']}"></jsp:include>
	
	
	<script src="/Belle_Rever/home/index/bootstrap/bootstrap.js"></script>
	<!-- 頁首頁尾js -->
	<script src="${initParam['header_footer_js']}"></script>

</body>
</html>