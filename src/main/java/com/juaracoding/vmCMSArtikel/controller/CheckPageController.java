package com.juaracoding.vmCMSArtikel.controller;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 17:47
@Last Modified 10/03/2023 17:47
Version 1.1
*/

import cn.apiclub.captcha.Captcha;
import com.juaracoding.vmCMSArtikel.dto.ForgetPasswordDTO;
import com.juaracoding.vmCMSArtikel.dto.UserDTO;
import com.juaracoding.vmCMSArtikel.model.Userz;
import com.juaracoding.vmCMSArtikel.utils.CaptchaUtils;
import com.juaracoding.vmCMSArtikel.utils.MappingAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/check")
public class CheckPageController {
    private Map<String,Object> objectMapper = new HashMap<String,Object>();
    private MappingAttribute mappingAttribute = new MappingAttribute();

    @GetMapping("/home")
    public String pageHome(Model model, WebRequest request)
    {
        mappingAttribute.setAttribute(model,objectMapper,request);
        return "home";
    }

    @GetMapping("/signin")
    public String pageOne(Model model)
    {
        Captcha captcha = CaptchaUtils.createCaptcha(150, 60);
        Userz users = new Userz();
        users.setHidden(captcha.getAnswer());
        users.setCaptcha("");
        users.setImage(CaptchaUtils.encodeBase64(captcha));
        model.addAttribute("usr",users);
        return "authz_signin";
    }

    @GetMapping("/register")
    public String pageTwo(Model model)
    {
        UserDTO users = new UserDTO();
        model.addAttribute("usr",users);
        return "authz_register";
    }

    @GetMapping("/verify")
    public String pageThree(Model model)
    {
        model.addAttribute("usr",new Userz());
        return "authz_verifikasi";
    }

    @GetMapping("/admin")
    public String pageFour(Model model, WebRequest request)
    {
        mappingAttribute.setAttribute(model,objectMapper,request);
        return "dashboard";
    }
    @GetMapping("/styledPage")
    public String pageFive(Model model)
    {
        model.addAttribute("name", "Paulo");
        return "styledPage";
    }

    @GetMapping("/index")
    public String pageSix()
    {
        return "index";
    }

    @GetMapping("/forgetpwd")
    public String pageSeven(Model model)
    {
        ForgetPasswordDTO forgetPasswordDTO = new ForgetPasswordDTO();
        model.addAttribute("forgetpwd",forgetPasswordDTO);
        return "authz_forget_pwd_email";
    }

    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
//        return "redirect:/api/check/signin";
        return "redirect:/";
    }
}
