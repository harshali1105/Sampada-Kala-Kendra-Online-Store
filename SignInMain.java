package com.skk.controller;

import java.io.IOException;

import java.io.PrintWriter;
import com.skk.impl.SignInDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SignIn")
public class SignInMain extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String userEmailId, userPass;
		boolean flag;
		SignInDaoImpl sdi = new SignInDaoImpl();
		
		userEmailId = req.getParameter("email");
		userPass = req.getParameter("pass");
		
		flag = sdi.getUserPasswordByEmail(userEmailId, userPass);
		
		
		out.println("<html><head>");
        out.println("<script src='https://cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
        out.println("</head><body>");

        out.println("<script>");
        out.println("document.addEventListener('DOMContentLoaded', function() {");
        if(flag) 
        {
        	 HttpSession session = req.getSession(); // Creates a new session if not existing
             session.setAttribute("userEmail", userEmailId); 
            out.println("Swal.fire({");
            out.println("icon: 'success',");
            out.println("title: 'Login Successful!',");
            out.println("text: 'Welcome!!',");
            out.println("showConfirmButton: false,");
            out.println("timer: 3000");
            out.println("}).then(() => {");
            out.println("window.location.href = 'DisplayProduct';");
            out.println("});");
        } 
        else {
            out.println("Swal.fire({");
            out.println("icon: 'error',");
            out.println("title: 'Login Failed!',");
            out.println("text: 'Incorrect password. Please try again.',");
            out.println("confirmButtonColor: '#d33'");
            out.println("}).then(() => {");
            out.println("window.location.href = 'Login.html';");
            out	.println("});");
        }
        out.println("});");
        out.println("</script>");

        out.println("</body></html>");
        out.close();
		
	}
}
