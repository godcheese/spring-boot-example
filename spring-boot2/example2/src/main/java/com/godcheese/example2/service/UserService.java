package com.godcheese.example2.service;

import org.springframework.security.core.userdetails.User;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/8 16:21
 */
public interface UserService {

    public boolean grantRole();

    /**
     * 修改用户角色或权限时必需加上此方法才能立即生效，暂未测试
     * @param user
     */
    public void invalidateSession(User user);
}
