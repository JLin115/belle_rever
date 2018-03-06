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
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<script type="text/javascript" src="../nav/js/nav.js"></script>
<script type="text/javascript" src="js/FeedBackManager.js"></script>
<link rel="stylesheet" href="css/FeedBackManager.css" />
<script type="text/javascript">
	var res = ${res}
</script>
</head>
<body>
	<jsp:include page="../nav/Nav.jsp"></jsp:include>

	<div class="maneger_index_show">
		<div class="maneger_index_show_title_img">
			<img src="${initParam['showImgRoute'] }comment-manage.png" />
		</div>
		<div class="maneger_index_show_left">
			<ul class="sidebar">
				<c:forEach var="x" items="${itemType}">
					<li><a
						href="<c:url  value="/manager/feedBackManager/manager_feebBack_search?itid=${x.key }&pageNow=1" />  ">${x.value}</a></li>
				</c:forEach>
			</ul>
		</div>

		<div class="maneger_index_show_right">
			<input type="hidden" class="res" value="${res}"> <input
				type="hidden" class="pageNow" value="${pageNow}"> <input
				type="hidden" class="totalPage" value="${totalPage}"> <input
				type="hidden" class="itid" value="${itid}">
			<div class="show_content_feedBack">


				<table>
					<thead>
						<tr>
							<th>評論圖片</th>
							<th>商品ID</th>
							<th>會員帳號</th>
							<th>評論內容</th>
							<th>刪除</th>
						</tr>
					</thead>
					<tbody class="insertContent">
						  
						
					</tbody> 
				</table>

			</div>

		</div>
	</div>

</body>
</html>