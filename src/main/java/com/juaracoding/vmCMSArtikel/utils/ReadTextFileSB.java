package com.juaracoding.vmCMSArtikel.utils;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 18:03
@Last Modified 10/03/2023 18:03
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.handler.ResourceNotFoundException;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

public class ReadTextFileSB {
    private FileInputStream fInput;
    private BufferedInputStream bInput;
    private String[] exceptionString = new String[2];
    private String contentFile;
    private byte[] contentOfFile;
    private String filePathz ;

    public ReadTextFileSB(String pathFiles) throws ResourceNotFoundException {
        setContentFile(pathFiles);
    }

    public void setContentFile(String pathFile) throws ResourceNotFoundException {

        try {
            File filez = ResourceUtils.getFile("classpath:"+pathFile);
            fInput = new FileInputStream(filez);
            bInput = new BufferedInputStream(fInput);
            byte[] bdata = FileCopyUtils.copyToByteArray(bInput);
            String data = new String(bdata, StandardCharsets.UTF_8);
            this.contentOfFile = bdata;
            this.contentFile = new String(contentOfFile, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        finally {
            try {
                fInput.close();
                bInput.close();
            } catch (Exception e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        }
    }

    public String getContentFile()
    {
        return contentFile;
    }

    public byte[] getByteOfFile()
    {
        return contentOfFile;
    }

    public static void main(String[] args) throws ResourceNotFoundException {
        ReadTextFileSB rtfSB = new ReadTextFileSB("\\data\\MailHTMLExample.txt");
        System.out.println("STRING => "+rtfSB.getContentFile());
        System.out.println("BYTE FILE => "+rtfSB.getByteOfFile());
    }
}
