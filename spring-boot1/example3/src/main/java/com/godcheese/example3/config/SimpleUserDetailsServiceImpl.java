package com.godcheese.example3.config;


import com.godcheese.example3.entity.RoleEntity;
import com.godcheese.example3.entity.UserEntity;
import com.godcheese.example3.entity.UserRoleEntity;
import com.godcheese.example3.mapper.RoleMapper;
import com.godcheese.example3.mapper.UserMapper;
import com.godcheese.example3.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/5 17:41
 */
public class SimpleUserDetailsServiceImpl implements UserDetailsService {

    /**
     * $2a$10$tUxT7Ud3T6Dj4tXxoV8NX.LD9rTb2xiFdwd3RCJ.DaGGU6WhPDzQG
     * 123456
     */

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 从数据库中获取 user 实体
        UserEntity userEntity = userMapper.getByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("Username " + username +" not found");
        }
        return new User(username, userEntity.getPassword(), listSimpleGrantedAuthorityByUserId(userEntity.getId()));
    }

    private List<SimpleGrantedAuthority> listSimpleGrantedAuthorityByUserId(Long userId) {
        List<UserRoleEntity> userRoleEntityList = userRoleMapper.listByUserId(userId);
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        if(userRoleEntityList!=null){
            for(UserRoleEntity userRoleEntity : userRoleEntityList){
                RoleEntity roleEntity = roleMapper.getOne(userRoleEntity.getRoleId());
                if(roleEntity != null) {
                    String roleValue = roleEntity.getValue();
                    simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(roleValue));
                }
            }
        }
        return simpleGrantedAuthorityList;
    }
}
