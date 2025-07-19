package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.skk.impl.ProductDaoImpl;
import com.skk.pojo.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Product")
public class ProductMain extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		Product p = new Product();
		ProductDaoImpl pdi = new ProductDaoImpl(); 
		String ProductName, ProductDescription, ProductImageUrl;
		double  ProductSize, ProductPrice;
		int  ProductQty, status;
		ProductName = req.getParameter("product_name");
		ProductPrice = Double.parseDouble(req.getParameter("product_price"));
		ProductSize = Double.parseDouble(req.getParameter("product_size"));
		ProductQty = Integer.parseInt(req.getParameter("product_qty"));
		ProductDescription = req.getParameter("product_description");
		ProductImageUrl = req.getParameter("product_image");
		
		p.setProductName(ProductName);
		p.setProductPrice(ProductPrice);
		p.setProductSize(ProductSize);
		p.setProductQty(ProductQty);
		p.setProductDescription(ProductDescription);
		p.setProductImageUrl(ProductImageUrl);
		
		status = pdi.AddProduct(p);
		if(status>0)
		{
			out.print("<script>alert('Product Saved Successfully!!');</script>");
			req.getRequestDispatcher("AdminProducts.html").include(req,resp);
		}
		else
		{
			out.print("<script>alert('Sorry!! Unable to save record');</script>");
		}
		out.close();
	}
}
