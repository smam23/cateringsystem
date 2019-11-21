package com.techelevator.items;

public class Item {

	private String itemType;
	private String itemId;
	private String name;
	private double price;

	public Item(String itemType, String itemId, String name, double price) {
		this.itemType = itemType;
		this.itemId = itemId;
		this.name = name;
		this.price = price;
	}

	public String getItemType() {
		return itemType;
	}

	public String getItemId() {
		return itemId;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
}
