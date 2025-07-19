package com.skk.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.skk.dao.OrderItemsDao;
import com.skk.pojo.OrderItems;
import com.skk.utility.DBUtility;

public class OrderItemsDaoImpl implements OrderItemsDao{
	PreparedStatement ps;
    public List<OrderItems> getItemsByOrderId(int orderId) {
        List<OrderItems> al = new ArrayList<>();
        String query = "SELECT * FROM OrderItems WHERE orderId = ?";

        try {
        	Connection con = DBUtility.establishConnection();
        
              ps = con.prepareStatement(query);
            
              ps.setInt(1, orderId);
              ResultSet rs = ps.executeQuery();

              while (rs.next()) {
                OrderItems item = new OrderItems();
                item.setOrderItemId(rs.getInt("orderItemId"));
                item.setOrderId(rs.getInt("orderId"));
                item.setProductId(rs.getInt("pId"));
                item.setProductName(rs.getString("pName"));
                item.setProductQty(rs.getInt("pQty"));
                item.setProductPrice(rs.getDouble("pPrice"));
                item.setProductSize(rs.getDouble("pSize"));
                item.setProductSubtotal(rs.getDouble("subtotal"));
                item.setProductImage(rs.getString("pImageUrl"));
                al.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return al;
    }
}
