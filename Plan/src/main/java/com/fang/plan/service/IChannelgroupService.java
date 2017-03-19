package com.fang.plan.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.Channelgroup;
/**
* @author ：CHANG
* Channelgroup 表数据服务层接口
*
*/
public interface IChannelgroupService  extends IService<Channelgroup>{
  
  List<Channelgroup> selectsiteandgroup(int id);
  
  List<Channelgroup> getWebsiteByUser(String email);
  
}