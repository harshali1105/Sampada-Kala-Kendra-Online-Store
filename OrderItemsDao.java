package com.skk.dao;

import java.util.List;

import com.skk.pojo.OrderItems;

public interface OrderItemsDao {
	List<OrderItems> getItemsByOrderId(int orderId);
}
