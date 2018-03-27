<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<link rel="stylesheet" href="/Belle_Rever/manager/nav/css/nav.css" />
<link rel="stylesheet" href="/Belle_Rever/css/styles.css" />
<script type="text/javascript"src="../nav/js/nav.js"></script>
<title>register</title>
<style type="text/css">
 
.ModifyMember_form div{
width:29%;
margin:50px auto; 
border:1px solid black;
 padding: 3% 0% 3% 3%;
}	
.ModifyMember_form span{
display:inline-block;
width:80px;
}
.ModifyMember_form span,input{
margin-top:10px;
}
.ModifyMember_form input[type="submit"]{
margin-left:200px;
margin-bottom:10px;
}
.error{
color:red;
font-size: 10px;
margin-top:10px;
}
</style>
</head>
<body>
<%@include file="../nav/Nav.jsp"%>
<div class="maneger_index_show">
		<div class="maneger_index_show_title_img"><img src="${initParam['showImgRoute'] }member_manage_top.png"/></div>
		<div class="maneger_index_show_right" style="margin-left: 6%;width:88% ;">
	<form class="ModifyMember_form" method="post" name="form" action="modifyMember" enctype="multipart/form-data">
		<div style="border:2px solid #d8d0d0;">
			<span>帳號:</span><input type="text" name="account" value="${mb.mid}"  readonly><br>
			<span>姓名:</span><input type="text" name="name" value="${mb.mname} "><span class="error">${errorMsg.nameError}</span><br>
			<span>生日:</span><input type="text" name="bd" value="${mb.mbday }"><span class="error"> ${errorMsg.bderror}</span><br>
			<span>電話:</span><input type="text" name="phone" value="${mb.mphone} "><span class="error">${errorMsg.phoneError}</span><br> 
			<span>信箱:</span><input type="text"name="email" value="${mb.memail} "><span class="error">${errorMsg.emailError}</span><br>
			<span>註冊日期:</span><input type="text"name="mregisterday" value="<fmt:formatDate value="${mb.mregisterday}" pattern="yyyy-MM-dd" />" readonly><br>
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
</div>
</div>

</body>
</html>