package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.skk.impl.OrdersDaoImpl;
import com.skk.pojo.Orders;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MyOrders")
public class MyOrders extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		HttpSession session = req.getSession();
		String userEmail = (String) session.getAttribute("userEmail");
		
		if(userEmail == null)
		{
			resp.sendRedirect("Login.html");
			return;
		}
		
		OrdersDaoImpl dao = new OrdersDaoImpl();
		List<Orders> al = dao.showOrdersbyemail(userEmail);
		
		out.println("<html><head><title>My Orders</title>");
        out.println("<style>");
        out.println("table { border-collapse: collapse; width: 90%; margin: 30px auto; }");
        out.println("th, td { padding: 12px; border: 1px solid black; text-align: center; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("body { font-family: Arial; background-color: #F4FFC3; text-align: center; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<h2>My Order History</h2>");
        out.println("<a href='DisplayProduct'><button style='margin: 10px 0 20px 20px;'>Back to Home</button></a>");
        out.println("<table>");
        out.println("<tr><th>Order ID</th><th>Total Amount</th><th>Status</th><th>Date</th><th>View Items</th></tr>");

        for (Orders o : al) {
            out.println("<tr>");
            out.println("<td>" + o.getOrderId() + "</td>");
            out.println("<td>" + o.getTotalBill() + "</td>");
            out.println("<td>" + o.getOrderStatus() + "</td>");
            out.println("<td>" + o.getOrderDate() + "</td>");
            out.println("<td><a href='ViewOrderItemsUser?id=" + o.getOrderId() + "'>View Items</a></td>");
            out.println("</tr>");
        }

        out.println("</table>");
        
        out.println("</body></html>");
        out.close();
	}
}
