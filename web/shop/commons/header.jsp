<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ir.javahosting.i18n.msg" />
<!DOCTYPE HTML>
<html lang="${language}">
<head>
<title>Картинна галерея</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon"  href="${pageContext.request.contextPath}/res/shop_assets/images/gallery-icon.png" sizes="16x16" type="image/png">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/res/shop_assets/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/res/shop_assets/css/form.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/res/shop_assets/js/jquery1.min.js"></script>

<link href="${pageContext.request.contextPath}/res/shop_assets/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath}/res/shop_assets/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>

<script src="${pageContext.request.contextPath}/res/shop_assets/js/jquery.easydropdown.js"></script>
</head>
<body>
     <div class="header-top" style="background: #cc7722">
	   <div class="wrap"> 
			  <div class="header-top-left" style="height: 40px">
                  <c:if test="${!empty sessionScope.customer}">
                      <ul style="margin-top: 10px">
                          <li style="color: white">Ласкаво просимо, <span style="font-weight: bold; color: #141A1B">${sessionScope.customer.username}</span></li>
                      </ul>
                  </c:if>
   			 </div>
			 <div class="cssmenu">
				<ul>
                    <c:if test="${empty sessionScope.customer}">
					    <li><a href="${pageContext.request.contextPath}/shop/login.jsp">Вхід</a></li> |
					    <li><a href="${pageContext.request.contextPath}/shop/register.jsp">Реєстрація</a></li> |
                        <li><a href="${pageContext.request.contextPath}/manage/index.jsp">Адміністратор</a></li>
                    </c:if>
                    <c:if test="${!empty sessionScope.customer}">
                        <li><a href="${pageContext.request.contextPath}/manage">Адміністратор</a></li> |
                        <li><a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=logout">Вихід</a></li>
                    </c:if>
				</ul>
			</div>
			<div class="clear"></div>
 		</div>
	</div>
	<div class="header-bottom">
	    <div class="wrap">
			<div class="header-bottom-left">
				<div class="logo">
					<a href="${pageContext.request.contextPath}/index.jsp">
						<img src="${pageContext.request.contextPath}/res/shop_assets/images/gallerylogo.jpg" style="height: 41px; width: 100px" alt=""/>
					</a>
				</div>
				<div class="menu">
	            <ul class="megamenu skyblue">
					<li class="active grid"><a href="${pageContext.request.contextPath}/index.jsp">Головна</a></li>
					<li><a class="color4" href="#">Категорії</a>
						<div class="megapanel">
							<div class="row">
								<div class="col1">
									<div class="h_nav">
										<h4>Список категорій</h4>
										<ul>
										<c:forEach items="${cs}" var="c">
											<li><a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=listPaintingByCategory&categoryId=${c.id}">${c.name}</a></li>
    									</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li><a class="color4" href="#">Художники</a>
						<div class="megapanel">
							<div class="row">
								<div class="col1">
									<div class="h_nav">
										<h4>Список художників</h4>
										<ul>
											<c:forEach items="${as}" var="c">
												<li><a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=listPaintingByArtist&artistId=${c.id}">${c.name}</a></li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li><a class="color4" href="#">Напрями</a>
						<div class="megapanel">
							<div class="row">
								<div class="col1">
									<div class="h_nav">
										<h4>Список напрямів</h4>
										<ul>
											<c:forEach items="${ws}" var="c">
												<li><a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=listPaintingByWay&wayId=${c.id}">${c.name}</a></li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</li>
					<li><a class="color4" href="#">Сортування</a>
						<div class="megapanel">
							<div class="row">
								<div class="col1">
									<div class="h_nav">
										<h4>Сотування за ціною</h4>
										<ul>
											<li><a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=listPaintingByPrice&sortType=1">Спочатку дешевші</a></li>
											<li><a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=listPaintingByPrice&sortType=2">Спочатку дорожчі</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</li>
			</ul>
			</div>
		</div>
	   <div class="header-bottom-right">
         <div class="search">	  
				<input type="text" id="search_name" name="s" class="textbox" value="Пошук" onfocus="this.value = '';" onblur="if (this.value === '') {this.value = 'Пошук';}">
				<input type="submit" value="Subscribe" id="submit" name="submit" onclick="findByName()">
				<div id="response"> </div>
		 </div>
	  <div class="tag-list">
		<ul class="icon1 sub-icon1 profile_img">
			<li><a class="active-icon c2" href="${pageContext.request.contextPath}/shop/cart.jsp"> </a>
			</li>
		</ul>
	    <ul class="last"><li><a href="${pageContext.request.contextPath}/shop/cart.jsp">Кошик</a></li></ul>
	  </div>
    </div>
     <div class="clear"></div>
     </div>
	</div>
<script>
	function findByName(){
		var name = document.getElementById("search_name").value;
		if (name === "" || name === "Пошук"){
		    alert("Введіть назву картини");
        }else {
            window.location.href = '${pageContext.request.contextPath}/servlet/ClientServlet?op=listPaintingByName&name=' + name;
        }
	}
</script>
</body>