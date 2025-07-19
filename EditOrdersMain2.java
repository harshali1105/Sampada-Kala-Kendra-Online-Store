package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.ProductDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditOrders2")
public class EditOrdersMain2 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		int orderId = Integer.parseInt(req.getParameter("id"));

		out.println("<html><head><title>Edit Order</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; padding: 30px; }");
        out.println(".container { background: #fff; width: 400px; margin: auto; padding: 20px 30px; border-radius: 10px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }");
        out.println("h2 { text-align: center; color: #333; }");
        out.println("label { display: block; margin-top: 15px; font-weight: bold; }");
        out.println("select, input[type='submit'] { width: 100%; padding: 10px; margin-top: 10px; border: 1px solid #ccc; border-radius: 5px; font-size: 16px; }");
        out.println("input[type='submit'] { background-color: #4CAF50; color: white; cursor: pointer; }");
        out.println("input[type='submit']:hover { background-color: #45a049; }");
        out.println(".back-btn { margin-top: 20px; text-align: center; }");
        out.println(".back-btn a button { padding: 10px 20px; border: none; background-color: #777; color: white; border-radius: 5px; cursor: pointer; }");
        out.println(".back-btn a button:hover { background-color: #555; }");
        out.println("</style>");
        out.println("</head><body>");

        out.println("<div class='container'>");
        out.println("<h2>Edit Order Status</h2>");
        out.println("<form method='post' action='EditOrders'>");
        out.println("<input type='hidden' name='id' value='" + orderId + "'/>");

        out.println("<label for='orderStatus'>Order Status:</label>");
        out.println("<select name='orderStatus'>");
        out.println("<option value='Pending'>Pending</option>");
        
        out.println("<option value='Completed'>Completed</option>");
        out.println("</select>");

        out.println("<input type='submit' value='Update Status'/>");
        out.println("</form>");

        out.println("<div class='back-btn'>");
        out.println("<a href='ViewCustomerOrders'><button>Back</button></a>");
        out.println("</div>");
        out.println("</div>");

        out.println("</body></html>");
        out.close();
	}
}
