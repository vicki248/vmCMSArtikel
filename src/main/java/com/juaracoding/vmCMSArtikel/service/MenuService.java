package com.juaracoding.vmCMSArtikel.service;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 18:00
@Last Modified 10/03/2023 18:00
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.configuration.OtherConfig;
import com.juaracoding.vmCMSArtikel.handler.ResourceNotFoundException;
import com.juaracoding.vmCMSArtikel.handler.ResponseHandler;
import com.juaracoding.vmCMSArtikel.model.Menu;
import com.juaracoding.vmCMSArtikel.repo.MenuRepo;
import com.juaracoding.vmCMSArtikel.utils.ConstantMessage;
import com.juaracoding.vmCMSArtikel.utils.LoggingFile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/*
    KODE MODUL 03
 */
@Service
@Transactional
public class MenuService {
    private MenuRepo menuRepo;

    private String[] strExceptionArr = new String[2];

    public MenuService(MenuRepo menuRepo) {
        strExceptionArr[0] = "MenuService";
        this.menuRepo = menuRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveMenu(Menu menu, WebRequest request) {
        String strMessage = ConstantMessage.SUCCESS_SAVE;
        try {
            menuRepo.save(menu);
        } catch (Exception e) {
            strExceptionArr[1] = "savePersonal(Personal personal) --- LINE 50";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST, null, "FE03001", request);
        }
        return new ResponseHandler().generateModelAttribut(strMessage,
                HttpStatus.CREATED, null, null, request);
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveUploadFile(List<Menu> listMenu,
                                              MultipartFile multipartFile,
                                              WebRequest request) throws Exception {
        //url -> OtherConfig.
        List<Menu> listMenuResult = null;
        String strMessage = ConstantMessage.SUCCESS_SAVE;

        try {
            listMenuResult = menuRepo.saveAll(listMenu);
            if (listMenuResult.size() == 0) {
                strExceptionArr[1] = "saveUploadFile(List<Menu> listMenu, MultipartFile multipartFile, WebRequest request) --- LINE 67";
                LoggingFile.exceptionStringz(strExceptionArr, new ResourceNotFoundException("FILE KOSONG"), OtherConfig.getFlagLogging());
                return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_EMPTY_FILE + " -- " + multipartFile.getOriginalFilename(),
                        HttpStatus.BAD_REQUEST, null, "FV03001", request);
            }
        } catch (Exception e) {
            strExceptionArr[1] = "saveUploadFile(List<Menu> listMenu, MultipartFile multipartFile, WebRequest request) --- LINE 75";
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateModelAttribut(ConstantMessage.ERROR_SAVE_FAILED,
                    HttpStatus.BAD_REQUEST, null, "FE03002", request);
        }
        return new ResponseHandler().generateModelAttribut(strMessage,
                HttpStatus.CREATED, null, null, request);
    }
}
