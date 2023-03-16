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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryArticleRepo extends JpaRepository<CategoryArticle,Long> {
    List<CategoryArticle> findAll();
    Page<CategoryArticle> findByIsDelete(Pageable page , byte byteIsDelete);
    List<CategoryArticle> findByIsDelete(byte byteIsDelete);
    Page<CategoryArticle> findByIsDeleteAndIdCategoryArticle(Pageable pageable, Byte isDelete, String valueparamValue);
    Page<CategoryArticle> findByIsDeleteAndNameCategoryArticle(Pageable pageable, Byte isDelete, String valueparamValue);

}
