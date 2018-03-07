<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Belle_Rever_Manager</title>

<link rel="stylesheet" href="/Belle_Rever/manager/nav/css/nav.css" />
<link rel="stylesheet" href="/Belle_Rever/css/styles.css" />
<link rel="stylesheet" href="css/ShowCoupon.css" />
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<script type="text/javascript" src="../nav/js/nav.js"></script>
<script type="text/javascript" src="js/ShowCoupon.js"></script>
<script type="text/javascript">
	var res =${cplist}   
</script>
</head>
<body>
	<input type="hidden" class="pageNow" value="${pageNow}">
	<input type="hidden" class="totalPage" value="${totalPage}">
	<jsp:include page="../nav/Nav.jsp"></jsp:include>
	<div class="maneger_index_show">
		<div class="maneger_index_show_title_img">
			<img src="">
		</div>
		<div class="maneger_index_show_right"
			style="width: 80%; margin-left: 10%;"> 
			<div class="searchForcp">
				<input type="text"> 
			</div>
			<div class="cpList"> 
				<table>
					<thead>
						<tr>
							<th>折價券編號</th>
							<th>描述</th>
							<th>價值</th>
							<th>數量</th>
							<th>開始日</th>
							<th>結束日</th>
							<th>刪除</th>
						</tr>
					</thead>
					<tbody class="insertContent">
						<tr>
							<td><a href="#">65464</a></td>
							<td>2sdsf</td>
							<td>3000</td>
							<td>1992/02/18</td>
							<td>1992/02/18</td>
							<td>6</td>
							<td></td>
						</tr>
					</tbody>
				</table>
				<div class="controlPage"></div>
			</div>
		</div>
	</div>

</body>
</html>