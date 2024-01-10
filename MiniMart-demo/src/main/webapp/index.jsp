<%@page import="com.jdc.shop.model.ShoppingCart"%>
<%@page import="com.jdc.shop.model.ProductModel"%>
<%@page import="com.jdc.shop.model.entity.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mart Demo</title>
</head>
<body>

	<h1>Product List</h1>

	<p>

		Shopping Cart : <a href="cart-show"> <%
 ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
 %>
			<%=null == cart ? 0 : cart.itemCount()%>
		</a>

	</p>

	<p>
		<%
		if (null != cart && cart.itemCount() > 0) {
		%>
		
			<a href="cart-clear">Clear Cart</a>
		
		<%
		}
		%>
	</p>

	<%
	ProductModel model = (ProductModel) application.getAttribute("products");
	%>


	<table style="width: 60%">
		<tr>
			<td>ID</td>
			<td>Category</td>
			<td>Name</td>
			<td>Price</td>
			<td>Add</td>
		</tr>

		<%
		for (int i = 0; i < model.getList().size(); i++) {
			Product p = model.getList().get(i);
		%>

		<tr>
			<td><%=p.getId()%></td>
			<td><%=p.getCategory()%></td>
			<td><%=p.getName()%></td>
			<td><%=p.getPrice()%></td>
			<td><a href="cart-add?product=<%=p.getId()%>">Add to Cart</a>
			</td>
		</tr>

		<%
		}
		%>

	</table>
	
	<p>
	<a href="">Sale History</a>
	</p>
	

</body>
</html>