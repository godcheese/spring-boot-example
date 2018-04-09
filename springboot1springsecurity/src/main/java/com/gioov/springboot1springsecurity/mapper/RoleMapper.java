package com.gioov.springboot1springsecurity.mapper;

import com.gioov.springboot1springsecurity.mapper.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/5 18:39
 */
@Mapper
@Component
public interface RoleMapper {

    RoleEntity getOne(Long id);

}
