package com.fang.plan.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("UserRoleRelation")
public class UserRoleRelation implements Serializable {
  
  private static final long serialVersionUID = 1L;
 
  @TableId(type=IdType.AUTO)//主键策略
  private int id;
	private int roleid;
	private int oaUserId;
	
	private String email;
	public void setId(String id){
	this.id=Integer.parseInt((id==null||id.equals("")?"0":id));;
	}
	public int getId(){
		return id;
	}
	public void setRoleid(int roleid){
	this.roleid=roleid;
	}
	public int getRoleid(){
		return roleid;
	}
  
  
  public int getOaUserId() {
    return oaUserId;
  }
  
  public void setOaUserId(int oaUserId) {
    this.oaUserId = oaUserId;
  }
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  
  
}
