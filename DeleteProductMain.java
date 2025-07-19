package com.skk.controller;

import java.io.IOException;
import com.skk.impl.ProductDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteProduct")
public class DeleteProductMain extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDaoImpl pdi = new ProductDaoImpl();
		String productId;
		productId = req.getParameter("id");
		int id = Integer.parseInt(productId);
		
		pdi.deleteProduct(id);
		resp.sendRedirect("ViewProduct");
	}
}
