package com.gioov.springboot1springsecurity.mapper;

import com.gioov.springboot1springsecurity.mapper.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018/4/5 17:49
 */
@Mapper
@Component
public interface UserMapper {

    UserEntity getByUsername(String username);

    UserEntity getOne(Long id);

    UserEntity deleteOne(Long id);

    List<UserEntity> listAll();

}
