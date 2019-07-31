package com.fang.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.plan.entity.SizeInfo;
import com.fang.plan.mapper.SizeInfoMapper;
import com.fang.plan.mapper.UserInitMapper;

/**
*
* SizeInfo 表数据服务层接口实现类
*
*/
@Service
public class SizeInfoServiceImpl extends ServiceImpl<SizeInfoMapper, SizeInfo> implements ISizeInfoService {

  @Autowired
  private  SizeInfoMapper mapper;
  
  @Override
  public List<SizeInfo> sizelist(Pagination pag, SizeInfo size) {
    // TODO Auto-generated method stub
    return mapper.sizelist(pag, size);
  }

  @Override
  public List<SizeInfo> getAllSizeInfo() {
    // TODO Auto-generated method stub
    return mapper.getAllSizeInfo();
  }

  @Override
  public SizeInfo getSizeById(int id) {
    // TODO Auto-generated method stub
    return mapper.getSizeById(id);
  }
  
}
