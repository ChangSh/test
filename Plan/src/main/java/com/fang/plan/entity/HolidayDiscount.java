package com.fang.plan.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("HolidayDiscount")
public class HolidayDiscount implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
    //IdType.AUTO 	数据库ID自增
	//IdType.INPUT 	用户输入ID
	//IdType.ID_WORKER 	全局唯一ID，内容为空自动填充（默认配置）
	//IdType.UUID 	全局唯一ID，内容为空自动填充
	
	// @TableField(exist=false) 数据库中无该字段时，设置为false
	
	// 注：自定实体参数 （如 Entity entity） 前台传递格式   entity[0].属性名   entity[1].属性名  
  
  @TableId(type=IdType.AUTO)//主键策略
  	private String id;
	private String companyshort;
	private int cityid;
	private int channelid;
	private String adplaceid;
	private int configyear;
	private int configmonth;
	private int configday;
	private double weekdaydiscount;
	private double weekenddiscount;
	private double holidaydiscount;
	private int discountpriority;
	private double showorder;
	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setCompanyshort(String companyshort){
	this.companyshort=companyshort;
	}
	public String getCompanyshort(){
		return companyshort;
	}
	public void setCityid(String cityid){
	this.cityid=Integer.parseInt((cityid==null||cityid.equals("")?"0":cityid));
	}
	public int getCityid(){
		return cityid;
	}
	public void setChannelid(String channelid){
	this.channelid=Integer.parseInt((channelid==null||channelid.equals("")?"0":channelid));
	}
	public int getChannelid(){
		return channelid;
	}
	public void setAdplaceid(String adplaceid){
	this.adplaceid=adplaceid;
	}
	public String getAdplaceid(){
		return adplaceid;
	}
	public void setConfigyear(String configyear){
	this.configyear=Integer.parseInt((configyear==null||configyear.equals("")?"0":configyear));
	}
	public int getConfigyear(){
		return configyear;
	}
	public void setConfigmonth(String configmonth){
	this.configmonth=Integer.parseInt((configmonth==null||configmonth.equals("")?"0":configmonth));
	}
	public int getConfigmonth(){
		return configmonth;
	}
	public void setConfigday(String configday){
	this.configday=Integer.parseInt((configday==null||configday.equals("")?"0":configday));
	}
	public int getConfigday(){
		return configday;
	}
	public void setWeekdaydiscount(String weekdaydiscount){
	this.weekdaydiscount=Double.parseDouble((weekdaydiscount==null||weekdaydiscount.equals("")?"0":weekdaydiscount));
	}
	public double getWeekdaydiscount(){
		return weekdaydiscount;
	}
	public void setWeekenddiscount(String weekenddiscount){
	this.weekenddiscount=Double.parseDouble((weekenddiscount==null||weekenddiscount.equals("")?"0":weekenddiscount));
	}
	public double getWeekenddiscount(){
		return weekenddiscount;
	}
	public void setHolidaydiscount(String holidaydiscount){
	this.holidaydiscount=Double.parseDouble((holidaydiscount==null||holidaydiscount.equals("")?"0":holidaydiscount));
	}
	public double getHolidaydiscount(){
		return holidaydiscount;
	}
	public void setDiscountpriority(String discountpriority){
	this.discountpriority=Integer.parseInt((discountpriority==null||discountpriority.equals("")?"0":discountpriority));
	}
	public int getDiscountpriority(){
		return discountpriority;
	}
	public void setShoworder(String showorder){
	this.showorder=Double.parseDouble((showorder==null||showorder.equals("")?"0":showorder));
	}
	public double getShoworder(){
		return showorder;
	}

  
}
