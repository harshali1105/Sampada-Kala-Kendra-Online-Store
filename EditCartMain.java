package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.CartDaoImpl;
import com.skk.pojo.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditCart")
public class EditCartMain extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CartDaoImpl cdi = new CartDaoImpl();
		PrintWriter out = resp.getWriter();
		
		int cartId = Integer.parseInt(req.getParameter("id"));
		int newQty = Integer.parseInt(req.getParameter("newQty"));
		
		Cart c = cdi.getCartById(cartId);
		
		double updatedsubtotal = newQty * c.getProductPrice();
		
		boolean updated = cdi.updateCart(cartId, newQty, updatedsubtotal);
		if(updated)
		{
			resp.sendRedirect("DisplayCart");
		}
		else
		{
			out.print("<script>alert('Failed To Updated Quantity!!')</script>");
			
		}
		out.close();
	}
}
