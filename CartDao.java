package com.skk.dao;

import java.util.List;

import com.skk.pojo.Cart;
import com.skk.pojo.Product;

public interface CartDao{
	boolean addToCart(Cart c);
	
	boolean deleteCart(int cartId);
	
	boolean deleteCart(String userEmailId);
	
	List<Cart> showCart();
	
	List<Cart> showCart(String userEmailId);
	
	boolean updateCart(int cartId, int newQty, double updatedSubtotal);
	
	Cart getCartById(int cartId);
	
	int getTotalCartQuantity(String userEmailId);
	boolean clearCart(String userEmail);
	
	double calculateTotal(String userEmailId);
}
