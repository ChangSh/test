package com.fang.plan.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("AdPlacePrice")
public class AdPlacePrice implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
    //IdType.AUTO 	数据库ID自增
	//IdType.INPUT 	用户输入ID
	//IdType.ID_WORKER 	全局唯一ID，内容为空自动填充（默认配置）
	//IdType.UUID 	全局唯一ID，内容为空自动填充
	
	// @TableField(exist=false) 数据库中无该字段时，设置为false
	
	// 注：自定实体参数 （如 Entity entity） 前台传递格式   entity[0].属性名   entity[1].属性名  
  
  @TableId(type=IdType.AUTO)//主键策略
  	private int id;
	private String companygroup;
	private String websiteid;
	
  private String channelid;
	private String platform;
	private String adplaceid;
	private String originality;
	private String priceyear;
	private double price;
	@TableField(exist=false)
	private double pricestart;
	@TableField(exist=false)
	private double priceend; 
	private String state;
  private String addtime;
  @TableField(exist=false)
  private String starttime;
  @TableField(exist=false)
  private String endtime;
  @TableField(exist=false)
  private String orderid;
  private int IsPay;
  @TableField(exist=false)
  private String ReserveDate;
  @TableField(exist=false)
  private int ReserveHour;
  
  @TableField(exist=false)
  private List<String> Times;
  
  @TableField(exist=false)
  private int priceId;
  @TableField(exist=false)
  private BigDecimal pprice;
  
  
  
  
  
  public BigDecimal getPprice() {
    return pprice;
  }





  
  public void setPprice(BigDecimal pprice) {
    this.pprice = pprice;
  }





  public int getPriceId() {
    return priceId;
  }




  
  public void setPriceId(int priceId) {
    this.priceId = priceId;
  }




  public List<String> getTimes() {
    return Times;
  }



  
  public void setTimes(List<String> times) {
    Times = times;
  }



  public int getReserveHour() {
    return ReserveHour;
  }


  
  public void setReserveHour(int reserveHour) {
    ReserveHour = reserveHour;
  }


  public String getReserveDate() {
    return ReserveDate;
  }

  
  public void setReserveDate(String reserveDate) {
    ReserveDate = reserveDate;
  }

  public int getIsPay() {
    return IsPay;
  }
  
  public String getOrderid() {
    return orderid;
  }




  

  public void setIsPay(int isPay) {
    IsPay = isPay;
  }

  

  

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }




  public String getStarttime() {
    return starttime;
  }


  
  public void setStarttime(String starttime) {
    this.starttime = starttime;
  }


  
  public String getEndtime() {
    return endtime;
  }


  
  public void setEndtime(String endtime) {
    this.endtime = endtime;
  }


  public String getState() {
    return state;
  }

  
  public void setState(String state) {
    this.state = state;
  }

  
  public String getAddTime() {
    return addtime;
  }

  
  public void setAddTime(String addTime) {
    addtime = addTime;
  }
	 
  public double getPricestart() {
    return pricestart;
  }
  
  public void setPricestart(String pricestart) {
    this.pricestart = Double.parseDouble((pricestart==null||pricestart.equals("")?"0":pricestart));
  }
  
  public double getPriceend() {
    return priceend;
  }
  
  public void setPriceend(String priceend) {
    this.priceend = Double.parseDouble((priceend==null||priceend.equals("")?"0":priceend));
  }

 
	
	
	public void setId(String id){
	this.id=Integer.parseInt((id==null||id.equals("")?"0":id));
	}
	public int getId(){
		return id;
	}
	public void setCompanygroup(String companygroup){
	this.companygroup=companygroup;
	}
	public String getCompanygroup(){
		return companygroup;
	}

	public void setPlatform(String platform){
	this.platform=platform;
	}
	public String getPlatform(){
		return platform;
	}

	public void setOriginality(String originality){
	this.originality=originality;
	}
	public String getOriginality(){
		return originality;
	}
	public void setPriceyear(String priceyear){
	this.priceyear=priceyear;
	}
	public String getPriceyear(){
		return priceyear;
	}
	public void setPrice(String price){
	this.price=Double.parseDouble((price==null||price.equals("")?"0":price));
	}
	public double getPrice(){
		return price;
	}

  public String getWebsiteid() {
    return websiteid;
  }
  
  public void setWebsiteid(String websiteid) {
    this.websiteid = websiteid;
  }
  
  public String getChannelid() {
    return channelid;
  }
  
  public void setChannelid(String channelid) {
    this.channelid = channelid;
  }
  
  public String getAdplaceid() {
    return adplaceid;
  }
  
  public void setAdplaceid(String adplaceid) {
    this.adplaceid = adplaceid;
  }
  
}
