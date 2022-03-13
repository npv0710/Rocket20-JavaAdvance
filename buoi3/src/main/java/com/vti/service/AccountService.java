package com.vti.service;

import java.util.List;

import com.vti.entity.Account;
import com.vti.repository.AccountRepository;
import com.vti.repository.IAccountRepository;

public class AccountService implements IAccountService{
	private IAccountRepository accountRepository;
	
	public AccountService() {
		accountRepository = new AccountRepository();
	}

	@Override
	public List<Account> getListAccounts() {
		return accountRepository.getListAccounts();
	}

}
