package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.skk.impl.ProductDaoImpl;
import com.skk.pojo.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewProduct")
public class ViewProductMain extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		ProductDaoImpl pdi = new ProductDaoImpl();
		PrintWriter out = resp.getWriter();
		List<Product> al = new ArrayList<Product>();
		al = pdi.ViewProduct();
		out.print("<html>");
        out.print("<head>");
        out.print("<title>Product List</title>");
        out.print("<link rel='stylesheet' href='AdminProducts.css'>");
        out.print("<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>");
        out.print("</head>");
        out.print("<body>");
        out.print("<div class='header'>");
        out.print("<a href='AdminProducts.html'>Add New Products</a>");
        out.print("<h1>Product List</h1>");
        out.print("</div>");
        
        out.print("<div class='producttable'>");
        out.print("<table class='table'>");
        out.print("<tr><th>Id</th><th>Name</th><th>Price</th><th>Size</th><th>Quantity</th><th>Description</th><th>Image</th><th>Edit</th><th>Delete</th></tr>");
       
        for (Product p : al) {
        	out.print("<tr><td>"+p.getProductId()+"</td><td>" + p.getProductName() + "</td><td>" + p.getProductPrice() + "</td><td>" + p.getProductSize() +
                      "</td><td>" + p.getProductQty() + "</td><td>" + p.getProductDescription() +
                      "</td><td><img src='idols/" + p.getProductImageUrl() + "' width='100'></td><td><a href='EditProduct2?id="+p.getProductId()+"'><i class='bx bxs-edit'></i></a></td><td><a href='DeleteProduct?id="+p.getProductId()+"'><i class='bx bxs-trash'></i></a></td></tr>");
         
        }
        
        out.print("</table>");
        out.print("</div>");
        out.print("</body>");
        out.print("</html>");
        out.close();
		
	}
}
