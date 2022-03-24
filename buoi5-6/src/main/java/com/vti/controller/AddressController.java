package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDTO;
import com.vti.dto.AddressDTO;
import com.vti.entity.Account;
import com.vti.entity.Address;
import com.vti.service.AddressService;
import com.vti.service.IAddressService;


@RestController
@RequestMapping(value = "/api/addresses")
public class AddressController {
	
	private IAddressService addService;
	
	public AddressController() {
		addService = new AddressService();
	}
	
	@GetMapping
	public List<AddressDTO> getListAddresses() {
		List<Address> addresses = addService.getListAddresses();
		
		List<AddressDTO> listAddressDTO = new ArrayList();
		
		//address.getAccounts().toString()
		
		for (Address address : addresses) {
			AddressDTO addDTO = new AddressDTO(address.getId(),
					address.getStreet(), address.getCity(),
					"123abc"
					//address.getAccounts().toString()
			);
			listAddressDTO.add(addDTO);
		}
		
		return listAddressDTO;
	}
	
}
