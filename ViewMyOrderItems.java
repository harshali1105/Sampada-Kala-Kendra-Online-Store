package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.skk.impl.OrderItemsDaoImpl;
import com.skk.impl.OrdersDaoImpl;
import com.skk.pojo.OrderItems;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewOrderItemsUser")
public class ViewMyOrderItems extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderIdStr = req.getParameter("id");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        if (orderIdStr != null && !orderIdStr.isEmpty()) {
            try {
                int orderId = Integer.parseInt(orderIdStr);
        
                OrderItemsDaoImpl odi = new OrderItemsDaoImpl();
                List<OrderItems> items = odi.getItemsByOrderId(orderId);

        out.println("<html><head><title>Products</title>");
        out.println("<style>");
        out.println("table { border-collapse: collapse; width: 90%; margin: 30px auto; }");
        out.println("th, td { padding: 12px; border: 1px solid black; text-align: center; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("body { font-family: Arial; background-color: #F4FFC3; text-align: center; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<h2>Order Items</h2>");
        out.println("<a href='MyOrders'><button style='margin-top: 20px; margin-left:0px;'>Back</button></a>");
        out.println("<table><tr><th>Product Name</th><th>Quantity</th><th>Product Price</th></tr>");
        for (OrderItems item : items) {
            out.println("<tr>");
            out.println("<td>" + item.getProductName() + "</td>");
            out.println("<td>" + item.getProductQty() + "</td>");
            out.println("<td>" + item.getProductPrice() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
  
            } catch(NumberFormatException e) {
            	out.println("<h3 style='color:red;'>Invalid Order ID format.</h3>");
            }
	}
        else {
        	out.println("<h3 style='color:red;'>Order ID is missing.</h3>");
        }
	}
}
