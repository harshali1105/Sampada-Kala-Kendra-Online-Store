package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.AdminDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Admin")
public class AdminMain extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		AdminDaoImpl adi = new AdminDaoImpl(); 
		String adminPassword;
		boolean flag;
		
		adminPassword = req.getParameter("pass");
		flag = adi.validateAdmin(adminPassword);
		
		out.println("<html><head>");
		out.println("<script src='https://cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
		out.println("</head><body>");

		out.println("<script>");
		out.println("document.addEventListener('DOMContentLoaded', function() {");
		if (flag) {
          out.println("Swal.fire({");
          out.println("icon: 'success',");
          out.println("title: 'Login Successful!',");
          out.println("text: 'Welcome, Admin!',");
          out.println("showConfirmButton: false,");
          out.println("timer: 3000");
          out.println("}).then(() => {");
          out.println("window.location.href = 'Admin.html';");
          out.println("});");
		} 
		else {
          out.println("Swal.fire({");
          out.println("icon: 'error',");
          out.println("title: 'Login Failed!',");
          out.println("text: 'Incorrect password. Please try again.',");
          out.println("confirmButtonColor: '#d33'");
          out.println("}).then(() => {");
          out.println("window.location.href = 'RoleSelection.html';");
          out.println("});");
		}
		out.println("});");
		out.println("</script>");

		out.println("</body></html>");
		out.close();
	}
}		
		

