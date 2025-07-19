package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.OrdersDaoImpl;
import com.skk.impl.ProductDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditOrders")
public class EditOrdersMain extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        int orderId = Integer.parseInt(req.getParameter("id"));
        String orderStatus = req.getParameter("orderStatus");

        OrdersDaoImpl odi = new OrdersDaoImpl();
        boolean updated = odi.updateOrderStatus(orderId, orderStatus);

        out.println("<html><head>");
        out.println("<script src='https://cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
        out.println("</head><body>");

        out.println("<script>");
        out.println("document.addEventListener('DOMContentLoaded', function() {");

        if (updated) {
            out.println("Swal.fire({");
            out.println("icon: 'success',");
            out.println("title: 'Order Status Updated!',");
            out.println("showConfirmButton: false,");
            out.println("timer: 2000");
            out.println("}).then(() => {");
            out.println("window.location.href = 'ViewCustomerOrders';");
            out.println("});");
        } else {
            out.println("Swal.fire({");
            out.println("icon: 'error',");
            out.println("title: 'Update Failed',");
            out.println("text: 'Something went wrong.'");
            out.println("});");
        }

        out.println("});");
        out.println("</script>");
        out.println("</body></html>");
        out.close();
	}
}
