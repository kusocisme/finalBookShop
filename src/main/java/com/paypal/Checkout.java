package com.paypal;


public class Checkout {
	private  String productName;
	private float subTotal;
	private float shipping;
	private float tax;
	private float total;
	
	
	public Checkout(String productName, float subTotal, float shipping, float tax, float total) {
		super();
		
		this.productName = productName;
		this.subTotal = (subTotal);
		this.shipping = (shipping);
		this.tax = (tax);
		this.total = (total);
		
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSubTotal() {
		return String.format("%.2f", subTotal);
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	public String getShipping() {
		return String.format("%.2f", shipping);
	}
	public void setShipping(float shipping) {
		this.shipping = shipping;
	}
	public String getTax() {
		return String.format("%.2f", tax);
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public String getTotal() {
		return String.format("%.2f", total);
	}
	public void setTotal(float total) {
		this.total = total;
	}
}
