package com.skk.controller;

import java.io.IOException;

import com.skk.impl.CartDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ClearCart")
public class ClearCart extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("userEmail") == null) {
			resp.sendRedirect("Login.html");
			return;
		}

		String userEmailId = (String) session.getAttribute("userEmail");
		CartDaoImpl cartDao = new CartDaoImpl();
		cartDao.clearCart(userEmailId);
		Double grandTotal = (Double) session.getAttribute("grandTotal");
        if (grandTotal == null) {
            grandTotal = 0.0; // Default value
        }

        

        // **Step 3: Clear Cart from Session**
        session.removeAttribute("cartItems");
        session.removeAttribute("grandTotal");

        // Send response to frontend
        resp.getWriter().write("Cart Cleared and Order Saved");
	}

}
