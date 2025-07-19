package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.CartDaoImpl;
import com.skk.impl.ProductDaoImpl;
import com.skk.pojo.Cart;
import com.skk.pojo.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditCart2")
public class EditCartMain2 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		CartDaoImpl cdi = new CartDaoImpl();
		out.println("<h1>Update Product</h1>");
		String cartId = req.getParameter("id");
		int id = Integer.parseInt(cartId);
		
		Cart c = cdi.getCartById(id);
		if (c == null) {
            out.print("<script>alert('Cart item not found!'); window.location='DisplayCart';</script>");
            return;
        }
		out.print("<html>");
		out.print("<head><title>Edit Quantity</title><link rel='stylesheet' href='AdminProducts.css'></head>");
		out.print("<body>");
		out.print("<div class='admin-product-form-container'>");
		out.print("<form action='EditCart' method='post'>");
		out.print("<table>");
		out.print("<tr><td></td><td><input type='hidden' name='id'  value='"+c.getCartId()+"'/><td></tr>");
		out.print("<tr><td><label >Product Name: "+c.getProductName()+"</label><br></td></tr>");
		out.print("<tr><td><label >Price per Product: "+c.getProductPrice()+"</label><br></td></tr>");
		out.print("<tr><td><label >Enter New Quantity</label><br></td></th>");
		out.print("<tr><td><input type='number' name='newQty' class='box' value='"+c.getProductQty()+"' min='1' required><br></td></tr>");
		out.print("<tr><td colspan='2'><input type='submit' class='btn' value='Edit &amp; Save '/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		out.close();
		
	}
}
