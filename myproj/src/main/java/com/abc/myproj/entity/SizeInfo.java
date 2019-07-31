package com.abc.myproj.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("SizeInfo")
public class SizeInfo implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
    //IdType.AUTO 	数据库ID自增
	//IdType.INPUT 	用户输入ID
	//IdType.ID_WORKER 	全局唯一ID，内容为空自动填充（默认配置）
	//IdType.UUID 	全局唯一ID，内容为空自动填充
	
	// @TableField(exist=false) 数据库中无该字段时，设置为false
	
	// 注：自定实体参数 （如 Entity entity） 前台传递格式   entity[0].属性名   entity[1].属性名  
  
  @TableId(type=IdType.AUTO)//主键策略
  	private int id;
	private int type;//1自定义尺寸    2 常规尺寸   3普通照片   4普通flash
	private String name;
	private int modaltype;//1 普通模板  2横幅模板  3全屏模板
	private String html;
	private double width;
	private double height;
	@TableField(exist=false) 
  private int modalnum;
	
  public int getModalnum() {
    return modalnum;
  }
  
  public void setModalnum(int modalnum) {
    this.modalnum = modalnum;
  }
  public void setId(String id){
	this.id=Integer.parseInt((id==null||id.equals("")?"0":id));
	}
	public int getId(){
		return id;
	}
	public void setType(String type){
	this.type=Integer.parseInt((type==null||type.equals("")?"0":type));
	}
	public int getType(){
		return type;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setModaltype(String modaltype){
	this.modaltype=Integer.parseInt((modaltype==null||modaltype.equals("")?"0":modaltype));
	}
	public int getModaltype(){
		return modaltype;
	}
	public void setHtml(String html){
	this.html=html;
	}
	public String getHtml(){
		return html;
	}
	public void setWidth(String width){
	  
	this.width=Double.parseDouble(width==null||width.equals("")?"0.00":width);
	}
	public double getWidth(){
		return width;
	}
	public void setHeight(String height){
	this.height=Double.parseDouble(height==null||height.equals("")?"0.00":height);
	}
	public double getHeight(){
		return height;
	}

  
}
