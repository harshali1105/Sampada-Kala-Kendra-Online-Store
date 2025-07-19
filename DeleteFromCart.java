package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.CartDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCartProduct")
public class DeleteFromCart extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CartDaoImpl cdi = new CartDaoImpl();
		PrintWriter out = resp.getWriter();
		String cartId;
		cartId = req.getParameter("id");
		int id = Integer.parseInt(cartId);
		
		cdi.deleteCart(id);
		
		resp.sendRedirect("DisplayCart");
		
	}
}
