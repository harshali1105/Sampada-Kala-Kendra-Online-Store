package com.skk.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skk.utility.DBUtility;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UpdatePayment")
public class updatePayment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        if (userEmail == null) {
            resp.getWriter().write("User not logged in");
            return;
        }

        try (Connection conn = DBUtility.establishConnection()) {
            String selectQuery = "SELECT orderId FROM Orders WHERE uEmailId = ? AND orderStatus = 'Pending' ORDER BY orderId DESC LIMIT 1";
            PreparedStatement psSelect = conn.prepareStatement(selectQuery);
            psSelect.setString(1, userEmail);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                int orderId = rs.getInt("orderId");

                String updateQuery = "UPDATE Orders SET payment_status = 'Paid' WHERE orderId = ?";
                PreparedStatement psUpdate = conn.prepareStatement(updateQuery);
                psUpdate.setInt(1, orderId);
                int rows = psUpdate.executeUpdate();

                if (rows > 0) {
                    resp.getWriter().write("Payment updated successfully");
                } else {
                    resp.getWriter().write("Failed to update payment");
                }
            } else {
                resp.getWriter().write("No pending order found");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.getWriter().write("Error occurred: " + e.getMessage());
        }
    }
}
