package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entity.Account;
import com.vti.entity.AccountDTO;
import com.vti.entity.AddressDTO;
import com.vti.service.AccountService;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/accounts")
public class AccountController {
	
//	@GetMapping()
//	public String getListAccounts() {
//		return "Get list accounts API";
//	}
	private IAccountService accountService;
	
	public AccountController() {
		accountService = new AccountService();
	}
	
	@GetMapping()
	public List<AccountDTO> getListAccounts() {
		List<Account> accounts = accountService.getListAccounts();
		List<AccountDTO> listAccountDTO = new ArrayList();
		for (Account account : accounts) {
			AccountDTO acDTO = new AccountDTO(account.getId(), 
					account.getUsername(), account.getFirstName(), account.getLastName(),
					account.getEmail(), account.getPassword(), account.getRole().toString(),
					account.getAddress().toString());
			listAccountDTO.add(acDTO);
		}
		return listAccountDTO;
	}
}
