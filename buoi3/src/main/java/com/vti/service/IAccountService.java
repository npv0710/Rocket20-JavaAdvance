package com.vti.service;

import java.util.List;

import com.vti.entity.Account;

public interface IAccountService {
	List<Account> getListAccounts();
	Account getAccountById(int id);
	Account getAccountByUsername(String username);
	void addNewAccount(Account ac);
	List<Account> getListAccountsWithSearch(String search);
}
