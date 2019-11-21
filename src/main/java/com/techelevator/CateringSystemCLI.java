package com.techelevator;

import java.util.List;

import com.techelevator.inventory.CsvInventoryReader;
import com.techelevator.inventory.FileLogWriter;
import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.InventoryReader;
import com.techelevator.items.Item;
import com.techelevator.items.Product;
import com.techelevator.till.AccountBalance;
import com.techelevator.till.ChangeMaker;
import com.techelevator.till.ShoppingCart;
import com.techelevator.view.Menu;

public class CateringSystemCLI {
	// main menu display items
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Catering Items";
	private static final String MAIN_MENU_OPTION_ORDER = "Order";
	private static final String MAIN_MENU_OPTION_QUIT = "Quit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_ORDER,
			MAIN_MENU_OPTION_QUIT };
	// order menu display items
	private static final String ORDER_MENU_OPTION_ADD_MONEY = "Add Money";
	private static final String ORDER_MENU_OPTION_SELECT_PRODUCTS = "Select Products";
	private static final String ORDER_MENU_OPTION_COMPLETE_TRANSACTION = "Complete Transaction";
	private static final String ORDER_MENU_OPTION_RETURN_MAIN_MENU = "Return to Main Menu";
	private static final String[] ORDER_MENU_OPTIONS = { ORDER_MENU_OPTION_ADD_MONEY, ORDER_MENU_OPTION_SELECT_PRODUCTS,
			ORDER_MENU_OPTION_COMPLETE_TRANSACTION, ORDER_MENU_OPTION_RETURN_MAIN_MENU };

	private Menu menu;
	private CateringSystem cateringSystem;
	private AccountBalance balance;
	private ShoppingCart shoppingCart;
	private ChangeMaker changeMaker;
	private FileLogWriter fileLogWriter;

	public CateringSystemCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws Exception {

		String fileName = menu.getUserInputtedFileName();
		InventoryReader inventoryReader = new CsvInventoryReader(fileName);
		Inventory inventory = new Inventory(inventoryReader);
		cateringSystem = new CateringSystem(inventory);
		balance = new AccountBalance();
		shoppingCart = new ShoppingCart();
		changeMaker = new ChangeMaker();
		fileLogWriter = new FileLogWriter();

		while (true) {
			String choice = (String) menu.getSelectionFromUser(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayCateringItems();
			} else if (choice.equals(MAIN_MENU_OPTION_ORDER)) {
				displayOrderOptions();
			} else if (choice.equals(MAIN_MENU_OPTION_QUIT)) {
				System.out.println("Thanks for ordering with us. Come back soon!");
				break;
			}
		}
	}

	private void displayCateringItems() {
		List<Product> inventoryList = cateringSystem.getItems();
		menu.displayAllItems(inventoryList);
	}

	private void displayOrderOptions() throws Exception {
		while (true) {
			System.out.printf("\n%s %.2f", "Current balance is: $", balance.getBalance());
			System.out.println();
			String choice = (String) menu.getSelectionFromUser(ORDER_MENU_OPTIONS);

			if (choice.equals(ORDER_MENU_OPTION_ADD_MONEY)) {
				double addedMoney = menu.getUserAddedMoney();
				balance.addMoney(addedMoney);
				fileLogWriter.write(" ADD MONEY: " + "$" + addedMoney + " $" + balance.getBalance());

			} else if (choice.equals(ORDER_MENU_OPTION_SELECT_PRODUCTS)) {
				displayCateringItems();
				String productId = menu.getUserProductOrder();
				while (true) {
					if (productId.equals("")) {
						System.out.println("Please enter a valid product option! :(");
						productId = menu.getUserProductOrder();
					} else {
						break;
					}
				}
				String quantityRequested = Integer.toString(menu.getUserProductQuantity());
				shoppingCart.updateInventory(balance, cateringSystem.getItems(), productId, quantityRequested);

			} else if (choice.equals(ORDER_MENU_OPTION_COMPLETE_TRANSACTION)) {
				List<Product> customerPurchaseLog = shoppingCart.getShoppingCart();
				System.out.println("Customer Purchase Report");
				System.out.printf("%-4s %-15s %-20s %-6s %-11s", "Qty", "Item Type", "Item Name", "Price",
						"Total Price");
				System.out.println();
				System.out.println("-------------------------------------------------------------");
				for (int i = 0; i < customerPurchaseLog.size(); i++) {

					Product productLog = customerPurchaseLog.get(i);
					Item item = productLog.getItem();

					System.out.printf("%-4d %-15s %-20s $%-5.2f $%-5.2f%n", productLog.getQuantity(),
							item.getItemType(), item.getName(), item.getPrice(),
							(item.getPrice() * productLog.getQuantity()));
				}
				System.out.println(changeMaker.MakeChange(balance.getBalance()));
				double balanceBeforeReset = balance.getBalance();
				balance.setBalance(0);
				fileLogWriter.write(" GIVE CHANGE: " + "$" + balanceBeforeReset + " $" + balance.getBalance());
			} else if (choice.equals(ORDER_MENU_OPTION_RETURN_MAIN_MENU)) {
				break;
			}

		}

	}

	public static void main(String[] args) throws Exception {
		Menu menu = new Menu();
		CateringSystemCLI cli = new CateringSystemCLI(menu);
		cli.run();
	}
}
