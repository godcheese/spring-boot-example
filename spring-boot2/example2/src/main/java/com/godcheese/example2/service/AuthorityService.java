package com.godcheese.example2.service;

import com.godcheese.example2.mapper.entity.AuthorityEntity;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/7 20:04
 */
public interface AuthorityService {

    List<AuthorityEntity> listByRoleId(Long roleId);
}
