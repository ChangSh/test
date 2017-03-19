package com.fang.plan.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.plan.entity.AdPlacePrice;
import com.fang.plan.entity.BannerAdplaceRelation;
import com.fang.plan.entity.Plan_Detail;
import com.fang.plan.mapper.Plan_DetailMapper;

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
      String orderid=entityList.getOrderid();
      int porderid=entityList.getPorderid();
      if(!ckeck(list,orderid,porderid)){
      
        StringBuffer sb=new StringBuffer(); 
        for(int i = 0 ; i < list.size() ; i++) {
          String sql=String.format("DELETE FROM Plan_Detail_%s_%s  WHERE OrderId = %s and KJ_ADP_ID=%s and (State<=%d or State=%d ) and Operators=%s", list.get(i).getYear(),list.get(i).getMonth(),"'"+orderid+"'",list.get(i).getKj_adp_id(),1,20,"'"+username+"'");
          planmapper.deletes(sql);
           if(list.get(i).getD()!=null){
             sb.append(ReturnSql(list.get(i),username,orderid,porderid));
           }         
        }
        planmapper.insertPlan_Detail(sb.toString());
  
        //计算金额
        StringBuffer sqlw=new StringBuffer(); 
        int iflog=0;
        for(int i = 0 ; i < list.size() ; i++) { 
          if(list.get(i).getD()!=null){
             if(iflog>0){
               sqlw.append(" UNION  ALL ");
             }
             sqlw.append(" SELECT ReserveDate,ADPId,ReserveHour, CASE WHEN State=20 THEN 0 ELSE 1 END AS IsPay  FROM Plan_Detail_"+list.get(i).getYear()+"_"+list.get(i).getMonth()+" WHERE OrderId='"+list.get(i).getOrderid()+"' AND POrderId="+list.get(i).getPorderid()+" ");
             iflog++;
          }
        }      
        planmapper.selectPrice(sqlw.toString());
        
        flog= true;
      }
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      throw e;  
    }
    return flog;
  }
  
  //多人同时提交 可能存在数据不及时显示到页面 页面无法控制  需要后台校验
  public boolean ckeck(List<Plan_Detail> list,String orderid,int porderid){
    boolean flog=false;
    StringBuffer sb=new StringBuffer(); 
    int iflog=0;
    for(int i = 0 ; i < list.size() ; i++) {
       if(list.get(i).getD()!=null){
         if(iflog>0){
           sb.append(" UNION  ALL ");
         }
        sb.append("SELECT   State,ReserveHour,KJ_ADP_ID,year(ReserveDate) AS year,month(ReserveDate) AS month,day(ReserveDate) AS reservedate")
            .append(" FROM Plan_Detail_"+list.get(i).getYear()+"_"+list.get(i).getMonth()+" WHERE  KJ_ADP_ID="+list.get(i).getKj_adp_id()+" and OrderId!='"+orderid+"'  and POrderId="+porderid+ " and state>1");
        iflog++;
       }         
    }
    List<Plan_Detail> re= planmapper.selectCheck(sb.toString());
    if(re!=null){
      for(int i = 0 ; i < list.size() ; i++) {
          for(int j=0;j<re.size();j++){
            if(list.get(i).getD()!=null){
              boolean by=list.get(i).getYear()==re.get(j).getYear();
              boolean bm=list.get(i).getMonth()==re.get(j).getMonth();
              if(by&&bm){
                List<String> m=list.get(i).getD();
                for(int k=0;k<m.size();k++){
                  if(m.get(k).equals(re.get(j).getReservedate())){
                    flog=true;
                    break;
                  }
                }
              }
            }
          }
      }
    }
    return flog;
  }
  
  
  
  public String ReturnSql(Plan_Detail entityList,String username,String orderid,int porderid){
    List<String> list=entityList.getD();
    List<String> ispay=entityList.getIspay();
    StringBuffer sb=new StringBuffer(); 
    if(list!=null){
      for(int i=0;i<list.size();i++){
        if(!list.get(i).equals(null)){
          sb.append( " INSERT INTO dbo.Plan_Detail_"+entityList.getYear()+"_"+entityList.getMonth())
            .append(" (OrderId, POrderId, KJ_ADP_ID, ADPId, FrameNo, ReserveDate, ReserveHour, CitySimpleName, Allowance, State, Operators, OperateTime)")
            .append("VALUES ('"+orderid+"', "+porderid+", '"+entityList.getKj_adp_id()+"', "+entityList.getAdpid()+", 1, '"+entityList.getYear()+"-"+entityList.getMonth()+"-"+list.get(i)+"', "+entityList.getReservehour()+", '', "+(entityList.getReservehour()==0?1:0.5)+", "+(ispay.get(i).equals("0")?20:1)+", '"+username+"', getdate()); \n "); 
        }
        if(i==0){
          if(entityList.getReservehour()==1){
            sb.append( " INSERT INTO dbo.Plan_Detail_"+entityList.getYear()+"_"+entityList.getMonth())
            .append(" (OrderId, POrderId, KJ_ADP_ID, ADPId, FrameNo, ReserveDate, ReserveHour, CitySimpleName, Allowance, State, Operators, OperateTime)")
            .append("  VALUES ('"+orderid+"', "+porderid+", '"+entityList.getKj_adp_id()+"', "+entityList.getAdpid()+", 1, '"+entityList.getYear()+"-"+entityList.getMonth()+"-"+1+"', 2, '', 0.5, 0, '"+username+"', getdate()); \n ");
          }
          if(entityList.getReservehour()==2){
            sb.append( " INSERT INTO dbo.Plan_Detail_"+entityList.getYear()+"_"+entityList.getMonth())
            .append(" (OrderId, POrderId, KJ_ADP_ID, ADPId, FrameNo, ReserveDate, ReserveHour, CitySimpleName, Allowance, State, Operators, OperateTime)")
            .append(" VALUES ('"+orderid+"', "+porderid+", '"+entityList.getKj_adp_id()+"', "+entityList.getAdpid()+", 1, '"+entityList.getYear()+"-"+entityList.getMonth()+"-"+1+"', 1, '', 0.5, 0, '"+username+"', getdate()); \n ") ;           
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
            String sql="update dbo.Plan_Detail_"+year+"_"+month+" set State=3 where id='"+bar.get(i).getAdplaceid()+"'"; 
            planmapper.updateState(sql);
          }
          return true;
    }catch (Exception e) {
      return false;
    }
  }
}
