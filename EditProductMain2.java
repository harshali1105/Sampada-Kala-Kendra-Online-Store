package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.ProductDaoImpl;
import com.skk.pojo.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditProduct2")
public class EditProductMain2 extends HttpServlet{
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		ProductDaoImpl pdi = new ProductDaoImpl();
		
		String productId = req.getParameter("id");
		int id = Integer.parseInt(productId);
		
		Product p = pdi.getProductById(id);
		out.print("<html>");
		out.print("<head><title>Edit Products</title><link rel='stylesheet' href='AdminProducts.css'></head>");
		out.print("<body>");
		
		out.print("<div class='admin-product-form-container'>");
		out.print("<div class='header'>");
		out.println("<h1>Update Product</h1>");
		out.print("</div>");
		out.print("<form action='EditProduct' method='post'>");
		out.print("<table>");
		out.print("<tr><td></td><td><input type='hidden' name='id' class='box' value='"+p.getProductId()+"'/><td></tr>");
		out.print("<tr><td>Name</td><td><input type='text' name='name' class='box' value='"+p.getProductName()+"'/><td></tr>");
		out.print("<tr><td>Price</td><td><input type='number' name='price' class='box' value='"+p.getProductPrice()+"'/><td></tr>");
		out.print("<tr><td>Size</td><td>");
		out.print("<select name='size' class='box'  value='"+p.getProductSize()+"'/>");
		out.print("<option>1.5</option>");
		out.print("<option>2</option>");
		out.print("<option>2.5</option>");
		out.print("<option>3</option>");
		out.print("</select>");
		out.print("<td></tr>");
		out.print("<tr><td>Quantity</td><td><input type='number' name='qty' class='box' value='"+p.getProductQty()+"'/><td></tr>");
		out.print("<tr><td>Description</td><td><input type='text' name='description' class='box' value='"+p.getProductDescription()+"'/><td></tr>");
		out.print("<tr><td>Image</td><td><input type='file' accept='image/png, image/jpeg, image/jpg' name='image' class='box' value='"+p.getProductImageUrl()+"'/><td></tr>");
		out.print("<tr><td colspan='2'><input type='submit' class='btn' value='Edit &amp; Save '/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		out.close();
		
	}
}
