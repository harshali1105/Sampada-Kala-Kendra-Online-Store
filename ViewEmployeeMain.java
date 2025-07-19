package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.skk.impl.EmployeeDaoImpl;
import com.skk.pojo.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewEmployee")
public class ViewEmployeeMain extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		PrintWriter out = resp.getWriter();
		List<Employee> al = new ArrayList<Employee>();
		al = edi.viewEmployee();
		out.print("<html>");
		out.print("<head>");
        out.print("<title>Product List</title>");
        out.print("<link rel='stylesheet' href='AdminEmployee.css'>");
        out.print("<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>");
        out.print("</head>");
        out.print("<body>");
        out.print("<div class='header'>");
        out.print("<a href='AdminEmployee.html'>Add New Employee</a>");
        out.print("<h1>Employee List</h1>");
        out.print("</div>");
        out.print("<div class='emptable'>");
        out.print("<table class='table'>");
        out.print("<tr><th>Id</th><th>Name</th><th>Location</th><th>Email Id</th><th>Contact No</th><th>Salary</th><th>Edit</th><th>Delete</th></tr>");

        int serialNo = 1;
        for (Employee e : al) {
            out.print("<tr><td>" + serialNo + "</td><td>" + e.getEmpName() + "</td><td>" + e.getEmpLoc() +
                      "</td><td>" + e.getEmpEmailId() + "</td><td>" + e.getEmpContact() +"</td><td>"+ e.getEmpSalary() +"</td><td><a href='EditEmployee2?id="+e.getEmpId()+"'><i class='bx bxs-edit'></i></a></td><td><a href='DeleteEmployee?id="+e.getEmpId()+"'><i class='bx bxs-trash'></i></a></td></tr>");
            serialNo++;
        }
        out.print("</div>");

        out.print("</table>");
        out.print("</body>");
        out.print("</html>");
        out.close();
		
	}
}
