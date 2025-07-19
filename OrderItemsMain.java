package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.skk.impl.OrderItemsDaoImpl;

import com.skk.pojo.OrderItems;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewOrdersAdmin")
public class OrderItemsMain extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String idStr = req.getParameter("id");
        int orderId = Integer.parseInt(idStr);


        OrderItemsDaoImpl itemDao = new OrderItemsDaoImpl();
        List<OrderItems> items = itemDao.getItemsByOrderId(orderId);

        out.print("<html>");
        out.print("<head>");
        out.print("<title>Order Items</title>");
        out.print("<link rel='stylesheet' href='AdminOrders.css'>");
        out.print("</head>");
        out.print("<body>");
        out.print("<div class='order-container'>");
        out.print("<h2>Order Items for Order ID: " + orderId + "</h2>");
        out.print("<a href='ViewCustomerOrders'><button>Back</button></a>");
        
        out.print("<table class='table'>");
        out.print("<tr><th>Order Item Id</th><th>Product Id</th><th>Product Name</th><th>Quantity</th><th>Price</th><th>Size</th><th>Subtotal</th><th>Image</th></tr>");

        for (OrderItems item : items) {
            out.print("<tr>");
            out.print("<td>" + item.getOrderItemId() + "</td>");
           
            out.print("<td>" + item.getProductId() + "</td>");
            out.print("<td>" + item.getProductName() + "</td>");
            out.print("<td>" + item.getProductQty() + "</td>");
            out.print("<td>" + item.getProductPrice() + "</td>");
            out.print("<td>" + item.getProductSize() + "</td>");
            out.print("<td>" + item.getProductSubtotal() + "</td>");
            out.print("<td>" + item.getProductImage() + "</td>");
            out.print("</tr>");
        }

        out.print("</table>");
        out.print("</div>");
        out.print("</body>");
        out.print("</html>");
        out.close();
    }
}
