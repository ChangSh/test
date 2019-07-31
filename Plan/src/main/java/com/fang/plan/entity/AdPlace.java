package com.fang.plan.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("AdPlace")
public class AdPlace implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
    //IdType.AUTO 	数据库ID自增
	//IdType.INPUT 	用户输入ID
	//IdType.ID_WORKER 	全局唯一ID，内容为空自动填充（默认配置）
	//IdType.UUID 	全局唯一ID，内容为空自动填充
	
	// @TableField(exist=false) 数据库中无该字段时，设置为false
	
	// 注：自定实体参数 （如 Entity entity） 前台传递格式   entity[0].属性名   entity[1].属性名  
  
  @TableId(type=IdType.AUTO)//主键策略
  	private int id;
	private String name;
	private float width;
	private float height;
	private int isdeleted;
	private String addtime;
	private int adderid;
	private int state;
	private int webid;
	private int channelid;
	private int bannerid;
	private String url;
	private String remarks;
	private int companygroupid;
	private String companygroupshort;
	private String companygroupname;
	private int cityid;
	private String cityshort;
	private String cityname;
	public void setId(String id){
	this.id=Integer.parseInt((id==null||id.equals("")?"0":id));
	}
	public int getId(){
		return id;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setWidth(float width){
	this.width=width;
	}
	public float getWidth(){
		return width;
	}
	public void setHeight(float height){
	this.height=height;
	}
	public float getHeight(){
		return height;
	}
	public void setIsdeleted(String isdeleted){
	this.isdeleted=Integer.parseInt((isdeleted==null||isdeleted.equals("")?"0":isdeleted));
	}
	public int getIsdeleted(){
		return isdeleted;
	}
	public void setAddtime(String addtime){
	this.addtime=addtime;
	}
	public String getAddtime(){
		return addtime;
	}
	public void setAdderid(String adderid){
	this.adderid=Integer.parseInt((adderid==null||adderid.equals("")?"0":adderid));
	}
	public int getAdderid(){
		return adderid;
	}
	public void setState(String state){
	this.state=Integer.parseInt((state==null||state.equals("")?"0":state));
	}
	public int getState(){
		return state;
	}
	public void setWebid(String webid){
	this.webid=Integer.parseInt((webid==null||webid.equals("")?"0":webid));
	}
	public int getWebid(){
		return webid;
	}
	public void setChannelid(String channelid){
	this.channelid=Integer.parseInt((channelid==null||channelid.equals("")?"0":channelid));
	}
	public int getChannelid(){
		return channelid;
	}
	public void setBannerid(String bannerid){
	this.bannerid=Integer.parseInt((bannerid==null||bannerid.equals("")?"0":bannerid));
	}
	public int getBannerid(){
		return bannerid;
	}
	public void setUrl(String url){
	this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setRemarks(String remarks){
	this.remarks=remarks;
	}
	public String getRemarks(){
		return remarks;
	}
	public void setCompanygroupid(String companygroupid){
	this.companygroupid=Integer.parseInt((companygroupid==null||companygroupid.equals("")?"0":companygroupid));
	}
	public int getCompanygroupid(){
		return companygroupid;
	}
	public void setCompanygroupshort(String companygroupshort){
	this.companygroupshort=companygroupshort;
	}
	public String getCompanygroupshort(){
		return companygroupshort;
	}
	public void setCompanygroupname(String companygroupname){
	this.companygroupname=companygroupname;
	}
	public String getCompanygroupname(){
		return companygroupname;
	}
	public void setCityid(String cityid){
	this.cityid=Integer.parseInt((cityid==null||cityid.equals("")?"0":cityid));
	}
	public int getCityid(){
		return cityid;
	}
	public void setCityshort(String cityshort){
	this.cityshort=cityshort;
	}
	public String getCityshort(){
		return cityshort;
	}
	public void setCityname(String cityname){
	this.cityname=cityname;
	}
	public String getCityname(){
		return cityname;
	}

  
}
