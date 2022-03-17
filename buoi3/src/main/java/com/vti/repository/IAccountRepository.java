package com.vti.repository;

import java.util.List;

import com.vti.entity.Account;

public interface IAccountRepository {

	List<Account> getListAccounts();

	Account getAccountById(int id);

	Account getAccountByUsername(String username);

	void addNewAccount(Account ac);
	
}
