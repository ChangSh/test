package com.test.mysys.system.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.test.mysys.system.model.Menu;



public class Tree {  
    private StringBuffer html = new StringBuffer();  
    private List<Menu> Menus;  
      
    public Tree(List<Menu> Menus){  
        this.Menus = Menus;  
    }  
      
    public String buildTree(){  
        html.append("<ul class=\"topnav\" >");  
        for (Menu menu : Menus) {  
            Long id = menu.getId();  
            if (menu.getPid() == null || menu.getPid() == 0) {
            	String s="";
            	if (StringUtils.isNotEmpty(menu.getImagesrc())){
            		s = "<img src=\"/mysys/static/images/"+menu.getImagesrc()+"\">";
            	}
            	if (StringUtils.isNotEmpty(menu.getUrl())){
            		html.append("\r\n<li id='" + id + "'><a href=\"javascript:go('"+menu.getUrl()+"');\">"+s+ menu.getMenuname()+ "</a>");
            	}else{
            		html.append("\r\n<li id='" + id + "'><a href=\"javascript:void(0)\">"+s+ menu.getMenuname()+ "</a>");
            	}
                build(menu);  
                html.append("\r\n</li>"); 
            }  
        }  
        html.append("\r\n</ul>");  
        return html.toString();  
    }  
      
    private void build(Menu Menu){  
        List<Menu> children = getChildren(Menu);  
        if (!children.isEmpty()) {  
            html.append("\r\n<ul>");  
            for (Menu child : children) {  
            	Long id = child.getId();  
                html.append("\r\n<li id='" + id + "'><a href=\"javascript:go('"+child.getUrl()+"');\">" + child.getMenuname()+ "</a></li>");  
                build(child);  
            }  
            html.append("\r\n</ul>");  
        }   
    }  
      
    private List<Menu> getChildren(Menu Menu){  
        List<Menu> children = new ArrayList<Menu>();  
        Long id = Menu.getId();  
        for (Menu child : Menus) {  
            if (id.equals(child.getPid())) {  
                children.add(child);  
            }  
        }  
        return children;  
    }  
}  