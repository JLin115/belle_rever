<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
	<%
	request.setCharacterEncoding("Big5");
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Belle_Rever/home/index/bootstrap/animate.css">
<link rel="stylesheet" href="/Belle_Rever/home/index/bootstrap/bootstrap.min.css">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>


<!--icon    -->
<link href="https://file.myfontastic.com/q7vPAHfyZAb68aTYyhnm4S/icons.css" rel="stylesheet">
<!-- 頁首頁尾 -->
<link href="${initParam['header_footer_css']}" rel="stylesheet">


<script type="text/javascript" src="js/aaa.jsp"></script>
<link rel="stylesheet" href="css/new.css" />
</head>
<body>
<jsp:include page="${initParam['header']}"></jsp:include>

	<div style="border:1px solid transparent;  width:70% ; height:700px; margin : 0 auto;">
        <div class="processphoto">
            <img src="	/Belle_Rever/home/home_img/process.png" style="width:100%;">
        </div>
        <div class="sType">
            <form action="Purchase" name="form" method="get">
                <div class="type1">
                    <span class="shipping" style="margin-right:12.2%; ">宅配貨到付款</span>
                    <span>全館消費滿 NT.699元可享免運。</span>
                    <span style="color:red;">(「貨到付款」服務僅限台灣本島)</span>
                </div>

                <div class="type2">
                    <span class=  "convient"style="margin-right:15%; ">超商取貨</span>
                    <span>全館消費滿 NT.699元可享免運。</span>
                </div>

                <input type="hidden" name="stype" value="${param.webtemp}">
                <div class="inputType">
                    <!-- ${errorMsg.typeError} -->
                    <c:if test="${ not empty param.webtemp }">
                        <div class="type3">
                            <span>超商名稱:</span>
                            <input type="text" name="st_name" value="${param.st_name}" readonly>
                        </div>
                        <div class="type4">
                            <span>超商地址:</span>
                            <input type="text" name="st_addr" value="${param.st_addr}" style="width:50%; margin-bottom:1%" readonly>
                        </div>
                    </c:if>
                </div>
				折價券:<div><input type="text" name="coupon" style=" width: 15%; background:transparent;"> </div><br>
                <!-- ${errorMsg.couponError} -->
            </form>
        </div>
       
        <hr style="border:solid 0.5px; color:#d6d1d1; height:0.05%; margin-bottom:0.5%">
       
        <div class="ordData">
          <div class="checkorder"> <div ><i class="icon-check-circle"></i> CHECK YOUR ORDER 確認購買明細</div></div>
            <table style="margin-top:3%;" class="table_FillOrdInfo">
                <tr>
                    <td class="pname">商品名稱</td>
                    <td>顏色</td>
                    <td>尺寸</td>
                    <td>數量</td>
                    <td>單價</td>
                    <td>折扣</td>
                    <td>小計</td>
                </tr>
                <c:forEach items="${Cart}" var="x">
                    <tr>
                        <td class="pname">
                            <img src="${initParam['itemImgRoute']}${x.itemPic1}" alt="">
                            <span>${x.itemHeader}</span>
                        </td>
                        <td>${x.itemColor}</td>
                        <td>${x.itemSize}</td>
                        <td>${x.ordQty}</td>
                        <td>${x.itemPrice}</td>
                        <td>${x.itemPrice * x.itemDiscount}</td>
                        <td class="singlePrice">${x.itemPrice * x.itemDiscount * x.ordQty}</td>
                    </tr>

                </c:forEach>
            </table>
        </div>

        <input type="submit" value="確認送出" style="margin-top:3%; font-family: 微軟正黑體; font-weight:bold; border:none; margin-left:47%; ">
    </div>
	<!-- 
	<div class="sType">
		<form action="Purchase" name="form" method="get">
			<div class="type1">超商取貨</div>
			<div class="type2">宅配</div>
			
			<input type="hidden" name="stype" value="${param.webtemp}">
			
		
			<div class="inputType">${errorMsg.typeError}
				<c:if test="${ not empty param.webtemp }">
					<span>超商名稱:</span>
					<input type="text" name="st_name" value="${param.st_name}" readonly>
					<br>
					<span>超商地址:</span>
					<input type="text" name="st_addr" value="${param.st_addr}" readonly>
				</c:if>
			</div>
			
			折價券:<div><input type="text" name="coupon" ></div>${errorMsg.couponError}<br>
			<input type="submit" value="送出">
		</form>
	</div>




	<div class="ordData">
		<table>
			<tr>
				<td>商品名稱</td>
				<td>顏色</td>
				<td>尺寸</td>
				<td>數量</td>
				<td>單價</td>
				<td>折扣</td>
				<td>小計</td>
			</tr>
			<c:forEach items="${Cart}" var="x">
				<tr>
					<td><img src="${initParam['itemImgRoute']}${x.itemPic1}" alt=""><span>${x.itemHeader}</span></td>
					<td>${x.itemColor}</td>
					<td>${x.itemSize}</td>
					<td>${x.ordQty}</td>
					<td>${x.itemPrice}</td>
					<td>${x.itemPrice * x.itemDiscount}</td>
					<td class="singlePrice">${x.itemPrice * x.itemDiscount * x.ordQty}</td>
				</tr>

			</c:forEach>
		</table>
	</div>
	
	-->
	
	
	
	
<jsp:include page="${initParam['footer']}"></jsp:include>
<script src="/Belle_Rever/home/index/bootstrap/bootstrap.js"></script>
<!-- 頁首頁尾js -->
<script src="${initParam['header_footer_js']}"></script>
</body>
</html>