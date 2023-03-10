package com.juaracoding.vmCMSArtikel.dto;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 18:09
@Last Modified 10/03/2023 18:09
Version 1.1
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.juaracoding.vmCMSArtikel.model.Divisi;
import com.juaracoding.vmCMSArtikel.model.Menu;

import java.util.List;

public class AksesDTO {
    private Long idAkses;

    private String namaAkses;

    @JsonIgnoreProperties("listAksesMenu")
    private List<Menu> listMenuAkses;

    private Divisi divisi;

    public Long getIdAkses() {
        return idAkses;
    }

    public void setIdAkses(Long idAkses) {
        this.idAkses = idAkses;
    }

    public String getNamaAkses() {
        return namaAkses;
    }

    public void setNamaAkses(String namaAkses) {
        this.namaAkses = namaAkses;
    }

    public List<Menu> getListMenuAkses() {
        return listMenuAkses;
    }

    public void setListMenuAkses(List<Menu> listMenuAkses) {
        this.listMenuAkses = listMenuAkses;
    }

    public Divisi getDivisi() {
        return divisi;
    }

    public void setDivisi(Divisi divisi) {
        this.divisi = divisi;
    }
}
