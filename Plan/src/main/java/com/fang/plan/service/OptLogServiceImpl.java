package com.fang.plan.service;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.plan.entity.OptLog;
import com.fang.plan.mapper.OptLogMapper;



/**
 * 日志业务实现类层
 * @author wangyouwei
 */
@Service
public class OptLogServiceImpl extends ServiceImpl<OptLogMapper, OptLog> implements IOptLogService {
  
}
