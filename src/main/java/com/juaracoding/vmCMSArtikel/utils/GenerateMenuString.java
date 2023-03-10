package com.juaracoding.vmCMSArtikel.utils;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 18:15
@Last Modified 10/03/2023 18:15
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.model.Akses;
import com.juaracoding.vmCMSArtikel.model.Menu;
import com.juaracoding.vmCMSArtikel.model.MenuHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateMenuString {
    private StringBuilder sBuild = new StringBuilder();
    private List<MenuHeader> listMenuHeader = new ArrayList<MenuHeader>();

    public String menuInnerHtml(Akses akses)
    {
        Map<String,String> mapx = new HashMap<String,String>();
        List<Menu> listMenuz = akses.getListMenuAkses();
        String namaMenuHeader = "";
        String linkMenu = "";
        String strListMenuHtml = "";
        String [] strLinkArr = null;
        String [] strSplitLink= null;

        for (int i=0;i<listMenuz.size();i++)
        {
            namaMenuHeader = listMenuz.get(i).getMenuHeader().getNamaMenuHeader();
            sBuild.setLength(0);
            linkMenu = sBuild.append(listMenuz.get(i).getPathMenu()).append("-").
                    append(listMenuz.get(i).getNamaMenu()).toString();//index 0  path menu, 1 nama menu

            if(mapx.get(namaMenuHeader)==null)
            {
                mapx.put(namaMenuHeader,linkMenu);
            }
            else
            {
                sBuild.setLength(0);
                linkMenu = sBuild.append(mapx.get(namaMenuHeader)).append("#").append(linkMenu).toString();
                mapx.put(namaMenuHeader,linkMenu);
            }
        }

        for (Map.Entry<String,String> strMap:
                mapx.entrySet()) {
            sBuild.setLength(0);
            strListMenuHtml = sBuild.append(strListMenuHtml).append("<li>")
                    .append("<a href=\"#\">").append(strMap.getKey()).append("</a>")
                    .append("<ul class=\"menu-dropdown\">").toString();
            linkMenu = strMap.getValue();
//            linkMenu = linkMenu.substring(0,linkMenu.length()-1);//menghilangkan tanda # di akhir
            strLinkArr = linkMenu.split("#");
            for (int i=0;i<strLinkArr.length;i++)
            {
                strSplitLink = strLinkArr[i].split("-");
                sBuild.setLength(0);
                strListMenuHtml = sBuild.append(strListMenuHtml).
                        append("<li>").append("<a href=\"").append(strSplitLink[0]).append("\">")
                        .append(strSplitLink[1]).append("</a>").append("</li>").toString();
            }
            sBuild.setLength(0);
            strListMenuHtml = sBuild.append(strListMenuHtml)
                    .append("</ul>").append("</li>").toString();
        }
//        System.out.println("STRING HTML : \n "+strListMenuHtml);
        return strListMenuHtml;
    }
}
