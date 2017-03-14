package com.abc.myproj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abc.myproj.entity.Channelgroup;
import com.baomidou.mybatisplus.mapper.BaseMapper;


/**
 * @author ：CHANG
 * Channelgroup 表数据库控制层接口
 *
 */
public interface ChannelgroupMapper extends BaseMapper<Channelgroup> {
  
  List<Channelgroup> selectsiteandgroup(@Param("id") int id);
  
  String getWebsiteByUser(String email);
  
  Channelgroup getEntity(Channelgroup cg);
  
  
}