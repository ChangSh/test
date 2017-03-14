package com.abc.myproj.service;

import java.util.List;

import com.abc.myproj.entity.SizeInfo;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;
/**
*
* SizeInfo 表数据服务层接口
*
*/
public interface ISizeInfoService  extends IService<SizeInfo>{
  /**
   * 自定义分页
   * @return
   */
  List<SizeInfo> sizelist(Pagination pag,SizeInfo size);
  
  List<SizeInfo> getAllSizeInfo();
  
  SizeInfo getSizeById(int id);
}