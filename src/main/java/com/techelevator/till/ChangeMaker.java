package com.techelevator.till;

public class ChangeMaker {

	public String MakeChange(double remainingBalance) {
		int twenties = 0;
		int tens = 0;
		int fives = 0;
		int ones = 0;
		int quarters = 0;
		int dimes = 0;
		int nickels = 0;

		while (remainingBalance >= 20) {
			remainingBalance -= 20;
			twenties++;
		}
		while (remainingBalance >= 10) {
			remainingBalance -= 10;
			tens++;
		}
		while (remainingBalance >= 5) {
			remainingBalance -= 5;
			fives++;
		}
		while (remainingBalance >= 1) {
			remainingBalance -= 1;
			ones++;
		}
		while (remainingBalance >= .25) {
			remainingBalance -= .25;
			quarters++;
		}
		while (remainingBalance >= .10) {
			remainingBalance -= .10;
			dimes++;
		}
		while (remainingBalance >= .05) {
			remainingBalance -= .05;
			nickels++;
		}
		String change = "Your change is: \n" + twenties + " - $20's\n" + tens + " - $10's\n" + fives + " - $5's\n"
				+ ones + " - $1's\n" + quarters + " - quarter's\n" + dimes + " - dime's\n" + nickels + " - nickel's\n";
		return change;
	}

}
// THIS NEEDED TO BE TESTED
// THIS COULD BE ONE WHILE LOOP WITH MULTIPLE CLASSES