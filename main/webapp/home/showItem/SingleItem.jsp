<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>Insert title here</title>

<link rel="stylesheet"
	href="/Belle_Rever/home/index/bootstrap/animate.css">
<link rel="stylesheet"
	href="/Belle_Rever/home/index/bootstrap/bootstrap.min.css">
<script src='/Belle_Rever/js/jquery-2.2.4.min.js'></script>

<!--icon    -->
<link
	href="https://file.myfontastic.com/q7vPAHfyZAb68aTYyhnm4S/icons.css"
	rel="stylesheet">
<!-- 頁首頁尾 -->
<link href="${initParam['header_footer_css']}" rel="stylesheet">


<link rel="stylesheet" href="css/SingleItem.css">
<script src='js/SingleItem.jsp'></script>
</head>
<body>


	<jsp:include page="${initParam['header']}"></jsp:include>
	<!-- 	<div class="div"></div> -->
	<!-- 	<div class="div2"></div> -->
	<!-- 	<div class="d1"> -->
	<%-- 	 --%>
	<%-- 		<span>編號：</span><input type="text" name='id' value="${ib.itemID }" /><br> --%>
	<!-- 		<input type="hidden" name="serN" value="" /> -->
	<!-- 		<input type="button" name="Purchase" value="購買" />	 -->
	<!-- 		<input type="button" name="Cart" value="加入購物車" />	 -->
	<!-- 	</div> -->




	<!-- 外框 -->
	<div class="singleitem_content">

		<!-- 預覽圖片 -->
		<div class="singleitemimgcontent">
			<!-- 預覽框 -->
			<div class="singleitembig">
				<img id="0" src="" />
			</div>
			<!-- **************************************************************** -->
			<div class="singleitemsmall">
			<c:if test="${not empty ib.itemPic1}">
			<img id="1" class="thumb normal" src="${initParam['itemImgRoute']}${ib.itemPic1}" alt="image1" onmouseover="preview(this)" />
			</c:if>
			<c:if test="${not empty ib.itemPic2}">
			<img id="2" class="thumb normal" src="${initParam['itemImgRoute']}${ib.itemPic2}" alt="image2" onmouseover="preview(this)" />
			</c:if>
			<c:if test="${not empty ib.itemPic3}">
			<img id="3" class="thumb normal" src="${initParam['itemImgRoute']}${ib.itemPic3}" alt="image3" onmouseover="preview(this)" />
			</c:if>
			<c:if test="${not empty ib.itemPic4}">
			<img id="4" class="thumb normal" src="${initParam['itemImgRoute']}${ib.itemPic4}" alt="image3" onmouseover="preview(this)" />
			</c:if>
			<c:if test="${not empty ib.itemPic5}">
			<img id="5" class="thumb normal" src="${initParam['itemImgRoute']}${ib.itemPic5}" alt="image3" onmouseover="preview(this)" />
			</c:if>
			</div>
		</div>


		<script>
			var lastImg = 1; //Set initial thumbnail and preview
			document.getElementById(0).src = document.getElementById(lastImg).src;
			document.getElementById(lastImg).className = "thumb selected";

			function preview(img) {
				document.getElementById(lastImg).className = "thumb normal";
				img.className = "thumb selected";
				document.getElementById(0).src = img.src;
				lastImg = img.id
			}
		</script>

		<div class="singleitembuycontent">
			<div class="singleitemitemdesc">
				<div>${ib.itemHeader}</div>
				<div>
				<fmt:formatNumber var="c" value="${ib.itemPrice *ib.itemdiscount.doubleValue()}" pattern="#"/>
				<c:choose>
							<c:when test="${ib.itemdiscount.doubleValue()  eq 1.00 }">
							NT:${c}
							</c:when>
							<c:when test="${ib.itemdiscount.doubleValue()  > 1.00 }">
							NT:${c}
								</c:when>
							<c:otherwise>
							<span class="onsales">NT:${ib.itemPrice}</span><br>
							NT:${c}
							</c:otherwise>
				</c:choose>
				</div>
				<div>
					<span>Select Color</span><br> 
					<input type="hidden" name="serN" value="" /> 
					<input type="hidden" name="id" value="${ib.itemID}" />
					<span class="div"></span><br> 
					
					<span>Select Size</span><br> 
					
					<span class="div2"></span><br> 
				



				</div>
				<div class="singleitempurchase_button">
					<span class="singleitempurchase_addCart" name="Cart">加入購物車</span> 
					<span class="singleitempurchase_purchase" name="Purchase">確認</span>
				
				</div>
			</div>
			<!-- 評價/簡介 -->
			<div class="singleitemgrade">
				<span id="tab-1"></span> <span id="tab-2"></span> <span id="tab-3"></span>
				<span id="tab-4"></span>

				<div id="tab">
					<!-- 頁籤按鈕 -->
					<ul>
						<li><a href="#tab-1">商品資訊</a></li>
						<li><a href="#tab-2">MODEL資訊</a></li>
						<li><a href="#tab-3">尺寸表</a></li>
						<li><a href="#tab-4">試穿報告</a></li>
					</ul>
					<!-- 頁籤的內容區塊 -->
					<div class="tab-content-1">
						<p>網路獨家 材質:滌綸65% 氨綸35% 產地:中國 彈性:無 內裡:有 透光度:不透 ※
							顏色請參考單品圖片較為接近，但因圖檔顏色會因個人電腦螢幕設定差異略有不同，請以實際商品顏色為準。/p>
					</div>
					<div class="tab-content-2">
						<p>‧Ring 身高172cm／胸圍Bust：32 
							 腰圍Wais：24／臀圍hips：34
						        ‧試穿報告：模特兒平日穿S號，此款版型正常 ‧
							Alina 身高172cm／胸圍Bust：32
							腰圍Waist：23／臀圍hips：33 ‧
							試穿報告：模特兒平日穿S號，此款版型正常</p>
					</div>
					<div class="tab-content-3">
						<p>內容-3</p>
					</div>
					<div class="tab-content-4">
						<p>內容-4</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="${initParam['footer']}"></jsp:include>
	<!-- 頁首頁尾js -->
	<script src="${initParam['header_footer_js']}"></script>
	<script src="/Belle_Rever/home/index/bootstrap/bootstrap.js"></script>
</body>
</html>