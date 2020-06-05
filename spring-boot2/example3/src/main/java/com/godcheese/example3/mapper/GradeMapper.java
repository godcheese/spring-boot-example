package com.godcheese.example3.mapper;

import com.github.pagehelper.Page;
import com.godcheese.example3.entity.GradeEntity;

import java.util.List;

public interface GradeMapper {

    int insertOne(GradeEntity gradeEntity);

    int insertAll(List<GradeEntity> gradeEntityList);

    int deleteOne(Long id);

    int deleteAll(List<Long> idList);

    int updateOne(GradeEntity gradeEntity);

    int updateAll(List<GradeEntity> gradeEntityList);

    GradeEntity selectOne(Long id);

    List<GradeEntity> selectAll(List<Long> idList);

    Page<GradeEntity> pageAll(GradeEntity gradeEntity);

    List<GradeEntity> search(GradeEntity gradeEntity);

    Page<GradeEntity> pageSearch(GradeEntity gradeEntity);
}