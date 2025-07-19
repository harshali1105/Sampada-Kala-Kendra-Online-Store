package com.skk.controller;

import java.io.IOException;
import java.util.List;

import com.skk.impl.CartDaoImpl;
import com.skk.pojo.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SummaryServlet")
public class SummaryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("userEmail") == null) {
            resp.sendRedirect("Login.html");
            return;
        }

        String userEmail = (String) session.getAttribute("userEmail");

        // Step 1: Get grandTotal from the form
        double grandTotal = Double.parseDouble(req.getParameter("grandTotal"));
        System.out.println("Checkout summary for: " + userEmail + " | Total: " + grandTotal);

        // Step 2: Fetch Cart Items
        CartDaoImpl cartDao = new CartDaoImpl();
        List<Cart> cartItems = cartDao.showCart(userEmail);

        // Optional: Store in session if you want to use it in Summary.html using JSP
        session.setAttribute("cartSummary", cartItems);
        session.setAttribute("grandTotal", grandTotal);

        // Step 3: Redirect to Summary Page
        resp.sendRedirect("Summary.html");
    }
}
