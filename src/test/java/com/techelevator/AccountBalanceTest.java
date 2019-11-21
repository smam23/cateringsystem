package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.till.AccountBalance;

public class AccountBalanceTest {

	private AccountBalance balance;

	@Before
	public void setup() {
		balance = new AccountBalance();
	}

	@Test
	public void user_successfully_adds_money_to_balance() {
		balance.addMoney(300);
		double result = balance.addMoney(200);
		Assert.assertEquals(500, result, 0);
	}

	@Test
	public void user_adds_money_that_exceeds_5000_declines_add_money() {
		balance.addMoney(4990);
		double result = balance.addMoney(11);
		Assert.assertEquals(4990, result, 0);
	}
}
