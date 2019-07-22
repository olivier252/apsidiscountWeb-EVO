package com.apsidiscount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.apsidiscount.entity.Constructeur;
import com.apsidiscount.service.ConstructorService;

public class ConstructorRestController {
	@Autowired
	private ConstructorService constructorService;
	
	//Ajout 21/7
	@GetMapping(produces = "application/json", path="/api/allconstructors")
	public List<Constructeur> getAllConstruct() {
	return  constructorService.getAllConstructors();
	}
}
