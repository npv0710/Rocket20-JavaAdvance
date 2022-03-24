package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Account;


public interface IAccountService {
	List<Account> getListAccounts();
	
	Page<Account> getPagingAccounts(Pageable pageable);
	
	boolean isAccountExists(int id);
	
	public boolean isAccountExistsByUsername(String username);
	
	public Account getAccountByUsername(String username);
	
	public void createAccount(Account ac);

	
}
