package com.abc.myproj.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.myproj.entity.BannerAdplaceRelation;
import com.abc.myproj.entity.Plan_Detail;
import com.abc.myproj.mapper.Plan_DetailMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
*
* PlanTb 表数据服务层接口实现类
*
*/
@Service
public class Plan_DetailServiceImpl extends ServiceImpl<Plan_DetailMapper, Plan_Detail> implements IPlan_DetailService {

  @Autowired
  private  Plan_DetailMapper planmapper;
  
  public boolean insertBatchs(Plan_Detail entityList,String username) {
    // TODO Auto-generated method stub
    boolean flog=false;
    try {
      List<Plan_Detail> list=entityList.getE();
      StringBuffer sb=new StringBuffer(); 
      for(int i = 0 ; i < list.size() ; i++) {
        String sql=String.format("DELETE FROM Plan_Detail_%s_%s  WHERE OrderId = %s and ADPId=%d and State<=%d", list.get(i).getYear(),list.get(i).getMonth(),"'BJ2015010155445'",6,1);
        planmapper.deletes(sql);
         if(list.get(i).getD()!=null){
           sb.append(ReturnSql(list.get(i),username));
         }         
      }
      planmapper.insertPlan_Detail(sb.toString());
      flog= true;
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      throw e;  
    }
    return flog;
  }
  
  public String ReturnSql(Plan_Detail entityList,String username){
    List<String> list=entityList.getD();
    StringBuffer sb=new StringBuffer(); 
    if(list!=null){
      for(int i=0;i<list.size();i++){
        if(!list.get(i).equals(null)){
          sb.append( " INSERT INTO dbo.Plan_Detail_"+entityList.getYear()+"_"+entityList.getMonth())
            .append(" (OrderId, POrderId, KJ_ADP_ID, ADPId, FrameNo, ReserveDate, ReserveHour, CitySimpleName, Allowance, State, Operators, OperateTime)")
            .append("VALUES ('BJ2015010155445', 1, '6', 6, 1, '"+entityList.getYear()+"-"+entityList.getMonth()+"-"+list.get(i)+"', "+entityList.getReservehour()+", 'bj', "+(entityList.getReservehour()==0?1:0.5)+", 1, '"+username+"', getdate()); \n "); 
        }
        if(i==0){
          if(entityList.getReservehour()==1){
            sb.append( " INSERT INTO dbo.Plan_Detail_"+entityList.getYear()+"_"+entityList.getMonth())
            .append(" (OrderId, POrderId, KJ_ADP_ID, ADPId, FrameNo, ReserveDate, ReserveHour, CitySimpleName, Allowance, State, Operators, OperateTime)")
            .append("  VALUES ('BJ2015010155445', 1, '6', 6, 1, '"+entityList.getYear()+"-"+entityList.getMonth()+"-"+1+"', 2, 'bj', 0.5, 0, '"+username+"', getdate()); \n ");
          }
          if(entityList.getReservehour()==2){
            sb.append( " INSERT INTO dbo.Plan_Detail_"+entityList.getYear()+"_"+entityList.getMonth())
            .append(" (OrderId, POrderId, KJ_ADP_ID, ADPId, FrameNo, ReserveDate, ReserveHour, CitySimpleName, Allowance, State, Operators, OperateTime)")
            .append(" VALUES ('BJ2015010155445', 1, '6', 6, 1, '"+entityList.getYear()+"-"+entityList.getMonth()+"-"+1+"', 1, 'bj', 0.5, 0, '"+username+"', getdate()); \n ") ;           
          }        
        }
      }    
     
    }
    else{
      sb.append( "SELECT 0");
    }
    return sb.toString();
  }

  @Override
  public boolean updateState(BannerAdplaceRelation entity) {
    
    try {
         List<BannerAdplaceRelation> bar=entity.getE();
          for(int i = 0 ; i < bar.size() ; i++){
            String date=bar.get(i).getReservedate();
            date=date.replaceAll("-", "/");
            Date d=new Date(date);
            int year=d.getYear()+1900;
            int month=d.getMonth()+1;
            /*Plan_Detail pd=new Plan_Detail();
            pd.setState("3");
            pd.setId(Integer.toString(bar.get(i).getAdplaceid()));*/
            String sql="update dbo.Plan_Detail_"+year+"_"+month+" set State=3 where id='"+bar.get(i).getAdplaceid()+"'"; 
            planmapper.updateState(sql);
          }
          return true;
    }catch (Exception e) {
      return false;
    }
  }
}
