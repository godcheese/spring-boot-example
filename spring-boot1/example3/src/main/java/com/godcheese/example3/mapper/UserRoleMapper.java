package com.godcheese.example3.mapper;

import com.godcheese.example3.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/5 18:37
 */
@Mapper
@Component
public interface UserRoleMapper {

    List<UserRoleEntity> listByUserId(Long userId);
}
