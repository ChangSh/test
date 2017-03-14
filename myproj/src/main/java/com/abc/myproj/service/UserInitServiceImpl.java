package com.abc.myproj.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abc.core.Common.tools.ReadExcel;
import com.abc.myproj.entity.City;
import com.abc.myproj.entity.Resource;
import com.abc.myproj.entity.Role;
import com.abc.myproj.entity.UserInit;
import com.abc.myproj.entity.UserRoleRelation;
import com.abc.myproj.mapper.RoleMapper;
import com.abc.myproj.mapper.UserInitMapper;
import com.abc.myproj.mapper.UserRoleRelationMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
*
* UserInit 表数据服务层接口实现类
*
*/
@Service
public class UserInitServiceImpl extends ServiceImpl<UserInitMapper, UserInit> implements IUserInitService {
  
  @Autowired
  private  UserInitMapper mapper;
  
  @Autowired
  private  UserRoleRelationMapper urmapper;
  
  @Autowired
  private  RoleMapper rolemapper;
  /**
   * 自动事务
   */
  @Override
  public boolean deleteBatchIds(List<? extends Serializable> idList) {
    return super.deleteBatchIds(idList);
  }


  @Override
  public List<Resource> myPermission(String username) {
    return mapper.myPermission(username);
  }


  @Override
  public List<Resource> mySource(String username) {
    return mapper.mySource(username);
  }


  @Override
  public List<UserInit> userlist(Pagination pag,UserInit user) {
    return mapper.userlist(pag,user);
  }


  @Override
  public List<City> myCity(String email) {
    return mapper.myCity(email);
  }


  @Override
  public boolean insertBatch(List<UserInit> entityList) {
    // TODO Auto-generated method stub
    
    
    return super.insertBatch(entityList);
    
  }


  @Override
  public boolean insertUserAndRole(UserInit user) {
    int result=mapper.insert(user);
    UserRoleRelation ur=new UserRoleRelation();
    ur.setRoleid(user.getRoleId());
    ur.setEmail(user.getEmail());
    
    int uresult=urmapper.insert(ur);
    return result+uresult==2?true:false;
  }
  @Override
  public boolean updateUserAndRole(UserInit user){
    String website = "";
    int result=mapper.updateById(user);
    UserRoleRelation ur=new UserRoleRelation();
    List<Integer> roleIds = user.getRoleIds();
    urmapper.deleteByEmail(user.getEmail());
    for(int i=0;i<roleIds.size();i++){
      ur.setRoleid(Integer.parseInt(roleIds.get(i).toString()));
      ur.setEmail(user.getEmail());
      urmapper.insert(ur);
    }

    EntityWrapper<UserInit> wrapper=new  EntityWrapper<UserInit>();   
    wrapper.where("email = {0} ",user.getEmail());
    mapper.update(user,wrapper);
    
    return result ==1?true:false;
  }
  @Override
  public boolean deleteUserAndRole(String[] idList) {
    List<UserInit> ur=mapper.selectBatchIds(Arrays.asList(idList));
    for(int i=0;i<ur.size();i++){
      urmapper.deleteByEmail(ur.get(i).getEmail());
    }
    int result=mapper.deleteBatchIds(Arrays.asList(idList));
   return result==1?true:false;
  }
  @Override  
  public boolean loadExcelFile(MultipartFile file) {  
      boolean result =true;  
      //创建处理EXCEL的类  
      ReadExcel readExcel=new ReadExcel();  
      //解析excel，获取上传的事件单  
      List<UserInit> useList = readExcel.getExcelInfo(file);  
      //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
      for(int i=0;i<useList.size();i++){
         String uuid=UUID.randomUUID().toString();
         useList.get(i).setUuid(uuid);
         int UserResult= 1;
         int a=mapper.ifExistUser(useList.get(i).getEmail());
         if( mapper.ifExistUser(useList.get(i).getEmail())== 0){
            UserResult= mapper.insert(useList.get(i));//插入用戶表
         }
         String roleName=useList.get(i).getRoleName();
         Role role=rolemapper.getByRoleName(roleName);
         
         UserRoleRelation ru=new UserRoleRelation();
         ru.setEmail(useList.get(i).getEmail());
         ru.setRoleid(role.getRoleid());
         int URResult=1;
         int b=urmapper.ifExistUserRole(ru);
         if(urmapper.ifExistUserRole(ru)==0){
           URResult= urmapper.insert(ru);//插入用戶角色关系表
         }
          
         result=UserResult+URResult==2?true && result:false;
      }  
      return result;  
  }


  @Override
  public List<City> allCity() {
    // TODO Auto-generated method stub
    return mapper.allCity();
  }

}
