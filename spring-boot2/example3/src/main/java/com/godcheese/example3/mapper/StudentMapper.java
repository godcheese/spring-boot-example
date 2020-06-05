package com.godcheese.example3.mapper;

import com.github.pagehelper.Page;
import com.godcheese.example3.entity.StudentEntity;

import java.util.List;

public interface StudentMapper {

    int insertOne(StudentEntity studentEntity);

    int insertAll(List<StudentEntity> studentEntityList);

    int deleteOne(Long id);

    int deleteAll(List<Long> idList);

    int updateOne(StudentEntity studentEntity);

    int updateAll(List<StudentEntity> studentEntityList);

    StudentEntity selectOne(Long id);

    List<StudentEntity> selectAll(List<Long> idList);

    Page<StudentEntity> pageAll(StudentEntity studentEntity);

    List<StudentEntity> search(StudentEntity studentEntity);

    Page<StudentEntity> pageSearch(StudentEntity studentEntity);
}