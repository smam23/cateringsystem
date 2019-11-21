package com.techelevator.items;

public class Product {

	private Item item;
	private int quantity = 50;

	public Product(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isSoldOut() {
		return quantity < 1;
	}
}
