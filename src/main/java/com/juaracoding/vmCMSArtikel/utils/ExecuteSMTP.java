package com.juaracoding.vmCMSArtikel.utils;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 18:02
@Last Modified 10/03/2023 18:02
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.configuration.OtherConfig;
import com.juaracoding.vmCMSArtikel.core.SMTPCore;

public class ExecuteSMTP {
    private String [] strException = new String[2];

    public ExecuteSMTP() {
        strException[0] = "ExecuteSMTP";
    }

    public Boolean sendSMTPToken(String mailAddress, String subject, String purpose, String token)
    {
        try
        {
            if(OtherConfig.getFlagSMTPActive().equalsIgnoreCase("y") && !mailAddress.equals(""))
            {
                String strContent = new ReadTextFileSB("\\data\\template-BCAF.html").getContentFile();
                strContent = strContent.replace("#$%^$%",purpose);
                strContent = strContent.replace("7tr54j",token);
                String [] strEmail = {mailAddress};
                SMTPCore sc = new SMTPCore();
                return  sc.sendSimpleMail(strEmail,
                        subject,
                        strContent,
                        "SSL");
            }
        }
        catch (Exception e)
        {
            strException[1]="sendToken(String mailAddress, String subject, String purpose, String token) -- LINE 35";
            LoggingFile.exceptionStringz(strException,e,OtherConfig.getFlagLogging());
            return false;
        }
        return true;
    }
}
