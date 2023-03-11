package com.juaracoding.vmCMSArtikel.repo;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 17:57
@Last Modified 10/03/2023 17:57
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepo extends JpaRepository<Menu,Long> {
    Page<Menu> findByIsDelete(Pageable page , byte byteIsDelete);
    Page<Menu> findByIsDeleteAndNamaMenuContainsIgnoreCase(Pageable page , byte byteIsDelete, String values);
    Page<Menu> findByIsDeleteAndPathMenuContainsIgnoreCase(Pageable page , byte byteIsDelete, String values);
    Page<Menu> findByIsDeleteAndEndPointContainsIgnoreCase(Pageable page , byte byteIsDelete, String values);
    Page<Menu> findByIsDeleteAndIdMenuContainsIgnoreCase(Pageable page , byte byteIsDelete, Long values);

}
