package com.jdc.shop.controller;

import java.io.IOException;
import java.util.List;

import com.jdc.shop.model.SaleModel;
import com.jdc.shop.model.ShoppingCart;
import com.jdc.shop.model.entity.Voucher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/sale-history","/sale-details", "/checkout"})
public class SaleServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private SaleModel model;
	
	@Override
	public void init() throws ServletException {
		var application = getServletContext();
		model = (SaleModel) application.getAttribute("saleModel");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		switch(req.getServletPath()) {
		
		case "/sale-history":
			showSaleHistory(req,resp);
			break;
		case "/sale-details":
			showDetails(req,resp);
			break;
		
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//get customer name
		var customer = req.getParameter("customer");
		
		//get sale items from shopping cart
		var cart = (ShoppingCart) req.getSession().getAttribute("cart");
		var sales = cart.items();
		
		//create voucher
		var voucherId = model.create(customer, sales);
		
		//reset shopping cart
		req.getSession().removeAttribute("cart");
		
		//redirect to sale details
		resp.sendRedirect(getServletContext().getContextPath().concat("/sale-details?id=")+voucherId);
		
	}

	private void showDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//get id from request parameter
		var str = req.getParameter("id");
		var id = Integer.parseInt(str);
		
		//get target voucher from model
		var voucher = model.findById(id);
		
		// set voucher to request scope
		req.setAttribute("data", voucher);
		
		getServletContext().getRequestDispatcher("/sale-details.jsp").forward(req, resp);
		
	}

	private void showSaleHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//get sale list from sale model
		List<Voucher> list = model.getSaleHistory();
		
		//add sale list to request scope
		req.setAttribute("data", list);
		
		//forward to sale-list.jsp
		getServletContext().getRequestDispatcher("/sale-list.jsp").forward(req, resp);
		
	}

}
