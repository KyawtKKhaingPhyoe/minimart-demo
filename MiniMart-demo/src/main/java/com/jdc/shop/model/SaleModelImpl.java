package com.jdc.shop.model;

import java.util.ArrayList;
import java.util.List;

import com.jdc.shop.model.entity.Voucher;

public class SaleModelImpl implements SaleModel{
	
	private List<Voucher> vouchers;
	
	public SaleModelImpl() {
		vouchers = new ArrayList<Voucher>();
	}

	@Override
	public List<Voucher> getSaleHistory() {
		
		return new ArrayList<Voucher>(vouchers);
	}

}
