package com.juaracoding.vmCMSArtikel.service;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 07/03/2023 20:22
@Last Modified 07/03/2023 20:22
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.model.Article;
import com.juaracoding.vmCMSArtikel.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleService {
    private ArticleRepo articleRepo;

    @Autowired
    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public List<Article> getAllArticle() {
        return articleRepo.findAll();
    }

    public void saveArticle(Article article) {
        this.articleRepo.save(article);
    }

    public Article getArticleById(long id) {
        Optional<Article> optional = articleRepo.findById(id);
        Article article = null;
        if (optional.isPresent()) {
            article = optional.get();
        } else {
            throw new RuntimeException(" Article not found for id :: " + id);
        }
        return article;
    }

    public void deleteArticleById(long id) {
        this.articleRepo.deleteById(id);
    }

    public Page<Article> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.articleRepo.findAll(pageable);
    }

    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }
}
