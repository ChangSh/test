/**
 * File：MyShiro.java
 * Package：com.fang.platform.core.security
 * Author：wangjiashuai
 * Date：2015-5-8 下午2:08:41
 * Copyright (C) 2003-2015 搜房资讯有限公司-版权所有
 */
package com.abc.core.web.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.abc.myproj.entity.Resource;
import com.abc.myproj.entity.UserInit;
import com.abc.myproj.service.IUserInitService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

/**
 * 自定义 realm
 * 
 * @author wangjiashuai
 */

public class MyShiroRealm extends AuthorizingRealm {
  
  /**
   * 用户服务
   */
 @Autowired(required=true)
 private IUserInitService iUserInitService;
  
  /**
   * 权限认证
   * 
   * @param principalCollection
   *        the primary identifying principals of the AuthorizationInfo that should be retrieved.
   * @return the AuthorizationInfo associated with this principals.
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();

    UserInit user = iUserInitService.selectOne(new EntityWrapper<UserInit>().eq("Email", loginName));
    
    if (user != null) {
      // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
      SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
      // 用户的角色集合
      // info.setRoles(user.getRolesName());
      // 用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
      List<Resource> resource=iUserInitService.myPermission(loginName);
      List<String> perm=new ArrayList<String>();
      for(int i=0;i<resource.size();i++){
       perm.add(resource.get(i).getAuthUrl());
      }
      info.addStringPermissions(perm);
      return info;
    }
    return null;
  }
  
  /**
   * 登录认证;
   * 
   * @param authenticationToken
   *        the authentication token containing the user's principal and credentials.
   * @return an {@link AuthenticationInfo} object containing account data resulting from the
   *         authentication ONLY if the lookup is successful (i.e. account exists and is valid,
   *         etc.)
   * @throws AuthenticationException
   *         if there is an error acquiring data or performing
   *         realm-specific authentication logic for the specified <tt>token</tt>
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    // UsernamePasswordToken对象用来存放提交的登录信息
    UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
    UserInit user = iUserInitService.selectOne(new EntityWrapper<UserInit>().eq("email", token.getUsername()));
    if (user != null) {
      // 若存在，将此用户存放到登录认证info中
      return new SimpleAuthenticationInfo(user.getEmail(), "", getName());//user.getPassword() 暂时替换 陈军
    }
    return null;
  }
  
  /** 重写退出时缓存处理方法 */
  protected void doClearCache(PrincipalCollection principalcollection) {
    clearCachedAuthorizationInfo(principalcollection);
  }
  
}