package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.skk.impl.ProductDaoImpl;
import com.skk.pojo.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/EditProduct")
@MultipartConfig
public class EditProductMain extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		ProductDaoImpl pdi = new ProductDaoImpl();
		PrintWriter out = resp.getWriter();
		
		String productId = req.getParameter("id");
		int id = Integer.parseInt(productId);
		
		String productName = req.getParameter("name");
		String productPrice = req.getParameter("price");
		double price = Double.parseDouble(productPrice);
		String productSize = req.getParameter("size");
		double size = Double.parseDouble(productSize);
		String productQty = req.getParameter("qty");
		int qty = Integer.parseInt(productQty);
		String productDescription = req.getParameter("description");
		String productImageUrl = req.getParameter("image");
		
		
		Product p = new Product();
		p.setProductId(id);
		p.setProductName(productName);
		p.setProductPrice(price);
		p.setProductSize(size);
		p.setProductQty(qty);
		p.setProductDescription(productDescription);
		p.setProductImageUrl(productImageUrl);
		
		
		int status = pdi.updateProduct(p);
		if(status>0)
		{
			resp.sendRedirect("ViewProduct");
		}
		else
		{
			out.print("<script>alert('Sorry!! Unable to connect')</script>");
			
		}
		out.close();
	
	}
}
