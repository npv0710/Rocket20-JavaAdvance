package com.vti.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.JwtResponseDTO;
import com.vti.dto.LoginDTO;
import com.vti.dto.SignupDTO;
import com.vti.entity.Account;
import com.vti.entity.Role;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IRoleRepository;
import com.vti.service.UserDetailsImpl;
import com.vti.utils.JwtUtils;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private IAccountRepository acRepository;
	
	@Autowired
	private IRoleRepository rlRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO lgDTO) throws Exception {
		
		Authentication auth = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(lgDTO.getUsername(), lgDTO.getPassword())
		);
		
		//authenticate(lgDTO.getUsername(), lgDTO.getPassword());
		

		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = jwtUtils.generateJwtToken(auth);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponseDTO(
			jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
			roles
		));
		
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@PostMapping("/signup") 
	//@Transactional
	public ResponseEntity<?> registerUser(@RequestBody SignupDTO signupDTO) {
		if (acRepository.existsByUserName(signupDTO.getUsername())) {
			return ResponseEntity.badRequest().body("Error: Username is already taken!");
		}
		if (acRepository.existsByEmail(signupDTO.getEmail())) {
			return ResponseEntity.badRequest().body("Error: Username is already taken!");
		}
		
		Account ac = modelMapper.map(signupDTO, Account.class);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String enCryptPassword = passwordEncoder.encode(signupDTO.getPassword());
		ac.setPassword(enCryptPassword);
		
		Set<String> strRoles = signupDTO.getRoles();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role roleUser = rlRepository.findByName(Role.RoleName.USER)
					.orElseThrow(() -> new RuntimeException("Error: Role USER is not found"));
			roles.add(roleUser);
		}else {
			strRoles.forEach(item -> {
				switch(item) {
					case "ADMIN":
						Role roleAdmin = rlRepository.findByName(Role.RoleName.ADMIN)
									.orElseThrow(() -> new RuntimeException("Error: Role AMIN is not found"));
						roles.add(roleAdmin);
					case "MODERATOR":
						Role roleModerator = rlRepository.findByName(Role.RoleName.MODERATOR)
						.orElseThrow(() -> new RuntimeException("Error: Role MODERATOR is not found"));
						roles.add(roleModerator);
					default:
						Role roleUser = rlRepository.findByName(Role.RoleName.USER)
						.orElseThrow(() -> new RuntimeException("Error: Role USER is not found"));
						roles.add(roleUser);
				}
			});
		}
		
		ac.setRoles(roles);
		acRepository.save(ac);
		
		return ResponseEntity.ok().body("User registered successfully!");
	}
	
}









