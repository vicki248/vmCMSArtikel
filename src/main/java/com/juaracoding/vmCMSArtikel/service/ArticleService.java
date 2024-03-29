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
import com.juaracoding.vmCMSArtikel.dto.ArticleDTO;
import com.juaracoding.vmCMSArtikel.handler.ResponseHandler;
import com.juaracoding.vmCMSArtikel.model.Article;
import com.juaracoding.vmCMSArtikel.repo.ArticleRepo;
import com.juaracoding.vmCMSArtikel.utils.ConstantMessage;
import com.juaracoding.vmCMSArtikel.utils.LoggingFile;
import com.juaracoding.vmCMSArtikel.utils.TransformToDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;
import java.util.*;

@Service
@Transactional
public class ArticleService {

    private ArticleRepo articleRepo;

    private Map<String,Object> objectMapper = new HashMap<String,Object>();

    private String[] strExceptionArr = new String[2];
    private TransformToDTO transformToDTO = new TransformToDTO();
    private Map<String,String> mapColumnSearch = new HashMap<String,String>();

    private Map<Integer, Integer> mapItemPerPage = new HashMap<Integer, Integer>();
    private String [] strColumnSearch = new String[2];
    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public ArticleService(ArticleRepo articleRepo) {
        mapColumn();
        strExceptionArr[0] = "ArticleService";
        this.articleRepo = articleRepo;
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

    public Map<String,Object> findAllArticle(Pageable pageable, WebRequest request)
    {
        List<ArticleDTO> listArticleDTO = null;
        Map<String,Object> mapResult = null;
        Page<Article> pageArticle = null;
        List<Article> listArticle = null;

        try
        {
            pageArticle = articleRepo.findByIsShow(pageable,(byte)1);
            listArticle = pageArticle.getContent();
            if(listArticle.size()==0)
            {
                return new ResponseHandler().
                        generateModelAttribut(ConstantMessage.WARNING_DATA_EMPTY,
                                HttpStatus.OK,
                                transformToDTO.transformObjectDataEmpty(objectMapper,pageable,mapColumnSearch),//HANDLE NILAI PENCARIAN
                                "FV03005",
                                request);
            }
            listArticleDTO = modelMapper.map(listArticle, new TypeToken<List<ArticleDTO>>() {}.getType());
            mapResult = transformToDTO.transformObject(objectMapper,listArticleDTO,pageArticle,mapColumnSearch);

        }
        catch (Exception e)
        {
            strExceptionArr[1] = "findAllArticle(Pageable pageable, WebRequest request) --- LINE 178";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_INTERNAL_SERVER,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    transformToDTO.transformObjectDataEmpty(objectMapper,pageable,mapColumnSearch),//HANDLE NILAI PENCARIAN
                    "FE03003", request);
        }

        return new ResponseHandler().
                generateModelAttribut(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        mapResult,
                        null,
                        null);
    }

    public Map<String,Object> findByPage(Pageable pageable,WebRequest request,String columFirst,String valueFirst)
    {
        Page<Article> pageArticle = null;
        List<Article> listArticle = null;
        List<ArticleDTO> listArticleDTO = null;
        Map<String,Object> mapResult = null;

        try
        {
            if(columFirst.equals("id"))
            {
                try
                {
                    Long.parseLong(valueFirst);
                }
                catch (Exception e)
                {
                    strExceptionArr[1] = "findByPage(Pageable pageable,WebRequest request,String columFirst,String valueFirst) --- LINE 209";
                    LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
                    return new ResponseHandler().
                            generateModelAttribut(ConstantMessage.WARNING_DATA_EMPTY,
                                    HttpStatus.OK,
                                    transformToDTO.transformObjectDataEmpty(objectMapper,pageable,mapColumnSearch),//HANDLE NILAI PENCARIAN
                                    "FE03004",
                                    request);
                }
            }
            pageArticle = getDataByValue(pageable,columFirst,valueFirst);
            listArticle = pageArticle.getContent();
            if(listArticle.size()==0)
            {
                return new ResponseHandler().
                        generateModelAttribut(ConstantMessage.WARNING_DATA_EMPTY,
                                HttpStatus.OK,
                                transformToDTO.transformObjectDataEmpty(objectMapper,pageable,mapColumnSearch),//HANDLE NILAI PENCARIAN EMPTY
                                "FV03006",
                                request);
            }
            listArticleDTO = modelMapper.map(listArticle, new TypeToken<List<ArticleDTO>>() {}.getType());
            mapResult = transformToDTO.transformObject(objectMapper,listArticleDTO,pageArticle,mapColumnSearch);
            System.out.println("LIST DATA => "+listArticleDTO.size());
        }

        catch (Exception e)
        {
            strExceptionArr[1] = "findByPage(Pageable pageable,WebRequest request,String columFirst,String valueFirst) --- LINE 237";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_FLOW_INVALID,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    transformToDTO.transformObjectDataEmpty(objectMapper,pageable,mapColumnSearch),
                    "FE03005", request);
        }
        return new ResponseHandler().
                generateModelAttribut(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        mapResult,
                        null,
                        request);
    }
    public Map<String,Object> findById(Long id, WebRequest request)
    {
        Article article = articleRepo.findById(id).orElseThrow (
                ()-> null
        );
        if(article == null)
        {
            return new ResponseHandler().generateModelAttribut(ConstantMessage.WARNING_MENU_NOT_EXISTS,
                    HttpStatus.NOT_ACCEPTABLE,
                    transformToDTO.transformObjectDataEmpty(objectMapper,mapColumnSearch),
                    "FV03005",request);
        }
        ArticleDTO articleDTO = modelMapper.map(article, new TypeToken<ArticleDTO>() {}.getType());
        return new ResponseHandler().
                generateModelAttribut(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        articleDTO,
                        null,
                        request);
    }

    private void mapColumn()
    {
        mapColumnSearch.put("id","ID ARTICLE");
        mapColumnSearch.put("title","TITLE ARTICLE");
        mapColumnSearch.put("body","CONTENT ARTICLE");
        mapColumnSearch.put("category","CATEGORY ARTICLE");
        //mapColumnSearch.get('path')->PATH MENU
    }

    private Page<Article> getDataByValue(Pageable pageable, String paramColumn, String paramValue)
    {
        if(paramValue.equals("") || paramValue==null)
        {
            return articleRepo.findByIsShow(pageable,(byte) 1);
        }
        if(paramColumn.equals("id"))
        {
            return articleRepo.findByIsShowAndIdArticle(pageable,(byte) 1,Long.parseLong(paramValue));
        } else if (paramColumn.equals("title")) {
            return articleRepo.findByIsShowAndTitleArticleContainsIgnoreCase(pageable,(byte) 1,paramValue);
        } else if (paramColumn.equals("body")) {
            return articleRepo.findByIsShowAndBodyArticleContainsIgnoreCase(pageable,(byte) 1,paramValue);
        }
        else if (paramColumn.equals("category")) {
            return articleRepo.findByIsShowAndCategoryContainsIgnoreCase(pageable,(byte) 1,paramValue);
        }

        return articleRepo.findByIsShow(pageable,(byte) 1);// ini default kalau parameter search nya tidak sesuai--- asumsi nya di hit bukan dari web
    }

}
