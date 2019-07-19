package com.apsidiscount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.apsidiscount.entity.Article;
import com.apsidiscount.service.ArticleInconnuException;
import com.apsidiscount.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@ExceptionHandler(ArticleInconnuException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String handlerArticleInconnuException(ArticleInconnuException e, Model model) {
		model.addAttribute("exception", e);
		return "error";
	}

	@GetMapping("article/{id}")
	public String afficher(@PathVariable long id, Model model) throws ArticleInconnuException {
		Article article = articleService.getById(id);
		model.addAttribute("article", article);
		return "article";
	}

//	@PostMapping("article/{id}")
//	public String modifierArticle(@PathVariable long id, @RequestParam String designation, @RequestParam String description, Model model ) throws ArticleInconnuException{
//		Article article = articleService.modifier(id, designation, description);
//		model.addAttribute("article", article);
//		model.addAttribute("message", "modifications enregistrées");
//		return "article";
//	}


//	@PutMapping("article/{id}")
//	public Article modifyArticle(Article article) throws ArticleInconnuException {
//		return articleService.modifier(article, article.getId());
//	}
}


