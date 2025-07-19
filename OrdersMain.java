package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.skk.impl.OrderItemsDaoImpl;
import com.skk.impl.OrdersDaoImpl;
import com.skk.pojo.Orders;
import com.skk.pojo.SignUp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewCustomerOrders")
public class OrdersMain extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		List<Orders> al = new ArrayList<Orders>();
		OrdersDaoImpl odi = new OrdersDaoImpl();
		
		HttpSession session = req.getSession(true);  
		
		
		al = odi.showOrders();	
		
		out.print("<html>");
		out.print("<head>");
        out.print("<title>Customer Orders</title>");
        out.print("<link rel='stylesheet' href='AdminOrders.css'>");
        out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/boxicons/2.1.0/css/boxicons.min.css'>");
        out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css'>");
        out.print("</head>");
        out.print("<body>");
        
        out.print("<div class='order-container'>");
        out.print("<div class='header'>");
       out.print("<a href='Admin.html' class='tooltip'><i class='fa-solid fa-arrow-left'></i><span class='tooltip-text'></span></a>");
        
        out.print("<h1>Customer Orders</h1>");
        out.print("</div>");
      
       
        out.print("<table class='table'>");
        out.print("<tr><th>Id</th><th>Customer EmailId</th><th>Total Amount</th><th>Payment Method</th><th>Order Date</th><th>Order Status</th><th>View Orders</th><th>View Customers</th><th>Edit</th><th>Delete</th></tr>");

   
        for (Orders o : al) {
            out.print("<tr><td>" + o.getOrderId()+"</td><td>"+ o.getUserEmailId() + "</td><td>"+ o.getTotalBill() +"</td><td>"+o.getPaymentMethod()+"</td><td>"+o.getOrderDate()+"</td><td>"+o.getOrderStatus()+
                      "</td><td><a href='ViewOrdersAdmin?id="+o.getOrderId()+"'><button>View Order</button></a></td><td><a href='ViewCustomerInfo?email="+o.getUserEmailId()+"'><button> Customer Info</button></a></td><td><a href='EditOrders2?id="+o.getOrderId()+"'><i class='bx bxs-edit'></i></a></td><td><a href='DeleteOrder?id="+o.getOrderId()+"'><i class='bx bxs-trash'></i></a></td></tr>");
           
        }	
      
        out.print("</table>");
        out.print("</div>");
        out.print("</body>");
        out.print("</html>");
        out.close();
	}
}
