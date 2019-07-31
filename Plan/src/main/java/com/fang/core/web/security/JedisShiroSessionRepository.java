package com.fang.core.web.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.fang.core.Common.tools.BdpLogger;


/**
 * Jedis共享session实现类
 * 
 * @author user
 */
public class JedisShiroSessionRepository implements ShiroSessionRepository {
  
  /**
   * redis session key前缀
   */
  private static final String REDIS_SHIRO_SESSION = "shiro-session:";
  
  /**
   * 毫秒与秒转化系数
   */
  private static final int PARAM = 1000;
  
  /**
   * spring-data-redis封装jredis对象
   */
  private RedisTemplate<Serializable, Serializable> redisTemplate;
  
  @Override
  public void saveSession(final Session session) {
    if (session == null || session.getId() == null) {
      BdpLogger.error("session或者session id为空");
      return;
    }
    try {
      redisTemplate.execute(new RedisCallback<Object>() {
        
        public Object doInRedis(RedisConnection connection) {
          // 获取key的序列化器
          RedisSerializer<Serializable> keyserializer = (RedisSerializer<Serializable>) redisTemplate
              .getKeySerializer();
          byte[] key = keyserializer.serialize(getRedisSessionKey(session.getId()));
          // 获取value的序列号器
          RedisSerializer<Session> valueserializer = (RedisSerializer<Session>) redisTemplate
              .getValueSerializer();
          byte[] value = valueserializer.serialize(session);
          connection.set(key, value);
          Long timeOut = session.getTimeout() / PARAM;
          connection.expire(key, Integer.parseInt(timeOut.toString()));
          return null;
        }
      });
    }
    catch (org.springframework.dao.DataAccessResourceFailureException e) {
      BdpLogger.error("持久化session失败，请检查redis数据库是否正常连接");
    }
  }
  
  @Override
  public void deleteSession(final Serializable sessionId) {
    if (sessionId == null) {
      BdpLogger.error("sessionid为空");
      return;
    }
    try {
      redisTemplate.execute(new RedisCallback<Object>() {
        
        public Object doInRedis(RedisConnection connection) {
          // 获取key的序列化器
          RedisSerializer<Serializable> keyserializer = (RedisSerializer<Serializable>) redisTemplate
              .getKeySerializer();
          byte[] key = keyserializer.serialize(getRedisSessionKey(sessionId));
          connection.del(key);
          
          return null;
        }
      });
    }
    catch (org.springframework.dao.DataAccessResourceFailureException e) {
      BdpLogger.error("删除session失败，请检查redis数据库是否正常连接");
    }
  }
  
  @Override
  public Session getSession(final Serializable sessionId) {
    if (sessionId == null) {
      BdpLogger.error("id为空");
      return null;
    }
    Session session = null;
    try {
      session = redisTemplate.execute(new RedisCallback<Session>() {
        
        public Session doInRedis(RedisConnection connection) {
          // 获取key的序列化器
          RedisSerializer<Serializable> keyserializer = (RedisSerializer<Serializable>) redisTemplate
              .getKeySerializer();
          byte[] key = keyserializer.serialize(getRedisSessionKey(sessionId));
          // 获取value的序列号器
          RedisSerializer<Session> valueserializer = (RedisSerializer<Session>) redisTemplate
              .getValueSerializer();
          byte[] value = connection.get(key);
          Session session = valueserializer.deserialize(value);
          
          return session;
        }
      });
    }
   catch (org.springframework.dao.DataAccessResourceFailureException e) {
      BdpLogger.error("获取session失败，请检查redis数据库是否正常连接");
   }
    
    return session;
  }
  
  @Override
  public Collection<Session> getAllSessions() {
    Set<Session> sessions = null;
    try {
      sessions = redisTemplate.execute(new RedisCallback<Set<Session>>() {
        
        public Set<Session> doInRedis(RedisConnection connection) {
          
          Set<Session> sessions = new HashSet<Session>();
          // 获取key的序列化器
          RedisSerializer<Serializable> keyserializer = (RedisSerializer<Serializable>) redisTemplate
              .getKeySerializer();
          // 模糊查询shirosession
          Set<byte[]> byteKeys = connection.keys(keyserializer.serialize(REDIS_SHIRO_SESSION + "*"));
          if (byteKeys != null && byteKeys.size() > 0) {
            for (byte[] bs : byteKeys) {
              RedisSerializer<Session> valueserializer = (RedisSerializer<Session>) redisTemplate
                  .getValueSerializer();
              byte[] value = connection.get(bs);
              Session s = valueserializer.deserialize(value);
              sessions.add(s);
            }
          }
          return sessions;
        }
      });
    }
    catch (org.springframework.dao.DataAccessResourceFailureException e) {
      BdpLogger.error("获取当前活动session失败，请检查redis数据库是否正常连接");
    }
    
    return sessions;
  }
  
  /**
   * 拼接redis中的session key 用于设置前缀方便模糊查询
   * 
   * @param sessionId
   *        sessionId
   * @return redis中的session key
   */
  private String getRedisSessionKey(Serializable sessionId) {
    return this.REDIS_SHIRO_SESSION + sessionId;
  }
  
  /**
   * @return redisTemplate
   */
  public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
    return redisTemplate;
  }
  
  /**
   * @param redisTemplate
   *        set redisTemplate
   */
  public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }
  
}
