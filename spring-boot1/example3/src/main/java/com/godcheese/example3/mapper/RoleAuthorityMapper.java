package com.godcheese.example3.mapper;

import com.godcheese.example3.entity.RoleAuthorityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/5 18:39
 */
@Mapper
@Component
public interface RoleAuthorityMapper {

    List<RoleAuthorityEntity> listByRoleId(Long userId);
}
