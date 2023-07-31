package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.controller.AddResponse;
import com.example.demo.entity.Country;
import com.example.demo.repository.Countryrepo;

@Component
@Service
public class CountryService {
	
	@Autowired
	Countryrepo countryrepo;
	
	
	public List<Country> getAllCountries() {	
		return countryrepo.findAll();
	}
	
	public Country getCountryById(int id) {	
		return countryrepo.findById(id).get();
	}
	
	public Country getCountryByName(String countryName) {
		List<Country> countries= countryrepo.findAll();
		Country country = null;
		
		for(Country con:countries) {
			if(con.getCountryName().equalsIgnoreCase(countryName))
				country = con;
		}
		return country;
	
	}

	public Country addCountry(Country country) {
		
		country.setId(getMaxId());
		countryrepo.save(country);
		return country;
	}
	
	public Country updateCountry(Country country) {
		countryrepo.save(country);
		return country;
	}
	
	public  int getMaxId() {
		return countryrepo.findAll().size()+1;		
	}
	
	public AddResponse deleteCountry(int id) {
	    countryrepo.deleteById(id);
	    AddResponse res=new AddResponse();
	    res.setMessage("Country Deleted..!");
	    res.setId(id);
	    return res;
		
	}
}
		
