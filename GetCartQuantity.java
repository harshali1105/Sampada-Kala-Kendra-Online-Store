package com.skk.controller;

import java.io.IOException;

import com.skk.impl.CartDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/GetCartQuantity")
public class GetCartQuantity extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userEmail = (session != null) ? (String) session.getAttribute("userEmail") : null;

        int totalQuantity = 0;
        if (userEmail != null) {
            CartDaoImpl cdi = new CartDaoImpl();
            totalQuantity = cdi.getTotalCartQuantity(userEmail);
        }

        resp.setContentType("text/plain");
        resp.getWriter().write(String.valueOf(totalQuantity));
    }
}
