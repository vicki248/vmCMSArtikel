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

@Entity
@Table(name = "MstMenuHeader")
public class MenuHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDMenuHeader")
    private Long idMenuHeader;

    @Column(name = "NamaMenuHeader")
    private String namaMenuHeader;

    @Column(name = "DeskripsiMenuHeader")
    private String deskripsiMenuHeader;

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


    public Long getIdMenuHeader() {
        return idMenuHeader;
    }

    public void setIdMenuHeader(Long idMenuHeader) {
        this.idMenuHeader = idMenuHeader;
    }

    public String getNamaMenuHeader() {
        return namaMenuHeader;
    }

    public void setNamaMenuHeader(String namaMenuHeader) {
        this.namaMenuHeader = namaMenuHeader;
    }

    public String getDeskripsiMenuHeader() {
        return deskripsiMenuHeader;
    }

    public void setDeskripsiMenuHeader(String deskripsiMenuHeader) {
        this.deskripsiMenuHeader = deskripsiMenuHeader;
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
