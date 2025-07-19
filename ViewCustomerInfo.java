package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.OrdersDaoImpl;
import com.skk.pojo.SignUp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ViewCustomerInfo")
public class ViewCustomerInfo extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		String email = req.getParameter("email");

		OrdersDaoImpl odi = new OrdersDaoImpl();
		SignUp user = odi.getUserByEmail(email);

		out.print("<html><head><title>Customer Info</title>");
		out.print("<style>");
		out.print("body { background-color: #F4FFC3; font-family: 'Segoe UI', sans-serif; margin: 0; padding: 40px; }");
		out.print("h2 { text-align: center; color: #2c3e50; margin-bottom: 30px; }");
		out.print(".order-container { max-width: 600px; margin: auto; background: white; padding: 30px; border-radius: 12px; box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1); }");
		out.print(".table { width: 100%; border-collapse: collapse; }");
		out.print(".table tr { border: 1px solid #ddd;  }");
		out.print(".table td { padding: 15px; text-align: center; font-size: 16px; }");
		out.print(".table td:first-child { font-weight: 600; background-color: #ecf0f1; color: #333; width: 40%; }");
		out.print(".tooltip { text-decoration: none; color: #27ae60; font-size: 18px; display: inline-block; margin-bottom: 20px; }");
		out.print(".tooltip:hover { color: #2ecc71; text-decoration: underline; }");
		out.print("</style>");

        out.print("</head><body>");

        out.print("<div class='order-container'>");
        out.print("<a href='javascript:history.back()' class='tooltip'>&larr; Back</a>");
        out.print("<h2>Customer Information</h2>");
		out.print("<table class='table'>");
		out.print("<tr><td><strong>First Name:</strong></td><td>" + user.getUserFname() + "</td></tr>");
		out.print("<tr><td><strong>Last Name:</strong></td><td>" + user.getUserLname() + "</td></tr>");
		out.print("<tr><td><strong>Contact No:</strong></td><td>" + user.getUserContactNo() + "</td></tr>");
		out.print("<tr><td><strong>Location:</strong></td><td>" + user.getUserLocation() + "</td></tr>");
		out.print("</table>");
		out.print("</div></body></html>");
	}
}
