package com.fang.plan.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("Role")
public class Role implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  @TableId(type=IdType.AUTO)//主键策略
  private int roleid;
	private String rolename;
	private String description;
	public void setRoleid(String roleid){
	this.roleid=Integer.parseInt((roleid==null||roleid.equals("")?"0":roleid));
	}
	public int getRoleid(){
		return roleid;
	}
	
  public void setRolename(String rolename){
	this.rolename=rolename;
	}
	public String getRolename(){
		return rolename;
	}
	public void setDescription(String description){
	this.description=description;
	}
	public String getDescription(){
		return description;
	}

  
}
