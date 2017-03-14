package com.abc.myproj.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("BannerAdplaceRelation")
public class BannerAdplaceRelation implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
    //IdType.AUTO 	数据库ID自增
	//IdType.INPUT 	用户输入ID
	//IdType.ID_WORKER 	全局唯一ID，内容为空自动填充（默认配置）
	//IdType.UUID 	全局唯一ID，内容为空自动填充
	
	// @TableField(exist=false) 数据库中无该字段时，设置为false
	
	// 注：自定实体参数 （如 Entity entity） 前台传递格式   entity[0].属性名   entity[1].属性名  
  
  @TableId(type=IdType.AUTO)//主键策略
  private int id;
	private int bannerid;
	private int adplaceid;
	private String reservedate;
	private String checkoperator;
	private String checktime;
	
	@TableField(exist=false)
	private List<BannerAdplaceRelation> e;//实体集合 
	  
	 
 
    public String getCheckoperator() {
      return checkoperator;
    }
    public void setCheckoperator(String checkoperator) {
      this.checkoperator = checkoperator;
    }
    public String getChecktime() {
      return checktime;
    }
    public void setChecktime(String checktime) {
      this.checktime = checktime;
    }
    public List<BannerAdplaceRelation> getE() {
      return e;
    }
    public void setE(List<BannerAdplaceRelation> e) {
      this.e = e;
    }
    public void setId(String id){
  	this.id=Integer.parseInt((id==null||id.equals("")?"0":id));
  	}
  	public int getId(){
  		return id;
  	}
  	public void setBannerid(String bannerid){
  	this.bannerid=Integer.parseInt((bannerid==null||bannerid.equals("")?"0":bannerid));
  	}
  	public int getBannerid(){
  		return bannerid;
  	}
  	public void setAdplaceid(String adplaceid){
  	this.adplaceid=Integer.parseInt((adplaceid==null||adplaceid.equals("")?"0":adplaceid));
  	}
  	public int getAdplaceid(){
  		return adplaceid;
  	}
    public String getReservedate() {
      return reservedate;
    }
    public void setReservedate(String reservedate) {
      this.reservedate = reservedate;
    }

  
}
