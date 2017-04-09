package com.test.mysys.common.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.entity.Record;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;


public class PushtoPhone {
    
	//工人端
    static String appId = "b03c5cfef65ed30108f0a3fd82c3f6b4";
    static String appkey = "110000";
    static String master = "D9F0dsPkhv6UZ0ap1O3zJ8";
    //客户端
    static String c_appId = "b03c5cfef65ed30108f0a3fd82c3f6b4";
    static String c_appkey = "110000";
    static String c_master = "D9F0dsPkhv6UZ0ap1O3zJ8";
    
    static String host = "http://sdk.open.api.igexin.com/serviceex";

    public static void pushToCustom(List<Record> records,Map map) throws Exception {
    	push(records,map,c_appId,c_appkey,c_master);
    }
    public static void pushToEmplee(List<Record> records,Map map) throws Exception {
    	push(records,map,appId,appkey,master);
    }
    
    private static void push(List<Record> records,Map map,String appId,String appkey,String master) throws Exception {
        //配置返回每个用户返回用户状态，可选
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(host, appkey, master);
          
        //通知透传模板
        NotificationTemplate template = notificationTemplateDemo(c_appId,c_appkey); 
        String sJson ="{\"orderID\":\"%s\",\"orderSN\":\"%s\"}";
        sJson = String.format(sJson,map.get("o_id"),map.get("order_code"));
        
        template.setTransmissionContent(sJson); // 传o_id
        template.setTitle(map.get("title")+"");//传title
        
        ListMessage message = new ListMessage();
        message.setData(template);
    
        //设置消息离线，并设置离线时间
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24*1000*3600);
        //配置推送目标
        List targets = new ArrayList();
        for (Record record : records) {
            Target target = new Target();
            target.setAppId(appId);
            target.setClientId(record.get("device_token").toString());
            targets.add(target);
		}
        //获取taskID
        String taskId = push.getContentId(message);
        //使用taskID对目标进行推送
        IPushResult ret = push.pushMessageToList(taskId, targets);
        //打印服务器返回信息
       // System.out.println(ret.getResponse().toString());
    }
    
    private static NotificationTemplate notificationTemplateDemo(String appId,String appkey) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appkey);
        // 设置通知栏标题与内容
        template.setTitle("请输入通知栏标题");
        template.setText("");
        // 配置通知栏图标
        //template.setLogo("icon.png");
        // 配置通知栏网络图标
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        //template.setTransmissionContent("请输入您要透传的内容");
        return template;
    }
    

    public static void main(String[] args) throws Exception {
//        //配置返回每个用户返回用户状态，可选
//        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
//        IGtPush push = new IGtPush(host, appkey, master);
//          
//        //通知透传模板
//        NotificationTemplate template = notificationTemplateDemo();   
//        ListMessage message = new ListMessage();
//        message.setData(template);
//    
//        //设置消息离线，并设置离线时间
//        message.setOffline(true);
//        //离线有效时间，单位为毫秒，可选
//        message.setOfflineExpireTime(24*1000*3600);
//    
//        //配置推送目标
//        List targets = new ArrayList();
//        Target target1 = new Target();
//        Target target2 = new Target();
//        target1.setAppId(appId);
//         //用户别名推送，cid和用户别名2者只能选其一
//        //String alias1 = "个";
//        //target1.setAlias(alias1);
//        target1.setClientId(CID1);
//        target2.setAppId(appId);
//        target2.setClientId(CID2);
//        //用户别名推送，cid和用户别名2者只能选其一
//        //String alias2 = "个推";
//        //target2.setAlias(alias2);
//        targets.add(target1);
//        targets.add(target2);
//        //获取taskID
//        String taskId = push.getContentId(message);
//        //使用taskID对目标进行推送
//        IPushResult ret = push.pushMessageToList(taskId, targets);
//        //打印服务器返回信息
//        System.out.println(ret.getResponse().toString());
    }
}