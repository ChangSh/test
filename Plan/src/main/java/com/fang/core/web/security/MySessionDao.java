/**
 * File：MySessionDao.java
 * Package：com.fang.bdp.core.web.security
 * Author：wangjiashuai
 * Date：2015-6-11 下午3:26:24
 * Copyright (C) 2003-2015 搜房资讯有限公司-版权所有
 */
package com.fang.core.web.security;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.util.CollectionUtils;

import com.fang.core.Common.tools.Constants;
import com.fang.core.Common.tools.PropertiesReaderUtils;
import com.fang.plan.entity.OnlineUser;



/**
 * 
 * session处理相关Dao，继承{@link org.apache.shiro.session.mgt.eis.AbstractSessionDAO}
 * 
 * @author wangjiashuai
 */
public class MySessionDao extends AbstractSessionDAO  {
  
  /**
   * 日期格式字符串
   */
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  /**
   * 超时时间
   */
  public static final String TIMEOUT = PropertiesReaderUtils.getProperties(Constants.COMM_PATH
      + "constants/constants.properties", "timeout");
  
  /**
   * 在线session
   */
  private ConcurrentMap<Serializable, Session> sessions;
  
  /**
   * con
   */
  public MySessionDao() {
    this.sessions = new ConcurrentHashMap<Serializable, Session>();
  }
  

  public List<OnlineUser> queryAll() {
    List<OnlineUser> onlineUserList = new ArrayList<OnlineUser>();
    Collection<Session> values = sessions.values();
    for (Session session : values) {
      Object nameObj = session
          .getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
      if (nameObj != null) {
        onlineUserList.add(new OnlineUser(session.getHost(), nameObj.toString(), DATE_FORMAT
            .format(session.getStartTimestamp()), DATE_FORMAT.format(session.getLastAccessTime())));
      }
    }
    return onlineUserList;
  }
  
  @Override
  public Collection<Session> getActiveSessions() {
    Collection<Session> values = sessions.values();
    if (CollectionUtils.isEmpty(values)) {
      return Collections.emptySet();
    }
    else {
      return Collections.unmodifiableCollection(values);
    }
  }
  
  @Override
  protected Serializable doCreate(Session session) {
    Serializable sessionId = generateSessionId(session);
    assignSessionId(session, sessionId);
    storeSession(sessionId, session);
    return sessionId;
  }
  
  /**
   * store session
   * 
   * @param id
   *        id
   * @param session
   *        session
   * @return Session
   */
  protected Session storeSession(Serializable id, Session session) {
    if (id == null) {
      throw new NullPointerException("id argument cannot be null.");
    }
    return sessions.putIfAbsent(id, session);
  }
  
  @Override
  protected Session doReadSession(Serializable sessionId) {
    return sessions.get(sessionId);
  }
  
  @Override
  public void update(Session session) {
    // Long timeOut =
    // Long.parseLong(systemParamService.searchSystemParam(SystemParamEnum.TIMEOUT).getSystemParamVal());
    session.setTimeout(Integer.parseInt(TIMEOUT));
    storeSession(session.getId(), session);
  }
  
  @Override
  public void delete(Session session) {
    if (session == null) {
      throw new NullPointerException("session argument cannot be null.");
    }
    Serializable id = session.getId();
    if (id != null) {
      sessions.remove(id);
    }
  }
}
