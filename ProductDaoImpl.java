package com.skk.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skk.dao.ProductDao;
import com.skk.pojo.Product;
import com.skk.utility.DBUtility;

public class ProductDaoImpl implements ProductDao
{
	ResultSet rs;
	String insertQuery, viewProductsQuery, deleteQuery, updateQuery, updateByIdQuery, displayProductByNameQuery;
	Connection conn;
	PreparedStatement ps;
	int status = 0;
	
	Product p = new Product();
	@Override
	public int AddProduct(Product p) {
		insertQuery = "insert into Product(pId, pName, pPrice,  pSize, pQty, pDescription,  pImageUrl) values (?,?,?,?,?,?,?)";
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(insertQuery);
			ps.setInt(1, p.getProductId());
			ps.setString(2, p.getProductName());
			ps.setDouble(3, p.getProductPrice());
			ps.setDouble(4, p.getProductSize());
			ps.setInt(5, p.getProductQty());
			ps.setString(6, p.getProductDescription());
			ps.setString(7, p.getProductImageUrl());
			
			status = ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public List<Product> ViewProduct() {
		viewProductsQuery = "select * from Product";
		List<Product> al = new ArrayList<Product>();
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(viewProductsQuery);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Product p = new Product();
				
				p.setProductId(rs.getInt("pId"));
				p.setProductName(rs.getString("pName"));
				p.setProductPrice(rs.getDouble("pPrice"));
				p.setProductSize(rs.getDouble("pSize"));
				p.setProductQty(rs.getInt("pQty"));
				p.setProductDescription(rs.getString("pDescription"));
				p.setProductImageUrl(rs.getString("pImageUrl"));
				al.add(p);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}

	@Override
	public int deleteProduct(int productId) {
		deleteQuery = "delete from Product where pId=?";
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(deleteQuery);
			ps.setInt(1, productId);
			status = ps.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int updateProduct(Product p) {
		updateQuery = "update Product set pName=?, pPrice=?, pSize=?, pQty=?, pDescription=?, pImageUrl=? where pId=?";
		
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(updateQuery);
			ps.setString(1, p.getProductName());
			ps.setDouble(2, p.getProductPrice());
			ps.setDouble(3, p.getProductSize());
			ps.setInt(4, p.getProductQty());
			ps.setString(5, p.getProductDescription());
			ps.setString(6, p.getProductImageUrl());
			ps.setInt(7, p.getProductId());	
			status = ps.executeUpdate();
			conn.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Product getProductById(int productId) {
		updateByIdQuery = "select * from Product where pId=?";
		Product p = null;
		try {
			conn = DBUtility.establishConnection();
			ps = conn.prepareStatement(updateByIdQuery);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			if(rs.next())
			{
				p = new Product();
				p.setProductId(rs.getInt(1));
				p.setProductName(rs.getString(2));
				p.setProductPrice(rs.getDouble(3));
				p.setProductSize(rs.getDouble(4));
				p.setProductQty(rs.getInt(5));
				p.setProductDescription(rs.getString(6));
				p.setProductImageUrl(rs.getString(7));				
			}
			conn.close();
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return p;
	}

	@Override
	public Product displayProduct(String productName) {
		displayProductByNameQuery = "select * from Product where pName=?";
		
		try {
			conn=DBUtility.establishConnection();
			ps = conn.prepareStatement(displayProductByNameQuery);
			ps.setString(1, productName);
			rs = ps.executeQuery();
			if(rs.next())
			{
				p.setProductId(rs.getInt("pId"));
				p.setProductName(rs.getString("pName"));
				p.setProductPrice(rs.getDouble("pPrice"));
				p.setProductSize(rs.getDouble("pSize"));
				p.setProductQty(rs.getInt("pQty"));
				p.setProductDescription(rs.getString("pDescription"));
				p.setProductImageUrl(rs.getString("pImageUrl"));			
			}
			else
			{
				System.out.println("Incorrect Credentials");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	
}