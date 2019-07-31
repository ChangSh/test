package com.abc.myproj.service;

import java.util.List;

import com.abc.myproj.entity.Channelgroup;
import com.baomidou.mybatisplus.service.IService;
/**
* @author ：CHANG
* Channelgroup 表数据服务层接口
*
*/
public interface IChannelgroupService  extends IService<Channelgroup>{
  
  List<Channelgroup> selectsiteandgroup(int id);
  
  List<Channelgroup> getWebsiteByUser(String email);
  
}