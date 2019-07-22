package com.apsidiscount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.apsidiscount.entity.Categorie;
import com.apsidiscount.service.CategoryService;

public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;
	
	//Ajout 21/7
	@GetMapping(produces = "application/json", path="/api/allcatyegory")
	public List<Categorie> getAllCategories() {
	return  categoryService.getAllCat();
	}
}
