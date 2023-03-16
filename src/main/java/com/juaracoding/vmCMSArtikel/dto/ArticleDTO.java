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
import com.juaracoding.vmCMSArtikel.utils.ConstantMessage;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDTO {
    private Long idArticle;
    @NotNull
    @NotEmpty
    @Length(message = ConstantMessage.WARNING_MENU_NAME_LENGTH,max = 100)
    private String titleArticle;
    private String slug;
    @NotNull
    @NotEmpty
    @Length(message = ConstantMessage.WARNING_MENU_NAME_LENGTH,max = 2000)
    private String bodyArticle;
    private String imageArticle;
    private Long idCategoryArticle;


    @JsonIgnoreProperties("listArticleAkses")
    private List<AksesDTO> listAksesArticle;

    @NotNull
    private CategoryArticleDTO category;


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


    public List<AksesDTO> getListAksesArticle() {
        return listAksesArticle;
    }

    public void setListAksesArticle(List<AksesDTO> listAksesArticle) {
        this.listAksesArticle = listAksesArticle;
    }

    public CategoryArticleDTO getCategory() {
        return category;
    }

    public String getNameCategoryArticle() {
        return category.getNameCategoryArticle();
    }

    public void setCategory(CategoryArticleDTO category) {
        this.category = category;
    }

    public static ArticleDTO fromEntity(Article article) {
        ArticleDTO dto = new ArticleDTO();
        dto.setIdArticle(article.getIdArticle());
        dto.setTitleArticle(article.getTitleArticle());
        dto.setSlug(article.getSlug());
        dto.setBodyArticle(article.getBodyArticle());
        dto.setIdCategoryArticle(article.getCategory().getIdCategoryArticle());
        dto.setImageArticle(article.getImageArticle());
        return dto;
    }

    public static List<ArticleDTO> fromEntities(List<Article> articles) {
        return articles.stream().map(ArticleDTO::fromEntity).collect(Collectors.toList());
    }

    public Article toEntity() {
        Article article = new Article();
        CategoryArticle category = new CategoryArticle();
        article.setIdArticle(this.getIdArticle());
        article.setTitleArticle(this.getTitleArticle());
        article.setSlug(this.getSlug());
        article.setBodyArticle(this.getBodyArticle());
        category.setIdCategoryArticle(this.getIdCategoryArticle());
        article.setCategory(category);
        article.setImageArticle(this.getImageArticle());
        return article;
    }
}
