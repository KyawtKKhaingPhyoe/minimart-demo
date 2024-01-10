package com.jdc.shop.model;

import java.io.Serializable;
import java.util.List;

import com.jdc.shop.model.entity.Product;
import com.jdc.shop.model.entity.SaleItem;

public interface ShoppingCart extends Serializable {
	
	void add(Product product);
	
	int itemCount();
	
	void clear();
	
	int total();
	
	List<SaleItem> items();
	
	void changeItemCount(boolean plus, int productId);
	
	public static ShoppingCart generate() {
		return new ShoppingCartImpl();
	}
	
}
