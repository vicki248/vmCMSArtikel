package com.juaracoding.vmCMSArtikel.model;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 17:52
@Last Modified 10/03/2023 17:52
Version 1.1
*/

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MstMenu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDMenu")
    private Long idMenu;

    @Column(name = "NamaMenu",nullable = false,length = 25)
    private String namaMenu;

    @Column(name = "PathMenu",nullable = false,length = 50)
    private String pathMenu;

    @Column(name = "EndPoints",nullable = false,length = 30)
    private String endPoint;

    @Column(name ="CreatedDate" , nullable = false)
    private Date createdDate = new Date();

    @Column(name = "CreatedBy", nullable = false)
    private Integer createdBy=1;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;
    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "IsDelete", nullable = false)
    private Byte isDelete = 1;
    /*
        end audit trails
     */

    @ManyToMany(mappedBy = "listMenuAkses")
    private List<Akses> listAksesMenu;

    @ManyToOne
    @JoinColumn(name = "IDMenuHeader")
    private MenuHeader menuHeader;

    public MenuHeader getMenuHeader() {
        return menuHeader;
    }

    public void setMenuHeader(MenuHeader menuHeader) {
        this.menuHeader = menuHeader;
    }

    public List<Akses> getListAksesMenu() {
        return listAksesMenu;
    }

    public void setListAksesMenu(List<Akses> listAksesMenu) {
        this.listAksesMenu = listAksesMenu;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}
