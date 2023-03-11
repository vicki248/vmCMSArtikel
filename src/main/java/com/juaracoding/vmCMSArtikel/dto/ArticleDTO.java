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
import com.juaracoding.vmCMSArtikel.model.Article;
import com.juaracoding.vmCMSArtikel.model.CategoryArticle;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDTO {
    private Long idArticle;
    private String titleArticle;
    private String slug;
    private String bodyArticle;
    private String imageArticle;
    private Long idCategoryArticle;

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

    public Long getIdCategoryArticle() {
        return idCategoryArticle;
    }

    public void setIdCategoryArticle(Long idCategoryArticle) {
        this.idCategoryArticle = idCategoryArticle;
    }

    public static ArticleDTO fromEntity(Article article) {
        ArticleDTO dto = new ArticleDTO();
        dto.setIdArticle(article.getIdArticle());
        dto.setTitleArticle(article.getTitleArticle());
        dto.setSlug(article.getSlug());
        dto.setBodyArticle(article.getBodyArticle());
        dto.setIdCategoryArticle(article.getIdCategoryArticle().getIdCategoryArticle());
        dto.setImageArticle(article.getImageArticle());
        return dto;
    }

    public static List<ArticleDTO> fromEntities(List<Article> articles) {
        return articles.stream().map(ArticleDTO::fromEntity).collect(Collectors.toList());
    }

    public Article toEntity() {
        Article article = new Article();
        article.setIdArticle(this.getIdArticle());
        article.setTitleArticle(this.getTitleArticle());
        article.setSlug(this.getSlug());
        article.setBodyArticle(this.getBodyArticle());
        CategoryArticle category = new CategoryArticle();
        category.setIdCategoryArticle(this.getIdCategoryArticle());
        article.setIdCategoryArticle(category);
        article.setImageArticle(this.getImageArticle());
        return article;
    }
}
