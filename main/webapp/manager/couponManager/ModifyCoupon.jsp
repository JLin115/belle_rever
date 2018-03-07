<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	$(window).ready(function () {
		$('.modifycp').on('submit',function(){ 
	 
			 $.ajax({
					'type':'GET',
					'url':'/Belle_Rever/manager/couponManager/ModifyCP',
					'cache': false,
					'data':{
						"cpId":$('.cpId').val(),
						"cpDes":$('.cpDes').val(),
						"cpVal":$('.cpVal').val(),
						"cpQty":$('.cpQty').val(),
						"valid":$('.valid').val(),
						"invalid":$('.invalid').val(),
						"mId":$('.mId').val()
					},
					'headers':{"X-Requested-With": "XmlHttpRequest"}, 
					success:function(data){
						location.href="/Belle_Rever/manager/couponManager/ShowCoupon?pageNow=1";
						 
					},error:function(data){
						alert(data)
						 
					}
					})
			
			
		})
	})
</script>
</head>
<body>
	<jsp:include page="../nav/Nav.jsp"></jsp:include>
	<div class="maneger_index_show">
		<div class="maneger_index_show_title_img"><img src="#"></div>

		<div class="maneger_index_show_right"
			style="width: 70%; margin-left: 15%; border: 1px solid black;">

			<form  class="modifycp">
				<span>折價券編號</span>：<input type="text" name="cpId" value="${cpb.cpId}"><span class="error">${errorMsg.cpId}</span><br> 
				<span>描述</span>：<input type="text" name="cpDes" value="${cpb.cpDes}"><span class="error">${errorMsg.cpDes}</span><br> 
				<span>折價券價值</span>：<input type="text" name="cpVal" value="${cpb.cpVal}"><span class="error">${errorMsg.cpVal}</span><br> 
				<span>折價券數量</span>：<input type="text" name="cpQty" value="${cpb.cpQty}"><span class="error">${errorMsg.cpQty}</span><br> 
				<span>有效日期</span>：<input type="text" name="valid" value="<fmt:formatDate value="${cpb.valid}" pattern="yyyy-MM-dd"/>"><span class="error">${errorMsg.valid}</span><br> 
				<span>失效日期</span>：<input type="text" name="invalid" value="<fmt:formatDate value="${cpb.invalid}" pattern="yyyy-MM-dd"/>"><span class="error">${errorMsg.invalid}</span><br>
				<span>會員ID</span>：<input type="text" name="mId" value="${cpb.mId}"><span class="error">${errorMsg.mId}</span><br> 
				 <input type="submit" value="送出" class="buttion_c">  
			</form>

		</div>
	</div>

</body>
</html>