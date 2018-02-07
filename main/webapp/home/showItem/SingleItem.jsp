<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Expires" content="0"> 
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
	<script type="text/javascript"src="js/ShowItem.jsp"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="div"></div>
	<div class="div2"></div>
	
	
	<form name="form"method="GET" action="">
	<div class="d1">
	${ib.itemHeader }
		<span>編號：</span><input type="text" name='id' value="${ib.itemID }" /><br>
		<input type="hidden" name="serN" value="" />
		<input type="button" name="Purchase" value="購買" />	
		<input type="button" name="Cart" value="加入購物車" />	
	
	</div>
	</form>

</body>
</html>