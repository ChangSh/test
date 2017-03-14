package com.abc.myproj.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 
 * @author ：CHANG
 * 
 * */
@TableName("CYFunction")
public class CyFunction implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  // IdType.AUTO 数据库ID自增
  // IdType.INPUT 用户输入ID
  // IdType.ID_WORKER 全局唯一ID，内容为空自动填充（默认配置）
  // IdType.UUID 全局唯一ID，内容为空自动填充
  
  @TableId(type = IdType.AUTO)
  // 主键策略
  private int id;
  private int pId;
  private String name;
  private String checked;

  
  public void setId(String id) {
    this.id = Integer.parseInt((id == null || id.equals("") ? "0" : id));
 
  }
  
  public int getId() {
    return id;
  }

  
  public int getpId() {
    return pId;
  }

  
  public void setpId(String pId) {
    this.pId = Integer.parseInt((pId == null || pId.equals("") ? "0" : pId));
  }

  
  public String getName() {
    return name;
  }

  
  public void setName(String name) {
    this.name = name;
  }

  
  public String getChecked() {
    return checked;
  }

  
  public void setChecked(String checked) {
    this.checked = checked;
  }
  
 
  
  
}
