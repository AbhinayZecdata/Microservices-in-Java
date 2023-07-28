package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.controller.AddResponse;
import com.example.demo.entity.Country;

@Service
public class CountryService {
	
	static HashMap<Integer, Country> countryIdMap;

	public CountryService() {
		
		countryIdMap=new HashMap<Integer, Country>();
		
		Country india=new Country(1,"India", "Delhi");
		Country usa=new Country(2,"Usa", "Washington");
		Country uk=new Country(3,"Uk", "London");
		
		countryIdMap.put(1, india);
		countryIdMap.put(2, usa);
		countryIdMap.put(3, uk);
		
	}
	
	public List<Country> getAllCountries() {	
		List<Country> countries=new ArrayList<Country>(countryIdMap.values());
		return countries;	
	}
	
	public Country getCountryById(int id) {	
		Country country=countryIdMap.get(id);
		return country;		
	}
	
	public Country getCountryByName(String countryName) {
		
		Country country=null;
		for(int i:countryIdMap.keySet()) {
			if(countryIdMap.get(i).getCountryName().equals(countryName))
				country=countryIdMap.get(i);
		}
	    return country;
	}

	public Country addCountry(Country country) {
		
		country.setId(getMaxId());
		countryIdMap.put(country.getId(), country);
		return country;
	}
	
	public Country updateCountry(Country country) {
		if(country.getId()>0) {
			countryIdMap.put(country.getId(), country);
		}
		return country;
	}
	
	public static int getMaxId() {
		int max=0;
		for(int id:countryIdMap.keySet())
		{
			if(max<=id)
				max=id;
		}
		return max+1;
	}
	
	public AddResponse deleteCountry(int id) {
		countryIdMap.remove(id);
		AddResponse res=new AddResponse();
		res.setMessage("Country Deleted Succefully !");
		res.setId(id);
		return res;
	}
	
}
