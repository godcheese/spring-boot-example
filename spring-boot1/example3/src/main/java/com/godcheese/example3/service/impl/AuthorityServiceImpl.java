package com.godcheese.example3.service.impl;

import com.godcheese.example3.entity.AuthorityEntity;
import com.godcheese.example3.entity.RoleAuthorityEntity;
import com.godcheese.example3.mapper.AuthorityMapper;
import com.godcheese.example3.mapper.RoleAuthorityMapper;
import com.godcheese.example3.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/7 20:04
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public List<AuthorityEntity> listByRoleId(Long roleId) {

        List<AuthorityEntity> authorityEntityList = new ArrayList<>();
        List<RoleAuthorityEntity> roleAuthorityEntityList = roleAuthorityMapper.listByRoleId(roleId);
        if(roleAuthorityEntityList!=null){
            for(RoleAuthorityEntity roleAuthorityEntity : roleAuthorityEntityList) {
                AuthorityEntity authorityEntity = authorityMapper.getOne(roleAuthorityEntity.getAuthorityId());
                if(authorityEntity!=null) {
                    authorityEntityList.add(authorityEntity);
                }
            }
        }

        return authorityEntityList;
    }
}
