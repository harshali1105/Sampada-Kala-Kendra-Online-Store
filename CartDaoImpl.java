package com.skk.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skk.dao.CartDao;
import com.skk.pojo.Cart;
import com.skk.pojo.Employee;
import com.skk.pojo.Product;
import com.skk.utility.DBUtility;


public class CartDaoImpl implements CartDao{
	String insertQuery, deleteCartQuery, displayCartQuery, deleteCartByCartIdQuery, updateQuery, updateByIdQuery, quantityQuery;
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	Statement st;
	int row;
	ProductDaoImpl pdi = new ProductDaoImpl();
	Cart c = new Cart();
	
	@Override
	public boolean addToCart(Cart c) {
		
		insertQuery = "insert into Cart(pId, pName, pPrice, pSize, uEmailId, pQty, pImageUrl, subtotal) values (?,?,?,?,?,?,?,?)";
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(insertQuery);
			Product p = pdi.displayProduct(c.getProductName());
			if(p!=null)
			{
				double subtotal = c.getProductPrice() * c.getProductQty();
				
				ps.setInt(1, p.getProductId());
				ps.setString(2, p.getProductName());
				ps.setDouble(3, c.getProductPrice());
				ps.setDouble(4, c.getProductSize());			
				ps.setString(5, c.getUserEmailId());
				ps.setInt(6, c.getProductQty());
				ps.setString(7, p.getProductImageUrl());
				ps.setDouble(8, subtotal);
				
				row = ps.executeUpdate();
				if(row>0)
				{
					return true;
				}
				else
				{
					System.out.println("Food Not Found");
					return false;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCart(int cartId) {
		deleteCartByCartIdQuery = "delete from Cart where cartId=?";
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(deleteCartByCartIdQuery);
			ps.setInt(1, cartId);
			row = ps.executeUpdate();
			if(row>0)
			{
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public boolean deleteCart(String userEmailId) {
		deleteCartQuery = "delete from Cart where uEmailId=?";
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(deleteCartQuery);
			ps.setString(1, userEmailId);
			row = ps.executeUpdate();
			
			if(row>0)
			{
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Cart> showCart() {
		displayCartQuery = "select * from Cart";
		List<Cart> al = new ArrayList<Cart>();
		
		try {
			conn = DBUtility.establishConnection();
			st = conn.createStatement();
			rs = st.executeQuery(displayCartQuery);
			while(rs.next())
			{
				Cart c = new Cart();
				
				c.setCartId(rs.getInt("cartId"));
				c.setProductId(rs.getInt("pId"));
				c.setProductName(rs.getString("pName"));
				c.setProductPrice(rs.getDouble("pPrice"));
				c.setProductSize(rs.getDouble("pSize"));
				c.setUserEmailId(rs.getString("userEmailId"));
				c.setProductQty(rs.getInt("pQty"));
				c.setProductImageUrl(rs.getString("pImageUrl"));
				c.setProductSubtotal(rs.getDouble("subtotal"));
				al.add(c);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}

	@Override
	public List<Cart> showCart(String userEmailId) {
		displayCartQuery = "select * from Cart where uEmailId=?";
		List<Cart> al = new ArrayList<Cart>();
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(displayCartQuery);
			ps.setString(1, userEmailId);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Cart c = new Cart();
				
				c.setCartId(rs.getInt("cartId"));
				c.setProductId(rs.getInt("pId"));
				c.setProductName(rs.getString("pName"));
				c.setProductPrice(rs.getDouble("pPrice"));
				c.setProductSize(rs.getDouble("pSize"));
				c.setProductQty(rs.getInt("pQty"));
				c.setProductImageUrl(rs.getString("pImageUrl"));
				c.setProductSubtotal(rs.getDouble("subtotal"));
				al.add(c);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}
	
	@Override
	public boolean updateCart(int cartId, int newQty, double updatedSubtotal) {
		updateQuery = "update Cart set pQty=?, subtotal=? where cartId=?";
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(updateQuery);
			ps.setInt(1, newQty);
			ps.setDouble(2, updatedSubtotal);
			ps.setInt(3, cartId);
			
			row = ps.executeUpdate();
			if(row>0)
			{
				return true;
			}
			conn.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Cart getCartById(int cartId) {
		updateByIdQuery = "select * from Cart where cartId=?";
		Cart c = null;
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(updateByIdQuery);
			ps.setInt(1, cartId);
			rs = ps.executeQuery();
			if(rs.next())
			{
				c = new Cart();
				c.setCartId(rs.getInt("cartId"));
				c.setProductId(rs.getInt("pId"));
				c.setProductName(rs.getString("pName"));
				c.setProductPrice(rs.getDouble("pPrice"));
				c.setProductSize(rs.getDouble("pSize"));
				c.setUserEmailId(rs.getString("userEmailId"));
				c.setProductQty(rs.getInt("pQty"));
				c.setProductImageUrl(rs.getString("pImageUrl"));	
				c.setProductSubtotal(rs.getDouble("subtotal"));
			}
			conn.close();
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return c;
	}

	@Override
	public int getTotalCartQuantity(String userEmailId) {
		int totalQuantity = 0;
		quantityQuery = "SELECT SUM(pQty) FROM Cart WHERE uEmailId=?";
	    try {
	        Connection conn = DBUtility.establishConnection();
	        ps = conn.prepareStatement(quantityQuery);
	        ps.setString(1, userEmailId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            totalQuantity = rs.getInt(1);
	        }
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return totalQuantity;
	}
	
	@Override
	public boolean clearCart(String userEmail) {
	    String query = "DELETE FROM Cart WHERE uEmailId = ?";
	    try (Connection con = DBUtility.establishConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setString(1, userEmail);
	        int rows = ps.executeUpdate();
	        return rows > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public double calculateTotal(String userEmailId) {
	    double total = 0.0;
	    String totalquery = "SELECT SUM(subtotal) FROM Cart WHERE uEmailId = ?";
	    
	    try {
	    	Connection conn = DBUtility.establishConnection();
	    
	         PreparedStatement ps = conn.prepareStatement(totalquery);
	        
	        ps.setString(1, userEmailId);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            total = rs.getDouble(1);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return total;
	}


}
