package com.juaracoding.vmCMSArtikel.controller;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 18:11
@Last Modified 10/03/2023 18:11
Version 1.1
*/

import cn.apiclub.captcha.Captcha;
import com.juaracoding.vmCMSArtikel.model.Userz;
import com.juaracoding.vmCMSArtikel.service.StudentService;
import com.juaracoding.vmCMSArtikel.utils.CaptchaUtils;
import com.juaracoding.vmCMSArtikel.utils.MappingAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {
    private StudentService studentService;
    private MappingAttribute mappingAttribute = new MappingAttribute();
    private Map<String,Object> objectMapper = new HashMap<String,Object>();

    @Autowired
    public MainController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String pageTwo(Model model, WebRequest request)
    {
        model.addAttribute("students", studentService.getAllStudents());

        Captcha captcha = CaptchaUtils.createCaptcha(150, 60);

        Userz users = new Userz();
        users.setHidden(captcha.getAnswer());
        users.setCaptcha("");
        users.setImage(CaptchaUtils.encodeBase64(captcha));
        model.addAttribute("usr",users);
        if(request.getAttribute("USR_ID",1)!=null)
        {
            mappingAttribute.setAttribute(model,objectMapper,request);
            return "article/article";
        }
//        return "authz_signin";
        return "article/article";
    }
}
