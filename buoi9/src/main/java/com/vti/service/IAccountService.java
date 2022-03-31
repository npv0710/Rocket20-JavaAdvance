package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;


public interface IAccountService {
	List<Account> getListAccounts();
	
	Page<Account> getListAccountsPaging(Pageable pageable, String search, AccountFilterForm acFF);
	
	boolean isAccountExists(int id);
	
	boolean isAccountExistsByUsername(String username);
	
	Account getAccountByUsername(String username);
	
	void createAccount(Account ac);

	void deleteAccount(int id);

	void updateAccount(AccountDTO acDTO);

	void deleteMultipleAccounts(List<Integer> ids);

}
