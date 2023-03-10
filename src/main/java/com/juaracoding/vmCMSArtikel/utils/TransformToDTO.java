package com.juaracoding.vmCMSArtikel.utils;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 14:33
@Last Modified 10/03/2023 14:33
Version 1.1
*/

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransformToDTO {
    private String sortBy = "";
    private String sort = "";
    public Map<String,Object> transformObject(Map<String,Object> mapz, List ls, Page page)
    {
        mapz.put("content",ls);
        mapz.put("currentPage",page.getNumber());
        mapz.put("totalItems",page.getTotalElements());
        mapz.put("totalPages",page.getTotalPages());
        mapz.put("sort",page.getSort());
        mapz.put("numberOfElements",page.getNumberOfElements());

        return mapz;
    }
    public Map<String,Object> transformObject(Map<String,Object> mapz, List ls, Page page,Map<String,String> searchParams)//<PENAMBAHAN 07-03-2023>
    {
        sortBy = page.getSort().toString().split(":")[0];
        sortBy = sortBy.equals("UNSORTED")?"id":sortBy;
        sort   = sortBy.equals("UNSORTED")?"asc":page.getSort().toString().split(":")[1];
        mapz.put("content",ls);
        mapz.put("totalItems",page.getTotalElements());
        mapz.put("totalPages",page.getTotalPages());
        mapz.put("sort",sort.trim().toLowerCase());
        mapz.put("numberOfElements",page.getNumberOfElements());
        mapz.put("searchParam",searchParams);

        return mapz;
    }

    public Map<String,Object> transformObjectDataEmpty(Map<String,Object> mapz, Pageable pageable, Map<String,String> searchParams)//<PENAMBAHAN 07-03-2023>
    {
        sortBy = pageable.getSort().toString().split(":")[0];
        sort   = sortBy.equals("UNSORTED")?"asc":pageable.getSort().toString().split(":")[1];

        mapz.put("content",new ArrayList<>());
        mapz.put("totalItems",0);
        mapz.put("totalPages",0);
        mapz.put("sort",sort.trim().toLowerCase());
        mapz.put("numberOfElements",0);
        mapz.put("searchParam",searchParams);

        return mapz;
    }

    public Map<String,Object> transformObjectDataEmpty(Map<String,Object> mapz, Map<String,String> searchParams)//<PENAMBAHAN 07-03-2023>
    {
        mapz.put("content",new Object());
        mapz.put("totalItems",0);
        mapz.put("totalPages",0);
        mapz.put("sort","asc");
        mapz.put("numberOfElements",0);
        mapz.put("searchParam",searchParams);

        return mapz;
    }
    public Map<String,Object> transformObjectDataSave(Map<String,Object> mapz,Long idDataSave, Map<String,String> searchParams)//<PENAMBAHAN 07-03-2023>
    {
        mapz.put("content",new Object());
        mapz.put("totalItems",0);
        mapz.put("totalPages",0);
        mapz.put("sort","asc");
        mapz.put("idDataSave",idDataSave);
        mapz.put("numberOfElements",0);
        mapz.put("searchParam",searchParams);

        return mapz;
    }
}
