package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
//			AccountDTO acDTO = new AccountDTO(account.getId(), 
//					account.getUsername(), account.getFirstName(), account.getLastName(),
//					account.getEmail(), account.getPassword(), account.getRole().toString(),
//					"HN");
			listAccountDTO.add(acDTO);
		}
		return listAccountDTO;
	}
	
	@GetMapping("/{id}")
	public AccountDTO getAccountById(@PathVariable(name = "id") int id) {
		Account account = accountService.getAccountById(id);
		AccountDTO acDTO = new AccountDTO(account.getId(), 
				account.getUsername(), account.getFirstName(), account.getLastName(),
				account.getEmail(), account.getPassword(), account.getRole().toString(),
				account.getAddress().toString());
		return acDTO;
	}
	
	@GetMapping("/info/{username}")
	public ResponseEntity<?> getAccountByUsername(@PathVariable(name = "username") String username) {
		Account account = accountService.getAccountByUsername(username);
		if (account != null) {
			AccountDTO acDTO = new AccountDTO(account.getId(), 
					account.getUsername(), account.getFirstName(), account.getLastName(),
					account.getEmail(), account.getPassword(), account.getRole().toString(),
					account.getAddress().toString());
			return ResponseEntity.status(HttpStatus.OK).body(acDTO);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Found");
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> addNewAccount(@RequestBody Account ac) {
		accountService.addNewAccount(ac);
		return ResponseEntity.status(HttpStatus.OK).body("Add new account successfully");
	}
}
