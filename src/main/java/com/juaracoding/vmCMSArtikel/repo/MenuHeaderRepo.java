package com.juaracoding.vmCMSArtikel.repo;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 17:56
@Last Modified 10/03/2023 17:56
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.model.MenuHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuHeaderRepo extends JpaRepository<MenuHeader,Long> {

    Page<MenuHeader> findByIsDelete(Pageable page , byte byteIsDelete);
    List<MenuHeader> findByIsDelete(byte byteIsDelete);
    Page<MenuHeader> findByIsDeleteAndIdMenuHeader(Pageable pageable, Byte isDelete, String valueparamValue);
    Page<MenuHeader> findByIsDeleteAndNamaMenuHeader(Pageable pageable,Byte isDelete,String valueparamValue);
    Page<MenuHeader> findByIsDeleteAndDeskripsiMenuHeader(Pageable pageable,Byte isDelete,String valueparamValue);
}
