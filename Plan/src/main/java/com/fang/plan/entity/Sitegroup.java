package com.fang.plan.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
/**
 * 
 * @author ：CHANG
 * 
 * */
@TableName("Sitegroup")
public class Sitegroup implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @TableId(type=IdType.AUTO)//主键策略
  private int id;
	private String sitegrouppy;
	private String sitegroupname;
	
	public void setId(String id){
	this.id=Integer.parseInt((id==null||id.equals("")?"0":id));
	}
	public int getId(){
		return id;
	}
  
  public String getSitegrouppy() {
    return sitegrouppy;
  }
  
  public void setSitegrouppy(String sitegrouppy) {
    this.sitegrouppy = sitegrouppy;
  }
  
  public String getSitegroupname() {
    return sitegroupname;
  }
  
  public void setSitegroupname(String sitegroupname) {
    this.sitegroupname = sitegroupname;
  }
	

  
}
