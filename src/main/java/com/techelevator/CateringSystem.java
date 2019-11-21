package com.techelevator;

import java.util.List;

import com.techelevator.inventory.Inventory;
import com.techelevator.items.Product;

public class CateringSystem {

	private Inventory inventory;

	public CateringSystem(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<Product> getItems() {
		return inventory.getListOfProducts();
	}
}