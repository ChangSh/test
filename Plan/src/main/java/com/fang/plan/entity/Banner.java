package com.fang.plan.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("Banner")
public class Banner implements Serializable {
  
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
	private String orderid;//所属投放
	private int weight;//权重
	private String targeturl;//点击地址
	private int bannertype;//创意类型（1自定义、2图片、3flash、4纯点击、5文字链  6打底素材）
	private int targetwindow;//目标窗口（1当前窗口、2新窗口）
	private int ifeffective;//是否有效  1有效 0无效
	private int ifsetsonbanner;//是否设置为子创意   1设置  0不设置
	//创意类型为图片、flash时,填写有以下属性
	private String bannerurl;//对应传漾的bannerimage,  创意是打底素材的时候，存打底素材的图片链接
	private int ifusecustomsize;//是否使用自定义尺寸  1使用  0不使用
	private double width;
	private double height;
	private String thirdmonitoring;//第三方曝光监测
	private int ifusediv;//是否使用div外框   1使用  0不使用
	//创意类型为自定义时：
	private int modalid;
	//创意类型为flash时：
	private int ifuseclicktag;//是否支持clickTAG  1支持  0不支持
	private int windowmode;//窗口模式：1透明、2不透明、3悬浮
	private String alternatepictureurl;//备用图片地址
	//创意类型为文字链时：
	private int modaltype;//模板类型（1标准、2自定义）
	private String texcontent;//文字内容
	private int ifusepagestyle;//是否跟随页面样式   1跟随   0不跟随
	private String font;
	private String fontsize;
	private int ifunderline;//有无下划线  1有  0没有
	private String fontcolor;
	private String mousecolor;
	private String html;
	private int fid;//创意组id
  private String addoperator;
  private String addtime;
  private int cyid;//传漾id
//创意类型是打底素材的时候
  private int adPlaceId;
  private String startTime;
  private String endTime;
  private String label;
  
  
  
  
  public int getAdPlaceId() {
    return adPlaceId;
  }
  
  public void setAdPlaceId(String adPlaceId) {
    this.adPlaceId = Integer.parseInt(adPlaceId==null||adPlaceId.equals("")?"0":adPlaceId);
  }
  
  public String getStartTime() {
    return startTime;
  }
  
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }
  
  public String getEndTime() {
    return endTime;
  }
  
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }
  
  public String getLabel() {
    return label;
  }
  
  public void setLabel(String label) {
    this.label = label;
  }
  public int getCyid() {
    return cyid;
  }
  public void setCyid(String cyid) {
    this.cyid = Integer.parseInt(cyid==null||cyid.equals("")?"0":cyid);
  }



  public String getAddoperator() {
    return addoperator;
  }


  
  public void setAddoperator(String addoperator) {
    this.addoperator = addoperator;
  }


  
  public String getAddtime() {
    return addtime;
  }


  
  public void setAddtime(String addtime) {
    this.addtime = addtime;
  }


  public int getFid() {
    return fid;
  }

  
  public void setFid(int fid) {
    this.fid = fid;
  }

  public String getHtml() {
    return html;
  }
  
  public void setHtml(String html) {
    this.html = html;
  }
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
 public String getOrderid() {
    return orderid;
  }  
  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }
  public void setWeight(String weight){
	this.weight=Integer.parseInt((weight==null||weight.equals("")?"0":weight));
	}
	public int getWeight(){
		return weight;
	}
	public void setTargeturl(String targeturl){
	this.targeturl=targeturl;
	}
	public String getTargeturl(){
		return targeturl;
	}
	public void setBannertype(String bannertype){
	this.bannertype=Integer.parseInt((bannertype==null||bannertype.equals("")?"0":bannertype));
	}
	public int getBannertype(){
		return bannertype;
	}
	public void setTargetwindow(String targetwindow){
	this.targetwindow=Integer.parseInt((targetwindow==null||targetwindow.equals("")?"0":targetwindow));
	}
	public int getTargetwindow(){
		return targetwindow;
	}
	public void setIfeffective(String ifeffective){
	this.ifeffective=Integer.parseInt((ifeffective==null||ifeffective.equals("")?"0":ifeffective));
	}
	public int getIfeffective(){
		return ifeffective;
	}
	public void setIfsetsonbanner(String ifsetsonbanner){
	this.ifsetsonbanner=Integer.parseInt((ifsetsonbanner==null||ifsetsonbanner.equals("")?"0":ifsetsonbanner));
	}
	public int getIfsetsonbanner(){
		return ifsetsonbanner;
	}
	public void setBannerurl(String bannerurl){
	this.bannerurl=bannerurl;
	}
	public String getBannerurl(){
		return bannerurl;
	}
	public void setIfusecustomsize(String ifusecustomsize){
	this.ifusecustomsize=Integer.parseInt((ifusecustomsize==null||ifusecustomsize.equals("")?"0":ifusecustomsize));
	}
	public int getIfusecustomsize(){
		return ifusecustomsize;
	}
