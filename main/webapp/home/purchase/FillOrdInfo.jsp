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
<script type="text/javascript"src="js/aaa.jsp"></script>
<link rel="stylesheet" href="css/aaa.css" />
</head>
<body>
    <div>
		<form action="" name="form" method="get">
        
         
         
         
            
        <div class="type1">超商取貨</div><div class="type2">宅配</div>
        <div class="inputType">
        <c:if test="${param.st_cate!=TOK }">
			<span>超商名稱:</span><input type="text" name="st_name" value="${param.st_name}"><br>
			<span>超商地址:</span><input type="text" name="st_addr" value="${param.st_addr}">
		</c:if></div>
		
		
		</form>
	</div>


</body>
</html>