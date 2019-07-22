package com.apsidiscount.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.apsidiscount.entity.Article;
import com.apsidiscount.entity.Categorie;
import com.apsidiscount.entity.Constructeur;
import com.apsidiscount.service.ArticleInconnuException;
import com.apsidiscount.service.ArticleService;

@RestController
public class ArticleRestController {
	
	@Autowired
	private ArticleService articleService;
	
	
	@ExceptionHandler(ArticleInconnuException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@RequestMapping(produces = "text/plain")
	public ErrorResponse handlerArticleInconnuException(ArticleInconnuException e, Model model) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(e.getMessage());
		return errorResponse;
	}
	
	@GetMapping(produces = "application/json", path="/api/article/{id}")
	public Article getById(@PathVariable long id) throws ArticleInconnuException{
		return articleService.getById(id);
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json", path="/api/article")
	public ResponseEntity<Article> create(@RequestBody Article article, UriComponentsBuilder uriBuilder) {
		//to do appelert le service
		
		URI location = uriBuilder.path("/api/article/{id}").buildAndExpand(article.getId()).toUri();
		return ResponseEntity.created(location).body(article);
	}
	
	@GetMapping(produces = "application/json", path="/api/allarticle")
	public List<Article> getAllArticle() {
		return  articleService.getAllArticle();
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json", path = "/api/article/{id}")
	public Article modifyArticle(@RequestBody Article article) throws ArticleInconnuException {
		return articleService.modifier(article);
	}
	
	//Ajout 21/7
	@GetMapping(produces = "application/json", path="/api/categorie/{id}/article")
	public List<Article> getArticlesByCat(@PathVariable Long id) {
		
		return  articleService.getArticlesByCategory(id);
	}
	
	@GetMapping(produces = "application/json", path="/api/manufacturer/{id}/article")
	public List<Article> getArticlesByConstruct(@PathVariable Long id) {
		return  articleService.getArticlesByConstructeur(id);
		
	}
	
}
