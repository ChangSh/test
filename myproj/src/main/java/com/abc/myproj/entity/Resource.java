package com.abc.myproj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 资源实体bean
 * @author pengmaokui
 */
public class Resource implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 3654359645907985386L;
  
  /**
   * id
   */
  private String id;
  
  /**
   * code
   */
  private String code;
  
  /**
   * 父code
   */
  private String pcode;
  
  /**
   * 名称
   */
  private String name;
  
  /**
   * tab标签页显示的名称，（特殊处理列）
   */
  private String tabName;
  
  /**
   * URL
   */
  private String url;
  
  /**
   * 权限URL
   */
  private String authUrl;
  
  /**
   * 排序
   */
  private Integer order;
  
  /**
   * 菜单类型，1为功能菜单，0为普通菜单
   */
/*  private String type;*/
  
  /**
   * ztree的辅助字段，初始化时树展开
   */
  private boolean open;
  
  /**
   * ztree的辅助字段，初始化时是否被选择
   */
  private boolean checked;
  
  /**
   * ztree的辅助字段，菜单图标
   */
  private String icon;
  /**
   * ztree的辅助字段，菜单图标
   */
  private String iconClose;
  /**
   * ztree的辅助字段，菜单图标
   */
  private String iconOpen;
  
  /**
   * 存放用sql查询出来的所有菜单中标记拥有的权限
   */
  private String selected;
  
  /**
   * 图片部分x
   */
  private String imgPosition_x;
  
  /**
   * 图片部分y
   */
  private String imgPosition_y;
  
  /**
   * 图片名称
   */
  private String imgUrl;

  /**
   * 资源子集，json格式化为ztree需要的格式，
   * 修改该属性名为“children”
   */
  @JsonProperty("children")
  private List<Resource> resources = new ArrayList<Resource>();
  
  /**
   * @return code
   */
  public String getCode() {
    return code;
  }
  
  /**
   * @param code
   *        set code
   */
  public void setCode(String code) {
    this.code = code;
  }
  
  /**
   * @return pcode
   */
  public String getPcode() {
    return pcode;
  }
  
  /**
   * @param pcode
   *        set pcode
   */
  public void setPcode(String pcode) {
    this.pcode = pcode;
  }

  /**
   * @return name
   */
  public String getName() {
    return name;
  }
  
  /**
   * @param name
   *        set name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return url
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * @param url set url
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return authUrl
   */
  public String getAuthUrl() {
    return authUrl;
  }
  
  /**
   * @param authUrl set authUrl
   */
  public void setAuthUrl(String authUrl) {
    this.authUrl = authUrl;
  }

  /**
   * @return order
   */
  public Integer getOrder() {
    return order;
  }
  
  /**
   * @param order
   *        set order
   */
  public void setOrder(Integer order) {
    this.order = order;
  }
  
  /**
   * @return resources
   */
  public List<Resource> getResources() {
    return resources;
  }
  
  /**
   * @param resources
   *        set resources
   */
  public void setResources(List<Resource> resources) {
    this.resources = resources;
  }

  
  /**
   * @return open
   */
  public boolean isOpen() {
    return open;
  }
  
  /**
   * @param open
   *        set open
   */
  public void setOpen(boolean open) {
    this.open = open;
  }
  
  /**
   * @return checked
   */
  public boolean isChecked() {
    return checked;
  }

  
  /**
   * @param checked
   *        set checked
   */
  public void setChecked(boolean checked) {
    this.checked = checked;
  }
  
  /**
   * @return selected
   */
  public String getSelected() {
    return selected;
  }
  
  /**
   * @param selected
   *        set selected
   */
  public void setSelected(String selected) {
    this.selected = selected;
  }
  
  /**
   * @return icon
   */
  public String getIcon() {
    return icon;
  }
  
  /**
   * @param icon
   *        set icon
   */
  public void setIcon(String icon) {
    this.icon = icon;
  }
  
  /**
   * @return iconClose
   */
  public String getIconClose() {
    return iconClose;
  }

  /**
   * @param iconClose
   *        set iconClose
   */
  public void setIconClose(String iconClose) {
    this.iconClose = iconClose;
  }
  
  /**
   * @return iconOpen
   */
  public String getIconOpen() {
    return iconOpen;
  }
  
  /**
   * @param iconOpen
   *        set iconOpen
   */
  public void setIconOpen(String iconOpen) {
    this.iconOpen = iconOpen;
  }
  
  /**
   * @return id
   */
  public String getId() {
    return id;
  }

  
  /**
   * @param id
   *        set id
   */
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * @return tabName
   */
  public String getTabName() {
    return tabName;
  }

  
  /**
   * @param tabName set tabName
   */
  public void setTabName(String tabName) {
    this.tabName = tabName;
  }

  
  public String getImgPosition_x() {
    return imgPosition_x;
  }

  
  public void setImgPosition_x(String imgPosition_x) {
    this.imgPosition_x = imgPosition_x;
  }

  
  public String getImgPosition_y() {
    return imgPosition_y;
  }

  
  public void setImgPosition_y(String imgPosition_y) {
    this.imgPosition_y = imgPosition_y;
  }

  
  public String getImgUrl() {
    return imgUrl;
  }

  
  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }
  
}
