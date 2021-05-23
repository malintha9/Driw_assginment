package com.products.priceengine.model;

import java.util.Map;

public class Products {

	private int quantity;

	private Map<String, Double> products;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Map<String, Double> getProducts() {
		return products;
	}

	public void setProducts(Map<String, Double> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Products [quantity=" + quantity + ", products=" + products + "]";
	}
	
}
