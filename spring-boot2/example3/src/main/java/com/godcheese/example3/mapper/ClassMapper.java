package com.godcheese.example3.mapper;

import com.github.pagehelper.Page;
import com.godcheese.example3.entity.ClassEntity;

import java.util.List;

public interface ClassMapper {

    int insertOne(ClassEntity classEntity);

    int insertAll(List<ClassEntity> classEntityList);

    int deleteOne(Long id);

    int deleteAll(List<Long> idList);

    int updateOne(ClassEntity classEntity);

    int updateAll(List<ClassEntity> classEntityList);

    ClassEntity selectOne(Long id);

    List<ClassEntity> selectAll(List<Long> idList);

    Page<ClassEntity> pageAll(ClassEntity classEntity);

    List<ClassEntity> search(ClassEntity classEntity);

    Page<ClassEntity> pageSearch(ClassEntity classEntity);
}