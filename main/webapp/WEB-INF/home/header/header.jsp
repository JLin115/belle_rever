<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="newlo">    </div>
<!-- <div style="background-image:url('img/17.jpg'); height:100% ; width:100%; position: absolute; z-index: 2;"> </div> -->
<div class="innerlo">
<!-- LoginServlet -->
	<div class="log_content" >
		<form  class="loginForm" method="post">
		<div class="log_content_title">會員登入<br>Sing In</div>
		<div>
			<input type="text" name="userId" placeholder="會員帳號"><br>
			<span class="errorText"></span>	
		
		</div>
		<div><input type="password" name="pswd" placeholder="會員密碼 "></div>
		<div class="forget_sign"><span class="forget">忘記密碼</span> <input type="button" class="signin" value="Sign In"></div>
		</form>
	</div>
<!-- 	<div style="background-image:url('img/17.jpg'); height:100% ; width:100%; position: absolute; z-index: 30;"> </div> -->
	<div class="reg_content">
<!-- RegisterServlet -->
	<form class="Register" enctype="application/x-www-form-urlencoded" style="font-family: '微軟正黑體';">
		<div class="log_content_title">會員註冊<br>Join</div>
		<div><input type="text" name="account"   placeholder="會員帳號 Account"></div>
		<div><input type="password" name="pas"   placeholder="會員密碼 Password">	</div>
		<div><input type="password" name="pasc"  	placeholder="確認密碼 Check Password"></div>	
		<div><input type="text" name="name"  placeholder="姓名 Name"></div>
		<div><input type="text" name="bd"   placeholder="生日 Birthday "></div>
		<div><input type="text" name="email"   placeholder="信箱 E-mail"></div>
		<div><input type="text" name="phone"   placeholder="手機 Telephone"></div>
		<div class="JoinB"><input type="button" value="Join"  >	</div>
		</form>
	</div>
	
</div>



<!-- cart -->
<div class="cart">

	<table class="cartT">
		<tr>
		    <td></td>
			<td>商品名稱</td>
			<td>顏色</td>
			<td>尺寸</td>
			<td>數量</td>
			<td>小計</td>
		</tr>
		<c:forEach items="${Cart}" var="x">
			<tr>
				<td><img src="${initParam['itemImgRoute']}${x.itemPic1}" alt=""></td>
				<td><span>${x.itemHeader}</span></td>
				<td>${x.itemColor}</td>
				<td>${x.itemSize}</td>
				<td>${x.ordQty}</td>
				<td class="singlePrice" name="singlePrice">${x.itemPrice * x.itemDiscount * x.ordQty}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="cartButton">Check Cart</div>	
</div>

<!--title-->
<nav class="navbar navbar-default bgcolor EN_font_family">
	<div class="container-fluid header_fix">
		<!-- Brand and toggle get grouped for better mobile display 
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Brand</a>
          </div>-->
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse coustom_navbar0"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
			
				<c:if test="${empty LoginOK  }">
				<li><a id="loginStatus"class="loginB" style="padding: 0; margin: 0; line-height: 27px; color: white;cursor: pointer;">Login</a></li>
				</c:if>
				
				<c:if test="${not empty LoginOK  }">
				<li><a id="loginStatus" class="logout" style="padding: 0; margin: 0; line-height: 27px; color: white;cursor: pointer;" href="#">Logout</a></li>
				</c:if>
				
				<li><span style="padding: 0; margin-left: 20px; margin-right: 20px; line-height: 27px; color: white;">/</span></li>
				<li><a class="memberB" style="padding: 0; margin: 0; line-height: 27px; color: white;cursor: pointer;">Member</a></li>
				<li><span style="padding: 0; margin-left: 20px; margin-right: 20px; line-height: 27px; color: white;">/</span></li>
				<li><a class="cartB" style="padding: 0; margin: 0; margin-right: 20%; line-height: 27px; color: white;cursor: pointer;">Cart</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
<!--頁首-->
<div class="page-header page_header EN_font_family">
	<span ><a class="header_title" href="<c:url value="${initParam['index']}" />">Belle_Rêver</a></span>
</div>
<!--頁首導覽-->
<nav class="navbar navbar-default coustom_navbar EN_font_family"
	style="padding: 0; margin: 0;">
	<div class="container-fluid ">
		<!-- Collect the nav links, forms, and other content for toggling -->
		<center>
			<div class="collapse navbar-collapse coustom_navbar_div"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav nav_ul coustom_navbar_js coustom_navbar_li">
					<li><a class="header_NewIn" style="padding: 0; margin: 0; line-height: 30px;">NEW IN</a></li>
					<li><a class="header_Clothes" style="padding: 0; margin: 0; line-height: 30px;">Clothes</a><div class="header_clothes_inner CH_font_family" style="width:400px">
<!-- 						<ul style="padding: 0;"> -->
<%-- 						<c:forEach var="x" items="${itemType}"> --%>
<%-- 							<li  ><a href="  <c:url  value="/home/showItem/ShowItem?itid=${x.key }&pageNow=1" />  ">${x.value}</a></li> --%>
<%-- 						</c:forEach> --%>
						
						<div>
							<div style="font-size: 24px;font-weight: bolder; padding: 10px 0;">洋裝</div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=1&pageNow=1" />">無袖洋裝</a></div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=2&pageNow=1" />">七分袖洋裝</a></div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=3&pageNow=1" />">長袖洋裝</a></div>
						</div>
						
						<div>
							<div style="font-size: 24px ;font-weight: bolder;padding: 10px 0;">上衣</div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=4&pageNow=1" />">內著</a></div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=5&pageNow=1" />">上衣</a></div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=6&pageNow=1" />">襯衫</a></div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=7&pageNow=1" />">針織/毛衣</a></div>
						</div>
						

						<div>
							<div style="font-size: 24px ;font-weight: bolder;padding: 10px 0;">下著</div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=8&pageNow=1" />">裙類</a></div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=9&pageNow=1" />">褲類</a></div>
						</div>
						

						<div>
							<div style="font-size: 24px; font-weight: bolder;padding: 10px 0;">外套</div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=10&pageNow=1" />">外套/罩衫</a></div>
						</div> 
						
						</div>
<!-- 						</ul> -->
					</li>
					<li><a class="header_Shoes" style="padding: 0; margin: 0; line-height: 30px;">Shoes</a><div class="header_Shoes_inner" style="width:">
						<div>
							<div style="font-size: 24px ;font-weight: bolder;padding: 10px 0;">女鞋</div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=11&pageNow=1" />">長靴</a></div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=12&pageNow=1" />">中筒靴</a></div>
							<div><a href="<c:url  value="/home/showItem/ShowItem?itid=13&pageNow=1" />">短靴</a></div>
						</div>
					
					</div></li>
					<li><a class="header_Accessories" style="padding: 0; margin: 0; line-height: 30px;">Accessories</a></li>
					<li><a class="header_Bags" style="padding: 0; margin: 0; line-height: 30px;">Bags</a></li>
					<li><a class="header_Sale" style="padding: 0; margin: 0; line-height: 30px; color: red;">Sale</a></li>
					<li><div style="border-bottom: 1px solid black; display: inline-block; padding-bottom: 0px; line-height: 30px; margin-left: 110%;">
					<span class="icon-search"></span><input type="text" class="coustom_text" placeholder="Search"></div></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</center>
	</div>
	<!-- /.container-fluid -->
</nav>