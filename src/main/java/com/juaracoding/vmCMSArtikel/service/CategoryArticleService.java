package com.juaracoding.vmCMSArtikel.service;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 09/03/2023 21:34
@Last Modified 09/03/2023 21:34
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.configuration.OtherConfig;
import com.juaracoding.vmCMSArtikel.handler.ResponseHandler;
import com.juaracoding.vmCMSArtikel.model.CategoryArticle;
import com.juaracoding.vmCMSArtikel.repo.CategoryArticleRepo;
import com.juaracoding.vmCMSArtikel.utils.ConstantMessage;
import com.juaracoding.vmCMSArtikel.utils.LoggingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryArticleService {
    private CategoryArticleRepo categoryArticleRepo;
    private String [] strExceptionArr = new String[2];

    @Autowired
    public CategoryArticleService(CategoryArticleRepo categoryArticleRepo) {
        this.categoryArticleRepo = categoryArticleRepo;
    }
    public List<CategoryArticle> getAllCategoryArticle() {
        return categoryArticleRepo.findAll();
    }

//    public ResponseEntity<Object> saveCategoryArticle(CategoryArticle categoryArticle)
//    {
//        String strMessage = ConstantMessage.SUCCESS_SAVE;
//        try
//        {
//            categoryArticleRepo.save(categoryArticle);
//        }
//        catch (Exception e)
//        {
//            strExceptionArr[1]="saveCategoryArticle(CategoryArticle categoryArticle)";
//            LoggingFile.exceptionStringz(strExceptionArr,e, OtherConfig.getFlagLogging());
//            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_SAVE_FAILED,
//                    HttpStatus.BAD_REQUEST,null,"FI02001",null);
//        }
//
//        return new ResponseHandler().generateResponse(strMessage,
//                HttpStatus.CREATED,null,null,null);
//    }
//
//    public Page<CategoryArticle> findPageSortBy(Pageable pageable){
//
//        return categoryArticleRepo.findAll(pageable);
//    }

//    // get all categories
//    public List<CategoryArticle> getAllCategories() {
//        return categoryArticleRepo.findAllCategories();
//    }
}
