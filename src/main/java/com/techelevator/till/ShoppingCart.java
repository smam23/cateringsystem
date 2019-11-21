package com.techelevator.till;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.CateringSystemCLI;
import com.techelevator.inventory.FileLogWriter;
import com.techelevator.items.Item;
import com.techelevator.items.Product;

public class ShoppingCart {

	public List<Product> getShoppingCart() {
		return shoppingCart;
	}

	CateringSystemCLI cateringSystemCLI;
	FileLogWriter fileLogWriter;
	List<Product> shoppingCart = new ArrayList<Product>();

	public void updateInventory(AccountBalance balance, List<Product> items, String productId, String quantityRequested)
			throws Exception {

		fileLogWriter = new FileLogWriter();
		int intQuantityRequested = Integer.parseInt(quantityRequested);
		boolean productExists = false;
		for (Product product : items) {
			Item productItem = product.getItem();
			if (productItem.getItemId().equals(productId)) {
				productExists = true;
				if (product.getQuantity() >= intQuantityRequested) {
					double totalCostOfItems = intQuantityRequested * productItem.getPrice();
					if (totalCostOfItems <= balance.getBalance()) {
						int currentQuantity = product.getQuantity();
						product.setQuantity(currentQuantity - intQuantityRequested);
						double currentBalance = balance.getBalance();
						balance.setBalance(currentBalance - totalCostOfItems);
						Product productLog = new Product(productItem, intQuantityRequested);
						Product updatedProductLog = new Product(productItem, 50 - product.getQuantity());
						fileLogWriter
								.write(String.format("%s %s %s $%.2f $%.2f", quantityRequested, productItem.getName(),
										productItem.getItemId(), totalCostOfItems, balance.getBalance()));
						if (!shoppingCart.contains(productLog)) {
							shoppingCart.add(productLog);
						} else {
							shoppingCart.set(items.indexOf(productLog), updatedProductLog);
						}
					} else {
						System.out.println("Insufficient funds. Please add more money.");
						break;
					}
				} else {
					if (product.getQuantity() == 0) {
						System.out.println("Product is Sold Out.");
					} else {
						System.out.println("Insufficient Stock.");
						break;
					}
				}
			}

		}
		if (!productExists) {
			System.out.println("Product does not exist.");
		}
	}
}
