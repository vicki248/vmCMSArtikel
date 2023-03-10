package com.juaracoding.vmCMSArtikel.dto;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 18:08
@Last Modified 10/03/2023 18:08
Version 1.1
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.juaracoding.vmCMSArtikel.utils.ConstantMessage;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class MenuDTO {
    private Long idMenu;
    @NotNull
    @NotEmpty
    @Length(message = ConstantMessage.WARNING_MENU_NAME_LENGTH,max = 25)
    private String namaMenu;


    @NotNull
    @NotEmpty
    @Length(message = ConstantMessage.WARNING_MENU_NAME_LENGTH,max = 50)
    private String pathMenu;

    @NotNull
    @NotEmpty
    @Length(message = ConstantMessage.WARNING_MENU_NAME_LENGTH,max = 50)
    private String endPoint;


    @JsonIgnoreProperties("listMenuAkses")
    private List<AksesDTO> listAksesMenu;

    @NotNull
    private MenuHeaderDTO menuHeader;

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public MenuHeaderDTO getMenuHeader() {
        return menuHeader;
    }

    public void setMenuHeader(MenuHeaderDTO menuHeader) {
        this.menuHeader = menuHeader;
    }

    public List<AksesDTO> getListAksesMenu() {
        return listAksesMenu;
    }

    public void setListAksesMenu(List<AksesDTO> listAksesMenu) {
        this.listAksesMenu = listAksesMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public String getPathMenu() {
        return pathMenu;
    }

    public void setPathMenu(String pathMenu) {
        this.pathMenu = pathMenu;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
