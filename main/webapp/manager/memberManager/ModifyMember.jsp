<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
<style type="text/css">
form div{
width:400px;
margin:50px auto;
border:1px solid black;
paddding:10px;
}	
span{
display:inline-block;
width:80px;
}
form span,input{
margin-top:10px;
}
input[type="submit"]{
margin-left:200px;
margin-bottom:10px;
}


</style>
</head>
<body>
	<form method="post" name="form" action="RegisterServlet">
		<div>
			<span>帳號:</span><input type="text" name="account" value="${mb.mid}"  readonly>${errorMsg.accountError}<br>
			<span>姓名:</span><input type="text" name="name" value="${mb.mname} ">${errorMsg.nameError}<br>
			<span>生日:</span><input type="text" name="bd" value="${mb.mbday }">${errorMsg.bderror}<br>
			<span>電話:</span><input type="text" name="phone" value="${mb.mphone} ">${errorMsg.phoneError}<br> 
			<span>信箱:</span><input type="text"name="email" value="${mb.memail} ">${errorMsg.emailError}<br>
			<span>註冊日期:</span><input type="text"name="email" value="${mb.mregisterday}">
			<span>權限:</span><select name="status">
								<c:if test="${mb.mpid ==1 }">
								<option value="1">正常</option>
								<option value="0">停權</option>
								</c:if>	
								<c:if test="${mb.mpid ==0 }">
								<option value="0">停權</option>
								<option value="1">正常</option>
								</c:if>
							
							</select>
			<input type="submit" value="submit" >
		</div>
	</form>



</body>
</html>