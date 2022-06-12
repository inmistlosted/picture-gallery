<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/shop/commons/header.jsp"%>
<div class="register_account">
           <div class="wrap">
    	     <h4 class="title">Кошик</h4>
    	     
   <c:if test="${empty sessionScope.cart.items}">
       <p class="cart">Кошик пустий.<br>Нажміть<a href="${pageContext.request.contextPath}/index.jsp"> тут</a> щоб продовжити перегляд</p>
    </c:if>
    <c:if test="${!empty sessionScope.cart.items}">
    	<p class="cart">
        <table border="1" style="width: 100%; background-color: #a35f1b; border: 2px solid darkred; margin-bottom: 80px">
    		<tr>
    			<th style="border-right: 3px solid darkred; padding: 25px; font-weight: bold; color: white; border-bottom: 2px solid darkred">Назва картини</th>
				<th style="border-right: 3px solid darkred; padding: 25px; font-weight: bold; color: white; border-bottom: 2px solid darkred">Художник</th>
				<th style="border-right: 3px solid darkred; padding: 25px; font-weight: bold; color: white; border-bottom: 2px solid darkred">Ціна</th>
				<th style="border-right: 3px solid darkred; padding: 25px; font-weight: bold; color: white; border-bottom: 2px solid darkred">Кількість</th>
				<th style="border-right: 3px solid darkred; padding: 5px; font-weight: bold; color: white; border-bottom: 2px solid darkred">Загальна вартість</th>
				<th style="border-bottom: 2px solid darkred"></th>
    		</tr>
    		<c:forEach items="${sessionScope.cart.items}" var="me" varStatus="vs">
    			<tr style="${vs.index%2==0?'background: white':'background: #e19549'}">
	    			<td style="width: 30%; text-align: center; border-right: 3px solid darkred; padding: 15px; border-bottom: 2px solid darkred">${me.value.painting.name}</td>
	    			<td style="width: 30%; text-align: center; border-right: 3px solid darkred; padding: 15px; border-bottom: 2px solid darkred">${me.value.painting.artist.name}</td>
	    			<td style="width: 8%; text-align: center; border-right: 3px solid darkred; padding: 15px; border-bottom: 2px solid darkred">$${me.value.painting.price}</td>
	    			<td style="width: 8%; text-align: center; border-right: 3px solid darkred; padding: 15px; border-bottom: 2px solid darkred"><input type="text" size="3" id="number" name="number" value="${me.value.number}" onchange="changeNumber(this,'${me.value.number}','${me.key}')"/></td>
	    			<td style="width: 14%; text-align: center; border-right: 3px solid darkred; padding: 15px; border-bottom: 2px solid darkred">$${me.value.price}</td>
	    			<td style="width: 10%; text-align: center; border-bottom: 2px solid darkred">
	    				<a href="javascript:delOneItem('${me.key}')" style="color: red" onmouseover="this.style.color='darkred'" onmouseout="this.style.color='red'">Видалити</a>
	    			</td>
	    		</tr>
    		</c:forEach>
    	</table>
		<p style="display: block; position: relative; width: 30%; left: 35%; text-align: center; text-transform: uppercase; font-size: 30px; margin-bottom: 10px">Чек</p>
		<table style="position: relative; width: 30%; border: 4px solid darkgreen; left: 35%;">
			<tr><td style="text-align: center; border-bottom: 2px solid forestgreen; padding: 10px">Кількість картин: ${sessionScope.cart.number}&nbsp;&nbsp;</td></tr>
			<tr><td style="text-align: center; border-bottom: 2px solid forestgreen; padding: 10px">Ціна: $${sessionScope.cart.price}&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
			<tr><td style="text-align: center; border-bottom: 2px solid forestgreen;"><a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=genOrder" style="display:block; color: white; background-color: forestgreen; width: 100%; height: 100%; padding: 10px 0">Замовити</a></td></tr>

		</table>
    </p>
    </c:if>
             </div>
    	</div>
    <script type="text/javascript">
    	function delOneItem(paintId){
    		var sure = window.confirm("Справді видалити?");
    		if(sure){
    			location.href="${pageContext.request.contextPath}/servlet/ClientServlet?op=delOneItem&paintId="+paintId;
    		}
    	}
    	function changeNumber(inputObj,oldNumber,paintId){
    		var value = inputObj.value;
    		if(!/^[1-9][0-9]*$/.test(value)){
				alert("Будь ласка, введіть номер правильно");
				this.focus();
				return;
    		}
    		var sure = window.confirm("Ви дійсно хочете змінити номер "+value+"?");
    		if(sure){
    			location.href="${pageContext.request.contextPath}/servlet/ClientServlet?op=changeNum&paintId="+paintId+"&num="+value;
    		}else{
    			inputObj.value = oldNumber;
    		}
    	}
    </script>
<%@ include file="/shop/commons/footer.jsp"%>
