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
@TableName("RoleFunctionRelation")
public class RoleFunctionRelation implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
    //IdType.AUTO 	数据库ID自增
	//IdType.INPUT 	用户输入ID
	//IdType.ID_WORKER 	全局唯一ID，内容为空自动填充（默认配置）
	//IdType.UUID 	全局唯一ID，内容为空自动填充
	
	// @TableField(exist=false) 数据库中无该字段时，设置为false
  
  @TableId(type=IdType.AUTO)//主键策略
  private int id;
	private int roleid;
	private int functionid;
	private String releasetime;
	public void setId(String id){
	this.id=Integer.parseInt((id==null||id.equals("")?"0":id));
	}
	public int getId(){
		return id;
	}
	public void setRoleid(String roleid){
	this.roleid=Integer.parseInt((roleid==null||roleid.equals("")?"0":roleid));
	}
	public int getRoleid(){
		return roleid;
	}
	public void setFunctionid(String functionid){
	this.functionid=Integer.parseInt((functionid==null||functionid.equals("")?"0":functionid));
	}
	public int getFunctionid(){
		return functionid;
	}

	public void setReleasetime(String releasetime){
	this.releasetime=releasetime;
	}
	public String getReleasetime(){
		return releasetime;
	}


  
}
