package com.juaracoding.vmCMSArtikel.controller;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 18:11
@Last Modified 10/03/2023 18:11
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.utils.MappingAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/menu")
public class RedirectMenuController {
    private MappingAttribute mappingAttribute = new MappingAttribute();
    private Map<String,Object> objectMapper = new HashMap<>();

//    @GetMapping("/salesone")
//    public String getSalesOne(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        model.addAttribute("usr",new UserDTO());
//        return "sales/sales_one";
//    }
//    @GetMapping("/salestwo")
//    public String getSalesTwo(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "sales/sales_two";
//    }
//    @GetMapping("/salesthree")
//    public String getSalesThree(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "sales/sales_three";
//    }
//    @GetMapping("/salesfour")
//    public String getSalesFour(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "sales/sales_four";
//    }
//
//    @GetMapping("/hrone")
//    public String getHROne(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "hrd/hr_one";
//    }
//    @GetMapping("/hrtwo")
//    public String getHRTwo(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "hrd/hr_two";
//    }
//    @GetMapping("/hrthree")
//    public String getHRThree(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "hrd/hr_three";
//    }
//    @GetMapping("/hrfour")
//    public String getHRFour(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "hrd/hr_four";
//    }
//
//    @GetMapping("/financeone")
//    public String getFinanceOne(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "finance/finance_one";
//    }
//    @GetMapping("/financetwo")
//    public String getFinanceTwo(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "finance/finance_two";
//    }
//    @GetMapping("/financetfinanceee")
//    public String getFinanceTfinanceee(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "finance/finance_tfinanceee";
//    }
//    @GetMapping("/financefour")
//    public String getFinanceFour(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "finance/finance_four";
//    }
//
//    @GetMapping("/absen")
//    public String getAbsensi(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "global/absen";
//    }
//
//    @GetMapping("/research")
//    public String getResearch(Model model, WebRequest request)
//    {
//        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
//        return "research";
//    }

    @GetMapping("/articles")
    public String getArticles(Model model, WebRequest request)
    {
        mappingAttribute.setAttribute(model,objectMapper,request);//untuk set session
        return "articles";
    }
}
