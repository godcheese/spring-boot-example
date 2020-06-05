package com.godcheese.example3.mapper;

import com.godcheese.example3.entity.ClassEntity;

public interface ClassDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClassEntity record);

    int insertSelective(ClassEntity record);

    ClassEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClassEntity record);

    int updateByPrimaryKey(ClassEntity record);
}