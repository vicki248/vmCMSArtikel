package com.juaracoding.vmCMSArtikel.handler;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 18:14
@Last Modified 10/03/2023 18:14
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.utils.ConstantMessage;

import java.util.regex.Pattern;

public class FormatValidation {
    public static Boolean emailFormatValidation(String email,String writeNull)
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_EMAIL_STANDARD_RFC5322)
                .matcher(email)
                .matches()))
        {
            return false;
        }
        return true;
    }

    public static Boolean phoneNumberFormatValidation(String phoneNumner,String writeNull)
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_PHONE)
                .matcher(phoneNumner)
                .matches()))
        {
            return false;
        }
        return true;
    }

    public static Boolean dateFormatYYYYMMDDValidation(String date, String writeNull)
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_DATE_YYYYMMDD)
                .matcher(date)
                .matches()))
        {
            return false;
        }
        return true;
    }
}
