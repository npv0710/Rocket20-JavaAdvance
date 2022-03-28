package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;
import com.vti.service.AccountService;


@RestController
@RequestMapping(value = "api/accounts")
@CrossOrigin("/*")
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/paging")
	public Page<AccountDTO> getListAccountsPaging(Pageable pageable, 
			@RequestParam(value = "search", required = false) String search,
			AccountFilterForm acFF
		) {
		System.out.println("search: " + search);
		System.out.println("acount paging: ");
		System.out.println("acFF: " + acFF.toString());
		Page<Account> pageAccount = acService.getListAccountsPaging(pageable, search, acFF);
		
		List<AccountDTO> listAccountDTO = modelMapper.map(pageAccount.getContent(), new TypeToken< List<AccountDTO> >(){}.getType());
		
		Page<AccountDTO> pageAccountDTO = new PageImpl(listAccountDTO, pageable, pageAccount.getTotalElements());
		
		return pageAccountDTO;
	}
	
}
