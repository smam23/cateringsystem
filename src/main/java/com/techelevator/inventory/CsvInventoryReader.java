package com.techelevator.inventory;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.items.Appetizers;
import com.techelevator.items.Beverages;
import com.techelevator.items.Desserts;
import com.techelevator.items.Entrees;
import com.techelevator.items.Item;
import com.techelevator.items.Product;

public class CsvInventoryReader implements InventoryReader {

	private String fileName;

	public CsvInventoryReader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Map<String, Product> read() throws Exception {

		Map<String, Product> inventoryMap = new LinkedHashMap<String, Product>();

		File file = new File(fileName);

		try (Scanner fileScanner = new Scanner(file)) {

			while (fileScanner.hasNextLine()) {
				String fileLine = fileScanner.nextLine();
				String[] linePieces = fileLine.split("\\|");
				inventoryMap.put(linePieces[0], buildProductFromPieces(linePieces));
			}

		} catch (Exception e) {
			System.out.println("File not found.");

		}
		return inventoryMap;
	}

	private Product buildProductFromPieces(String[] linePieces) {

		Item item = null;
		double price = Double.parseDouble(linePieces[2]);

		switch (linePieces[3]) {
		case "B":
			item = new Beverages("Beverage", linePieces[0], linePieces[1], price);
			break;
		case "A":
			item = new Appetizers("Appetizer", linePieces[0], linePieces[1], price);
			break;
		case "E":
			item = new Entrees("Entree", linePieces[0], linePieces[1], price);
			break;
		case "D":
			item = new Desserts("Dessert", linePieces[0], linePieces[1], price);
			break;
		}

		return new Product(item, 50);
	}

}