/*	public void setWidth(String width){
    
	  this.width=Double.parseDouble(width==null||width.equals("")?"0.00":width);
	  }*/
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
	public void setThirdmonitoring(String thirdmonitoring){
	this.thirdmonitoring=thirdmonitoring;
	}
	public String getThirdmonitoring(){
		return thirdmonitoring;
	}
	public void setIfusediv(String ifusediv){
	this.ifusediv=Integer.parseInt((ifusediv==null||ifusediv.equals("")?"0":ifusediv));
	}
	public int getIfusediv(){
		return ifusediv;
	}
	public void setModalid(String modalid){
	this.modalid=Integer.parseInt((modalid==null||modalid.equals("")?"0":modalid));
	}
	public int getModalid(){
		return modalid;
	}
	public void setIfuseclicktag(String ifuseclicktag){
	this.ifuseclicktag=Integer.parseInt((ifuseclicktag==null||ifuseclicktag.equals("")?"0":ifuseclicktag));
	}
	public int getIfuseclicktag(){
		return ifuseclicktag;
	}
	public void setWindowmode(String windowmode){
	this.windowmode=Integer.parseInt((windowmode==null||windowmode.equals("")?"0":windowmode));
	}
	public int getWindowmode(){
		return windowmode;
	}
	public void setAlternatepictureurl(String alternatepictureurl){
	this.alternatepictureurl=alternatepictureurl;
	}
	public String getAlternatepictureurl(){
		return alternatepictureurl;
	}
	public void setModaltype(String modaltype){
	this.modaltype=Integer.parseInt((modaltype==null||modaltype.equals("")?"0":modaltype));
	}
	public int getModaltype(){
		return modaltype;
	}
	public void setTexcontent(String texcontent){
	this.texcontent=texcontent;
	}
	public String getTexcontent(){
		return texcontent;
	}
	public void setIfusepagestyle(String ifusepagestyle){
	this.ifusepagestyle=Integer.parseInt((ifusepagestyle==null||ifusepagestyle.equals("")?"0":ifusepagestyle));
	}
	public int getIfusepagestyle(){
		return ifusepagestyle;
	}
	public void setFont(String font){
	this.font=font;
	}
	public String getFont(){
		return font;
	}
	public void setFontsize(String fontsize){
	this.fontsize=fontsize;
	}
	public String getFontsize(){
		return fontsize;
	}
	public void setIfunderline(String ifunderline){
	this.ifunderline=Integer.parseInt((ifunderline==null||ifunderline.equals("")?"0":ifunderline));
	}
	public int getIfunderline(){
		return ifunderline;
	}
	public void setFontcolor(String fontcolor){
	this.fontcolor=fontcolor;
	}
	public String getFontcolor(){
		return fontcolor;
	}
	public void setMousecolor(String mousecolor){
	this.mousecolor=mousecolor;
	}
	public String getMousecolor(){
		return mousecolor;
	}

  
}
