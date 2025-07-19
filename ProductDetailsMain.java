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

@WebServlet("/ProductDetails")
public class ProductDetailsMain extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		ProductDaoImpl pdi = new ProductDaoImpl();
		
		String productId = req.getParameter("id");
		int id = Integer.parseInt(productId);
		Product p = pdi.getProductById(id);
		
		out.println("<html>");
        out.println("<head>");
        out.println("<title>Product Details</title>");
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css' />");
        out.println("<link rel='stylesheet' href='Details.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id='header-container'></div>");
        out.println("<div class='details-container'>");
        out.println("	<div class='header'>");
        out.println("		<a href='DisplayProduct' class='tooltip'><i class='fa-solid fa-arrow-left'></i>");
        out.println("		<span class='tooltip-text'>Back</span></a>");
        out.println("		<a href='DisplayCart'><div class='iconCart'>");
        out.println("			<i class='fa-solid fa-cart-shopping'></i></a>");
        out.println("			<div class='totalQuantity'>0</div>");
       	out.println("		</div>");
       	out.println("	</div>");
       	
        out.println("    <div class='detail'>");
        out.println("        <div class='image'><img src='idols/" + p.getProductImageUrl() + "' width='300px'></div>");
        out.println("        <div class='content'>");
        out.println("            <form action='CartServlet' method='POST'>");
        out.println("				 <input type='hidden' name='pImage' value='"+p.getProductImageUrl()+"'>");
        out.println("                <input type='hidden' name='pId' value='" + p.getProductId() + "'>");
        out.println("                <input type='hidden' name='pName' value='" + p.getProductName() + "'>");
        out.println("                <input type='hidden' id='selectedSize' name='selectedSize' value=''>");
        out.println("				 <input type='hidden' name='pQty' value='1' min='1'>");
        out.println("                <input type='hidden' id='selectedPrice' name='selectedPrice' value=''>");
        out.println("                <div class='name'>" + p.getProductName() + "</div>");
        out.println("                <select class='size-select' name='size' onchange='updatePrice(this)' required>");
        out.println("                    <option value='' disabled selected>Select Size</option>");
        out.println("                    <option value='500' data-size='1.5'>1.5 feet - ₹500</option>");
        out.println("                    <option value='1000' data-size='2'>2 feet - ₹1000</option>");
        out.println("                    <option value='2000' data-size='2.5'>2.5 feet - ₹2000</option>");
        out.println("                    <option value='3000' data-size='3'>3 feet - ₹3000</option>");
        out.println("                </select>");
        out.println("                <div class='price' id='productPrice'>₹" + p.getProductPrice() + "</div>");
        out.println("                <div class='buttons'>");
        out.println("                    <button type='submit' name='action' value='cart'>Add To Cart <i class='fa-solid fa-cart-shopping'></i></button>");
        out.println("                    <button type='submit' name='action' value='buy'>Buy Now</button>");
        out.println("                </div>");
        out.println("            </form>");
        out.println("            <div class='Description'>" + p.getProductDescription() + "</div>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</div>");
        out.println("<div id='footer-container' ></div>");
        
        out.println("<script src='Script.js'></script>");
        out.println("<script>");
        out.println("function updatePrice(selectElement) {");
        out.println("    const selectedOption = selectElement.options[selectElement.selectedIndex];");
        out.println("    const newPrice = selectedOption.value;");
        out.println("    const newSize = selectedOption.getAttribute('data-size');");
        out.println("    document.getElementById('productPrice').innerText = '₹' + newPrice;");
        out.println("    document.getElementById('selectedPrice').value = newPrice;");  // Update hidden price field
        out.println("    document.getElementById('selectedSize').value = newSize;");  // Update hidden size field
        out.println("}");
        out.println("function updateQuantity(input) {");
        out.println("    document.getElementById('selectedQty').value = input.value;");  // Update hidden quantity field
        out.println("}");
        out.println("</script>");

        out.println("</body>");
        out.println("</html>");

        out.close();
	}
}
