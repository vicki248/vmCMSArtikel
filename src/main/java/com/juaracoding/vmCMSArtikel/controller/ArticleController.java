package com.juaracoding.vmCMSArtikel.controller;/*
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
import com.juaracoding.vmCMSArtikel.model.Article;
import com.juaracoding.vmCMSArtikel.service.ArticleService;
import com.juaracoding.vmCMSArtikel.utils.ConstantMessage;
import com.juaracoding.vmCMSArtikel.utils.ManipulationMap;
import com.juaracoding.vmCMSArtikel.utils.MappingAttribute;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/usrmgmnt")
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    private ModelMapper modelMapper;

    private Map<String,Object> objectMapper = new HashMap<String,Object>();
    private Map<String,String> mapSorting = new HashMap<String,String>();

    private List<Article> lsCPUpload = new ArrayList<Article>();
    private String [] strExceptionArr = new String[2];

    private MappingAttribute mappingAttribute = new MappingAttribute();

    @Autowired
    public ArticleController(ArticleService articleService) {
        strExceptionArr[0] = "ArticleController";
        mapSorting();
        this.articleService = articleService;
    }

    private void mapSorting()
    {
        mapSorting.put("id","idArticle");
        mapSorting.put("title","titleArticle");
        mapSorting.put("id","idArticle");
        mapSorting.put("body","bodyArticle");
    }

    @GetMapping("/v1/article/new")
    public String createArticle(Model model, WebRequest request)
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        model.addAttribute("article", new ArticleDTO());
        return "article/create_article";
    }

    @GetMapping("/v1/article/edit/{id}")
    public String editArticle(Model model, WebRequest request, @PathVariable("id") Long id)
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        objectMapper = articleService.findById(id,request);
        ArticleDTO articleDTO = (objectMapper.get("data")==null?null:(ArticleDTO) objectMapper.get("data"));
        if((Boolean) objectMapper.get("success"))
        {
            ArticleDTO articleDTOForSelect = (ArticleDTO) objectMapper.get("data");
            model.addAttribute("article", articleDTO);
            return "article/edit_article";

        }
        else
        {
            model.addAttribute("article", new ArticleDTO());
            return "redirect:/api/usrmgmnt/article/default";
        }
    }
    @PostMapping("/v1/article/new")
    public String newArticle(@ModelAttribute(value = "article")
                          @Valid ArticleDTO articleDTO
            , BindingResult bindingResult
            , Model model
            , WebRequest request
    )
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }

        /* START VALIDATION */
        if(bindingResult.hasErrors())
        {
            model.addAttribute("article",articleDTO);
            model.addAttribute("status","error");

            return "article/create_article";
        }
        Boolean isValid = true;

        if(!isValid)
        {
            model.addAttribute("article",articleDTO);
            return "article/create_article";
        }
        /* END OF VALIDATION */

        Article article = modelMapper.map(articleDTO, new TypeToken<Article>() {}.getType());
        objectMapper = articleService.saveArticle(article,request);
        if(objectMapper.get("message").toString().equals(ConstantMessage.ERROR_FLOW_INVALID))//AUTO LOGOUT JIKA ADA PESAN INI
        {
            return "redirect:/api/check/logout";
        }

        if((Boolean) objectMapper.get("success"))
        {
            mappingAttribute.setAttribute(model,objectMapper);
            model.addAttribute("message","DATA BERHASIL DISIMPAN");
            Long idDataSave = objectMapper.get("idDataSave")==null?1:Long.parseLong(objectMapper.get("idDataSave").toString());
            return "redirect:/api/usrmgmnt/v1/article/fbpsb/0/asc/idArticle?columnFirst=idArticle&valueFirst="+idDataSave+"&sizeComponent=5";//LANGSUNG DITAMPILKAN FOKUS KE HASIL EDIT USER TADI
        }
        else
        {
            mappingAttribute.setErrorMessage(bindingResult,objectMapper.get("message").toString());
            model.addAttribute("article",new ArticleDTO());
            model.addAttribute("status","error");
            return "article/create_article";
        }
    }

    @PostMapping("/v1/article/edit/{id}")
    public String doRegis(@ModelAttribute("article")
                          @Valid ArticleDTO articleDTO
            , BindingResult bindingResult
            , Model model
            , WebRequest request
            , @PathVariable("idArticle") Long id
    )
    {
        /* START VALIDATION */
        if(bindingResult.hasErrors())
        {
            model.addAttribute("article",articleDTO);
            return "article/edit_article";
        }
        Boolean isValid = true;

        if(!isValid)
        {
            model.addAttribute("article",articleDTO);
            return "article/edit_article";
        }
        /* END OF VALIDATION */

        Article article = modelMapper.map(articleDTO, new TypeToken<Article>() {}.getType());
        objectMapper = articleService.updateArticle(id,article,request);
        if(objectMapper.get("message").toString().equals(ConstantMessage.ERROR_FLOW_INVALID))//AUTO LOGOUT JIKA ADA PESAN INI
        {
            return "redirect:/api/check/logout";
        }

        if((Boolean) objectMapper.get("success"))
        {
            mappingAttribute.setAttribute(model,objectMapper);
            model.addAttribute("article",new ArticleDTO());
            return "redirect:/api/usrmgmnt/v1/article/fbpsb/0/asc/idArticle?columnFirst=idArticle&valueFirst="+id+"&sizeComponent=5";//LANGSUNG DITAMPILKAN FOKUS KE HASIL EDIT USER TADI
        }
        else
        {
            mappingAttribute.setErrorMessage(bindingResult,objectMapper.get("message").toString());
            model.addAttribute("article",new ArticleDTO());
            return "article/edit_article";
        }
    }


    @GetMapping("/v1/article/default")
    public String getDefaultData(Model model,WebRequest request)
    {
        if(OtherConfig.getFlagSessionValidation().equals("y"))
        {
            mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
            if(request.getAttribute("USR_ID",1)==null){
                return "redirect:/api/check/logout";
            }
        }
        Pageable pageable = PageRequest.of(0,5, Sort.by("idArticle"));
        objectMapper = articleService.findAllArticle(pageable, request);
        mappingAttribute.setAttribute(model,objectMapper,request);

        model.addAttribute("article",new ArticleDTO());
        model.addAttribute("sortBy","idArticle");
        model.addAttribute("currentPage",1);
        model.addAttribute("asc","asc");
        model.addAttribute("columnFirst","");
        model.addAttribute("valueFirst","");
        model.addAttribute("sizeComponent",5);
        return "/article/article";
    }

    @GetMapping("/v1/article/fbpsb/{page}/{sort}/{sortby}")
    public String findByArticle(
            Model model,
            @PathVariable("page") Integer pagez,
            @PathVariable("sort") String sortz,
            @PathVariable("sortby") String sortzBy,
            @RequestParam String columnFirst,
            @RequestParam String valueFirst,
            @RequestParam String sizeComponent,
            WebRequest request
    ){
        sortzBy = mapSorting.get(sortzBy);
        sortzBy = sortzBy==null?"idArticle":sortzBy;
        Pageable pageable = PageRequest.of(pagez==0?pagez:pagez-1,Integer.parseInt(sizeComponent.equals("")?"5":sizeComponent), sortz.equals("asc")?Sort.by(sortzBy):Sort.by(sortzBy).descending());
        objectMapper = articleService.findByPage(pageable,request,columnFirst,valueFirst);
        mappingAttribute.setAttribute(model,objectMapper,request);
        model.addAttribute("article",new ArticleDTO());
        model.addAttribute("currentPage",pagez==0?1:pagez);
        model.addAttribute("sortBy", ManipulationMap.getKeyFromValue(mapSorting,sortzBy));
        model.addAttribute("columnFirst",columnFirst);
        model.addAttribute("valueFirst",valueFirst);
        model.addAttribute("sizeComponent",sizeComponent);

        return "/article/article";
    }
}
