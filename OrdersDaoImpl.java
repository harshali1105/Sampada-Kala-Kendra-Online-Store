package com.skk.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.skk.dao.OrdersDao;
import com.skk.pojo.Cart;
import com.skk.pojo.Orders;
import com.skk.pojo.SignUp;
import com.skk.utility.DBUtility;


public class OrdersDaoImpl implements OrdersDao{
	String productName, updatePaymentStatus, productNameFromCartQuery, totalBillQuery, placeOrderQuery, addOrderItemsquery, showOrdersByCustomerEmailId, orderQuery, deleteOrderQuery, insertQuery, orderItemQuery;
	Connection conn;
	String orderDate = new Date().toString();
	PreparedStatement ps;
	Statement st;
	ResultSet rs;
	int row;
	double totalBill=0;
	Orders o = new Orders();
	ProductDaoImpl pdi = new ProductDaoImpl();
	List<Orders> al = new ArrayList<Orders>();
		
	public boolean placeOrder(Orders o, List<Cart> cartItems) {
	    boolean result = false;
	    insertQuery = "INSERT INTO Orders (uEmailId, totalAmount, paymentMethod, orderDate, orderStatus) VALUES (?, ?, ?, ?, ?)";
	    orderItemQuery = "INSERT INTO OrderItems (orderId, pId, pName, pQty, pPrice, pSize, subtotal, pImageUrl) values (?,?,?,?,?,?,?,?)";
	    try {
	    	Connection conn = DBUtility.establishConnection(); 	    

	    	PreparedStatement ps = conn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, o.getUserEmailId());
	        ps.setDouble(2, o.getTotalBill()); 
	        ps.setString(3, o.getPaymentMethod());
	        ps.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
	        ps.setString(5, "Pending");

	        int rows = ps.executeUpdate();
	        if (rows > 0) {
	        	rs = ps.getGeneratedKeys();
//	        	ps = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

	            if (rs.next()) {
	                int orderId = rs.getInt(1);

	                // Now insert each cart item into OrderItems
	                PreparedStatement psOrderItems = conn.prepareStatement(orderItemQuery);

	                for (Cart c : cartItems) {
	                    double subtotal = c.getProductQty() * c.getProductPrice();
	                    psOrderItems.setInt(1, orderId);
	                    psOrderItems.setInt(2, c.getProductId());
	                    psOrderItems.setString(3, c.getProductName());
	                    psOrderItems.setInt(4, c.getProductQty());
	                    psOrderItems.setDouble(5, c.getProductPrice());
	                    psOrderItems.setDouble(6, c.getProductSize());
	                    psOrderItems.setDouble(7, subtotal);
	                    psOrderItems.setString(8, c.getProductImageUrl());
	                    psOrderItems.addBatch();
	                }

	                psOrderItems.executeBatch();
	               result=true;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}


	@Override
	public List<Orders> showOrdersbyemail(String userEmailId) {
		showOrdersByCustomerEmailId = "select * from Orders where uEmailId=?";
		
		List<Orders> al = new ArrayList<>();
		
		try {
			conn = DBUtility.establishConnection();
	        ps = conn.prepareStatement(showOrdersByCustomerEmailId);
	        ps.setString(1, userEmailId);  // ✅ Filter by user email
	        rs = ps.executeQuery();
			while (rs.next()) {
	            Orders o = new Orders();
	            o.setOrderId(rs.getInt("orderId"));
	            o.setUserEmailId(rs.getString("uEmailId"));
	            o.setTotalBill(rs.getDouble("totalAmount"));
	            o.setPaymentMethod(rs.getString("paymentMethod"));
	            o.setOrderDate(rs.getTimestamp("orderDate"));
	            o.setOrderStatus(rs.getString("orderStatus"));
	            al.add(o);
	        }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return al;
	}

	@Override
	public boolean cancelOrder(String userEmailId) {
	    String cancelOrderQuery = "UPDATE Orders SET orderStatus = 'Cancelled' WHERE uEmailId = ? AND orderStatus = 'Pending'";

	    try {
	        conn = DBUtility.establishConnection();
	        ps = conn.prepareStatement(cancelOrderQuery);
	        ps.setString(1, userEmailId);
	        row = ps.executeUpdate();

	        return row > 0;
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	

	


	@Override
	public boolean updatePaymentStatus(String payment_status, int orderId) {
		updatePaymentStatus = "update Orders set payment_status = ? where orderId = ?";
		 try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(updatePaymentStatus);
			ps.setString(1, payment_status);
			ps.setInt(2, orderId);
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
	public List<Orders> showOrders() {
		showOrdersByCustomerEmailId = "select * from Orders";
		
		List<Orders> al = new ArrayList<>();
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(showOrdersByCustomerEmailId);
			rs = ps.executeQuery();
	
			while (rs.next()) {
	            Orders o = new Orders();
	            o.setOrderId(rs.getInt("orderId"));
	            o.setUserEmailId(rs.getString("uEmailId"));
	            o.setTotalBill(rs.getDouble("totalAmount"));
	            o.setPaymentMethod(rs.getString("paymentMethod"));
	            o.setOrderDate(rs.getTimestamp("orderDate"));
	            o.setOrderStatus(rs.getString("orderStatus"));
	            al.add(o);
	        }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return al;
	}

	
	@Override
	public boolean deleteOrder(int orderId) {
		deleteOrderQuery = "delete from Orders where orderId=?";
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(deleteOrderQuery);
			ps.setInt(1, orderId);
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
	public boolean updateOrderStatus(int orderId, String status) {
	    boolean flag = false;
	    String query = "UPDATE Orders SET orderStatus = ? WHERE orderId = ?";

	    try (Connection con = DBUtility.establishConnection();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setString(1, status);
	        ps.setInt(2, orderId);

	        int rows = ps.executeUpdate();
	        if (rows > 0) {
	            flag = true;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return flag;
	}


	
	public SignUp getUserByEmail(String userEmailId) {
	    SignUp user = null;
	    try {
	        Connection con = DBUtility.establishConnection();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM Users WHERE uEmailId = ?");
	        ps.setString(1, userEmailId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            user = new SignUp();
	            user.setUserFname(rs.getString("uFname"));
	            user.setUserLname(rs.getString("uLname"));
	            user.setUserContactNo(rs.getLong("uContactNo"));
	            user.setUserLocation(rs.getString("uLocation"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return user;
	}


	
}
