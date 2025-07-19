package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.skk.impl.CartDaoImpl;
import com.skk.pojo.Cart;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartMain extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		CartDaoImpl cdi = new CartDaoImpl();
		Cart c= new Cart();
		int productId, productQty;
		String productName, productImage;
		double productPrice, productSize;
		boolean flag;
		
		HttpSession session = req.getSession(true);
        String userEmail = (session != null && session.getAttribute("userEmail") != null) ? (String) session.getAttribute("userEmail") : null;

        if (userEmail == null) {
            out.println("<script>alert('User not logged in!! Login First!!');</script>");
            req.getRequestDispatcher("Login.html").include(req,resp);
            return;
        }
        
        
		
        
		productId = Integer.parseInt(req.getParameter("pId"));
		productImage = req.getParameter("pImage");
		productName = req.getParameter("pName");
		productQty = Integer.parseInt(req.getParameter("pQty"));
		productPrice = Double.parseDouble(req.getParameter("selectedPrice"));
		productSize = Double.parseDouble(req.getParameter("selectedSize"));
		
		c.setProductId(productId);
		c.setProductImageUrl(productImage);
		c.setProductName(productName);
		c.setProductQty(productQty);
		c.setProductPrice(productPrice);
		c.setProductSize(productSize);
		c.setUserEmailId(userEmail);
		
		
		
		
		flag = cdi.addToCart(c);
		if(flag)
		{
			out.print("<script>alert('Ganesha Idol Added to the Cart!!');</script>");
			resp.sendRedirect("DisplayCart");
		}
		else
		{
			out.print("<script>alert('Sorry!! Unable to save record');</script>");
		}
		out.close();
	}
	
}
