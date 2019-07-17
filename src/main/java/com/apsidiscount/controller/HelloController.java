package com.apsidiscount.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	@GetMapping("/hello")
	public String sayHello(@RequestParam String nom, Model model) {
		model.addAttribute("nom", nom);
		return "Hello";
	}
	
	@GetMapping("/Personne")
	public String afficherPersonne(@ModelAttribute("personne") PersonneDTO personne) {
		return "personne";
	}
	
	
}
