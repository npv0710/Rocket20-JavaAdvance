package com.vti.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;
import com.vti.repository.IAccountRepository;
import com.vti.specification.AccountSpecification;

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
	public boolean isAccountExists(int id) {
		return false;
	}

	@Override
	public boolean isAccountExistsByUsername(String username) {
		return false;
	}

	@Override
	public Account getAccountByUsername(String username) {
		return null;
	}

	@Override
	public Page<Account> getListAccountsPaging(Pageable pageable, String search, AccountFilterForm acFF) {
		Specification<Account> where = AccountSpecification.buildWhere(search, acFF);
		return acRepository.findAll(where, pageable);
	}
	
	@Override
	public void createAccount(Account ac) {
		acRepository.save(ac);
	}
	
	@Override
	public void deleteAccount(int id) {
		acRepository.deleteById(id);
	}
	
	@Override
	public void updateAccount(AccountDTO acDTO) {
		Account ac = modelMapper.map(acDTO, Account.class);
		acRepository.save(ac);
	}
	
	@Override
	public void deleteMultipleAccounts(List<Integer> ids) {
		acRepository.deleteMultilAccount(ids);
	}
	
	
}
