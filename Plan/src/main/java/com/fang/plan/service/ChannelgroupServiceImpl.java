package com.fang.plan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.plan.entity.Channelgroup;
import com.fang.plan.mapper.ChannelgroupMapper;

/**
* @author ：CHANG
* Channelgroup 表数据服务层接口实现类
*
*/
@Service
public class ChannelgroupServiceImpl extends ServiceImpl<ChannelgroupMapper, Channelgroup> implements IChannelgroupService {

  @Autowired
  ChannelgroupMapper mapper;
  @Override
  public List<Channelgroup> selectsiteandgroup(int id) {
    
    return mapper.selectsiteandgroup(id);
  }
  
  @Override
  public List<Channelgroup> getWebsiteByUser(String email) {
    
    String ws = mapper.getWebsiteByUser(email);
    String[] unit = ws.split(",");
    List<Channelgroup> l = new ArrayList<Channelgroup>();
    for(int i=0;i<unit.length;i++){
      Channelgroup cg = new Channelgroup();
      cg.setSitegrouppy(unit[i].substring(0,unit[i].indexOf("-")));
      cg.setWebsitepy(unit[i].substring(unit[i].indexOf("-")+1));
      cg = mapper.getEntity(cg);
      l.add(cg);
    }

    return l;
  }
  
}
