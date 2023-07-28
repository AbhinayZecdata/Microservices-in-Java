package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;

@RestController
public class Controller {
	
	@Autowired
	CountryService countryService;
	
	
	@GetMapping("/getcountries")	
	public List<Country> getCountries() {
		return countryService.getAllCountries();
	}
	
	@GetMapping("/getcountries/{id}")
	public Country getCountyById(@PathVariable(value="id")int id) {
		return countryService.getCountryById(id);
	}
	
	@GetMapping("/getcountries/countryname")
	public Country getCountryByName(@RequestParam(value="name") String name) {
			return countryService.getCountryByName(name);
	}
	
	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country) {
		return countryService.addCountry(country);
	}

	@PutMapping("/updatecountry/{id}")
	public Country updateCountry(@RequestBody Country country,@PathVariable(value="id") int id) {
		Country existingCountry=countryService.getCountryById(id);
		 if (existingCountry != null) {
		        existingCountry.setCountryName(country.getCountryName());
		        existingCountry.setCountryCapital(country.getCountryCapital());
		        return countryService.updateCountry(existingCountry);
		    } else {
		        throw new RuntimeException("Country with ID " + id + " not found.");
		    }
	}
	
	@DeleteMapping("/deletecountry/{id}")
	public AddResponse deleteCountry(@PathVariable(value="id") int id) {
		return countryService.deleteCountry(id);	
	}
}
