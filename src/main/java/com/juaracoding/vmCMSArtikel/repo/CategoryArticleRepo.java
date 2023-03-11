package com.juaracoding.vmCMSArtikel.repo;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 09/03/2023 21:34
@Last Modified 09/03/2023 21:34
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.model.CategoryArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryArticleRepo extends JpaRepository<CategoryArticle,Long> {
//    // custom query to get all categories
//    @Query("SELECT c FROM CategoryArticle c")
//    List<CategoryArticle> findAllCategories();

}
