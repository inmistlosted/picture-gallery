<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/shop/commons/header.jsp"%>
<div class="main">
	<div class="wrap">
		<div class="section group">
		  <div class="cont span_2_of_3">
		  	<h2 class="head">Картини в галереї</h2>
			<div class="top-box">
			 <c:forEach items="${page.records}" var="b">
				 <div class="col_1_of_3 span_1_of_3" onmouseover="this.style.background = '#cc7722'" onmouseout="this.style.background = 'white'">
			   <a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=buyPainting&paintId=${b.id}">
				<div class="inner_content clearfix">
					<div class="product_image">
						<img src="${pageContext.request.contextPath}/res/shop_assets/images/painting/${b.filename}" alt=""/>
					</div>
                    <div class="price">
					   <div class="cart-left">
							<p class="title">${b.name}</p>
							<p class="description" style="margin: 10px 0; color: #1B6AAA">${b.way.name}</p>
						   <p class="description">${b.artist.name}</p>
							<div class="price1">
							  <span class="actual">$${b.price}</span>
							</div>
						</div>
						<div class="cart-right"> </div>
						<div class="clear"></div>
					 </div>
                   </div>
                 </a>
				</div>
			   </c:forEach>


				<div class="clear"></div>
                                <%@ include file="/shop/commons/page.jsp"%>
			</div>

		  </div>
			<div class="rsidebar span_1_of_left">
				<div class="top-border" style="border-color: #cc7722"> </div>
				 <div class="border">

                                     <img src="${pageContext.request.contextPath}/res/shop_assets/images/wall1.jpg" style="width: 230px;height: 250px"  alt="" />
              <div class="btn"><a href="${pageContext.request.contextPath}/shop/timetable.jsp" style="background: #cc7722; width: 60%; left: 10%" onmouseover="this.style.background = '#e1770d'" onmouseout="this.style.background = '#cc7722'">Графік роботи</a></div>
             </div>
	    </div>
	   <div class="clear"></div>
	</div>
	</div>
    <style>
        .checkout{
            background: #cc7722;
        }

        .checkout:hover{
            background: #e1770d
        }
    </style>
	</div>
<%@ include file="/shop/commons/footer.jsp"%>
