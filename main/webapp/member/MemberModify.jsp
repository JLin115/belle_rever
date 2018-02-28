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
<link rel="stylesheet" href="css/aaa.css" />
<script type="text/javascript">
$(window).ready(function() {
	$('.member_title_img').attr('src',"${initParam['showImgRoute']}member_modify.png")
})

</script>
</head>
<body>
		<jsp:include page="${initParam['header']}"></jsp:include>
		<jsp:include page="${initParam['member_header']}"></jsp:include>
	
	
	<div class="member_content" >
	<div style=" padding:20px ;"></div>
	
	<div class="MemberModify_content_left">
	<form method="post" name="form" action="MemberModifyServlet" >
		<div>
			<span>會員帳號:</span><span>${LoginOK.mid}</span><br>
			<span>會員姓名:</span><input type="text" name="name" value="${LoginOK.mname}"><span class="errorMsg">${errorMsg.nameError}</span><br> 
			<span>生日:</span><input type="text" name="bd" value="${LoginOK.mbday}"><span class="errorMsg">${errorMsg.bderror}</span><br>
			<span>電話:</span><input type="text" name="phone" value="${LoginOK.mphone}"><span class="errorMsg">${errorMsg.phoneError}</span><br> 
			<span>信箱:</span><input type="text" name="email" value="${LoginOK.memail}"><span class="errorMsg">${errorMsg.emailError}</span><br><br>
			<input type="submit" value="確認送出">
		</div>
	</form>
	</div>

	<div class="MemberModify_content_right">
	<form method="post" name="form" action="PasswordModifyServlet">
		<div>
			<span>修改密碼:</span><input type="password" name="pas"><span class="errorMsg">${errorMsg.paswordError}</span><br>
			<span>密碼確認:</span><input type="password" name="pasc"><span class="errorMsg">${errorMsg.paswordError2}</span><br>
			<br> <input type="submit" value="確認送出">
		</div>
	</form>
	</div>
	
	</div>


	<jsp:include page="${initParam['member_footer']}"></jsp:include>
	<jsp:include page="${initParam['footer']}"></jsp:include>
	<script src="/Belle_Rever/home/index/bootstrap/bootstrap.js"></script>
	<!-- 頁首頁尾js -->
	<script src="${initParam['header_footer_js']}"></script>

</body>
</html>