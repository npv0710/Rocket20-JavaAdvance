package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entity.Address;
import com.vti.entity.AddressDTO;
import com.vti.service.AddressService;
import com.vti.service.IAddressService;

@RestController
@RequestMapping(value = "api/addresses")
public class AddressController {
private IAddressService addressService;
	
	public AddressController() {
		addressService = new AddressService();
	}
	
	@GetMapping()
	public List<AddressDTO> getListAccounts() {
		List<Address> addresses = addressService.getListAddresses();
		List<AddressDTO> listAddressDTO = new ArrayList();
		for (Address address : addresses) {
			//AddressDTO addDTO = new AddressDTO(address.getId(), address.getStreet(), address.getCity(), address.getAccount().getUsername());
			AddressDTO addDTO = new AddressDTO(address.getId(), address.getStreet(), address.getCity(), address.getAccounts().toString());
			listAddressDTO.add(addDTO);
		}
		return listAddressDTO;
	}
}
