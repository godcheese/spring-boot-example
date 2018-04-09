package com.gioov.springboot1springsecurity.service.impl;

import com.gioov.springboot1springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/8 16:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SessionRegistry sessionRegistry;

    public boolean grantRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getDetails();
        return false;
    }


    /**
     * 修改用户角色或权限时必需加上此方法才能立即生效，暂未测试
     * @param user
     */
    private void invalidateSession(User user){
        List<Object> o= sessionRegistry.getAllPrincipals();
        for (Object principal : o) {
            if (principal instanceof User) {
                final User loggedUser = (User) principal;
                if (user.getUsername().equals(loggedUser.getUsername())) {
                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                    if (null != sessionsInfo && sessionsInfo.size() > 0) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            sessionInformation.expireNow();
                        }
                    }
                }
            }
        }
    }
}
