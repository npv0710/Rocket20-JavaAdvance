package com.vti.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vti.entity.Account;

import com.vti.repository.IAccountRepository;

@Service
public class AccountService implements IAccountService{
	@SuppressWarnings("unused")
	@Autowired
	private IAccountRepository acRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Account> getListAccounts() {
		return acRepository.findAll();
	}

	@Override
	public Page<Account> getPagingAccounts(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountExists(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountExistsByUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getAccountByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createAccount(Account ac) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
