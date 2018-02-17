<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="js/comment.js" charset="utf-8"></script>
<link rel="stylesheet" href="css/Comment.css">
</head>
<body>
	<form method="post" action="InsertCommemt" enctype="multipart/form-data">
		<div>
			<span>${param.title}</span><br>
			<input type="hidden" name="itemId" value="${param.itemId}">
			<input type="hidden" name="ordId" value="${param.ordId}">
			<input type="hidden" name="ordSern" value="${param.ordSern}">
			<textarea class="textarea" name="commemt" maxlength="50"></textarea>
			<span class="commemtError"></span><br>
			<label class="button" for="file">選擇圖片</label>
		
			 <input type="submit" class="submit" value="送出">
		</div>
		<div>
			<input type="file" id="file" name="file" accept=" image/jpeg, image/png"> 
			<img class="img" src="">
			<span class="picError"></span>
		</div>
		<br>

	</form>
</body>
</html>