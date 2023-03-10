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
import com.juaracoding.vmCMSArtikel.handler.ResourceNotFoundException;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Transactional
public class ArticleService {
    private ArticleRepo articleRepo;

    private String[] strExceptionArr = new String[2];
    @Autowired
    private ModelMapper modelMapper;

    private Map<String,Object> objectMapper = new HashMap<String,Object>();

    private TransformToDTO transformToDTO = new TransformToDTO();

    private Map<String,String> mapColumnSearch = new HashMap<String,String>();
    private Map<Integer, Integer> mapItemPerPage = new HashMap<Integer, Integer>();
    private String [] strColumnSearch = new String[2];

    @Autowired
    public ArticleService(ArticleRepo articleRepo) {
        strExceptionArr[0]="ArticleService";
        mapColumn();
        this.articleRepo = articleRepo;
    }

    public Map<String, Object> saveArticle(Article article, WebRequest request) {
        String strMessage = ConstantMessage.SUCCESS_SAVE;
        Object strUserIdz = request.getAttribute("USR_ID",1);

        try {
            if(strUserIdz==null)
            {
                return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_FLOW_INVALID,
                        HttpStatus.NOT_ACCEPTABLE,null,"FV11001",request);
            }
            article.setCreatedBy(Integer.parseInt(strUserIdz.toString()));
            article.setCreatedDate(new Date());
            articleRepo.save(article);
        } catch (Exception e) {
            strExceptionArr[1] = "saveArticle(Article article, WebRequest request) --- LINE 64";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST,
                    transformToDTO.transformObjectDataEmpty(objectMapper,mapColumnSearch),
                    "FE11001", request);
        }
        return new ResponseHandler().generateModelAttribut(strMessage,
                HttpStatus.CREATED,
                transformToDTO.transformObjectDataSave(objectMapper, article.getIdArticle(),mapColumnSearch),
                null, request);
    }

    public Map<String, Object> updateArticle(Long idArticle,Article article, WebRequest request) {
        String strMessage = ConstantMessage.SUCCESS_SAVE;
        Object strUserIdz = request.getAttribute("USR_ID",1);

        try {
            Article nextArticle = articleRepo.findById(idArticle).orElseThrow(
                    ()->null
            );

            if(nextArticle==null)
            {
                return new ResponseHandler().generateModelAttribut(ConstantMessage.WARNING_DEMO_NOT_EXISTS,
                        HttpStatus.NOT_ACCEPTABLE,
                        transformToDTO.transformObjectDataEmpty(objectMapper,mapColumnSearch),
                        "FV11002",request);
            }
            if(strUserIdz==null)
            {
                return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_FLOW_INVALID,
                        HttpStatus.NOT_ACCEPTABLE,
                        null,
                        "FV11003",request);
            }
            nextArticle.setIdArticle(article.getIdArticle());
            nextArticle.setTitleArticle(article.getTitleArticle());
            nextArticle.setBodyArticle(article.getBodyArticle());
            nextArticle.setImageArticle(article.getImageArticle());
            nextArticle.setModifiedBy(Integer.parseInt(strUserIdz.toString()));
            nextArticle.setModifiedDate(new Date());

        } catch (Exception e) {
            strExceptionArr[1] = " updateArticle(Long idArticle,Article article, WebRequest request) --- LINE 112";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST,
                    transformToDTO.transformObjectDataEmpty(objectMapper,mapColumnSearch),
                    "FE11002", request);
        }
        return new ResponseHandler().generateModelAttribut(strMessage,
                HttpStatus.CREATED,
                transformToDTO.transformObjectDataEmpty(objectMapper,mapColumnSearch),
                null, request);
    }



    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveUploadFileArticle(List<Article> listArticle,
                                                  MultipartFile multipartFile,
                                                  WebRequest request) throws Exception {
        List<Article> listArticleResult = null;
        String strMessage = ConstantMessage.SUCCESS_SAVE;

        try {
            listArticleResult = articleRepo.saveAll(listArticle);
            if (listArticleResult.size() == 0) {
                strExceptionArr[1] = "saveUploadFileArticle(List<Article> listArticle, MultipartFile multipartFile, WebRequest request) --- LINE 136";
                LoggingFile.exceptionStringz(strExceptionArr, new ResourceNotFoundException("FILE KOSONG"), OtherConfig.getFlagLogging());
                return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_EMPTY_FILE + " -- " + multipartFile.getOriginalFilename(),
                        HttpStatus.BAD_REQUEST, null, "FV11004", request);
            }
        } catch (Exception e) {
            strExceptionArr[1] = "saveUploadFileArticle(List<Article> listArticle, MultipartFile multipartFile, WebRequest request) --- LINE 88";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST, null, "FE11002", request);
        }
        return new ResponseHandler().
                generateModelAttribut(strMessage,
                        HttpStatus.CREATED,
                        transformToDTO.transformObjectDataEmpty(objectMapper,mapColumnSearch),
                        null,
                        request);
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
                                "FV11005",
                                request);
            }
            listArticleDTO = modelMapper.map(listArticle, new TypeToken<List<ArticleDTO>>() {}.getType());
            mapResult = transformToDTO.transformObject(objectMapper,listArticleDTO,pageArticle,mapColumnSearch);

        }
        catch (Exception e)
        {
            strExceptionArr[1] = "findAllArticle(Pageable pageable, WebRequest request) --- LINE 182";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_INTERNAL_SERVER,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    transformToDTO.transformObjectDataEmpty(objectMapper,pageable,mapColumnSearch),//HANDLE NILAI PENCARIAN
                    "FE11003", request);
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
                if(!valueFirst.equals("") && valueFirst!=null)
                {
                    try
                    {
                        Long.parseLong(valueFirst);
                    }
                    catch (Exception e)
                    {
                        strExceptionArr[1] = "findByPage(Pageable pageable,WebRequest request,String columFirst,String valueFirst) --- LINE 215";
                        LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
                        return new ResponseHandler().
                                generateModelAttribut(ConstantMessage.WARNING_DATA_EMPTY,
                                        HttpStatus.OK,
                                        transformToDTO.transformObjectDataEmpty(objectMapper,pageable,mapColumnSearch),//HANDLE NILAI PENCARIAN
                                        "FE11004",
                                        request);
                    }
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
                                "FV11006",
                                request);
            }
            listArticleDTO = modelMapper.map(listArticle, new TypeToken<List<ArticleDTO>>() {}.getType());
            mapResult = transformToDTO.transformObject(objectMapper,listArticleDTO,pageArticle,mapColumnSearch);
            System.out.println("LIST DATA => "+listArticleDTO.size());
        }

        catch (Exception e)
        {
            strExceptionArr[1] = "findByPage(Pageable pageable,WebRequest request,String columFirst,String valueFirst) --- LINE 243";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_FLOW_INVALID,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    transformToDTO.transformObjectDataEmpty(objectMapper,pageable,mapColumnSearch),
                    "FE11005", request);
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
            return new ResponseHandler().generateModelAttribut(ConstantMessage.WARNING_DEMO_NOT_EXISTS,
                    HttpStatus.NOT_ACCEPTABLE,
                    transformToDTO.transformObjectDataEmpty(objectMapper,mapColumnSearch),
                    "FV11005",request);
        }
        ArticleDTO articleDTO = modelMapper.map(article, new TypeToken() {}.getType());
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
        mapColumnSearch.put("body","BODY ARTICLE");
    }

    private Page<Article> getDataByValue(Pageable pageable, String paramColumn, String paramValue)
    {
        if(paramValue.equals("") || paramValue==null)
        {
            return articleRepo.findByIsShow(pageable,(byte) 1);
        }
        if(paramColumn.equals("id"))
        {
            return articleRepo.findByIsShowAndIdArticleContainsIgnoreCase(pageable,(byte) 1,Long.parseLong(paramValue));
        } else if (paramColumn.equals("title")) {
            return articleRepo.findByIsShowAndTitleArticleContainsIgnoreCase(pageable,(byte) 1,paramValue);
        }

        return articleRepo.findByIsShow(pageable,(byte) 1);// ini default kalau parameter search nya tidak sesuai--- asumsi nya di hit bukan dari web
    }
}
