package com.abc.myproj.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("UserInit")
public class UserInit implements Serializable {
  
  private static final long serialVersionUID = 1L;
  @TableId(type=IdType.UUID)//主键
  private String uuid;
  @TableField(exist=false) 
  private int roleId;
  @TableField(exist=false) 
  private List<Integer> roleIds;
  @TableField(exist=true) 
  private int OaUserID;
  private String email;
  private String realName;
  @TableField(exist=false) 
  private String roleName;
  @TableField(exist=false)
  private List<Integer> cityIds;
  private String userName; 
  private String groupName;
  private String website;
  
  public List<Integer> getCityIds() {
    return cityIds;
  }


  
  public void setCityIds(List<Integer> cityIds) {
    this.cityIds = cityIds;
  }



   
  
  public List<Integer> getRoleIds() {
    return roleIds;
  }




  
  public void setRoleIds(List<Integer> roleIds) {
    this.roleIds = roleIds;
  }




  public String getGroupName() {
    return groupName;
  }
 
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
 
  public String getUuid() {
    return uuid;
  }

  
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  
  
  public int getOaUserID() {
    return OaUserID;
  }

  
  public void setOaUserID(int oaUserID) {
    OaUserID = oaUserID;
  }

  public int getRoleId() {
    return roleId;
  }

  
  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

 
  public String getEmail() {
    return email;
  }

  
  public void setEmail(String email) {
    this.email = email;
  }

  
  public String getRealName() {
    return realName;
  }

  
  public void setRealName(String realName) {
    this.realName = realName;
  }

  
  public String getRoleName() {
    return roleName;
  }

  
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  
  public String getUserName() {
    return userName;
  }

  
  public void setUserName(String userName) {
    this.userName = userName;
  }



  
  public String getWebsite() {
    return website;
  }



  
  public void setWebsite(String website) {
    this.website = website;
  }

}
