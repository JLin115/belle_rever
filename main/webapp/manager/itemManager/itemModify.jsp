<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="itemtype" class="manager.itemManager.model.ItemDAOImpl"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>
<script type="text/javascript"src="js/itemModify.js"></script>
<link rel="stylesheet" href="css/itemModify.css" />
<title>Shelver</title>
</head>
<body>
	<form enctype="multipart/form-data" name="form"method="POST" action="ItemModify" >
		<div class="content">					
			<div class="d1">
				<span>編號：</span><input type="text" name='id' value="${ib.itemID }" /><p>${errorMsg.idError}</p><br> 
				<span>價格：</span><input type="text" name='price' value="${ib.itemPrice }"/><p>${errorMsg.priceError}</p><br> 
				<span>折扣：</span><input type="text" name='discount' value="${ib.itemdiscount }" /><p>${errorMsg.discountError}</p><br>  
				<span>標頭：</span><input type="text" name='title' value="${ib.itemHeader}"/><p>${errorMsg.titleError}</p><br> 
				<span>描述：</span><textarea class="des" name='des' maxlength='100'>${ib.itemDes }</textarea><p class="areap">${errorMsg.desError}</p><br> 
				<span>類型：</span><select name = "type" value="${ib.itId }">
					<option value="0">請選擇類別</option>
					
					<c:forEach var="x" items="${itemtype.allItemType}">
					<option value="${x.key}">${x.value}</option>
					</c:forEach>
				</select><p>${errorMsg.typeError}</p><br> 
				<span>狀態:</span><select name="status">
					<option value="1">上架</option>
					<option value="0">下架</option>
					</select><br> 
				<span>主照片：</span><input type="file" name='pic1' /><p>${errorMsg.pic1Error }</p><br>
				<span>照片：</span><input type="file" name='pic2' /><p>${errorMsg.pic2Error }</p><br> 
				<span>照片：</span><input type="file" name='pic3' /><p>${errorMsg.pic3Error }</p><br> 
				<span>照片：</span><input type="file" name='pic4' /><br>${errorMsg.pic4Error }</p><br> 
				<span>照片：</span><input type="file" name='pic5' /><br>${errorMsg.pic5Error }</p><br> 
			</div>
			<div class="d2">
				<div class="auto">
					
				<c:forEach var ="x" items="${ivbList}" varStatus="s">
				<div id="auto${x.serialNumber}">
					<span>流水號：${x.serialNumber}</span><br>
					<span> 顏色：</span><input type="text" name='color0' value="${x.color }" /><br> 
					<span>尺寸：</span><input type="text" name='size0' value="${x.size }"/><br> 
					<span> 庫存：</span><input type="text" name='stock0' value="${x.stock }"/>
				
				</div>
					<c:if test="${s.last}">
					 <input type="hidden" name="identify" id="identify" value="${x.serialNumber}" />
					</c:if>
				
				</c:forEach>
					
					
				</div>
			</div>
			<div class="b1">
				<input id="add" type="button" value="++" /><br>
				<input id="dec" type="button" value="--" /><br> 
					<input id="submit" type="submit" value="submit" />
			</div>
		
			<div class="b2">${errorMsg.ColorSizeStockError}</div>
		</div>
	</form>

</body>
</html>