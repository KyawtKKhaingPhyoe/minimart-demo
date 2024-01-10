<%@page import="com.jdc.shop.model.entity.SaleItem"%>
<%@page import="java.util.List"%>
<%@page import="com.jdc.shop.model.ShoppingCart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
</head>
<body>

	<h1>My Cart</h1>

	<p>Item Details in Shopping Cart</p>

	<%
	ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
	List<SaleItem> items = cart.items();
	%>

	<table style="width: 70%">
		<tr>
			<td>Product</td>
			<td>Category</td>
			<td>Unit Price</td>
			<td style="text-align: center;">Count</td>
			<td style="text-align: center;">Total</td>
		</tr>

		<%
		for (SaleItem item : cart.items()) {
		%>
		
		<tr>
			<td><%= item.getProduct().getName() %></td>
			<td><%= item.getProduct().getCategory() %></td>
			<td style="text-align: center;"><%= item.getProduct().getPrice() %></td>
			<td style="text-align: center;">
			
				<!--  minus count -->
				<a href="cart-minus?product=<%= item.getProduct().getId()%>">-</a>
				
				<%= item.getCount() %>
				
				<!--  plus count -->
				<a href="cart-plus?product=<%= item.getProduct().getId()%>">+</a>
				
			</td>
			<td style="text-align: center;"><%= item.getTotal() %></td>
		</tr>
		
		<%
		}
		%>
		
		<tr>
			<td colspan="3">Total</td>
			<td style="text-align: center;"><%= cart.itemCount() %></td>
			<td style="text-align: center;"><%= cart.total() %></td>
		</tr>

	</table>
	
	<p>
		<a href="index.jsp">Home</a>
	</p>

</body>
</html>