package com.skk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.skk.impl.CartDaoImpl;
import com.skk.impl.ProductDaoImpl;
import com.skk.impl.SignUpDaoImpl;
import com.skk.pojo.Cart;
import com.skk.pojo.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DisplayProduct")
public class DisplayProductMain extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		ProductDaoImpl pdi = new ProductDaoImpl();
		SignUpDaoImpl sdi = new SignUpDaoImpl(); 
		CartDaoImpl cdi = new CartDaoImpl();
		List<Product> al = new ArrayList<Product>();
		al = pdi.ViewProduct();
		
		HttpSession session = req.getSession(true);
        String userEmail = (session != null && session.getAttribute("userEmail") != null) ? (String) session.getAttribute("userEmail") : null;
        int totalCartQuantity = 0;
	    if(userEmail != null) {
	    	totalCartQuantity = cdi.getTotalCartQuantity(userEmail);
	    }
	    
	    
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Product</title>");
		out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css' />");
		out.print("<link rel='stylesheet' href='Product.css'>");
		out.print("</head>");
		out.print("<body>");
		out.print("<div id='header-container'></div>");
		out.print("<div class='product-container'>");
		out.print("<div class='header'>");
	    out.print("<div class='search-box'>");
	    out.print("<input type='text' placeholder='Search Products Here' id='search-box'>");
	    out.print("<i class='fa-solid fa-magnifying-glass'></i>");
	    out.print("</div>");
	    out.print("<div class='user'>");
	    if(userEmail != null) {
            out.print("<div class='dropdown'>");
            out.print("<button class='dropbtn'><i class='fa-solid fa-user'></i> " + userEmail + "</button>");
            out.print("<div class='dropdown-content'>");
            out.print("<a href='LogoutServlet'>Logout</a>");
            out.println("<a href='MyOrders'>My Orders</a>");

            out.print("</div>");
            out.print("</div>");
        } else {
            out.print("<a href='Login.html'><i class='fa-solid fa-user'></i></a>");
        }
      	out.print("</div>");
        out.print("<div class='iconCart'>");
        out.print("<a href='DisplayCart'><i class='fa-solid fa-cart-shopping'></i></a>");
        out.print("<div class='totalQuantity'>"+ totalCartQuantity +"</div>");
        out.print("</div>");
        out.print("</div>");
		out.print("<div class='listProduct'>");
		for(Product p : al)
		{
			
			out.print("<div class='product-card' data-name='" + p.getProductName().toLowerCase() +"'>");
			out.print("<a href='ProductDetails?id="+p.getProductId()+"'>");
			out.print("<img src='idols/"+p.getProductImageUrl()+"'>");
			out.print("</a>");
			out.print("<h3>"+p.getProductName()+"</h3>");
			out.print("<p>Price: &#8377;"+p.getProductPrice()+"</p>");
			out.print("</div>");
			
		}
		
		out.print("</div>");
		out.print("</div>");
		out.print("<div id='footer-container'></div>");
		out.print("<script src='Script.js'></script>");
		out.print("<script>");
		out.print("document.getElementById('search-box').addEventListener('keyup', function() {");
        out.print("   let searchValue = this.value.toLowerCase();");
        out.print("    let products = document.querySelectorAll('.product-card');");
        out.print("    products.forEach(product => {");
        out.print("        let name = product.getAttribute('data-name');");
        out.print("       if (name.includes(searchValue)) {");
        out.print("           product.style.display = 'block';");
        out.print("       } else {");
        out.print("           product.style.display = 'none';");
        out.print("       }");
        out.print("   });");
        out.print("});");
        

		out.print("</script>");
		out.print("</body>");
		out.print("</html>");
		out.close();
	}
}
