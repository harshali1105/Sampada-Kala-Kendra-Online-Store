package com.skk.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skk.impl.CartDaoImpl;
import com.skk.impl.OrdersDaoImpl;
import com.skk.pojo.Cart;
import com.skk.pojo.Orders;
import com.skk.utility.DBUtility;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PlaceOrder")
public class PlaceOrderServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        
        List<Cart> cartItems = (List<Cart>) session.getAttribute("cartItems");
        
        String address = req.getParameter("address");
        String paymentMethod = req.getParameter("paymentMethod");

        CartDaoImpl cimpl = new CartDaoImpl();
        double totalBill = cimpl.calculateTotal(userEmail);
        
        Orders o = new Orders();
        o.setUserEmailId(userEmail);
        o.setAddress(address);
        o.setPaymentMethod(paymentMethod);
        o.setTotalBill(totalBill);

        OrdersDaoImpl odi = new OrdersDaoImpl();
        boolean success = odi.placeOrder(o, cartItems);
        
        if(success){
            cimpl.clearCart(userEmail);
            session.removeAttribute("cartItems");
            resp.sendRedirect("OrderSuccess.html");
        } else {
            resp.sendRedirect("DisplayProduct?msg=fail");
        }
       
    }
}
