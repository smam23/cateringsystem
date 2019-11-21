package com.techelevator.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.techelevator.items.Item;
import com.techelevator.items.Product;

public class Menu {

	private Scanner in = new Scanner(System.in);

	public String getUserInputtedFileName() {
		System.out.println();
		System.out.print("Path to the inventory file: ");
		String fileName = in.nextLine();
		return fileName;
	}

	public double getUserAddedMoney() {
		double userAddedMoney = 0;

		while (userAddedMoney == 0) {
			System.out.println();
			System.out.print("How much money would you like to add: ");
			try {
				userAddedMoney = in.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Not a valid amount input!");
				userAddedMoney = 0;
			} finally {
				in.nextLine();
			}
		}
		return userAddedMoney;
	}

	public String getUserProductOrder() {
		System.out.println();
		System.out.println("What product would you like to order: ");
		String userProductOrder = in.nextLine().toUpperCase();
		return userProductOrder;
	}

	public int getUserProductQuantity() {
		int userProductQuantity = 0;

		while (userProductQuantity == 0) {
			System.out.println();
			System.out.println("How many would you like to order: ");
			try {
				userProductQuantity = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Not a valid Quantity -_-");
				userProductQuantity = 0;
			} finally {
				in.nextLine();
			}
		}
		return userProductQuantity;
	}

	public void displayUserMessage(String message) {
		System.out.println(message);
		System.out.flush();
	}

	public void displayAllItems(List<Product> inventoryList) {

		for (int i = 0; i < inventoryList.size(); i++) {

			Product product = inventoryList.get(i);
			Item item = product.getItem();

			System.out.printf("%-5s %-20s $%-10.2f %-12s%n", "(" + item.getItemId() + ")", item.getName(),
					item.getPrice(), product.getQuantity());
		}

		System.out.println();
		System.out.flush();
	}

	public String getSelectionFromUser(String[] options) {

		String selectedOption = null;

		while (selectedOption == null) {

			displayUserOptions(options);

			try {
				int userChoice = in.nextInt();
				if (userChoice - 1 >= options.length || userChoice < 1) {
					displayUserMessage("Please select a valid option!");
				} else {
					selectedOption = options[userChoice - 1];
				}
			} catch (InputMismatchException e) {
				System.out.println("Please select a valid option");
			} finally {
				in.nextLine();
			}
		}
		return selectedOption;
	}

	public void displayUserOptions(String[] options) {
		for (int i = 0; i < options.length; i++) {
			System.out.println("(" + (i + 1) + ") " + options[i]);
		}
		System.out.print("Choice: ");
		System.out.flush();
	}

}
