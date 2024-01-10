package com.jdc.shop.model;

import java.util.ArrayList;
import java.util.List;

import com.jdc.shop.model.entity.Product;
import com.jdc.shop.model.entity.SaleItem;

class ShoppingCartImpl implements ShoppingCart {

	private static final long serialVersionUID = 1L;

	private List<SaleItem> items;

	public ShoppingCartImpl() {
		items = new ArrayList<SaleItem>();
	}

	@Override
	public void add(Product product) {
		
		var item = findItemByProduct(product.getId());
		
		if( null == item ) {
			item = new SaleItem();
			item.setProduct(product);
			items.add(item);
		}
		item.addOne();
		
	}

	@Override
	public int itemCount() {
		int count = 0;
		for (SaleItem item : items) {
			count += item.getCount();
		}
		return count;
	}

	@Override
	public void clear() {
		items.clear();
	}

	@Override
	public int total() {
		int total = 0;
		for(SaleItem item : items) {
			total += item.getTotal();
		}
		return total;
	}
	
	private SaleItem findItemByProduct(int productId) {
		
		for(SaleItem item: items) {
			if(item.getProduct().getId() == productId) {
				return item;
			}
		}
		
		return null;
		
	}

	@Override
	public List<SaleItem> items() {
		return new ArrayList<SaleItem>(items);
	}

	@Override
	public void changeItemCount(boolean plus, int productId) {
		
		var item = findItemByProduct(productId);
		
		if(null != item) {
			item.changeCount(plus);
			if(item.getCount() == 0) {
				items.remove(item);
			}
		}
		
	}

}
