package com.juaracoding.vmCMSArtikel.repo;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 07/03/2023 20:22
@Last Modified 07/03/2023 20:22
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<Article,Long> {
    Page<Article> findByIsShow(Pageable page , byte byteIsDelete);
    Page<Article> findByIsShowAndTitleArticleContainsIgnoreCase(Pageable page , byte byteIsDelete, String values);
    Page<Article> findByIsShowAndIdArticleContainsIgnoreCase(Pageable page , byte byteIsDelete, Long values);
    Page<Article> findByIsShowAndBodyArticleContainsIgnoreCase(Pageable page , byte byteIsDelete, String values);
    Page<Article> findByIsShowAndCategoryContainsIgnoreCase(Pageable page , byte byteIsDelete, String values);




}
