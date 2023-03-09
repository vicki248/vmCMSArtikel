package com.juaracoding.vmCMSArtikel.service;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 07/03/2023 20:22
@Last Modified 07/03/2023 20:22
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.configuration.OtherConfig;
import com.juaracoding.vmCMSArtikel.handler.ResponseHandler;
import com.juaracoding.vmCMSArtikel.model.Article;
import com.juaracoding.vmCMSArtikel.repo.ArticleRepo;
import com.juaracoding.vmCMSArtikel.utils.ConstantMessage;
import com.juaracoding.vmCMSArtikel.utils.LoggingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService {
    private ArticleRepo articleRepo;
    private String [] strExceptionArr = new String[2];

    @Autowired
    public ArticleService(ArticleRepo articleRepo) {
        strExceptionArr[0] = "ArticleService";
        this.articleRepo = articleRepo;
    }

    public ResponseEntity<Object> saveArticle(Article article)
    {
        String strMessage = ConstantMessage.SUCCESS_SAVE;
        try
        {
            articleRepo.save(article);
        }
        catch (Exception e)
        {
            strExceptionArr[1]="saveArticle(Article article)";
            LoggingFile.exceptionStringz(strExceptionArr,e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST,null,"FI02001",null);
        }

        return new ResponseHandler().generateResponse(strMessage,
                HttpStatus.CREATED,null,null,null);
    }

    public Page<Article> findPageSortBy(Pageable pageable){

        return articleRepo.findAll(pageable);
    }
}
