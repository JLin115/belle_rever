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
<link rel="stylesheet" href="css/analysis.css" />
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>
<script type="text/javascript" src="../nav/js/nav.js"></script>
<script type="text/javascript" src="/Belle_Rever/js/canvasjs.min.js"></script>
<script type="text/javascript" src="js/analysis.js"></script>
</head>
<body>
	<jsp:include page="../nav/Nav.jsp"></jsp:include>
	<div class="maneger_index_show">
		<div class="maneger_index_show_title_img">
			<img src="${initParam['showImgRoute']}analysis.png">
		</div>
		<div class="maneger_index_show_left">
		<ul class="choose"><li><select class="Yopt">
		<option>Choose</option>
		</select></li></ul>
		<ul class="anaUl">
		</ul> 
		</div> 
		<div class="maneger_index_show_right"> 
			<a href="/Belle_Rever/manager/analysis/getMonthAna"></a>
			<div id="chartContainer" style="height: 500px; width: 80%; margin-top: 1%;margin-left: 10%;"></div>
		</div>
	</div>

</body>
</html>