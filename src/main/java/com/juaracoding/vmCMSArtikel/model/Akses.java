package com.juaracoding.vmCMSArtikel.model;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 17:55
@Last Modified 10/03/2023 17:55
Version 1.1
*/

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MstAkses")
public class Akses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDAkses")
    private Long idAkses;

    @Column(name = "NamaAkses")
    private String namaAkses;

    @ManyToMany
    @JoinTable(name = "MapAksesMenu",
            joinColumns = {
                    @JoinColumn(name = "IDAkses",referencedColumnName = "IDAkses")}, inverseJoinColumns = {
            @JoinColumn (name = "IDMenu",referencedColumnName = "IDMenu")}, uniqueConstraints = @UniqueConstraint(columnNames = {
            "IDAkses", "IDMenu" }))
    private List<Menu> listMenuAkses;

    @ManyToOne
    @JoinColumn(name = "IDDivisi")
    private Divisi divisi;

    @Column(name ="CreatedDate" , nullable = false)
    private Date createdDate = new Date();

    @Column(name = "CreatedBy", nullable = false)
    private Integer createdBy=1;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;
    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "IsDelete", nullable = false)
    private Byte isDelete = 0;//khusus disini default 0 karena setelah verifikasi baru di update menjadi 1
    /*
        end audit trails
     */

    public Divisi getDivisi() {
        return divisi;
    }

    public void setDivisi(Divisi divisi) {
        this.divisi = divisi;
    }

    public List<Menu> getListMenuAkses() {
        return listMenuAkses;
    }

    public void setListMenuAkses(List<Menu> listMenuAkses) {
        this.listMenuAkses = listMenuAkses;
    }

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
