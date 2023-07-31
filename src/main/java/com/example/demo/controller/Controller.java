package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Country> getCountyById(@PathVariable(value="id")int id) {
		try {
			Country country= countryService.getCountryById(id);
			return new ResponseEntity<Country>(country,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value="name") String name) {
		try {
			Country country= countryService.getCountryByName(name);
			return new ResponseEntity<Country>(country,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}      
	
	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country) {
		return countryService.addCountry(country);
	}

	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@RequestBody Country country,@PathVariable(value="id") int id) { 
	try {
		Country existCountry = countryService.getCountryById(id);
		existCountry.setCountryName(country.getCountryName());
		existCountry.setCountryCapital(country.getCountryCapital());
		
		Country UpdatedCountry = countryService.updateCountry(existCountry);
		return new ResponseEntity<Country>(UpdatedCountry,HttpStatus.OK);
	}
	catch(Exception e) {
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}	
	}
	
	@DeleteMapping("/deletecountry/{id}")
	public AddResponse deleteCountry(@PathVariable(value="id") int id) {
		return countryService.deleteCountry(id);
		
 	}
}
