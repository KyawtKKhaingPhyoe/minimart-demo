package com.jdc.shop.controller;

import java.io.IOException;
import java.util.List;

import com.jdc.shop.model.SaleModel;
import com.jdc.shop.model.entity.Voucher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/sale-history"})
public class SaleServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private SaleModel model;
	
	@Override
	public void init() throws ServletException {
		var application = getServletContext();
		model = (SaleModel) application.getAttribute("sale.model");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		switch(req.getServletPath()) {
		
		case "/sale-history":
			showSaleHistory(req,resp);
			break;
		
		}
		
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
