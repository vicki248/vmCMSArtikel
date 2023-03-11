package com.juaracoding.vmCMSArtikel.controller;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 07/03/2023 20:22
@Last Modified 07/03/2023 20:22
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.model.Article;
import com.juaracoding.vmCMSArtikel.model.CategoryArticle;
import com.juaracoding.vmCMSArtikel.repo.ArticleRepo;
import com.juaracoding.vmCMSArtikel.repo.CategoryArticleRepo;
import com.juaracoding.vmCMSArtikel.service.ArticleService;
import com.juaracoding.vmCMSArtikel.service.CategoryArticleService;
import com.juaracoding.vmCMSArtikel.utils.MappingAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ArticleController {
    private ArticleService articleService;
    private MappingAttribute mappingAttribute = new MappingAttribute();
    private Map<String,Object> objectMapper = new HashMap<String,Object>();
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    private CategoryArticleService categoryArticleService;

    @Autowired
    private CategoryArticleRepo categoryArticleRepo;

    @Autowired
    private ArticleRepo articleRepo;

    @GetMapping("/")
    public String showArticles(Model model) {
        List<Article> articles = articleRepo.findAll();
        model.addAttribute("articles", articles);
        return "article/article";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("categories", categoryArticleRepo.findAll());
        return "add-article";
    }

    @PostMapping("/add")
    public String addArticle(@ModelAttribute("article") Article article, Model model) {
        articleService.saveArticle(article);
        return "redirect:/article/list";
    }

    // display list of article
//    @GetMapping("/")
//    public String viewHomePage(Model model, WebRequest request) {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        if(request.getAttribute("USR_ID",1)==null){
//            return "redirect:/api/check/logout";
//        }
//        return findPaginated(1, "firstName", "asc", model,request);
//    }

    @GetMapping("/showNewArticleForm")
    public String showNewArticleForm(Model model, WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }

        // create model attribute to bind form data
        Article article = new Article();
        model.addAttribute("article", article);
        return "article/create_article";
    }

    @PostMapping("/saveArticle")
    public String saveArticle(@ModelAttribute("article") Article article,Model model, WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }
        // save article to database
        articleService.saveArticle(article);
        return "redirect:/api/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model,WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }
        // get article from the service
        Article article = articleService.getArticleById(id);
        // set article as a model attribute to pre-populate the form
        model.addAttribute("article", article);
        return "article/edit_article";
    }

    @GetMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable (value = "id") long id,Model model,WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }
        // call delete article method
        articleService.deleteArticleById(id);
        return "redirect:/api/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model,WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }

        int pageSize = 5;

        Page<Article> page = articleService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Article> articles = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("articles", articles);
        return "index";
    }
}
