package com.skk.pojo;

public class Cart {
	private int cartId, productId, productQty;
	private String userEmailId, productName, productImageUrl;
	private double productSize, productPrice, productSubtotal;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProductQty() {
		return productQty;
	}
	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductSize() {
		return productSize;
	}
	public void setProductSize(double productSize) {
		this.productSize = productSize;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	public double getProductSubtotal() {
		return productSubtotal;
	}
	public void setProductSubtotal(double productSubtotal) {
		this.productSubtotal = productSubtotal;
	}
}
