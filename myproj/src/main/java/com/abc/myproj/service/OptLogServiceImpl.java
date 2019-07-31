package com.abc.myproj.service;

import org.springframework.stereotype.Service;

import com.abc.myproj.entity.OptLog;
import com.abc.myproj.mapper.OptLogMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;



/**
 * 日志业务实现类层
 * @author wangyouwei
 */
@Service
public class OptLogServiceImpl extends ServiceImpl<OptLogMapper, OptLog> implements IOptLogService {
  
}
