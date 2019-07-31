package com.test.edusys.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.edusys.system.model.Menu;
import com.test.edusys.system.service.MenuGetService;

@Controller
@RequestMapping(value="/system/menuList")
public class MenuListController {
    
	@Resource
	private MenuGetService service;
	@RequestMapping("/getmenu")
	@ResponseBody
	public Map<String, Object> getmenu(String userid,HttpServletRequest req){
		String html="";
		req.getContextPath();
		List<Menu>menumodelgen=service.QuerymenuGenbasicuserid(userid);
		
		html +="<ul class = 'ce'>";
		 for (int i = 0; i <menumodelgen.size(); i++)
		 {   
			 System.out.print(menumodelgen.get(i).getId());
	    	 System.out.print(menumodelgen.get(i).getUrl());
			/* String name=URLDecoder.decode(menumodelgen.get(i).getMenuname(),"UTF-8");*/
			 html +="<li>";
			 if (menumodelgen.get(i).getId()!= null&&StringUtils.isEmpty(menumodelgen.get(i).getUrl()))
             {    
		    	  
		    	  html +="<a>";
		          html +=menumodelgen.get(i).getMenuname()+"</a>";
                  html += AddTree(userid,menumodelgen.get(i).getId(),req.getContextPath());
             }
		      else 	
		     {    
	           html +="<a href='"+req.getContextPath()+"/"+menumodelgen.get(i).getUrl()+"' target=\'home\' >"+menumodelgen.get(i).getMenuname()+"</a>";
	           html += AddTree(userid,menumodelgen.get(i).getId(),req.getContextPath());  
		    	 
		     }
		     
		    
			 html +="</li>";
		 }
		 html += "</ul>";
		 
		 
		 Map<String, Object> map = new HashMap<String, Object>();
	     map.put("html",html);
		 return map;	
	}	
	public String AddTree(String userid, long moduleid,String lj)
    {
        String html = "";
        List<Menu>menumodelchild=service.QuerymenuGetChild(userid, moduleid);
        
        if (menumodelchild.size() > 0)
        {
            html += "<ul class=\'er\'>";
            for (int y = 0; y <menumodelchild.size(); y++)
            {   
                html += "<li><cite></cite><a href='"+lj+"/"+menumodelchild.get(y).getUrl()+"' target=\'home\' >"+menumodelchild.get(y).getMenuname()+"</a><i></i>";
                List<Menu>menumodelchild1=service.QuerymenuGetChild(userid,menumodelchild.get(y).getId());
                if (menumodelchild1.size() > 0)
                {
                    html += "<li>";
                    AddTree(userid,menumodelchild.get(y).getId(),lj);
                    html += "</li>";
                    html += "</ul>";
                }
                html += "</li>";
            }
            html += "</ul>";

        }
        return html;
    }
	
}
