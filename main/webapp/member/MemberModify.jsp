<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div>個人資料及密碼修改</div><p/>
            <form method="post" name="form" action="MemberModifyServlet">
		<div>
			<span>會員帳號:"${LoginOK.mid}"</span><p/>
			<span>會員姓名:</span><input type="text" name="name" value="${LoginOK.mname}">${errorMsg.nameError}<br>
			<span>生日:</span><input type="text" name="bd" value="${LoginOK.mbday}">${errorMsg.bderror}<br>
			<span>電話:</span><input type="text" name="phone" value="${LoginOK.mphone}">${errorMsg.phoneError}<br> 
			<span>信箱:</span><input type="text"name="email" value="${LoginOK.memail}">${errorMsg.emailError}<br>
			<input type="submit" value="確認送出" >
		</div>
    </form>
    
    <form method="post" name="form" action="PasswordModifyServlet">
		<div>	
			<span>修改密碼:</span><input type="password" name="pas">${errorMsg.paswordError}<br>
			<span>密碼確認:</span><input type="password" name="pasc">${errorMsg.paswordError2}<br>
			<br>
			<input type="submit" value="確認送出" >
		</div>
	</form>
	
	<div>
			<a href="../logout.jsp">登出</a>
		</div>
		<p />
</body>
</html>