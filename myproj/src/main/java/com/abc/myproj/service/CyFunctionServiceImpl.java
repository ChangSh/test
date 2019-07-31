package com.abc.myproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.myproj.entity.CyFunction;
import com.abc.myproj.mapper.CyFunctionMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
* @author ：CHANG
* CyFunction 表数据服务层接口实现类
*
*/
@Service
public class CyFunctionServiceImpl extends ServiceImpl<CyFunctionMapper, CyFunction> implements ICyFunctionService {

  @Autowired
  private  CyFunctionMapper mapper;
  @Override
  public List<CyFunction> getCyFunctionList(int roleId) {
    
    return mapper.getCyFunctionList(roleId);
  }

}
