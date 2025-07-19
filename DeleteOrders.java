package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import com.skk.impl.OrdersDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteOrder")
public class DeleteOrders extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OrdersDaoImpl odi = new OrdersDaoImpl();
		PrintWriter out = resp.getWriter();
		String orderId;
		orderId = req.getParameter("id");
		int id = Integer.parseInt(orderId);
		
		boolean flag = odi.deleteOrder(id);
		if(flag)
		{
			System.out.println("Deleted successfully");
		}
		else
		{
			System.out.println("Deletion failed");
		}
		
		resp.sendRedirect("ViewCustomerOrders");
	}
}
