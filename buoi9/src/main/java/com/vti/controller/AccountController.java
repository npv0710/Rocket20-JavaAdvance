package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> createAccount(@RequestBody AccountDTO acDTO) {
		System.out.println(acDTO);
		
		Account ac = modelMapper.map(acDTO, Account.class);
		
		acService.createAccount(ac);
		
		return ResponseEntity.status(HttpStatus.OK).body("Account created!");
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") int id) {
		acService.deleteAccount(id);
		return ResponseEntity.status(HttpStatus.OK).body("Account deleted!");
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAccount(@PathVariable(name = "id") int id, @RequestBody AccountDTO acDTO) {
		System.out.println("id: " + id);
		System.out.println(acDTO);
		acDTO.setId(id);
		acService.updateAccount(acDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Account updated");
	}
	
	@RequestMapping(value = "/deletemultiple", method = RequestMethod.POST)
	public ResponseEntity<?> deleteMultipleAccounts(@RequestBody List<Integer> ids) {
		System.out.println(ids);
		acService.deleteMultipleAccounts(ids);
		return ResponseEntity.status(HttpStatus.OK).body("All accounts deleted");
	}
	
}
