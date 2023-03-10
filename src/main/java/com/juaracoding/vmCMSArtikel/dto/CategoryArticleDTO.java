package com.juaracoding.vmCMSArtikel.dto;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 09/03/2023 22:27
@Last Modified 09/03/2023 22:27
Version 1.1
*/

public class CategoryArticleDTO {
    private Long idCategoryArticle;
    private String nameCategoryArticle;

    public Long getIdCategoryArticle() {
        return idCategoryArticle;
    }

    public void setIdCategoryArticle(Long idCategoryArticle) {
        this.idCategoryArticle = idCategoryArticle;
    }

    public String getNameCategoryArticle() {
        return nameCategoryArticle;
    }

    public void setNameCategoryArticle(String nameCategoryArticle) {
        this.nameCategoryArticle = nameCategoryArticle;
    }
}
