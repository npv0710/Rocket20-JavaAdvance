package com.vti.service;

import java.util.List;

import com.vti.entity.Address;
import com.vti.repository.AddressRepository;
import com.vti.repository.IAddressRepository;

public class AddressService implements IAddressService{
	
	private IAddressRepository addRepository;
	public AddressService () {
		addRepository = new AddressRepository();
	}
	@Override
	public List<Address> getListAddresses() {
		return addRepository.getListAddresses();
	}
	
}
