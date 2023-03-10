package com.juaracoding.vmCMSArtikel.utils;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 12:11
@Last Modified 10/03/2023 12:11
Version 1.1
*/

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Map;

public class MappingAttribute {
    public void setAttribute(Model model, Map<String,Object> mapz)
    {
        model.addAttribute("message", mapz.get("message"));
        model.addAttribute("status", mapz.get("status"));
        model.addAttribute("data", mapz.get("data")==null?"":mapz.get("data"));
        model.addAttribute("timestamp", new Date());
        model.addAttribute("success",mapz.get("success"));
        if(mapz.get("errorCode") != null)
        {
            model.addAttribute("errorCode",mapz.get("errorCode"));
            model.addAttribute("path",mapz.get("path"));
        }
    }

    //method digunakan setelah user berhasil login untuk validasi session di seluruh API Transaction
    public void setAttribute(Model model, Map<String,Object> mapz, WebRequest request)
    {
        model.addAttribute("message", mapz.get("message"));
        model.addAttribute("status", mapz.get("status"));
        model.addAttribute("data", mapz.get("data")==null?"":mapz.get("data"));
        model.addAttribute("timestamp", new Date());
        model.addAttribute("success",mapz.get("success"));
        model.addAttribute("USR_IDZ",request.getAttribute("USR_ID",1));//panggil di html dengan nama ${USR_IDZ}
        model.addAttribute("NO_HPZ",request.getAttribute("NO_HP",1));//panggil di web dengan nama ${NO_HPZ}
        model.addAttribute("EMAILZ",request.getAttribute("EMAIL",1));//panggil di web dengan nama ${EMAILZ}
        model.addAttribute("USR_NAMEZ",request.getAttribute("USR_NAME",1));//panggil di web dengan nama ${USR_NAMEZ}
        model.addAttribute("HTML_MENUZ",request.getAttribute("HTML_MENU",1));//panggil di web dengan nama ${HTML_MENUZ}<07 03 2023>
        if(mapz.get("errorCode") != null)
        {
            model.addAttribute("errorCode",mapz.get("errorCode"));
            model.addAttribute("path",mapz.get("path"));
        }
    }

    public BindingResult setErrorMessage(BindingResult br, String  strErrorMessage)
    {
        br.addError(new ObjectError("globalError",strErrorMessage));
        return br;
    }
}
