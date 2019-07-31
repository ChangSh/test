package com.abc.core.web.security;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.abc.myproj.entity.OnlineUser;

/**
 * session持久化 用于shiro实现session共享
 * 
 * @author zhangjiayi
 */
public class MySessionDaoForRedis extends AbstractSessionDAO  {
  
  /**
   * 日期格式字符串
   */
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  /**
   * 在线session
   */
  private ConcurrentMap<Serializable, Session> sessions;
  /* 
  @Autowired
  private SystemParamService systemParamService;*/
  
  /**
   * session共享实现类
   */
  @Autowired
  @Qualifier("shiroSessionRepository")
  private ShiroSessionRepository shiroSessionRepository;

  /**
   * con
   */
  public MySessionDaoForRedis() {
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
    //shiro原来的dao存储
    storeSession(sessionId, session);
    //自定义存储
    shiroSessionRepository.saveSession(session);
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
    return shiroSessionRepository.getSession(sessionId);
  }
  
  @Override
  public void update(Session session) {
    session.setTimeout(24*60*60000);
    storeSession(session.getId(), session);
    shiroSessionRepository.saveSession(session);
  }
  
  @Override
  public void delete(Session session) {
    if (session == null) {
      throw new NullPointerException("session argument cannot be null.");
    }
    Serializable id = session.getId();
    if (id != null) {
      sessions.remove(id);
      shiroSessionRepository.deleteSession(id);
    }
  }
  
  
}
