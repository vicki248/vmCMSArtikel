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
import com.juaracoding.vmCMSArtikel.repo.ArticleRepo;
import com.juaracoding.vmCMSArtikel.repo.CategoryArticleRepo;
import com.juaracoding.vmCMSArtikel.service.ArticleService;
import com.juaracoding.vmCMSArtikel.service.CategoryArticleService;
import com.juaracoding.vmCMSArtikel.utils.ManipulationMap;
import com.juaracoding.vmCMSArtikel.utils.MappingAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@Controller
@RequestMapping("/api")
public class ArticleController {
    private ArticleService articleService;
    private MappingAttribute mappingAttribute = new MappingAttribute();
    private Map<String,Object> objectMapper = new HashMap<String,Object>();
    @Autowired
    private CategoryArticleService categoryArticleService;

    @Autowired
    private CategoryArticleRepo categoryArticleRepo;

    @Autowired
    private ArticleRepo articleRepo;
    private List<Article> lsCPUpload = new ArrayList<Article>();

    private String [] strExceptionArr = new String[2];
    private Map<String,String> mapSorting = new HashMap<String,String>();
    @Autowired
    public ArticleController(ArticleService articleService, CategoryArticleService categoryArticleService) {
        strExceptionArr[0] ="ArticleController";
        mapSorting();
        this.articleService = articleService;
        this.categoryArticleService = categoryArticleService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model, WebRequest request) {
        if (OtherConfig.getFlagSessionValidation().equals("y")) {
            mappingAttribute.setAttribute(model, objectMapper, request);//untuk set session
            if (request.getAttribute("USR_ID", 1) == null) {
                return "redirect:/api/check/logout";
            }
        }
        Pageable pageable = PageRequest.of(0,5, Sort.by("idArticle"));
        objectMapper = articleService.findAllArticle(pageable,request);
        mappingAttribute.setAttribute(model,objectMapper,request);

        model.addAttribute("article",new ArticleDTO());
        model.addAttribute("sortBy","idArticle");
        model.addAttribute("currentPage",1);
        model.addAttribute("asc","asc");
        model.addAttribute("columnFirst","");
        model.addAttribute("valueFirst","");
        model.addAttribute("sizeComponent",5);
//        return "/article/article";
        return findPaginated(1, "idArticle", "asc", model,request);

    }

    @GetMapping("/v1/article/fbpsb/{page}/{sort}/{sortby}")
    public String findBYArticle(
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
        if (valueFirst == null || valueFirst.trim().isEmpty()) {
            columnFirst = ""; // Jika valueFirst kosong, maka kolom dicari di-set kosong
        }
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

    @GetMapping("/showNewArticleForm")
    public String showNewArticleForm(Model model, WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        model.addAttribute("categories", categoryArticleRepo.findAll());//untuk parent nya

        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }

        // create model attribute to bind form data
        Article article = new Article();
        model.addAttribute("article", article);
//        model.addAttribute("article", new ArticleDTO());
        return "article/create_article";
    }

    @PostMapping("/saveArticle")
    public String saveArticle(@ModelAttribute("article") Article article,Model model, WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }
        // save article to database
        article.setCreatedBy(Integer.parseInt(request.getAttribute("USR_ID",1).toString()));
        articleService.saveArticle(article);
        return "redirect:/api/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model,WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        model.addAttribute("categories", categoryArticleRepo.findAll());//untuk parent nya
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }
        // get article from the service
        Article article = articleService.getArticleById(id);

        // set article as a model attribute to pre-populate the form
        model.addAttribute("article", article);
        return "/article/edit_article";
    }

    @GetMapping("/v1/article/edit/{id}")
    public String editArticle(Model model,WebRequest request,@PathVariable("id") Long id)
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
            model.addAttribute("listCategoryArticle", categoryArticleService.findAllCategoryArticle());
//            System.out.println("selectedValues -> "+articleDTOForSelect.getArticleHeader().getIdArticleHeader());
            model.addAttribute("selectedValues", articleDTOForSelect.getNameCategoryArticle());
            return "article/edit_article";

        }
        else
        {
            model.addAttribute("article", new ArticleDTO());
            return "redirect:/api/article/default";
        }
    }

    @GetMapping("/deleteArticle/{id}")
    public String deleteArticle(@PathVariable (value = "id") long id,Model model,WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }
        // call delete article method
        articleService.deleteArticleById(id);
        return "redirect:/api/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model,WebRequest request) {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        if(request.getAttribute("USR_ID",1)==null){
            return "redirect:/api/check/logout";
        }

        int pageSize = 5;

        Page<Article> page = articleService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Article> articles = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("articles", articles);
        return "article/article";
    }
    private void mapSorting()
    {
        mapSorting.put("id","idArticle");
        mapSorting.put("title","titleArticle");
        mapSorting.put("body","bodyArticle");
        mapSorting.put("category","category");
    }
}
