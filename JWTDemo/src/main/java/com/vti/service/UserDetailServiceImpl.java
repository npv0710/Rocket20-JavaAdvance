package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.Account;
import com.vti.repository.IAccountRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private IAccountRepository acRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account ac = acRepository.findByUserName(username)
					.orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));
		
		return UserDetailsImpl.build(ac);
	}

}
