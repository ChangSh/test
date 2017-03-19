package com.abc.myproj.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("Plan_Detail")
public class Plan_Detail implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
    //IdType.AUTO 	数据库ID自增
	//IdType.INPUT 	用户输入ID
	//IdType.ID_WORKER 	全局唯一ID，内容为空自动填充（默认配置）
	//IdType.UUID 	全局唯一ID，内容为空自动填充
	
	// @TableField(exist=false) 数据库中无该字段时，设置为false
	
	// 注：自定实体参数 （如 Entity entity） 前台传递格式   entity[0].属性名   entity[1].属性名  
  
  @TableId(type=IdType.AUTO)//主键策略
  	private int id;
	private String orderid;
	private int porderid;
	private String kj_adp_id;
	private int adpid;
	private int frameno;
	private String reservedate;
	private int reservehour;
	private String citysimplename;
	private double allowance;
	private String state;
	private String operators;
	private String operatetime;	
  @TableField(exist=false)
  private int year;
  @TableField(exist=false)
  private int month;
  @TableField(exist=false)
	private List<String> d;
  @TableField(exist=false)
	private List<Plan_Detail> e;//实体集合 
 
  
  
  public int getYear() {
    return year;
  }



  
  public void setYear(int year) {
    this.year = year;
  }



  
  public int getMonth() {
    return month;
  }



  
  public void setMonth(int month) {
    this.month = month;
  }

  
  public List<Plan_Detail> getE() {
    return e;
  }

  public void setE(List<Plan_Detail> e) {
    this.e = e;
  }


  public List<String> getD() {
    return d;
  }

  
  public void setD(List<String> d) {
    this.d = d;
  }




  public void setId(String id){
	this.id=Integer.parseInt((id==null||id.equals("")?"0":id));
	}
	public int getId(){
		return id;
	}
	public void setOrderid(String orderid){
	this.orderid=orderid;
	}
	public String getOrderid(){
		return orderid;
	}
	public void setPorderid(String porderid){
	this.porderid=Integer.parseInt((porderid==null||porderid.equals("")?"0":porderid));
	}
	public int getPorderid(){
		return porderid;
	}
	public void setKj_adp_id(String kj_adp_id){
	this.kj_adp_id=kj_adp_id;
	}
	public String getKj_adp_id(){
		return kj_adp_id;
	}
	public void setAdpid(String adpid){
	this.adpid=Integer.parseInt((adpid==null||adpid.equals("")?"0":adpid));
	}
	public int getAdpid(){
		return adpid;
	}
	public void setFrameno(String frameno){
	this.frameno=Integer.parseInt((frameno==null||frameno.equals("")?"0":frameno));
	}
	public int getFrameno(){
		return frameno;
	}
	public void setReservedate(String reservedate){
	this.reservedate=reservedate;
	}
	public String getReservedate(){
		return reservedate;
	}
	public void setReservehour(String reservehour){
	this.reservehour=Integer.parseInt((reservehour==null||reservehour.equals("")?"0":reservehour));
	}
	public int getReservehour(){
		return reservehour;
	}
	public void setCitysimplename(String citysimplename){
	this.citysimplename=citysimplename;
	}
	public String getCitysimplename(){
		return citysimplename;
	}
	public void setAllowance(double allowance){
	this.allowance=allowance;
	}
	public double getAllowance(){
		return allowance;
	}
	public void setState(String state){
	this.state=state;
	}
	public String getState(){
		return state;
	}
	public void setOperators(String operators){
	this.operators=operators;
	}
	public String getOperators(){
		return operators;
	}
	public void setOperatetime(String operatetime){
	this.operatetime=operatetime;
	}
	public String getOperatetime(){
		return operatetime;
	}

  
}
