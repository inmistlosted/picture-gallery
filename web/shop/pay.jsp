<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Онлайн оплата</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  <h2 style="position: relative; display: block; width: 30%; left: 35%; text-align: center; font-size: 30px; margin:150px 0 15px">Чек</h2>
    <form action="#" method="post">
		<table style="position: relative; width: 40%; left: 30%; border: 2px solid black; margin-bottom: 30px">
			<tr style="border-bottom: 1px solid black">
				<td style="padding: 30px; font-size: 20px; text-align: right; border-bottom: 1px solid black">Номер замовлення:</td>
				<td style="border-bottom: 1px solid black">
					<INPUT TYPE="text" NAME="ordernum" value="${order.ordernum}${param.ordernum}"  readonly="readonly" style="border: 1px solid black; padding: 10px 0 10px 10px; width: 90%; margin-bottom: 10px">
				</td>
			</tr>
			<tr style="border-bottom: 1px solid black">
				<td style="padding: 30px; font-size: 20px; text-align: right; border-bottom: 1px solid black">Сума замовлення:</td>
				<td style="border-bottom: 1px solid black">
					<INPUT TYPE="text" NAME="money" size="6" value="$${order.price }"  readonly="readonly" style="border: 1px solid black; padding: 10px 0 10px 10px; width: 90%; margin-bottom: 10px"></td>
				</td>
			</tr>
			<tr>
				<td style="padding: 30px; font-size: 20px; text-align: right; border-bottom: 1px solid black">Виберіть потрібний банк:</td>
				<td style="border-bottom: 1px solid black">
					<select style="border: 1px solid black; padding: 10px 0 10px 10px; width: 90%; margin-bottom: 10px">
						<option>Privat Bank</option>
						<option>Monobank</option>
						<option>Bank Aval</option>
						<option>Alpha Bank</option>
					</select>
			</tr>
			<tr>
				<td colspan="2"><INPUT TYPE="submit" value="Сплатити" style="width: 100%; padding: 10px 0; background: black; color: white; font-size: 15px; border: none; cursor: pointer"
						   onmouseover="this.style.background= 'grey'" onmouseout="this.style.background='black'">
				</td>
			</tr>
		</table>
	</form>
  </body>
</html>