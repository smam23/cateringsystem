package com.techelevator.till;

public class AccountBalance {

	private double balance = 0;

	public AccountBalance() {
	}

	public double addMoney(double addedMoney) {
		if ((balance + addedMoney) <= 5000) {
			balance += addedMoney;
			return balance;
		}
		System.out.println("Balance cannot exceed $5,000");
		System.out.printf("%s %.2f", "Current balance is: $", balance);
		return balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}