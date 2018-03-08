<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--     <meta charset="UTF-8"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

<link rel="stylesheet" href="/Belle_Rever/home/index/bootstrap/animate.css">
<link rel="stylesheet" href="/Belle_Rever/home/index/bootstrap/bootstrap.min.css">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>

<!-- 輪播 -->
<link rel="stylesheet" href="/Belle_Rever/home/index/owl.carousel/owl.carousel.min.css"></link>
<link rel="stylesheet" href="/Belle_Rever/home/index/owl.carousel/owl.theme.default.min.css"></link>
<script src="/Belle_Rever/home/index/owl.carousel/owl.carousel.min.js"></script>


<!--icon    -->
<link href="https://file.myfontastic.com/q7vPAHfyZAb68aTYyhnm4S/icons.css" rel="stylesheet">
<!-- 頁首頁尾 -->
<link href="${initParam['header_footer_css']}" rel="stylesheet">

 

<script>
$(window).ready(function (params) {
$('.owl-carousel').owlCarousel({
loop:true,
margin:0,
 /*nav:true,   控制列 */
autoWidth:true,   /* 可自行設定輪播寬度 */
items:4,  /* 一頁出現的張數 */
autoplay:true,  /* 自動輪播 */
autoplayTimeout:1000,  /* 輪播速度 */
autoplayHoverPause:true
});
})

</script>
</head>
<body>
<jsp:include page="${initParam['header']}"></jsp:include>
          <!--輪播-->
<div class="coustom" style=""> 
    <div class="col-md-12 owl-carousel owl-theme "   >
        <div class="item" style=" "><a href="#1"><img   src="${initParam['showImgRoute']}15.jpg"></a></div>
        <div class="item" style=""><a href="#2"><img   src="${initParam['showImgRoute']}16.jpg"></a></div>
        <div class="item" style=" "><a href="#4"><img  src="${initParam['showImgRoute']}18.jpg"></a></div>
        <div class="item" style=""><a href="#5"><img   src="${initParam['showImgRoute']}16.jpg"></a></div>
        <div class="item" style=""><a href="#6"><img    src="${initParam['showImgRoute']}17.jpg"></a></div>
        <div class="item" style=""><a href="#7"><img   src="${initParam['showImgRoute']}18.jpg"></a></div>
     </div>
    </div>
 
    
<script src="/Belle_Rever/home/index/bootstrap/bootstrap.js"></script>
<jsp:include page="${initParam['footer']}"></jsp:include>

<!-- 頁首頁尾js -->
<script src="${initParam['header_footer_js']}"></script>
</body>
</html>