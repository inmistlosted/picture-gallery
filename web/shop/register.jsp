<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/shop/commons/header.jsp"%>
<c:if test="${!empty sessionScope.customer}">
	<h2 style="text-align: center; padding-top: 20px; font-size: 25px">Доброго дня, <span style="color: #cc7722; font-weight: bold">${sessionScope.customer.username}</span> ви вже увійшли в систему</h2>
	<p style="text-align: center; padding-bottom: 20px; font-size: 20px">Нажміть<a href="${pageContext.request.contextPath}/index.jsp"> тут</a> щоб продовжити перегляд</p>
</c:if>
<c:if test="${empty sessionScope.customer}">
    <h3 style="position: relative; display: block; width: 30%; left: 35%; text-align: center; font-size: 30px; margin-bottom: 15px">Реєстрація</h3>
    <form action="${pageContext.request.contextPath}/servlet/ClientServlet?op=customerRegist" method="post">
    	<table style="position: relative; width: 40%; left: 30%; border: 2px solid black; margin-bottom: 30px">
    		<tr style="border-bottom: 1px solid black">
    			<td style="padding: 30px; font-size: 20px; text-align: center">Ім'я користувача:</td>
    			<td>
    				<input name="username" style="border: 1px solid black; padding: 10px 0 10px 10px; width: 90%; margin-bottom: 10px"/>
    			</td>
    		</tr>
    		<tr style="border-bottom: 1px solid black">
    			<td style="padding: 30px; font-size: 20px; text-align: center">Пароль:</td>
    			<td>
    				<input type="password" name="password" style="border: 1px solid black; padding: 10px 0 10px 10px; width: 90%; margin-bottom: 10px"/>
    			</td>
    		</tr>
    		<tr style="border-bottom: 1px solid black">
    			<td style="padding: 30px; font-size: 20px; text-align: center">Телефон:</td>
    			<td>
    				<input name="phone" style="border: 1px solid black; padding: 10px 0 10px 10px; width: 90%; margin-bottom: 10px"/>
    			</td>
    		</tr>
    		<tr style="border-bottom: 1px solid black">
    			<td style="padding: 30px; font-size: 20px; text-align: center">Адреса:</td>
    			<td><input name="address" style="border: 1px solid black; padding: 10px 0 10px 10px; width: 90%; margin-bottom: 10px"/></td>
    		</tr>
    		<tr style="border-bottom: 1px solid black">
    			<td style="padding: 30px; font-size: 20px; text-align: center">Електронна адреса:</td>
    			<td>
    				<input name="email" style="border: 1px solid black; padding: 10px 0 10px 10px; width: 90%; margin-bottom: 10px"/>
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="submit" value="Зареєструватись" style="width: 100%; padding: 10px 0; background: #a35f1b; color: white; font-size: 15px; border: none; cursor: pointer"
						   onmouseover="this.style.background= '#cc7722'" onmouseout="this.style.background='#a35f1b'"/>
    			</td>
    		</tr>
    	</table>
    </form>
</c:if>
<%@ include file="/shop/commons/footer.jsp"%>
