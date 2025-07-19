package com.skk.pojo;

public class OrderItems {
	private int orderItemId, orderId, productQty, productId;
	private String productName, productImage;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public double getProductSize() {
		return productSize;
	}
	public void setProductSize(double productSize) {
		this.productSize = productSize;
	}
	public double getProductSubtotal() {
		return productSubtotal;
	}
	public void setProductSubtotal(double productSubtotal) {
		this.productSubtotal = productSubtotal;
	}
	private double productPrice, productSize, productSubtotal;
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductQty() {
		return productQty;
	}
	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	
}
