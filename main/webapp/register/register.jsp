<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
width:100px;
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
	<form method="post" action="/register/registerServlet">
		<div>
			<span>帳號:</span><input type="text" name="account"><br>
			<span>密碼:</span><input type="password" name="pas"><br>
			<span>密碼確認:</span><input type="text" name="pasc"><br>
			<span>姓名:</span><input type="text" name="name"><br>
			<span>生日:</span><input type="text" name="bd"><br>
			<span>電話:</span><input type="text" name="phone"><br> 
			<span>信箱:</span><input type="text"name="email"><br>
			<input type="submit" value="submit" >
		</div>
	</form>



</body>
</html>