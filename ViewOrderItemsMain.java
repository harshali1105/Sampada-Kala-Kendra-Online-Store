package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.skk.impl.OrderItemsDaoImpl;
import com.skk.pojo.OrderItems;
import com.skk.pojo.SignUp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ViewOrderItemsMain extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		List<OrderItems> al = new ArrayList<OrderItems>();
		OrderItemsDaoImpl odi = new OrderItemsDaoImpl();
		
		HttpSession session = req.getSession(true);  
		int orderId = Integer.parseInt(req.getParameter("id"));
		
		al = odi.getItemsByOrderId(orderId);	
		
		out.print("<html>");
		out.print("<head>");
        out.print("<title>Customer Orders</title>");
        out.print("<link rel='stylesheet' href='AdminOrders.css'>");
        out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/boxicons/2.1.0/css/boxicons.min.css'>");
        out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css'>");
        out.print("</head>");
        out.print("<body>");
        
        out.print("<div class='order-container'>");
        out.print("<div class='header'>");
       out.print("<a href='Admin.html' class='tooltip'><i class='fa-solid fa-arrow-left'></i><span class='tooltip-text'></span></a>");
        
        out.print("<h1>Customer Orders</h1>");
        out.print("</div>");
      
       
        out.print("<table class='table'>");
        out.print("<tr><th>Order Item Id</th><th>Order Id</th><th>Product Name</th><th>Quantity</th><th>Product Price</th></tr>");
        SignUp s = new SignUp();
   
        for (OrderItems o : al) {
            out.print("<tr><td>" + o.getOrderItemId()+"</td><td>" + o.getOrderId() + "</td><td>" + o.getProductName() +
                      "</td><td>"+ o.getProductQty() + "</td><td>"+ o.getProductPrice() +"</td>");
           
        }	
      
        out.print("</table>");
        out.print("</div>");
        out.print("</body>");
        out.print("</html>");
        out.close();
	}
}
