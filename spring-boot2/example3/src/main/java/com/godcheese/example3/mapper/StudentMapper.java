package com.godcheese.example3.mapper;

import com.godcheese.example3.entity.StudentEntity;

public interface StudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudentEntity record);

    int insertSelective(StudentEntity record);

    StudentEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudentEntity record);

    int updateByPrimaryKey(StudentEntity record);
}