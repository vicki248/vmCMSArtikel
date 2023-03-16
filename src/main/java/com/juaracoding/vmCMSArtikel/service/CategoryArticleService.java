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
import com.juaracoding.vmCMSArtikel.dto.ArticleDTO;
import com.juaracoding.vmCMSArtikel.dto.CategoryArticleDTO;
import com.juaracoding.vmCMSArtikel.handler.ResponseHandler;
import com.juaracoding.vmCMSArtikel.model.CategoryArticle;
import com.juaracoding.vmCMSArtikel.repo.CategoryArticleRepo;
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

import java.util.*;

@Service
@Transactional
public class CategoryArticleService {
    private ModelMapper modelMapper;

    private CategoryArticleRepo categoryArticleRepo;
    private String [] strExceptionArr = new String[2];
    private Map<String,Object> objectMapper = new HashMap<String,Object>();
    private List<CategoryArticle> lsCPUpload = new ArrayList<CategoryArticle>();

    private TransformToDTO transformToDTO = new TransformToDTO();

    private Map<String,String> mapColumnSearch = new HashMap<String,String>();
    private String [] strColumnSearch = new String[2];

    @Autowired
    public CategoryArticleService(CategoryArticleRepo categoryArticleRepo) {
        strExceptionArr[0] = "CategoryArticleService";
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

    public Map<String, Object> saveCategoryArticle(CategoryArticle categoryArticle, WebRequest request) {
        String strMessage = ConstantMessage.SUCCESS_SAVE;
        Object strUserIdz = request.getAttribute("USR_IDZ",1);

        try {
            if(strUserIdz==null)
            {
                return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_FLOW_INVALID,
                        HttpStatus.NOT_ACCEPTABLE,null,"FV02001",request);
            }
            categoryArticle.setCreatedBy(Integer.parseInt(strUserIdz.toString()));
            categoryArticle.setCreatedDate(new Date());
            categoryArticleRepo.save(categoryArticle);
        } catch (Exception e) {
            strExceptionArr[1] = "saveCategoryArticle(CategoryArticle categoryArticle, WebRequest request) --- LINE 58";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST, null, "FE02001", request);
        }
        return new ResponseHandler().generateModelAttribut(strMessage,
                HttpStatus.CREATED, null, null, request);
    }

    public Map<String, Object> updateCategory(Long idCategoryArticle,CategoryArticle categoryArticle, WebRequest request) {
        String strMessage = ConstantMessage.SUCCESS_SAVE;
        Object strUserIdz = request.getAttribute("USR_IDZ",1);

        try {
            CategoryArticle nextCategoryArticle = categoryArticleRepo.findById(idCategoryArticle).orElseThrow(
                    ()->null
            );

            if(nextCategoryArticle==null)
            {
                return new ResponseHandler().generateModelAttribut(ConstantMessage.WARNING_MENU_NOT_EXISTS,
                        HttpStatus.NOT_ACCEPTABLE,null,"FV02002",request);
            }
            if(strUserIdz==null)
            {
                return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_FLOW_INVALID,
                        HttpStatus.NOT_ACCEPTABLE,null,"FV02003",request);
            }
            nextCategoryArticle.setNameCategoryArticle(categoryArticle.getNameCategoryArticle());
            nextCategoryArticle.setModifiedBy(Integer.parseInt(strUserIdz.toString()));
            nextCategoryArticle.setModifiedDate(new Date());

        } catch (Exception e) {
            strExceptionArr[1] = "updateCategoryArticle(Long idCategoryArticle,CategoryArticle categoryArticle, WebRequest request)  --- LINE 91";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_UPDATE_FAILED,
                    HttpStatus.BAD_REQUEST, null, "FE02002", request);
        }
        return new ResponseHandler().generateModelAttribut(strMessage,
                HttpStatus.CREATED, null, null, request);
    }

    public Map<String,Object> findPageAllCategoryArticle(Pageable pageable, WebRequest request)
    {
        List<ArticleDTO> listArticleDTO = null;
        Map<String,Object> mapResult = null;
        Page<CategoryArticle> pageArticle = null;
        List<CategoryArticle> listCategoryArticle = null;

        try
        {
            pageArticle = categoryArticleRepo.findByIsDelete(pageable,(byte)1);
            listCategoryArticle = pageArticle.getContent();
            if(listCategoryArticle.size()==0)
            {
                return new ResponseHandler().
                        generateModelAttribut(ConstantMessage.WARNING_DATA_EMPTY,
                                HttpStatus.OK,
                                null,
                                null,
                                request);
            }
            listArticleDTO = modelMapper.map(listCategoryArticle, new TypeToken<List<ArticleDTO>>() {}.getType());
            mapResult = transformToDTO.transformObject(objectMapper,listArticleDTO,pageArticle,mapColumnSearch);
        }
        catch (Exception e)
        {
            strExceptionArr[1] = "findAllArticle(Pageable pageable, WebRequest request) --- LINE 121";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_INTERNAL_SERVER,
                    HttpStatus.INTERNAL_SERVER_ERROR, null, "FE03003", request);
        }



        return new ResponseHandler().
                generateModelAttribut(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        mapResult,
                        null,
                        null);
    }
    public Map<String,Object> findAllCategoryArticle()//KHUSUS UNTUK FORM INPUT SAJA
    {
        List<CategoryArticleDTO> listCategoryArticleDTO = null;
        Map<String,Object> mapResult = null;
        Page<CategoryArticle> pageArticle = null;
        List<CategoryArticle> listArticle = null;

        try
        {
            listArticle = categoryArticleRepo.findByIsDelete((byte)1);
            if(listArticle.size()==0)
            {
                return new ResponseHandler().
                        generateModelAttribut(ConstantMessage.WARNING_DATA_EMPTY,
                                HttpStatus.OK,
                                null,
                                null,
                                null);
            }
            listCategoryArticleDTO = modelMapper.map(listArticle, new TypeToken<List<CategoryArticleDTO>>() {}.getType());
        }
        catch (Exception e)
        {
            strExceptionArr[1] = "findAllArticle(Pageable pageable, WebRequest request) --- LINE 121";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_INTERNAL_SERVER,
                    HttpStatus.INTERNAL_SERVER_ERROR, null, "FE03003", null);
        }



        return new ResponseHandler().
                generateModelAttribut(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        listCategoryArticleDTO,
                        null,
                        null);
    }

    public Map<String,Object> findById(Long id)
    {
        CategoryArticle categoryArticle= categoryArticleRepo.findById(id).orElseThrow (
                ()-> null
        );
        if(categoryArticle == null)
        {
            return new ResponseHandler().generateModelAttribut(ConstantMessage.WARNING_MENU_NOT_EXISTS,
                    HttpStatus.NOT_ACCEPTABLE,
                    transformToDTO.transformObjectDataEmpty(objectMapper,mapColumnSearch),
                    "FV03005",null);
        }
        ArticleDTO ArticleDTO = modelMapper.map(categoryArticle, new TypeToken<ArticleDTO>() {}.getType());
        return new ResponseHandler().
                generateModelAttribut(ConstantMessage.SUCCESS_FIND_BY,
                        HttpStatus.OK,
                        ArticleDTO,
                        null,
                        null);
    }

    public Map<String,String> mapColumn()
    {
        /*
        key = id di web, value = tampilan ke user
         */
        mapColumnSearch.put("id","ID");
        mapColumnSearch.put("nama","NAMA");
        mapColumnSearch.put("deskripsi","DESKRIPSI");

        return mapColumnSearch;
    }

    public Page<CategoryArticle> getDataByValue(Pageable pageable, String paramColumn, String paramValue)
    {
        if(paramColumn.equals("id"))
        {
            categoryArticleRepo.findByIsDeleteAndIdCategoryArticle(pageable,(byte) 1,paramValue);
        } else if (paramColumn.equals("nama")) {
            categoryArticleRepo.findByIsDeleteAndNameCategoryArticle(pageable,(byte) 1,paramValue);
        }

        return categoryArticleRepo.findByIsDeleteAndIdCategoryArticle(pageable,(byte) 1,paramValue);
    }
}
