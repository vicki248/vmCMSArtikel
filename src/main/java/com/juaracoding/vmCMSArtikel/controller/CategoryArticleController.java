package com.juaracoding.vmCMSArtikel.controller;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 09/03/2023 21:39
@Last Modified 09/03/2023 21:39
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.model.CategoryArticle;
import com.juaracoding.vmCMSArtikel.repo.CategoryArticleRepo;
import com.juaracoding.vmCMSArtikel.service.CategoryArticleService;
import org.springframework.ui.Model;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categoryArticle")
public class CategoryArticleController {
    private CategoryArticleService categoryArticleService;

    private String[] strExcep = new String[2];

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CategoryArticleController(CategoryArticleService categoryArticleService) {
        this.categoryArticleService = categoryArticleService;
    }

    @Autowired
    private CategoryArticleRepo categoryArticleRepo;

    @GetMapping
    public ResponseEntity<List<CategoryArticle>> getAllCategoryArticles() {
        List<CategoryArticle> categoryArticles = categoryArticleService.getAllCategoryArticle();
        return ResponseEntity.ok(categoryArticles);
    }
//    @PostMapping("v1/sv")
//    public ResponseEntity<Object> saveCategoryArticle(@Valid
//                                              @RequestBody CategoryArticle categoryArticle
//    )
//    {
//        return categoryArticleService. saveCategoryArticle(categoryArticle);
//
//    }
}
