package com.juaracoding.vmCMSArtikel.dto;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 09/03/2023 22:26
@Last Modified 09/03/2023 22:26
Version 1.1
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDTO {
    private Long idArticle;
    private String titleArticle;
    private String slug;
    private String bodyArticle;
    private String imageArticle;
    private CategoryArticleDTO categoryArticle;

    public Long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitleArticle() {
        return titleArticle;
    }

    public void setTitleArticle(String titleArticle) {
        this.titleArticle = titleArticle;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBodyArticle() {
        return bodyArticle;
    }

    public void setBodyArticle(String bodyArticle) {
        this.bodyArticle = bodyArticle;
    }

    public String getImageArticle() {
        return imageArticle;
    }

    public void setImageArticle(String imageArticle) {
        this.imageArticle = imageArticle;
    }

    public CategoryArticleDTO getCategoryArticle() {
        return categoryArticle;
    }

    public void setCategoryArticle(CategoryArticleDTO categoryArticle) {
        this.categoryArticle = categoryArticle;
    }

}
