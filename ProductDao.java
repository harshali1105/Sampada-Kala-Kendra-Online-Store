package com.skk.dao;
import java.util.List;

import com.skk.pojo.Product;

public interface ProductDao {
	int AddProduct(Product p);
	List<Product> ViewProduct();
	int deleteProduct(int productId);
	int updateProduct(Product p);
	Product getProductById(int productId);
	Product displayProduct(String productName);
}
