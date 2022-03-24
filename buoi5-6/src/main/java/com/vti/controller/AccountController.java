package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.service.AccountService;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/accounts")
public class AccountController {
	
//	@GetMapping
//	public String getListAccounts() {
//		return "Demo API get list accounts";
//	}
	
	private IAccountService acService;
	
	public AccountController() {
		acService = new AccountService();
	}
	@GetMapping
	public List<AccountDTO> getListAccounts() {
		
		List<Account> accounts = acService.getListAccounts();
		
		List<AccountDTO> listAccountDTO = new ArrayList();
		
		for (Account account : accounts) {
			AccountDTO acDTO = new AccountDTO(account.getId(),
					account.getUsername(), account.getFirstName(),
					account.getLastName(), account.getRole().toString(), account.getAddress().toString()
			);
			listAccountDTO.add(acDTO);
		}
		
		return listAccountDTO;
	}
}
