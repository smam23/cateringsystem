package com.techelevator.inventory;

import java.util.Map;

import com.techelevator.items.Product;

public interface InventoryReader {

	public Map<String, Product> read() throws Exception;

}
