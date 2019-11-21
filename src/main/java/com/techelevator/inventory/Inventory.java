package com.techelevator.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.techelevator.items.Product;
import com.techelevator.view.Menu;

public class Inventory {

	private InventoryReader reader;

	private Map<String, Product> inventoryMap;

	public Inventory(InventoryReader reader) throws Exception {
		this.reader = reader;
		loadInventory();
	}

	public void loadInventory() throws Exception {
		inventoryMap = reader.read();
	}

	public List<Product> getListOfProducts() {
		return new ArrayList<Product>(inventoryMap.values());
	}

}