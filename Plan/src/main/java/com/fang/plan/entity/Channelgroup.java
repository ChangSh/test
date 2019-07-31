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
@TableName("Channelgroup")
public class Channelgroup implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
    //IdType.AUTO 	数据库ID自增
	//IdType.INPUT 	用户输入ID
	//IdType.ID_WORKER 	全局唯一ID，内容为空自动填充（默认配置）
	//IdType.UUID 	全局唯一ID，内容为空自动填充
	
	// @TableField(exist=false) 数据库中无该字段时，设置为false
	
	// 注：自定实体参数 （如 Entity entity） 前台传递格式   entity[0].属性名   entity[1].属性名  
  
  @TableId(type=IdType.AUTO)//主键策略
  	private int id;
	private String channelgroupname;
	private int status;
	private int websiteid;
	private String websitepy;
	private String url;
	private String addtime;
	private String addperson;
	private int sitegroupid;
	private String sitegrouppy;
	private String sitegroupname;
	private String websitename;
	public void setId(String id){
	this.id=Integer.parseInt((id==null||id.equals("")?"0":id));
	}
	public int getId(){
		return id;
	}
	public void setChannelgroupname(String channelgroupname){
	this.channelgroupname=channelgroupname;
	}
	public String getChannelgroupname(){
		return channelgroupname;
	}
	public void setStatus(String status){
	this.status=Integer.parseInt((status==null||status.equals("")?"0":status));
	}
	public int getStatus(){
		return status;
	}
	public void setWebsiteid(String websiteid){
	this.websiteid=Integer.parseInt((websiteid==null||websiteid.equals("")?"0":websiteid));
	}
	public int getWebsiteid(){
		return websiteid;
	}
	public void setWebsitepy(String websitepy){
	this.websitepy=websitepy;
	}
	public String getWebsitepy(){
		return websitepy;
	}
	public void setUrl(String url){
	this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setAddtime(String addtime){
	this.addtime=addtime;
	}
	public String getAddtime(){
		return addtime;
	}
	public void setAddperson(String addperson){
	this.addperson=addperson;
	}
	public String getAddperson(){
		return addperson;
	}
	public void setSitegroupid(String sitegroupid){
	this.sitegroupid=Integer.parseInt((sitegroupid==null||sitegroupid.equals("")?"0":sitegroupid));
	}
	public int getSitegroupid(){
		return sitegroupid;
	}
	public void setSitegrouppy(String sitegrouppy){
	this.sitegrouppy=sitegrouppy;
	}
	public String getSitegrouppy(){
		return sitegrouppy;
	}
  
  public String getSitegroupname() {
    return sitegroupname;
  }
  
  public void setSitegroupname(String sitegroupname) {
    this.sitegroupname = sitegroupname;
  }
  
  public String getWebsitename() {
    return websitename;
  }
  
  public void setWebsitename(String websitename) {
    this.websitename = websitename;
  }
	
  
}
