package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.SignUpDaoImpl;
import com.skk.pojo.SignUp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SignUp")
public class SignUpMain extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String userFname,userLname,userEmailId,userPass,userCpass, userLocation;
		long userContactNo;
		SignUp s = new SignUp();
		SignUpDaoImpl ldi = new SignUpDaoImpl();
		boolean flag;
		
		userFname = req.getParameter("fname");
		userLname = req.getParameter("lname");
		userContactNo = Long.parseLong(req.getParameter("contact"));
		userLocation = req.getParameter("location");
		userEmailId = req.getParameter("email");
		userPass = req.getParameter("pass");
		userCpass = req.getParameter("cpass");
		
		out.println("<html><head>");
        out.println("<script src='https://cdn.jsdelivr.net/npm/sweetalert2@11'></script>");
        out.println("</head><body>");

        
		if(userPass.equals(userCpass))
		{
			
			
			s.setUserFname(userFname);
			s.setUserLname(userLname);
			s.setUserContactNo(userContactNo);
			s.setUserLocation(userLocation);
			s.setUserEmailId(userEmailId);
			s.setUserPass(userPass);
			
			
			
			flag = ldi.addUser(s);
			
	        out.println("<script>");
	        out.println("document.addEventListener('DOMContentLoaded', function() {");
	        if (flag) 
	        {
	            out.println("Swal.fire({");
	            out.println("icon: 'success',");
	            out.println("title: 'Register Successful!',");
	            out.println("showConfirmButton: false,");
	            out.println("timer: 3000");
	            out.println("}).then(() => {");
	            out.println("window.location.href = 'Login.html';");
	            out.println("});");
	        } 
	        else 
	        {
	            
	        	out.println("Swal.fire({");
	            out.println("icon: 'warning',");
	            out.println("title: 'Registration failed!',");
	            out.println("text: 'User Already Exists.',");
	            out.println("confirmButtonColor: '#d33',");
	            
	            out.println("}).then(() => {");
	            out.println("window.location.href = 'Login.html';");
	            out.println("});");
	        }
	        out.println("});</script>");
	        	
		}
		else
		{
			 out.println("<script>");
	            out.println("Swal.fire({");
	            out.println("icon: 'error',");
	            out.println("title: 'Oops...',");
	            out.println("text: 'Passwords do not match!'");
	            out.println("}).then(() => {");
	            out.println("window.location.href = 'Login.html';");
	            out.println("});");
	            out.println("</script>");
		}
		
        

        out.println("</body></html>");
        out.close();
	}
}
