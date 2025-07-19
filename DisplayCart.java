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

@WebServlet("/DisplayCart")
public class DisplayCart extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CartDaoImpl cdi = new CartDaoImpl();
		PrintWriter out = resp.getWriter();
		
		HttpSession session = req.getSession(true);
        String userEmail = (session != null && session.getAttribute("userEmail") != null) ? (String) session.getAttribute("userEmail") : null;

		if (userEmail == null) {
			out.println("<script>alert('User not logged in');</script>");
			req.getRequestDispatcher("Login.html").include(req, resp);
			return;
		}
		List<Cart> cartItems = cdi.showCart(userEmail);
        
		session.setAttribute("cartItems", cartItems);
        
		
		out.print("<html>");
        out.print("<head>");
        out.print("<title>Cart</title>");
        out.print("<link rel='stylesheet' href='AdminProducts.css'>");
        out.print("<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>");
        out.print("</head>");
        out.print("<body>");
        out.print("<div class='header'>");
        out.print("<a href='DisplayProduct'>Back</a>");
        out.print("<h1 style='display:flex; position:center;'>Your Cart</h1>");
        out.print("</div>");
        
        out.print("<div class='producttable'>");
        out.print("<table class='table'>");
        out.print("<tr><th>Id</th><th>Image</th><th>Product Name</th><th>Product Qty</th><th>Product Price</th><th>SubTotal</th><th>Product Size</th><th>Edit</th><th>Remove</th></tr>");
        
        double grandTotal = 0;
        	
        for (Cart c : cartItems) {
        	
        	grandTotal += c.getProductSubtotal();
        	
        	out.print("<tr><td>"+c.getCartId()+"</td><td><img src='idols/"+c.getProductImageUrl()+"' width='50' alt='Product Image'></td><td>" + c.getProductName() + "</td><td>" +c.getProductQty() + "</td><td>" + c.getProductPrice() +
                      "</td><td>"+c.getProductSubtotal()+"</td><td>" + c.getProductSize() + "</td><td><a href='EditCart2?id="+c.getCartId()+"'><i class='bx bxs-edit'></i></a></td><td><a href='DeleteCartProduct?id="+c.getCartId()+"'><i class='bx bxs-trash'></i></a></td></tr>");
            
            
            
            
        }
        
        
        out.print("<tr><td colspan='5'><h3>Grand Total: </h3></td>");  
        out.print("<td><strong>"+grandTotal+"</strong></td>");
        
        out.print("<form action='SummaryServlet' method='post'>");
        out.print("<input type='hidden' name='grandTotal' value='"+grandTotal+"'>");
        out.print("<td colspan='3'><button type='submit' class='checkout-btn'>Checkout</button></td></tr>");
        out.print("</table>");    
                 
        
        out.print("</form>");
        
        out.print("</div>");
        out.print("</body>");
        out.print("</html>");
        out.close();
	}
}
