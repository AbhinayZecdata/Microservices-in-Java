package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Country;

public interface Countryrepo extends JpaRepository<Country,Integer>{

}
