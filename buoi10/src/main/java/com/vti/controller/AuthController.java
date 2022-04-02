package com.vti.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.JwtResponseDTO;
import com.vti.dto.SigninDTO;
import com.vti.dto.SignupDTO;
import com.vti.entity.Account;
import com.vti.repository.IAccountRepository;
import com.vti.service.IAccountService;
import com.vti.utils.JwtUtils;


@RestController
@RequestMapping(value = "api/auth")
public class AuthController {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IAccountService acService;

	@Autowired
	private IAccountRepository acRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody SigninDTO signinDTO) throws Exception {
		
		System.out.println(signinDTO);
		
		Authentication auth = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(signinDTO.getUsername(), signinDTO.getPassword())
		);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = jwtUtils.generateJwtToken(auth);
		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		
		return ResponseEntity.ok(new JwtResponseDTO(
			jwt, userDetails.getUsername(), userDetails.getAuthorities().toString()
		));
	}
	
	@PostMapping("/signup") 
	public ResponseEntity<?> registerUser(@RequestBody SignupDTO signupDTO) {
		if (acRepository.existsByUsername(signupDTO.getUsername())) {
			return ResponseEntity.badRequest().body("Error: Username is already taken!");
		}
		if (acRepository.existsByEmail(signupDTO.getEmail())) {
			return ResponseEntity.badRequest().body("Error: Username is already taken!");
		}
		
		Account ac = modelMapper.map(signupDTO, Account.class);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String enCryptPassword = passwordEncoder.encode(signupDTO.getPassword());
		ac.setPassword(enCryptPassword);
		
		acService.createAccount(ac);
		
		return ResponseEntity.ok().body("User registered successfully!");
	}
	
}
