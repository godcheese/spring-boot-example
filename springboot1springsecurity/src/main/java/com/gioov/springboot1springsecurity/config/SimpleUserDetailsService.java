package com.gioov.springboot1springsecurity.config;

import com.gioov.springboot1springsecurity.mapper.*;
import com.gioov.springboot1springsecurity.mapper.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SimpleUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUserDetailsService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 从数据库中获取 user 实体
        UserEntity userEntity = userMapper.getByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("Username " + username +" not found");
        }
        return new User(username, userEntity.getPassword(), listSimpleGrantedAuthorityByUserId(userEntity.getId()));

    }

    /**
     * 列出用户所拥有的角色
     * @param userId
     * @return
     */
    private List<SimpleGrantedAuthority> listSimpleGrantedAuthorityByUserId(Long userId) {
        List<UserRoleEntity> userRoleEntityList = userRoleMapper.listByUserId(userId);
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        if(userRoleEntityList!=null){
            for(UserRoleEntity userRoleEntity : userRoleEntityList){
                RoleEntity roleEntity = roleMapper.getOne(userRoleEntity.getRoleId());
                if(roleEntity != null) {

                    Long roleId = roleEntity.getId();
                    String roleName = roleEntity.getValue();

                    simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(roleName));

                    List<SimpleGrantedAuthority> simpleGrantedAuthorityList1 = listSimpleGrantedAuthorityByRoleId(roleId);
                    if(simpleGrantedAuthorityList1!=null && !simpleGrantedAuthorityList1.isEmpty()){
                        simpleGrantedAuthorityList.addAll(simpleGrantedAuthorityList1);
                    }
                }
            }
        }
        return simpleGrantedAuthorityList;
    }


    /**
     * 列出角色所拥有的权限
     * @param roleId
     * @return
     */
    private List<SimpleGrantedAuthority> listSimpleGrantedAuthorityByRoleId(Long roleId) {
        LOGGER.info("roleId={}",roleId);
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        
        List<RoleAuthorityEntity> roleAuthorityEntityList = roleAuthorityMapper.listByRoleId(roleId);
        if(roleAuthorityEntityList!=null){
            for(RoleAuthorityEntity roleAuthorityEntity : roleAuthorityEntityList) {
                AuthorityEntity authorityEntity = authorityMapper.getOne(roleAuthorityEntity.getAuthorityId());
                LOGGER.info("authorityEntity={}",authorityEntity);
                if(authorityEntity!=null) {
                    simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(authorityEntity.getValue()));
                }
            }
        }

        return simpleGrantedAuthorityList;
    }

}
