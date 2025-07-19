package com.skk.dao;

import java.util.List;

import com.skk.pojo.Cart;
import com.skk.pojo.Orders;
import com.skk.pojo.SignUp;

public interface OrdersDao {
	
	List<Orders> showOrders();
	
	List<Orders> showOrdersbyemail(String userEmailId);
	
	boolean deleteOrder(int orderId);
	
	boolean cancelOrder(String userEmailId);
		
	boolean updateOrderStatus(int orderId, String status);
	
	boolean updatePaymentStatus(String payment_satatus, int orderId);
	
	boolean placeOrder(Orders o, List<Cart> cartItems);
	
	public SignUp getUserByEmail(String userEmailId);
}
