package com.godcheese.example3.mapper;

import com.godcheese.example3.entity.GradeEntity;

public interface GradeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(GradeEntity record);

    int insertSelective(GradeEntity record);

    GradeEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GradeEntity record);

    int updateByPrimaryKey(GradeEntity record);
}