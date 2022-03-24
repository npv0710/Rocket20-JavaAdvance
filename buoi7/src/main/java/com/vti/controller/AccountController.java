package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.service.AccountService;


@RestController
@RequestMapping(value = "api/accounts")
public class AccountController {
	@Autowired
	private AccountService acService;
	
	@SuppressWarnings("unused")
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public List<AccountDTO> getListAccounts() {
		List<Account> lsAc = acService.getListAccounts();
		List<AccountDTO> lsAcDTO = modelMapper.map(lsAc, new TypeToken< List<AccountDTO> >(){}.getType());
		return lsAcDTO;
	}
	
}
